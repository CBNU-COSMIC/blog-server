package org.cosmic.cafe.exception.type;

import org.cosmic.cafe.exception.ErrorCode;

public class AuthenticationException extends CustomException {
    public AuthenticationException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
