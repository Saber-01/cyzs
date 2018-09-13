package com.org.cygs.dao;

import java.util.List;

import com.org.cygs.pojo.ZAuditInfo;

public interface ZAuditInfoDao {

    public List<ZAuditInfo> getZAuditInfoByMId(String zMId);

    public void insertSelective(ZAuditInfo record);
}