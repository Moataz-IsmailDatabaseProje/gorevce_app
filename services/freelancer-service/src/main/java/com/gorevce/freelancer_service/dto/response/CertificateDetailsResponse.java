package com.gorevce.freelancer_service.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CertificateDetailsResponse {
    private String id;
    private String name;
    private String description;
    private String issuer;
    private Date issueDate;
    private Date expirationDate;
    private String credentialId;
    private String credentialUrl;
    private String imageUrl;
}
