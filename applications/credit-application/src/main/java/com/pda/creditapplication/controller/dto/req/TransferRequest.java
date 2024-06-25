package com.pda.creditapplication.controller.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Schema(description = "크레딧 전송 Request")
public class TransferRequest {
    @Schema(description = "전송 목적지 유저", example = "1")
    private Long toUserId;
    @Min(1)
    @Schema(description = "차감액(양수만 가능)", example = "100", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long amount;
    @PastOrPresent
    @Schema(description = "차감 발생 날짜/시간", example = "2024-06-19T10:41:04.172+09:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime transactionDateTime;
}
