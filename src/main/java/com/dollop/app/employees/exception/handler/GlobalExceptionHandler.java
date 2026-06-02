package com.dollop.app.employees.exception.handler;

import com.dollop.app.employees.exception.ApiException;
import com.dollop.app.employees.exception.ValidationException;
import com.dollop.app.employees.response.ErrorResponse;
import com.dollop.app.employees.response.ValidationError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Global exception handler that centralizes error handling and returns consistent ErrorResponse bodies.
 * This follows industry practice: use a Problem-Details-like structure, include traceId/timestamp,
 * and map validation errors to a structured list.
 */
@RestControllerAdvice(basePackages = "com.dollop.app.employees")
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex, HttpServletRequest request) {
        ErrorResponse err = ErrorResponse.of(ex.getStatus(), ex.getClass().getSimpleName(), ex.getMessage(), request.getRequestURI());
        if (ex.getErrorCode() != null) {
            err.setType(ex.getErrorCode());
        }
        return ResponseEntity.status(ex.getStatus()).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<ValidationError> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> ValidationError.builder()
                        .field(fe.getField())
                        .rejectedValue(fe.getRejectedValue())
                        .message(fe.getDefaultMessage())
                        .code(fe.getCode())
                        .build())
                .collect(Collectors.toList());

        ErrorResponse err = ErrorResponse.validation(HttpStatus.BAD_REQUEST.value(), "Validation Failed", "Request validation failed", request.getRequestURI(), errors);
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        List<ValidationError> errors = violations.stream()
                .map(v -> ValidationError.builder()
                        .field(v.getPropertyPath() != null ? v.getPropertyPath().toString() : null)
                        .rejectedValue(v.getInvalidValue())
                        .message(v.getMessage())
                        .code(v.getConstraintDescriptor() != null ? v.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName() : null)
                        .build())
                .collect(Collectors.toList());

        ErrorResponse err = ErrorResponse.validation(HttpStatus.BAD_REQUEST.value(), "Validation Failed", "Constraint validation failed", request.getRequestURI(), errors);
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex, HttpServletRequest request) {
        ErrorResponse err = ErrorResponse.validation(HttpStatus.BAD_REQUEST.value(), "Validation Failed", ex.getMessage(), request.getRequestURI(), ex.getErrors());
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request) {
        ErrorResponse err = ErrorResponse.of(HttpStatus.BAD_REQUEST.value(), "Malformed Request", ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAll(Exception ex, HttpServletRequest request) {
        // Generic fallback - do not expose internal details in production
        ErrorResponse err = ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", "An unexpected error occurred", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}
