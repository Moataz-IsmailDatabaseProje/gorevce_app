package com.gorevce.freelancer_service.repository;

import com.gorevce.freelancer_service.model.SocialMedia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SocialMediaRepository extends MongoRepository<SocialMedia, String> {
}
