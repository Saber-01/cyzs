package com.org.cygs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.SystemParameterDao;
import com.org.cygs.pojo.SystemParameter;
import com.org.cygs.service.SystemParameterService;

@Service
public class SystemParameterServiceImpl implements SystemParameterService {
	@Resource
	private SystemParameterDao systemParameterDao;
	
	public SystemParameter selectByParaName(String paraName) {
		return systemParameterDao.selectByParaName(paraName);
	}
	
	public void updateSystemParam(SystemParameter sParameter) {
		systemParameterDao.updateSystemParam(sParameter);
	}

}
