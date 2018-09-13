package com.org.cygs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.MissionDeleteDao;
import com.org.cygs.pojo.MissionDelete;
import com.org.cygs.service.MissionDeleteService;

@Service
public class MissionDeleteServiceImpl implements MissionDeleteService {
	@Resource
	private MissionDeleteDao missionDeleteDao;
	
	public void insertMissionDelete(MissionDelete record) {
		missionDeleteDao.insertMissionDelete(record);
	}
	
	public String getMdIdByMax() {
		return missionDeleteDao.getMdIdByMax();
	}
	
	public String getMdMCode(String mCode) {
		return missionDeleteDao.getMdMCode(mCode);
	}
	
}
