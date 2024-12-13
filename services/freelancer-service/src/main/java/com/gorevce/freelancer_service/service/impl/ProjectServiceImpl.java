package com.gorevce.freelancer_service.service.impl;

import com.gorevce.freelancer_service.dto.request.ProjectRequest;
import com.gorevce.freelancer_service.dto.response.ProjectDetailsResponse;
import com.gorevce.freelancer_service.dto.response.ProjectResponse;
import com.gorevce.freelancer_service.exception.CustomException;
import com.gorevce.freelancer_service.model.Project;
import com.gorevce.freelancer_service.repository.ProjectRepository;
import com.gorevce.freelancer_service.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ProjectResponse createProject(ProjectRequest projectDto) {
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
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new CustomException("Project not found",404, Map.of("projectId",projectId))
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
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new CustomException("Project not found",404, Map.of("projectId",projectId))
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
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new CustomException("Project not found",404, Map.of("projectId",projectId))
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
                orElseThrow(() -> new CustomException("Project not found",404, Map.of("projectId",projectId)));
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
}
