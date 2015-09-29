package com.optum.operations.momlibrary.dao;

import java.util.List;

import com.optum.operations.momlibrary.entity.MomUpdate;

public interface MoMDao {
	public List<MomUpdate> searchMoM(MomUpdate mom);
	public void addMoM (MomUpdate mom);
	public void updateMoM (MomUpdate mom);
	public void deleteMoM (MomUpdate mom);
	
}
