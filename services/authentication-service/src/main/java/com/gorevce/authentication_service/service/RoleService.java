package com.gorevce.authentication_service.service;

import com.gorevce.authentication_service.model.Role;

public interface RoleService {
    // Check if role exists
    boolean roleExists(String role);
    // Get role by name
    Role getRole(String role);
    // Create a new role
    void createRole(String role);
    // Delete a role
    void deleteRole(String role);

    Role createRoleIfNotFound(String roleAdmin);
    // Add a parent role to the role
}
