package com.optum.operations.momlibrary.service;

import java.util.List;

import com.optum.operations.momlibrary.entity.Inquiry;
import com.optum.operations.momlibrary.entity.InquiryReply;

public interface InquiryService {
	public void addInquiry(Inquiry inquiry);
	public List<Inquiry> viewAll();
	public void updateInquiry(Inquiry inquiry);
	public Inquiry getInquiry(Inquiry inquiry);
	public InquiryReply viewReply(Inquiry inquiry);
	public void addReply(InquiryReply reply);
	public void updateReply(InquiryReply reply);
}
