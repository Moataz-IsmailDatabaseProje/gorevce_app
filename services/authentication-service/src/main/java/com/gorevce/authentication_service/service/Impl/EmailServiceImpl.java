package com.gorevce.authentication_service.service.Impl;

import com.gorevce.authentication_service.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // Set the default "From" email address
    private final String FROM_EMAIL = "ismailkattan.contact@gmail.com"; // <-- Replace with a valid email address

    // Method to send verification email
    @Override
    public void sendVerificationEmail(String to, String verificationUrl) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Set up the email details
        helper.setFrom(FROM_EMAIL);
        helper.setTo(to);
        helper.setSubject("Email Verification");
        helper.setText("<p>Click the link to verify your email:</p><a href='" + verificationUrl + "'>Verify Email</a>", true); // HTML content

        mailSender.send(message);
    }

    // Method to send reset password email
    @Override
    public void sendResetPasswordEmail(String to, String resetPasswordUrl) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Set up the email details
        helper.setFrom(FROM_EMAIL);
        helper.setTo(to);
        helper.setSubject("Reset Password");
        helper.setText("<p>Click the link to reset your password:</p><a href='" + resetPasswordUrl + "'>Reset Password</a>", true); // HTML content

        mailSender.send(message);
    }

    // Alternative method for simple plain-text emails (optional)
    @Override
    public void sendPlainTextEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM_EMAIL);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }
}
