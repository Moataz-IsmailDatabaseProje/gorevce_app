package com.gorevce.authentication_service.service.Impl;

import com.gorevce.authentication_service.dto.response.RoleResponse;
import com.gorevce.authentication_service.exception.CustomException;
import com.gorevce.authentication_service.model.Role;
import com.gorevce.authentication_service.repository.RoleRepository;
import com.gorevce.authentication_service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean roleExists(String role) {
        return roleRepository.findByName(role).isPresent();
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.findByName(roleName).orElse(null);
    }

    @Override
    public RoleResponse createRole(String role) {
        // role to save
        String roleToSave = role.toUpperCase().startsWith("ROLE_") ? role.toUpperCase() : ("ROLE_" + role.toUpperCase());
        // Check if the role already exists
        if (roleRepository.findByName(roleToSave).isPresent()) {
            throw new CustomException("Role already exists", 400, Collections.singletonMap("role", role));
        }
        // Create a new role
        Role newRole = Role.builder()
                .name(roleToSave)
                .build();
        // Save the role
        Role createdRole = roleRepository.save(newRole);
        return RoleResponse.builder()
                .id(createdRole.getId())
                .role(createdRole.getName())
                .build();
    }

    @Override
    public RoleResponse deleteRole(String id) {
        // find the role
        Role role = roleRepository.findById(id).orElseThrow(
                () -> new CustomException("Role does not exist", 404, Collections.singletonMap("roleId", id))
        );
        // Delete the role
        roleRepository.delete(role);
        return RoleResponse.builder()
                .id(role.getId())
                .role(role.getName())
                .build();
    }

    @Override
    public RoleResponse updateRole(String id, String role) {
        // Check if the role exists
        Role roleToUpdate = roleRepository.findById(id).orElseThrow(
                () -> new CustomException("Role does not exist", 404, Collections.singletonMap("roleID", id))
        );
        // Update the role
        roleToUpdate.setName(role);
        // Save the role
        roleRepository.save(roleToUpdate);
        return RoleResponse.builder()
                .id(roleToUpdate.getId())
                .role(roleToUpdate.getName())
                .build();
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        // Get all roles
        List<Role> roles = roleRepository.findAll();
        // Create a list of role responses
        return roles.stream().map(role -> RoleResponse.builder()
                .id(role.getId())
                .role(role.getName())
                .build()).toList();
    }

    @Override
    public RoleResponse getRoleById(String id) {
        // Check if the role exists
        if (roleRepository.findById(id).isEmpty()) {
            throw new CustomException("Role does not exist", 404, Collections.singletonMap("roleId", id));
        }
        // Get the role
        Role role = roleRepository.findById(id).get();
        return RoleResponse.builder()
                .id(role.getId())
                .role(role.getName())
                .build();
    }

    @Override
    public Role getRoleObjectById(String roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    @Override
    public Role createRoleIfNotFound(String roleAdmin) {
        // Check if the role already exists
        if (roleRepository.findByName(roleAdmin).isPresent()) {
            return roleRepository.findByName(roleAdmin).get();
        }
        // Create a new role
        Role newRole = Role.builder()
                .name(roleAdmin)
                .build();
        // Save the role
        return roleRepository.save(newRole);
    }
}
