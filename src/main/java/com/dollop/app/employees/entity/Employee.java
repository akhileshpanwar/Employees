
	package com.dollop.app.employees.entity;

	import java.time.LocalDateTime;
	import java.util.Objects;

	import jakarta.persistence.Column;
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.PrePersist;
	import jakarta.persistence.PreUpdate;
	import jakarta.persistence.Table;
	import jakarta.validation.constraints.Email;
	import jakarta.validation.constraints.NotBlank;
	import jakarta.validation.constraints.Size;
	import lombok.AllArgsConstructor;
	import lombok.Data;
	import lombok.NoArgsConstructor;

	@Entity
	@Table(name = "full")
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
public class Employee {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "first_name", nullable = false, length = 100)
	    @NotBlank
	    @Size(max = 100)
	    private String firstName;

	    @Column(name = "last_name", length = 100)
	    @Size(max = 100)
	    private String lastName;

	    @Column(nullable = false, unique = true, length = 150)
	    @Email
	    @NotBlank
	    @Size(max = 150)
	    private String email;

	    @Column(length = 20)
	    @Size(max = 20)
	    private String phone;

	    @Column(length = 255)
	    @Size(max = 255)
	    private String address;

	    @Column(length = 100)
	    @Size(max = 100)
	    private String department;

	    private Double salary;

	    @Column(name = "active")
	    private Boolean active = Boolean.TRUE;

	    @Column(name = "created_at", updatable = false)
	    private LocalDateTime createdAt;

	    @Column(name = "updated_at")
	    private LocalDateTime updatedAt;

	   
	
}
