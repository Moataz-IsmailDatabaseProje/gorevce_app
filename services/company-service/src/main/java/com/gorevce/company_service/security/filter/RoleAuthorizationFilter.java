package com.gorevce.company_service.security.filter;

import com.gorevce.company_service.dto.EndpointPermissionsDto;
import com.gorevce.company_service.exception.CustomException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    @Qualifier("plainRestTemplate")
    private RestTemplate restTemplate;

    @Value("${application.config.authentication-service.url}")
    private String authenticationServiceUrl;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException , CustomException {
        try {
            String endpoint = request.getRequestURI();
            String httpMethod = request.getMethod();

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


            if (authentication != null && authentication.isAuthenticated()) {
                EndpointPermissionsDto permissions = null;

                permissions = restTemplate.getForObject(authenticationServiceUrl + "/permissions/rest-template/get-permission?endpoint=" + endpoint + "&httpMethod=" + httpMethod, EndpointPermissionsDto.class);

                if (permissions != null) {
                    Set<String> userRoles = authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toSet());
                    boolean hasPermission = permissions.getRoles().stream()
                            .anyMatch(role -> userRoles.contains(role.getRole()));
                    if (!hasPermission) {
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        response.getWriter().write("User does not have permission to access this endpoint");
                        throw new CustomException("User does not have permission to access this endpoint", 403, Map.of("endpoint", endpoint, "method", httpMethod));
                    }
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Internal server error occurred: " + e.getMessage());
        }
    }
}
