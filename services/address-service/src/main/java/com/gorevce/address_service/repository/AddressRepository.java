package com.gorevce.address_service.repository;


import com.gorevce.address_service.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AddressRepository extends MongoRepository<Address, String> {
    List<Address> findAllByAddressOfId(String addressOfId);
}
