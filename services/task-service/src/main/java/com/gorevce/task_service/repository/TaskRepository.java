package com.gorevce.task_service.repository;


import com.gorevce.task_service.model.Task;
import com.gorevce.task_service.model.enums.TaskStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByCompanyId(String companyId);

    List<Task> findByStatus(TaskStatus taskStatus);
}
