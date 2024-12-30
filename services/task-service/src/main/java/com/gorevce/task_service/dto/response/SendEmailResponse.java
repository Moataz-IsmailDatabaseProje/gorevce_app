package com.gorevce.task_service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendEmailResponse {
    private String email;
    private String subject;
    private String message;
    private String status;
}
