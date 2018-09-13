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
    
    
	//����BackAllMission�洢����,���㹤��
  	public void backallmission(Map<String, Object> map);
    
    // ���ݿⱸ��
    void backupDatabase(Map<String, Object> map);

}