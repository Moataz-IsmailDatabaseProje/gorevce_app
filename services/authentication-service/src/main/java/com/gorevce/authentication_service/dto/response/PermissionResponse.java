package com.gorevce.authentication_service.dto.response;

import com.gorevce.authentication_service.model.Role;
import lombok.*;

import java.util.Set;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class PermissionResponse {
    private String id;
    private String endpoint;
    private String method;
    private String description;
    private Set<RoleResponse> roles;


}
