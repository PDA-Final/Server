package com.pda.userapplication.inadapters.controllers.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ExchangeRequest {
    @NotBlank
    @Schema(description = "계좌번호", example = "33023-230303-23232")
    private String accountNumber;
    @Min(1)
    @Schema(description = "환전액", example = "1000")
    private Long amount;
}
