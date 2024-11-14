package com.gorevce.authentication_service.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordRequest {
    private String password;
}
