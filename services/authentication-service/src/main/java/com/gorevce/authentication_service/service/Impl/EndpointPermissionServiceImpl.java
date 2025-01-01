package com.gorevce.authentication_service.service.Impl;

import com.gorevce.authentication_service.dto.request.PermissionRequest;
import com.gorevce.authentication_service.dto.response.PermissionResponse;
import com.gorevce.authentication_service.dto.response.RoleResponse;
import com.gorevce.authentication_service.exception.CustomException;
import com.gorevce.authentication_service.model.EndpointPermission;
import com.gorevce.authentication_service.model.Role;
import com.gorevce.authentication_service.repository.EndpointPermissionsRepository;
import com.gorevce.authentication_service.service.EndpointPermissionService;
import com.gorevce.authentication_service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EndpointPermissionServiceImpl implements EndpointPermissionService {

    @Autowired
    private RoleService roleService;
    @Autowired
    private EndpointPermissionsRepository endpointPermissionRepository;


    @Override
    public List<PermissionResponse> getAllPermissions() {
        return endpointPermissionRepository.findAll().stream().map(endpointPermission -> PermissionResponse.builder()
                .id(endpointPermission.getId())
                .endpoint(endpointPermission.getEndpoint())
                .method(endpointPermission.getHttpMethod())
                .description(endpointPermission.getDescription())
                .roles(endpointPermission.getRoles()
                        .stream()
                        .map(role -> RoleResponse.builder()
                                .id(role.getId())
                                .role(role.getName())
                                .build())
                        .collect(Collectors.toSet()))
                .build()).collect(Collectors.toList());
    }

    @Override
    public PermissionResponse createPermission(PermissionRequest createPermissionRequest) {
        // check if the permission already exists
        if (endpointPermissionRepository.existsByEndpointAndHttpMethod(createPermissionRequest.getEndpoint(), createPermissionRequest.getMethod())) {
            throw new CustomException("Permission already exists", 400, Map.of("endpoint", createPermissionRequest.getEndpoint(), "method", createPermissionRequest.getMethod()));
        }

        // create a new permission
        EndpointPermission endpointPermission = EndpointPermission.builder()
                .endpoint(createPermissionRequest.getEndpoint())
                .httpMethod(createPermissionRequest.getMethod())
                .description(createPermissionRequest.getDescription())
                .roles(createPermissionRequest
                        .getRoles()
                        .stream()
                        .map(
                                roleName -> roleService.getRoleByName(roleName)
                        )
                        .collect(Collectors.toSet())
                ).build();
        // save the permission
        EndpointPermission saved = endpointPermissionRepository.save(endpointPermission);
        return PermissionResponse.builder()
                .id(saved.getId())
                .endpoint(saved.getEndpoint())
                .method(saved.getHttpMethod())
                .description(saved.getDescription())
                .roles(saved.getRoles()
                        .stream()
                        .map(role -> RoleResponse.builder()
                                .id(role.getId())
                                .role(role.getName())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public PermissionResponse deletePermission(String permissionId) {
        // find the permission by id
        EndpointPermission endpointPermission = endpointPermissionRepository.findById(permissionId).orElseThrow(
                () -> new CustomException("Permission not found", 404, Map.of("permissionId", permissionId))
        );

        // delete the permission
        endpointPermissionRepository.delete(endpointPermission);
        return PermissionResponse.builder()
                .id(endpointPermission.getId())
                .endpoint(endpointPermission.getEndpoint())
                .method(endpointPermission.getHttpMethod())
                .description(endpointPermission.getDescription())
                .roles(endpointPermission.getRoles()
                        .stream()
                        .map(role -> RoleResponse.builder()
                                .id(role.getId())
                                .role(role.getName())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public PermissionResponse updatePermission(String permissionId, PermissionRequest updatePermissionRequest) {
        // find the permission by id
        EndpointPermission endpointPermission = endpointPermissionRepository.findById(permissionId).orElseThrow(
                () -> new CustomException("Permission not found", 404, Map.of("permissionId", permissionId))
        );
        // update the permission
        endpointPermission.setEndpoint(updatePermissionRequest.getEndpoint());
        endpointPermission.setHttpMethod(updatePermissionRequest.getMethod());
        endpointPermission.setDescription(updatePermissionRequest.getDescription());
        endpointPermission.setRoles(updatePermissionRequest
                .getRoles()
                .stream()
                .map(
                        roleName -> roleService.getRoleByName(roleName)
                )
                .collect(Collectors.toSet())
        );

        // save the permission
        EndpointPermission saved = endpointPermissionRepository.save(endpointPermission);
        return PermissionResponse.builder()
                .id(saved.getId())
                .endpoint(saved.getEndpoint())
                .method(saved.getHttpMethod())
                .description(saved.getDescription())
                .roles(saved.getRoles()
                        .stream()
                        .map(role -> RoleResponse.builder()
                                .id(role.getId())
                                .role(role.getName())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public PermissionResponse getPermissionById(String permissionId) {
        // find the permission by id
        EndpointPermission endpointPermission = endpointPermissionRepository.findById(permissionId).orElseThrow(
                () -> new CustomException("Permission not found", 404, Map.of("permissionId", permissionId))
        );
        Set<Role> roles = endpointPermission.getRoles();
        Set<RoleResponse> roleResponses = roles.stream().map(role -> RoleResponse.builder()
                .id(role.getId())
                .role(role.getName())
                .build()).collect(Collectors.toSet());
        return PermissionResponse.builder()
                .id(endpointPermission.getId())
                .endpoint(endpointPermission.getEndpoint())
                .method(endpointPermission.getHttpMethod())
                .description(endpointPermission.getDescription())
                .roles(roleResponses)
                .build();
    }

    @Override
    public PermissionResponse permissionByEndpointAndHttpMethod(String endpoint, String httpMethod) {
        // find the permission by endpoint and httpMethod
        EndpointPermission endpointPermission = endpointPermissionRepository.findByEndpointAndHttpMethod(endpoint, httpMethod).orElseThrow(
                () -> new CustomException("Permission not found", 404, Map.of("endpoint", endpoint, "httpMethod", httpMethod))
        );
        Set<Role> roles = endpointPermission.getRoles();
        Set<RoleResponse> roleResponses = roles.stream().map(role -> RoleResponse.builder()
                .id(role.getId())
                .role(role.getName())
                .build()).collect(Collectors.toSet());
        return PermissionResponse.builder()
                .id(endpointPermission.getId())
                .endpoint(endpointPermission.getEndpoint())
                .method(endpointPermission.getHttpMethod())
                .description(endpointPermission.getDescription())
                .roles(roleResponses)
                .build();
    }

    @Override
    public PermissionResponse removeRole(String permissionId, String roleId) {
        // find the permission by id
        EndpointPermission endpointPermission = endpointPermissionRepository.findById(permissionId).orElseThrow(
                () -> new CustomException("Permission not found", 404, Map.of("permissionId", permissionId))
        );
        // find the role by id
        Role role = roleService.getRoleObjectById(roleId);
        List<String> roleIdList = endpointPermission.getRoles().stream().map(Role::getId).toList();
        // check if the role is in the permission
        if (!roleIdList.contains(roleId)) {
            throw new CustomException("Role not found in permission", 404, Map.of("roleId", roleId, "permissionId", permissionId));
        }
        // reset the roles of the permission
        endpointPermission.setRoles(endpointPermission.getRoles().stream().filter(r -> !r.getId().equals(roleId)).collect(Collectors.toSet()));
        // save the permission
        EndpointPermission saved = endpointPermissionRepository.save(endpointPermission);
        return PermissionResponse.builder()
                .id(saved.getId())
                .endpoint(saved.getEndpoint())
                .method(saved.getHttpMethod())
                .description(saved.getDescription())
                .roles(saved.getRoles()
                        .stream()
                        .map(role1 -> RoleResponse.builder()
                                .id(role1.getId())
                                .role(role1.getName())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public PermissionResponse addRole(String permissionId, String roleId) {
        // find the permission by id
        EndpointPermission endpointPermission = endpointPermissionRepository.findById(permissionId).orElseThrow(
                () -> new CustomException("Permission not found", 404, Map.of("permissionId", permissionId))
        );
        // find the role by id
        Role role = roleService.getRoleObjectById(roleId);
        List<String> roleIdList = endpointPermission.getRoles().stream().map(Role::getId).toList();
        // check if the role is already in the permission
        if (roleIdList.contains(roleId)) {
            throw new CustomException("Role already in permission", 400, Map.of("roleId", roleId, "permissionId", permissionId));
        }
        // add the role to the permission
        endpointPermission.getRoles().add(role);
        // save the permission
        EndpointPermission saved = endpointPermissionRepository.save(endpointPermission);
        return PermissionResponse.builder()
                .id(saved.getId())
                .endpoint(saved.getEndpoint())
                .method(saved.getHttpMethod())
                .description(saved.getDescription())
                .roles(saved.getRoles()
                        .stream()
                        .map(role1 -> RoleResponse.builder()
                                .id(role1.getId())
                                .role(role1.getName())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
