package org.cosmic.cafe.domain.post.exception;

import org.cosmic.cafe.exception.ErrorCode;

public enum PostErrorCode implements ErrorCode {

    ARGUMENT_IS_NULL("P001", "인자는 null일 수 없습니다."),
    NOT_FOUND("POO2", "게시글이 존재하지 않습니다."),
    UPDATE_PERMISSION_DENIED("P003", "게시글 수정 권한이 없습니다.");

    private final String code;
    private final String message;

    PostErrorCode(String code, String message) {
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
