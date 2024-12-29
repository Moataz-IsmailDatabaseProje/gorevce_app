package com.gorevce.task_service.service;

import com.gorevce.task_service.dto.request.ApplicationRequest;
import com.gorevce.task_service.dto.response.ApplicationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ApplicationService {
    // create application
    ApplicationResponse createApplication(ApplicationRequest applicationRequest);
    // update application
    ApplicationResponse updateApplication(ApplicationRequest applicationRequest, String applicationId);
    // delete application
    void deleteApplication(String applicationId);
    // get application by id
    ApplicationResponse getApplication(String applicationId);
    // get all applications
    List<ApplicationResponse> getAllApplications();
    // get applications by task id
    List<ApplicationResponse> getApplicationsByTaskId(String taskId);
    // get applications by freelancer id
    List<ApplicationResponse> getApplicationsByFreelancerId(String freelancerId);

}
