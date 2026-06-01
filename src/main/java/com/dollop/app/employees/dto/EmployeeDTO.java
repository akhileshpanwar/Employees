package com.dollop.app.employees.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Employee payloads.
 * Mirrors the persistent Employee entity but is used for service/controller boundaries.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {

    private Long id;

    @NotBlank
    @Size(max = 100)
    private String firstName;

    @Size(max = 100)
    private String lastName;

    @NotBlank
    @Email
    @Size(max = 150)
    private String email;

    @Size(max = 20)
    private String phone;

    @Size(max = 255)
    private String address;

    @Size(max = 100)
    private String department;

    private Double salary;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
