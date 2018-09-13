package com.org.cygs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.ZPartDao;
import com.org.cygs.pojo.ZPart;
import com.org.cygs.service.ZPartService;

@Service
public class ZPartServiceImpl implements ZPartService {

	@Resource
	private ZPartDao zPartDao;
	
	public void insertZPart(ZPart record) {
		zPartDao.insertZPart(record);
	}

}
