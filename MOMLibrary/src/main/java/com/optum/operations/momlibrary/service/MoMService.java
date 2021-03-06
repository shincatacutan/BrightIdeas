package com.optum.operations.momlibrary.service;

import java.util.List;

import com.optum.operations.momlibrary.entity.MomUpdate;

public interface MoMService {
	public List<MomUpdate> searchMOM(MomUpdate mom);
	public void addMoM (MomUpdate mom);
	public void updateMoM (MomUpdate mom);
	public void deleteMoM (MomUpdate mom);
}
