package com.org.cygs.service;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.CheckUnit;
import com.org.cygs.pojo.PagePojo;

public interface CheckUnitService {

	public List<CheckUnit> getAllCheckUnit();
	public int checkUnitAdd(CheckUnit cu);
	public int checkUnitEdit(CheckUnit cu);
	public int checkUnitDelete(String cuId);
	public CheckUnit getCheckUnitById(String cuId);
	
	//用于判断新增或修改是否重复
	public List<CheckUnit> judgeCheckUnit(CheckUnit cu);
	
	public PagePojo<CheckUnit> selectCheckUnitListByName(int pageNo,int pageSize,String cuName);
	
	// 查询所有结算单位
	public List<CheckUnit> selectAllCheckUnit();
	
	//查找结算单位
	public List<CheckUnit> selectCheckUnitListByName(String cuName);
	
	public Map<String, Object> getCheckUnitList(Map<String, Object> map);
	
	public int getCheckUnitCount(Map<String,Object> map);
}
