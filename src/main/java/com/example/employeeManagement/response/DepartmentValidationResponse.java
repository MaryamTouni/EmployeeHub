package com.example.employeeManagement.response;

public class DepartmentValidationResponse {

	private String department;
	private boolean valid;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
