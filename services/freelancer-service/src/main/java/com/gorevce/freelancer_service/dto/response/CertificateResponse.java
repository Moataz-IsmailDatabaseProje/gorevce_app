package com.gorevce.freelancer_service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CertificateResponse {
    private String id;
    private String name;
    private String description;
    private String issuer;
    private String imageUrl;
    private String freelancerId;
}
