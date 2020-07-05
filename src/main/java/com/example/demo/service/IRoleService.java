package com.example.demo.service;

import com.example.demo.entity.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    public List<Role> getAll();
    void saveRole(Role role);
    void deleteRole(int id);
    Optional<Role> findRoleById(int id);
}
