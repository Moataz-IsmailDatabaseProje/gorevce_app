package com.gorevce.authentication_service.controller;


import com.gorevce.authentication_service.dto.request.PermissionRequest;
import com.gorevce.authentication_service.exception.CustomException;
import com.gorevce.authentication_service.service.EndpointPermissionService;
import com.gorevce.authentication_service.util.ApiResponse;
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
}
