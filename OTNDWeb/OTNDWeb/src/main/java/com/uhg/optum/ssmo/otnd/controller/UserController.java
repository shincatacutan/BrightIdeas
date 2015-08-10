package com.uhg.optum.ssmo.otnd.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uhg.optum.ssmo.otnd.entity.Employee;
import com.uhg.optum.ssmo.otnd.service.EmployeeService;

@Controller
public class UserController {
	@Autowired
	private EmployeeService employeeService;

	private final static Logger logger = LoggerFactory
			.getLogger(UserController.class);

	private static final String VIEW_INDEX = "index";

	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
	public String getUser(@RequestParam String empID, ModelMap model,
			HttpServletRequest request) {
		String user = empID;
		logger.debug(" [getUser] == ntID: " + user);
		Employee employee = employeeService.getEmployee(user);
		if(null == employee){
			logger.debug(" [getUser] employee not found!");
			return null;
		}
		request.getSession().setAttribute("employee", employee);
		logger.debug(" [getUser] == user full name: " + employee.getFullName());
		model.addAttribute("employee", employee);
		getUserRole(model);
		return VIEW_INDEX;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultHome(ModelMap model) {
		return VIEW_INDEX;
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, ModelMap model) {
		return VIEW_INDEX;
	}

	private void getUserRole(ModelMap model) {
		model.addAttribute("isBackdoor", false);
		Employee employee = employeeService.getEmployee(((Employee) (model
				.get("employee"))).getNetworkID());

		if (employee.getRoleType().getId() == 1) {
			model.addAttribute("isAdmin", true);
		} else {
			model.addAttribute("isAdmin", false);
		}
		logger.debug(" [getUser] user role: "+employee.getRoleType().getId());
	}
}
