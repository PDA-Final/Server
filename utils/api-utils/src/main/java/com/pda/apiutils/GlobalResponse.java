package com.pda.apiutils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

;

@Getter
@Schema(description = "Response Wrapper")
public class GlobalResponse<T> {
    @Schema(description = "처리 성공 여부", example="true")
    private final boolean success = true;
    @Schema(description = "메시지", example="처리 성공!")
    private String message;
    private T data;

    @Builder(access = AccessLevel.MODULE)
    private GlobalResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
