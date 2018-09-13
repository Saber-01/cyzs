package com.org.cygs.dao;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.MissionType;
import com.org.cygs.pojo.User;

public interface MissionTypeDao {

	public List<MissionType> getMissionType();
	
    public List<MissionType> getMissionTypeList(Map<String, Object> map);
    public int getMissionTypeCount(Map<String, Object> map);
    
    
	public int missionDelete(String mtId);
	public int missionEdit(MissionType mt);
	public MissionType getMissionTypeById(String mtId);
	public int missionAdd(MissionType mt);
	
	//ͨ���������Ż������ƣ��ж��Ƿ��ظ�
	public List<MissionType> judgeMissionType(MissionType mt);
	
	//ͨ�����������ƻ�ȡID
	public MissionType getMissionTypeByName(String mtName);
}
