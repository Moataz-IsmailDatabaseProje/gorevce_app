package com.gorevce.freelancer_service.service;

import com.gorevce.freelancer_service.dto.request.WorkExperienceRequest;
import com.gorevce.freelancer_service.dto.response.WorkExperienceDetailsResponse;
import com.gorevce.freelancer_service.dto.response.WorkExperienceResponse;
import com.gorevce.freelancer_service.model.WorkExperience;

import java.util.List;

public interface WorkExperienceService {
    // create work experience
    WorkExperienceResponse createWorkExperience(WorkExperienceRequest workExperience);

    // get work experience by id
    WorkExperienceResponse getWorkExperienceById(String id);

    // get work experience by freelancer id
    List<WorkExperienceResponse> getWorkExperienceByFreelancerId(String freelancerId);

    // update work experience
    WorkExperienceResponse updateWorkExperience(String id, WorkExperienceRequest workExperience);

    // delete work experience
    void deleteWorkExperience(String id);

    // get all work experience
    List<WorkExperienceResponse> getAllWorkExperience();

    // get work experience details by id
    WorkExperienceDetailsResponse getWorkExperienceDetailsById(String id);

    // get work experience model by id
    WorkExperience getWorkExperienceModelById(String id);
}
