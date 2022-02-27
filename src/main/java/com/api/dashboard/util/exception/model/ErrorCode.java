package com.api.dashboard.util.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    API_NOT_RESPONSE(HttpStatus.SERVICE_UNAVAILABLE,"API가 정상적으로 응답하지 않습니다. 잠시 후 다시 시도하여주세요.");

    private final HttpStatus httpStatus;
    private final String message;
}
