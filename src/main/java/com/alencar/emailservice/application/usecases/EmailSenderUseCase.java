package com.alencar.emailservice.application.usecases;

public interface EmailSenderUseCase {
    void sendEmail(String to, String subject, String body);
}
