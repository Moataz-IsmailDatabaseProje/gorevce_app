package com.gorevce.freelancer_service.repository;

import com.gorevce.freelancer_service.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {
}
