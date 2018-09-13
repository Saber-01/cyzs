package com.org.cygs.service;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.PartPosition;

public interface PartPositionService {
	public void insertPartPosition(PartPosition partPosition);
	
	public PartPosition selectPartPosition(String id);
	
	public void deletePartPosition(String id);
	
	public void updatePartPosition(PartPosition partPosition);
	// 新建任务书时使用
	public List<PartPosition> getPartPositionByPId(String id);
	// 新建合同单价时使用
	public List<PartPosition> selectPartPositionByPId(String pId);
	
	public List<PartPosition> getPartPositionList1(PartPosition partPosition);
	
	public Map<String, Object> getPartPositionList(Map<String, Object> map);
}
