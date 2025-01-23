package org.cosmic.cafe.exception.type;

import org.bee.metro.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;
/* Springframework 의존성이 추가되지 않아서 import를 하지 못하는 것 같습니다.*/

public class BadRequestException extends BusinessException {

    public BadRequestException(ErrorCode errorCode) {
        super(HttpStatus.BAD_REQUEST.value(), errorCode);
    }

    public BadRequestException(String message, ErrorCode errorCode) {
        super(message, HttpStatus.BAD_REQUEST.value(), errorCode);
    }
}
