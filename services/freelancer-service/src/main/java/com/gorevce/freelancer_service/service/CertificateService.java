package com.gorevce.freelancer_service.service;

import com.gorevce.freelancer_service.dto.request.CertificateRequest;
import com.gorevce.freelancer_service.dto.response.CertificateDetailsResponse;
import com.gorevce.freelancer_service.dto.response.CertificateResponse;

import java.util.List;

public interface CertificateService {

    public CertificateResponse createCertificate(CertificateRequest certificateDto);

    public CertificateResponse getCertificate(String certificateId);

    public CertificateResponse updateCertificate(String certificateId, CertificateRequest certificateDto);

    public void deleteCertificate(String certificateId);

    public List<CertificateResponse> getCertificates();

    public List<CertificateResponse> getCertificatesByFreelancer(String freelancerId);

    public CertificateDetailsResponse getCertificateDetails(String certificateId);

}
