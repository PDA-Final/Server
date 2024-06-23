package com.pda.userapplication.inadapters.controllers;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.tofinsecurity.user.AuthUser;
import com.pda.tofinsecurity.user.AuthUserInfo;
import com.pda.userapplication.services.in.FinfluencerUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
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
    public GlobalResponse<Void> becomeFinfluencer(@AuthUser AuthUserInfo authUser) {
        finfluencerUseCase.becomeFinfluencer(authUser.getId(), authUser.getToken());
        return ApiUtils.success("핀플루언서가 되셨습니다");
    }
}
