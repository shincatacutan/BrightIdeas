package com.uhg.optum.ssmo.otnd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uhg.optum.ssmo.otnd.dao.InquiryDao;
import com.uhg.optum.ssmo.otnd.entity.Inquiry;
import com.uhg.optum.ssmo.otnd.service.InquiryService;

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

}
