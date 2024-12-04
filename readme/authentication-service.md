
# Gorevce App

* A project aimed at managing short-term jobs, connecting freelancers with employers, and providing a collaborative environment for mutual understanding between them.

* In this repository, the backend for the Gorevce project has been developed. Here's how you can use it:

## Table Of Contents
- [Features](#features)
- [Endpoints](#endpoints)
    - [Auth Endpoints](#auth-endpoints)
      - [Auth Request Bodies](#auth-requests)
      - [Auth Response Bodies](#auth-responses)
    - [Change Credential Endpoints](#change-credential-endpoints)
        - [Change Credential Request Bodies](#change-credential-request)
        - [Change Credential Response Bodies](#change-credential-response)
    - [Role Endpoints](#role-endpoints)
        - [Role Response Bodies](#role-response)
    - [Permission Endpoints](#permission-endpoints)
        - [Permission Request Bodies](#permission-request)
        - [Permission Response Bodies](#permission-response)
- [Author](#author)

## Features

1. **User Management**
   - User authentication, email verification, username and email changes, password reset.

2. **Role and Permission Management**
   - Creation, update, deletion, and retrieval of roles and permissions.
   - Assignment of roles to users and permissions to roles.

3. **Token Management**
   - Token generation, validation, expiration, refresh, and blacklist/whitelist functionality.

4. **Email Services**
   - Sending verification and notification emails.

5. **Security Enhancements**
   - Password hashing and expiration checks for secure access.

## Endpoints

### Auth Endpoints

* http://localhost:8090/authentication/auth/**

| Method  | Endpoint with Param/Path Variable            | Description                                    | Request Body (Linked to Section)     | Response Body (Linked to Section)                   |
|---------|----------------------------------------------|------------------------------------------------|--------------------------------------|-----------------------------------------------------|
| POST    | `/authentication/auth/register`                   | Register a new user                           | [SignupRequest](#signup-request)     | [RegisterResponse](#Register-Response)              |
| POST    | `/authentication/auth/login`                      | Log in an existing user                       | [AuthRequest](#auth-request)         | [LoginResponse](#Login-Response)                    |
| POST    | `/authentication/auth/reset-password?token={token}` | Change the password for a user                | [PasswordRequest](#password-request) | [ResetPasswordResponse](#Reset-Password-Response)   |
| POST    | `/authentication/auth/forgot-password?email={email}` | Send a password reset link to the email       | None                                 | [ForgotPasswordResponse](#Forgot-Password-Response) |
| GET     | `/authentication/auth/verify-email?token={token}` | Verify user email address                     | None                                 | [VerifyEmailResponse](#Verify-Email-Response)       |
| POST    | `/authentication/auth/create-password?email={email}` | Create a new password for the user            | [PasswordRequest](#password-request) | [CreatePasswordResponse](#Create-Password-Response) |

### Change Credential Endpoints

* http://localhost:8090/authentication/change-credential/**

| Method  | Endpoint with Param/Path Variable            | Description                                    | Request Body (Linked to Section)                  | Response Body (Linked to Section)                   |
|---------|----------------------------------------------|------------------------------------------------|---------------------------------------------------|-----------------------------------------------------|
| POST    | `/authentication/change-credential/change-email`                    | Change the email address for a user           | [ChangeEmailRequest](#change-email-request)       | [ChangeEmailResponse](#Change-Email-Response)       |
| POST    | `/authentication/change-credential/change-username`                 | Change the username for a user                | [ChangeUsernameRequest](#change-username-request) | [ChangeUsernameResponse](#Change-Username-Response) |

### Role Endpoints

* http://localhost:8090/authentication/role/**

| Method  | Endpoint with Param/Path Variable            | Description                                    | Request Body (Linked to Section)              | Response Body (Linked to Section)           |
|---------|----------------------------------------------|------------------------------------------------|-----------------------------------------------|---------------------------------------------|
| GET     | `/get-roles`                                 | Retrieve all roles                            | None                                          | [RolesResponse](#roles-response)            |
| POST    | `/create-role`                               | Create a new role                             | None (uses `role` query param)                | [CreateRoleResponse](#create-role-response) |
| PUT     | `/update-role`                               | Update an existing role                       | None (uses `roleId` and `role` query param)   | [UpdateRoleResponse](#update-role-response) |
| DELETE  | `/delete-role`                               | Delete a role                                 | None (uses `roleId` query param)              | [DeleteRoleResponse](#delete-role-response) |
| GET     | `/get-role`                                  | Retrieve a role by ID                         | None (uses `roleId` query param)              | [RoleResponse](#role-response)              |
| POST    | `/set-role`                                  | Assign a role to a user                       | None (uses `userId` and `roleId` query param) | [SetRoleResponse](#set-role-response)       |

### Permission Endpoints

* http://localhost:8090/authentication/permissions/**

| Method  | Endpoint with Param/Path Variable            | Description                                    | Request Body (Linked to Section)         | Response Body (Linked to Section)                       |
|---------|----------------------------------------------|------------------------------------------------|------------------------------------------|---------------------------------------------------------|
| GET     | `/get-permissions`                           | Retrieve all permissions                      | None                                     | [PermissionsResponse](#permissions-response)            |
| POST    | `/create-permission`                         | Create a new permission                       | [PermissionRequest](#permission-request) | [CreatePermissionResponse](#create-permission-response) |
| DELETE  | `/delete-permission`                         | Delete a permission                           | None (uses `permissionId` query param)   | [DeletePermissionResponse](#delete-permission-response) |
| PUT     | `/update-permission`                         | Update an existing permission                 | [PermissionRequest](#permission-request) | [UpdatePermissionResponse](#update-permission-response) |
| GET     | `/get-permission`                            | Retrieve a permission by ID                   | None (uses `permissionId` query param)   | [PermissionResponse](#permission-response)              |

--- 

#### Auth Requests

* ##### Auth Request
```json
{
    "username": "string",
    "email": "string",
    "password": "string"
}
```
* ##### Signup Request

```json
{
    "username": "string",
    "email": "string"
}
```

* ##### Password Request
```json
{
    "password": "string"
}
```



* ##### Password  Request
```json
{
    "password": "string"
}
```
---

#### Auth Responses

* ##### Register Response

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

* ##### Login Response

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

* ##### Verify Email Response

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

* ##### Create Password Response

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

* ##### Forgot Password Response

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

* ##### Reset Password Response

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

---

#### Change Credential Request


* #####  Change Email Request
```json
{
    "id": "string",
    "username": "string",
    "lastEmail": "string",
    "newEmail": "string"
}
```

* ##### Change Username Request

```json 
{
    "id": "string",
    "lastUsername": "string",
    "newUsername": "string"
}
```

--- 

#### Change Credential Response

* ##### Change Email Response

  * Changing email successided

```json
{
  "message": "Email changed successfully",
  "httpStatusCode": 200,
  "response": {
    "username": "ismail",
    "email": "ismail@gmail.com",
    "isEmailVerified": true,
    "roles": [
      {
        "id": "674cde6eff58f26cbd119921",
        "role": "ROLE_USER"
      }
    ],
    "id": "674cdf3fff58f26cbd119923"
  }
}
```

* Changing email failed

```json
{
    "message": "User not found",
    "httpStatusCode": 404,
    "response": {
        "email": "ismail@gmail.co"
    }
}
```

* ##### Change Username Response

    * Changing username successided

```json
{
    "message": "Username changed successfully, please login again with your new username.",
    "httpStatusCode": 200,
    "response": {
        "username": "ismail3",
        "email": "isma@gmail.com",
        "isEmailVerified": true,
        "roles": [
            {
                "id": "674cde6eff58f26cbd119921",
                "role": "ROLE_USER"
            }
        ],
        "id": "674cdf3fff58f26cbd119923"
    }
}
```

* Changing username failed

```json
{
    "message": "User not found",
    "httpStatusCode": 404,
    "response": {
        "username": "ismail3"
    }
}
```

---

#### Role Response

* ##### Roles Response

  * Retrieving roles successided

```json
{
    "message": "Roles retrieved successfully",
    "httpStatusCode": 200,
    "response": [
        {
            "id": "674cde6eff58f26cbd119921",
            "role": "ROLE_USER"
        },
        {
            "id": "674cde6eff58f26cbd119922",
            "role": "ROLE_ADMIN"
        }
    ]
}
```

* ##### Create Role Response

  * Creating role successided

```json
{
    "message": "Role created successfully",
    "httpStatusCode": 200,
    "response": {
        "id": "674cde6eff58f26cbd119921",
        "role": "ROLE_USER"
    }
}
```

* Creating role failed

```json
{
    "message": "Role already exists",
    "httpStatusCode": 400,
    "response": {
        "role": "ROLE_USER"
    }
}
```

* ##### Update Role Response

  * Updating role successided

```json
{
    "message": "Role updated successfully",
    "httpStatusCode": 200,
    "response": {
        "id": "674cde6eff58f26cbd119921",
        "role": "ROLE_USER"
    }
}
```

* Updating role failed

```json
{
    "message": "Role not found",
    "httpStatusCode": 404,
    "response": {
        "roleId": "674cde6eff58f26cbd119921"
    }
}
```

* ##### Delete Role Response

  * Deleting role successided

```json
{
    "message": "Role deleted successfully",
    "httpStatusCode": 200,
    "response": {
        "roleId": "674cde6eff58f26cbd119921",
        "role": "ROLE_USER"
    }
}
```

* Deleting role failed

```json
{
    "message": "Role not found",
    "httpStatusCode": 404,
    "response": {
        "roleId": "674cde6eff58f26cbd119921"
    }
}
```

* ##### Role Response

  * Retrieving role successided

```json
{
    "message": "Role retrieved successfully",
    "httpStatusCode": 200,
    "response": {
        "id": "674cde6eff58f26cbd119921",
        "role": "ROLE_USER"
    }
}
```

* Retrieving role failed

```json
{
    "message": "Role not found",
    "httpStatusCode": 404,
    "response": {
        "roleId": "674cde6eff58f26cbd119921"
    }
}
```

* ##### Set Role Response

  * Setting role successided

```json
{
    "message": "Role set successfully",
    "httpStatusCode": 200,
    "response": {
        "userId": "674cde6eff58f26cbd119921",
        "roleId": "674cde6eff58f26cbd119921"
    }
}
```

* Setting role failed

```json
{
    "message": "Role not found",
    "httpStatusCode": 404,
    "response": {
        "userId": "674cde6eff58f26cbd119921",
        "roleId": "674cde6eff58f26cbd119921"
    }
}
```

---

#### Permission Requests

* ##### Permission Request

```json
{
    "endpoint": "string",
    "method": "string",
    "description": "string",
    "roles": [
        {
            "id": "string",
            "role": "string"
        },
        {
            "id": "string",
            "role": "string"
        },
      ". . ."
    ]
}
```

---

### Permission Responses

* ##### Permissions Response

  * Retrieving permissions successided

```json
{
    "message": "Permissions retrieved successfully",
    "httpStatusCode": 200,
    "response": [
        {
            "id": "674cde6eff58f26cbd119921",
            "endpoint": "/authentication/auth/register",
            "method": "POST",
            "description": "Register a new user",
            "roles": [
                {
                    "id": "674cde6eff58f26cbd119921",
                    "role": "ROLE_USER"
                }
            ]
        },
        {
            "id": "674cde6eff58f26cbd119922",
            "endpoint": "/authentication/auth/login",
            "method": "POST",
            "description": "Log in an existing user",
            "roles": [
                {
                    "id": "674cde6eff58f26cbd119921",
                    "role": "ROLE_USER"
                }
            ]
        }
    ]
}
```

* ##### Create Permission Response

  * Creating permission successided

```json
{
    "message": "Permission created successfully",
    "httpStatusCode": 200,
    "response": {
        "id": "674cde6eff58f26cbd119921",
        "endpoint": "/authentication/auth/register",
        "method": "POST",
        "description": "Register a new user",
        "roles": [
            {
                "id": "674cde6eff58f26cbd119921",
                "role": "ROLE_USER"
            }
        ]
    }
}
```

* Creating permission failed

```json
{
    "message": "Permission already exists",
    "httpStatusCode": 400,
    "response": {
        "endpoint": "/authentication/auth/register",
        "method": "POST"
    }
}
```

* ##### Update Permission Response

  * Updating permission successided

```json
{
    "message": "Permission updated successfully",
    "httpStatusCode": 200,
    "response": {
        "id": "674cde6eff58f26cbd119921",
        "endpoint": "/authentication/auth/register",
        "method": "POST",
        "description": "Register a new user",
        "roles": [
            {
                "id": "674cde6eff58f26cbd119921",
                "role": "ROLE_USER"
            }
        ]
    }
}
```

* Updating permission failed

```json
{
    "message": "Permission not found",
    "httpStatusCode": 404,
    "response": {
        "permissionId": "674cde6eff58f26cbd119921"
    }
}
```

* ##### Delete Permission Response

  * Deleting permission successided

```json
{
    "message": "Permission deleted successfully",
    "httpStatusCode": 200,
    "response": {
        "permissionId": "674cde6eff58f26cbd119921",
        "endpoint": "/authentication/auth/register",
        "method": "POST",
        "description": "Register a new user",
        "roles": [
            {
                "id": "674cde6eff58f26cbd119921",
                "role": "ROLE_USER"
            }
        ]
    }
}
```

* Deleting permission failed

```json
{
    "message": "Permission not found",
    "httpStatusCode": 404,
    "response": {
        "permissionId": "674cde6eff58f26cbd119921"
    }
}
```

* ##### Permission Response

  * Retrieving permission successided

```json
{
    "message": "Permission retrieved successfully",
    "httpStatusCode": 200,
    "response": {
        "id": "674cde6eff58f26cbd119921",
        "endpoint": "/authentication/auth/register",
        "method": "POST",
        "description": "Register a new user",
        "roles": [
            {
                "id": "674cde6eff58f26cbd119921",
                "role": "ROLE_USER"
            }
        ]
    }
}
```

* Retrieving permission failed

```json
{
    "message": "Permission not found",
    "httpStatusCode": 404,
    "response": {
        "permissionId": "674cde6eff58f26cbd119921"
    }
}
```

---

## Author

👤 **Ismail Kattan**

- Github: [IsmailKattan](https://github.com/IsmailKattan)
- LinkedIn: [ismail kattan](https://www.linkedin.com/in/ismail-kattan-538b87219/)
- Email: [ismail kattan](mailto:ismailkattan.contact@gmail.com)
- Phone: [+905070457070](tel:+905070457070)

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!
