package com.org.cygs.dao;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.CheckUnit;
import com.org.cygs.pojo.Permission;

public interface CheckUnitDao {

	public List<CheckUnit> getAllCheckUnit();
	public int checkUnitAdd(CheckUnit cu);
	public int checkUnitEdit(CheckUnit cu);
	public int checkUnitDelete(String cuId);
	public CheckUnit getCheckUnitById(String cuId);
	
	//用于判断新增或修改是否重复
	public List<CheckUnit> judgeCheckUnit(CheckUnit cu);
	
	//查找结算单位
	public List<CheckUnit> selectCheckUnitListByName(String cuName);
	
	//查询所有结算单位
	public List<CheckUnit> selectAllCheckUnit();
	
	public List<CheckUnit> getCheckUnitList(Map<String, Object> map);
	public int getCheckUnitCount(Map<String, Object> map);
}
