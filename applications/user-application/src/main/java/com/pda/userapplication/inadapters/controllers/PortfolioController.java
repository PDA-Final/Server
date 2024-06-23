package com.pda.userapplication.inadapters.controllers;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.tofinsecurity.user.AuthUser;
import com.pda.tofinsecurity.user.AuthUserInfo;
import com.pda.userapplication.services.in.PortfolioUseCase;
import com.pda.userapplication.services.in.dto.req.PortfolioSubscribeServiceRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Portfolio", description = "포트폴리오 API")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class PortfolioController {
    private final PortfolioUseCase portfolioUseCase;

    @PostMapping("/{id}/portfolios")
    @Operation(summary = "포트폴리오 구독", description = "자기자신 or 돈없음 or 이미 구독 중 => X ",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "성공")
    @ResponseStatus(HttpStatus.CREATED)
    public GlobalResponse<Void> subscribe(@AuthUser AuthUserInfo authUser, @PathVariable Long id) {
        portfolioUseCase.subscribe(PortfolioSubscribeServiceRequest.builder()
                .myId(authUser.getId())
                .toId(id)
            .token(authUser.getToken())
            .build());
        return ApiUtils.success("구독 완료");
    }

    @GetMapping("/{id}/portfolios")
    @Operation(summary = "포트폴리오 조회", description = "포트폴리오 조회 API",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "성공")
    public GlobalResponse<Void> getPortfolios(@AuthUser AuthUserInfo authUser, @PathVariable Long id) {
        portfolioUseCase.getPortfolios(authUser.getId(), id);
        return ApiUtils.success("포트폴리오 조회");
    }
}
