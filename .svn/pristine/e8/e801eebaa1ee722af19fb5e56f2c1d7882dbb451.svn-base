package com.org.cygs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.StopAuditDao;
import com.org.cygs.pojo.StopAudit;
import com.org.cygs.service.StopAuditService;

@Service("StopAuditService")
public class StopAuditServiceImpl implements StopAuditService{
	
	@Resource
	private StopAuditDao stopAuditDao;
	
	//审核时首先删除暂不处理任务书
    public void deleteStopAudit(String mId,String uId){
    	stopAuditDao.deleteStopAudit(mId, uId);
    }
    
	public void deleteStopAuditByMid(String mId) {
		stopAuditDao.deleteStopAuditByMid(mId);
	}

	//审核选择暂不处理   添加记录到 暂不处理表中
    public void addStopAudit(StopAudit stopAudit){
    	stopAuditDao.addStopAudit(stopAudit);
    }

}