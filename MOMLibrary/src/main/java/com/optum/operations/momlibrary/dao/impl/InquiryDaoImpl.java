package com.optum.operations.momlibrary.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.optum.operations.momlibrary.dao.InquiryDao;
import com.optum.operations.momlibrary.entity.Inquiry;
import com.optum.operations.momlibrary.entity.InquiryReply;

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

	@Override
	public InquiryReply viewReply(Inquiry inquiry) {
		Criteria criteria = getSession().createCriteria(InquiryReply.class);
        criteria.add(Restrictions.eq("inqId",inquiry));

		return (InquiryReply) criteria.uniqueResult();
	}

	@Override
	public void addReply(InquiryReply reply) {
		persist(reply);
		
	}

	@Override
	public void updateReply(InquiryReply reply) {
		update(reply);
	}

	@Override
	public Inquiry getInquiry(Inquiry inquiry) {
		Criteria criteria = getSession().createCriteria(Inquiry.class);
        criteria.add(Restrictions.eq("id",inquiry.getInqId()));
		return (Inquiry) criteria.uniqueResult();
	}

	@Override
	public void updateInquiry(Inquiry inquiry) {
		update(inquiry);
	}

}
