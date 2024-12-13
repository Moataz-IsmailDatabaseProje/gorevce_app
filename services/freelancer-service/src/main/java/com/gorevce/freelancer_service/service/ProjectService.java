package com.gorevce.freelancer_service.service;

import com.gorevce.freelancer_service.dto.request.ProjectRequest;
import com.gorevce.freelancer_service.dto.response.ProjectDetailsResponse;
import com.gorevce.freelancer_service.dto.response.ProjectResponse;

import java.util.List;

public interface ProjectService {

        public ProjectResponse createProject(ProjectRequest projectDto);

        public ProjectResponse getProject(String projectId);

        public ProjectResponse updateProject(String projectId, ProjectRequest projectDto);

        public void deleteProject(String projectId);

        public List<ProjectResponse> getProjects();

        public List<ProjectResponse> getProjectsByFreelancer(String freelancerId);

        public ProjectDetailsResponse getProjectDetails(String projectId);
}
