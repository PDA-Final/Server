package com.pda.userapplication.services;

import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.exceptionhandler.exceptions.ConflictException;
import com.pda.exceptionhandler.exceptions.UnAuthorizedException;
import com.pda.tofinenums.user.Job;
import com.pda.tofinenums.user.UserRole;
import com.pda.tofinsecurity.UserInfoEncoder;
import com.pda.tofinsecurity.jwt.JwtProvider;
import com.pda.tofinsecurity.jwt.TokenInfo;
import com.pda.tofinsecurity.jwt.TokenableUser;
import com.pda.userapplication.domains.NormalUser;
import com.pda.userapplication.domains.User;
import com.pda.userapplication.domains.vo.Birth;
import com.pda.userapplication.domains.vo.ImageUrl;
import com.pda.userapplication.domains.vo.Nickname;
import com.pda.userapplication.domains.vo.TofinId;
import com.pda.userapplication.services.in.IsAvailableTofinIdUseCase;
import com.pda.userapplication.services.in.ReissueUseCase;
import com.pda.userapplication.services.in.SignInUseCase;
import com.pda.userapplication.services.in.SignUpUseCase;
import com.pda.userapplication.services.in.dto.req.SignInServiceRequest;
import com.pda.userapplication.services.in.dto.req.SignUpServiceRequest;
import com.pda.userapplication.services.in.dto.res.AvailableTofinIdServiceResponse;
import com.pda.userapplication.services.in.dto.res.TokenInfoServiceResponse;
import com.pda.userapplication.services.out.CreateUserOutputPort;
import com.pda.userapplication.services.out.ReadUserOutputPort;
import com.pda.userapplication.services.out.RefreshTokenOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements SignUpUseCase, ReissueUseCase, IsAvailableTofinIdUseCase, SignInUseCase {
    private final CreateUserOutputPort createUserOutputPort;
    private final ReadUserOutputPort readUserOutputPort;
    private final UserInfoEncoder userInfoEncoder;
    private final JwtProvider jwtProvider;
    private final RefreshTokenOutputPort refreshTokenOutputPort;

    @Transactional
    @Override
    public void signUp(final SignUpServiceRequest request) {
        if (isDuplicateTofinId(TofinId.of(request.getTofinId())))
            throw new ConflictException("해당 아이디는 이미 존재합니다.");

        createUserOutputPort.create(NormalUser.builder()
            .toFinId(TofinId.of(request.getTofinId()))
            .userInfo(userInfoEncoder.hashed(request.getUserInfo()))
            .birth(Birth.of(request.getBirth()))
            .job(toJobFrom(request.getJob()))
            .profileImage(ImageUrl.of(request.getProfileImage()))
            .nickname(Nickname.of(request.getNickname()))
            .role(UserRole.NORMAL)
            .build());
    }

    @Override
    public AvailableTofinIdServiceResponse isAvailableTofinId(final String tofinId) {
        try {
            boolean isAvailable = !isDuplicateTofinId(TofinId.of(tofinId));

            return AvailableTofinIdServiceResponse.builder()
                .tofinId(tofinId)
                .available(isAvailable)
                .reason(isAvailable?"사용 가능한 아이디 입니다":"이미 사용중인 아이디 입니다")
                .build();

        } catch (IllegalArgumentException e) {
            return AvailableTofinIdServiceResponse.builder()
                .tofinId(tofinId)
                .available(false)
                .reason("아이디는 8~30글자 사이, 영문+숫자 형식이어야 합니다.")
                .build();
        }
    }

    @Transactional
    @Override
    public TokenInfoServiceResponse signIn(final SignInServiceRequest request) {
        User user = readUserOutputPort.findByTofinId(TofinId.of(request.getTofinId()))
            .orElseThrow(() -> new UnAuthorizedException("아이디 혹은 비밀번호가 틀렸습니다."));

        if (!userInfoEncoder.matches(request.getUserInfo(), user.getUserInfo()))
            throw new UnAuthorizedException("아이디 혹은 비밀번호가 틀렸습니다.");

        return toTokenInfoServiceResponse(
            generateTokenAndSaveRefresh(user));
    }

    @Transactional
    @Override
    public TokenInfoServiceResponse reissue(Optional<String> refreshToken) {
        String token = refreshToken.orElseThrow(() -> new UnAuthorizedException("리프레쉬 토큰 이상"));

        User user = refreshTokenOutputPort.deleteByRefreshToken(token)
            .orElseThrow(() -> new UnAuthorizedException("해당 리프레쉬 토큰은 사용할 수 없음"));

        return toTokenInfoServiceResponse(
            generateTokenAndSaveRefresh(user));
    }

    private boolean isDuplicateTofinId(final TofinId tofinId) {
        return readUserOutputPort.isExistsByTofinId(tofinId);
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

    private Job toJobFrom(String job) {
        try {
            return job==null?null:Job.of(job);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(String.format("%s(은)는 올바르지 않은 직업입니다.", job));
        }
    }

    private TokenInfoServiceResponse toTokenInfoServiceResponse(final TokenInfo tokenInfo) {
        return TokenInfoServiceResponse.builder()
            .accessToken(tokenInfo.getAccessToken())
            .refreshToken(tokenInfo.getRefreshToken())
            .grantType(tokenInfo.getGrantType())
            .build();
    }
}
