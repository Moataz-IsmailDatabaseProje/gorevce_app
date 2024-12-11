package com.gorevce.address_service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse {
    private String id;
    private String country;
    private String city;
    private String street;
    private String postalCode;
    private String additionalInfo;
    private String addressOfId;

}
