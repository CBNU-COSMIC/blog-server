package org.cosmic.cafe.domain.schedule.exception;

import org.cosmic.cafe.exception.ErrorCode;

public enum ScheduleErrorCode implements ErrorCode {
    REQUIRED_VALUE("S001", "해당 값은 필수 입력 값입니다."),
    INVALID_DATE_TIME("S002", "시작일시는 종료일시보다 이전이어야 합니다.");

    private final String code;
    private final String message;

    ScheduleErrorCode(String code, String message) {
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
