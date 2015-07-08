package com.uhg.optum.ssmo.otnd.dao;

import java.util.List;

import com.uhg.optum.ssmo.otnd.entity.MoMUnit;

public interface MoMDAO {
	public List<MoMUnit> searchMoM(MoMUnit mom);
	public void addMoM (MoMUnit mom);
	public void updateMoM (MoMUnit mom);
	public void deleteMoM (MoMUnit mom);
	
}
