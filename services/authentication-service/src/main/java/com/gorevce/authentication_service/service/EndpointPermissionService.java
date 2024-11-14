package com.gorevce.authentication_service.service;

import com.gorevce.authentication_service.model.Role;

import java.util.List;
import java.util.Set;

public interface EndpointPermissionService {
    // Create a new endpoint permission
    void createEndpointPermission(String endpoint, String httpMethod);
    // Delete an endpoint permission
    void deleteEndpointPermission(String endpoint, String httpMethod);
    // Add a permission to the endpoint
    void addPermission(String endpoint, String httpMethod, String role);
    // Remove a permission from the endpoint
    void removePermission(String endpoint, String httpMethod, String role);
    // Check if the user has permission to access the endpoint
    boolean hasPermission(String endpoint, String httpMethod, String userRole);
    // Get the list of permissions for the endpoint
    List<String> getPermissions(String endpoint, String httpMethod);
    // Create a new endpoint permission if it does not exist
    void createEndpointPermissionIfNotFound(String path, String get, Set<Role> adminRole);
}
