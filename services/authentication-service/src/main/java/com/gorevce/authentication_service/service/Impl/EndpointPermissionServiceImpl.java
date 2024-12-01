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
        // get all permissions
        List<EndpointPermission> endpointPermissions = endpointPermissionRepository.findAll();
        List<PermissionResponse> permissionResponses = new ArrayList<>();
        // for each permission in endpointPermissions
        for (EndpointPermission endpointPermission : endpointPermissions) {
            Set<Role> roles = endpointPermission.getRoles();
            Set<RoleResponse> roleResponses = roles.stream().map(role -> RoleResponse.builder()
                    .id(role.getId())
                    .role(role.getName())
                    .build()).collect(Collectors.toSet());

            // set the roles of the permission to the roleResponses
            permissionResponses = endpointPermissions.stream().map(
                    permission -> PermissionResponse.builder()
                    .id(permission.getId())
                    .endpoint(permission.getEndpoint())
                    .method(permission.getHttpMethod())
                    .description(permission.getDescription())
                    .roles(roleResponses)
                    .build()).toList();
        }
        return permissionResponses;
    }

    @Override
    public PermissionResponse createPermission(PermissionRequest createPermissionRequest) {
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
                .roles(new HashSet<>())
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
                .roles(new HashSet<>())
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
        // save the permission
        EndpointPermission saved = endpointPermissionRepository.save(endpointPermission);
        return PermissionResponse.builder()
                .id(saved.getId())
                .endpoint(saved.getEndpoint())
                .method(saved.getHttpMethod())
                .description(saved.getDescription())
                .roles(new HashSet<>())
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

}
