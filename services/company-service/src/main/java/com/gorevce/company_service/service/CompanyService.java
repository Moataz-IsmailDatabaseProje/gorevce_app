package com.gorevce.company_service.service;

import com.gorevce.company_service.dto.CompanyRequest;
import com.gorevce.company_service.dto.CompanyResponse;
import com.gorevce.company_service.model.Company;

import java.util.List;

public interface CompanyService {
    // CRUD operations
    // Create a new company
    CompanyResponse createCompany(CompanyRequest company);
    // Read a company
    CompanyResponse getCompany(String id);
    // Update a company
    CompanyResponse updateCompany(CompanyRequest company, String id);
    // Delete a company
    void deleteCompany(String id);
    // Restore a company
    void restoreCompany(String id);
    // Get all companies
    List<CompanyResponse> getCompanies();
    // Get companies by user id
    List<CompanyResponse> getCompaniesByUserId(String userId);
    // Get companies by address id
    List<CompanyResponse> getCompaniesByAddressId(String addressId);
}
