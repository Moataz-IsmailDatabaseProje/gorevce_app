package com.gorevce.company_service.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EndpointPermissionsDto {
    private String id;
    private String endpoint;
    private String method;
    private String description;
    private Set<RoleDto> roles;
}
