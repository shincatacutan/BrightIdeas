package com.optum.operations.momlibrary.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optum.operations.momlibrary.dao.MoMDao;
import com.optum.operations.momlibrary.entity.MomUpdate;
import com.optum.operations.momlibrary.service.MoMService;

@Service
public class MoMServiceImpl implements MoMService {
	
	@Autowired
	private MoMDao momDAO;
	
	@Override
	public List<MomUpdate> searchMOM(MomUpdate mom) {
		List<MomUpdate> updates = momDAO.searchMoM(mom);
		
		return updates;
	}

	@Override
	public void addMoM(MomUpdate mom) {
		momDAO.addMoM(mom);
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
