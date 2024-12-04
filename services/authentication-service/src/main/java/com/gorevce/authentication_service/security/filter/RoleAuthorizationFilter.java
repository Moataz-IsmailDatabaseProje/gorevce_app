package com.gorevce.authentication_service.security.filter;

import com.gorevce.authentication_service.model.EndpointPermission;
import com.gorevce.authentication_service.model.Role;
import com.gorevce.authentication_service.repository.EndpointPermissionsRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private EndpointPermissionsRepository endpointPermissionsRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String endpoint = request.getRequestURI();
        String httpMethod = request.getMethod();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Optional<EndpointPermission> endpointPermission = endpointPermissionsRepository.findByEndpointAndHttpMethod(endpoint, httpMethod);
            if (endpointPermission.isPresent()) {
                Set<String> userRoles = authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toSet());
                boolean hasPermission = endpointPermission.get().getRoles().stream()
                        .map(Role::getName)
                        .anyMatch(userRoles::contains);

                if (!hasPermission) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}