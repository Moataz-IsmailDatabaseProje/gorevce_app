package com.gorevce.company_service.service.impl;

import com.gorevce.company_service.dto.AddressDto;
import com.gorevce.company_service.dto.CompanyRequest;
import com.gorevce.company_service.dto.CompanyResponse;
import com.gorevce.company_service.dto.UserDto;
import com.gorevce.company_service.exception.CustomException;
import com.gorevce.company_service.model.Company;
import com.gorevce.company_service.repository.CompanyRepository;
import com.gorevce.company_service.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    @Qualifier("plainRestTemplate")
    private RestTemplate restTemplate;

    @Value("${application.config.authentication-service.url}")
    private String authenticatorServiceUrl;

    @Value("${application.config.address-service.url}")
    private String addressServicePath;

    @Override
    public CompanyResponse createCompany(CompanyRequest companyRequest) {
        // check if company has user
        if (companyRequest.getUserId() != null) {
            UserDto user = restTemplate.getForObject(authenticatorServiceUrl + "/auth/rest-template/get-user/" + companyRequest.getUserId(), UserDto.class);
        } else {
            throw new CustomException(
                    "Company must have a user",
                    400,
                    Map.of(
                            "userId", "null"
                    )
            );
        }

        // check if tax number exists
        if (companyRepository.existsByTaxNumber(companyRequest.getTaxNumber())) {
            throw new CustomException(
                    "Company with tax number already exists",
                    400,
                    Map.of(
                            "taxNumber", companyRequest.getTaxNumber()
                    )
            );
        }
        // check if email exists
        if (companyRepository.existsByEmail(companyRequest.getEmail())) {
            throw new CustomException(
                    "Company with email already exists",
                    400,
                    Map.of(
                            "email", companyRequest.getEmail()
                    )
            );
        }
        // check if phone exists
        if (companyRepository.existsByPhone(companyRequest.getPhone())) {
            throw new CustomException(
                    "Company with phone already exists",
                    400,
                    Map.of(
                            "phone", companyRequest.getPhone()
                    )
            );
        }

        // create company
        Company company = Company.builder()
                .name(companyRequest.getName())
                .description(companyRequest.getDescription())
                .taxNumber(companyRequest.getTaxNumber())
                .email(companyRequest.getEmail())
                .phone(companyRequest.getPhone())
                .website(companyRequest.getWebsite())
                .logo(companyRequest.getLogo())
                .userId(companyRequest.getUserId())
                .build();
        // save company
        Company saved = companyRepository.save(company);
        // check if company has address
        if (companyRequest.getAddress() != null) {
            companyRequest.getAddress().setAddressOfId(saved.getId());
            AddressDto address = restTemplate.postForObject(addressServicePath + "/rest-template/create-address", companyRequest.getAddress(), AddressDto.class);
            assert address != null;
            saved.setAddressId(address.getId());
        }
        // save company
        Company savedCompany = companyRepository.save(saved);
        // return company
        return CompanyResponse.builder()
                .id(savedCompany.getId())
                .name(savedCompany.getName())
                .description(savedCompany.getDescription())
                .taxNumber(savedCompany.getTaxNumber())
                .email(savedCompany.getEmail())
                .phone(savedCompany.getPhone())
                .website(savedCompany.getWebsite())
                .logo(savedCompany.getLogo())
                .userId(savedCompany.getUserId())
                .addressId(savedCompany.getAddressId())
                .build();
    }

    @Override
    public CompanyResponse getCompany(String id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty() || company.get().getIsDeleted()) {
            throw new CustomException(
                    "Company not found",
                    404,
                    Map.of(
                            "id", id
                    )
            );
        }
        // return company
        return CompanyResponse.builder()
                .id(company.get().getId())
                .name(company.get().getName())
                .description(company.get().getDescription())
                .taxNumber(company.get().getTaxNumber())
                .email(company.get().getEmail())
                .phone(company.get().getPhone())
                .website(company.get().getWebsite())
                .logo(company.get().getLogo())
                .userId(company.get().getUserId())
                .addressId(company.get().getAddressId())
                .build();
    }

    @Override
    public CompanyResponse updateCompany(CompanyRequest company, String id) {
        // check if company exists
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isEmpty() || companyOptional.get().getIsDeleted()) {
            throw new CustomException(
                    "Company not found",
                    404,
                    Map.of(
                            "id", id
                    )
            );
        }
        // update company
        Company updatedCompany = companyOptional.get();
        updatedCompany.setName(company.getName());
        updatedCompany.setDescription(company.getDescription());
        updatedCompany.setTaxNumber(company.getTaxNumber());
        updatedCompany.setEmail(company.getEmail());
        updatedCompany.setPhone(company.getPhone());
        updatedCompany.setWebsite(company.getWebsite());
        updatedCompany.setLogo(company.getLogo());
        // save company
        Company savedCompany = companyRepository.save(updatedCompany);
        // return company
        return CompanyResponse.builder()
                .id(savedCompany.getId())
                .name(savedCompany.getName())
                .description(savedCompany.getDescription())
                .taxNumber(savedCompany.getTaxNumber())
                .email(savedCompany.getEmail())
                .phone(savedCompany.getPhone())
                .website(savedCompany.getWebsite())
                .logo(savedCompany.getLogo())
                .userId(savedCompany.getUserId())
                .addressId(savedCompany.getAddressId())
                .build();

    }

    @Override
    public void deleteCompany(String id) {
        // check if company exists
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isEmpty() || companyOptional.get().getIsDeleted()) {
            throw new CustomException(
                    "Company not found",
                    404,
                    Map.of(
                            "id", id
                    )
            );
        }
        // delete company
        Company company = companyOptional.get();
        company.setIsDeleted(true);
        companyRepository.save(company);

    }

    @Override
    public void restoreCompany(String id) {
        // check if company exists
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isEmpty() || !companyOptional.get().getIsDeleted()) {
            throw new CustomException(
                    "Company not found",
                    404,
                    Map.of(
                            "id", id
                    )
            );
        }
        // restore company
        Company company = companyOptional.get();
        company.setIsDeleted(false);
        companyRepository.save(company);

    }

    @Override
    public List<CompanyResponse> getCompanies() {
        // get all companies
        List<Company> companies = companyRepository.findAll();
        // return companies
        return companies.stream().map(company -> CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .description(company.getDescription())
                .taxNumber(company.getTaxNumber())
                .email(company.getEmail())
                .phone(company.getPhone())
                .website(company.getWebsite())
                .logo(company.getLogo())
                .userId(company.getUserId())
                .addressId(company.getAddressId())
                .build()).toList();
    }

    @Override
    public List<CompanyResponse> getCompaniesByUserId(String userId) {
        // get companies by user id
        List<Company> companies = companyRepository.findByUserId(userId);
        // return companies
        return companies.stream().map(company -> CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .description(company.getDescription())
                .taxNumber(company.getTaxNumber())
                .email(company.getEmail())
                .phone(company.getPhone())
                .website(company.getWebsite())
                .logo(company.getLogo())
                .userId(company.getUserId())
                .addressId(company.getAddressId())
                .build()).toList();
    }

    @Override
    public List<CompanyResponse> getCompaniesByAddressId(String addressId) {
        // get companies by address id
        List<Company> companies = companyRepository.findByAddressId(addressId);
        // return companies
        return companies.stream().map(company -> CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .description(company.getDescription())
                .taxNumber(company.getTaxNumber())
                .email(company.getEmail())
                .phone(company.getPhone())
                .website(company.getWebsite())
                .logo(company.getLogo())
                .userId(company.getUserId())
                .addressId(company.getAddressId())
                .build()).toList();
    }

    @Override
    public Boolean doesCompanyExist(String companyId) {
        return companyRepository.existsById(companyId);
    }
}
