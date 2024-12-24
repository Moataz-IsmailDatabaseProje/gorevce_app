package com.gorevce.company_service.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "company")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class company {
    private String id;
    private String name;
    private String description;
    private String email;
    private String phone;
    private String website;
    private String logo;

    private String userId;
    private String addressId;
}
