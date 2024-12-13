package com.gorevce.freelancer_service.controller;


import com.gorevce.freelancer_service.dto.request.ProjectRequest;
import com.gorevce.freelancer_service.exception.CustomException;
import com.gorevce.freelancer_service.service.ProjectService;
import com.gorevce.freelancer_service.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/freelancer/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    // create project
    @PostMapping("/create-project")
    public ResponseEntity<?> createProject(@RequestBody ProjectRequest projectRequest) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Project created successfully")
                            .httpStatusCode(200)
                            .response(projectService.createProject(projectRequest))
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

    // get project by id
    @PostMapping("/get-project")
    public ResponseEntity<?> getProjectById(@RequestBody String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Project retrieved successfully")
                            .httpStatusCode(200)
                            .response(projectService.getProject(id))
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

    // update project
    @PutMapping("/update-project")
    public ResponseEntity<?> updateProject(@RequestBody ProjectRequest projectRequest, @RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Project updated successfully")
                            .httpStatusCode(200)
                            .response(projectService.updateProject(id, projectRequest))
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

    // delete project
    @DeleteMapping("/delete-project")
    public ResponseEntity<?> deleteProject(@RequestParam String id) {
        try {
            projectService.deleteProject(id);
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Project deleted successfully")
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

    // get all projects
    @GetMapping("/get-projects")
    public ResponseEntity<?> getProjects() {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Projects retrieved successfully")
                            .httpStatusCode(200)
                            .response(projectService.getProjects())
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

    // get all projects by freelancer
    @GetMapping("/get-projects-by-freelancer")
    public ResponseEntity<?> getProjectsByFreelancer(@RequestParam String freelancerId) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Projects retrieved successfully")
                            .httpStatusCode(200)
                            .response(projectService.getProjectsByFreelancer(freelancerId))
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

    // get project details
    @GetMapping("/get-project-details")
    public ResponseEntity<?> getProjectDetails(@RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Project details retrieved successfully")
                            .httpStatusCode(200)
                            .response(projectService.getProjectDetails(id))
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
