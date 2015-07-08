package com.uhg.optum.ssmo.otnd.service;

import java.util.List;

import com.uhg.optum.ssmo.otnd.entity.Inquiry;

public interface InquiryService {
	public void addInquiry(Inquiry inquiry);
	public List<Inquiry> viewAll();
}
