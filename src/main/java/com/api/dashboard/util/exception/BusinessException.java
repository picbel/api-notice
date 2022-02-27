package com.api.dashboard.util.exception;

import com.api.dashboard.util.exception.model.ErrorCode;
import lombok.Getter;

@Getter
public abstract class BusinessException extends RuntimeException{
    private final ErrorCode errorCode;
    private String detail;

    protected BusinessException(ErrorCode errorCode,String detail) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detail = detail;
    }

    protected BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }



}
