package com.org.cygs.service;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.PrIndexPC;
import com.org.cygs.pojo.Project;
import com.org.cygs.pojo.User;

public interface ProjectService {
	
	public List<Project> getAllProject();
	public List<Project> getAllProjectName();//������ȡid �� name
	
	public Project getProjectById(String prId);
	public int projectAdd(Project pr);
	public int projectEdit(Project pr);
	public int projectDelete(String prId);
	public List<User> getAllRole(String roleType);
	public List<PrIndexPC> getProjectDetailById(String prId);
	public int projectDetailDeleteById(String pcPId);
	public int projectDetailEdit(PrIndexPC pp);
	public int projectDetailAdd(PrIndexPC pp);
	public PrIndexPC getPrIndexPCById(String pcPId);
	
	//���ڲ�����޸�ʱ���ж��Ƿ��ظ����ֵ
	public List<Project> judgeProject(Project pr);
	//���ڶԹ�����ϸ���롢�޸�ʱ���ж��Ƿ��ظ�
	public List<PrIndexPC> judgeProjectDetail(PrIndexPC pp);
	
	public List<Project> getProjectByUId(String uId);
	public List<Project> getProjectByChengkonId(String uId);  //�鿴�ɿز������Ӧ�Ĺ���
	public List<Project> getProjectByChengkonFuId(String uId);//�鿴�ɿز�    --�� --�����Ӧ�Ĺ���
	public List<Project> getProjectByZhuguanId(String uId);  //�鿴����Ԥ���Ӧ�Ĺ���
	public List<Project> getProjectByShenJiId(String uId);  //�鿴��ƶ�Ӧ�Ĺ���
	public String getUserName(String prId);  //�鿴���̶�Ӧ����Ŀ��������
	public String getShenJiName(String prId);//�鿴���̶�Ӧ���������
	public String getZhuGuanName(String prId); //�鿴���̶�Ӧ������Ԥ��
	public List<Project> getProjectByGongzhang(String uId); // �鿴������Ӧ�Ĺ���
	public List<Project> getProjectByYusuan(String uId);	// �鿴Ԥ����Ա��Ӧ�Ĺ���
	
	
	public int setPageStatus(Map<String, Object> map);   //���÷�ҳ����,���ص�ǰҳ��
	
	public Page<Project> selectProjectList(Map<String, Object> map);
	public int getPrCode();
	public int getCount();
	
	//�����û���¼����ȡ��֮�����Ĺ����б�
	public List<Project> getProjectListByUserLoginName(String userLoginName);
	

	public List<Project> getProjectByPrName(String prName);  //�鿴�������ƶ�Ӧ�Ĺ���
	

	public Map<String, Object> getProjectList(Map<String, Object> map);

	public List<PrIndexPC> getPrIndexPCByOption(String prId,String unitNumber);  //�鿴�������ƺͶ��Ŷ�Ӧ�Ĺ���PrindexPc

	public Map<String,Object> getPrIndexPCList(Map<String,Object> map);
}
