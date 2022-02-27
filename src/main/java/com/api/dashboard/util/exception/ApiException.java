package com.api.dashboard.util.exception;


import com.api.dashboard.util.exception.model.ErrorCode;

public class ApiException extends BusinessException{
    public ApiException(ErrorCode errorCode) {
        super(errorCode);
    }
    public ApiException(ErrorCode errorCode, String detail) {
        super(errorCode,detail);

    }
}
