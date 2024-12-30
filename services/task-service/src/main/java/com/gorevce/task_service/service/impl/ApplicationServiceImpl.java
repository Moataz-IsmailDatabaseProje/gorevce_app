package com.gorevce.task_service.service.impl;

import com.gorevce.task_service.dto.EmailDto;
import com.gorevce.task_service.dto.request.ApplicationRequest;
import com.gorevce.task_service.dto.response.ApplicationResponse;
import com.gorevce.task_service.dto.response.SendEmailResponse;
import com.gorevce.task_service.exception.CustomException;
import com.gorevce.task_service.model.Application;
import com.gorevce.task_service.model.Task;
import com.gorevce.task_service.model.enums.ApplicationStatus;
import com.gorevce.task_service.model.enums.TaskStatus;
import com.gorevce.task_service.repository.ApplicationRepository;
import com.gorevce.task_service.service.ApplicationService;
import com.gorevce.task_service.service.EmailService;
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

    @Autowired
    private EmailService emailService;

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

    @Override
    public ApplicationResponse acceptApplication(String applicationId) {
        // get application by id or throw exception
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
        // check if application status is created
        if (application.getStatus() != ApplicationStatus.CREATED) {
            throw new CustomException(
                    "Application is not created",
                    400,
                    Map.of(
                            "applicationId", applicationId
                    )
            );
        }
        // check if task exists
        if (!validationService.doesTaskExist(application.getTaskId())) {
            throw new CustomException(
                    "Task does not exist",
                    400,
                    null
            );
        }
        // check task status
        if (validationService.getTaskStatus(application.getTaskId()) != TaskStatus.PUBLISHED) {
            throw new CustomException(
                    "Task is not existing",
                    400,
                    Map.of(
                            "taskId", application.getTaskId()
                    )
            );
        }
        // check if task is owned by company
        if (!validationService.isTaskOwnedByCompany(application.getTaskId())) {
            throw new CustomException(
                    "Task is not owned by company",
                    400,
                    Map.of(
                            "taskId", application.getTaskId()
                    )
            );
        }
        // check if freelancer exists
        if (!validationService.doesFreelancerExist(application.getFreelancerId())) {
            throw new CustomException(
                    "Freelancer does not exist",
                    400,
                    null
            );
        }

        // update status of application to accepted
        application.setStatus(ApplicationStatus.ACCEPTED);

        // update status of task to assigned
        validationService.updateTaskStatus(application.getTaskId(), TaskStatus.ASSIGNED);

        // save application
        Application saved = applicationRepository.save(application);

        // return application
        return mapApplicationToResponse(saved);
    }

    @Override
    public SendEmailResponse sendEmail(EmailDto emailDto) {
        // prepare email
        try {
            emailService.sendPlainTextEmail(emailDto.getEmail(), emailDto.getSubject(), emailDto.getMessage());
            return SendEmailResponse.builder()
                    .email(emailDto.getEmail())
                    .subject(emailDto.getSubject())
                    .message(emailDto.getMessage())
                    .build();
        } catch (CustomException e) {
            throw new CustomException(
                    "Email could not be sent",
                    400,
                    Map.of(
                            "email", emailDto.getEmail()
                    )
            );
        }
    }

    @Override
    public ApplicationResponse applyPayment(String applicationId) {
        // get application by id or throw exception
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
        // check if application status is accepted
        if (application.getStatus() != ApplicationStatus.ACCEPTED) {
            throw new CustomException(
                    "Application is not accepted",
                    400,
                    Map.of(
                            "applicationId", applicationId
                    )
            );
        }
        // set Task status to completed
        validationService.updateTaskStatus(application.getTaskId(), TaskStatus.COMPLETED);
        // set application status to completed
        validationService.updateApplicationStatus(applicationId, ApplicationStatus.COMPLETED);
        // return application
        return mapApplicationToResponse(application);
    }

}
