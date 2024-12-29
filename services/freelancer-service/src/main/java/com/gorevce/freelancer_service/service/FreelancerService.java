package com.gorevce.freelancer_service.service;

import com.gorevce.freelancer_service.dto.request.FreelancerRequest;
import com.gorevce.freelancer_service.dto.response.*;

import java.util.List;
import java.util.Map;

public interface FreelancerService {
    // create freelancer
    FreelancerResponse createFreelancer(FreelancerRequest freelancer);

    // get freelancer by id
    FreelancerResponse getFreelancerById(String id);

    // update freelancer
    FreelancerResponse updateFreelancer(String id, FreelancerRequest freelancer);


    // get all freelancers
    List<FreelancerResponse> getAllFreelancers();

    // get freelancer details by id
    FreelancerDetailsResponse getFreelancerDetailsById(String id);

    // get user by id
    Object getUserById(String id);

    // get freelancer address by id
    Object getAddressById(String id);

    // get freelancer work experience by id
    List<WorkExperienceResponse> getWorkExperienceById(String id);

    // get freelancer education by id
    List<EducationResponse> getEducationById(String id);

    // get freelancer skills by id
    Map<String,Integer> getSkillsById(String id);

    // get freelancer certificates by id
    List<CertificateResponse> getCertificateById(String id);

    // get freelancer projects by id
    List<ProjectResponse> getProjectById(String id);

    // get freelancer reviews by id
    List<ReviewResponse> getReviewById(String id);

    // get freelancer social media by id
    List<SocialMediaResponse> getSocialMediaById(String id);

    // delete freelancer by id
    void deleteFreelancerById(String id);

    // restore freelancer by id
    void restoreFreelancerById(String id);

    Boolean doesFreelancerExist(String id);
}
