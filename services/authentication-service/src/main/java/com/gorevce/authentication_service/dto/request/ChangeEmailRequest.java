package com.gorevce.authentication_service.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeEmailRequest {
    String Id;
    String username;
    String lastEmail;
    String newEmail;
}
