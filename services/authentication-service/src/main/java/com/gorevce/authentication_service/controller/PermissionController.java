package com.gorevce.authentication_service.controller;


import com.gorevce.authentication_service.dto.request.PermissionRequest;
import com.gorevce.authentication_service.dto.response.PermissionResponse;
import com.gorevce.authentication_service.exception.CustomException;
import com.gorevce.authentication_service.service.EndpointPermissionService;
import com.gorevce.authentication_service.util.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/authentication/permissions")
public class PermissionController {

    @Autowired
    private EndpointPermissionService endpointPermissionService;

    @GetMapping("/get-permissions")
    @Operation(summary = "Get permissions", description = "Get permissions")
    public ResponseEntity<?> getPermissions() {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Permissions retrieved successfully",
                            200,
                            endpointPermissionService.getAllPermissions()
                    )
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            new ApiResponse(
                                    e.getMessage(),
                                    e.getHttpStatusCode(),
                                    e.getDetails()
                            )
                    );
        }
    }

    // addPermission
    @PostMapping("/create-permission")
    @Operation(summary = "Add permission", description = "Add permission")
    public ResponseEntity<?> addPermission(@RequestBody PermissionRequest createPermissionRequest) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Permission added successfully",
                            200,
                            endpointPermissionService.createPermission(createPermissionRequest)
                    )
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            new ApiResponse(
                                    e.getMessage(),
                                    e.getHttpStatusCode(),
                                    e.getDetails()
                            )
                    );
        }
    }

    // deletePermission
    @DeleteMapping("/delete-permission")
    @Operation(summary = "Delete permission", description = "Delete permission")
    public ResponseEntity<?> deletePermission(@RequestParam String permissionId) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Permission deleted successfully",
                            200,
                            endpointPermissionService.deletePermission(permissionId)
                    )
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            new ApiResponse(
                                    e.getMessage(),
                                    e.getHttpStatusCode(),
                                    e.getDetails()
                            )
                    );
        }
    }

    // updatePermission
    @PutMapping("/update-permission")
    @Operation(summary = "Update permission", description = "Update permission")
    public ResponseEntity<?> updatePermission(@RequestParam String permissionId, @RequestBody PermissionRequest updatePermissionRequest) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Permission updated successfully",
                            200,
                            endpointPermissionService.updatePermission(permissionId, updatePermissionRequest)
                    )
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            new ApiResponse(
                                    e.getMessage(),
                                    e.getHttpStatusCode(),
                                    e.getDetails()
                            )
                    );
        }
    }

    // getPermissionById
    @GetMapping("/get-permission")
    @Operation(summary = "Get permission by id", description = "Get permission by id")
    public ResponseEntity<?> getPermissionById(@RequestParam String permissionId) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Permission retrieved successfully",
                            200,
                            endpointPermissionService.getPermissionById(permissionId)
                    )
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            new ApiResponse(
                                    e.getMessage(),
                                    e.getHttpStatusCode(),
                                    e.getDetails()
                            )
                    );
        }
    }
    // getPermissionByEndpoint
    @GetMapping("/rest-template/get-permission")
    @Operation(summary = "Get permission by endpoint for rest template", description = "Get permission by endpoint for rest template")
    public PermissionResponse getPermissionByEndpoint(@RequestParam String endpoint, @RequestParam String httpMethod) {
        return endpointPermissionService.permissionByEndpointAndHttpMethod(endpoint, httpMethod);
    }
}
