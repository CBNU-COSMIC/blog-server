package org.cosmic.cafe.domain.schedule.exception;

import org.cosmic.cafe.exception.ErrorCode;

public enum ScheduleErrorCode implements ErrorCode {
    ;

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
