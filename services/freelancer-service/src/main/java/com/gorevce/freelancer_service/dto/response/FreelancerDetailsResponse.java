package com.gorevce.freelancer_service.dto.response;

import com.gorevce.freelancer_service.dto.AddressDto;
import com.gorevce.freelancer_service.dto.UserDto;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FreelancerDetailsResponse {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private Map<String,Integer> skills;
    private List<CertificateResponse> certificates;
    private List<ProjectResponse> projects;
    private List<ReviewResponse> reviews;
    private List<EducationResponse> education;
    private List<WorkExperienceResponse> workExperience;
    private List<SocialMediaResponse> socialMedia;

    private AddressDto address;
    private UserDto user;
//    private String addressId;
//    private String UserId;
}
