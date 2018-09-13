package com.org.cygs.service;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.MissionType;
import com.org.cygs.pojo.PagePojo;

public interface MissionTypeService {

	public List<MissionType> getMissionType();
	public int missionDelete(String mtId);
	public MissionType getMissionTypeById(String mtId);
	public int missionEdit(MissionType mt);
	public int missionAdd(MissionType mt);
	
	//通过任务书编号或者名称，判断是否重复
	public List<MissionType> judgeMissionType(MissionType mt);
	//通过任务书名称获取Id
	public MissionType getMissionTypeByName(String mtName);
	
	//分页
	public PagePojo<MissionType> selectMissionTypeList(int pageNo,int pageSize);
	
	public Map<String, Object> getMissionTypeList(Map<String, Object> map);

}
