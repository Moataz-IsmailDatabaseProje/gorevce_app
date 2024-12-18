package com.gorevce.freelancer_service.repository;

import com.gorevce.freelancer_service.model.WorkExperience;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WorkExperienceRepository extends MongoRepository<WorkExperience, String> {
    List<WorkExperience> findByFreelancerId(String freelancerId);
}
