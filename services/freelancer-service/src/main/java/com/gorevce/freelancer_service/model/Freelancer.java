package com.gorevce.freelancer_service.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
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
    private String surname;
    private String email;
    private String phoneNumber;
    private Map<String,Integer> skills;
    private Set<Certificate> certificates;
    private Set<Project> projects;
    private Set<Review> reviews;
    private Set<Education> education;
    private Set<WorkExperience> workExperience;
    private Set<SocialMedia> socialMedia;
    private Boolean isDeleted;

    private String addressId;
    private String UserId;
}
