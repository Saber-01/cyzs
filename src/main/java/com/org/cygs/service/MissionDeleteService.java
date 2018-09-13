package com.org.cygs.service;

import com.org.cygs.pojo.MissionDelete;

public interface MissionDeleteService {
	public void insertMissionDelete(MissionDelete record);
	public String getMdIdByMax();
	public String getMdMCode(String mCode);
}
