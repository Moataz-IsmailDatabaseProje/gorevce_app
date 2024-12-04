package com.gorevce.authentication_service.controller;

import com.gorevce.authentication_service.dto.request.ChangeEmailRequest;
import com.gorevce.authentication_service.dto.request.ChangeUsernameRequest;
import com.gorevce.authentication_service.exception.CustomException;
import com.gorevce.authentication_service.service.AuthService;
import com.gorevce.authentication_service.util.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication/change-credential")
public class ChangeCredentialController {

    @Autowired
    private AuthService authService;

    // changeEmail
    @PostMapping("/change-email")
    ResponseEntity<?> changeEmail(@RequestBody ChangeEmailRequest changeEmailRequest, HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Email changed successfully, verify mail send to new email address please verify your new email.",
                            200,
                            authService.changeEmail(changeEmailRequest, token)
                    )
            );
        }
        catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            new ApiResponse(
                                    e.getMessage(),
                                    e.getHttpStatusCode(),
                                    e.getDetails()
                            )
                    );
        }
    }
    // changeUsername
    @PostMapping("/change-username")
    ResponseEntity<?> changeUsername(@RequestBody ChangeUsernameRequest changeUsernameRequest, HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Username changed successfully, please login again with your new username.",
                            200,
                            authService.changeUsername(changeUsernameRequest, token)
                    )
            );
        }
        catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            new ApiResponse(
                                    e.getMessage(),
                                    e.getHttpStatusCode(),
                                    e.getDetails()
                            )
                    );
        }
    }

}
