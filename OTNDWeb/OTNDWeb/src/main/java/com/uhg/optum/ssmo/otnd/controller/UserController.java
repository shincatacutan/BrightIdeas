package com.uhg.optum.ssmo.otnd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uhg.optum.ssmo.otnd.entity.Employee;
import com.uhg.optum.ssmo.otnd.service.EmployeeService;
@Controller
public class UserController {
	@Autowired
	private EmployeeService employeeService;
	private final static Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
	public @ResponseBody Employee getUser() {
		String user = System.getProperty("user.name");
		logger.debug(" [getUser] == ntID: " + user);
		return employeeService.getEmployee(user);
	}
}
