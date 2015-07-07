package com.optum.operations.momlibrary.service;

import java.util.List;

import com.optum.operations.momlibrary.entity.Inquiry;

public interface InquiryService {
	public void addInquiry(Inquiry inquiry);
	public List<Inquiry> viewAll();
}
