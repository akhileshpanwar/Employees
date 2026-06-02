package com.dollop.app.employees.exception;

import org.springframework.http.HttpStatus;

/**
 * Authentication failure (401)
 */
public class UnauthorizedException extends ApiException {
    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED.value());
    }

    public UnauthorizedException(String message, String errorCode) {
        super(message, HttpStatus.UNAUTHORIZED.value(), errorCode);
    }
}
