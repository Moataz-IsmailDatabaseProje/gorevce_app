package com.gorevce.address_service.controller;


import com.gorevce.address_service.dto.AddressRequest;
import com.gorevce.address_service.exception.CustomException;
import com.gorevce.address_service.service.AddressService;
import com.gorevce.address_service.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // create address
    @PostMapping("/create-address")
    ResponseEntity<?> createAddress(@RequestBody AddressRequest addressRequest) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Address created successfully",
                            200,
                            addressService.createAddress(addressRequest)
                    )
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            new ApiResponse(
                                    e.getMessage(),
                                    e.getHttpStatusCode(),
                                    e.getDetails()
                            )
                    );
        }

    }
    // get address by id
    ResponseEntity<?> getAddressById(String id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Address retrieved successfully",
                            200,
                            addressService.getAddressById(id)
                    )
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            new ApiResponse(
                                    e.getMessage(),
                                    e.getHttpStatusCode(),
                                    e.getDetails()
                            )
                    );
        }
    }
    // get all addresses
    ResponseEntity<?> getAllAddresses() {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Addresses retrieved successfully",
                            200,
                            addressService.getAllAddresses()
                    )
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            new ApiResponse(
                                    e.getMessage(),
                                    e.getHttpStatusCode(),
                                    e.getDetails()
                            )
                    );
        }
    }
    // update address
    ResponseEntity<?> updateAddress(String id, AddressRequest addressRequest) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Address updated successfully",
                            200,
                            addressService.updateAddress(id, addressRequest)
                    )
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            new ApiResponse(
                                    e.getMessage(),
                                    e.getHttpStatusCode(),
                                    e.getDetails()
                            )
                    );
        }
    }
    // delete address
    ResponseEntity<?> deleteAddress(String id) {
        try {
            addressService.deleteAddress(id);
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Address deleted successfully",
                            200,
                            null
                    )
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            new ApiResponse(
                                    e.getMessage(),
                                    e.getHttpStatusCode(),
                                    e.getDetails()
                            )
                    );
        }
    }

}
