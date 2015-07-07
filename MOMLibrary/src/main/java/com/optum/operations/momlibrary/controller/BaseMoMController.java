package com.optum.operations.momlibrary.controller;

import java.util.List;

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

import com.optum.operations.momlibrary.entity.MoMUnit;
import com.optum.operations.momlibrary.entity.User;
import com.optum.operations.momlibrary.service.MoMService;

@Controller
public class BaseMoMController {

	@Autowired
	private MoMService momservice;
	private static int counter = 0;
	private static final String VIEW_INDEX = "index";
	private final static Logger logger = LoggerFactory
			.getLogger(BaseMoMController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {

		model.addAttribute("message", "Welcome");
		model.addAttribute("counter", ++counter);
		logger.debug("[welcome] counter : {}", counter);

		// Spring uses InternalResourceViewResolver and return back index.jsp
		return VIEW_INDEX;

	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String welcomeName(@PathVariable String name, ModelMap model) {

		model.addAttribute("message", "Welcome " + name);
		model.addAttribute("counter", ++counter);
		logger.debug("[welcomeName] counter : {}", counter);
		return VIEW_INDEX;

	}

	@RequestMapping("/view")
	public @ResponseBody User getUser() {
		User user = new User();
		user.setAccess("admin");
		user.setLanID("scatacut");
		user.setFirstName("Shin");
		user.setLastName("Catacutan");
		return user;
	}

	@RequestMapping(value = "/searchMOM", method = RequestMethod.POST)
	public @ResponseBody List<MoMUnit> searchMOM(@RequestParam String title,
			@RequestParam String author, @RequestParam String createDate) {
		logger.debug("[searchMOM] title: "+title);
		List<MoMUnit> updates = momservice.searchMOM(new MoMUnit());

		return updates;
	}

}