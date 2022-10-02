package com.example.jwt.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jwt.model.entity.Role;

public interface RoleDAO extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
