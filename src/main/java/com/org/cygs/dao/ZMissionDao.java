package com.org.cygs.dao;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.Unit;
import com.org.cygs.pojo.ZMission;

public interface ZMissionDao {

    public void insertSelective(ZMission record);

    public ZMission getZMissionByMId(String zMId);
    
	//按条件查询任务书
  	public List<ZMission> getZMissionList(Map<String, Object> map);
  	public int getZCount(Map<String, Object> map);
  	
}