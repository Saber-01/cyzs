package com.org.cygs.service;

import java.util.List;

import com.org.cygs.pojo.Department;
import com.org.cygs.pojo.InCheckUnit;
import com.org.cygs.pojo.InProject;
import com.org.cygs.pojo.InProjectVo;
import com.org.cygs.pojo.Project;

public interface LockInfoService {
	
/*	// 工程类：根据主键查询工程
	public Project selectPrByPrimaryKey(String prId);
	
	// 才行工程所有的信息
	//public List<PrIndexSVo> selectProjectAllInfo(String prId);
	
	// 查询所有部门名称和编号
	public List<Department> selectDpNameandCode();
	
	// 根据部门编码查询部门名称
	public Department selectDpByCode(String dpCode);*/
	// 查询所有工程
	// public List<Project> selectAllProject();
	
	// 通过uid（安装施工负责人）查询安装工程
	public List<InProjectVo> selectByUid(String uid);
	public List<InProject> selectInPrByUid(String uid);
	// 通过主键查询查询安装工程
	public List<InProjectVo> selectInPrPcByInPrId(String inPrId);
	// 查询所有安装结算单位
	public List<InCheckUnit> selectAllInCu();
	public InCheckUnit selectInCuByPrimaryKey(String inCuId);
    
    
}
