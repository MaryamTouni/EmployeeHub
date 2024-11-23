package com.example.employeeManagement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.employeeManagement.model.Employee;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository repository;

	@Test
	@Order(1)
	@Rollback(value = false)
	public void saveEmployeeTest() {
		Employee employee = new Employee("Maryam", "Ahmed", "maryam@test.com", "it", new BigDecimal(1500));
		Employee dbEmp = repository.save(employee);
		assertThat(dbEmp.getId()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	public void getEmployeeTest() {
		Employee employee = repository.findById(1L).orElse(null);
		assertThat(employee).isNotNull();
		assertThat(employee.getEmail()).isEqualTo("maryam@test.com");
	}

	@Test
	@Order(3)
	public void getListOfEmployeesTest() {
		List<Employee> employees = repository.findAll();
		assertThat(employees.size()).isGreaterThanOrEqualTo(1);
	}

	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateEmployeeTest() {
		Employee employee = repository.findById(1L).orElse(null);
		if (employee != null) {
			employee.setDepartment("Software");
			Employee newEmployee = repository.save(employee);
			assertThat(newEmployee.getDepartment()).isEqualTo("Software");
		}
	}

	@Test
	@Order(5)
	@Rollback(value = false)
	public void deleteEmployeeTest() {
		Employee employee = repository.findById(1L).orElse(null);
		if (employee != null) {
			repository.delete(employee);
			Employee removedEmployee = repository.findById(1L).orElse(null);
			assertThat(removedEmployee).isNull();
		}
	}

}
