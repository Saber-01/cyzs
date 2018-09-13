package com.org.cygs.service;

import java.util.List;

import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.MissionHistory;

public interface MissionHistoryService {
	public void addMissionHistory(Mission mission);
	public List<MissionHistory> getMHListByMId(String mId);
	public MissionHistory getMHByMhId(String mhId);
	public String getMaxMhIdBymId(String mId);

}
