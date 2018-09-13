package com.org.cygs.service;

import java.util.List;

import com.org.cygs.pojo.Department;
import com.org.cygs.pojo.InCheckUnit;
import com.org.cygs.pojo.InProject;
import com.org.cygs.pojo.InProjectVo;
import com.org.cygs.pojo.Project;

public interface LockInfoService {
	
/*	// �����ࣺ����������ѯ����
	public Project selectPrByPrimaryKey(String prId);
	
	// ���й������е���Ϣ
	//public List<PrIndexSVo> selectProjectAllInfo(String prId);
	
	// ��ѯ���в������ƺͱ��
	public List<Department> selectDpNameandCode();
	
	// ���ݲ��ű����ѯ��������
	public Department selectDpByCode(String dpCode);*/
	// ��ѯ���й���
	// public List<Project> selectAllProject();
	
	// ͨ��uid����װʩ�������ˣ���ѯ��װ����
	public List<InProjectVo> selectByUid(String uid);
	public List<InProject> selectInPrByUid(String uid);
	// ͨ��������ѯ��ѯ��װ����
	public List<InProjectVo> selectInPrPcByInPrId(String inPrId);
	// ��ѯ���а�װ���㵥λ
	public List<InCheckUnit> selectAllInCu();
	public InCheckUnit selectInCuByPrimaryKey(String inCuId);
    
    
}
