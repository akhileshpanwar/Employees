package com.dollop.app.employees.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dollop.app.employees.entity.Permission;

@Repository
public interface PermissionRepository
        extends JpaRepository<Permission, UUID> {

    Optional<Permission> findByPermissionName(
            String permissionName);
}