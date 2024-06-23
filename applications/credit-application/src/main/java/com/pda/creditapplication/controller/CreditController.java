package com.pda.creditapplication.controller;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalResponse;
import com.pda.creditapplication.controller.dto.req.WithdrawRequest;
import com.pda.creditapplication.service.CreditService;
import com.pda.creditapplication.service.dto.req.SetAmountServiceRequest;
import com.pda.tofinsecurity.user.AuthUser;
import com.pda.tofinsecurity.user.AuthUserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Credit", description = "크레딧 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/credit")
@Slf4j
public class CreditController {
    private final CreditService creditService;

    @PostMapping("/withdraw")
    @Operation(summary = "크레딧 인출", description = "크레딧 인출 API",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "성공")
    @ResponseStatus(HttpStatus.CREATED)
    public GlobalResponse<Void> withdraw(@AuthUser AuthUserInfo userInfo, @Valid @RequestBody WithdrawRequest request) {
        creditService.withdraw(SetAmountServiceRequest.builder()
                .userId(userInfo.getId())
                .transactionDateTime(request.getTransactionDateTime())
                .amount(request.getAmount())
            .build());
        return ApiUtils.success("인출 성공");
    }

    @GetMapping
    @Operation(summary = "크레딧 보유 현황 조회", description = "크레딧 보유 현황 조회",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "성공")
    public GlobalResponse<Long> getCredit(@AuthUser AuthUserInfo userInfo) {
        return ApiUtils.success("크레딧 보유 현황 조회", creditService.getAmount(userInfo.getId()));
    }
}
