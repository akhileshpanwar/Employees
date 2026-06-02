package com.dollop.app.employees.exception;

import org.springframework.http.HttpStatus;
import java.util.List;
import com.dollop.app.employees.response.ValidationError;

/**
 * Exception used to carry validation errors programmatically.
 */
public class ValidationException extends ApiException {
    private final List<ValidationError> errors;

    public ValidationException(String message, List<ValidationError> errors) {
        super(message, HttpStatus.BAD_REQUEST.value());
        this.errors = errors;
    }

    public ValidationException(String message, List<ValidationError> errors, String errorCode) {
        super(message, HttpStatus.BAD_REQUEST.value(), errorCode);
        this.errors = errors;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }
}
