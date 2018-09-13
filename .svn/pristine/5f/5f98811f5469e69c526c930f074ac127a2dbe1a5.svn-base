package com.org.cygs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.OperationDao;
import com.org.cygs.pojo.Operation;
import com.org.cygs.service.OperationService;

@Service("operationService")
public class OperationServiceImpl implements OperationService {
	@Resource
	private OperationDao operationDao;

	public List<Operation> selectOperationByPsId(String psId) {
		return operationDao.selectOperationByPsId(psId);
	}

	@Override
	public Operation getOperationByOid(String oId) {
		
		return this.operationDao.getOperationByOid(oId);
	}

}
