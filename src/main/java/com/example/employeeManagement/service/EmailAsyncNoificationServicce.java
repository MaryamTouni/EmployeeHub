package com.example.employeeManagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;

@Service
public class EmailAsyncNoificationServicce {

	private static final Logger logger = LoggerFactory.getLogger(EmailNotificationService.class);

	 @Autowired
	    private JavaMailSender emailSender;

	    @Async
	    @Retryable(value = { Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 2000))
	    public void sendEmail(String to, String subject, String body) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject(subject);
	        message.setText(body);
	        emailSender.send(message);
	        logger.info("Email sent to: " + to);
	    }

	    @Recover
	    public void recover(Exception e, String to, String subject, String body) {
	        logger.error("Failed to send email after retries. Last error: " + e.getMessage());
	    }
}
