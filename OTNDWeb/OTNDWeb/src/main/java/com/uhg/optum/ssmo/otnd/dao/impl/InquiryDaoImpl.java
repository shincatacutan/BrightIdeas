package com.uhg.optum.ssmo.otnd.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.uhg.optum.ssmo.otnd.dao.InquiryDao;
import com.uhg.optum.ssmo.otnd.entity.Inquiry;

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
