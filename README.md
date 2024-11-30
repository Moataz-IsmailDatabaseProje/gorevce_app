
# Gorevce App 

* A project aimed at managing short-term jobs, connecting freelancers with employers, and providing a collaborative environment for mutual understanding between them.

* In this repository, the backend for the Gorevce project has been developed. Here's how you can use it:

## Table Of Contents
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Main User Credential](#Main-User-Credential)
- [Project Structure](#project-structure)
- [Endpoints](#endpoints)
- [Request Bodies](#request-bodies)
- [Author](#author)

## Endpoints 

### Auth Endpoints

* http://localhost:8090/auth/**

| Method  | Endpoint with Param/Path Variable            | Description                                    | Request Body (Linked to Section)                              | Response Body (Linked to Section)                                |
|---------|----------------------------------------------|------------------------------------------------|--------------------------------------------------------------|------------------------------------------------------------------|
| POST    | `/auth/register`                             | Register a new user                           | [SignupRequest](#signuprequest)                               | [RegisterResponse](#RegisterResponse)                            |
| POST    | `/auth/login`                                | Log in an existing user                       | [AuthRequest](#authrequest)                                   | [LoginResponse](#LoginResponse)                                  |
| POST    | `/auth/reset-password?token={token}`         | Change the password for a user                | [PasswordRequest](#passwordrequest)                           | [ResetPasswordResponse](#ResetPasswordResponse)                  |
| POST    | `/auth/forgot-password?email={email}`        | Send a password reset link to the email       | None                                                         | [ForgotPasswordResponse](#ForgotPasswordResponse)                |
| GET     | `/auth/verify-email?token={token}`           | Verify user email address                     | None                                                         | [VerifyEmailResponse](#VerifyEmailResponse)                      |
| POST    | `/auth/change-email`                         | Change the email address for a user           | [ChangeEmailRequest](#changeemailrequest)                     | [ChangeEmailResponse](#ChangeEmailResponse)                      |
| POST    | `/auth/change-username`                      | Change the username for a user                | [ChangeUsernameRequest](#changeusernamerequest)               | [ChangeUsernameResponse](#ChangeUsernameResponse)                |
| POST    | `/auth/create-password?email={email}`        | Create a new password for the user            | [PasswordRequest](#passwordrequest)                           | [CreatePasswordResponse](#CreatePasswordResponse)                |


#### Request Body Definitions:

##### AuthRequest
```json
{
    "username": "string",
    "email": "string",
    "password": "string"
}
```
##### SignupRequest

```json
{
    "username": "string",
    "email": "string"
}
```

#####  ChangeEmailRequest
```json
{
    "id": "string",
    "username": "string",
    "lastEmail": "string",
    "newEmail": "string"
}
```

##### ChangeUsernameRequest

```json 
{
    "id": "string",
    "lastUsername": "string",
    "newUsername": "string"
}
```

##### PasswordRequest
```json
{
    "password": "string"
}
```

#### Response Body Definitions:

##### RegisterResponse

* Registration successided

```json
{
    "message": "User registered successfully",
    "httpStatusCode": 200,
    "response": {
        "username": "ismail",
        "email": "smlkttn836@gmail.com",
        "isEmailVerified": null,
        "roles": [],
        "id": "6745f63520f15533cebff976"
    }
}
```

* Registration failed

```json
{
    "message": "Email already in use",
    "httpStatusCode": 400,
    "response": {
        "email": "smlkttn836@gmail.com"
    }
}
```

##### LoginResponse

* Logging in successided

```json
{
    "message": "logged in successfully",
    "httpStatusCode": 200,
    "response": {
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpc21haWwiLCJpYXQiOjE3MzI2NDI0MzIsImV4cCI6MTczMjcyODgzMn0.8t9fzJRMdtyhjGtRL7UfHe5ejpVHiWtwhqjcNexT3OciORYh5qWqoQA5V-PmMlsxlmpCF7NXyrZAzDUraMPerw",
        "username": "ismail",
        "email": "smlkttn836@gmail.com",
        "roles": [],
        "id": "6745fdfbef979c3c48e342cf"
    }
}
```

* Logging in failed

```json
{
    "message": "Invalid username or password",
    "httpStatusCode": 401,
    "response": {
        "username": "ismail"
    }
}
```

##### VerifyEmailResponse

* Email verification successided

```json
{
    "message": "Email verified successfully",
    "httpStatusCode": 200,
    "response": {
        "username": "ismail",
        "email": "smlkttn836@gmail.com",
        "isEmailVerified": true,
        "roles": [],
        "id": "6745f98f9401000d2ba16941"
    }
}
```
* Email verification failed

```json 
{
    "message": "Invalid token",
    "httpStatusCode": 400,
    "response": {
        "token": "ca5cf37e-e1a7-4b74-84b3-3731f31689f0"
    }
}
```

##### CreatePasswordResponse

* Creation successided

```json
{
    "message": "Password created successfully",
    "httpStatusCode": 200,
    "response": {
        "username": "ismail",
        "email": "smlkttn836@gmail.com",
        "isEmailVerified": true,
        "roles": [],
        "id": "6745fdfbef979c3c48e342cf"
    }
}
```
* Creation failed

```json
{
    "message": "Password already created",
    "httpStatusCode": 400,
    "response": {
        "email": "smlkttn836@gmail.com"
    }
}
```

##### ForgotPasswordResponse

* Sending email successided

```json
{
    "message": "Password reset link sent to email",
    "httpStatusCode": 200,
    "response": {
        "username": "ismail",
        "email": "smlkttn836@gmail.com",
        "isEmailVerified": true,
        "roles": [],
        "id": "6745fdfbef979c3c48e342cf"
    }
}
```

* Sending email failed
```json
{
    "message": "User not found",
    "httpStatusCode": 404,
    "response": {
        "email": "smlkttn836@gmail.co"
    }
}
```

##### ResetPasswordResponse

* Resetting password successided
```json
{
    "message": "Password changed successfully",
    "httpStatusCode": 200,
    "response": {
        "username": "ismail",
        "email": "smlkttn836@gmail.com",
        "isEmailVerified": true,
        "roles": [],
        "id": "6745fdfbef979c3c48e342cf"
    }
}
```

* Resetting password failed

```json
{
    "message": "Invalid token",
    "httpStatusCode": 400,
    "response": {
        "token": "3cda365b-9071-49be-9ce2-a3d182ffcf50"
    }
}
```

