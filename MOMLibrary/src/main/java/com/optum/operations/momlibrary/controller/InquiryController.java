package com.optum.operations.momlibrary.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.optum.operations.momlibrary.entity.Inquiry;
import com.optum.operations.momlibrary.entity.User;
import com.optum.operations.momlibrary.service.InquiryService;

@Controller
public class InquiryController {

	@Autowired
	private InquiryService inquiryService;

	private final static Logger logger = LoggerFactory
			.getLogger(InquiryController.class);

	@RequestMapping(value = "/addInquiry", method = RequestMethod.POST)
	public @ResponseBody String addInquiry(@RequestParam String title, @RequestParam String body, HttpServletRequest request) {
		logger.debug("[addInquiry] title: " + title);
		logger.debug("[addInquiry] body: " + body);
		Inquiry inquiry = new Inquiry();
		inquiry.setBody(body);
		inquiry.setTitle(title);
		
		inquiry.setCreateUser(((User)request.getSession().getAttribute("employee")).getLanID());
		inquiry.setCreateDate(new LocalDate());
		
		inquiryService.addInquiry(inquiry);
		return "Success";
	}
	
	@RequestMapping(value = "/getAllInquiry", method = RequestMethod.POST)
	public @ResponseBody List<Inquiry> getAllInquiry() {
		logger.debug("[getAllInquiry]");
		return (List<Inquiry>) inquiryService.viewAll();
	}

}
