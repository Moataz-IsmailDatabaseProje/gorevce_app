package com.gorevce.freelancer_service.controller;


import com.gorevce.freelancer_service.dto.request.WorkExperienceRequest;
import com.gorevce.freelancer_service.exception.CustomException;
import com.gorevce.freelancer_service.service.WorkExperienceService;
import com.gorevce.freelancer_service.util.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/freelancer/work-experience")
public class WorkExperienceController {

    @Autowired
    private WorkExperienceService workExperienceService;

    // create work experience
    @PostMapping("/create-work-experience")
    @Operation(summary = "Create work experience", description = "Create work experience")
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

    // get work experience by id
    @GetMapping("/get-work-experience")
    @Operation(summary = "Get work experience by id", description = "Get work experience by id")
    public ResponseEntity<?> getWorkExperienceById(@RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Work experience retrieved successfully")
                            .httpStatusCode(200)
                            .response(workExperienceService.getWorkExperienceById(id))
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

    // update work experience
    @PutMapping("/update-work-experience")
    @Operation(summary = "Update work experience", description = "Update work experience")
    public ResponseEntity<?> updateWorkExperience(@RequestBody WorkExperienceRequest workExperience, @RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Work experience updated successfully")
                            .httpStatusCode(200)
                            .response(workExperienceService.updateWorkExperience(id, workExperience))
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

    // get all work experiences by freelancer id
    @GetMapping("/get-work-experiences")
    @Operation(summary = "Get work experiences by freelancer id", description = "Get work experiences by freelancer id")
    public ResponseEntity<?> getWorkExperiencesByFreelancerId(@RequestParam String freelancerId) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Work experiences retrieved successfully")
                            .httpStatusCode(200)
                            .response(workExperienceService.getWorkExperienceByFreelancerId(freelancerId))
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

    // delete work experience
    @DeleteMapping("/delete-work-experience")
    @Operation(summary = "Delete work experience", description = "Delete work experience")
    public ResponseEntity<?> deleteWorkExperience(@RequestParam String id) {
        try {
            workExperienceService.deleteWorkExperience(id);
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Work experience deleted successfully")
                            .httpStatusCode(200)
                            .response(null)
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

    // get all work experiences
    @GetMapping("/get-all-work-experiences")
    @Operation(summary = "Get all work experiences", description = "Get all work experiences")
    public ResponseEntity<?> getAllWorkExperiences() {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Work experiences retrieved successfully")
                            .httpStatusCode(200)
                            .response(workExperienceService.getAllWorkExperience())
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

    // get work experience details by id
    @GetMapping("/get-work-experience-details")
    @Operation(summary = "Get work experience details by id", description = "Get work experience details by id")
    public ResponseEntity<?> getWorkExperienceDetailsById(@RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Work experience details retrieved successfully")
                            .httpStatusCode(200)
                            .response(workExperienceService.getWorkExperienceDetailsById(id))
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
