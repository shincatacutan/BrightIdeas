package com.optum.operations.momlibrary.dao;

import java.util.List;

import com.optum.operations.momlibrary.entity.MoMUnit;

public interface MoMDAO {
	public List<MoMUnit> searchMoM(MoMUnit mom);
	public void addMoM (MoMUnit mom);
	public void updateMoM (MoMUnit mom);
	public void deleteMoM (MoMUnit mom);
	
}
