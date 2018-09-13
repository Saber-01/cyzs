package com.org.cygs.service;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.SystemOperation;

public interface SystemOperationService {
	public void insertSysOperation(SystemOperation record);
	
	public Page<SystemOperation> getSystemOperationList(Map<String, Object> map);
	
	public int getSystemOperationCount(Map<String, Object> map);
	
	public List<SystemOperation> getSysPrList();
	
	//调用BackAllMission存储过程
	public void backallmission(Map<String, Object> map);
	
	// 数据库备份
	public void backupDatabase(Map<String, Object> map);
	
    public Map<String, Object> getSystemOperationList1(Map<String, Object> map);
}
