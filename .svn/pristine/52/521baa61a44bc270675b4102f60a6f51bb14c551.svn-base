package com.org.cygs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.ZAuditInfoDao;
import com.org.cygs.pojo.ZAuditInfo;
import com.org.cygs.service.ZAuditInfoService;

@Service
public class ZAuditInfoServiceImpl implements ZAuditInfoService {
	
	@Resource
	private ZAuditInfoDao zAuditInfoDao;
	
	
	public List<ZAuditInfo> getZAuditInfoByMId(String zMId) {
		return zAuditInfoDao.getZAuditInfoByMId(zMId);
	}

}
