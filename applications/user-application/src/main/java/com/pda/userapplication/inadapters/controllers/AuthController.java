package com.pda.userapplication.inadapters.controllers;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.tofinsecurity.jwt.JwtProvider;
import com.pda.tofinsecurity.jwt.TokenableUser;
import com.pda.tofinsecurity.user.AuthUser;
import com.pda.userapplication.inadapters.controllers.dto.req.SignInRequest;
import com.pda.userapplication.inadapters.controllers.dto.req.SignUpRequest;
import com.pda.userapplication.services.in.ReissueUseCase;
import com.pda.userapplication.services.in.SignInUseCase;
import com.pda.userapplication.services.in.SignUpUseCase;
import com.pda.userapplication.services.in.dto.req.SignInServiceRequest;
import com.pda.userapplication.services.in.dto.req.SignUpServiceRequest;
import com.pda.userapplication.services.in.dto.res.TokenInfoServiceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "인증 API")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final SignUpUseCase signUpUseCase;
    private final SignInUseCase signInUseCase;
    private final ReissueUseCase reissueUseCase;
    private final JwtProvider jwtProvider;

    @PostMapping("/sign-up")
    @Operation(
        summary = "일반 회원가입", description = "일반 유저에 대한 회원가입입니다. 아직 성향 / 자산 연결은 제공하지 않습니다.")
    @ApiResponse(responseCode = "201", description = "회원 생성(성공)")
    public GlobalResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        signUpUseCase.signUp(SignUpServiceRequest.builder()
                .tofinId(signUpRequest.getTofinId())
                .userInfo(signUpRequest.getUserInfo())
                .birth(signUpRequest.getBirth())
                .profileImage(signUpRequest.getProfileImg())
                .nickname(signUpRequest.getNickname())
                .job(signUpRequest.getJob())
            .build());
        return ApiUtils.created("유저 회원가입 완료");
    }

    @PostMapping("/sign-in")
    @Operation(summary = "로그인", description = "모든 유저 로그인")
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<TokenInfoServiceResponse> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        return ApiUtils.success("로그인 성공", signInUseCase.signIn(SignInServiceRequest.builder()
                .tofinId(signInRequest.getTofinId())
                .userInfo(signInRequest.getUserInfo())
            .build()));
    }

    @GetMapping("/reissue")
    @Operation(summary = "토큰 재발급", description = "액세스 / 리프레쉬 토큰 재발급",
        security = @SecurityRequirement(name = "bearerAuth"))
    public GlobalResponse<TokenInfoServiceResponse> reissue(HttpServletRequest request) {
        return ApiUtils.success("토큰 재발급 완료", reissueUseCase.reissue(jwtProvider.resolveToken(request)));
    }

    @GetMapping("/test")
    @Operation(summary = "인증 & 인가 테스트", description = "인가 테스트를 위한 API입니다. Jwt 정보를 파싱해서 리턴합니다. (해당 API는 ADMIN 권한만 access 가능합니다.)",
        security = @SecurityRequirement(name = "bearerAuth"))
    public GlobalResponse<TokenableUser> test(@AuthUser TokenableUser user) {
        return ApiUtils.success("인가 테스트 성공", user);
    }
}
