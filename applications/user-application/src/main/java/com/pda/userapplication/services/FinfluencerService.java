package com.pda.userapplication.services;

import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.tofinenums.user.UserRole;
import com.pda.tofinsecurity.jwt.JwtProvider;
import com.pda.tofinsecurity.jwt.TokenInfo;
import com.pda.tofinsecurity.jwt.TokenableUser;
import com.pda.userapplication.domains.User;
import com.pda.userapplication.domains.vo.UserId;
import com.pda.userapplication.services.in.FinfluencerUseCase;
import com.pda.userapplication.services.in.dto.res.TokenInfoServiceResponse;
import com.pda.userapplication.services.out.CreditOutputPort;
import com.pda.userapplication.services.out.ReadFollowOutputPort;
import com.pda.userapplication.services.out.ReadUserOutputPort;
import com.pda.userapplication.services.out.RefreshTokenOutputPort;
import com.pda.userapplication.services.out.SaveUserOutputPort;
import com.pda.userapplication.services.out.SendUpdateUserOutputPort;
import com.pda.userapplication.services.out.dto.req.UserUpdateOutputRequest;
import com.pda.userapplication.services.out.dto.res.FollowInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FinfluencerService implements FinfluencerUseCase {
    private final SaveUserOutputPort saveUserOutputPort;
    private final ReadUserOutputPort readUserOutputPort;
    private final ReadFollowOutputPort readFollowOutputPort;
    private final SendUpdateUserOutputPort sendUpdateUserOutputPort;
    private final CreditOutputPort creditOutputPort;
    private final JwtProvider jwtProvider;
    private final RefreshTokenOutputPort refreshTokenOutputPort;

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
            .build());
        return toTokenInfoServiceResponse(
            generateTokenAndSaveRefresh(saveUser), saveUser);
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
