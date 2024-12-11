package com.gorevce.address_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Id
    private String id;
    private String country;
    private String city;
    private String street;
    private String postalCode;
    private String additionalInfo;
    private String addressOfId;
}
