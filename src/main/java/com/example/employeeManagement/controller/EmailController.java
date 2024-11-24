package com.example.employeeManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeManagement.service.EmailValidationService;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

	private final EmailValidationService emailValidationService;

    public EmailController(EmailValidationService emailValidationService) {
        this.emailValidationService = emailValidationService;
    }

    @GetMapping("/validate")
    public String validateEmail(@RequestParam String email) {
        return emailValidationService.validateEmail(email);
    }
}
