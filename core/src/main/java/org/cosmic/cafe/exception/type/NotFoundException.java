package org.cosmic.cafe.exception.type;

import org.cosmic.cafe.exception.ErrorCode;
import org.cosmic.cafe.exception.HttpStatus;

public class NotFoundException extends BusinessException {

    public NotFoundException(ErrorCode errorCode) {
        super(HttpStatus.NOT_FOUND.value(), errorCode);
    }

    public NotFoundException(String message, ErrorCode errorCode) {
        super(message, HttpStatus.NOT_FOUND.value(), errorCode);
    }
}
