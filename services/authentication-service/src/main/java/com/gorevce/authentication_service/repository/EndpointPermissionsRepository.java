package com.gorevce.authentication_service.repository;

import com.gorevce.authentication_service.model.EndpointPermission;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EndpointPermissionsRepository extends MongoRepository<EndpointPermission, Long> {


    Optional<EndpointPermission> findByEndpointAndHttpMethod(String endpoint, String httpMethod);

    void deleteByEndpointAndHttpMethod(String endpoint, String httpMethod);

    Optional<EndpointPermission> findById(String id);

    void deleteById(String id);
}
