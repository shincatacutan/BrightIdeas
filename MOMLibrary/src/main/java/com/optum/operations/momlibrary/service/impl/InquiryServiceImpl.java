package com.optum.operations.momlibrary.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.optum.operations.momlibrary.dao.InquiryDao;
import com.optum.operations.momlibrary.entity.Inquiry;
import com.optum.operations.momlibrary.entity.InquiryReply;
import com.optum.operations.momlibrary.service.InquiryService;

@Service
@Transactional
public class InquiryServiceImpl implements InquiryService {
	
	@Autowired
	private InquiryDao inquiryDao;

	@Override
	public void addInquiry(Inquiry inquiry) {
		inquiryDao.addInquiry(inquiry);
	}

	@Override
	public List<Inquiry> viewAll() {
		return inquiryDao.viewAll();
	}

	@Override
	public InquiryReply viewReply(Inquiry inquiry) {
		return inquiryDao.viewReply(inquiry);
	}

	@Override
	public void addReply(InquiryReply reply) {
		inquiryDao.addReply(reply);
	}

	@Override
	public void updateReply(InquiryReply reply) {
		inquiryDao.updateReply(reply);
	}

	@Override
	public Inquiry getInquiry(Inquiry inquiry) {
		return inquiryDao.getInquiry(inquiry);
	}

	@Override
	public void updateInquiry(Inquiry inquiry) {
		inquiryDao.updateInquiry(inquiry);
	}

}
