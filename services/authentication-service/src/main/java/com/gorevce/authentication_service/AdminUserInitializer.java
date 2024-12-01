package com.gorevce.authentication_service;

import com.gorevce.authentication_service.model.Role;
import com.gorevce.authentication_service.model.User;
import com.gorevce.authentication_service.repository.UserRepository;
import com.gorevce.authentication_service.service.EndpointPermissionService;
import com.gorevce.authentication_service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private EndpointPermissionService endpointPermissionService;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            // setup default roles

            // setup default permissions

            // setup default super admin user
            Role userRole = roleService.createRoleIfNotFound("ROLE_USER");
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin123")); // Use secure password encoding
            adminUser.setRoles(Set.of(Role.builder().name("SUPER_ADMIN").build()));
            userRepository.save(adminUser);
            System.out.println("Default admin user created.");
        }
    }
}
