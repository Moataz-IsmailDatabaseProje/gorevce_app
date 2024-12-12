package com.gorevce.freelancer_service.repository;

import com.gorevce.freelancer_service.model.Freelancer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FreelancerRepository extends MongoRepository<Freelancer, String> {
}
