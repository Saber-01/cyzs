package com.org.cygs.dao;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.MissionType;
import com.org.cygs.pojo.Unit;

public interface YUnitDao {

	public int unitAdd(Unit unit);
	public int unitDeleteById(String unId);
	public int unitEdit(Unit unit);
	public List<Unit> getAllUnit();
	public Unit getUnitById(String unId);
	
	//用于修改、新增判断
	public List<Unit> judgeUnit(Unit unit);
	
	//计数，用于新增单位时，设置编号
	public int getCount();
	
	public Unit getUnitByName(String unName);
	
    public List<Unit> getUnitList(Map<String, Object> map);
    public int getUnitCount(Map<String, Object> map);
}
