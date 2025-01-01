package com.gorevce.authentication_service.controller;

import com.gorevce.authentication_service.exception.CustomException;
import com.gorevce.authentication_service.service.AuthService;
import com.gorevce.authentication_service.service.RoleService;
import com.gorevce.authentication_service.util.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/authentication/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthService authService;

    // getRoles
    @GetMapping("/get-roles")
    @Operation(summary = "Get roles", description = "Get roles")
    public ResponseEntity<?> getRoles() {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Roles retrieved successfully",
                            200,
                            roleService.getAllRoles()
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
    // addRole
    @PostMapping("/create-role")
    @Operation(summary = "Add role", description = "Add role")
    public ResponseEntity<?> addRole(@RequestParam String role) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Role added successfully",
                            200,
                            roleService.createRole(role)
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
    // updateRole
    @PutMapping("/update-role")
    @Operation(summary = "Update role", description = "Update role")
    public ResponseEntity<?> updateRole(@RequestParam String roleId, @RequestParam String role) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Role updated successfully",
                            200,
                            roleService.updateRole(roleId, role)
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
    // deleteRole
    @DeleteMapping("/delete-role")
    @Operation(summary = "Delete role", description = "Delete role")
    public ResponseEntity<?> deleteRole(@RequestParam String roleId) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Role deleted successfully",
                            200,
                            roleService.deleteRole(roleId)
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
    // getRoleById
    @GetMapping("/get-role")
    @Operation(summary = "Get role by id", description = "Get role by id")
    public ResponseEntity<?> getRoleById(@RequestParam String roleId) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Role retrieved successfully",
                            200,
                            roleService.getRoleById(roleId)
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

    // set role to user
    @PostMapping("/set-role")
    @Operation(summary = "Set role to user", description = "Set role to user")
    public ResponseEntity<?> setRoleToUser(@RequestParam String userId, @RequestParam String roleId) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Role set to user successfully",
                            200,
                            authService.setRoleToUser(userId, roleId)
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
    // remove role from user
    @DeleteMapping("/remove-role")
    @Operation(summary = "Remove role from user", description = "Remove role from user")
    public ResponseEntity<?> removeRoleFromUser(@RequestParam String userId, @RequestParam String roleId) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Role removed from user successfully",
                            200,
                            authService.removeRoleFromUser(userId, roleId)
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
