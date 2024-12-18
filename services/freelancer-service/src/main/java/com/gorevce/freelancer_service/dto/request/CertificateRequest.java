package com.gorevce.freelancer_service.dto.request;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CertificateRequest {
    private String name;
    private String description;
    private String issuer;
    private Date issueDate;
    private Date expirationDate;
    private String credentialId;
    private String credentialUrl;
    private String imageUrl;
    private String freelancerId;
}
