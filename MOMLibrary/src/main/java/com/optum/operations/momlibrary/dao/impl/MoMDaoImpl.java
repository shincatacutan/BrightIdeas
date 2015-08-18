package com.optum.operations.momlibrary.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.optum.operations.momlibrary.dao.MoMDAO;
import com.optum.operations.momlibrary.entity.MoMUnit;
@Transactional
@Repository
public class MoMDaoImpl extends AbstractDao implements MoMDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<MoMUnit> searchMoM(MoMUnit mom) {
		Criteria criteria = getSession().createCriteria(MoMUnit.class);
		return (List<MoMUnit>) criteria.list();
	}

	@Override
	public void addMoM(MoMUnit mom) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMoM(MoMUnit mom) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMoM(MoMUnit mom) {
		// TODO Auto-generated method stub
		
	}

}
