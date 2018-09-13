package com.org.cygs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.MissionHistoryDao;
import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.MissionHistory;
import com.org.cygs.service.MissionHistoryService;


@Service("missionHistoryService")
public class MissionHistoryServiceImpl implements MissionHistoryService{
	
	@Resource
	private MissionHistoryDao missionHistoryDao;
	
	public void addMissionHistory(Mission mission){
		
		missionHistoryDao.addMissionHistory(mission);
	}
	
	public List<MissionHistory> getMHListByMId(String mId){
		return missionHistoryDao.getMHListByMId(mId);
	}
	
	public MissionHistory getMHByMhId(String mhId){
		return missionHistoryDao.getMHByMhId(mhId);
	}
	
	public String getMaxMhIdBymId(String mId){
		return missionHistoryDao.getMaxMhIdBymId(mId);
	}

}
