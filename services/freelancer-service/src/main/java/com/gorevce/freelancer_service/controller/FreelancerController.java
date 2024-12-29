package com.gorevce.freelancer_service.controller;


import com.gorevce.freelancer_service.dto.request.FreelancerRequest;
import com.gorevce.freelancer_service.exception.CustomException;
import com.gorevce.freelancer_service.service.FreelancerService;
import com.gorevce.freelancer_service.service.ProjectService;
import com.gorevce.freelancer_service.util.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/freelancer")
public class FreelancerController {

    @Autowired
    private FreelancerService freelancerService;

    // create freelancer
    @PostMapping("/create-freelancer")
    @Operation(summary = "Create freelancer", description = "Create freelancer")
    public ResponseEntity<?> createFreelancer(@RequestBody FreelancerRequest freelancerRequest) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Freelancer created successfully")
                            .httpStatusCode(200)
                            .response(freelancerService.createFreelancer(freelancerRequest))
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
    // get freelancer
    @GetMapping("/get-freelancer")
    @Operation(summary = "Get freelancer", description = "Get freelancer")
    public ResponseEntity<?> getFreelancerById(@RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Freelancer retrieved successfully")
                            .httpStatusCode(200)
                            .response(freelancerService.getFreelancerById(id))
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
    // update freelancer
    @PutMapping("/update-freelancer")
    @Operation(summary = "Update freelancer", description = "Update freelancer")
    public ResponseEntity<?> updateFreelancer(@RequestParam String id, @RequestBody FreelancerRequest freelancerRequest) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Freelancer updated successfully")
                            .httpStatusCode(200)
                            .response(freelancerService.updateFreelancer(id, freelancerRequest))
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
    // delete freelancer
    @DeleteMapping("/delete-freelancer")
    @Operation(summary = "Delete freelancer", description = "Delete freelancer")
    public ResponseEntity<?> deleteFreelancerById(@RequestParam String id) {
        try {
            freelancerService.deleteFreelancerById(id);
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Freelancer deleted successfully")
                            .httpStatusCode(200)
                            .response(null)
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
    // restore freelancer
    @PutMapping("/restore-freelancer")
    @Operation(summary = "Restore freelancer", description = "Restore freelancer")
    public ResponseEntity<?> restoreFreelancerById(@RequestParam String id) {
        try {
            freelancerService.restoreFreelancerById(id);
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Freelancer restored successfully")
                            .httpStatusCode(200)
                            .response(null)
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
    // get all freelancers
    @GetMapping("/get-all-freelancers")
    @Operation(summary = "Get all freelancers", description = "Get all freelancers")
    public ResponseEntity<?> getAllFreelancers() {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Freelancers retrieved successfully")
                            .httpStatusCode(200)
                            .response(freelancerService.getAllFreelancers())
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
    // get freelancer details
    @GetMapping("/get-freelancer-details")
    @Operation(summary = "Get freelancer details", description = "Get freelancer details")
    public ResponseEntity<?> getFreelancerDetailsById(@RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Freelancer details retrieved successfully")
                            .httpStatusCode(200)
                            .response(freelancerService.getFreelancerDetailsById(id))
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

    // get user by id
    @GetMapping("/get-credentials")
    @Operation(summary = "Get user by id", description = "Get user by id")
    public ResponseEntity<?> getUserById(@RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("User retrieved successfully")
                            .httpStatusCode(200)
                            .response(freelancerService.getUserById(id))
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
    // get freelancer address by id
    @GetMapping("/get-address")
    @Operation(summary = "Get address by id", description = "Get address by id")
    public ResponseEntity<?> getAddressById(@RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Address retrieved successfully")
                            .httpStatusCode(200)
                            .response(freelancerService.getAddressById(id))
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

    // get freelancer work experience by id
    @GetMapping("/get-work-experience")
    @Operation(summary = "Get work experience by id", description = "Get work experience by id")
    public ResponseEntity<?> getWorkExperienceById(@RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Work experience retrieved successfully")
                            .httpStatusCode(200)
                            .response(freelancerService.getWorkExperienceById(id))
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
    // get freelancer education by id
    @GetMapping("/get-education")
    @Operation(summary = "Get education by id", description = "Get education by id")
    public ResponseEntity<?> getEducationById(@RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Education retrieved successfully")
                            .httpStatusCode(200)
                            .response(freelancerService.getEducationById(id))
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
    // get freelancer skills by id
    @GetMapping("/get-skills")
    @Operation(summary = "Get skills by id", description = "Get skills by id")
    public ResponseEntity<?> getSkillsById(@RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Skills retrieved successfully")
                            .httpStatusCode(200)
                            .response(freelancerService.getSkillsById(id))
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
    // get freelancer certificates by id
    @GetMapping("/get-certificates")
    @Operation(summary = "Get certificates by id", description = "Get certificates by id")
    public ResponseEntity<?> getCertificateById(@RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Certificates retrieved successfully")
                            .httpStatusCode(200)
                            .response(freelancerService.getCertificateById(id))
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
    // get freelancer projects by id
    @GetMapping("/get-projects")
    @Operation(summary = "Get projects by id", description = "Get projects by id")
    public ResponseEntity<?> getProjectById(@RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Projects retrieved successfully")
                            .httpStatusCode(200)
                            .response(freelancerService.getProjectById(id))
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
    // get freelancer reviews by id
    @GetMapping("/get-reviews")
    @Operation(summary = "Get reviews by id", description = "Get reviews by id")
    public ResponseEntity<?> getReviewById(@RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Reviews retrieved successfully")
                            .httpStatusCode(200)
                            .response(freelancerService.getReviewById(id))
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
    // get freelancer social media by id
    @GetMapping("/get-social-media")
    @Operation(summary = "Get social media by id", description = "Get social media by id")
    public ResponseEntity<?> getSocialMediaById(@RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Social media retrieved successfully")
                            .httpStatusCode(200)
                            .response(freelancerService.getSocialMediaById(id))
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
    @GetMapping("/rest-template/exists/{id}")
    @Operation(summary = "Check if freelancer exists for rest tamplate", description = "Check if freelancer exists for rest template")
    public ResponseEntity<Boolean> exists(@PathVariable String id) {
        return ResponseEntity.ok(freelancerService.doesFreelancerExist(id));
    }
}
