package com.optum.operations.momlibrary.service;

import java.util.List;

import com.optum.operations.momlibrary.entity.MoMUnit;

public interface MoMService {
	public List<MoMUnit> searchMOM(MoMUnit mom);
	public void addMoM (MoMUnit mom);
	public void updateMoM (MoMUnit mom);
	public void deleteMoM (MoMUnit mom);
}
