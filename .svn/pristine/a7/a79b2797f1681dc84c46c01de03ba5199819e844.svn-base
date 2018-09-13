package com.org.cygs.dao;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.SystemOperation;
import com.org.cygs.pojo.User;

public interface SystemOperationDao {
	
	public List<SystemOperation> getSystemOperationList(Map<String, Object> map);
	
    void insertSysOperation(SystemOperation record);
    
    public int getSystemOperationCount(Map<String, Object> map);
    
    List<SystemOperation> getSysPrList();
    
    
	//调用BackAllMission存储过程,结算工程
  	public void backallmission(Map<String, Object> map);
    
    // 数据库备份
    void backupDatabase(Map<String, Object> map);

}