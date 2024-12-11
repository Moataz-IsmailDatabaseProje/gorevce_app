package com.gorevce.address_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressRequest {
    private String country;
    private String city;
    private String street;
    private String postalCode;
    private String additionalInfo;
    private String addressOfId;
}
