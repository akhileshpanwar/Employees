package com.dollop.app.employees.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Standardized error response for APIs. Inspired by RFC 7807 (Problem Details) and
 * extended with common industry fields like timestamp, traceId and validation errors.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    /** A URI reference that identifies the problem type (optional) */
    private String type;

    /** Short, human-readable summary of the problem type */
    private String title;

    /** HTTP status code */
    private int status;

    /** Detailed human-readable explanation specific to this occurrence */
    private String detail;

    /** URI reference that identifies the specific occurrence (often the request path) */
    private String instance;

    /** Epoch millis when the error happened */
    private Long timestamp;

    /** Correlation id / trace id (useful for logs and troubleshooting) */
    private String traceId;

    /** Optional list of per-field validation errors */
    private List<ValidationError> errors;

    // Convenience factory methods
    public static ErrorResponse of(int status, String title, String detail, String instance) {
        return ErrorResponse.builder()
                .status(status)
                .title(title)
                .detail(detail)
                .instance(instance)
                .timestamp(Instant.now().toEpochMilli())
                .traceId(generateTraceId())
                .build();
    }

    public static ErrorResponse validation(int status, String title, String detail, String instance, List<ValidationError> errors) {
        return ErrorResponse.builder()
                .status(status)
                .title(title)
                .detail(detail)
                .instance(instance)
                .errors(errors)
                .timestamp(Instant.now().toEpochMilli())
                .traceId(generateTraceId())
                .build();
    }

    private static String generateTraceId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Convert to the generic ApiResponse wrapper for APIs that use ApiResponse across success/failure.
     */
    public ApiResponse<Object> toApiResponse() {
        return ApiResponse.failure(this.getDetail() != null ? this.getDetail() : this.getTitle(), this.getStatus());
    }
}
