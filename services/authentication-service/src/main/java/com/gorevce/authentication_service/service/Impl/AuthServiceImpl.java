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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    @Value("${application.email.verification.url}")
    private String verificationUrl;


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

        // create roles
        Set<Role> roles = new HashSet<>();
        if (signupRequest.getRole() != null) {
            String roleName = signupRequest.getRole().toUpperCase().startsWith("ROLE_") ? signupRequest.getRole().toUpperCase() : ("ROLE_" + signupRequest.getRole().toUpperCase());
            Role role = roleService.getRoleByName(roleName);
            if (role == null) {
                throw new CustomException("Role not found", 404, Collections.singletonMap("role", signupRequest.getRole()));
            }
            Role defaultRole = roleService.getRoleByName("ROLE_USER");
            if (defaultRole == null) {
                throw new CustomException("Role not found", 404, Collections.singletonMap("role", "USER"));
            }
            roles.add(defaultRole);
            roles.add(role);
        } else {
            Role role = roleService.getRoleByName("ROLE_USER");
            if (role == null) {
                throw new CustomException("Role not found", 404, Collections.singletonMap("role", "USER"));
            }
            roles.add(role);
        }
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
        try {
            emailService.sendVerificationEmail(user.getEmail(), verificationUrl + user.getVerificationToken());
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
                        savedUser.getRoles().stream().map(role -> RoleResponse.builder()
                                .id(role.getId())
                                .role(role.getName())
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
        // find by user id if id not null or find by username if username not null
        User userToUpdate = null;
        if (changeEmailRequest.getId() != null) {
            userToUpdate = userRepository.findById(changeEmailRequest.getId())
                    .orElseThrow(() -> new CustomException("User not found", 404, Collections.singletonMap("email", changeEmailRequest.getLastEmail())));
        } else if (changeEmailRequest.getUsername() != null) {
            userToUpdate = userRepository.findByUsername(changeEmailRequest.getUsername())
                    .orElseThrow(() -> new CustomException("User not found", 404, Collections.singletonMap("email", changeEmailRequest.getLastEmail())));
        }
        if (changeEmailRequest.getId() != null && changeEmailRequest.getUsername() != null) {
            userToUpdate = userRepository.findById(changeEmailRequest.getId())
                    .orElseThrow(() -> new CustomException("User not found", 404, Collections.singletonMap("email", changeEmailRequest.getLastEmail())));
            if (!userToUpdate.getUsername().equals(changeEmailRequest.getUsername())) {
                throw new CustomException("Username not match", 400, Collections.singletonMap("username", changeEmailRequest.getUsername()));
            }
        }
        if (userToUpdate == null) {
            throw new CustomException("User not found", 404, Collections.singletonMap("email", changeEmailRequest.getLastEmail()));
        }
        // check if new email is not in use
        if (userRepository.findByEmail(changeEmailRequest.getNewEmail()).isPresent()) {
            throw new CustomException("Email already in use", 400, Collections.singletonMap("email", changeEmailRequest.getNewEmail()));
        }
        // check if user enabled
        if (!userToUpdate.getIsEnabled()) {
            throw new CustomException("User is disabled", 400, Collections.singletonMap("email", changeEmailRequest.getLastEmail()));
        }
        // check if user email verified
        if (!userToUpdate.getIsEmailVerified()) {
            throw new CustomException("Email not verified", 400, Collections.singletonMap("email", changeEmailRequest.getLastEmail()));
        }
        // check if uer who want to change email is the same user or is user with super admin role
        String tokenWithoutBearer = token.substring(7);
        // extract username from token
        String username = jwtUtils.extractUsername(tokenWithoutBearer);
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException("User not found", 404, Collections.singletonMap("username", username)));
        if (userToUpdate.getEmail() != null) {
            if (!(userToUpdate.getUsername().equals(currentUser.getUsername()) || currentUser.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_SUPER_ADMIN")))) {
                throw new CustomException("Unauthorized", 401, Collections.singletonMap("email", changeEmailRequest.getLastEmail()));
            }
        }
        // check if user email is not in use
        if (userRepository.findByEmail(changeEmailRequest.getNewEmail()).isPresent()) {
            throw new CustomException("Email already in use", 400, Collections.singletonMap("email", changeEmailRequest.getNewEmail()));
        }
        // set email
        userToUpdate.setEmail(changeEmailRequest.getNewEmail());
        userToUpdate.setIsEmailVerified(false);
        userToUpdate.setIsEnabled(false);
        // create verification token
        userToUpdate.setVerificationToken(UUID.randomUUID().toString());
        // send verification email
        String verificationUrl = "http://localhost:8090/authentication/auth/verify-email?token=" + userToUpdate.getVerificationToken();
        try {
            emailService.sendVerificationEmail(userToUpdate.getEmail(), verificationUrl);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        // save user
        userRepository.save(userToUpdate);
        // return user info response
        return UserInfoResponse.builder()
                .Id(userToUpdate.getId())
                .username(userToUpdate.getUsername())
                .email(userToUpdate.getEmail())
                .isEmailVerified(userToUpdate.getIsEmailVerified())
                .roles(
                        userToUpdate.getRoles().stream().map(role -> RoleResponse.builder()
                                .id(role.getId())
                                .role(role.getName())
                                .build()
                        ).toList()
                )
                .build();
    }

    @Override
    public UserInfoResponse changeUsername(ChangeUsernameRequest changeUsernameRequest, String token) {
        // check if user enabled
        User userToUpdate = userRepository.findById(changeUsernameRequest.getId())
                .orElseThrow(() -> new CustomException("User not found", 404, Collections.singletonMap("username", changeUsernameRequest.getLastUsername())));
        if (!userToUpdate.getUsername().equals(changeUsernameRequest.getLastUsername())) {
            throw new CustomException("Username not match", 400, Collections.singletonMap("username", changeUsernameRequest.getLastUsername()));
        }
        if (!userToUpdate.getIsEnabled()) {
            throw new CustomException("User is disabled", 400, Collections.singletonMap("username", changeUsernameRequest.getLastUsername()));
        }
        // check if user email verified
        if (!userToUpdate.getIsEmailVerified()) {
            throw new CustomException("Email not verified", 400, Collections.singletonMap("username", changeUsernameRequest.getLastUsername()));
        }
        // check new username not in use
        if (userRepository.findByUsername(changeUsernameRequest.getNewUsername()).isPresent()) {
            throw new CustomException("Username already in use", 400, Collections.singletonMap("username", changeUsernameRequest.getNewUsername()));
        }
        // find user of endpoint by username
        String tokenWithoutBearer = token.substring(7);
        // extract username from token
        String username = jwtUtils.extractUsername(tokenWithoutBearer);
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException("User not found", 404, Collections.singletonMap("username", username)));
        // check if user who want to change email is the same user or is user with super admin role
        if (userToUpdate.getEmail() != null) {
            if (!(userToUpdate.getUsername().equals(currentUser.getUsername()) || currentUser.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_SUPER_ADMIN")))) {
                throw new CustomException("Unauthorized", 401, Collections.singletonMap("username", changeUsernameRequest.getLastUsername()));
            }
        }
        // check if user id and username match
        if (!userToUpdate.getUsername().equals(changeUsernameRequest.getLastUsername())) {
            throw new CustomException("Username not match", 400, Collections.singletonMap("username", changeUsernameRequest.getLastUsername()));
        }
        // check if user new username is not in use
        if (userRepository.findByUsername(changeUsernameRequest.getNewUsername()).isPresent()) {
            throw new CustomException("Username already in use", 400, Collections.singletonMap("username", changeUsernameRequest.getNewUsername()));
        }
        // set username
        userToUpdate.setUsername(changeUsernameRequest.getNewUsername());
        // save user
        userRepository.save(userToUpdate);
        return UserInfoResponse.builder()
                .Id(userToUpdate.getId())
                .username(userToUpdate.getUsername())
                .email(userToUpdate.getEmail())
                .isEmailVerified(userToUpdate.getIsEmailVerified())
                .roles(
                        userToUpdate.getRoles().stream().map(role -> RoleResponse.builder()
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
                .orElseThrow(() -> new CustomException("User not found", 404, new HashMap<>(Map.of("user", userId, "role", roleId))));
        // find by role id
        Role role = roleService.getRoleObjectById(roleId);
        // check if user already have the role
        List<String> rolesIds = user.getRoles().stream().map(Role::getId).toList();
        if (rolesIds.contains(roleId)) {
            throw new CustomException("User already have the role", 400, new HashMap<>(Map.of("user", userId, "role", roleId)));
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

    @Override
    public UserInfoResponse getUserInfoResponseById(String userId) {
        // find by user id
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(
                                "User not found",
                                404,
                                Map.of(
                                        "user", userId
                                )
                        )
                );
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
    public UserInfoResponse removeRoleFromUser(String userId, String roleId) {
        // find by user id
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new CustomException(
                                "User not found",
                                404,
                                Map.of(
                                        "user", userId,
                                        "role", roleId
                                )
                        )
                );
        // find by role id
        Role role = roleService.getRoleObjectById(roleId);
        // check if user deleting user ROLE_USER
        if (role.getName().equals("ROLE_USER")) {
            throw new CustomException(
                    "Cannot delete ROLE_USER",
                    400,
                    Map.of(
                            "user", userId,
                            "role", roleId
                    )
            );
        }
        // check if user have the role
        List<String> rolesIds = user.getRoles().stream().map(Role::getId).toList();
        if (!rolesIds.contains(roleId)) {
            throw new CustomException(
                    "User does not have the role",
                    400,
                    Map.of(
                            "user", userId,
                            "role", roleId
                    )
            );
        }
        // reset roles without removed
        user.setRoles(user.getRoles().stream().filter(role1 -> !role1.getId().equals(roleId)).collect(Collectors.toSet()));
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

    @Override
    public List<UserInfoResponse> getAllUsers() {
        // find all users
        List<User> users = userRepository.findAll();
        return users.stream().map(
                userDto -> UserInfoResponse.builder()
                        .Id(userDto.getId())
                        .username(userDto.getUsername())
                        .email(userDto.getEmail())
                        .isEmailVerified(userDto.getIsEmailVerified())
                        .roles(
                                userDto.getRoles().stream().map(role -> RoleResponse.builder()
                                        .id(role.getId())
                                        .role(role.getName())
                                        .build()
                                ).toList()
                        )
                        .build()
        ).toList();
    }
}