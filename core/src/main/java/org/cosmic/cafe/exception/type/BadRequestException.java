package org.bee.metro.global.exception.type;

import org.bee.metro.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class BadRequestException extends BusinessException {

    public BadRequestException(ErrorCode errorCode) {
        super(HttpStatus.BAD_REQUEST.value(), errorCode);
    }

    public BadRequestException(String message, ErrorCode errorCode) {
        super(message, HttpStatus.BAD_REQUEST.value(), errorCode);
    }
}
