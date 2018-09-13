package com.org.cygs.dao;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.AuditInfo;

public interface AuditInfoDao {

    // 插入审核信息
    void insertAuditInfo(AuditInfo record);
    // 通过mid删除
    void delAuditInfoByMId(String mId);

    AuditInfo selectByPrimaryKey(String aId);

    int updateByPrimaryKeySelective(AuditInfo record);
    
    public List<AuditInfo> getAuditInfoByMId(String mId);
    
    public List<AuditInfo> getDAuditInfoByMId(String mId);
    public List<AuditInfo> getDAuditByMId(String mId);
    
    
    public AuditInfo getAuditInfoByAId(String aId);
    public void updateAuditComment(AuditInfo auditInfo);
    //public 
    
    //通过条件查询任务书审核历史记录
    public List<AuditInfo> getAuditInfoList(Map<String, Object> map);
    public int getAuditInfoCount(Map<String, Object> map);
    
    //查询AuditInfo中，bdate<auditTime<edate，的最大的auditTime的auditInfo条目
    public List<AuditInfo> getAuditInfoByAuditTime(String bdate,String edate);
}