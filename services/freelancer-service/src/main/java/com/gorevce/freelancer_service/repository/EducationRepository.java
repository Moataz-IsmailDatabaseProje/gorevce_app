package com.gorevce.freelancer_service.repository;

import com.gorevce.freelancer_service.model.Education;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EducationRepository extends MongoRepository<Education, String> {

}
