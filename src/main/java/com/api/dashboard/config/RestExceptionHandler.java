package com.api.dashboard.config;

import com.api.dashboard.util.exception.ErrorResponse;
import com.api.dashboard.util.exception.model.ErrorCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ErrorResponse> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        return ErrorResponse.toResponseEntity(ErrorCode.ILLEGAL_ARGUMENT,e.getMessage());
    }
}
