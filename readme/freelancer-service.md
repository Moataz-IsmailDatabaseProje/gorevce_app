
# Freelancer Service

* This service is used to manage the freelancer details.
* Freelancer details can be added, updated, deleted and fetched using this service.
* This service is used to manage the freelancer details.

## Table of Contents
- [Freelancer Service](#freelancer-service)
  - [Table of Contents](#table-of-contents)
  - [Features](#features)
  - [Usage](#usage)
    - [Endpoints](#endpoints)
        - [Freelancer Endpoints](#freelancer-endpoints)
        - [Certificate Endpoints](#certificate-endpoints)
        - [Education Endpoints](#education-endpoints)
        - [Project Endpoints](#project-endpoints)
        - [Review Endpoints](#review-endpoints)
        - [Social Media Endpoints](#social-media-endpoints)
        - [Work Experience Endpoints](#work-experience-endpoints)
    - [Request Bodies](#request-bodies)
        - [Freelancer Request](#freelancer-request)
        - [Certificate Request](#certificate-request)
        - [Education Request](#education-request)
        - [Project Request](#project-request)
        - [Review Request](#review-request)
        - [Social Media Request](#social-media-request)
        - [Work Experience Request](#work-experience-request)
    - [Response Bodies](#response-bodies)
        - [Freelancer Response](#freelancer-response)
        - [Certificate Response](#certificate-response)
        - [Education Response](#education-response)
        - [Project Response](#project-response)
        - [Review Response](#review-response)
        - [Social Media Response](#social-media-response)
        - [Work Experience Response](#work-experience-response)
  - [Author](#author)

## Features

* Create a new freelancer
* Retrieve all freelancers 
* Retrieve a freelancer by ID
* Update an existing freelancer
* Delete a freelancer by ID 
* Restore of a freelancer
* Retrieve all certificates of a freelancer
* Retrieve all educations of a freelancer
* Retrieve all projects of a freelancer
* Retrieve all reviews of a freelancer
* Retrieve all social media accounts of a freelancer
* Retrieve all work experiences of a freelancer
* Retrieve freelancer address
* Retrieve freelancer user info
---
* Create a new certificate
* Retrieve all certificates of a freelancer
* Retrieve a certificate by ID
* Update an existing certificate
* Delete a certificate by ID
* Retrieve all certificates
* Retrieve certificate details by certificate ID
---
* Create a new education
* Retrieve all educations of a freelancer
* Retrieve an education by ID
* Update an existing education
* Delete an education by ID
* Retrieve all educations
* Retrieve education details by education ID
---
* Create a new project
* Retrieve all projects of a freelancer
* Retrieve a project by ID
* Update an existing project
* Delete a project by ID
* Retrieve all projects
* Retrieve project details by project ID
---
* Create a new review
* Retrieve all reviews of a freelancer
* Retrieve a review by ID
* Update an existing review
* Delete a review by ID
* Retrieve all reviews
* Retrieve review details by review ID
---
* Create a new social media contact
* Retrieve all social media contacts of a freelancer
* Retrieve a social media contact by ID
* Update an existing social media contact
* Delete a social media contact by ID
* Retrieve all social media contacts
* Retrieve social media contact details by social media contact ID
---
* Create a new work experience
* Retrieve all work experiences of a freelancer
* Retrieve a work experience by ID
* Update an existing work experience
* Delete a work experience by ID
* Retrieve all work experiences
* Retrieve work experience details by work experience ID

## Usage

### Endpoints

#### Freelancer Endpoints

* http://localhost:8090/freelancer/**

| Method | Endpoint with Param/Path Variable | Description                      | Request Body (Linked to Section)         | Response Body (Linked to Section)                                 |
|--------|-----------------------------------|----------------------------------|------------------------------------------|-------------------------------------------------------------------|
| POST   | `/create-freelancer`              | Create a new freelancer          | [FreelancerRequest](#freelancer-request) | [FreelancerResponse](#freelancer-response)                        |
| GET    | `/get-freelancer`                 | Retrieve freelancer by id        | user id as request parameter             | [FreelancerResponse](#freelancer-response)                        |
| PUT    | `/update-freelancer`              | Update an existing freelancer    | [FreelancerRequest](#freelancer-request) | [FreelancerResponse](#freelancer-response)                        |
| DELETE | `/delete-freelancer`              | Delete a freelancer by id        | user id as request parameter             | -                                                                 |
| PUT    | `/restore-freelancer`             | Restore a freelancer by id       | user id as request parameter             | -                                                                 |
| GET    | `/get-all-freelancers`            | Retrieve all freelancers         | -                                        | [FreelancersResponse](#freelancers-response)                      |
| GET    | `/get-freelancer-details`         | Retrieve freelancer details      | user id as request parameter             | [FreelancerDetailsResponse](#freelancer-details-response)         |
| GET    | `/get-credentials`                | Retrieve freelancer credentials  | user id as request parameter             | [FreelancerCredentialsResponse](#freelancer-credentials-response) | 
| GET    | `/get-address`                    | Retrieve freelancer address      | user id as request parameter             | [FreelancerAddressResponse](#freelancer-address-response)         |
| GET    | `/get-work-experience`            | Retrieve freelancer work exp     | user id as request parameter             | [WorkExperienceResponse](#work-experience-response)               |
| GET    | `/get-education`                  | Retrieve freelancer education    | user id as request parameter             | [EducationResponse](#education-response)                          |
| GET    | `/get-projects`                   | Retrieve freelancer projects     | user id as request parameter             | [ProjectResponse](#project-response)                              |
| GET    | `/get-reviews`                    | Retrieve freelancer reviews      | user id as request parameter             | [ReviewResponse](#review-response)                                |
| GET    | `/get-social-media`               | Retrieve freelancer social media | user id as request parameter             | [SocialMediaResponse](#social-media-response)                     |
| GET    | `/get-skills`                     | Retrieve freelancer skills       | user id as request parameter             | [FreelancerSkillsResponse](#freelancer-skills-response)           |
| GET    | `/get-certificates`               | Retrieve freelancer certificates | user id as request parameter             | [CertificateResponse](#certificate-response)                      |
--- 

#### Certificate Endpoints

* http://localhost:8090/freelancer/certificates/**

| Method | Endpoint with Param/Path Variable | Description                                         | Request Body (Linked to Section)           | Response Body (Linked to Section)                            |
|--------|---------------------------------|-----------------------------------------------------|--------------------------------------------|--------------------------------------------------------------|
| POST   | `/create-certificate`           | Create a new certificate                            | [CertificateRequest](#certificate-request) | [CertificateResponse](#certificate-response)                 |
| GET    | `/get-certificate?id=`          | Retrieve certificate by id                          | certificate id as request parameter        | [CertificateResponse](#certificate-response)                 |
| GET    | `/get-certificates`             | Retrieve freelancer certificates by freelancer id   | user id as request parameter               | [CertificatesResponse](#certificates-response)               |
| PUT    | `/update-certificate`           | Update an existing certificate                      | [CertificateRequest](#certificate-request) | [CertificateResponse](#certificate-response)                 |
| DELETE | `/delete-certificate`           | Delete a certificate by id                          | certificate id as request parameter        | -                                                            |
| GET    | `/get-all-certificates`         | Retrieve all certificates                           | -                                          | [CertificatesResponse](#certificates-response)               |
| GET    | `/get-certificate-details`      | Retrieve certificate details                        | certificate id as request parameter        | [CertificateDetailsResponse](#certificate-details-response)  |
--- 
#### Education Endpoints

* http://localhost:8090/freelancer/educations/**

| Method | Endpoint with Param/Path Variable | Description                                      | Request Body (Linked to Section)           | Response Body (Linked to Section)                            |
|--------|-----------------------------------|--------------------------------------------------|--------------------------------------------|--------------------------------------------------------------|
| POST   | `/create-education`               | Create a new education                           | [EducationRequest](#education-request)     | [EducationResponse](#education-response)                     |
| GET    | `/get-education`                  | Retrieve education by id                         | education id as request parameter          | [EducationResponse](#education-response)                     |
| GET    | `/get-educations`                 | Retrieve freelancer educations by freelancer id  | user id as request parameter               | [EducationsResponse](#educations-response)                   |
| PUT    | `/update-education`               | Update an existing education                     | [EducationRequest](#education-request)     | [EducationResponse](#education-response)                     |
| DELETE | `/delete-education`               | Delete an education by id                        | education id as request parameter          | -                                                            |
| GET    | `/get-all-educations`             | Retrieve all educations                          | -                                          | [EducationsResponse](#educations-response)                   |
| GET    | `/get-education-details`          | Retrieve education details                       | education id as request parameter          | [EducationDetailsResponse](#education-details-response)      |
--- 
#### Project Endpoints

* http://localhost:8090/freelancer/projects/**

| Method | Endpoint with Param/Path Variable | Description                                    | Request Body (Linked to Section)             | Response Body (Linked to Section)                            |
|--------|-----------------------------------|------------------------------------------------|----------------------------------------------|--------------------------------------------------------------|
| POST   | `/create-project`                 | Create a new project                           | [ProjectRequest](#project-request)           | [ProjectResponse](#project-response)                         |
| GET    | `/get-project`                    | Retrieve project by id                         | project id as request parameter              | [ProjectResponse](#project-response)                         |
| GET    | `/get-projects`                   | Retrieve freelancer projects by freelancer id  | user id as request parameter                 | [ProjectsResponse](#projects-response)                       |
| PUT    | `/update-project`                 | Update an existing project                     | [ProjectRequest](#project-request)           | [ProjectResponse](#project-response)                         |
| DELETE | `/delete-project`                 | Delete a project by id                         | project id as request parameter              | -                                                            |
| GET    | `/get-all-projects`               | Retrieve all projects                          | -                                            | [ProjectsResponse](#projects-response)                       |
| GET    | `/get-project-details`            | Retrieve project details                       | project id as request parameter              | [ProjectDetailsResponse](#project-details-response)          |
---
#### Review Endpoints

* http://localhost:8090/freelancer/reviews/**

| Method | Endpoint with Param/Path Variable | Description                                    | Request Body (Linked to Section)          | Response Body (Linked to Section)                            |
|--------|-----------------------------------|------------------------------------------------|-------------------------------------------|--------------------------------------------------------------|
| POST   | `/create-review`                  | Create a new review                            | [ReviewRequest](#review-request)          | [ReviewResponse](#review-response)                           |
| GET    | `/get-review`                     | Retrieve review by id                          | review id as request parameter            | [ReviewResponse](#review-response)                           |
| GET    | `/get-reviews`                    | Retrieve freelancer reviews by freelancer id   | user id as request parameter              | [ReviewsResponse](#reviews-response)                         |
| PUT    | `/update-review`                  | Update an existing review                      | [ReviewRequest](#review-request)          | [ReviewResponse](#review-response)                           |
| DELETE | `/delete-review`                  | Delete a review by id                          | review id as request parameter            | -                                                            |
| GET    | `/get-all-reviews`                | Retrieve all reviews                           | -                                         | [ReviewsResponse](#reviews-response)                         |
---
#### Social Media Endpoints

* http://localhost:8090/freelancer/social-media/**

| Method | Endpoint with Param/Path Variable | Description                                            | Request Body (Linked to Section)              | Response Body (Linked to Section)                            |
|--------|-----------------------------------|--------------------------------------------------------|-----------------------------------------------|--------------------------------------------------------------|
| POST   | `/create-social-media`            | Create a new social media contact                      | [SocialMediaRequest](#social-media-request)   | [SocialMediaResponse](#social-media-response)                |
| GET    | `/get-social-media`               | Retrieve social media contact by id                    | social media id as request parameter          | [SocialMediaResponse](#social-media-response)                |
| GET    | `/get-social-media-by-freelancer` | Retrieve freelancer social media contacts by user id   | user id as request parameter                  | [SocialMediasResponse](#social-medias-response)              |
| PUT    | `/update-social-media`            | Update an existing social media contact                | [SocialMediaRequest](#social-media-request)   | [SocialMediaResponse](#social-media-response)                |
| DELETE | `/delete-social-media`            | Delete a social media contact by id                    | social media id as request parameter          | -                                                            |
| GET    | `/get-all-social-media`           | Retrieve all social media contacts                     | -                                             | [SocialMediasResponse](#social-medias-response)              |
---
#### Work Experience Endpoints

* http://localhost:8090/freelancer/work-experiences/**

| Method | Endpoint with Param/Path Variable | Description                                      | Request Body (Linked to Section)             | Response Body (Linked to Section)                            |
|--------|-----------------------------------|--------------------------------------------------|----------------------------------------------|--------------------------------------------------------------|
| POST   | `/create-work-experience`         | Create a new work experience                     | [WorkExperienceRequest](#work-experience-request) | [WorkExperienceResponse](#work-experience-response)         |
| GET    | `/get-work-experience`            | Retrieve work experience by id                   | work experience id as request parameter       | [WorkExperienceResponse](#work-experience-response)         |
| GET    | `/get-work-experiences`           | Retrieve freelancer work experiences by freelancer id | user id as request parameter            | [WorkExperiencesResponse](#work-experiences-response)       |
| PUT    | `/update-work-experience`         | Update an existing work experience                | [WorkExperienceRequest](#work-experience-request) | [WorkExperienceResponse](#work-experience-response)         |
| DELETE | `/delete-work-experience`         | Delete a work experience by id                    | work experience id as request parameter       | -                                                            |
| GET    | `/get-all-work-experiences`       | Retrieve all work experiences                     | -                                            | [WorkExperiencesResponse](#work-experiences-response)       |
| GET    | `/get-work-experience-details`    | Retrieve work experience details                  | work experience id as request parameter       | [WorkExperienceDetailsResponse](#work-experience-details-response) |
---


### Request Bodies

#### Freelancer Request
```json
{
  "name": "John",
  "surname": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "+123456789",
  "skills": {
    "Java": 8,
    "Spring": 7,
    "MongoDB": 6
  },
  "certificates": [
    {
      "name": "Java SE 11 Developer",
      "description": "Oracle Certified Java SE 11 Developer",
      "issuer": "Oracle",
      "issueDate": "2023-01-15T00:00:00.000Z",
      "expirationDate": "2026-01-15T00:00:00.000Z",
      "credentialId": "ABC123",
      "credentialUrl": "https://certificates.example.com/ABC123",
      "imageUrl": "https://images.example.com/java-certificate.png",
      "freelancerId": null
    }
  ],
  "projects": [
    {
      "name": "E-commerce Platform",
      "description": "An e-commerce platform for small businesses.",
      "imageUrl": "https://images.example.com/project-ecommerce.png",
      "projectUrl": "https://github.com/johndoe/ecommerce",
      "projectType": "Web Application",
      "projectStatus": "Completed",
      "projectStartDate": "2022-03-01T00:00:00.000Z",
      "projectEndDate": "2022-12-01T00:00:00.000Z",
      "freelancerId": null
    }
  ],
  "education": [
    {
      "school": "University of Example",
      "degree": "Bachelor of Science",
      "fieldOfStudy": "Computer Engineering",
      "startDate": "2018-09-01T00:00:00.000Z",
      "endDate": "2022-06-30T00:00:00.000Z",
      "isCurrentlyStudying": false,
      "grade": "3.8",
      "description": "Focused on software development and AI.",
      "imageUrl": "https://images.example.com/university-logo.png",
      "freelancerId": null
    }
  ],
  "workExperience": [
    {
      "title": "Software Engineer",
      "company": "Tech Corp",
      "city": "Istanbul",
      "description": "Developed scalable backend services.",
      "imageUrl": "https://images.example.com/techcorp-logo.png",
      "startDate": "2022-07-01T00:00:00.000Z",
      "endDate": "2024-01-01T00:00:00.000Z",
      "isCurrent": true,
      "freelancerId": null
    }
  ],
  "socialMedia": [
    {
      "platform": "LinkedIn",
      "url": "https://linkedin.com/in/johndoe",
      "imageUrl": "https://images.example.com/linkedin-icon.png",
      "freelancerId": null
    }
  ],
  "address": {
    "id": "addr-123",
    "country": "Turkey",
    "city": "Sakarya",
    "street": "123 Main St",
    "postalCode": "54000",
    "additionalInfo": "Near central park",
    "addressOfId": null
  },
  "userId": "674cde6eff58f26cbd119922"
}
```

---
#### Certificate Request

```json
{
    "name": "certificate1",
    "description": "certificate of certificate",
    "issuer": "certificate issuer",
    "issueDate": "2024-12-15",
    "expirationDate": "2025-12-14",
    "credentialId": "5413851",
    "credentialUrl": "http:/fmlkdfkss",
    "imageUrl": "c:gsf/fsf",
    "freelancerId": null
}
```
--- 
#### Education Request

```json
{
    "degree": "Bachelors",
    "fieldOfStudy": "Computer Science",
    "school": "University of Computer Science",
    "startDate": "2018-12-15",
    "endDate": "2022-12-14",
    "grade": "A",
    "description": "Bachelors in Computer Science",
    "isCurrentlyStudying" : true,
    "imageUrl": null,
    "freelancerId": null
}
```
---

#### Project Request
```json
{
    "name": "Website Redesign",
    "description": "A project to redesign the company's main website for better user experience.",
    "imageUrl": "https://example.com/images/project-redesign.jpg",
    "projectUrl": "https://example.com/projects/website-redesign",
    "projectType": "Web Development",
    "projectStatus": "In Progress",
    "projectStartDate": "2024-01-15",
    "projectEndDate": "2024-04-30",
    "freelancerId": null
}
```
---

#### Review Request
```json
{
    "reviewerId": "user456",
    "review": "Excellent work! The freelancer delivered the project on time with outstanding quality.",
    "rating": 5,
    "imageUrl": "https://example.com/images/review-image.jpg",
    "freelancerId": "freelancer123"
}
```
---

#### Social Media Request
```json
{
    "platform": "LinkedIn",
    "url": "https://www.linkedin.com/in/freelancer123",
    "imageUrl": "https://example.com/images/linkedin-profile.jpg",
    "freelancerId": "freelancer123"
}
```
---

#### Work Experience Request
```json
{
    "title": "Software Engineer",
    "company": "Tech Innovations Inc.",
    "city": "Istanbul",
    "description": "Developed and maintained scalable web applications, contributing to a 30% increase in system performance.",
    "imageUrl": "https://example.com/images/tech-innovations.jpg",
    "startDate": "2021-03-01",
    "endDate": "2023-11-30",
    "isCurrent": false,
    "freelancerId": "freelancer123"
}

```

---
### Response Bodies

#### Freelancer Response 
* Freelancer Response 
```json
{
    "message": "Freelancer created successfully",
    "httpStatusCode": 200,
    "response": {
        "id": "6766499dbb6c14153000ac84",
        "name": "John",
        "surname": "Doe",
        "email": "john.doe@example.com",
        "phoneNumber": "+123456789",
        "skills": {
            "Java": 8,
            "Spring": 7,
            "MongoDB": 6
        },
        "certificatesId": [
            "6766499dbb6c14153000ac85"
        ],
        "projectsId": [
            "6766499dbb6c14153000ac87"
        ],
        "reviewsId": null,
        "educationId": [
            "6766499dbb6c14153000ac88"
        ],
        "workExperienceId": [
            "6766499dbb6c14153000ac86"
        ],
        "socialMediaId": [
            "6766499ebb6c14153000ac89"
        ],
        "addressId": "6766499d6beeb472bdd3a832",
        "userId": "674cde6eff58f26cbd119922"
    }
}
```
* Freelancer Details Response
```json
{
    "message": "Freelancer details retrieved successfully",
    "httpStatusCode": 200,
    "response": {
        "id": "6766499dbb6c14153000ac84",
        "name": "John",
        "surname": "Doe",
        "email": "john.doe@example.com",
        "phoneNumber": "+123456789",
        "skills": {
            "Java": 8,
            "Spring": 7,
            "MongoDB": 6
        },
        "certificates": [
            {
                "id": "6766499dbb6c14153000ac85",
                "name": "Java SE 11 Developer",
                "description": "Oracle Certified Java SE 11 Developer",
                "issuer": "Oracle",
                "imageUrl": "https://images.example.com/java-certificate.png",
                "freelancerId": "6766499dbb6c14153000ac84"
            }
        ],
        "projects": [
            {
                "id": "6766499dbb6c14153000ac87",
                "name": "E-commerce Platform",
                "description": "An e-commerce platform for small businesses.",
                "imageUrl": "https://images.example.com/project-ecommerce.png",
                "projectUrl": "https://github.com/johndoe/ecommerce",
                "freelancerId": "6766499dbb6c14153000ac84"
            }
        ],
        "reviews": [],
        "education": [
            {
                "id": "6766499dbb6c14153000ac88",
                "school": "University of Example",
                "degree": "Bachelor of Science",
                "fieldOfStudy": "Computer Engineering",
                "startYear": "2018",
                "endYear": "2022",
                "isCurrentlyStudying": false,
                "freelancerId": "6766499dbb6c14153000ac84"
            }
        ],
        "workExperience": [
            {
                "id": "6766499dbb6c14153000ac86",
                "title": "Software Engineer",
                "company": "Tech Corp",
                "imageUrl": "https://images.example.com/techcorp-logo.png",
                "endDate": "2024-01-01T00:00:00.000+00:00",
                "freelancerId": "6766499dbb6c14153000ac84",
                "current": false
            }
        ],
        "socialMedia": [
            {
                "id": "6766499ebb6c14153000ac89",
                "platform": "LINKEDIN",
                "url": "https://linkedin.com/in/johndoe",
                "imageUrl": "https://images.example.com/linkedin-icon.png",
                "freelancerId": "6766499dbb6c14153000ac84"
            }
        ],
        "address": {
            "id": "6766499d6beeb472bdd3a832",
            "country": "Turkey",
            "city": "Sakarya",
            "street": "123 Main St",
            "postalCode": "54000",
            "additionalInfo": "Near central park",
            "addressOfId": "6766499dbb6c14153000ac84"
        },
        "user": {
            "id": "674cde6eff58f26cbd119922",
            "username": "admin",
            "email": "ismail@gmail.com",
            "roles": [
                {
                    "id": "674cde6eff58f26cbd119920",
                    "role": "ROLE_SUPER_ADMIN"
                }
            ]
        }
    }
}
```

---
#### Certificate Response 

* Certificate Response
```json 
{
    "message": "Certificate created successfully",
    "httpStatusCode": 200,
    "response": {
        "id": "67659395fbf76807a5d8e21a",
        "name": "certificate1",
        "description": "certificate of certificate",
        "issuer": "certificate issuer",
        "imageUrl": "c:gsf/fsf",
        "freelancerId": null
    }
}
```

* Certificate Details Response
```json
{
    "message": "Certificate details retrieved successfully",
    "httpStatusCode": 200,
    "response": {
        "id": "67659395fbf76807a5d8e21a",
        "name": "certificate1",
        "description": "certificate of certificate",
        "issuer": "certificate issuer",
        "issueDate": "2024-12-15T00:00:00.000+00:00",
        "expirationDate": "2025-12-14T00:00:00.000+00:00",
        "credentialId": "5413851",
        "credentialUrl": "http:/fmlkdfkss",
        "imageUrl": "c:gsf/fsf"
    }
}
```
---

#### Education Response
* Education Response 
```json
{
    "message": "Education created successfully",
    "httpStatusCode": 200,
    "response": {
        "id": "676599e0fbf76807a5d8e21c",
        "school": "University of Computer Science",
        "degree": "Bachelors",
        "fieldOfStudy": "Computer Science",
        "startYear": "2018",
        "endYear": "2022",
        "isCurrentlyStudying": true,
        "freelancerId": null
    }
}
```
* Education Details Response 
```json
{
    "message": "Education details retrieved successfully",
    "httpStatusCode": 200,
    "response": {
        "id": "676599e0fbf76807a5d8e21c",
        "school": "University of Computer Science",
        "degree": "Bachelors",
        "fieldOfStudy": "Computer Science",
        "startDate": "2018-12-15T00:00:00.000+00:00",
        "endDate": "2022-12-14T00:00:00.000+00:00",
        "isCurrentlyStudying": true,
        "grade": "A",
        "description": "Bachelors in Computer Science",
        "imageUrl": null,
        "freelancerId": null
    }
}
```
---

#### Project Response
* Project Response
```json
{
    "message": "Project retrieved successfully",
    "httpStatusCode": 200,
    "response": {
        "id": "67659ea903d7d432b0d26db1",
        "name": "Website Redesign",
        "description": "A project to redesign the company's main website for better user experience.",
        "imageUrl": "https://example.com/images/project-redesign.jpg",
        "projectUrl": "https://example.com/projects/website-redesign",
        "freelancerId": null
    }
}
```

* Project Details Response
```json
{
    "message": "Project details retrieved successfully",
    "httpStatusCode": 200,
    "response": {
        "id": "67659ea903d7d432b0d26db1",
        "name": "Website Redesign",
        "description": "A project to redesign the company's main website for better user experience.",
        "imageUrl": "https://example.com/images/project-redesign.jpg",
        "projectUrl": "https://example.com/projects/website-redesign",
        "projectType": "Web Development",
        "projectStatus": "In Progress",
        "projectStartDate": "2024-01-15T00:00:00.000+00:00",
        "projectEndDate": "2024-04-30T00:00:00.000+00:00",
        "freelancerId": null
    }
}
```
---

#### Review Response
* Review Response 
```json
{
    "message": "Review created successfully",
    "httpStatusCode": 200,
    "response": {
        "id": "6765a0a0b6455d4e79de7e6f",
        "reviewerId": "user456",
        "review": "Excellent work! The freelancer delivered the project on time with outstanding quality.",
        "createdAt": "2024-12-20T16:51:44.293+00:00",
        "rating": 5,
        "imageUrl": "https://example.com/images/review-image.jpg",
        "freelancerId": "freelancer123"
    }
}
```
---

#### Social Media Response
* Social Media Response
```json
{
    "message": "Social media created successfully",
    "httpStatusCode": 200,
    "response": {
        "id": "6765afa8396a303880a108bb",
        "platform": "LINKEDIN",
        "url": "https://www.linkedin.com/in/freelancer123",
        "imageUrl": "https://example.com/images/linkedin-profile.jpg",
        "freelancerId": "freelancer123"
    }
}
```

---

#### Work Experience Response
* Work Experience Response
```json
{
    "message": "Work experience created successfully",
    "httpStatusCode": 200,
    "response": {
        "id": "6765b344396a303880a108bc",
        "title": "Software Engineer",
        "company": "Tech Innovations Inc.",
        "imageUrl": "https://example.com/images/tech-innovations.jpg",
        "endDate": "2023-11-30T00:00:00.000+00:00",
        "freelancerId": "freelancer123",
        "current": false
    }
}
```

* Work Experience Details Response
```json
{
    "message": "Work experience details retrieved successfully",
    "httpStatusCode": 200,
    "response": {
        "id": "6765b344396a303880a108bc",
        "title": "Software Engineer",
        "company": "Tech Innovations Inc.",
        "city": "Istanbul",
        "description": "Developed and maintained scalable web applications, contributing to a 30% increase in system performance.",
        "imageUrl": "https://example.com/images/tech-innovations.jpg",
        "startDate": "2021-03-01T00:00:00.000+00:00",
        "endDate": "2023-11-30T00:00:00.000+00:00",
        "freelancerId": "freelancer123",
        "current": false
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
