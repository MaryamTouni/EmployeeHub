package com.example.employeeManagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.amazonaws.services.simpleemail.model.Content;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;

@Service
public class EmailNotificationService {

	private static final Logger logger = LoggerFactory.getLogger(EmailNotificationService.class);

	private final AmazonSimpleEmailService sesClient;

    private final String region = "me-south-1";

    public EmailNotificationService() {
        this.sesClient = AmazonSimpleEmailServiceClientBuilder.standard()
                .withRegion(region)
                .build();
    }

    @Retryable(value = { Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public void sendEmail(String to, String subject, String bodyText) {
        try {
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(new com.amazonaws.services.simpleemail.model.Destination().withToAddresses(to))
                    .withMessage(new Message()
                            .withBody(new Body().withText(new Content().withData(bodyText)))
                            .withSubject(new Content().withData(subject)))
                    .withSource("email@test.com"); 

            SendEmailResult result = sesClient.sendEmail(request);
            logger.info("Email sent! Message ID: " + result.getMessageId());
        } catch (Exception ex) {
            logger.error("The email was not sent. Error message: " + ex.getMessage());
            throw ex; 
        }
    }
    
    @Recover
    public void recover(Exception e, String to, String subject, String bodyText) {
        logger.error("Failed to send email after retries. Last error: " + e.getMessage());
    }
}
