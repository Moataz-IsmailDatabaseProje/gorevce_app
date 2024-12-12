package com.gorevce.freelancer_service.service;

import com.gorevce.freelancer_service.dto.request.EducationRequest;
import com.gorevce.freelancer_service.dto.response.EducationDetailsResponse;
import com.gorevce.freelancer_service.dto.response.EducationResponse;

import java.util.List;

public interface EducationService {
    
    public EducationResponse createEducation(EducationRequest educationDto);
    
    public EducationResponse getEducation(String educationId);
    
    public EducationResponse updateEducation(String educationId, EducationRequest educationDto);
    
    public void deleteEducation(String educationId);
    
    public List<EducationResponse> getEducations();
    
    public List<EducationResponse> getEducationsByFreelancer(String freelancerId);
    
    public EducationDetailsResponse getEducationDetails(String educationId);
    
    
}
