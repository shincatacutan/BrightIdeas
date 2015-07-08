package com.uhg.optum.ssmo.otnd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uhg.optum.ssmo.otnd.dao.MoMDAO;
import com.uhg.optum.ssmo.otnd.entity.MoMUnit;
import com.uhg.optum.ssmo.otnd.service.MoMService;

@Service
public class MoMServiceImpl implements MoMService {
	
	@Autowired
	private MoMDAO momDAO;
	
	@Override
	public List<MoMUnit> searchMOM(MoMUnit mom) {
		List<MoMUnit> updates = momDAO.searchMoM(mom);
		
		return updates;
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
