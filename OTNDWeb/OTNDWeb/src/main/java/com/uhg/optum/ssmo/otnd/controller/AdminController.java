package com.uhg.optum.ssmo.otnd.controller;

import javax.servlet.ServletContext;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uhg.optum.ssmo.otnd.entity.Employee;
import com.uhg.optum.ssmo.otnd.entity.PayrollPeriod;
import com.uhg.optum.ssmo.otnd.entity.Role;
import com.uhg.optum.ssmo.otnd.service.EmployeeService;
import com.uhg.optum.ssmo.otnd.service.PayrollPeriodService;

@Controller
public class AdminController {
	@Autowired
	ServletContext context;
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private PayrollPeriodService periodService;

	private final static Logger logger = LoggerFactory
			.getLogger(AdminController.class);

	private static final String VIEW_INDEX = "index";

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public @ResponseBody String addUser(@RequestParam String empID,
			@RequestParam String ntID, @RequestParam String firstName,
			@RequestParam String lastName, @RequestParam int roleId,
			@RequestParam String project, @RequestParam String manager) {
		logger.debug("[addUser] adding employee...");
		Employee employee = new Employee();
		employee.setEmpID(empID);
		employee.setNetworkID(ntID);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setProject(project);
		employee.setManager(manager);
		Role role = new Role();
		role.setId(roleId);
		employee.setRoleType(role);

		employeeService.addEmployee(employee);
		return "Success";
	}

	@RequestMapping(value = "/addPayPeriod", method = RequestMethod.POST)
	public @ResponseBody String addPayPeriod(@RequestParam String payPeriod,
			@RequestParam String status) {
		logger.debug("[addPayPeriod] adding pay period...");
		logger.debug("[addPayPeriod] payPeriod: " + payPeriod);
		logger.debug("[addPayPeriod] status: " + status);
		PayrollPeriod pp = new PayrollPeriod();
		String date[] = payPeriod.split("/");
		logger.debug("[addPayPeriod] payPeriod: " + payPeriod);
		logger.debug("[addPayPeriod] status: " + status);
		LocalDate localDate = new LocalDate(Integer.parseInt(date[2]),
				Integer.parseInt(date[0]), Integer.parseInt(date[1]));
		logger.debug("[addPayPeriod] localdate: " + localDate.toString());
		pp.setPeriod(localDate);
		pp.setStatus(status);
		periodService.addPayroll(pp);
		return "Success";
	}

	@RequestMapping(value = "/backdoor/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("isBackdoor", true);
		logger.debug("[accessing secret admin page...]");
		return VIEW_INDEX;

	}
}
