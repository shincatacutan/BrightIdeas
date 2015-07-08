package com.uhg.optum.ssmo.otnd.service;

import java.util.List;

import com.uhg.optum.ssmo.otnd.entity.Employee;

public interface EmployeeService {
	public Employee getEmployee(String ntID);
	
	public List<Employee> getEmployees(Employee ntID);

	public void addEmployee(Employee Employee);

	public void updateEmployee(Employee Employee);

	public void deleteEmployee(Employee Employee);
}
