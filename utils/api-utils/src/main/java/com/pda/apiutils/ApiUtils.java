package com.pda.apiutils;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiUtils {
    public static <T> GlobalResponse<T> makeResponse(HttpStatus status, String message, T data) {
        return GlobalResponse.<T>builder()
            .message(message)
            .data(data)
            .build();
    }

    public static <T> GlobalResponse<T> success(String message, T data) {
        return makeResponse(HttpStatus.OK, message, data);
    }

    public static GlobalResponse success(String message) {
        return makeResponse(HttpStatus.OK, message, null);
    }

    public static <T> GlobalResponse<T> created(String message, T data) {
        return makeResponse(HttpStatus.CREATED, message, data);
    }

    public static GlobalResponse created(String message) {
        return makeResponse(HttpStatus.CREATED, message, null);
    }

    public static GlobalExceptionResponse exception(String message) {
        return GlobalExceptionResponse.of(message);
    }

    public static GlobalExceptionResponse exceptions(List<String> messages) {
        return GlobalExceptionResponse.of(messages);
    }
}
