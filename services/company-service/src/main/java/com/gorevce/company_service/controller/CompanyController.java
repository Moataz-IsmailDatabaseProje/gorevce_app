package com.gorevce.company_service.controller;

import com.gorevce.company_service.dto.CompanyRequest;
import com.gorevce.company_service.exception.CustomException;
import com.gorevce.company_service.service.CompanyService;
import com.gorevce.company_service.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    // create company
    @PostMapping("/create-company")
    public ResponseEntity<?> createCompany(@RequestBody CompanyRequest companyRequest) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Company created successfully",
                            200,
                            companyService.createCompany(companyRequest)
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

    // get company
    @GetMapping("/get-company")
    public ResponseEntity<?> getCompany(@RequestParam String id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Company retrieved successfully",
                            200,
                            companyService.getCompany(id)
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

    // update company
    @PutMapping("/update-company")
    public ResponseEntity<?> updateCompany(@RequestBody CompanyRequest companyRequest, @RequestParam String id) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Company updated successfully",
                            200,
                            companyService.updateCompany(companyRequest, id)
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

    // delete company
    @DeleteMapping("/delete-company")
    public ResponseEntity<?> deleteCompany(@RequestParam String id) {
        try {
            companyService.deleteCompany(id);
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Company deleted successfully",
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

    // restore company
    @PutMapping("/restore-company")
    public ResponseEntity<?> restoreCompany(@RequestParam String id) {
        try {
            companyService.restoreCompany(id);
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Company restored successfully",
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

    // get all companies
    @GetMapping("/get-companies")
    public ResponseEntity<?> getCompanies() {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Companies retrieved successfully",
                            200,
                            companyService.getCompanies()
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

    // get all companies by user id
    @GetMapping("/get-companies-by-user-id")
    public ResponseEntity<?> getCompaniesByUserId(@RequestParam String userId) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Companies retrieved successfully",
                            200,
                            companyService.getCompaniesByUserId(userId)
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

    // get companies by address id
    @GetMapping("/get-companies-by-address-id")
    public ResponseEntity<?> getCompaniesByAddressId(@RequestParam String addressId) {
        try {
            return ResponseEntity.ok(
                    new ApiResponse(
                            "Companies retrieved successfully",
                            200,
                            companyService.getCompaniesByAddressId(addressId)
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
