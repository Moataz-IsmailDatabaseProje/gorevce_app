package com.gorevce.freelancer_service.service.impl;

import com.gorevce.freelancer_service.dto.request.ProjectRequest;
import com.gorevce.freelancer_service.dto.response.ProjectDetailsResponse;
import com.gorevce.freelancer_service.dto.response.ProjectResponse;
import com.gorevce.freelancer_service.event.added.ProjectAddedEvent;
import com.gorevce.freelancer_service.exception.CustomException;
import com.gorevce.freelancer_service.model.Project;
import com.gorevce.freelancer_service.repository.ProjectRepository;
import com.gorevce.freelancer_service.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public ProjectResponse createProject(ProjectRequest projectDto) {
        // check date range
        if(projectDto.getProjectStartDate().after(projectDto.getProjectEndDate())){
            throw new CustomException(
                    "Project start date must be before project end date",
                    400,
                    Map.of(
                            "projectStartDate",projectDto.getProjectStartDate().toString(),
                            "projectEndDate",projectDto.getProjectEndDate().toString()
                    )
            );
        }
        // check project is not in the future
        if(projectDto.getProjectStartDate().after(new Date())){
            throw new CustomException(
                    "Project start date must be in the past",
                    400,
                    Map.of(
                            "projectStartDate",projectDto.getProjectStartDate().toString()
                    )
            );
        }
        // check freelancer id is not null
        if(projectDto.getFreelancerId() == null){
            throw new CustomException(
                    "Freelancer id is required",
                    400,
                    Map.of(
                            "freelancerId","null"
                    )
            );
        }



        // create project
        Project project = Project.builder()
                .name(projectDto.getName())
                .description(projectDto.getDescription())
                .imageUrl(projectDto.getImageUrl())
                .projectUrl(projectDto.getProjectUrl())
                .freelancerId(projectDto.getFreelancerId())
                .projectType(projectDto.getProjectType())
                .projectStatus(projectDto.getProjectStatus())
                .projectStartDate(projectDto.getProjectStartDate())
                .projectEndDate(projectDto.getProjectEndDate())
                .build();
        // save project
        Project savedProject = projectRepository.save(project);
        // publish project added event
        eventPublisher.publishEvent(
                new ProjectAddedEvent(
                        this,
                        savedProject.getFreelancerId(),
                        savedProject.getId()
                )
        );
        // return project
        return ProjectResponse.builder()
                .id(savedProject.getId())
                .name(savedProject.getName())
                .description(savedProject.getDescription())
                .imageUrl(savedProject.getImageUrl())
                .projectUrl(savedProject.getProjectUrl())
                .freelancerId(savedProject.getFreelancerId())
                .build();
    }

    @Override
    public ProjectResponse getProject(String projectId) {
        // get project
        Project project = projectRepository.findById(projectId)
                .orElseThrow(
                        () -> new CustomException(
                                "Project not found",
                                404,
                                Map.of(
                                        "projectId",projectId
                                )
                        )
                );
        // return project
        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .imageUrl(project.getImageUrl())
                .projectUrl(project.getProjectUrl())
                .freelancerId(project.getFreelancerId())
                .build();
    }

    @Override
    public ProjectResponse updateProject(String projectId, ProjectRequest projectDto) {
        // get project
        Project project = projectRepository.findById(projectId)
                .orElseThrow(
                        () -> new CustomException(
                                "Project not found",
                                404,
                                Map.of(
                                        "projectId",projectId
                                )
                        )
                );
        // update project
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setImageUrl(projectDto.getImageUrl());
        project.setProjectUrl(projectDto.getProjectUrl());
        project.setFreelancerId(projectDto.getFreelancerId());
        project.setProjectType(projectDto.getProjectType());
        project.setProjectStatus(projectDto.getProjectStatus());
        project.setProjectStartDate(projectDto.getProjectStartDate());
        project.setProjectEndDate(projectDto.getProjectEndDate());
        // save project
        Project savedProject = projectRepository.save(project);
        // return project
        return ProjectResponse.builder()
                .id(savedProject.getId())
                .name(savedProject.getName())
                .description(savedProject.getDescription())
                .imageUrl(savedProject.getImageUrl())
                .projectUrl(savedProject.getProjectUrl())
                .freelancerId(savedProject.getFreelancerId())
                .build();

    }

    @Override
    public void deleteProject(String projectId) {
        // get project
        Project project = projectRepository.findById(projectId)
                .orElseThrow(
                        () -> new CustomException(
                                "Project not found",
                                404,
                                Map.of(
                                        "projectId",projectId
                                )
                        )
                );
        // delete project
        projectRepository.delete(project);
    }

    @Override
    public List<ProjectResponse> getProjects() {
        // get all projects
        List<Project> projects = projectRepository.findAll();
        // return projects
        return projects.stream().map(project -> ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .imageUrl(project.getImageUrl())
                .projectUrl(project.getProjectUrl())
                .freelancerId(project.getFreelancerId())
                .build()
        ).toList();
    }

    @Override
    public List<ProjectResponse> getProjectsByFreelancer(String freelancerId) {
        // get all projects by freelancer
        List<Project> projects = projectRepository.findByFreelancerId(freelancerId);
        // return projects
        return projects.stream().map(project -> ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .imageUrl(project.getImageUrl())
                .projectUrl(project.getProjectUrl())
                .freelancerId(project.getFreelancerId())
                .build()
        ).toList();
    }

    @Override
    public ProjectDetailsResponse getProjectDetails(String projectId) {
        // get project
        Project project = projectRepository.findById(projectId).
                orElseThrow(
                        () -> new CustomException(
                                "Project not found",
                                404,
                                Map.of(
                                        "projectId",projectId
                                )
                        )
                );
        // return project details
        return ProjectDetailsResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .imageUrl(project.getImageUrl())
                .projectUrl(project.getProjectUrl())
                .projectType(project.getProjectType())
                .projectStatus(project.getProjectStatus())
                .projectStartDate(project.getProjectStartDate())
                .projectEndDate(project.getProjectEndDate())
                .freelancerId(project.getFreelancerId())
                .build();
    }

    @Override
    public Project getProjectModelById(String id) {
        // get project by id
        return projectRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException(
                                "Project not found",
                                404,
                                Map.of(
                                        "id",id
                                )
                        )
                );
    }
}
