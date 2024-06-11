package com.pda.exceptionhandler;

import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalExceptionResponse;
import com.pda.exceptionhandler.exceptions.BadRequestException;
import com.pda.exceptionhandler.exceptions.ConflictException;
import com.pda.exceptionhandler.exceptions.ForbiddenException;
import com.pda.exceptionhandler.exceptions.UnAuthorizedException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GlobalExceptionResponse<String> handleBadRequestException(final BadRequestException e) {
        return ApiUtils.exception(e.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public GlobalExceptionResponse<String> handleConflictException(final ConflictException e) {
        return ApiUtils.exception(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Hidden
    public GlobalExceptionResponse<List<String>> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        return ApiUtils.exceptions(e.getBindingResult()
            .getFieldErrors()
            .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList()));
    }

    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public GlobalExceptionResponse<String> handleUnauthorizedException(final UnAuthorizedException e) {
        return ApiUtils.exception(e.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public GlobalExceptionResponse<String> handleForbiddenException(final ForbiddenException e) {
        return ApiUtils.exception(e.getMessage());
    }
}