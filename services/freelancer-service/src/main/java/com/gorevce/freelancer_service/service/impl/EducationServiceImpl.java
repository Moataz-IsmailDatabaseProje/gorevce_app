package com.gorevce.freelancer_service.service.impl;

import com.gorevce.freelancer_service.dto.request.EducationRequest;
import com.gorevce.freelancer_service.dto.response.EducationDetailsResponse;
import com.gorevce.freelancer_service.dto.response.EducationResponse;
import com.gorevce.freelancer_service.exception.CustomException;
import com.gorevce.freelancer_service.model.Education;
import com.gorevce.freelancer_service.repository.EducationRepository;
import com.gorevce.freelancer_service.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    private EducationRepository educationRepository;

    @Override
    public EducationResponse createEducation(EducationRequest educationDto) {
        // check if start date is after end date
        if (educationDto.getStartDate().after(educationDto.getEndDate())) {
            throw new CustomException(
                    "Start date cannot be after end date",
                    400,
                    Map.of(
                        "startDate", educationDto.getStartDate(),
                        "endDate", educationDto.getEndDate()

                    )
            );
        }
        // check if start date is after current date
        if (educationDto.getStartDate().after(new Date())) {
            throw new CustomException(
                    "Start date cannot be in the future",
                    400,
                    Map.of(
                        "startDate", educationDto.getStartDate()
                    )
            );
        }
        // create education
        Education education = Education.builder()
                .school(educationDto.getSchool())
                .degree(educationDto.getDegree())
                .fieldOfStudy(educationDto.getFieldOfStudy())
                .startDate(educationDto.getStartDate())
                .endDate(educationDto.getEndDate())
                .isCurrentlyStudying(educationDto.getIsCurrentlyStudying())
                .grade(educationDto.getGrade())
                .description(educationDto.getDescription())
                .imageUrl(educationDto.getImageUrl())
                .freelancerId(educationDto.getFreelancerId())
                .build();
        // save education
        Education savedEducation = educationRepository.save(education); // save education
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(savedEducation.getStartDate());
        String startYear = String.valueOf(calendar.get(Calendar.YEAR));
        calendar.setTime(savedEducation.getEndDate());
        String endYear = String.valueOf(calendar.get(Calendar.YEAR));
        // return saved education
        return EducationResponse.builder()
                .id(savedEducation.getId())
                .school(savedEducation.getSchool())
                .degree(savedEducation.getDegree())
                .fieldOfStudy(savedEducation.getFieldOfStudy())
                .startYear(startYear)
                .endYear(endYear)
                .isCurrentlyStudying(savedEducation.getIsCurrentlyStudying())
                .freelancerId(savedEducation.getFreelancerId())
                .build();
    }

    @Override
    public EducationResponse getEducation(String educationId) {
        // get education
        Education education = educationRepository.findById(educationId)
                .orElseThrow(
                    () -> new CustomException(
                        "Education not found",
                        404,
                        Map.of(
                                "educationId", educationId
                        )
                    )
                );
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(education.getStartDate());
        String startYear = String.valueOf(calendar.get(Calendar.YEAR));
        calendar.setTime(education.getEndDate());
        String endYear = String.valueOf(calendar.get(Calendar.YEAR));
        // return education
        return EducationResponse.builder()
                .id(education.getId())
                .school(education.getSchool())
                .degree(education.getDegree())
                .fieldOfStudy(education.getFieldOfStudy())
                .startYear(startYear)
                .endYear(endYear)
                .isCurrentlyStudying(education.getIsCurrentlyStudying())
                .freelancerId(education.getFreelancerId())
                .build();
    }

    @Override
    public EducationResponse updateEducation(String educationId, EducationRequest educationDto) {
        // check if start date is after end date
        if (educationDto.getStartDate().after(educationDto.getEndDate())) {
            throw new CustomException(
                    "Start date cannot be after end date",
                    400,
                    Map.of(
                        "startDate", educationDto.getStartDate(),
                        "endDate", educationDto.getEndDate()
                    )
            );
        }
        // check if start date is after current date
        if (educationDto.getStartDate().after(new Date())) {
            throw new CustomException(
                    "Start date cannot be in the future",
                    400,
                    Map.of(
                        "startDate", educationDto.getStartDate()
                    )
            );
        }
        // get education
        Education education = educationRepository.findById(educationId)
                .orElseThrow(
                    () -> new CustomException(
                        "Education not found",
                        404,
                        Map.of(
                            "educationId", educationId
                        )
                    )
                );
        // update education
        education.setSchool(educationDto.getSchool());
        education.setDegree(educationDto.getDegree());
        education.setFieldOfStudy(educationDto.getFieldOfStudy());
        education.setStartDate(educationDto.getStartDate());
        education.setEndDate(educationDto.getEndDate());
        education.setIsCurrentlyStudying(educationDto.getIsCurrentlyStudying());
        education.setGrade(educationDto.getGrade());
        education.setDescription(educationDto.getDescription());
        education.setImageUrl(educationDto.getImageUrl());
        education.setFreelancerId(educationDto.getFreelancerId());
        // save education
        Education savedEducation = educationRepository.save(education);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(savedEducation.getStartDate());
        String startYear = String.valueOf(calendar.get(Calendar.YEAR));
        calendar.setTime(savedEducation.getEndDate());
        String endYear = String.valueOf(calendar.get(Calendar.YEAR));
        // return saved education
        return EducationResponse.builder()
                .id(savedEducation.getId())
                .school(savedEducation.getSchool())
                .degree(savedEducation.getDegree())
                .fieldOfStudy(savedEducation.getFieldOfStudy())
                .startYear(startYear)
                .endYear(endYear)
                .isCurrentlyStudying(savedEducation.getIsCurrentlyStudying())
                .freelancerId(savedEducation.getFreelancerId())
                .build();
    }

    @Override
    public void deleteEducation(String educationId) {
        // get education
        Education education = educationRepository.findById(educationId)
                .orElseThrow(
                    () -> new CustomException(
                        "Education not found",
                        404,
                        Map.of(
                            "educationId", educationId
                        )
                    )
                );
        // delete education
        educationRepository.delete(education);
    }

    @Override
    public List<EducationResponse> getEducations() {
        // get educations
        List<Education> educations = educationRepository.findAll();
        // return educations
        return educations.stream()
                .map(education -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(education.getStartDate());
                    String startYear = String.valueOf(calendar.get(Calendar.YEAR));
                    calendar.setTime(education.getEndDate());
                    String endYear = String.valueOf(calendar.get(Calendar.YEAR));
                    return EducationResponse.builder()
                            .id(education.getId())
                            .school(education.getSchool())
                            .degree(education.getDegree())
                            .fieldOfStudy(education.getFieldOfStudy())
                            .startYear(startYear)
                            .endYear(endYear)
                            .isCurrentlyStudying(education.getIsCurrentlyStudying())
                            .freelancerId(education.getFreelancerId())
                            .build();
                })
                .toList();
    }

    @Override
    public List<EducationResponse> getEducationsByFreelancer(String freelancerId) {
        // get educations
        List<Education> educations = educationRepository.findByFreelancerId(freelancerId);
        // return educations
        return educations.stream()
                .map(education -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(education.getStartDate());
                    String startYear = String.valueOf(calendar.get(Calendar.YEAR));
                    calendar.setTime(education.getEndDate());
                    String endYear = String.valueOf(calendar.get(Calendar.YEAR));
                    return EducationResponse.builder()
                            .id(education.getId())
                            .school(education.getSchool())
                            .degree(education.getDegree())
                            .fieldOfStudy(education.getFieldOfStudy())
                            .startYear(startYear)
                            .endYear(endYear)
                            .isCurrentlyStudying(education.getIsCurrentlyStudying())
                            .freelancerId(education.getFreelancerId())
                            .build();
                })
                .toList();
    }

    @Override
    public EducationDetailsResponse getEducationDetails(String educationId) {
        // get education
        Education education = educationRepository.findById(educationId)
                .orElseThrow(
                    () -> new CustomException(
                        "Education not found",
                        404,
                        Map.of(
                            "educationId", educationId
                        )
                    )
                );
        // return education
        return EducationDetailsResponse.builder()
                .id(education.getId())
                .school(education.getSchool())
                .degree(education.getDegree())
                .fieldOfStudy(education.getFieldOfStudy())
                .startDate(education.getStartDate())
                .endDate(education.getEndDate())
                .isCurrentlyStudying(education.getIsCurrentlyStudying())
                .grade(education.getGrade())
                .description(education.getDescription())
                .imageUrl(education.getImageUrl())
                .freelancerId(education.getFreelancerId())
                .build();
    }

    @Override
    public Education getEducationModelById(String id) {
        return educationRepository.findById(id)
                .orElseThrow(
                    () -> new CustomException(
                        "Education not found",
                        404,
                        Map.of(
                            "educationId", id
                        )
                    )
                );
    }
}
