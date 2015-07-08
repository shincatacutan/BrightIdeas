package com.uhg.optum.ssmo.otnd.controller;

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

import com.uhg.optum.ssmo.otnd.entity.MoMUnit;
import com.uhg.optum.ssmo.otnd.service.MoMService;

@Controller
public class BaseMoMController {

	@Autowired
	private MoMService momservice;
	private final static Logger logger = LoggerFactory
			.getLogger(BaseMoMController.class);

	@RequestMapping(value = "/searchMOM", method = RequestMethod.POST)
	public @ResponseBody List<MoMUnit> searchMOM(@RequestParam String title,
			@RequestParam String author, @RequestParam String createDate) {
		logger.debug("[searchMOM] title: "+title);
		List<MoMUnit> updates = momservice.searchMOM(new MoMUnit());

		return updates;
	}

}