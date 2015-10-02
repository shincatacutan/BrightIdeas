package com.optum.operations.momlibrary.dao;

import java.util.List;

import com.optum.operations.momlibrary.entity.Inquiry;
import com.optum.operations.momlibrary.entity.InquiryReply;

public interface InquiryDao {
	public void addInquiry(Inquiry inquiry);
	public Inquiry getInquiry(Inquiry inquiry);
	public void updateInquiry(Inquiry inquiry);
	public List<Inquiry> viewAll();
	public InquiryReply viewReply(Inquiry inquiry);
	public void addReply(InquiryReply reply);
	public void updateReply(InquiryReply reply);
}
