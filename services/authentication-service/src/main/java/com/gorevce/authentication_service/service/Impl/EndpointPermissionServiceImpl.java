package com.gorevce.authentication_service.service.Impl;

import com.gorevce.authentication_service.exception.CustomException;
import com.gorevce.authentication_service.model.EndpointPermission;
import com.gorevce.authentication_service.model.Role;
import com.gorevce.authentication_service.repository.EndpointPermissionsRepository;
import com.gorevce.authentication_service.service.EndpointPermissionService;
import com.gorevce.authentication_service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class EndpointPermissionServiceImpl implements EndpointPermissionService {

    @Autowired
    private RoleService roleService;
    @Autowired
    private EndpointPermissionsRepository endpointPermissionRepository;


    @Override
    public void createEndpointPermission(String endpoint, String httpMethod) {
        // Check if the endpoint permission already exists
        if (endpointPermissionRepository.findByEndpointAndHttpMethod(endpoint, httpMethod).isPresent()) {
            throw new CustomException("Endpoint permission already exists", 400, Collections.singletonMap("endpoint", endpoint));
        }
        // Create a new endpoint permission
        EndpointPermission endpointPermission = EndpointPermission.builder()
                .endpoint(endpoint)
                .httpMethod(httpMethod)
                .roles(Collections.emptySet())
                .build();
        // Save the endpoint permission
        endpointPermissionRepository.save(endpointPermission);
    }

    @Override
    public void deleteEndpointPermission(String endpoint, String httpMethod) {
        // Check if the endpoint permission exists
        if (endpointPermissionRepository.findByEndpointAndHttpMethod(endpoint, httpMethod).isEmpty()) {
            throw new CustomException("Endpoint permission does not exist", 404, Collections.singletonMap("endpoint", endpoint));
        }
        // Delete the endpoint permission
        endpointPermissionRepository.deleteByEndpointAndHttpMethod(endpoint, httpMethod);
    }

    @Override
    public void addPermission(String endpoint, String httpMethod, String role) {
        // Check if the endpoint permission exists
        if (endpointPermissionRepository.findByEndpointAndHttpMethod(endpoint, httpMethod).isEmpty()) {
            throw new CustomException("Endpoint permission does not exist", 404, Collections.singletonMap("endpoint", endpoint));
        }
        // Check if the role exists
        if (!roleService.roleExists(role)) {
            throw new CustomException("Role does not exist", 404, Collections.singletonMap("role", role));
        }
        // Check if the role is already added to the endpoint permission
        if (endpointPermissionRepository.findByEndpointAndHttpMethod(endpoint, httpMethod).get().getRoles().stream().anyMatch(r -> r.getName().equals(role))) {
            throw new CustomException("Role already added to the endpoint permission", 400, Collections.singletonMap("role", role));
        }
        // Add the role to the endpoint permission
        EndpointPermission endpointPermission = endpointPermissionRepository.findByEndpointAndHttpMethod(endpoint, httpMethod).get();
        endpointPermission.getRoles().add(roleService.getRole(role));
        // Save the endpoint permission
        endpointPermissionRepository.save(endpointPermission);
    }

    @Override
    public void removePermission(String endpoint, String httpMethod, String role) {
        // Check if the endpoint permission exists
        if (endpointPermissionRepository.findByEndpointAndHttpMethod(endpoint, httpMethod).isEmpty()) {
            throw new CustomException("Endpoint permission does not exist", 404, Collections.singletonMap("endpoint", endpoint));
        }
        // Check if the role exists
        if (!roleService.roleExists(role)) {
            throw new CustomException("Role does not exist", 404, Collections.singletonMap("role", role));
        }
        // Check if the role is already added to the endpoint permission
        if (endpointPermissionRepository.findByEndpointAndHttpMethod(endpoint, httpMethod).get().getRoles().stream().noneMatch(r -> r.getName().equals(role))) {
            throw new CustomException("Role not added to the endpoint permission", 400, Collections.singletonMap("role", role));
        }
        // Remove the role from the endpoint permission
        EndpointPermission endpointPermission = endpointPermissionRepository.findByEndpointAndHttpMethod(endpoint, httpMethod).get();
        endpointPermission.getRoles().remove(roleService.getRole(role));
        // Save the endpoint permission
        endpointPermissionRepository.save(endpointPermission);

    }

    @Override
    public boolean hasPermission(String endpoint, String httpMethod, String userRole) {
        // Check if the endpoint permission exists
        if (endpointPermissionRepository.findByEndpointAndHttpMethod(endpoint, httpMethod).isEmpty()) {
            return false;
        }
        // Check if the role exists
        if (!roleService.roleExists(userRole)) {
            return false;
        }
        // Check if the user has permission to access the endpoint
        return endpointPermissionRepository.findByEndpointAndHttpMethod(endpoint, httpMethod).get().getRoles().stream().anyMatch(r -> r.getName().equals(userRole));
    }

    @Override
    public List<String> getPermissions(String endpoint, String httpMethod) {
        // Check if the endpoint permission exists
        if (endpointPermissionRepository.findByEndpointAndHttpMethod(endpoint, httpMethod).isEmpty()) {
            throw new CustomException("Endpoint permission does not exist", 404, Collections.singletonMap("endpoint", endpoint));
        }
        // Get the list of permissions for the endpoint
        return endpointPermissionRepository.findByEndpointAndHttpMethod(endpoint, httpMethod).get().getRoles().stream().map(Role::getName).toList();
    }

    @Override
    public void createEndpointPermissionIfNotFound(String path, String get, Set<Role> adminRole) {
        // Check if the endpoint permission already exists
        if (endpointPermissionRepository.findByEndpointAndHttpMethod(path, get).isPresent()) {
            return;
        }
        // Create a new endpoint permission
        EndpointPermission endpointPermission = EndpointPermission.builder()
                .endpoint(path)
                .httpMethod(get)
                .roles(adminRole)
                .build();
        // Save the endpoint permission
        endpointPermissionRepository.save(endpointPermission);

    }
}
