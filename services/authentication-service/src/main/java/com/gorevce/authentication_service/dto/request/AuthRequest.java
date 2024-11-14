package com.gorevce.authentication_service.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthRequest {
    private String username;
    private String email;
    private String password;

    public String getUsernameOrEmail() {
        return username != null ? username : email;
    }
}
