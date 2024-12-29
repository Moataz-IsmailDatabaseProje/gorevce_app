package com.gorevce.task_service.exception;

import java.util.Map;

public class CustomException extends RuntimeException {
    private final int httpStatusCode;
    private final Map<String, Object> details;

    public CustomException(String message, int httpStatusCode, Map<String, Object> details) {
        super(message);
        this.httpStatusCode = httpStatusCode;
        this.details = details;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public Map<String, Object> getDetails() {
        return details;
    }
}