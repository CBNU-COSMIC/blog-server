package org.cosmic.cafe.exception;

public enum HttpStatus {
    BAD_REQUEST(400),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    private final int value;

    HttpStatus(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
