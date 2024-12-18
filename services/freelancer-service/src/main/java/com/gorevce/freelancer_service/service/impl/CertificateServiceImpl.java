package com.gorevce.freelancer_service.service.impl;

import com.gorevce.freelancer_service.dto.request.CertificateRequest;
import com.gorevce.freelancer_service.dto.response.CertificateDetailsResponse;
import com.gorevce.freelancer_service.dto.response.CertificateResponse;
import com.gorevce.freelancer_service.exception.CustomException;
import com.gorevce.freelancer_service.model.Certificate;
import com.gorevce.freelancer_service.repository.CertificateRepository;
import com.gorevce.freelancer_service.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;

    @Override
    public CertificateResponse createCertificate(CertificateRequest certificateDto) {
        // check issueDate and expirationDate
        if (certificateDto.getIssueDate().after(certificateDto.getExpirationDate())) {
            throw new CustomException(
                    "Issue date cannot be after expiration date",
                    400,
                    Map.of(
                            "issueDate", certificateDto.getIssueDate(),
                            "expirationDate", certificateDto.getExpirationDate()
                    )
            );
        }
        if (certificateDto.getIssueDate().before(new Date())) {
            throw new CustomException(
                    "Issue date cannot be in the past",
                    400,
                    Map.of(
                            "issueDate", certificateDto.getIssueDate()
                    )
            );
        }
        // create certificate
        Certificate certificate = Certificate.builder()
                .name(certificateDto.getName())
                .description(certificateDto.getDescription())
                .issuer(certificateDto.getIssuer())
                .issueDate(certificateDto.getIssueDate())
                .expirationDate(certificateDto.getExpirationDate())
                .credentialId(certificateDto.getCredentialId())
                .credentialUrl(certificateDto.getCredentialUrl())
                .imageUrl(certificateDto.getImageUrl())
                .freelancerId(certificateDto.getFreelancerId())
                .build();
        // save certificate
        Certificate savedCertificate = certificateRepository.save(certificate); // save certificate
        // return certificate
        return CertificateResponse.builder()
                .id(savedCertificate.getId())
                .name(savedCertificate.getName())
                .description(savedCertificate.getDescription())
                .issuer(savedCertificate.getIssuer())
                .imageUrl(savedCertificate.getImageUrl())
                .freelancerId(savedCertificate.getFreelancerId())
                .build();
    }

    @Override
    public CertificateResponse getCertificateById(String certificateId) {
        // get certificate
        Certificate certificate = certificateRepository.findById(certificateId)
                .orElseThrow(
                        () -> new CustomException(
                                "Certificate not found",
                                404,
                                Map.of(
                                        "certificateId", certificateId)
                        )
                );
        // return certificate
        return CertificateResponse.builder()
                .id(certificate.getId())
                .name(certificate.getName())
                .description(certificate.getDescription())
                .issuer(certificate.getIssuer())
                .imageUrl(certificate.getImageUrl())
                .freelancerId(certificate.getFreelancerId())
                .build();
    }

    @Override
    public CertificateResponse updateCertificate(String certificateId, CertificateRequest certificateDto) {
        // get certificate
        Certificate certificate = certificateRepository.findById(certificateId)
                .orElseThrow(
                        () -> new CustomException(
                                "Certificate not found",
                                404,
                                Map.of(
                                        "certificateId", certificateId
                                )
                        )
                );
        // check issueDate and expirationDate
        if (certificateDto.getIssueDate().after(certificateDto.getExpirationDate())) {
            throw new CustomException(
                    "Issue date cannot be after expiration date",
                    400,
                    Map.of(
                            "issueDate", certificateDto.getIssueDate(),
                            "expirationDate", certificateDto.getExpirationDate()
                    )
            );
        }
        if (certificateDto.getIssueDate().before(new Date())) {
            throw new CustomException(
                    "Issue date cannot be in the past",
                    400,
                    Map.of(
                            "issueDate", certificateDto.getIssueDate()
                    )
            );
        }
        // update certificate
        certificate.setName(certificateDto.getName());
        certificate.setDescription(certificateDto.getDescription());
        certificate.setIssuer(certificateDto.getIssuer());
        certificate.setIssueDate(certificateDto.getIssueDate());
        certificate.setExpirationDate(certificateDto.getExpirationDate());
        certificate.setCredentialId(certificateDto.getCredentialId());
        certificate.setCredentialUrl(certificateDto.getCredentialUrl());
        certificate.setImageUrl(certificateDto.getImageUrl());
        certificate.setFreelancerId(certificateDto.getFreelancerId());
        // save certificate
        Certificate savedCertificate = certificateRepository.save(certificate);
        // return certificate
        return CertificateResponse.builder()
                .id(savedCertificate.getId())
                .name(savedCertificate.getName())
                .description(savedCertificate.getDescription())
                .issuer(savedCertificate.getIssuer())
                .imageUrl(savedCertificate.getImageUrl())
                .freelancerId(savedCertificate.getFreelancerId())
                .build();
    }

    @Override
    public void deleteCertificate(String certificateId) {
        // get certificate
        Certificate certificate = certificateRepository.findById(certificateId)
                .orElseThrow(
                        () -> new CustomException(
                                "Certificate not found",
                                404,
                                Map.of(
                                        "certificateId", certificateId
                                )
                        )
                );
        // delete certificate
        certificateRepository.delete(certificate);
    }

    @Override
    public List<CertificateResponse> getCertificates() {
        // get certificates
        List<Certificate> certificates = certificateRepository.findAll();
        // return certificates
        return certificates.stream()
                .map(certificate -> CertificateResponse.builder()
                        .id(certificate.getId())
                        .name(certificate.getName())
                        .description(certificate.getDescription())
                        .issuer(certificate.getIssuer())
                        .imageUrl(certificate.getImageUrl())
                        .freelancerId(certificate.getFreelancerId())
                        .build())
                .toList();
    }

    @Override
    public List<CertificateResponse> getCertificatesByFreelancer(String freelancerId) {
        // get certificates
        List<Certificate> certificates = certificateRepository.findByFreelancerId(freelancerId);
        // return certificates
        return certificates.stream()
                .map(certificate -> CertificateResponse.builder()
                        .id(certificate.getId())
                        .name(certificate.getName())
                        .description(certificate.getDescription())
                        .issuer(certificate.getIssuer())
                        .imageUrl(certificate.getImageUrl())
                        .freelancerId(certificate.getFreelancerId())
                        .build())
                .toList();
    }

    @Override
    public CertificateDetailsResponse getCertificateDetails(String certificateId) {
        // get certificate
        Certificate certificate = certificateRepository.findById(certificateId)
                .orElseThrow(
                        () -> new CustomException(
                                "Certificate not found",
                                404,
                                Map.of(
                                        "certificateId", certificateId
                                )
                        )
                );

        // return certificate
        return CertificateDetailsResponse.builder()
                .id(certificate.getId())
                .name(certificate.getName())
                .description(certificate.getDescription())
                .issuer(certificate.getIssuer())
                .issueDate(certificate.getIssueDate())
                .expirationDate(certificate.getExpirationDate())
                .credentialId(certificate.getCredentialId())
                .credentialUrl(certificate.getCredentialUrl())
                .imageUrl(certificate.getImageUrl())
                .build();
    }

    @Override
    public Certificate getCertificateModelById(String id) {
        // get certificate
        return certificateRepository.findById(id)

                .orElseThrow(
                        () -> new CustomException(
                                "Certificate not found",
                                404,
                                Map.of(
                                        "certificateId", id)
                        )
                );
    }
}
