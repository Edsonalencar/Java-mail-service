package com.alencar.emailservice.application.api.EmailSender;

import com.alencar.emailservice.domain.EmailService.EmailSenderService;
import com.alencar.emailservice.infra.handlerException.EmailServiceException;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailSenderControler {

    private final EmailSenderService emailSenderService;

    @Autowired
    public EmailSenderControler(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping()
    public ResponseEntity<String> sendEmail(@RequestBody SendEmailDTO sendEmailDTO) {
        try {
            this.emailSenderService.sendEmail(sendEmailDTO.to(), sendEmailDTO.subject(), sendEmailDTO.Body());

            return ResponseEntity.ok().body("Email enviado com sucesso!");
        } catch (EmailServiceException exception) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao enviar o email!");
        }
    }
}
