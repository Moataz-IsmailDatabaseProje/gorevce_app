package com.gorevce.freelancer_service.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String id;
    private String username;
    private String email;
    private Set<RoleDto> roles;
}
