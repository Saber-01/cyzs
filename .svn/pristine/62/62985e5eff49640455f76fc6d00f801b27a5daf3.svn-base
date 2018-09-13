package com.org.cygs.service;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.AuditInfo;
import com.org.cygs.pojo.DataGrid;
import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.Page;

public interface AuditInfoService {
	public void insertAuditInfo(AuditInfo record);
	// 通过mid删除
    public void delAuditInfoByMId(String mId);
	
	public List<AuditInfo> getAuditInfoByMId(String mId);
	public List<AuditInfo> getDAuditInfoByMId(String mId);
	public List<AuditInfo> getDAuditByMId(String mId);
	 //通过条件查询任务书审核历史记录
	public DataGrid<AuditInfo> getAuditInfo(Map<String, Object> map);

	
	public AuditInfo getAuditInfoByAId(String aId);
    public void updateAuditComment(AuditInfo auditInfo);
    
    //查询AuditInfo中，bdate<auditTime<edate，的最大的auditTime的auditInfo条目
    public List<AuditInfo> getAuditInfoByAuditTime(String bdate,String edate);
}
