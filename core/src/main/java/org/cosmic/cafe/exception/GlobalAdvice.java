package org.bee.metro.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.bee.metro.global.auth.exception.type.AuthenticationException;
import org.bee.metro.global.auth.exception.type.AuthorizationException;
import org.bee.metro.global.exception.type.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalAdvice {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException exception) {
        ErrorResponse errorResponse = ErrorResponse.of(exception.getErrorCode().getCode(), exception.getErrorCode().getMessage());

        log.error("{}", exception);

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorResponse);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErrorResponse> handleAuthorizationException(AuthorizationException exception) {
        ErrorResponse errorResponse = ErrorResponse.of(exception.getErrorCode().getCode(), exception.getErrorCode().getMessage());

        log.error("{}", exception);

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(errorResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException exception) {
        ErrorResponse errorResponse = ErrorResponse.of(exception.getErrorCode().getCode(), exception.getErrorCode().getMessage());

        log.error("{}", exception);

        return ResponseEntity
                .status(exception.getStatusCode())
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalServerError(Exception exception) {
        ErrorResponse errorResponse = ErrorResponse.of(GlobalErrorCode.INTERNAL_SERVER_ERROR.getCode(),
                GlobalErrorCode.INTERNAL_SERVER_ERROR.getMessage());

        log.error("{}", exception);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }
}
