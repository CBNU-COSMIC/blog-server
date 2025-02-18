package org.cosmic.cafe.domain.member.exception;

import org.cosmic.cafe.exception.ErrorCode;

public enum MemberErrorCode implements ErrorCode {
    REQUIRED_VALUE("U001", "해당 값은 필수 입력 값입니다."),
    INVALID_NAME_FORMAT("U002","해당 값은 영어나 한글만 사용할 수 있습니다."),
    INVALID_MEMBERID_FORMAT("U003","해당 값은 영어 소문자와 숫자만 사용할 수 있습니다."),
    INVALID_PASSWORD_LENGTH("U004","해당 값은 8~16자여야 합니다."),
    INVALID_PASSWORD_FORMAT("U005","해당 값은 소문자, 대문자, 숫자, 기호를 모두 포함해야 합니다."),
    INVALID_STUDENTNUMBER_LENGTH("U006","해당 값은 6~11자 사이의 숫자여야 합니다."),
    INVALID_EMAIL_FORMAT("U007","이메일 형식에 맞게 작성해야 합니다."),
    INVALID_NICKNAME_LENGTH("U008","해당 값은 문자만 가능합니다."),
    INVALID_NICKNAME_FORMAT("U009","해당 값은 10자 미만이어야 합니다.");
    //INVALID_DATE_TIME("S002", "시작일시는 종료일시보다 이전이어야 합니다."),
    //NOT_FOUND("S003", "해당 일정이 존재하지 않습니다."),
    //DELETE_PERMISSION_DENIED("S004", "해당 일정을 삭제할 권한이 없습니다.");

    private final String code;
    private final String message;

    MemberErrorCode(String code, String message) {
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