package com.gorevce.authentication_service.service;

import com.gorevce.authentication_service.dto.response.RoleResponse;
import com.gorevce.authentication_service.model.Role;

import java.util.List;

public interface RoleService {
    // Check if role exists
    boolean roleExists(String role);
    // Get role by name
    Role getRoleByName(String roleName);
    // Create a role if it does not exist
    Role createRoleIfNotFound(String roleAdmin);

    // ------------------------------------------------------------
    // endpoint methods for managing roles
    // crud operations for roles
    // ------------------------------------------------------------
    // Create a new role
    RoleResponse createRole(String role);
    // Delete a role
    RoleResponse deleteRole(String id);
    // Update a role
    RoleResponse updateRole(String id, String role);
    // Get all roles
    List<RoleResponse> getAllRoles();
    // Get a role by id
    RoleResponse getRoleById(String id);

    Role getRoleObjectById(String roleId);
}
