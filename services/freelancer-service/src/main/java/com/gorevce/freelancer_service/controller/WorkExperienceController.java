package com.gorevce.freelancer_service.controller;


import com.gorevce.freelancer_service.dto.request.WorkExperienceRequest;
import com.gorevce.freelancer_service.exception.CustomException;
import com.gorevce.freelancer_service.service.WorkExperienceService;
import com.gorevce.freelancer_service.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/freelancer/work-experience")
public class WorkExperienceController {

    @Autowired
    private WorkExperienceService workExperienceService;

    // create work experience
    @PostMapping("/create-work-experience")
    public ResponseEntity<?> createWorkExperience(@RequestBody WorkExperienceRequest workExperience) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Work experience created successfully")
                            .httpStatusCode(200)
                            .response(workExperienceService.createWorkExperience(workExperience))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.status(e.getHttpStatusCode()).body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build());
        }
    }
}
