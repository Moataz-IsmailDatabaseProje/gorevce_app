package com.gorevce.authentication_service.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeUsernameRequest {
    String Id;
    String lastUsername;
    String newUsername;
}
