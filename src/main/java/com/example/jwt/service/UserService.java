package com.example.jwt.service;

import java.util.List;

import com.example.jwt.model.entity.Role;
import com.example.jwt.model.entity.User;

public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);

    List<User> getUsers();
}
