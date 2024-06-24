package com.pda.userapplication.services;

import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.exceptionhandler.exceptions.InternalServerException;
import com.pda.tofinenums.user.UserRole;
import com.pda.tofinsecurity.jwt.JwtProvider;
import com.pda.tofinsecurity.jwt.TokenInfo;
import com.pda.tofinsecurity.jwt.TokenableUser;
import com.pda.userapplication.domains.User;
import com.pda.userapplication.domains.UserDetail;
import com.pda.userapplication.domains.vo.UserId;
import com.pda.userapplication.services.in.FinfluencerUseCase;
import com.pda.userapplication.services.in.dto.req.ExchangeServiceRequest;
import com.pda.userapplication.services.in.dto.res.TokenInfoServiceResponse;
import com.pda.userapplication.services.out.CreditOutputPort;
import com.pda.userapplication.services.out.ReadFollowOutputPort;
import com.pda.userapplication.services.out.ReadUserDetailOutputPort;
import com.pda.userapplication.services.out.ReadUserOutputPort;
import com.pda.userapplication.services.out.RefreshTokenOutputPort;
import com.pda.userapplication.services.out.SaveAssetsOutputPort;
import com.pda.userapplication.services.out.SaveUserOutputPort;
import com.pda.userapplication.services.out.SendCreditOutputPort;
import com.pda.userapplication.services.out.SendUpdateUserOutputPort;
import com.pda.userapplication.services.out.dto.req.SendCreditOutputRequest;
import com.pda.userapplication.services.out.dto.req.TransferCashRequest;
import com.pda.userapplication.services.out.dto.req.UserUpdateOutputRequest;
import com.pda.userapplication.services.out.dto.res.FollowInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FinfluencerService implements FinfluencerUseCase {
    private final SaveUserOutputPort saveUserOutputPort;
    private final ReadUserOutputPort readUserOutputPort;
    private final ReadFollowOutputPort readFollowOutputPort;
    private final ReadUserDetailOutputPort readUserDetailOutputPort;
    private final SendUpdateUserOutputPort sendUpdateUserOutputPort;
    private final CreditOutputPort creditOutputPort;
    private final JwtProvider jwtProvider;
    private final RefreshTokenOutputPort refreshTokenOutputPort;
    private final SaveAssetsOutputPort saveAssetsOutputPort;
    private final SendCreditOutputPort sendCreditOutputPort;

    @Transactional
    @Override
    public TokenInfoServiceResponse becomeFinfluencer(Long userId, String token) {
        // 조건 300, 500크레딧 차감
        User user = readUserOutputPort.getUserByUserId(UserId.of(userId));

        FollowInfoResponse followInfo = readFollowOutputPort.getFollowInfo(user.getId(),Optional.empty());

        log.info(String.valueOf(followInfo.getNumOfFollowers()), "fuck followers");
        if (followInfo.getNumOfFollowers() < 300) throw new BadRequestException("핀플루언서로 등업하기 위해서 300명 이상의 팔로워들이 필요합니다.");

        user.setRole(UserRole.FINFLUENCER);
        User saveUser = saveUserOutputPort.save(user);

        // 크레딧 차감
        creditOutputPort.consumeCredit(500L, token);
        sendUpdateUserOutputPort.sendUserOutput(UserUpdateOutputRequest.builder()
            .userId(userId)
            .role(UserRole.FINFLUENCER)
            .profileImage(user.getProfileImage().toString())
            .job(user.getJob())
            .nickname(user.getNickname().toString())
            .build());
        return toTokenInfoServiceResponse(
            generateTokenAndSaveRefresh(saveUser), saveUser);
    }

    @Override
    public void exchange(final ExchangeServiceRequest request) {
        UserDetail user = readUserDetailOutputPort.findUserDetailById(UserId.of(request.getUserId()))
            .orElseThrow(() -> new BadRequestException("자산 연결이 되어 있지 않습니다."));
        UserDetail admin = readUserDetailOutputPort.getAdmin();

        // 차감
        creditOutputPort.consumeCredit(request.getAmount(), request.getToken());

        // 환전
        try {
            saveAssetsOutputPort.transfer(TransferCashRequest.builder()
                .frontId(admin.getFrontSocialId())
                .backId(admin.getBackSocialId())
                .contact(admin.getContact())
                .fromAccountNumber("963-8691-9306263")
                .toAccountNumber(request.getAccountNumber())
                .amount(request.getAmount())
                .build());
        } catch (InternalServerException e) {
            sendCreditOutputPort.addCredit(SendCreditOutputRequest.builder()
                    .transactionDateTime(LocalDateTime.now())
                    .userId(user.getId().toLong())
                    .amount(request.getAmount())
                .build());

            throw new BadRequestException("송금에 실패하였습니다.");
        }

    }

    private TokenInfoServiceResponse toTokenInfoServiceResponse(final TokenInfo tokenInfo, User user) {
        return TokenInfoServiceResponse.builder()
            .id(user.getId().toLong())
            .nickname(user.getNickname().toString())
            .accessToken(tokenInfo.getAccessToken())
            .refreshToken(tokenInfo.getRefreshToken())
            .grantType(tokenInfo.getGrantType())
            .build();
    }

    private TokenInfo generateTokenAndSaveRefresh(User user) {
        TokenInfo tokenInfo = jwtProvider.generateToken(TokenableUser.builder()
            .id(user.getId().toLong())
            .userRole(user.getRole())
            .job(user.getJob())
            .profileImage(user.getProfileImage().toString())
            .nickname(user.getNickname().toString())
            .birth(user.getBirth().toLocalDate())
            .build());

        refreshTokenOutputPort.saveOnlyOneUser(user, tokenInfo.getRefreshToken());

        return tokenInfo;
    }

}
