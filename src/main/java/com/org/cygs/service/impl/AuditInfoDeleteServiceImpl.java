package com.org.cygs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.AuditInfoDeleteDao;
import com.org.cygs.pojo.AuditInfoDelete;
import com.org.cygs.service.AuditInfoDeleteService;

@Service
public class AuditInfoDeleteServiceImpl implements AuditInfoDeleteService {
	@Resource
	private AuditInfoDeleteDao auditInfoDeleteDao;
	
	public void insertAuditInfoDelete(AuditInfoDelete record) {
		auditInfoDeleteDao.insertAuditInfoDelete(record);
	}
	
	public void delAuditInfoDelete(String mId){
		auditInfoDeleteDao.delAuditInfoDelete(mId);
	}

}
