package com.org.cygs.dao;



import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.Job;


public interface JobDao {
	public void updateJob(Job job);
	
	public void insertJob(Job job);
	
	public void deleteJob(String jobKey);
	
	public Job selectJob(String jobKey);//����������ѯ�����Ĺ�����Ŀ
	
	public List<Job> selectJobByPsId(String psId);//���ݹ��̲�λ������ѯ�ֲ��µ����й���
 	
    public List<Job> selectJobs(Job job);//���ݸ����ĸ����ؼ��ֲ�ѯ���������Ĺ���
	
    public List<Job> getJobList(Map<String, Object> map);
    public int getJobCount(Map<String, Object> map);
	
    public Job getNewJobKey();
		
}
