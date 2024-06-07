package com.pda.apiutils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Schema(description = "예외 객체")
public class GlobalExceptionResponse<T> {
    @Schema(description = "처리 성공 여부", example="false")
    private boolean success = false;
    @Schema(description = "예외 메시지", example="string | []")
    private final T message;

    static GlobalExceptionResponse<String> of(String message) {
        return new GlobalExceptionResponse<>(message);
    }

    static GlobalExceptionResponse<List<String>> of(List<String> messages) {
        return new GlobalExceptionResponse<>(messages);
    }

}
