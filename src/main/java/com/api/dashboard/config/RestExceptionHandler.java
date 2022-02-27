package com.api.dashboard.config;


import com.api.dashboard.util.exception.ApiException;
import com.api.dashboard.util.exception.BusinessException;
import com.api.dashboard.util.exception.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@Log4j2
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    protected ResponseEntity<ErrorResponse> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException throw Exception : {}", e.getErrorCode());
        return Objects.isNull(e.getDetail())? ErrorResponse.toResponseEntity(e.getErrorCode()) : ErrorResponse.toResponseEntity(e.getErrorCode(),e.getDetail());
    }
}
