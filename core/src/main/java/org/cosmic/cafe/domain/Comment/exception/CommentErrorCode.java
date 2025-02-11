package org.cosmic.cafe.domain.Comment.exception;

import org.cosmic.cafe.exception.ErrorCode;

public enum CommentErrorCode implements ErrorCode {

    CONTENT_IS_NULL("COO1", "댓글 내용이 비어있습니다.");

    private final String code;
    private final String message;

    CommentErrorCode(String code, String message) {
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
