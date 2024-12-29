package com.gorevce.task_service.service;

import com.gorevce.task_service.dto.request.TaskRequest;
import com.gorevce.task_service.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {
    // create task
    TaskResponse createTask(TaskRequest taskRequest);
    // update task
    TaskResponse updateTask(TaskRequest taskRequest, String taskId);
    // delete task
    void deleteTask(String taskId);
    // get task by id
    TaskResponse getTask(String taskId);
    // get all tasks
    List<TaskResponse> getAllTasks();
    // get tasks by company id
    List<TaskResponse> getTasksByCompanyId(String companyId);
    // publish task
    TaskResponse publishTask(String taskId);
    // unpublish task
    void unpublishTask(String taskId);
    // void task
    void voidTask(String taskId);
    // unvoid task
    TaskResponse unvoidTask(String taskId);
    // get tasks for freelancer
    List<TaskResponse> getTasksForFreelancer();
}
