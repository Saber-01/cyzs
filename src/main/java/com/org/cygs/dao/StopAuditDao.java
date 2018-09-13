package com.org.cygs.dao;

import com.org.cygs.pojo.StopAudit;
public interface StopAuditDao {
	
	
	//审核时首先删除暂不处理任务书
    public void deleteStopAudit(String mId,String uId);
    
    //根据mid删除
    public void deleteStopAuditByMid(String mId);
    
  //审核选择暂不处理   添加记录到 暂不处理表中
    public void addStopAudit(StopAudit stopAudit);

}
