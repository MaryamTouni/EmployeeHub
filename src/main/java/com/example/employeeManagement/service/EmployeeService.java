package com.example.employeeManagement.service;

import java.util.List;

import com.example.employeeManagement.model.Employee;

public interface EmployeeService {

	Employee createEmployee(Employee emp);

	Employee getEmployeeById(Long id);

	Employee updateEmployee(Employee employee);

	void deleteEmployee(Long id);

	List<Employee> getAllEmployee();

}
