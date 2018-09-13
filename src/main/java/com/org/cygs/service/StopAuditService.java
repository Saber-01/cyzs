package com.org.cygs.service;
import com.org.cygs.pojo.StopAudit;
public interface StopAuditService {

	//审核时首先删除暂不处理任务书
    public void deleteStopAudit(String mId,String uId);
    
    public void deleteStopAuditByMid(String mId);
    
  //审核选择暂不处理   添加记录到 暂不处理表中
    public void addStopAudit(StopAudit stopAudit);
}
