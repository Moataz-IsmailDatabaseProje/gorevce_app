package com.gorevce.freelancer_service.controller;

import com.gorevce.freelancer_service.dto.request.EducationRequest;
import com.gorevce.freelancer_service.exception.CustomException;
import com.gorevce.freelancer_service.service.EducationService;
import com.gorevce.freelancer_service.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/freelancer/educations")
public class EducationController {

    @Autowired
    private EducationService educationService;

    // create education
    @PostMapping("/create-education")
    public ResponseEntity<?> createEducation(@RequestBody EducationRequest educationRequest) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Education created successfully")
                            .httpStatusCode(200)
                            .response(educationService.createEducation(educationRequest))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            ApiResponse.builder()
                                    .message(e.getMessage())
                                    .httpStatusCode(e.getHttpStatusCode())
                                    .response(e.getDetails())
                                    .build()
                    );

        }
    }

    // get education by id
    @GetMapping("/get-education")
    public ResponseEntity<?> getEducationById(@RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Education retrieved successfully")
                            .httpStatusCode(200)
                            .response(educationService.getEducation(id))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            ApiResponse.builder()
                                    .message(e.getMessage())
                                    .httpStatusCode(e.getHttpStatusCode())
                                    .response(e.getDetails())
                                    .build()
                    );

        }
    }

    // get all educations by freelancer id
    @GetMapping("/get-educations")
    public ResponseEntity<?> getEducationsByFreelancerId(@RequestParam String freelancerId) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Educations retrieved successfully")
                            .httpStatusCode(200)
                            .response(educationService.getEducationsByFreelancer(freelancerId))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            ApiResponse.builder()
                                    .message(e.getMessage())
                                    .httpStatusCode(e.getHttpStatusCode())
                                    .response(e.getDetails())
                                    .build()
                    );

        }
    }

    // update education
    @PutMapping("/update-education")
    public ResponseEntity<?> updateEducation(@RequestParam String id, @RequestBody EducationRequest educationRequest) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Education updated successfully")
                            .httpStatusCode(200)
                            .response(educationService.updateEducation(id, educationRequest))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            ApiResponse.builder()
                                    .message(e.getMessage())
                                    .httpStatusCode(e.getHttpStatusCode())
                                    .response(e.getDetails())
                                    .build()
                    );

        }
    }

    // delete education
    @DeleteMapping("/delete-education")
    public ResponseEntity<?> deleteEducation(@RequestParam String id) {
        try {
            educationService.deleteEducation(id);
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Education deleted successfully")
                            .httpStatusCode(200)
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            ApiResponse.builder()
                                    .message(e.getMessage())
                                    .httpStatusCode(e.getHttpStatusCode())
                                    .response(e.getDetails())
                                    .build()
                    );

        }
    }

    // get all educations
    @GetMapping("/get-all-educations")
    public ResponseEntity<?> getAllEducations() {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Educations retrieved successfully")
                            .httpStatusCode(200)
                            .response(educationService.getEducations())
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            ApiResponse.builder()
                                    .message(e.getMessage())
                                    .httpStatusCode(e.getHttpStatusCode())
                                    .response(e.getDetails())
                                    .build()
                    );

        }
    }

    // get education details
    @GetMapping("/get-education-details")
    public ResponseEntity<?> getEducationDetails(@RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Education details retrieved successfully")
                            .httpStatusCode(200)
                            .response(educationService.getEducationDetails(id))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            ApiResponse.builder()
                                    .message(e.getMessage())
                                    .httpStatusCode(e.getHttpStatusCode())
                                    .response(e.getDetails())
                                    .build()
                    );

        }
    }


}
