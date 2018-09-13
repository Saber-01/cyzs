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
	
	//�����޸ġ������ж�
	public List<Unit> judgeUnit(Unit unit);
	
	//��ҳ�б�
	public PagePojo<Unit> selectUnitList(int pageNum,int pageSize);
	
	//�����������ñ��
	public int getCount();
	
	public Unit getUnitByName(String unName);
	
	public Map<String, Object> getUnitList(Map<String, Object> map);
}
