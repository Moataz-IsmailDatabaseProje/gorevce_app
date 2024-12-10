package com.gorevce.freelancer_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "freelancers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Freelancer {
    @Id
    private String id;
    private String name;
    private String email;
    private String username;
    private String phoneNumber;
    private String profilePicture;
    private String description;
    private Set<Skill> skills;
    private Set<Education> educations;
    private Set<Experience> experiences;
    private Set<Certification> certifications;
    private String hourlyRate;
    private String availability;

    private String addressId;
    private String CredentialsId;
}
