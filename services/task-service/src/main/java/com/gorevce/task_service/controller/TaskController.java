package com.gorevce.task_service.controller;

import com.gorevce.task_service.dto.request.TaskRequest;
import com.gorevce.task_service.exception.CustomException;
import com.gorevce.task_service.service.TaskService;
import com.gorevce.task_service.util.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    // create task
    @Operation(summary = "Create task",description = "Create task")
    @PostMapping("/create-task")
    public ResponseEntity<?> createTask(@RequestBody TaskRequest taskRequest) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Task created successfully")
                            .httpStatusCode(200)
                            .response(taskService.createTask(taskRequest))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }

    }
    // get task
    @Operation(summary = "Get task",description = "Get task")
    @GetMapping("/get-task")
    public ResponseEntity<?> getTask(@RequestParam String taskId) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Task retrieved successfully")
                            .httpStatusCode(200)
                            .response(taskService.getTask(taskId))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }
    // get all tasks (for admin)
    @Operation(summary = "Get all tasks for admin",description = "Get all tasks for admin")
    @GetMapping("/get-all-tasks")
    public ResponseEntity<?> getAllTasks() {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Tasks retrieved successfully")
                            .httpStatusCode(200)
                            .response(taskService.getAllTasks())
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }
    // update task
    @Operation(summary = "Update task",description = "Update task")
    @PutMapping("/update-task")
    public ResponseEntity<?> updateTask(@RequestBody TaskRequest taskRequest, @RequestParam String taskId) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Task updated successfully")
                            .httpStatusCode(200)
                            .response(taskService.updateTask(taskRequest, taskId))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }
    // delete task
    @Operation(summary = "Delete task",description = "Delete task")
    @DeleteMapping("/delete-task")
    public ResponseEntity<?> deleteTask(@RequestBody String taskId) {
        try {
            taskService.deleteTask(taskId);
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Task deleted successfully")
                            .httpStatusCode(200)
                            .response(null)
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }
    // get tasks by company id
    @Operation(summary = "Get tasks by company id for company",description = "Get tasks by company id for company")
    @GetMapping("/get-task-by-company")
    public ResponseEntity<?> getTaskByCompanyId(@RequestParam String companyId) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Tasks retrieved successfully")
                            .httpStatusCode(200)
                            .response(taskService.getTasksByCompanyId(companyId))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }
    // get tasks (available for freelancer)
    @GetMapping("/get-tasks")
    @Operation(summary = "Get tasks for freelancers",description = "Get tasks for freelancers")
    public ResponseEntity<?> getTasksForFreelancer() {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Tasks retrieved successfully")
                            .httpStatusCode(200)
                            .response(taskService.getTasksForFreelancer())
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }
    // publish task
    @Operation(summary = "Publish task",description = "Publish task")
    @PutMapping("/publish-task")
    public ResponseEntity<?> publishTask(@RequestParam String taskId) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Task published successfully")
                            .httpStatusCode(200)
                            .response(taskService.publishTask(taskId))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }
    // unpublish task
    @Operation(summary = "Unpublish task",description = "Unpublish task")
    @PutMapping("/unpublish-task")
    public ResponseEntity<?> unpublishTask(@RequestParam String taskId) {
        try {
            taskService.unpublishTask(taskId);
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Task unpublished successfully")
                            .httpStatusCode(200)
                            .response(null)
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }
    // void task
    @Operation(summary = "Void task",description = "Void task")
    @PutMapping("/void-task")
    public ResponseEntity<?> voidTask(@RequestParam String taskId) {
        try {
            taskService.voidTask(taskId);
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Task voided successfully")
                            .httpStatusCode(200)
                            .response(null)
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }
    // unvoid task
    @Operation(summary = "Unvoid task",description = "Unvoid task")
    @PutMapping("/unvoid-task")
    public ResponseEntity<?> unvoidTask(@RequestParam String taskId) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Task unvoided successfully")
                            .httpStatusCode(200)
                            .response(taskService.unvoidTask(taskId))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }

    // accept task by freelancer
    @Operation(summary = "Accept task by freelancer (3)",description = "Accept task by freelancer (3)")
    @PutMapping("/accept-task")
    public ResponseEntity<?> acceptTask(@RequestParam String taskId, @RequestParam String applicationId) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Task accepted successfully")
                            .httpStatusCode(200)
                            .response(taskService.acceptTask(taskId, applicationId))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }

    // reject task by freelancer
    @Operation(summary = "Reject task by freelancer (3)",description = "Reject task by freelancer (3)")
    @PutMapping("/reject-task")
    public ResponseEntity<?> rejectTask(@RequestParam String taskId, @RequestParam String applicationId) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Task rejected successfully")
                            .httpStatusCode(200)
                            .response(taskService.rejectTask(taskId, applicationId))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }

    // task completed by freelancer
    @Operation(summary = "Task completed by freelancer (4)",description = "Task completed by freelancer (4)")
    @PutMapping("/complete-task")
    public ResponseEntity<?> completeTask(@RequestParam String taskId) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Setting task as completed successfully, please wait for company to review")
                            .httpStatusCode(200)
                            .response(taskService.completeTask(taskId))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }

    // task reviewed by company
    @Operation(summary = "Task reviewed by company (5)",description = "Task reviewed by company (5)")
    @PutMapping("/review-task")
    public ResponseEntity<?> reviewTask(@RequestParam String taskId) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Task reviewed successfully, please wait for payment  to be processed")
                            .httpStatusCode(200)
                            .response(taskService.reviewTask(taskId))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }

    // after review task, the task is not completed
    @Operation(summary = "after review Task not completed (5)",description = "after review Task not completed (5)")
    @PutMapping("/not-completed-task")
    public ResponseEntity<?> notCompletedTask(@RequestParam String taskId) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Task not completed successfully")
                            .httpStatusCode(200)
                            .response(taskService.notCompletedTask(taskId))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }

    // set payment status to paid
    @Operation(summary = "Set payment status to paid (6)",description = "Set payment status to paid (6)")
    @PutMapping("/set-payment-status")
    public ResponseEntity<?> setPaymentStatus(@RequestParam String taskId) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Payment status set to paid successfully")
                            .httpStatusCode(200)
                            .response(taskService.setPaymentStatus(taskId))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build()
            );
        }
    }

}
