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
	
	//��������ʱ���ж��Ƿ��ظ�
	public List<ProjectIndexS> judgeProjectIndexS(ProjectIndexS pis);
	
	public int setPageStatus(Map<String, Object> map);   //���÷�ҳ����,���ص�ǰҳ��
	
	public Page<ProjectIndexS> selectProjectIndexSList(Map<String, Object> map);
	
	// ��ѯ������Ӧ�Ĺ���
	public List<ProjectIndexS> selectSProject(String uId);
	
	public Map<String, Object> getProjectIndexSList(Map<String, Object> map);
}
