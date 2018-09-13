package com.org.cygs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.ZPrIndexPcDao;
import com.org.cygs.pojo.ZPrIndexPc;
import com.org.cygs.service.ZPrIndexPcService;

@Service
public class ZPrIndexPcServiceImpl implements ZPrIndexPcService {
	@Resource
	private ZPrIndexPcDao zPrIndexPcDao;
	
	
	public List<ZPrIndexPc> selectUnitNumberByZprId(String zPrId) {
		return zPrIndexPcDao.selectUnitNumberByZprId(zPrId);
	}

}
