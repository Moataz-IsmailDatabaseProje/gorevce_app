package com.gorevce.freelancer_service.repository;

import com.gorevce.freelancer_service.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProjectRepository extends MongoRepository<Project, String> {
    List<Project> findByFreelancerId(String freelancerId);
}
