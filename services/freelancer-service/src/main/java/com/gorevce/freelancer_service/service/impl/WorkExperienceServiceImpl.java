package com.gorevce.freelancer_service.service.impl;

import com.gorevce.freelancer_service.dto.request.WorkExperienceRequest;
import com.gorevce.freelancer_service.dto.response.WorkExperienceDetailsResponse;
import com.gorevce.freelancer_service.dto.response.WorkExperienceResponse;
import com.gorevce.freelancer_service.event.added.WorkExperienceAddedEvent;
import com.gorevce.freelancer_service.exception.CustomException;
import com.gorevce.freelancer_service.model.WorkExperience;
import com.gorevce.freelancer_service.repository.WorkExperienceRepository;
import com.gorevce.freelancer_service.service.FreelancerService;
import com.gorevce.freelancer_service.service.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {

    @Autowired
    private WorkExperienceRepository workExperienceRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public WorkExperienceResponse createWorkExperience(WorkExperienceRequest workExperience) {
        // check start date is before end date
        if (workExperience.getStartDate().after(workExperience.getEndDate())) {
            throw new CustomException(
                    "Start date is after end date",
                    400,
                    Map.of(
                            "startDate", workExperience.getStartDate(),
                            "endDate", workExperience.getEndDate()
                    )
            );
        }
        // check if start date is in the future
        if (workExperience.getStartDate().after(new java.util.Date())) {
            throw new CustomException(
                    "Start date is in the future",
                    400,
                    Map.of(
                            "startDate", workExperience.getStartDate()
                    )
            );
        }
        // check if freelancer id is not null
        if (workExperience.getFreelancerId() == null) {
            throw new CustomException(
                    "Freelancer id is required",
                    400,
                    Map.of(
                            "freelancerId", "null"
                    )
            );
        }
        // create work experience
        WorkExperience workExperienceModel = WorkExperience.builder()
                .title(workExperience.getTitle())
                .company(workExperience.getCompany())
                .city(workExperience.getCity())
                .description(workExperience.getDescription())
                .imageUrl(workExperience.getImageUrl())
                .startDate(workExperience.getStartDate())
                .endDate(workExperience.getEndDate())
                .isCurrent(workExperience.isCurrent())
                .freelancerId(workExperience.getFreelancerId())
                .build();
        // save work experience to database
        WorkExperience saved = workExperienceRepository.save(workExperienceModel);
        eventPublisher.publishEvent(new WorkExperienceAddedEvent(this, saved.getFreelancerId(), saved.getId()));
        // return work experience
        return WorkExperienceResponse.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .company(saved.getCompany())
                .imageUrl(saved.getImageUrl())
                .endDate(saved.getEndDate())
                .isCurrent(saved.isCurrent())
                .freelancerId(saved.getFreelancerId())
                .build();
    }

    @Override
    public WorkExperienceResponse getWorkExperienceById(String id) {
        // get work experience by id
        WorkExperience workExperience = workExperienceRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException(
                                "Work experience not found",
                                404,
                                Map.of(
                                        "id", id
                                )
                        )
                );
        // return work experience
        return WorkExperienceResponse.builder()
                .id(workExperience.getId())
                .title(workExperience.getTitle())
                .company(workExperience.getCompany())
                .imageUrl(workExperience.getImageUrl())
                .endDate(workExperience.getEndDate())
                .isCurrent(workExperience.isCurrent())
                .freelancerId(workExperience.getFreelancerId())
                .build();
    }

    @Override
    public List<WorkExperienceResponse> getWorkExperienceByFreelancerId(String freelancerId) {
        // get work experience by freelancer id
        List<WorkExperience> workExperienceList = workExperienceRepository.findByFreelancerId(freelancerId);
        // return work experience
        return workExperienceList.stream()
                .map(workExperience -> WorkExperienceResponse.builder()
                        .id(workExperience.getId())
                        .title(workExperience.getTitle())
                        .company(workExperience.getCompany())
                        .imageUrl(workExperience.getImageUrl())
                        .endDate(workExperience.getEndDate())
                        .isCurrent(workExperience.isCurrent())
                        .freelancerId(workExperience.getFreelancerId())
                        .build())
                .toList();
    }

    @Override
    public WorkExperienceResponse updateWorkExperience(String id, WorkExperienceRequest workExperience) {
        // check start date is before end date
        if (workExperience.getStartDate().after(workExperience.getEndDate())) {
            throw new CustomException(
                    "Start date is after end date",
                    400,
                    Map.of(
                            "startDate", workExperience.getStartDate(),
                            "endDate", workExperience.getEndDate()
                    )
            );
        }
        // check if start date is in the future
        if (workExperience.getStartDate().after(new java.util.Date())) {
            throw new CustomException(
                    "Start date is in the future",
                    400,
                    Map.of(
                            "startDate", workExperience.getStartDate()
                    )
            );
        }
        // get work experience by id
        WorkExperience workExperienceModel = workExperienceRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException(
                                "Work experience not found",
                                404,
                                Map.of(
                                        "id", id)
                        )
                );
        // update work experience
        workExperienceModel.setTitle(workExperience.getTitle());
        workExperienceModel.setCompany(workExperience.getCompany());
        workExperienceModel.setCity(workExperience.getCity());
        workExperienceModel.setDescription(workExperience.getDescription());
        workExperienceModel.setImageUrl(workExperience.getImageUrl());
        workExperienceModel.setStartDate(workExperience.getStartDate());
        workExperienceModel.setEndDate(workExperience.getEndDate());
        workExperienceModel.setCurrent(workExperience.isCurrent());
        workExperienceModel.setFreelancerId(workExperience.getFreelancerId());
        // save work experience to database
        WorkExperience saved = workExperienceRepository.save(workExperienceModel);
        // return work experience
        return WorkExperienceResponse.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .company(saved.getCompany())
                .imageUrl(saved.getImageUrl())
                .endDate(saved.getEndDate())
                .isCurrent(saved.isCurrent())
                .freelancerId(saved.getFreelancerId())
                .build();
    }

    @Override
    public void deleteWorkExperience(String id) {
        // check if work experience exists
        if (!workExperienceRepository.existsById(id)) {
            throw new CustomException(
                    "Work experience not found",
                    404,
                    Map.of(
                            "id", id
                    )
            );
        }
        // delete work experience
        workExperienceRepository.deleteById(id);
    }

    @Override
    public List<WorkExperienceResponse> getAllWorkExperience() {
        // get all work experience
        List<WorkExperience> workExperienceList = workExperienceRepository.findAll();
        // return work experience
        return workExperienceList.stream()
                .map(workExperience -> WorkExperienceResponse.builder()
                        .id(workExperience.getId())
                        .title(workExperience.getTitle())
                        .company(workExperience.getCompany())
                        .imageUrl(workExperience.getImageUrl())
                        .endDate(workExperience.getEndDate())
                        .isCurrent(workExperience.isCurrent())
                        .freelancerId(workExperience.getFreelancerId())
                        .build())
                .toList();
    }

    @Override
    public WorkExperienceDetailsResponse getWorkExperienceDetailsById(String id) {
        // get work experience by id
        WorkExperience workExperience = workExperienceRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException(
                                "Work experience not found",
                                404,
                                Map.of(
                                        "id", id
                                )
                        )
                );
        // return work experience
        return WorkExperienceDetailsResponse.builder()
                .id(workExperience.getId())
                .title(workExperience.getTitle())
                .company(workExperience.getCompany())
                .city(workExperience.getCity())
                .description(workExperience.getDescription())
                .imageUrl(workExperience.getImageUrl())
                .startDate(workExperience.getStartDate())
                .endDate(workExperience.getEndDate())
                .isCurrent(workExperience.isCurrent())
                .freelancerId(workExperience.getFreelancerId())
                .build();
    }

    @Override
    public WorkExperience getWorkExperienceModelById(String id) {
        // get work experience by id
        return workExperienceRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException(
                                "Work experience not found",
                                404,
                                Map.of(
                                        "id", id
                                )
                        )
                );
    }
}
