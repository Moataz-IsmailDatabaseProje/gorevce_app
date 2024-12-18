package com.gorevce.freelancer_service.dto.response;

import com.gorevce.freelancer_service.model.*;
import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FreelancerResponse {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private Map<String,Integer> skills;
    private List<String> certificatesId;
    private List<String> projectsId;
    private List<String> reviewsId;
    private List<String> educationId;
    private List<String> workExperienceId;
    private List<String> socialMediaId;

    private String addressId;
    private String UserId;
}
