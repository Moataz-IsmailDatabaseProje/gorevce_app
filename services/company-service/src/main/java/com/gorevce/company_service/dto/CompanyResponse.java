package com.gorevce.company_service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyResponse {
    private String id;
    private String name;
    private String description;
    private String taxNumber;
    private String email;
    private String phone;
    private String website;
    private String logo;
    private String userId;
    private String addressId;
}
