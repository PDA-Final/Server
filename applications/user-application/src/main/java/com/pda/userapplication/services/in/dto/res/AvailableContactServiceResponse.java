package com.pda.userapplication.services.in.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Schema(description = "사용가능한 전화번호 Response")
public class AvailableContactServiceResponse {
    @Schema(description = "검사한 전화번호", example = "01025300767")
    private String contact;
    @Schema(description = "사용 가능 여부", example = "true")
    private boolean available;
    @Schema(description = "사용하지 못하는 이유", example = "이미 사용중 입니다")
    private String reason;
}
