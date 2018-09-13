package com.org.cygs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.org.cygs.dao.JobDao;
import com.org.cygs.pojo.Job;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.PartPosition;
import com.org.cygs.service.JobService;

@Service
public class JobServiceImpl implements JobService{
    @Resource
    private JobDao jobDao;
    
	public void addJob(Job job){
		jobDao.insertJob(job);
	}
	
	public void updateJob(Job job){
		jobDao.updateJob(job);
	}
	
	public void deleteJob(String  id){
		jobDao.deleteJob(id);
		
	}
	
	
	
  
    
    public List<Job> selectJobByPsId(String psId){
    	return jobDao.selectJobByPsId(psId);
    }
    
    public Job selectJob(String jobKey){
    	
    	return jobDao.selectJob(jobKey);
    }
	
    public List<Job> selectJobs(Job job){   	
    	return jobDao.selectJobs(job);
    }
    
    public PagePojo<Job> slelectJobList(int pageNum, int pageSize,Job job){
    	PageHelper.startPage(pageNum, pageSize);
    	List<Job>  jobList=jobDao.selectJobs(job);
    	return new PagePojo<>(jobList);
    }
    
    public Map<String, Object> getJobList(Map<String, Object> map){
    	int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = jobDao.getJobCount(map);
		List<Job> jobList = jobDao.getJobList(map);
		Map<String, Object> pages = new HashMap<String, Object>();
		pages.put("total", totals);
		pages.put("rows", jobList);
		return pages;
    }
    
    public int getJobCount(Map<String, Object> map){
    	return jobDao.getJobCount(map);
    }
    
    public Job getNewJobKey(){
    	return jobDao.getNewJobKey();
    }
}
