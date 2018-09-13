package com.org.cygs.service;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.PrIndexPC;

public interface ProjectIndexPCService {
	// 工程和工程类别：根据prId查询工程的栋号
	public List<PrIndexPC> selectUnitByPrId(String prId);
	
	// 工程和工程类别：根据主键查询工程
	public PrIndexPC selectPrPcByPrimaryKey(String pcPId);
	
	// 根据工程集合查询所有栋号
	public List<PrIndexPC> getUnitNumberByPrList(Map<String, Object> map);
}
