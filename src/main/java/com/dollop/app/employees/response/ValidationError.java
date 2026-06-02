package com.dollop.app.employees.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a single field validation error in request processing.
 * Common industry fields: field name, rejected value, message and optional error code.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationError {
    /** Name of the field that failed validation (e.g. "email") */
    private String field;

    /** The rejected value (may be null or sanitized) */
    private Object rejectedValue;

    /** Human-readable validation message */
    private String message;

    /** Optional machine-readable error code (e.g. "NotBlank", "Size") */
    private String code;
}
