package com.gorevce.address_service.util;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
    private String message;
    private int httpStatusCode;
    private Object response;
}
