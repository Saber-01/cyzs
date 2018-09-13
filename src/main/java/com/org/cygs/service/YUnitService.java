package com.org.cygs.service;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.Unit;

public interface YUnitService {

	public int unitAdd(Unit unit);
	public int unitDeleteById(String unId);
	public int unitEdit(Unit unit);
	public List<Unit> getAllUnit();
	public Unit getUnitById(String unId);
	
	//用于修改、新增判断
	public List<Unit> judgeUnit(Unit unit);
	
	//分页列表
	public PagePojo<Unit> selectUnitList(int pageNum,int pageSize);
	
	//计数用于设置编号
	public int getCount();
	
	public Unit getUnitByName(String unName);
	
	public Map<String, Object> getUnitList(Map<String, Object> map);
}
