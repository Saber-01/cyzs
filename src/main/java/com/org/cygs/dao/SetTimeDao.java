package com.org.cygs.dao;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.SetTime;

public interface SetTimeDao {

	public List<SetTime> getSetTimeByStatusSYearSMonth(int status,int sYear,int sMonth);
	//获取统计月度时间范围
	public List<SetTime> getSetTime(Map<String, Object> map);
	public int getSetTimeCount();
	
	//获取最大年月
	public SetTime getMaxSetTimeYearMonth();
	
	public void insertSetTime(SetTime st);
	
	public SetTime getSetTimeByPrimaryKey(String setId);
	
	public void updateSetTimeByPrimaryKey(SetTime st);
	
	public List<SetTime> getSetTimeList1(Map<String, Object> map);
	public int getSetTimeCount1(Map<String, Object> map);
}
