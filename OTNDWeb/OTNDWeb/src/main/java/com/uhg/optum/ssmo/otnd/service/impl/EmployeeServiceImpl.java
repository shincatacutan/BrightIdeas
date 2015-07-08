package com.uhg.optum.ssmo.otnd.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uhg.optum.ssmo.otnd.dao.EmployeeDao;
import com.uhg.optum.ssmo.otnd.entity.Employee;
import com.uhg.optum.ssmo.otnd.service.EmployeeService;
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	private final static Logger logger = LoggerFactory
			.getLogger(EmployeeServiceImpl.class);
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Employee getEmployee(String ntID) {
		logger.debug("[service layer] getEmployee : "+ntID);
		return employeeDao.getEmployee(ntID);
	}

	@Override
	public void addEmployee(Employee employee) {
		employeeDao.addEmployee(employee);

	}

	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Employee> getEmployees(Employee lanID) {
		// TODO Auto-generated method stub
		return null;
	}

}
