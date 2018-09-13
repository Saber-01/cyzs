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
	
	//��������ʱ���ж��Ƿ��ظ�
	public List<ProjectIndexS> judgeProjectIndexS(ProjectIndexS pis);
	
	//��ȡһҳ������
	public List<ProjectIndexS> selectProjectIndexS(Map<String,Object>map);
	
	//ͳ�ƺ�������Ŀ
	public int getCount(Map<String,Object>map);
    public List<ProjectIndexS> getProjectIndexSList(Map<String, Object> map);
	
	List<ProjectIndexS> selectSProject(String uId);	// �鿴������Ӧ�Ĺ���
}
