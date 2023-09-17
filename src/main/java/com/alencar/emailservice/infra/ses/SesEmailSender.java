package com.alencar.emailservice.infra.ses;

import com.alencar.emailservice.adapters.EmailSenderGateway;
import com.alencar.emailservice.infra.handlerException.EmailServiceException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SesEmailSender implements EmailSenderGateway {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Autowired
    public SesEmailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        Destination toUser = new Destination().withToAddresses(to);
        Content subjectContent = new Content(subject);
        Content bodyContent = new Content(body);

        Message msg = new Message()
                .withSubject(subjectContent)
                .withBody(new Body(bodyContent));

        SendEmailRequest request = new SendEmailRequest()
                .withSource("edson.cesar.alencar@gmail.com")
                .withDestination(toUser)
                .withMessage(msg);


        try {
            this.amazonSimpleEmailService.sendEmail(request);
        }
        catch (AmazonServiceException exception) {
            throw new EmailServiceException("Ocorreu uma falha ao tentar enviar o email", exception);
        }
    }
}
