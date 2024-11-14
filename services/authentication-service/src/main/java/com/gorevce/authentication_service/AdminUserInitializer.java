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
            // setup default super admin user
            Role supreAdminRole = roleService.createRoleIfNotFound("ROLE_SUPER_ADMIN");
            // setup the endpoint permissions
            endpointPermissionService.createEndpointPermissionIfNotFound("/auth/register", "POST", Set.of(supreAdminRole));
            endpointPermissionService.createEndpointPermissionIfNotFound("/auth/login", "POST", Set.of(supreAdminRole));
            endpointPermissionService.createEndpointPermissionIfNotFound("/auth/logout", "POST", Set.of(supreAdminRole));
            endpointPermissionService.createEndpointPermissionIfNotFound("/auth/create-password", "POST", Set.of(supreAdminRole));
            endpointPermissionService.createEndpointPermissionIfNotFound("/auth/reset-password", "POST", Set.of(supreAdminRole));
            endpointPermissionService.createEndpointPermissionIfNotFound("/auth/forgot-password", "POST", Set.of(supreAdminRole));
            endpointPermissionService.createEndpointPermissionIfNotFound("/auth/verify-email", "GET", Set.of(supreAdminRole));
            endpointPermissionService.createEndpointPermissionIfNotFound("/auth/change-email", "POST", Set.of(supreAdminRole));
            endpointPermissionService.createEndpointPermissionIfNotFound("/auth/change-username", "POST", Set.of(supreAdminRole));

            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin123")); // Use secure password encoding
            adminUser.setRoles(Set.of(Role.builder().name("ROLE_SUPER_ADMIN").build()));
            userRepository.save(adminUser);
            System.out.println("Default admin user created.");
        }
    }
}
