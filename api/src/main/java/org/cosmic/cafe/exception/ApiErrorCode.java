package org.cosmic.cafe.exception;

public enum ApiErrorCode implements ErrorCode {
    UNAUTHORIZED("A0001", "인증되지 않은 사용자입니다."),
    FORBIDDEN("A0002", "권한이 없는 사용자입니다."),
    ;
    
    private final String code;
    private final String message;

    ApiErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
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
