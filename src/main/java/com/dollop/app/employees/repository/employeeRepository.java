package com.dollop.app.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dollop.app.employees.entity.Employee;

public interface employeeRepository extends JpaRepository<Employee, Long> {

}
