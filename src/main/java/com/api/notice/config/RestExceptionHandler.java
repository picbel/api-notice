package com.api.notice.config;

import com.api.notice.util.exception.BusinessException;
import com.api.notice.util.exception.ErrorResponse;
import com.api.notice.util.exception.model.ErrorCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException = {}",e.getMessage());
        return ErrorResponse.toResponseEntity(e.getErrorCode(),e.getErrorCode().getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException = {}",e.getMessage());
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ErrorResponse.toResponseEntity(ErrorCode.METHOD_ARGUMENT_NOT_VALID,errors.toString());
    }

}
