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
@Schema(description = "사용가능한 아이디 Response")
public class AvailableTofinIdServiceResponse {
    @Schema(description = "검사한 투핀 아이디", example = "dongwon0103")
    private String tofinId;
    @Schema(description = "사용 가능 여부", example = "true")
    private boolean available;
    @Schema(description = "사용하지 못하는 이유", example = "이미 사용중 입니다")
    private String reason;
}
