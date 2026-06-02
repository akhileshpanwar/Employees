package com.dollop.app.employees.exception;

import org.springframework.http.HttpStatus;

/**
 * Bad request (400)
 */
public class BadRequestException extends ApiException {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST.value());
    }

    public BadRequestException(String message, String errorCode) {
        super(message, HttpStatus.BAD_REQUEST.value(), errorCode);
    }
}
