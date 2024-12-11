package com.gorevce.address_service.util;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private int httpStatusCode;
    private Object response;
}
