package com.gorevce.authentication_service.controller;

import com.gorevce.authentication_service.dto.request.*;
import com.gorevce.authentication_service.exception.CustomException;
import com.gorevce.authentication_service.service.AuthService;
import com.gorevce.authentication_service.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    // signUp
    @PostMapping("/register")
    ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "User registered successfully",
                            200,
                            authService.signup(signupRequest)
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
    // signIn
    @PostMapping("/login")
    ResponseEntity<?> signIn(@RequestBody AuthRequest authRequest) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "User logged in successfully",
                            200,
                            authService.signin(authRequest)
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
    // TODO: Implement signOut
    // signOut


    // changePassword
    @PostMapping("/reset-password")
    ResponseEntity<?> changePassword(@RequestParam String token,@RequestBody PasswordRequest password) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Password changed successfully",
                            200,
                            authService.changePassword(token, password)
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

    // forgotPassword
    @PostMapping("/forgot-password")
    ResponseEntity<?> forgotPassword(@RequestParam String email) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Password reset link sent to email",
                            200,
                            authService.forgotPassword(email)
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
    // verifyEmail
    @GetMapping("/verify-email")
    ResponseEntity<?> verifyEmail(@RequestParam String token) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Email verified successfully",
                            200,
                            authService.verifyEmail(token)
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
    // changeEmail
    @PostMapping("/change-email")
    ResponseEntity<?> changeEmail(@RequestBody ChangeEmailRequest changeEmailRequest) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Email changed successfully",
                            200,
                            authService.changeEmail(changeEmailRequest)
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
    ResponseEntity<?> changeUsername(@RequestBody ChangeUsernameRequest changeUsernameRequest) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Username changed successfully",
                            200,
                            authService.changeUsername(changeUsernameRequest)
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

    // create new password
    @PostMapping("/create-password")
    ResponseEntity<?> createNewPassword(@RequestParam String email,@RequestBody PasswordRequest passwordRequest) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Password changed successfully",
                            200,
                            authService.createNewPassword(email, passwordRequest)
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
