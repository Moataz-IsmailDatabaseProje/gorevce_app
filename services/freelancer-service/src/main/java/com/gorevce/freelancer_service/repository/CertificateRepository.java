package com.gorevce.freelancer_service.repository;

import com.gorevce.freelancer_service.model.Certificate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CertificateRepository extends MongoRepository<Certificate, String> {

    List<Certificate> findByFreelancerId(String freelancerId);
}
