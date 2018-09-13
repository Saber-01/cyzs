package com.org.cygs.service;

import com.org.cygs.pojo.SystemParameter;

public interface SystemParameterService {

	public SystemParameter selectByParaName(String paraName);
	
	public void updateSystemParam(SystemParameter sParameter);
}
