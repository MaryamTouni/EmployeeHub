package com.example.employeeManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeManagement.service.DepartmentValidationService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

	@Autowired
	private DepartmentValidationService departmentValidationService;

	 @GetMapping("/validate-department")
	    public boolean validateDepartment(@RequestParam String department) {
	        return departmentValidationService.isDepartmentValid(department);
	    }
}
