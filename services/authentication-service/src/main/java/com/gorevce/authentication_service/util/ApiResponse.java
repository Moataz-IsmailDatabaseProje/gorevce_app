package com.gorevce.authentication_service.util;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {
    private String message;
    private int httpStatusCode;
    private Object response;
}