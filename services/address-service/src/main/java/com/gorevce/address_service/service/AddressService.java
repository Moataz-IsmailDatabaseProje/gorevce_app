package com.gorevce.address_service.service;


import com.gorevce.address_service.dto.AddressRequest;
import com.gorevce.address_service.dto.AddressResponse;

import java.util.List;

public interface AddressService {
    // create address
    AddressResponse createAddress(AddressRequest addressRequest);

    // get address by id
    AddressResponse getAddressById(String id);

    // get all addresses
    List<AddressResponse> getAllAddresses();

    // update address
    AddressResponse updateAddress(String id, AddressRequest addressRequest);

    // delete address
    void deleteAddress(String id);

    // get all addresses by addressOfId
    List<AddressResponse> getAllAddressesByAddressOfId(String addressOfId);
}
