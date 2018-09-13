package com.org.cygs.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.PrIndexPCDao;
import com.org.cygs.pojo.PrIndexPC;
import com.org.cygs.service.ProjectIndexPCService;

@Service
public class ProjectIndexPCServiceImpl implements ProjectIndexPCService {
	@Resource
	private PrIndexPCDao prIndexPcPoDao;
	
	public List<PrIndexPC> selectUnitByPrId(String prId) {
		return prIndexPcPoDao.selectUnitByPrId(prId);
	}

	public PrIndexPC selectPrPcByPrimaryKey(String pcPId) {
		return prIndexPcPoDao.selectByPrimaryKey(pcPId);
	}
	
	public List<PrIndexPC> getUnitNumberByPrList(Map<String, Object> map) {
		return prIndexPcPoDao.getUnitNumberByPrList(map);
	}

}
