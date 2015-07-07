package com.optum.operations.momlibrary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.optum.operations.momlibrary.entity.User;
import com.optum.operations.momlibrary.service.UserService;
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	private final static Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
	public @ResponseBody User getUser() {
		String user = System.getProperty("user.name");
		logger.debug(" [getUser] == lanID: " + user);
		return userService.getUser(user);
	}
}
