package com.gorevce.authentication_service.service;

import com.gorevce.authentication_service.dto.request.*;
import com.gorevce.authentication_service.dto.response.LoginResponseDto;
import com.gorevce.authentication_service.dto.response.RoleResponse;
import com.gorevce.authentication_service.dto.response.UserInfoResponse;

import java.util.List;

public interface AuthService {
    // signup
    UserInfoResponse signup(SignupRequest signupRequest);

    // signin
    LoginResponseDto signin(AuthRequest authRequest);

    // verify email
    UserInfoResponse verifyEmail(String token);

    // forgot password
    UserInfoResponse forgotPassword(String email);

    // create new password
    UserInfoResponse createNewPassword(String email, PasswordRequest passwordRequest);

    // change password
    UserInfoResponse changePassword(String token, PasswordRequest passwordRequest);

    // change email
    UserInfoResponse changeEmail(ChangeEmailRequest changeEmailRequest, String token);

    // change username
    UserInfoResponse changeUsername(ChangeUsernameRequest changeUsernameRequest, String token);

    UserInfoResponse setRoleToUser(String userId, String roleId);
}