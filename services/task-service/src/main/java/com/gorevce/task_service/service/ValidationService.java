package com.gorevce.task_service.service;

import com.gorevce.task_service.exception.CustomException;
import com.gorevce.task_service.repository.ApplicationRepository;
import com.gorevce.task_service.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ValidationService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ApplicationRepository applicationRepository;
    @Value("${application.config.freelancer-service.url}")
    private String FREELANCER_SERVICE_URL;

    @Value("${application.config.company-service.url}")
    private String COMPANY_SERVICE_URL;

    @Value("${application.config.address-service.url}")
    private String ADDRESS_SERVICE_URL;

    @Autowired
    @Qualifier("plainRestTemplate")
    private RestTemplate restTemplate;

    public boolean doesTaskExist(String taskId) {
        return taskRepository.existsById(taskId);
    }

    public boolean doesApplicationExist(String applicationId) {
        return applicationRepository.existsById(applicationId);
    }

    public boolean doesFreelancerExist(String freelancerId) {
        try {
            ResponseEntity<Boolean> response = restTemplate.getForEntity(FREELANCER_SERVICE_URL + "/rest-template/exists/" + freelancerId, Boolean.class);
            return response.getBody() != null && response.getBody();
        } catch (HttpClientErrorException e) {
            return false;
        }
    }

    public boolean doesCompanyExist(String companyId) {
        try {
            ResponseEntity<Boolean> response = restTemplate.getForEntity(COMPANY_SERVICE_URL + "/rest-template/exists/" + companyId, Boolean.class);
            return response.getBody() != null && response.getBody();
        } catch (HttpClientErrorException e) {
            return false;
        }
    }

    public boolean doesAddressExist(String addressId) {
        try {
            ResponseEntity<Boolean> response = restTemplate.getForEntity(ADDRESS_SERVICE_URL + "/rest-template/exists/" + addressId, Boolean.class);
            return response.getBody() != null && response.getBody();
        } catch (HttpClientErrorException e) {
            return false;
        }
    }
}
