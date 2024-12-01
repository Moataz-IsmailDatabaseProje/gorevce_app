package com.gorevce.authentication_service.service;

import com.gorevce.authentication_service.dto.request.PermissionRequest;
import com.gorevce.authentication_service.dto.response.PermissionResponse;
import com.gorevce.authentication_service.model.Role;

import java.util.List;
import java.util.Set;

public interface EndpointPermissionService {

    List<PermissionResponse> getAllPermissions();

    PermissionResponse createPermission(PermissionRequest createPermissionRequest);

    PermissionResponse deletePermission(String permissionId);

    PermissionResponse updatePermission(String permissionId, PermissionRequest updatePermissionRequest);

    PermissionResponse getPermissionById(String permissionId);
}
