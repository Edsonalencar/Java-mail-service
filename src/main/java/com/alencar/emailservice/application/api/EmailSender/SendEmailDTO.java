package com.alencar.emailservice.application.api.EmailSender;

public record SendEmailDTO(String to, String subject, String Body) {}