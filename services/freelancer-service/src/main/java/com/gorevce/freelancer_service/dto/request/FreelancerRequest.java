package com.gorevce.freelancer_service.dto.request;

import com.gorevce.freelancer_service.dto.AddressDto;
import com.gorevce.freelancer_service.dto.response.*;
import lombok.*;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FreelancerRequest {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private Map<String,Integer> skills;
    private Set<CertificateRequest> certificates;
    private Set<ProjectRequest> projects;
    private Set<EducationRequest> education;
    private Set<WorkExperienceRequest> workExperience;
    private Set<SocialMediaRequest> socialMedia;

    private AddressDto address;
    private String UserId;
}
