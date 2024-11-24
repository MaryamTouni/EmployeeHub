package com.example.employeeManagement.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DepartmentValidationService {

	private final RestTemplate restTemplate;

	public DepartmentValidationService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public boolean isDepartmentValid(String department) {
        String url = "http://localhost:8081/validate-department?department=" + department;
        return restTemplate.getForObject(url, Boolean.class);
    }

}
