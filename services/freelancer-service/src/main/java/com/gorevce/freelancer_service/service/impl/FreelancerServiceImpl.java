package com.gorevce.freelancer_service.service.impl;

import com.gorevce.freelancer_service.dto.AddressDto;
import com.gorevce.freelancer_service.dto.UserDto;
import com.gorevce.freelancer_service.dto.request.FreelancerRequest;
import com.gorevce.freelancer_service.dto.response.*;
import com.gorevce.freelancer_service.exception.CustomException;
import com.gorevce.freelancer_service.model.*;
import com.gorevce.freelancer_service.repository.FreelancerRepository;
import com.gorevce.freelancer_service.service.FreelancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class FreelancerServiceImpl implements FreelancerService {

    // inject dependencies here
    @Autowired
    private WorkExperienceServiceImpl workExperienceService;
    @Autowired
    private EducationServiceImpl educationService;
    @Autowired
    private CertificateServiceImpl certificateService;
    @Autowired
    private ProjectServiceImpl projectService;
    @Autowired
    private ReviewServiceImpl reviewService;
    @Autowired
    private SocialMediaServiceImpl socialMediaService;
    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    @Qualifier("plainRestTemplate")
    private RestTemplate restTemplate;

    @Value("${application.config.authentication-service.url}")
    private String authenticationServiceUrl;

    @Value("${application.config.address-service.url}")
    private String addressServiceUrl;
    @Override
    public FreelancerResponse createFreelancer(FreelancerRequest freelancer) {
        // create freelancer
        Freelancer newFreelancer = Freelancer.builder()
                .name(freelancer.getName())
                .surname(freelancer.getSurname())
                .email(freelancer.getEmail())
                .phoneNumber(freelancer.getPhoneNumber())
                .skills(freelancer.getSkills())
                .UserId(freelancer.getUserId())
                .isDeleted(false)
                .build();
        // save freelancer to database
        Freelancer saved = freelancerRepository.save(newFreelancer);
        // check if freelancer has certificates
        if (freelancer.getCertificates() != null && !freelancer.getCertificates().isEmpty()) {
            // create certificates
            freelancer.getCertificates().forEach(certificate -> {
                CertificateResponse certificateResponse = certificateService.createCertificate(certificate);
                // get certificate object by id from service
                Certificate certificateModel = certificateService.getCertificateModelById(certificateResponse.getId());
                // add certificate to freelancer
                saved.getCertificates().add(certificateModel);
            });
        }
        // check if freelancer has work experiences
        if (freelancer.getWorkExperience() != null && !freelancer.getWorkExperience().isEmpty()) {
            // create work experiences
            freelancer.getWorkExperience().forEach(workExperience -> {
                WorkExperienceResponse workExperienceResponse = workExperienceService.createWorkExperience(workExperience);
                workExperienceResponse.setFreelancerId(saved.getId());
                // get work experience object by id from service
                WorkExperience workExperienceModel = workExperienceService.getWorkExperienceModelById(workExperienceResponse.getId());
                // add work experience to freelancer
                saved.getWorkExperience().add(workExperienceModel);
            });
        }
        // check if freelancer has projects
        if (freelancer.getProjects() != null && !freelancer.getProjects().isEmpty()) {
            // create projects
            freelancer.getProjects().forEach(project -> {
                ProjectResponse projectResponse = projectService.createProject(project);
                projectResponse.setFreelancerId(saved.getId());
                // get project object by id from service
                Project projectModel = projectService.getProjectModelById(projectResponse.getId());
                // add project to freelancer
                saved.getProjects().add(projectModel);
            });
        }
        // check if freelancer has education
        if (freelancer.getEducation() != null && !freelancer.getEducation().isEmpty()) {
            // create education
            freelancer.getEducation().forEach(education -> {
                EducationResponse educationResponse = educationService.createEducation(education);
                educationResponse.setFreelancerId(saved.getId());
                // get education object by id from service
                Education educationModel = educationService.getEducationModelById(educationResponse.getId());
                // add education to freelancer
                saved.getEducation().add(educationModel);
            });
        }
        // check if freelancer has SocialMedia
        if (freelancer.getSocialMedia() != null && !freelancer.getSocialMedia().isEmpty()){
            // create social media
            freelancer.getSocialMedia().forEach(socialMedia -> {
                SocialMediaResponse socialMediaResponse = socialMediaService.createSocialMedia(socialMedia);
                socialMediaResponse.setFreelancerId(saved.getId());
                // get social media object by id from service
                SocialMedia socialMediaModel = socialMediaService.getSocialMediaModelById(socialMediaResponse.getId());
                // add social media to freelancer
                saved.getSocialMedia().add(socialMediaModel);
            });
        }
        // save freelancer to database
        freelancerRepository.save(saved);
        // return freelancer
        return FreelancerResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .surname(saved.getSurname())
                .email(saved.getEmail())
                .phoneNumber(saved.getPhoneNumber())
                .skills(saved.getSkills())
                .UserId(saved.getUserId())
                .certificatesId(saved.getCertificates().stream().map(Certificate::getId).toList())
                .workExperienceId(saved.getWorkExperience().stream().map(WorkExperience::getId).toList())
                .projectsId(saved.getProjects().stream().map(Project::getId).toList())
                .educationId(saved.getEducation().stream().map(Education::getId).toList())
                .socialMediaId(saved.getSocialMedia().stream().map(SocialMedia::getId).toList())
                .build();
    }

    @Override
    public FreelancerResponse getFreelancerById(String id) {
        // get freelancer by id
        Freelancer freelancer = freelancerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException(
                                "Freelancer not found",
                                404,
                                Map.of(
                                        "id", id
                                )
                        )
                );
        // check if freelancer is deleted
        if (freelancer.getIsDeleted()) {
            throw new CustomException(
                    "Freelancer not found",
                    404,
                    Map.of(
                            "id", id
                    )
            );
        }
        // return freelancer
        return FreelancerResponse.builder()
                .id(freelancer.getId())
                .name(freelancer.getName())
                .surname(freelancer.getSurname())
                .email(freelancer.getEmail())
                .phoneNumber(freelancer.getPhoneNumber())
                .skills(freelancer.getSkills())
                .UserId(freelancer.getUserId())
                .certificatesId(freelancer.getCertificates().stream().map(Certificate::getId).toList())
                .workExperienceId(freelancer.getWorkExperience().stream().map(WorkExperience::getId).toList())
                .projectsId(freelancer.getProjects().stream().map(Project::getId).toList())
                .educationId(freelancer.getEducation().stream().map(Education::getId).toList())
                .socialMediaId(freelancer.getSocialMedia().stream().map(SocialMedia::getId).toList())
                .build();
    }

    @Override
    public FreelancerResponse updateFreelancer(String id, FreelancerRequest freelancer) {
        // get freelancer by id
        Freelancer updatedFreelancer = freelancerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException(
                                "Freelancer not found",
                                404,
                                Map.of(
                                        "id", id
                                )
                        )
                );
        // check if freelancer is deleted
        if (updatedFreelancer.getIsDeleted()) {
            throw new CustomException(
                    "Freelancer not found",
                    404,
                    Map.of(
                            "id", id
                    )
            );
        }

        // update freelancer
        updatedFreelancer.setName(freelancer.getName());
        updatedFreelancer.setSurname(freelancer.getSurname());
        updatedFreelancer.setEmail(freelancer.getEmail());
        updatedFreelancer.setPhoneNumber(freelancer.getPhoneNumber());
        updatedFreelancer.setSkills(freelancer.getSkills());
        updatedFreelancer.setUserId(freelancer.getUserId());
        // save freelancer to database
        Freelancer saved = freelancerRepository.save(updatedFreelancer);
        // return freelancer
        return FreelancerResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .surname(saved.getSurname())
                .email(saved.getEmail())
                .phoneNumber(saved.getPhoneNumber())
                .skills(saved.getSkills())
                .UserId(saved.getUserId())
                .certificatesId(saved.getCertificates().stream().map(Certificate::getId).toList())
                .workExperienceId(saved.getWorkExperience().stream().map(WorkExperience::getId).toList())
                .projectsId(saved.getProjects().stream().map(Project::getId).toList())
                .educationId(saved.getEducation().stream().map(Education::getId).toList())
                .socialMediaId(saved.getSocialMedia().stream().map(SocialMedia::getId).toList())
                .build();
    }

    @Override
    public List<FreelancerResponse> getAllFreelancers() {
        // get all freelancers
        List<Freelancer> freelancers = freelancerRepository.findAll();
        // filter deleted freelancers
        freelancers.removeIf(Freelancer::getIsDeleted);
        // return freelancers
        return freelancers.stream()
                .map(freelancer -> FreelancerResponse.builder()
                        .id(freelancer.getId())
                        .name(freelancer.getName())
                        .surname(freelancer.getSurname())
                        .email(freelancer.getEmail())
                        .phoneNumber(freelancer.getPhoneNumber())
                        .skills(freelancer.getSkills())
                        .UserId(freelancer.getUserId())
                        .certificatesId(freelancer.getCertificates().stream().map(Certificate::getId).toList())
                        .workExperienceId(freelancer.getWorkExperience().stream().map(WorkExperience::getId).toList())
                        .projectsId(freelancer.getProjects().stream().map(Project::getId).toList())
                        .educationId(freelancer.getEducation().stream().map(Education::getId).toList())
                        .socialMediaId(freelancer.getSocialMedia().stream().map(SocialMedia::getId).toList())
                        .build())
                .toList();
    }

    @Override
    public FreelancerDetailsResponse getFreelancerDetailsById(String id) {
        // get freelancer by id
        Freelancer freelancer = freelancerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException(
                                "Freelancer not found",
                                404,
                                Map.of(
                                        "id", id
                                )
                        )
                );
       // check if freelancer is deleted
        if (freelancer.getIsDeleted()) {
            throw new CustomException(
                    "Freelancer not found",
                    404,
                    Map.of(
                            "id", id
                    )
            );
        }
        // get experience by freelancer id
        List<WorkExperienceResponse> workExperience = workExperienceService.getWorkExperienceByFreelancerId(id);
        // get education by freelancer id
        List<EducationResponse> education = educationService.getEducationsByFreelancer(id);
        // get certificates by freelancer id
        List<CertificateResponse> certificates = certificateService.getCertificatesByFreelancer(id);
        // get projects by freelancer id
        List<ProjectResponse> projects = projectService.getProjectsByFreelancer(id);
        // get reviews by freelancer id
        List<ReviewResponse> reviews = reviewService.getReviewsByFreelancer(id);
        // get social media by freelancer id
        List<SocialMediaResponse> socialMedia = socialMediaService.getSocialMediaByFreelancerId(id);
        // get User from authentication service by id
        UserDto user = null;
        try {
            user = restTemplate.getForObject(authenticationServiceUrl + "/auth/rest-template/get-user/" + freelancer.getUserId(), UserDto.class);
        } catch (Exception e) {
            throw new CustomException(
                    "User not found",
                    404,
                    Map.of(
                            "id", freelancer.getUserId()
                    )
            );
        }

        // get address from address service by id
        AddressDto address = null;
        try {
            address = restTemplate.getForObject(addressServiceUrl + "/address/rest-template/get-address/" + freelancer.getAddressId(), AddressDto.class);
        } catch (Exception e) {
            throw new CustomException(
                    "Address not found",
                    404,
                    Map.of(
                            "id", freelancer.getAddressId()
                    )
            );
        }

        // return freelancer details
        return FreelancerDetailsResponse.builder()
                .id(freelancer.getId())
                .name(freelancer.getName())
                .surname(freelancer.getSurname())
                .email(freelancer.getEmail())
                .phoneNumber(freelancer.getPhoneNumber())
                .skills(freelancer.getSkills())
                .workExperience(workExperience)
                .education(education)
                .certificates(certificates)
                .projects(projects)
                .reviews(reviews)
                .socialMedia(socialMedia)
                .build();
    }

    @Override
    public Object getUserById(String id) {
        // get userDto from authentication service
        try {
            return restTemplate.getForObject(authenticationServiceUrl + "/auth/rest-template/get-user/" + id, UserDto.class);
        } catch (Exception e) {
            throw new CustomException(
                    "User not found",
                    404,
                    Map.of(
                            "id", id
                    )
            );
        }
    }

    @Override
    public Object getAddressById(String id) {
        try {
            return restTemplate.getForObject(addressServiceUrl + "/address/rest-template/get-address/" + id, AddressDto.class);
        } catch (Exception e) {
            throw new CustomException(
                    "Address not found",
                    404,
                    Map.of(
                            "id", id
                    )
            );
        }
    }

    @Override
    public List<WorkExperienceResponse> getWorkExperienceById(String id) {
        // check if freelancer exists
        Freelancer freelancer = freelancerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException(
                                "Freelancer not found",
                                404,
                                Map.of(
                                        "id", id
                                )
                        )
                );
        // check if freelancer is deleted
        if (freelancer.getIsDeleted()) {
            throw new CustomException(
                    "Freelancer not found",
                    404,
                    Map.of(
                            "id", id
                    )
            );
        }
        // get work experiences by freelancer id
        return workExperienceService.getWorkExperienceByFreelancerId(id);
    }

    @Override
    public List<EducationResponse> getEducationById(String id) {
        // check if freelancer exists
        Freelancer freelancer = freelancerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException(
                                "Freelancer not found",
                                404,
                                Map.of(
                                        "id", id
                                )
                        )
                );
        // check if freelancer is deleted
        if (freelancer.getIsDeleted()) {
            throw new CustomException(
                    "Freelancer not found",
                    404,
                    Map.of(
                            "id", id
                    )
            );
        }
        // get education by freelancer id
        return educationService.getEducationsByFreelancer(id);
    }

    @Override
    public Map<String, Integer> getSkillsById(String id) {
        // get skills by freelancer id
        Freelancer freelancer = freelancerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException(
                                "Freelancer not found",
                                404,
                                Map.of(
                                        "id", id
                                )
                        )
                );
        // check if freelancer is deleted
        if (freelancer.getIsDeleted()) {
            throw new CustomException(
                    "Freelancer not found",
                    404,
                    Map.of(
                            "id", id
                    )
            );
        }
        return freelancer.getSkills();
    }

    @Override
    public List<CertificateResponse> getCertificateById(String id) {
        // check if freelancer exists
        Freelancer freelancer = freelancerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException(
                                "Freelancer not found",
                                404,
                                Map.of(
                                        "id", id
                                )
                        )
                );
        // check if freelancer is deleted
        if (freelancer.getIsDeleted()) {
            throw new CustomException(
                    "Freelancer not found",
                    404,
                    Map.of(
                            "id", id
                    )
            );
        }
        // get certificates by freelancer id
        return certificateService.getCertificatesByFreelancer(id);
    }

    @Override
    public List<ProjectResponse> getProjectById(String id) {
        // check if freelancer exists
        Freelancer freelancer = freelancerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException(
                                "Freelancer not found",
                                404,
                                Map.of(
                                        "id", id
                                )
                        )
                );
        // check if freelancer is deleted
        if (freelancer.getIsDeleted()) {
            throw new CustomException(
                    "Freelancer not found",
                    404,
                    Map.of(
                            "id", id
                    )
            );
        }
        // get projects by freelancer id
        return projectService.getProjectsByFreelancer(id);
    }

    @Override
    public List<ReviewResponse> getReviewById(String id) {
        // check if freelancer exists
        Freelancer freelancer = freelancerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException(
                                "Freelancer not found",
                                404,
                                Map.of(
                                        "id", id
                                )
                        )
                );
        // check if freelancer is deleted
        if (freelancer.getIsDeleted()) {
            throw new CustomException(
                    "Freelancer not found",
                    404,
                    Map.of(
                            "id", id
                    )
            );
        }
        // get reviews by freelancer id
        return reviewService.getReviewsByFreelancer(id);
    }

    @Override
    public List<SocialMediaResponse> getSocialMediaById(String id) {
        // check if freelancer exists
        Freelancer freelancer = freelancerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException(
                                "Freelancer not found",
                                404,
                                Map.of(
                                        "id", id
                                )
                        )
                );
        // check if freelancer is deleted
        if (freelancer.getIsDeleted()) {
            throw new CustomException(
                    "Freelancer not found",
                    404,
                    Map.of(
                            "id", id
                    )
            );
        }
        // get social media by freelancer id
        return socialMediaService.getSocialMediaByFreelancerId(id);
    }

    @Override
    public void deleteFreelancerById(String id) {
        // find freelancer by id
        Freelancer freelancer = freelancerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException(
                                "Freelancer not found",
                                404,
                                Map.of(
                                        "id", id
                                )
                        )
                );
        // if freelancer already deleted
        if (freelancer.getIsDeleted()) {
            throw new CustomException(
                    "Freelancer already deleted",
                    400,
                    Map.of(
                            "id", id
                    )
            );
        }
        // delete freelancer
        freelancer.setIsDeleted(true);
        freelancerRepository.save(freelancer);
    }

    @Override
    public void restoreFreelancerById(String id) {
        // find freelancer by id
        Freelancer freelancer = freelancerRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException(
                                "Freelancer not found",
                                404,
                                Map.of(
                                        "id", id
                                )
                        )
                );
        // if freelancer already restored
        if (!freelancer.getIsDeleted()) {
            throw new CustomException(
                    "Freelancer already restored",
                    400,
                    Map.of(
                            "id", id
                    )
            );
        }
        // restore freelancer
        freelancer.setIsDeleted(false);
        freelancerRepository.save(freelancer);
    }
}
