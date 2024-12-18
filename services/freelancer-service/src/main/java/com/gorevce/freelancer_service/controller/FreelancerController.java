package com.gorevce.freelancer_service.controller;


import com.gorevce.freelancer_service.dto.request.FreelancerRequest;
import com.gorevce.freelancer_service.exception.CustomException;
import com.gorevce.freelancer_service.service.FreelancerService;
import com.gorevce.freelancer_service.service.ProjectService;
import com.gorevce.freelancer_service.util.ApiResponse;
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
    // get freelancer address by id
    // get freelancer work experience by id
    @GetMapping("/get-work-experience")
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
}
