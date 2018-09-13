package com.org.cygs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.ZPartPositionDao;
import com.org.cygs.pojo.ZPartPosition;
import com.org.cygs.service.ZPartPositionService;

@Service
public class ZPartPositionServiceImpl implements ZPartPositionService {

	@Resource
	private ZPartPositionDao zPartPositionDao;
	
	public void insertZPartPos(ZPartPosition record) {
		zPartPositionDao.insertZPartPos(record);
	}

}
