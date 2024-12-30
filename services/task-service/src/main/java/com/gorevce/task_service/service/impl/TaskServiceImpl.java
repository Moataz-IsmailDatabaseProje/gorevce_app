package com.gorevce.task_service.service.impl;

import com.gorevce.task_service.dto.request.TaskRequest;
import com.gorevce.task_service.dto.response.TaskResponse;
import com.gorevce.task_service.exception.CustomException;
import com.gorevce.task_service.model.Task;
import com.gorevce.task_service.model.enums.ApplicationStatus;
import com.gorevce.task_service.model.enums.DifficultyLevel;
import com.gorevce.task_service.model.enums.TaskStatus;
import com.gorevce.task_service.repository.TaskRepository;
import com.gorevce.task_service.service.TaskService;
import com.gorevce.task_service.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ValidationService validationService;

    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
//         check if company exists
        if (!validationService.doesCompanyExist(taskRequest.getCompanyId())) {
            throw new CustomException(
                    "Company does not exist",
                    400,
                    Map.of(
                            "companyId", taskRequest.getCompanyId()
                    )
            );
        }
        // check if difficulty is valid
        if (DifficultyLevel.getDifficultyLevel(taskRequest.getDifficulty()) == null) {
            throw new CustomException(
                    "Invalid difficulty level",
                    400,
                    Map.of(
                            "difficulty", taskRequest.getDifficulty()
                    )
            );
        }
        // if address not null check if address exists
        if (taskRequest.getAddressId() != null && !validationService.doesAddressExist(taskRequest.getAddressId())) {
            throw new CustomException(
                    "Address does not exist",
                    400,
                    Map.of(
                            "addressId", taskRequest.getAddressId()
                    )
            );
        }
        // create task
        Task task = Task.builder()
                .name(taskRequest.getName())
                .description(taskRequest.getDescription())
                .duration(taskRequest.getDuration())
                .wageCeiling(taskRequest.getWageCeiling())
                .wageFloor(taskRequest.getWageFloor())
                .difficulty(DifficultyLevel.getDifficultyLevel(taskRequest.getDifficulty()))
                .companyId(taskRequest.getCompanyId())
                .applicationIds(List.of())
                .freelancerId(null)
                .addressId(taskRequest.getAddressId())
                .imageUrls(taskRequest.getImageUrls())
                .status(TaskStatus.CREATED)
                .build();
        Task savedTask = taskRepository.save(task);
        return mapTaskToTaskResponse(savedTask);
    }

    private TaskResponse mapTaskToTaskResponse(Task savedTask) {
        return TaskResponse.builder()
                .id(savedTask.getId())
                .name(savedTask.getName())
                .description(savedTask.getDescription())
                .status(savedTask.getStatus().name())
                .duration(savedTask.getDuration())
                .wageCeiling(savedTask.getWageCeiling())
                .wageFloor(savedTask.getWageFloor())
                .difficulty(savedTask.getDifficulty().name())
                .companyId(savedTask.getCompanyId())
                .freelancerId(savedTask.getFreelancerId())
                .addressId(savedTask.getAddressId())
                .imageUrls(savedTask.getImageUrls())
                .applicationCount(savedTask.getApplicationIds().size())
                .build();
    }

    @Override
    public TaskResponse updateTask(TaskRequest taskRequest, String taskId) {
        // check if task exists
        Task task = taskRepository.findById(taskId)
                .orElseThrow(
                        () -> new CustomException(
                                "Task not found",
                                404,
                                Map.of(
                                        "taskId", taskId
                                )
                        )
                );
        // check if difficulty is valid
        if (DifficultyLevel.getDifficultyLevel(taskRequest.getDifficulty()) == null) {
            throw new CustomException(
                    "Invalid difficulty level",
                    400,
                    Map.of(
                            "difficulty", taskRequest.getDifficulty()
                    )
            );
        }
        // update task
        task.setName(taskRequest.getName());
        task.setDescription(taskRequest.getDescription());
        task.setDuration(taskRequest.getDuration());
        task.setWageCeiling(taskRequest.getWageCeiling());
        task.setWageFloor(taskRequest.getWageFloor());
        task.setImageUrls(taskRequest.getImageUrls());
        task.setDifficulty(DifficultyLevel.getDifficultyLevel(taskRequest.getDifficulty()));
        Task updatedTask = taskRepository.save(task);

        return mapTaskToTaskResponse(updatedTask);
    }

    @Override
    public void deleteTask(String taskId) {
        // check if task exists
        Task task = taskRepository.findById(taskId)
                .orElseThrow(
                        () -> new CustomException(
                                "Task not found",
                                404,
                                Map.of(
                                        "taskId", taskId
                                )
                        )
                );

        // check if task status is not cancelled
        if (task.getStatus() != TaskStatus.VOIDED) {
            throw new CustomException(
                    "uncanceled task cannot be deleted",
                    400,
                    Map.of(
                            "taskId", taskId,
                            "status", task.getStatus().name()
                    )
            );
        }
        // delete task
        taskRepository.delete(task);
    }

    @Override
    public TaskResponse getTask(String taskId) {
        // check if task exists
        Task task = taskRepository.findById(taskId)
                .orElseThrow(
                        () -> new CustomException(
                                "Task not found",
                                404,
                                Map.of(
                                        "taskId", taskId
                                )
                        )
                );
        return mapTaskToTaskResponse(task);
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        // get all tasks
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(
                        this::mapTaskToTaskResponse
                )
                .toList();

    }

    @Override
    public List<TaskResponse> getTasksByCompanyId(String companyId) {
        // get tasks by company id
        List<Task> tasks = taskRepository.findByCompanyId(companyId);
        return tasks.stream()
                .map(
                        this::mapTaskToTaskResponse
                )
                .toList();
    }

    @Override
    public TaskResponse publishTask(String taskId) {
        // check if task exists
        Task task = taskRepository.findById(taskId)
                .orElseThrow(
                        () -> new CustomException(
                                "Task not found",
                                404,
                                Map.of(
                                        "taskId", taskId
                                )
                        )
                );
        // check if task status is created
        if (task.getStatus() != TaskStatus.CREATED) {
            throw new CustomException(
                    "Task cannot be published",
                    400,
                    Map.of(
                            "taskId", taskId,
                            "status", task.getStatus().name()
                    )
            );
        }
        // publish task
        task.setStatus(TaskStatus.PUBLISHED);
        Task publishedTask = taskRepository.save(task);
        return mapTaskToTaskResponse(publishedTask);
    }

    @Override
    public void unpublishTask(String taskId) {
        // check if task exists
        Task task = taskRepository.findById(taskId)
                .orElseThrow(
                        () -> new CustomException(
                                "Task not found",
                                404,
                                Map.of(
                                        "taskId", taskId
                                )
                        )
                );
        // check if task status is published
        if (task.getStatus() != TaskStatus.PUBLISHED) {
            throw new CustomException(
                    "Task cannot be unpublished",
                    400,
                    Map.of(
                            "taskId", taskId,
                            "status", task.getStatus().name()
                    )
            );
        }
        // unpublish task
        task.setStatus(TaskStatus.CREATED);
        taskRepository.save(task);
    }

    @Override
    public void voidTask(String taskId) {
        // check if task exists
        Task task = taskRepository.findById(taskId)
                .orElseThrow(
                        () -> new CustomException(
                                "Task not found",
                                404,
                                Map.of(
                                        "taskId", taskId
                                )
                        )
                );

        // if task is created or published, void it
        if (task.getStatus() == TaskStatus.CREATED || task.getStatus() == TaskStatus.PUBLISHED) {
            task.setStatus(TaskStatus.VOIDED);
            taskRepository.save(task);
        } else {
            throw new CustomException(
                    "Task cannot be voided",
                    400,
                    Map.of(
                            "taskId", taskId,
                            "status", task.getStatus().name()
                    )
            );
        }
    }

    @Override
    public TaskResponse unvoidTask(String taskId) {
        // check if task exists
        Task task = taskRepository.findById(taskId)
                .orElseThrow(
                        () -> new CustomException(
                                "Task not found",
                                404,
                                Map.of(
                                        "taskId", taskId
                                )
                        )
                );

        // if task is voided, unvoid it
        if (task.getStatus() == TaskStatus.VOIDED) {
            task.setStatus(TaskStatus.CREATED);
            Task unvoidedTask = taskRepository.save(task);
            return mapTaskToTaskResponse(unvoidedTask);
        } else {
            throw new CustomException(
                    "Task cannot be unvoided",
                    400,
                    Map.of(
                            "taskId", taskId,
                            "status", task.getStatus().name()
                    )
            );
        }
    }

    @Override
    public List<TaskResponse> getTasksForFreelancer() {
        // get published tasks
        List<Task> tasks = taskRepository.findByStatus(TaskStatus.PUBLISHED);

        return tasks.stream()
                .map(
                        this::mapTaskToTaskResponse
                )
                .toList();
    }

    @Override
    public TaskResponse acceptTask(String taskId, String applicationId) {
        // check if task exists
        Task task = taskRepository.findById(taskId)
                .orElseThrow(
                        () -> new CustomException(
                                "Task not found",
                                404,
                                Map.of(
                                        "taskId", taskId
                                )
                        )
                );

        // check if task status is assigned
        if (task.getStatus() != TaskStatus.ASSIGNED) {
            throw new CustomException(
                    "Task cannot be accepted",
                    400,
                    Map.of(
                            "taskId", taskId,
                            "status", task.getStatus().name()
                    )
            );
        }

        // check if application exists
        if (!task.getApplicationIds().contains(applicationId)) {
            throw new CustomException(
                    "Application does not exist",
                    400,
                    Map.of(
                            "applicationId", applicationId
                    )
            );
        }

        // accept task
        task.setStatus(TaskStatus.IN_PROGRESS);
        task.setFreelancerId(applicationId);
        // reject other applications
        task.getApplicationIds().stream()
                .filter(
                        application -> !application.equals(applicationId)
                )
                .forEach(
                        application -> validationService.updateApplicationStatus(application, ApplicationStatus.REJECTED)
                );
        Task acceptedTask = taskRepository.save(task);
        return mapTaskToTaskResponse(acceptedTask);
    }

    @Override
    public TaskResponse rejectTask(String taskId, String applicationId) {
        // check if task exists
        Task task = taskRepository.findById(taskId)
                .orElseThrow(
                        () -> new CustomException(
                                "Task not found",
                                404,
                                Map.of(
                                        "taskId", taskId
                                )
                        )
                );

        // check if task status is assigned
        if (task.getStatus() != TaskStatus.ASSIGNED) {
            throw new CustomException(
                    "Task cannot be rejected",
                    400,
                    Map.of(
                            "taskId", taskId,
                            "status", task.getStatus().name()
                    )
            );
        }

        // check if application exists
        if (!task.getApplicationIds().contains(applicationId)) {
            throw new CustomException(
                    "Application does not exist",
                    400,
                    Map.of(
                            "applicationId", applicationId
                    )
            );
        }

        // reject task
        task.setStatus(TaskStatus.PUBLISHED);
        task.setFreelancerId(null);
        // set application status to rejected
        validationService.updateApplicationStatus(applicationId, ApplicationStatus.REJECTED);
        Task rejectedTask = taskRepository.save(task);
        return mapTaskToTaskResponse(rejectedTask);
    }

    @Override
    public TaskResponse completeTask(String taskId) {
        // check if task exists
        Task task = taskRepository.findById(taskId)
                .orElseThrow(
                        () -> new CustomException(
                                "Task not found",
                                404,
                                Map.of(
                                        "taskId", taskId
                                )
                        )
                );

        // check if task status is in progress
        if (task.getStatus() != TaskStatus.IN_PROGRESS) {
            throw new CustomException(
                    "Task cannot be completed",
                    400,
                    Map.of(
                            "taskId", taskId,
                            "status", task.getStatus().name()
                    )
            );
        }

        // complete task
        task.setStatus(TaskStatus.IN_REVIEW);
        Task completedTask = taskRepository.save(task);
        return mapTaskToTaskResponse(completedTask);
    }

    @Override
    public TaskResponse reviewTask(String taskId) {
        // check if task exists
        Task task = taskRepository.findById(taskId)
                .orElseThrow(
                        () -> new CustomException(
                                "Task not found",
                                404,
                                Map.of(
                                        "taskId", taskId
                                )
                        )
                );

        // check if task status is in review
        if (task.getStatus() != TaskStatus.IN_REVIEW) {
            throw new CustomException(
                    "Task cannot be reviewed",
                    400,
                    Map.of(
                            "taskId", taskId,
                            "status", task.getStatus().name()
                    )
            );
        }

        // review task
        task.setStatus(TaskStatus.IN_PAYMENT);
        Task reviewedTask = taskRepository.save(task);
        return mapTaskToTaskResponse(reviewedTask);
    }

    @Override
    public TaskResponse notCompletedTask(String taskId) {
        // check if task exists
        Task task = taskRepository.findById(taskId)
                .orElseThrow(
                        () -> new CustomException(
                                "Task not found",
                                404,
                                Map.of(
                                        "taskId", taskId
                                )
                        )
                );

        // check if task status is in progress
        if (task.getStatus() != TaskStatus.IN_PROGRESS) {
            throw new CustomException(
                    "Task cannot be not completed",
                    400,
                    Map.of(
                            "taskId", taskId,
                            "status", task.getStatus().name()
                    )
            );
        }

        // not complete task
        task.setStatus(TaskStatus.IN_PROGRESS);
        Task notCompletedTask = taskRepository.save(task);
        return mapTaskToTaskResponse(notCompletedTask);
    }

    @Override
    public TaskResponse setPaymentStatus(String taskId) {
        // check if task exists
        Task task = taskRepository.findById(taskId)
                .orElseThrow(
                        () -> new CustomException(
                                "Task not found",
                                404,
                                Map.of(
                                        "taskId", taskId
                                )
                        )
                );

        // check if task status is in payment
        if (task.getStatus() != TaskStatus.IN_PAYMENT) {
            throw new CustomException(
                    "Task cannot be paid",
                    400,
                    Map.of(
                            "taskId", taskId,
                            "status", task.getStatus().name()
                    )
            );
        }

        // pay task
        task.setStatus(TaskStatus.PAID);
        Task paidTask = taskRepository.save(task);
        return mapTaskToTaskResponse(paidTask);
    }

}
