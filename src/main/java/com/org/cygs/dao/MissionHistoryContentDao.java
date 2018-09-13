package com.org.cygs.dao;

import java.util.List;

import com.org.cygs.pojo.MissionHistoryContent;

public interface MissionHistoryContentDao {
	
	public void addMissionHitoryContent(MissionHistoryContent missionContent);
	public List<MissionHistoryContent> getMHCList(String mhId);
	public List<MissionHistoryContent> getHTNQTMHCList(String mhId);

}
