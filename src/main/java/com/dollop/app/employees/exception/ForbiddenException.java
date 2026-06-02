package com.dollop.app.employees.exception;

import org.springframework.http.HttpStatus;

/**
 * Authorization failure (403)
 */
public class ForbiddenException extends ApiException {
    public ForbiddenException(String message) {
        super(message, HttpStatus.FORBIDDEN.value());
    }

    public ForbiddenException(String message, String errorCode) {
        super(message, HttpStatus.FORBIDDEN.value(), errorCode);
    }
}
