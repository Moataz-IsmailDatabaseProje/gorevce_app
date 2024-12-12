package com.gorevce.freelancer_service.controller;


import com.gorevce.freelancer_service.dto.request.CertificateRequest;
import com.gorevce.freelancer_service.exception.CustomException;
import com.gorevce.freelancer_service.service.CertificateService;
import com.gorevce.freelancer_service.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/freelancer/certificates")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    // create certificate
    @PostMapping("/create-certificate")
    public ResponseEntity<?> createCertificate(@RequestBody CertificateRequest certificateRequest) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Certificate created successfully")
                            .httpStatusCode(200)
                            .response(certificateService.createCertificate(certificateRequest))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            ApiResponse.builder()
                                    .message(e.getMessage())
                                    .httpStatusCode(e.getHttpStatusCode())
                                    .response(e.getDetails())
                                    .build()
                    );

        }
    }
    // get certificate by id
    @GetMapping("/get-certificate")
    public ResponseEntity<?> getCertificateById(@RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Certificate retrieved successfully")
                            .httpStatusCode(200)
                            .response(certificateService.getCertificate(id))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            ApiResponse.builder()
                                    .message(e.getMessage())
                                    .httpStatusCode(e.getHttpStatusCode())
                                    .response(e.getDetails())
                                    .build()
                    );
        }
    }
    // get all certificates by freelancer id
    @GetMapping("/get-certificates")
    public ResponseEntity<?> getCertificatesByFreelancerId(@RequestParam String freelancerId) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Certificates retrieved successfully")
                            .httpStatusCode(200)
                            .response(certificateService.getCertificatesByFreelancer(freelancerId))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            ApiResponse.builder()
                                    .message(e.getMessage())
                                    .httpStatusCode(e.getHttpStatusCode())
                                    .response(e.getDetails())
                                    .build()
                    );
        }
    }
    // update certificate by id
    @PutMapping("/update-certificate")
    public ResponseEntity<?> updateCertificate(@RequestParam String id, @RequestBody CertificateRequest certificateRequest) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Certificate updated successfully")
                            .httpStatusCode(200)
                            .response(certificateService.updateCertificate(id, certificateRequest))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            ApiResponse.builder()
                                    .message(e.getMessage())
                                    .httpStatusCode(e.getHttpStatusCode())
                                    .response(e.getDetails())
                                    .build()
                    );
        }
    }
    // delete certificate by id
    @DeleteMapping("/delete-certificate")
    public ResponseEntity<?> deleteCertificate(@RequestParam String id) {
        try {
            certificateService.deleteCertificate(id);
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Certificate deleted successfully")
                            .httpStatusCode(200)
                            .response(null)
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            ApiResponse.builder()
                                    .message(e.getMessage())
                                    .httpStatusCode(e.getHttpStatusCode())
                                    .response(e.getDetails())
                                    .build()
                    );
        }
    }
    // get all certificates
    @GetMapping("/get-all-certificates")
    public ResponseEntity<?> getAllCertificates() {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Certificates retrieved successfully")
                            .httpStatusCode(200)
                            .response(certificateService.getCertificates())
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            ApiResponse.builder()
                                    .message(e.getMessage())
                                    .httpStatusCode(e.getHttpStatusCode())
                                    .response(e.getDetails())
                                    .build()
                    );
        }
    }
    // get certificate details by id
    @GetMapping("/get-certificate-details")
    public ResponseEntity<?> getCertificateDetails(@RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Certificate details retrieved successfully")
                            .httpStatusCode(200)
                            .response(certificateService.getCertificateDetails(id))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity
                    .status(e.getHttpStatusCode())
                    .body(
                            ApiResponse.builder()
                                    .message(e.getMessage())
                                    .httpStatusCode(e.getHttpStatusCode())
                                    .response(e.getDetails())
                                    .build()
                    );
        }
    }
}
