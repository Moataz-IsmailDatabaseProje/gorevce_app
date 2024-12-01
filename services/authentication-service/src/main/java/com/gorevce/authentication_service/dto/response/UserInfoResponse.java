package com.gorevce.authentication_service.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoResponse {
    String Id;
    String username;
    String email;
    Boolean isEmailVerified;
    List<RoleResponse> roles;
}
