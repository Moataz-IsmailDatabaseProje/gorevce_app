package com.gorevce.task_service.controller;

import com.gorevce.task_service.dto.request.ApplicationRequest;
import com.gorevce.task_service.exception.CustomException;
import com.gorevce.task_service.service.ApplicationService;
import com.gorevce.task_service.util.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks/application")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // create application
    @Operation(summary = "Create application",description = "Create application")
    @PostMapping("/create-application")
    public ResponseEntity<?> createApplication(@RequestBody ApplicationRequest applicationRequest) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Application created successfully")
                            .httpStatusCode(200)
                            .response(applicationService.createApplication(applicationRequest))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }

    // update application
    @Operation(summary = "Update application",description = "Update application")
    @PutMapping("/update-application")
    public ResponseEntity<?> updateApplication(@RequestBody ApplicationRequest applicationRequest,@RequestParam String applicationId) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Application updated successfully")
                            .httpStatusCode(200)
                            .response(applicationService.updateApplication(applicationRequest,applicationId))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }

    // delete application
    @Operation(summary = "Delete application",description = "Delete application")
    @DeleteMapping("/delete-application")
    public ResponseEntity<?> deleteApplication(@RequestParam String applicationId) {
        try {
            applicationService.deleteApplication(applicationId);
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Application deleted successfully")
                            .httpStatusCode(200)
                            .response(null)
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }
    // get application by id
    @Operation(summary = "Get application by id",description = "Get application by id")
    @GetMapping("/get-application")
    public ResponseEntity<?> getApplication(@RequestParam String applicationId) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Application retrieved successfully")
                            .httpStatusCode(200)
                            .response(applicationService.getApplication(applicationId))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }

    // get all applications
    @Operation(summary = "Get all applications for super admin",description = "Get all applications for super admin")
    @GetMapping("/get-all-applications")
    public ResponseEntity<?> getAllApplications() {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Applications retrieved successfully")
                            .httpStatusCode(200)
                            .response(applicationService.getAllApplications())
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }

    // get applications by task id
    @Operation(summary = "Get applications by task id for company",description = "Get applications by task id for company")
    @GetMapping("/get-applications-by-task-id")
    public ResponseEntity<?> getApplicationsByTaskId(@RequestParam String taskId) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Applications retrieved successfully")
                            .httpStatusCode(200)
                            .response(applicationService.getApplicationsByTaskId(taskId))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }

    // get applications by freelancer id
    @Operation(summary = "Get applications by freelancer id for freelancer",description = "Get applications by freelancer id for freelancer")
    @GetMapping("/get-applications-by-freelancer-id")
    public ResponseEntity<?> getApplicationsByFreelancerId(@RequestParam String freelancerId) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Applications retrieved successfully")
                            .httpStatusCode(200)
                            .response(applicationService.getApplicationsByFreelancerId(freelancerId))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }


}
