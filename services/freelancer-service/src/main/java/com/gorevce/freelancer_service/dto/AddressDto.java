package com.gorevce.freelancer_service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {
    private String id;
    private String country;
    private String city;
    private String street;
    private String postalCode;
    private String additionalInfo;
    private String addressOfId;
}
