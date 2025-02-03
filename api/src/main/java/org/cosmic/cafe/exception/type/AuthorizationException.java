package org.cosmic.cafe.exception.type;

import org.cosmic.cafe.exception.ErrorCode;

public class AuthorizationException extends CustomException {
    public AuthorizationException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
