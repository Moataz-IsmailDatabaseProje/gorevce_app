package com.gorevce.authentication_service.dto.response;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginResponseDto {
    private String Id;
    private String token;
    private String username;
    private String email;
    private List<String> roles;
}
