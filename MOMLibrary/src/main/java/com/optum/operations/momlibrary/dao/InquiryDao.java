package com.optum.operations.momlibrary.dao;

import java.util.List;

import com.optum.operations.momlibrary.entity.Inquiry;

public interface InquiryDao {
	public void addInquiry(Inquiry inquiry);
	public List<Inquiry> viewAll();
}
