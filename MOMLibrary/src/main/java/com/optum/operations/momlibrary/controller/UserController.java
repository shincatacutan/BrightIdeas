package com.optum.operations.momlibrary.controller;

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

import com.optum.operations.momlibrary.entity.User;
import com.optum.operations.momlibrary.service.UserService;
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	private final static Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String getUser(@RequestParam String username, ModelMap model,HttpServletRequest request) {
		logger.debug(" [getUser] == username: " + username);
		User emp = userService.getUser(username);  
		if(null == emp){
			return null;
		}
		logger.debug(" [getUser] == registered user: " + emp.getFirstName()+" "+emp.getLastName());
		model.addAttribute("employee", emp);
		request.getSession().setAttribute("employee", emp);
		return VIEW_INDEX;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultHome(ModelMap model) {
		model.addAttribute("isAdmin", false);
		return WELCOME_PAGE;
	}
	

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, ModelMap model) {
		model.addAttribute("isAdmin", false);
		return WELCOME_PAGE;
	}
	private static final String VIEW_INDEX = "index";
	private static final String WELCOME_PAGE = "welcome";
}
