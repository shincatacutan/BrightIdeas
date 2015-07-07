package com.optum.operations.momlibrary.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.optum.operations.momlibrary.dao.InquiryDao;
import com.optum.operations.momlibrary.entity.Inquiry;

@Repository
public class InquiryDaoImpl extends AbstractDao implements InquiryDao {

	@Override
	public void addInquiry(Inquiry inquiry) {
		persist(inquiry);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Inquiry> viewAll() {
		Criteria criteria = getSession().createCriteria(Inquiry.class);
		return (List<Inquiry>) criteria.list();
	}

}
