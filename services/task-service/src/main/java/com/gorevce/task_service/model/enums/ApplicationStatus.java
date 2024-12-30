package com.gorevce.task_service.model.enums;

public enum ApplicationStatus {
    CREATED,
    ACCEPTED,
    REJECTED,
    COMPLETED,
    CANCELLED;

    // function to get the application enum from string
    public static ApplicationStatus getApplicationStatus(String status) {
        for (ApplicationStatus applicationStatus : ApplicationStatus.values()) {
            if (applicationStatus.name().equalsIgnoreCase(status)) {
                return applicationStatus;
            }
        }
        return null;
    }

    // function to get the application string from application enum
    public static String getApplicationStatusString(ApplicationStatus status) {
        return status.name();
    }

    // function to check if the application is valid
    public static boolean isValidApplicationStatus(String status) {
        for (ApplicationStatus applicationStatus : ApplicationStatus.values()) {
            if (applicationStatus.name().equalsIgnoreCase(status)) {
                return true;
            }
        }
        return false;
    }

    // function to check if the application is exist
    public static boolean isApplicationStatusExist(ApplicationStatus status) {
        for (ApplicationStatus applicationStatus : ApplicationStatus.values()) {
            if (applicationStatus == status) {
                return true;
            }
        }
        return false;
    }

    // function to check if the application is exist by string
    public static boolean isApplicationStatusExist(String status) {
        for (ApplicationStatus applicationStatus : ApplicationStatus.values()) {
            if (applicationStatus.name().equalsIgnoreCase(status)) {
                return true;
            }
        }
        return false;
    }
}
