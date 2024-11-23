package com.example.employeeManagement.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.employeeManagement.model.Employee;
import com.example.employeeManagement.service.EmployeeService;

@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService service;

	@Test
	public void testGetEmployeeById() throws Exception {
		when(service.getEmployeeById(1L))
				.thenReturn(new Employee("Menna", "Ahmed", "maryam@test.com", "it", new BigDecimal(2500)));

		mockMvc.perform(get("/employees/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("Menna")).andExpect(jsonPath("$.department").value("it"));
	}

	@Test
	public void testGetAllEmployees() throws Exception {
		when(service.getAllEmployee())
				.thenReturn(Arrays.asList(new Employee("Menna", "Ahmed", "menna@test.com", "it", new BigDecimal(2500)),
						new Employee("Mostafa", "Ahmed", "mostafa@test.com", "Software", new BigDecimal(7000)),
						new Employee("Maryam", "Ahmed", "maryam@test.com", "Finance", new BigDecimal(3000))));

		mockMvc.perform(get("/employees")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].firstName").value("Menna")).andExpect(jsonPath("$[0].department").value("it"))
				.andExpect(jsonPath("$[1].firstName").value("Mostafa"))
				.andExpect(jsonPath("$[1].department").value("Software"));
	}

	@Test
	public void testCreateEmployee() throws Exception {
		Employee newEmp = new Employee("Mostafa", "Ahmed", "mostafa@test.com", "Software", new BigDecimal(7000));
		when(service.createEmployee(newEmp)).thenReturn(newEmp);
		mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(
				"{\"firstName\":\"Mostafa\",\"lastName\":\"Ahmed\",\"email\":\"mostafa@test.com\",\"department\":\"Software\",\"salary\":7000}"))
				.andExpect(status().isOk());
	}

	@Test
	public void testUpdateEmployee() throws Exception {
		Employee emp = new Employee("Maryam", "Ahmed", "maryam@test.com", "Finance", new BigDecimal(3000));
		when(service.updateEmployee(emp)).thenReturn(emp);

		mockMvc.perform(put("/employees/1").contentType(MediaType.APPLICATION_JSON).content(
				"{\"firstName\":\"Maryam\",\"lastName\":\"Ahmed\",\"email\":\"maryam@test.com\",\"department\":\"Finance\",\"salary\":3000}"))
				.andExpect(status().isOk());
	}

	@Test
	public void testDeleteEmployee() throws Exception {
		doNothing().when(service).deleteEmployee(1L);
		mockMvc.perform(delete("/employees/1")).andExpect(status().isOk());
	}

}
