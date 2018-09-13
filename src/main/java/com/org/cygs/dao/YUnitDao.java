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
	
	//�����޸ġ������ж�
	public List<Unit> judgeUnit(Unit unit);
	
	//����������������λʱ�����ñ��
	public int getCount();
	
	public Unit getUnitByName(String unName);
	
    public List<Unit> getUnitList(Map<String, Object> map);
    public int getUnitCount(Map<String, Object> map);
}
