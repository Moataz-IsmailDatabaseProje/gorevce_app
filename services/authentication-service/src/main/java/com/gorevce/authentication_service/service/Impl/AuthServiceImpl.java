package com.gorevce.authentication_service.service.Impl;

import com.gorevce.authentication_service.dto.request.*;
import com.gorevce.authentication_service.dto.response.LoginResponseDto;
import com.gorevce.authentication_service.dto.response.RoleResponse;
import com.gorevce.authentication_service.dto.response.UserInfoResponse;
import com.gorevce.authentication_service.exception.CustomException;
import com.gorevce.authentication_service.model.Role;
import com.gorevce.authentication_service.model.User;
import com.gorevce.authentication_service.repository.UserRepository;
import com.gorevce.authentication_service.security.jwt.JwtUtils;
import com.gorevce.authentication_service.service.AuthService;
import com.gorevce.authentication_service.service.EmailService;
import com.gorevce.authentication_service.service.RoleService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public UserInfoResponse signup(SignupRequest signupRequest) {
        // check if user credential exists
        if (userRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
            // throw exception
            throw new CustomException("Email already in use", 400, Collections.singletonMap("email", signupRequest.getEmail()));
        }
        if (userRepository.findByUsername(signupRequest.getUsername()).isPresent()) {
            // throw exception
            throw new CustomException("Username already exists", 400, Collections.singletonMap("username", signupRequest.getUsername()));
        }

        // check if role exists
        if (!roleService.roleExists("ROLE_USER")) {
            // throw
            throw new CustomException("ROLE_USER not found",400,null);
        }
        Role role = roleService.getRoleByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        // create user
        User user = User.builder()
                .email(signupRequest.getEmail())
                .username(signupRequest.getUsername())
                .roles(roles)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(false)
                .build();
        // create verification token
        user.setVerificationToken(UUID.randomUUID().toString());
        // Save the user with the verification token
        User savedUser = userRepository.save(user);
        // send verification email
        String verificationUrl = "http://localhost:8090/authentication/auth/verify-email?token=" + user.getVerificationToken();
        try {
            emailService.sendVerificationEmail(user.getEmail(), verificationUrl);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        // return user info response
        return UserInfoResponse.builder()
                .Id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .isEmailVerified(savedUser.getIsEmailVerified())
                .roles(
                        savedUser.getRoles().stream().map(role1 -> RoleResponse.builder()
                                .id(role1.getId())
                                .role(role1.getName())
                                .build()
                        ).toList()
                )
                .build();
    }

    @Override
    public LoginResponseDto signin(AuthRequest authRequest) {
        User user = null;
        if (authRequest.getUsername() != null) {
            user = userRepository.findByUsername(authRequest.getUsername())
                    .orElseThrow(() -> new CustomException("User not found", 404, Collections.singletonMap("username", authRequest.getUsername())));
        } else if (authRequest.getEmail() != null) {
            user = userRepository.findByEmail(authRequest.getEmail())
                    .orElseThrow(() -> new CustomException("User not found", 404, Collections.singletonMap("email", authRequest.getEmail())));
        }
        // check if user enabled
        if (user != null && !user.getIsEnabled()) {
            throw new CustomException("User is disabled", 400, Collections.singletonMap("email", user.getEmail()));
        }
        // Authenticate user credentials
        Authentication authentication = null;
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (Exception ex) {
            throw new CustomException("Invalid username or password", 401, Collections.singletonMap("username", authRequest.getUsername()));
        }
        assert user != null;
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String jwt = jwtUtils.generateToken(userDetails);
        return LoginResponseDto.builder()
                .Id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .token(jwt)
                .roles(user.getRoles().stream().map(Role::getName).toList())
                .build();
    }

    @Override
    public UserInfoResponse verifyEmail(String token) {
        // find by verification token
        User user = userRepository.findByVerificationToken(token)
                .orElseThrow(() -> new CustomException("Invalid token", 400, Collections.singletonMap("token", token)));
        // verify email
        user.setIsEmailVerified(true);
        user.setIsEnabled(true);
        user.setVerificationToken(null);
        userRepository.save(user);
        return UserInfoResponse.builder()
                .Id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .isEmailVerified(user.getIsEmailVerified())
                .roles(
                        user.getRoles().stream().map(role -> RoleResponse.builder()
                                .id(role.getId())
                                .role(role.getName())
                                .build()
                        ).toList()
                )
                .build();

    }

    @Override
    public UserInfoResponse forgotPassword(String email) {
        // find by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("User not found", 404, Collections.singletonMap("email", email)));
        // check if user have reset password token
        String resetPasswordToken = user.getResetPasswordToken();
        if (resetPasswordToken == null) {
            // set reset password token
            user.setResetPasswordToken(UUID.randomUUID().toString());
            // save user
            userRepository.save(user);
        }
        // send reset password email
        String resetPasswordUrl = "http://localhost:8090/authentication/auth/reset-password?token=" + user.getResetPasswordToken();
        try {
            emailService.sendResetPasswordEmail(user.getEmail(), resetPasswordUrl);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return UserInfoResponse.builder()
                .Id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .isEmailVerified(user.getIsEmailVerified())
                .roles(
                        user.getRoles().stream().map(role -> RoleResponse.builder()
                                .id(role.getId())
                                .role(role.getName())
                                .build()
                        ).toList()
                )
                .build();
    }

    @Override
    public UserInfoResponse createNewPassword(String email, PasswordRequest passwordRequest) {
        // find by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("User not found", 404, Collections.singletonMap("email", email)));
        // check if user enabled
        if (!user.getIsEnabled()) {
            throw new CustomException("User is disabled", 400, Collections.singletonMap("email", email));
        }
        // check if user email verified
        if (!user.getIsEmailVerified()) {
            throw new CustomException("Email not verified", 400, Collections.singletonMap("email", email));
        }
        // check if user created password
        if (user.getIsPasswordCreated()) {
            throw new CustomException("Password already created", 400, Collections.singletonMap("email", email));
        }
        // set password
        user.setPassword(passwordEncoder.encode(passwordRequest.getPassword()));
        user.setIsPasswordCreated(true);
        // save user
        userRepository.save(user);
        return UserInfoResponse.builder()
                .Id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .isEmailVerified(user.getIsEmailVerified())
                .roles(
                        user.getRoles().stream().map(role -> RoleResponse.builder()
                                .id(role.getId())
                                .role(role.getName())
                                .build()
                        ).toList()
                )
                .build();
    }

    @Override
    public UserInfoResponse changePassword(String token, PasswordRequest passwordRequest) {
        // find by reset password token
        User user = userRepository.findByResetPasswordToken(token)
                .orElseThrow(() -> new CustomException("Invalid token", 400, Collections.singletonMap("token", token)));
        // check if user enabled
        if (!user.getIsEnabled()) {
            throw new CustomException("User is disabled", 400, Collections.singletonMap("email", user.getEmail()));
        }
        // check if user email verified
        if (!user.getIsEmailVerified()) {
            throw new CustomException("Email not verified", 400, Collections.singletonMap("email", user.getEmail()));
        }
        // set password
        user.setPassword(passwordEncoder.encode(passwordRequest.getPassword()));
        user.setResetPasswordToken(null);
        // save user
        userRepository.save(user);
        return UserInfoResponse.builder()
                .Id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .isEmailVerified(user.getIsEmailVerified())
                .roles(
                        user.getRoles().stream().map(role -> RoleResponse.builder()
                                .id(role.getId())
                                .role(role.getName())
                                .build()
                        ).toList()
                )
                .build();

    }

    @Override
    public UserInfoResponse changeEmail(ChangeEmailRequest changeEmailRequest, String token) {
        String tokenWithoutBearer = token.substring(7);
        // extract username from token
        String username = jwtUtils.extractUsername(tokenWithoutBearer);
        // find by username
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException("User not found", 404, Collections.singletonMap("username", username)));

        // find by email
        User user = userRepository.findByEmail(changeEmailRequest.getLastEmail())
                .orElseThrow(() -> new CustomException("User not found", 404, Collections.singletonMap("email", changeEmailRequest.getLastEmail())));
        // check if user who want to change email is the same user
        if (currentUser.getEmail()!=null) {
            if (!currentUser.getEmail().equals(changeEmailRequest.getLastEmail())) {
                throw new CustomException("Unauthorized", 401, Collections.singletonMap("email", currentUser.getEmail()));
            }
        } else {
            if (!currentUser.getUsername().equals(user.getUsername())) {
                throw new CustomException("Unauthorized", 401, Collections.singletonMap("username", currentUser.getUsername()));
            }
        }
        // check if user enabled
        if (!user.getIsEnabled()) {
            throw new CustomException("User is disabled", 400, Collections.singletonMap("email", changeEmailRequest.getLastEmail()));
        }
        // set email
        user.setEmail(changeEmailRequest.getNewEmail());
        // save user
        userRepository.save(user);
        return UserInfoResponse.builder()
                .Id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .isEmailVerified(user.getIsEmailVerified())
                .roles(
                        user.getRoles().stream().map(role -> RoleResponse.builder()
                                .id(role.getId())
                                .role(role.getName())
                                .build()
                        ).toList()
                )
                .build();
    }

    @Override
    public UserInfoResponse changeUsername(ChangeUsernameRequest changeUsernameRequest, String token) {
        String tokenWithoutBearer = token.substring(7);
        // extract username from token
        String username = jwtUtils.extractUsername(tokenWithoutBearer);
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException("User not found", 404, Collections.singletonMap("username", username)));
        // check if user who want to change username is the same user
        if (!currentUser.getUsername().equals(changeUsernameRequest.getLastUsername())) {
            throw new CustomException("Unauthorized", 401, Collections.singletonMap("username", changeUsernameRequest.getLastUsername()));
        }
        // find by username
        User user = userRepository.findByUsername(changeUsernameRequest.getLastUsername())
                .orElseThrow(() -> new CustomException("User not found", 404, Collections.singletonMap("username", changeUsernameRequest.getLastUsername())));
        // check if user enabled
        if (!user.getIsEnabled()) {
            throw new CustomException("User is disabled", 400, Collections.singletonMap("username", changeUsernameRequest.getLastUsername()));
        }
        // set username
        user.setUsername(changeUsernameRequest.getNewUsername());
        // save user
        userRepository.save(user);
        return UserInfoResponse.builder()
                .Id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .isEmailVerified(user.getIsEmailVerified())
                .roles(
                        user.getRoles().stream().map(role -> RoleResponse.builder()
                                .id(role.getId())
                                .role(role.getName())
                                .build()
                        ).toList()
                )
                .build();

    }

    @Override
    public UserInfoResponse setRoleToUser(String userId, String roleId) {
        // find by user id
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found", 404, Collections.singletonMap("user", userId)));
        // find by role id
        Role role = roleService.getRoleObjectById(roleId);
        // check if user enabled
        if (!user.getIsEnabled()) {
            throw new CustomException("User is disabled", 400, Collections.singletonMap("user", userId));
        }
        // check if user email verified
        if (!user.getIsEmailVerified()) {
            throw new CustomException("Email not verified", 400, Collections.singletonMap("user", userId));
        }
        // check if user created password
        if (!user.getIsPasswordCreated()) {
            throw new CustomException("Password not created", 400, Collections.singletonMap("user", userId));
        }
        // check if user already have the role
        if (user.getRoles().contains(role)) {
            throw new CustomException("User already have the role", 400, Collections.singletonMap("role", roleId));
        }
        // set role
        user.getRoles().add(role);
        // save user
        userRepository.save(user);

        return UserInfoResponse.builder()
                .Id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .isEmailVerified(user.getIsEmailVerified())
                .roles(
                        user.getRoles().stream().map(role1 -> RoleResponse.builder()
                                .id(role1.getId())
                                .role(role1.getName())
                                .build()
                        ).toList()
                )
                .build();
    }
}
