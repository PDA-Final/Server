package com.pda.userapplication.inadapters.controllers.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Schema(description = "성향테스트 Req")
public class SetTendencyRequest {
    @Schema(description = "성향:계좌", example = "true")
    private boolean account;
    @Schema(description = "성향:카드", example = "true")
    private boolean card;
    @Schema(description = "성향:대출", example = "true")
    private boolean loan;
    @Schema(description = "성향:투자", example = "true")
    private boolean invest;
    @NotBlank
    @Schema(description = "이용 목적(SHARE/REVIEW/FUN)", example = "SHARE")
    private String purpose;
}
