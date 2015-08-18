package com.uhg.optum.ssmo.otnd.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uhg.optum.ssmo.otnd.entity.Employee;
import com.uhg.optum.ssmo.otnd.exception.GenericException;
import com.uhg.optum.ssmo.otnd.service.EmployeeService;

@Controller
public class UserController {
	@Autowired
	private EmployeeService employeeService;

	private final static Logger logger = LoggerFactory
			.getLogger(UserController.class);

	private static final String VIEW_INDEX = "index";
	private static final String WELCOME = "welcome";

	@ExceptionHandler(GenericException.class)
	public ModelAndView handleCustomException(GenericException ex) {

		ModelAndView model = new ModelAndView("error/generic_error");
		model.addObject("errCode", ex.getErrCode());
		model.addObject("errMsg", ex.getErrMsg());

		return model;
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String getUser(@RequestParam String empID, ModelMap model,
			HttpServletRequest request) {
		Employee loggedEmp = (Employee) request.getSession().getAttribute(
				"employee");

		if (null == loggedEmp) {
			if ("".equals(empID)) {
				throw new GenericException("E002",
						"Browser cache error. Please refresh browser.");
			}
			String user = empID;
			logger.debug(" [getUser] == ntID: " + user);
			Employee employee = employeeService.getEmployee(user);
			if (null == employee) {
				request.getSession().setAttribute("employee", new Employee());
				logger.debug(" [getUser] employee not found!");
				throw new GenericException("E001",
						"Employee is not yet registered to the system. Please contact administrator.");
			}
			request.getSession().setAttribute("employee", employee);
			logger.debug(" [getUser] == user full name: "
					+ employee.getFullName());
			model.addAttribute("employee", employee);
			getUserRole(model);
		}

		return VIEW_INDEX;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultHome(ModelMap model) {
		return WELCOME;
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, ModelMap model) {
		return WELCOME;
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
		logger.debug(" [getUser] user role: " + employee.getRoleType().getId());
	}
}
