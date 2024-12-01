package com.gorevce.authentication_service.dto.request;


import lombok.*;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class PermissionRequest {
    private String endpoint;
    private String method;
    private String description;
    private Set<String> roles;
}
