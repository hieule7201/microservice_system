package com.minhhieu.userservice.repository;

import com.minhhieu.userservice.model.ERole;
import com.minhhieu.userservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNameRole(ERole name);
}
