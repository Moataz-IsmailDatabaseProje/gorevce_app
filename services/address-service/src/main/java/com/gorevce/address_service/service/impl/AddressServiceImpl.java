package com.gorevce.address_service.service.impl;

import com.gorevce.address_service.dto.AddressRequest;
import com.gorevce.address_service.dto.AddressResponse;
import com.gorevce.address_service.exception.CustomException;
import com.gorevce.address_service.model.Address;
import com.gorevce.address_service.repository.AddressRepository;
import com.gorevce.address_service.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public AddressResponse createAddress(AddressRequest addressRequest) {
        // create address
        Address address = Address.builder()
                .country(addressRequest.getCountry())
                .city(addressRequest.getCity())
                .street(addressRequest.getStreet())
                .postalCode(addressRequest.getPostalCode())
                .additionalInfo(addressRequest.getAdditionalInfo())
                .addressOfId(addressRequest.getAddressOfId())
                .build();
        // save address to database
        Address savedAddress = addressRepository.save(address);
        // return address response
        return AddressResponse.builder()
                .id(savedAddress.getId())
                .country(savedAddress.getCountry())
                .city(savedAddress.getCity())
                .street(savedAddress.getStreet())
                .postalCode(savedAddress.getPostalCode())
                .additionalInfo(savedAddress.getAdditionalInfo())
                .addressOfId(savedAddress.getAddressOfId())
                .build();
    }

    @Override
    public AddressResponse getAddressById(String id) {
        // get address by id
        Address address = addressRepository.findById(id).orElseThrow(
                () -> new CustomException("Address not found", 404, Map.of("id", id))
        );
        // return address response
        return AddressResponse.builder()
                .id(address.getId())
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .postalCode(address.getPostalCode())
                .additionalInfo(address.getAdditionalInfo())
                .addressOfId(address.getAddressOfId())
                .build();
    }

    @Override
    public List<AddressResponse> getAllAddresses() {
        // get all addresses
        List<Address> addresses = addressRepository.findAll();
        // return address response list
        return addresses.stream().map(address -> AddressResponse.builder()
                .id(address.getId())
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .postalCode(address.getPostalCode())
                .additionalInfo(address.getAdditionalInfo())
                .addressOfId(address.getAddressOfId())
                .build()
        ).toList();
    }

    @Override
    public AddressResponse updateAddress(String id, AddressRequest addressRequest) {
        // find address by id
        Address address = addressRepository.findById(id).orElseThrow(
                () -> new CustomException("Address not found", 404, Map.of("id", id))
        );
        // update address
        address.setCountry(addressRequest.getCountry());
        address.setCity(addressRequest.getCity());
        address.setStreet(addressRequest.getStreet());
        address.setPostalCode(addressRequest.getPostalCode());
        address.setAdditionalInfo(addressRequest.getAdditionalInfo());
        address.setAddressOfId(addressRequest.getAddressOfId());
        // save updated address to database
        Address updatedAddress = addressRepository.save(address);
        // return address response
        return AddressResponse.builder()
                .id(updatedAddress.getId())
                .country(updatedAddress.getCountry())
                .city(updatedAddress.getCity())
                .street(updatedAddress.getStreet())
                .postalCode(updatedAddress.getPostalCode())
                .additionalInfo(updatedAddress.getAdditionalInfo())
                .addressOfId(updatedAddress.getAddressOfId())
                .build();
    }

    @Override
    public void deleteAddress(String id) {
        // find address by id
        Address address = addressRepository.findById(id).orElseThrow(
                () -> new CustomException("Address not found", 404, Map.of("id", id))
        );
        // delete address
        addressRepository.delete(address);
    }

    @Override
    public List<AddressResponse> getAllAddressesByAddressOfId(String addressOfId) {
        // get all addresses by addressOfId
        List<Address> addresses = addressRepository.findAllByAddressOfId(addressOfId);
        // return address response list
        return addresses.stream().map(address -> AddressResponse.builder()
                .id(address.getId())
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .postalCode(address.getPostalCode())
                .additionalInfo(address.getAdditionalInfo())
                .addressOfId(address.getAddressOfId())
                .build()
        ).toList();
    }

    @Override
    public Boolean doesAddressExist(String addressId) {
        // check if address exists
        return addressRepository.existsById(addressId);
    }
}
