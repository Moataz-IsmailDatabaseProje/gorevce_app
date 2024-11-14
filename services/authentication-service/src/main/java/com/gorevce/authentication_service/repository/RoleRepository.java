package com.gorevce.authentication_service.repository;

import com.gorevce.authentication_service.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(String role);
}
