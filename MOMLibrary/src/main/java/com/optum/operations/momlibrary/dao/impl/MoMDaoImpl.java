package com.optum.operations.momlibrary.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.optum.operations.momlibrary.dao.MoMDao;
import com.optum.operations.momlibrary.entity.MomUpdate;
@Transactional
@Repository
public class MoMDaoImpl extends AbstractDao implements MoMDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<MomUpdate> searchMoM(MomUpdate mom) {
		Criteria criteria = getSession().createCriteria(MomUpdate.class);
		return (List<MomUpdate>) criteria.list();
	}

	@Override
	public void addMoM(MomUpdate mom) {
		persist(mom);
	}

	@Override
	public void updateMoM(MomUpdate mom) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMoM(MomUpdate mom) {
		// TODO Auto-generated method stub
		
	}

}
