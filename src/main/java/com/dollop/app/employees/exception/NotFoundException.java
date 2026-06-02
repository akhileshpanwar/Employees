package com.dollop.app.employees.exception;

import org.springframework.http.HttpStatus;

/**
 * Resource not found (404)
 */
public class NotFoundException extends ApiException {
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }

    public NotFoundException(String message, String errorCode) {
        super(message, HttpStatus.NOT_FOUND.value(), errorCode);
    }
}
