package com.gorevce.freelancer_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "certificates")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Certificate {
    @Id
    private String id;
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
