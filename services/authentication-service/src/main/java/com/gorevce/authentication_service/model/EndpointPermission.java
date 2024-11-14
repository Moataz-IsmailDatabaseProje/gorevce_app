package com.gorevce.authentication_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "endpoint_permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EndpointPermission {
    @Id
    private String id;
    private String endpoint;
    private String httpMethod;
    private Set<Role> roles;

}
