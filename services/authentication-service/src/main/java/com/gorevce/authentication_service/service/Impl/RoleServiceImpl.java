package com.gorevce.authentication_service.service.Impl;

import com.gorevce.authentication_service.exception.CustomException;
import com.gorevce.authentication_service.model.Role;
import com.gorevce.authentication_service.repository.RoleRepository;
import com.gorevce.authentication_service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean roleExists(String role) {
        return roleRepository.findByName(role).isPresent();
    }

    @Override
    public Role getRole(String role) {
        return roleRepository.findByName(role).orElse(null);
    }

    @Override
    public void createRole(String role) {
        // Check if the role already exists
        if (roleRepository.findByName(role).isPresent()) {
            throw new CustomException("Role already exists", 400, Collections.singletonMap("role", role));
        }
        // Create a new role
        Role newRole = Role.builder()
                .name(role)
                .build();
        // Save the role
        roleRepository.save(newRole);

    }

    @Override
    public void deleteRole(String role) {
        // Check if the role exists
        if (roleRepository.findByName(role).isEmpty()) {
            throw new CustomException("Role does not exist", 404, Collections.singletonMap("role", role));
        }
        // Delete the role
        roleRepository.deleteById(role);

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
        roleRepository.save(newRole);
        return newRole;
    }
}
