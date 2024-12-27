package com.gorevce.task_service.service.impl;

import com.gorevce.task_service.dto.request.ApplicationRequest;
import com.gorevce.task_service.dto.response.ApplicationResponse;
import com.gorevce.task_service.exception.CustomException;
import com.gorevce.task_service.model.Application;
import com.gorevce.task_service.model.enums.ApplicationStatus;
import com.gorevce.task_service.repository.ApplicationRepository;
import com.gorevce.task_service.service.ApplicationService;
import com.gorevce.task_service.service.TaskService;
import com.gorevce.task_service.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ValidationService validationService;

    @Override
    public ApplicationResponse createApplication(ApplicationRequest applicationRequest) {
        // check if task exists
        if (!validationService.doesTaskExist(applicationRequest.getTaskId())) {
            throw new CustomException(
                    "Task does not exist",
                    400,
                    null
            );
        }
        // check if freelancer exists
        if (!validationService.doesFreelancerExist(applicationRequest.getFreelancerId())) {
            throw new CustomException(
                    "Freelancer does not exist",
                    400,
                    null
            );
        }
        if (
                validationService.doesTaskExist(applicationRequest.getTaskId()) &&
                validationService.doesFreelancerExist(applicationRequest.getFreelancerId())
        ) {
            // create application
            Application application = Application.builder()
                    .taskId(applicationRequest.getTaskId())
                    .freelancerId(applicationRequest.getFreelancerId())
                    .status(ApplicationStatus.CREATED)
                    .comment(applicationRequest.getComment())
                    .date(new Date())
                    .wage(applicationRequest.getWage())
                    .duration(applicationRequest.getDuration())
                    .build();
            Application saved = applicationRepository.save(application);
            return mapApplicationToResponse(saved);
        }
        throw new CustomException(
                "something went wrong",
                400,
                null
        );
    }

    private ApplicationResponse mapApplicationToResponse(Application saved) {
        return ApplicationResponse.builder()
                .id(saved.getId())
                .taskId(saved.getTaskId())
                .freelancerId(saved.getFreelancerId())
                .status(saved.getStatus().name())
                .comment(saved.getComment())
                .date(saved.getDate().toString())
                .wage(saved.getWage())
                .duration(saved.getDuration())
                .build();
    }

    @Override
    public ApplicationResponse updateApplication(ApplicationRequest applicationRequest, String applicationId) {
        // check if task exists
        if (!validationService.doesTaskExist(applicationRequest.getTaskId())) {
            throw new CustomException(
                    "Task does not exist",
                    400,
                    null
            );
        }
        // check if freelancer exists
        if (!validationService.doesFreelancerExist(applicationRequest.getFreelancerId())) {
            throw new CustomException(
                    "Freelancer does not exist",
                    400,
                    null
            );
        }
        // get application
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(
                        () -> new CustomException(
                            "Application does not exist",
                            400,
                            Map.of(
                                    "applicationId", applicationId
                            )
                        )
                );
        // update application
        application.setComment(applicationRequest.getComment());
        application.setWage(applicationRequest.getWage());
        application.setDuration(applicationRequest.getDuration());
        Application saved = applicationRepository.save(application);
        return mapApplicationToResponse(saved);
    }

    @Override
    public void deleteApplication(String applicationId) {
        // get application
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(
                        () -> new CustomException(
                                "Application does not exist",
                                400,
                                Map.of(
                                        "applicationId", applicationId
                                )
                        )
                );
        // check application status
        if (application.getStatus() != ApplicationStatus.CANCELLED) {
            throw new CustomException(
                    "Application is not cancelled",
                    400,
                    Map.of(
                            "applicationId", applicationId
                    )
            );
        }
        // delete application
        applicationRepository.delete(application);
    }

    @Override
    public ApplicationResponse getApplication(String applicationId) {
        // get application
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(
                        () -> new CustomException(
                                "Application does not exist",
                                400,
                                Map.of(
                                        "applicationId", applicationId
                                )
                        )
                );
        return mapApplicationToResponse(application);
    }

    @Override
    public List<ApplicationResponse> getAllApplications() {
        // get all applications
        List<Application> applications = applicationRepository.findAll();
        return applications.stream()
                .map(this::mapApplicationToResponse)
                .toList();
    }

    @Override
    public List<ApplicationResponse> getApplicationsByTaskId(String taskId) {
        // get applications by task id
        List<Application> applications = applicationRepository.findByTaskId(taskId);
        return applications.stream()
                .map(this::mapApplicationToResponse)
                .toList();
    }

    @Override
    public List<ApplicationResponse> getApplicationsByFreelancerId(String freelancerId) {
        // get applications by freelancer id
        List<Application> applications = applicationRepository.findByFreelancerId(freelancerId);
        return applications.stream()
                .map(this::mapApplicationToResponse)
                .toList();
    }
}
