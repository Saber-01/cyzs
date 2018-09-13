package com.org.cygs.dao;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.MissionType;
import com.org.cygs.pojo.ProjectIndexS;

public interface ProjectIndexSDao {

	public List<ProjectIndexS> getAllProjectIndexS();
	public ProjectIndexS getProjectIndexSById(String prSId);
	public int projectIndexSAdd(ProjectIndexS pris);
	public int projectIndexSEdit(ProjectIndexS pris);
	public int projectIndexSDelete(String prSId);
	public List<ProjectIndexS> getAllGzName();
	public List<ProjectIndexS> projectIndexSSearch(ProjectIndexS pis);
	
	//新增数据时，判断是否重复
	public List<ProjectIndexS> judgeProjectIndexS(ProjectIndexS pis);
	
	//获取一页的数据
	public List<ProjectIndexS> selectProjectIndexS(Map<String,Object>map);
	
	//统计含多少条目
	public int getCount(Map<String,Object>map);
    public List<ProjectIndexS> getProjectIndexSList(Map<String, Object> map);
	
	List<ProjectIndexS> selectSProject(String uId);	// 查看工长对应的工程
}
