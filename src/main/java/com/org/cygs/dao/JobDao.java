package com.org.cygs.dao;



import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.Job;


public interface JobDao {
	public void updateJob(Job job);
	
	public void insertJob(Job job);
	
	public void deleteJob(String jobKey);
	
	public Job selectJob(String jobKey);//根据主键查询单个的工作项目
	
	public List<Job> selectJobByPsId(String psId);//根据工程部位主键查询分部下的所有工作
 	
    public List<Job> selectJobs(Job job);//根据给出的各个关键字查询符合条件的工作
	
    public List<Job> getJobList(Map<String, Object> map);
    public int getJobCount(Map<String, Object> map);
	
    public Job getNewJobKey();
		
}
