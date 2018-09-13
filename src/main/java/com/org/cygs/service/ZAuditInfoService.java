package com.org.cygs.service;

import java.util.List;

import com.org.cygs.pojo.ZAuditInfo;

public interface ZAuditInfoService {
	public List<ZAuditInfo> getZAuditInfoByMId(String zMId);
}
