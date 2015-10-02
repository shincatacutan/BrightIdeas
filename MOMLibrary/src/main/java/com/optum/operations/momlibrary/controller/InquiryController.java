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
import com.optum.operations.momlibrary.entity.InquiryReply;
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
		logger.debug("[addInquiry] title: " + title + " body " + body);
		Inquiry inquiry = new Inquiry();
		inquiry.setBody(body);
		inquiry.setTitle(title);
		User employee = ((User) request.getSession().getAttribute("employee"));
		inquiry.setCreateUser(employee);
		inquiry.setCreateDate(new LocalDate());
		// inquiry.setUpdateUser(employee);
		// inquiry.setUpdateDate(new LocalDate());
		inquiry.setStatus("Open");
		inquiryService.addInquiry(inquiry);
		return "Success";
	}

	@RequestMapping(value = "/getAllInquiry", method = RequestMethod.POST)
	public @ResponseBody List<InquiryVO> getAllInquiry() {
		List<Inquiry> inquiryList = inquiryService.viewAll();
		List<InquiryVO> returnList = new ArrayList<InquiryVO>();

		for (Inquiry inquiry : inquiryList) {
			InquiryReply reply = inquiryService.viewReply(inquiry);
			if (null == reply) {
				reply = new InquiryReply();
			}
			returnList.add(new InquiryVO(String.valueOf(inquiry.getInqId()),
					inquiry.getTitle(), inquiry.getBody(), inquiry
							.getCreateDate().toString(), null == inquiry
							.getUpdateDate() ? "NEW" : inquiry.getUpdateDate()
							.toString(), inquiry.getCreateUser().getLanID(),
					null == inquiry.getUpdateUser() ? "NEW" : inquiry
							.getUpdateUser().getLanID(), inquiry.getStatus(),
					reply.getReply()));
		}
		return returnList;
	}

	@RequestMapping(value = "/addReply", method = RequestMethod.POST)
	public @ResponseBody String addReply(@RequestParam String id,
			@RequestParam String reply, @RequestParam String status,
			HttpServletRequest request) {
		logger.debug("[addReply] id: " + id);
		Inquiry ownerInq = new Inquiry(Integer.parseInt(id));

		InquiryReply replyBody = inquiryService.viewReply(ownerInq);
		if (null != replyBody) {
			replyBody.setReply(reply);
			inquiryService.updateReply(replyBody);
		} else {
			replyBody = new InquiryReply();
			replyBody.setInqId(ownerInq);
			replyBody.setReply(reply);
			replyBody.setReplyDate(new LocalDate());
			inquiryService.addReply(replyBody);
		}

		User employee = ((User) request.getSession().getAttribute("employee"));

		updateExistingInquiry(status, ownerInq, employee);

		return "SUCCESS";
	}

	private void updateExistingInquiry(String status, Inquiry ownerInq,
			User employee) {
		Inquiry inq = inquiryService.getInquiry(ownerInq);
		inq.setStatus(status);
		inq.setUpdateDate(new LocalDate());
		inq.setUpdateUser(employee);
		inquiryService.updateInquiry(inq);
	}
}
