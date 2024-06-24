package com.pda.userapplication.inadapters.controllers;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.tofinsecurity.user.AuthUser;
import com.pda.tofinsecurity.user.AuthUserInfo;
import com.pda.userapplication.inadapters.controllers.dto.req.ExchangeRequest;
import com.pda.userapplication.services.in.FinfluencerUseCase;
import com.pda.userapplication.services.in.dto.req.ExchangeServiceRequest;
import com.pda.userapplication.services.in.dto.res.TokenInfoServiceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Finfluencer", description = "핀플루언서 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/finfluencer")
public class FinfluencerController {
    private final FinfluencerUseCase finfluencerUseCase;

    @PutMapping
    @Operation(summary = "핀플루언서 등업", description = "핀플루언서 등업",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<TokenInfoServiceResponse> becomeFinfluencer(@AuthUser AuthUserInfo authUser) {
        return ApiUtils.success("핀플루언서가 되셨습니다", finfluencerUseCase
            .becomeFinfluencer(authUser.getId(), authUser.getToken()));
    }

    @PostMapping("/exchange")
    @Operation(summary = "크레딧 환전", description = "크레딧 환전 API",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<Void> exchange(@AuthUser AuthUserInfo authUser, @RequestBody @Valid ExchangeRequest request) {
        finfluencerUseCase.exchange(ExchangeServiceRequest.builder()
                .accountNumber(request.getAccountNumber())
                .amount(request.getAmount())
                .token(authUser.getToken())
                .userId(authUser.getId())
            .build());
        return ApiUtils.success("환전에 성공하였습니다.");
    }
}
