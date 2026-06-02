package com.dollop.app.employees.exception;

/**
 * Base runtime exception for application-specific errors.
 * Carries an HTTP status code and optional machine-readable error code.
 */
public abstract class ApiException extends RuntimeException {
    private final int status;
    private final String errorCode;

    protected ApiException(String message, int status) {
        this(message, status, null);
    }

    protected ApiException(String message, int status, String errorCode) {
        super(message);
        this.status = status;
        this.errorCode = errorCode;
    }

    public int getStatus() {
        return status;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
