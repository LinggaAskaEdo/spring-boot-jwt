package com.example.jwt.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jwt.model.entity.User;

public interface UserDAO extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
