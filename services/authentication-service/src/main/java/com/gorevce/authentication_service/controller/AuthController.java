package com.gorevce.authentication_service.controller;

import com.gorevce.authentication_service.dto.request.*;
import com.gorevce.authentication_service.dto.response.UserInfoResponse;
import com.gorevce.authentication_service.exception.CustomException;
import com.gorevce.authentication_service.service.AuthService;
import com.gorevce.authentication_service.util.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.ws.rs.OPTIONS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.processing.SupportedOptions;

@RestController
@CrossOrigin
@RequestMapping("/authentication/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    // signUp
    @PostMapping("/register")
    @Operation(summary = "Sign up", description = "Sign up")
    ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "registered successfully, please verify your email to login an email has been sent to your email address.",
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
    @Operation(summary = "Sign in", description = "Sign in")
    @PostMapping("/login")
    ResponseEntity<?> signIn(@RequestBody AuthRequest authRequest) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "logged in successfully",
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
    @Operation(summary = "Change password", description = "Change password")
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
    @Operation(summary = "Forgot password", description = "Forgot password")
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
    @Operation(summary = "Verify email", description = "Verify email")
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

    // create new password
    @PostMapping("/create-password")
    @Operation(summary = "Create new password", description = "Create new password")
    ResponseEntity<?> createNewPassword(@RequestParam String email,@RequestBody PasswordRequest passwordRequest) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Password created successfully",
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

    // get user details
    @GetMapping("/rest-template/get-user/{userId}")
    @Operation(summary = "Get user details", description = "Get user details")
    ResponseEntity<?> getUserDetails(@PathVariable String userId) {
        try {
            return ResponseEntity.ok(
                    authService.getUserInfoResponseById(userId)
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
