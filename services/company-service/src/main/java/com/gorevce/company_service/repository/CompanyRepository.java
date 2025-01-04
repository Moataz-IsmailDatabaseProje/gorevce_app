package com.gorevce.company_service.repository;

import com.gorevce.company_service.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends MongoRepository<Company, String> {

    Boolean existsByTaxNumber(String taxNumber);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    Company findByUserId(String userId);

    Company findByAddressId(String addressId);
}
