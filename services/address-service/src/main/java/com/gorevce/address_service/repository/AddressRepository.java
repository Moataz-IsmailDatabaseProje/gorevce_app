package com.gorevce.address_service.repository;


import com.gorevce.address_service.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String> {
}
