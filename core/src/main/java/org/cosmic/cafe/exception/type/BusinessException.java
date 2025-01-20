package org.bee.metro.global.exception.type;

import org.bee.metro.global.exception.ErrorCode;

public class BusinessException extends RuntimeException {

    private final int statusCode;
    private final ErrorCode errorCode;

    public BusinessException(int statusCode, ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }

    public BusinessException(String message, int statusCode, ErrorCode errorCode) {
        super(message);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
