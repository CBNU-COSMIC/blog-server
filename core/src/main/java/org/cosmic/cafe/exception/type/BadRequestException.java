package org.cosmic.cafe.exception.type;

import org.cosmic.cafe.exception.ErrorCode;
import org.cosmic.cafe.exception.HttpStatus;

public class BadRequestException extends BusinessException {

    public BadRequestException(ErrorCode errorCode) {
        super(HttpStatus.BAD_REQUEST.value(), errorCode);
    }

    public BadRequestException(String message, ErrorCode errorCode) {
        super(message, HttpStatus.BAD_REQUEST.value(), errorCode);
    }
}
