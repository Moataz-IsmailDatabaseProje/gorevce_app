package com.gorevce.task_service.model.enums;

public enum TaskStatus {
    // 1. status of the task
    CREATED,
    // 2. status of the task
    PUBLISHED,
    // 3. status of the task
    VOIDED,
    // 3. status of the task
    // 4. status of the task
    ASSIGNED,
    IN_PROGRESS,
    // 5. status of the task
    IN_REVIEW,
    // 6. status of the task
    IN_PAYMENT,
    // 7. status of the task
    PAID,
    // 8. status of the task
    COMPLETED,
    // 9. status of the task
    POSTED,
    // can be used in any case
    IN_PROGRESS_PROBLEM,
    // the last saved status of the task with something went wrong
    ABORTED;

    // function to get the task enum from string
    public static TaskStatus getTaskStatus(String status) {
        for (TaskStatus taskStatus : TaskStatus.values()) {
            if (taskStatus.name().equalsIgnoreCase(status)) {
                return taskStatus;
            }
        }
        return null;
    }

    // function to get the task string from task enum
    public static String getTaskStatusString(TaskStatus status) {
        return status.name();
    }


    // function to check if the task is valid
    public static boolean isValidTaskStatus(String status) {
        for (TaskStatus taskStatus : TaskStatus.values()) {
            if (taskStatus.name().equalsIgnoreCase(status)) {
                return true;
            }
        }
        return false;
    }

    // function to check if the task is exist
    public static boolean isTaskStatusExist(TaskStatus status) {
        for (TaskStatus taskStatus : TaskStatus.values()) {
            if (taskStatus == status) {
                return true;
            }
        }
        return false;
    }


    // function to check if the task is exist by string
    public static boolean isTaskStatusExist(String status) {
        for (TaskStatus taskStatus : TaskStatus.values()) {
            if (taskStatus.name().equalsIgnoreCase(status)) {
                return true;
            }
        }
        return false;
    }
}
