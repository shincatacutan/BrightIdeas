package com.uhg.optum.ssmo.otnd.dao;

import java.util.List;

import com.uhg.optum.ssmo.otnd.entity.Inquiry;

public interface InquiryDao {
	public void addInquiry(Inquiry inquiry);
	public List<Inquiry> viewAll();
}
