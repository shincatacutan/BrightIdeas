package com.uhg.optum.ssmo.otnd.service;

import java.util.List;

import com.uhg.optum.ssmo.otnd.entity.MoMUnit;

public interface MoMService {
	public List<MoMUnit> searchMOM(MoMUnit mom);
	public void addMoM (MoMUnit mom);
	public void updateMoM (MoMUnit mom);
	public void deleteMoM (MoMUnit mom);
}
