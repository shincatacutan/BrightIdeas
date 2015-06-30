package com.optum.operations.momlibrary.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.optum.operations.momlibrary.model.Update;
import com.optum.operations.momlibrary.model.User;

@Controller
public class UpdatesListController {

	private static int counter = 0;
	private static final String VIEW_INDEX = "index";
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(UpdatesListController.class);
	
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
	public @ResponseBody User getUser(){
		User user = new User();
		user.setAccess("admin");
		user.setLanID("scatacut");
		user.setFirstName("Shin");
		user.setLastName("Catacutan");
		return user;
	}
	
	@RequestMapping(value="/showUpdates", method = RequestMethod.GET)
	public @ResponseBody List<Update> getUpdates() {
		logger.debug("[getUpdates] name: ");
		List<Update> updates = new ArrayList<Update>();
		Update update = new Update();
		update.setTitle("This is a title");
		update.setDescription("Donec porta erat vel sapien malesuada, sit amet imperdiet arcu interdum.");
		update.setId(10001);
		List<String> tags = new ArrayList<String>();
		tags.add("tag1");
		tags.add("tag2");
		tags.add("tag3");
		update.setTags(tags);
		User user = new User();
		user.setAccess("admin");
		user.setLanID("kperry");
		user.setFirstName("Katy");
		user.setLastName("Perry");
		update.setUser(user);
		update.setCreateDate("06/23/2015");
		update.setUpdateDate("06/23/2015");
		updates.add(update);
		
		Update update2 = new Update();
		update2.setTitle("Test Update Title");
		update2.setDescription("Fusce tristique sed dolor id volutpat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Cras");
		update2.setId(10002);
		List<String> tags2 = new ArrayList<String>();
		tags2.add("tag1");
		tags2.add("tag2");
		tags2.add("tag3");
		update2.setTags(tags2);
		User user2 = new User();
		user2.setAccess("admin");
		user2.setLanID("scatacut");
		user2.setFirstName("Shin");
		user2.setLastName("Catacutan");
		update2.setUser(user2);
		update2.setCreateDate("06/24/2015");
		update2.setUpdateDate("06/24/2015");
		updates.add(update2);
		
		Update update3 = new Update();
		update3.setTitle("Super Title");
		update3.setDescription("In vitae sapien vulputate, eleifend ipsum non, vulputate arcu. Ut venenatis sem in neque interdum mollis.");
		update3.setId(10003);
		List<String> tags3 = new ArrayList<String>();
		tags3.add("tag1");
		tags3.add("tag3");
		tags3.add("tag3");
		update3.setTags(tags3);
		User user3 = new User();
		user3.setAccess("user");
		user3.setLanID("tswift");
		user3.setFirstName("Taytay");
		user3.setLastName("Swift");
		update3.setUser(user3);
		update3.setCreateDate("06/25/3015");
		update3.setUpdateDate("06/25/3015");
		updates.add(update3);
		return updates;
	}

}