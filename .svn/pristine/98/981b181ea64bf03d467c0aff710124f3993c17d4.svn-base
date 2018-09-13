package com.org.cygs.service;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.Job;
import com.org.cygs.pojo.PagePojo;

public interface JobService {
	
	public void addJob(Job job);
	
	public void updateJob(Job job);
	
	public void deleteJob(String  id);
	
	public PagePojo<Job> slelectJobList(int pageNum, int pageSize,Job job);
	
    public List<Job> selectJobs(Job job);
    
    public List<Job> selectJobByPsId(String psId);
    
    public Job selectJob(String jobKey);
    
    public Map<String, Object> getJobList(Map<String, Object> map);
    public int getJobCount(Map<String, Object> map);
    
    public Job getNewJobKey();
}