package com.optum.operations.momlibrary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.optum.operations.momlibrary.entity.Role;
import com.optum.operations.momlibrary.entity.User;
import com.optum.operations.momlibrary.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;
	private final static Logger logger = LoggerFactory
			.getLogger(AdminController.class);
	
	private static final String VIEW_INDEX = "index";

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public @ResponseBody String addInquiry(@RequestParam String empID, @RequestParam String lanID,
			@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam int roleId) {
		logger.debug("[addUser] adding user...");
		User user = new User();
		user.setEmpID(empID);
		user.setLanID(lanID);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		Role role = new Role();
		role.setId(roleId);
		user.setAccess(role);
		
		userService.addUser(user);
		return "Success";
	}
	
	@RequestMapping(value = "/backdoor/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {

		model.addAttribute("isAdmin", true);
		logger.debug("[accessing secret admin page...]");
		return VIEW_INDEX;

	}

}
