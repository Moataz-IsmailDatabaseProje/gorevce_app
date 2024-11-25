
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
| POST    | `/auth/register`                             | Register a new user                           | [SignupRequest](#signuprequest)                               | [registerResponse](#registerresponse)                            |
| POST    | `/auth/login`                                | Log in an existing user                       | [AuthRequest](#authrequest)                                   | [loginResponse](#loginresponse)                                  |
| POST    | `/auth/reset-password?token={token}`         | Change the password for a user                | [PasswordRequest](#passwordrequest)                           | [resetPasswordResponse](#resetpasswordresponse)                  |
| POST    | `/auth/forgot-password?email={email}`        | Send a password reset link to the email       | None                                                         | [forgotPasswordResponse](#forgotpasswordresponse)                |
| GET     | `/auth/verify-email?token={token}`           | Verify user email address                     | None                                                         | [verifyEmailResponse](#verifyemailresponse)                      |
| POST    | `/auth/change-email`                         | Change the email address for a user           | [ChangeEmailRequest](#changeemailrequest)                     | [changeEmailResponse](#changeemailresponse)                      |
| POST    | `/auth/change-username`                      | Change the username for a user                | [ChangeUsernameRequest](#changeusernamerequest)               | [changeUsernameResponse](#changeusernameresponse)                |
| POST    | `/auth/create-password?email={email}`        | Create a new password for the user            | [PasswordRequest](#passwordrequest)                           | [createPasswordResponse](#createpasswordresponse)                |


#### Request Body Definitions:

##### AuthRequest
```json
{
    "username": "string",
    "email": "string",
    "password": "string"
}
```
#### SignupRequest

```json
{
    "username": "string",
    "email": "string"
}
```

####  ChangeEmailRequest
```json
{
    "id": "string",
    "username": "string",
    "lastEmail": "string",
    "newEmail": "string"
}
```

#### ChangeUsernameRequest

```json 
{
    "id": "string",
    "lastUsername": "string",
    "newUsername": "string"
}
```

#### PasswordRequest
```json
{
    "password": "string"
}
```





