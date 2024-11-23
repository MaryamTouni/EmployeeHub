package com.example.employeeManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeeManagement.exception.EmployeeNotFoundException;
import com.example.employeeManagement.model.Employee;
import com.example.employeeManagement.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository repository;

	@Override
	public Employee createEmployee(Employee emp) {
		logger.info("<< Starting method createEmployee >>");
		return repository.save(emp);
	}

	@Override
	public Employee getEmployeeById(Long id) {
		logger.info("<< Starting method getEmployeeById >>");
		Optional<Employee> emp = repository.findById(id);
		if (emp.isPresent()) {
			return emp.get();
		} else {
			logger.error("Error getting employee with id: "+id);
			throw new EmployeeNotFoundException("Can't find employee with id: " + id);
		}
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		logger.info("<< Starting method updateEmployee >>");
		Optional<Employee> empOldVer = repository.findById(employee.getId());
		if (empOldVer.isPresent()) {
			Employee empNewVer = empOldVer.get();
			empNewVer.setId(employee.getId());
			empNewVer.setFirstName(employee.getFirstName());
			empNewVer.setLastName(employee.getLastName());
			empNewVer.setDepartment(employee.getDepartment());
			empNewVer.setEmail(employee.getEmail());
			empNewVer.setSalary(employee.getSalary());
			repository.save(empNewVer);
			return empNewVer;
		} else {
			logger.error("Error updating employee with id: "+ employee.getId());
			throw new EmployeeNotFoundException("Can't find employee with id: " + employee.getId());
		}
	}

	@Override
	public void deleteEmployee(Long id) {
		logger.info("<< Starting method deleteEmployee >>");
		Optional<Employee> emp = repository.findById(id);
		if (emp.isPresent()) {
			repository.delete(emp.get());
		} else {
			logger.error("Error deleting employee with id: ", id);
			throw new EmployeeNotFoundException("Can't find employee with id: " + id);
		}

	}

	@Override
	public List<Employee> getAllEmployee() {
		logger.info("<< Starting method getAllEmployee >>");
		return repository.findAll();
	}

}
