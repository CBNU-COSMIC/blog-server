package org.cosmic.cafe.exception;

import lombok.Getter;

@Getter
public enum GlobalErrorCode implements ErrorCode {

    // 클라이언트 오류
    INVALID_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "G001", "요청 파라미터가 잘못되었습니다."),

    // 서버 오류
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "G004", "서버 내부 오류가 발생했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    GlobalErrorCode(HttpStatus httpStatus, String code, String message) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
