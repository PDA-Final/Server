package com.pda.userapplication.inadapters.controllers.auth;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.userapplication.inadapters.controllers.auth.dto.req.SignUpRequest;
import com.pda.userapplication.services.auth.in.SignUpUseCase;
import com.pda.userapplication.services.auth.in.dto.req.SignUpServiceRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final SignUpUseCase signUpUseCase;

    @PostMapping("/sign-up")
    public GlobalResponse signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        signUpUseCase.signUp(SignUpServiceRequest.builder()
                .tofinId(signUpRequest.getTofinId())
                .userInfo(signUpRequest.getUserInfo())
            .build());
        return ApiUtils.created("유저 회원가입 완료");
    }
}
