package com.optum.operations.momlibrary.controller;

import java.util.ArrayList;
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
import com.optum.operations.momlibrary.vo.InquiryVO;

@Controller
public class InquiryController {

	@Autowired
	private InquiryService inquiryService;

	private final static Logger logger = LoggerFactory
			.getLogger(InquiryController.class);

	@RequestMapping(value = "/addInquiry", method = RequestMethod.POST)
	public @ResponseBody String addInquiry(@RequestParam String title,
			@RequestParam String body, HttpServletRequest request) {
		logger.debug("[addInquiry] title: " + title);
		logger.debug("[addInquiry] body: " + body);
		Inquiry inquiry = new Inquiry();
		inquiry.setBody(body);
		inquiry.setTitle(title);
		User employee = ((User) request.getSession().getAttribute("employee"));
		inquiry.setCreateUser(employee);
		inquiry.setCreateDate(new LocalDate());
		inquiry.setUpdateUser(employee);
		inquiry.setUpdateDate(new LocalDate());
		inquiry.setStatus("Open");
		inquiryService.addInquiry(inquiry);
		return "Success";
	}

	@RequestMapping(value = "/getAllInquiry", method = RequestMethod.POST)
	public @ResponseBody List<InquiryVO> getAllInquiry() {
		List<Inquiry> inquiryList = inquiryService.viewAll();
		List<InquiryVO> returnList = new ArrayList<InquiryVO>();

		for (Inquiry inquiry : inquiryList) {
			returnList.add(new InquiryVO(String.valueOf(inquiry.getInqId()),
					inquiry.getTitle(), inquiry.getBody(), inquiry
							.getCreateDate().toString(), inquiry
							.getUpdateDate().toString(), inquiry
							.getCreateUser().getLanID(), inquiry
							.getUpdateUser().getLanID(), inquiry.getStatus()));
		}
		return returnList;
	}
}
