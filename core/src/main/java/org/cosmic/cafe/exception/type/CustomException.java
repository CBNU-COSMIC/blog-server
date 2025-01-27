package org.cosmic.cafe.exception.type;

import org.cosmic.cafe.exception.ErrorCode;

public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
