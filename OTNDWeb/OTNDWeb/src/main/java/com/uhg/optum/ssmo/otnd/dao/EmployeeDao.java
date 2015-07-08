package com.uhg.optum.ssmo.otnd.dao;

import java.util.List;

import com.uhg.optum.ssmo.otnd.entity.Employee;


public interface EmployeeDao {
	public Employee getEmployee(String ntId);
	public List<Employee> getEmployees(Employee emp);
	public void addEmployee (Employee emp);
	public void updateEmployee (Employee emp);
	public void deleteEmployee (Employee emp);
}
