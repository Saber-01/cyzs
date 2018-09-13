package com.org.cygs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.MissionHistoryContentDao;
import com.org.cygs.pojo.MissionHistoryContent;
import com.org.cygs.service.MissionHistoryContentService;

@Service("missionHistoryContentService")
public class MissionHistoryContentServiceImpl implements MissionHistoryContentService{
	
	@Resource
	private MissionHistoryContentDao missionHistoryContentDao;
	
	public void addMissionHitoryContent(MissionHistoryContent missionContent){
		
		missionHistoryContentDao.addMissionHitoryContent(missionContent);
	}
	public List<MissionHistoryContent> getMHCList(String mhId){
		return missionHistoryContentDao.getMHCList(mhId);
	}
	
	public List<MissionHistoryContent> getHTNQTMHCList(String mhId) {
		return missionHistoryContentDao.getHTNQTMHCList(mhId);
	}
}
