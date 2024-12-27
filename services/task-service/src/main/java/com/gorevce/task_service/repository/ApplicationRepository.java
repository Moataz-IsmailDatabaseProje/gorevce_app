package com.gorevce.task_service.repository;

import com.gorevce.task_service.model.Application;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ApplicationRepository extends MongoRepository<Application, String> {

    List<Application> findByTaskId(String taskId);

    List<Application> findByFreelancerId(String freelancerId);
}
