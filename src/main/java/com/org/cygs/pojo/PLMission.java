package com.org.cygs.pojo;

import java.util.List;

public class PLMission {
	private Mission mission;
	private List<MissionContent> mcList;
	
	public Mission getMission() {
		return mission;
	}
	public void setMission(Mission mission) {
		this.mission = mission;
	}
	public List<MissionContent> getMcList() {
		return mcList;
	}
	public void setMcList(List<MissionContent> mcList) {
		this.mcList = mcList;
	}
	

}
