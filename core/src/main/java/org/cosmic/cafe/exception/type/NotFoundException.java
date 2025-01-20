package org.bee.metro.global.exception.type;

import org.bee.metro.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BusinessException {

    public NotFoundException(ErrorCode errorCode) {
        super(HttpStatus.NOT_FOUND.value(), errorCode);
    }

    public NotFoundException(String message, ErrorCode errorCode) {
        super(message, HttpStatus.NOT_FOUND.value(), errorCode);
    }
}
