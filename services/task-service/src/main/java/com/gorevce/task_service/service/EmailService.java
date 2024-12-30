package com.gorevce.task_service.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    // Method to send verification email
    void sendVerificationEmail(String to, String verificationUrl) throws MessagingException;

    // Method to send reset password email
    void sendResetPasswordEmail(String to, String resetPasswordUrl) throws MessagingException;

    // Alternative method for simple plain-text emails (optional)
    void sendPlainTextEmail(String to, String subject, String text);
}
