package com.pda.boardapplication.exception;


import com.pda.apiutils.ApiUtils;
import com.pda.apiutils.GlobalExceptionResponse;
import com.pda.exceptionhandler.exceptions.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GlobalExceptionResponse<String> handleNotFoundException(final NotFoundException e) {
        log.info("Target element does not exists : {}", e.getMessage());
        return ApiUtils.exception(e.getMessage());
    }
}
