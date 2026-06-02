package com.dollop.app.employees.exception;

import org.springframework.http.HttpStatus;

/**
 * Conflict (409) - typically for duplicate or state conflict errors
 */
public class ConflictException extends ApiException {
    public ConflictException(String message) {
        super(message, HttpStatus.CONFLICT.value());
    }

    public ConflictException(String message, String errorCode) {
        super(message, HttpStatus.CONFLICT.value(), errorCode);
    }
}
