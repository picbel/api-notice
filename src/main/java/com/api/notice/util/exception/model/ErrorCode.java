package com.api.notice.util.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ILLEGAL_ARGUMENT(HttpStatus.BAD_REQUEST,"해당 요청에 문법적인 오류가 있습니다."),
    METHOD_ARGUMENT_NOT_VALID(HttpStatus.BAD_REQUEST,"유효성 검사에서 실패하였습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
