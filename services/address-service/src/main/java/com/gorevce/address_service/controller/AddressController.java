package com.gorevce.address_service.controller;


import com.gorevce.address_service.dto.AddressRequest;
import com.gorevce.address_service.exception.CustomException;
import com.gorevce.address_service.service.AddressService;
import com.gorevce.address_service.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/get-address")
    ResponseEntity<?> getAddressById(@RequestParam String id) {
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
    @GetMapping("/get-addresses")
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
    @PutMapping("/update-address")
    ResponseEntity<?> updateAddress(@RequestParam String id,@RequestBody AddressRequest addressRequest) {
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
    @DeleteMapping("/delete-address")
    ResponseEntity<?> deleteAddress(@RequestParam String id) {
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

    // get all addresses by addressOfId
    @GetMapping("/get-addresses-by-addressOfId")
    ResponseEntity<?> getAllAddressesByAddressOfId(@RequestParam String objectId) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Addresses retrieved successfully",
                            200,
                            addressService.getAllAddressesByAddressOfId(objectId)
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

    // get address by addressId for rest template call
    @GetMapping("/rest-template/get-address/{addressId}")
    ResponseEntity<?> getAddressByIdForRestTemplate(@PathVariable String addressId) {
        try {
            return ResponseEntity.ok(
                    addressService.getAddressById(addressId)
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
