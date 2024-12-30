package com.gorevce.task_service.controller;

import com.gorevce.task_service.dto.EmailDto;
import com.gorevce.task_service.exception.CustomException;
import com.gorevce.task_service.service.ApplicationService;
import com.gorevce.task_service.util.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks/email")
public class EmailController {

    @Autowired
    private ApplicationService applicationService;

    // send email to freelancer for application
    @Operation(summary = "Send email to freelancer for application",description = "Send email to freelancer for application")
    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailDto emailDto) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Email sent successfully")
                            .httpStatusCode(200)
                            .response(applicationService.sendEmail(emailDto))
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
