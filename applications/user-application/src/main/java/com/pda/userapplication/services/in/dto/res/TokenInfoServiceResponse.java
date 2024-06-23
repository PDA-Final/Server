package com.pda.userapplication.services.in.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "토큰 Response")
public class TokenInfoServiceResponse {
    @Schema(description = "유저 아이디(PK)", example = "65")
    private Long id;
    @Schema(description = "인증 타입", example = "Bearer")
    private String grantType;
    @Schema(description = "액세스 토큰(15분)", example = "adfjkladfjkaldfjkladfjkladfjakl")
    private String accessToken;
    @Schema(description = "리프레쉬 토큰(토큰 재발급, 30일)", example = "adfjkladfjkaldfjkladfjkladfjakl")
    private String refreshToken;
}
