package com.gorevce.freelancer_service.repository;

import com.gorevce.freelancer_service.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {
}
