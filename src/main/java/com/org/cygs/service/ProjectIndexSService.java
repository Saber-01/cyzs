package com.org.cygs.service;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.ProjectIndexS;

public interface ProjectIndexSService {

	public List<ProjectIndexS> getAllProjectIndexS();
	public ProjectIndexS getProjectIndexSById(String prSId);
	public int projectIndexSAdd(ProjectIndexS pris);
	public int projectIndexSEdit(ProjectIndexS pris);
	public int projectIndexSDelete(String prsId);
	public List<ProjectIndexS> getAllGzName();
	public List<ProjectIndexS> projectIndexSSearch(ProjectIndexS pis);
	
	//新增数据时，判断是否重复
	public List<ProjectIndexS> judgeProjectIndexS(ProjectIndexS pis);
	
	public int setPageStatus(Map<String, Object> map);   //设置分页数据,返回当前页码
	
	public Page<ProjectIndexS> selectProjectIndexSList(Map<String, Object> map);
	
	// 查询工长对应的工程
	public List<ProjectIndexS> selectSProject(String uId);
	
	public Map<String, Object> getProjectIndexSList(Map<String, Object> map);
}
