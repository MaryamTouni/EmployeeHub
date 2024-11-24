package com.example.employeeManagement.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailValidationService {
	
	private final String apiKey= "928ad7f360304d2a8de53579399e1b5f";

    private final RestTemplate restTemplate;

    public EmailValidationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String validateEmail(String email) {
        String url = String.format("https://api.zerobounce.net/v2/validate?api_key=%s&email=%s", apiKey, email);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

}
