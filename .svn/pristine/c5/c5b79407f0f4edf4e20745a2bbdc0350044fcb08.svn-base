package com.org.cygs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.SystemOperationDao;
import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.Part;
import com.org.cygs.pojo.SystemOperation;
import com.org.cygs.service.SystemOperationService;

@Service
public class SystemOperationServiceImpl implements SystemOperationService {
	@Resource
	private SystemOperationDao systemOperationDao;
	
	public void insertSysOperation(SystemOperation record) {
		systemOperationDao.insertSysOperation(record);
	}
	
	// 获取工程名称
	public List<SystemOperation> getSysPrList() {
		return systemOperationDao.getSysPrList();
	}

	public int setPageStatus(Map<String, Object> map){
		int pageNo = (int) map.get("pageNo");
		int offset = (pageNo-1)*15;
		map.put("offset", offset);
		map.put("pageSize", 15);
		return pageNo;
	}
	
	public Page<SystemOperation> getSystemOperationList(Map<String, Object> map) {
		int pageNum = setPageStatus(map);
		int totals = systemOperationDao.getSystemOperationCount(map);
		System.out.println(totals);
		List<SystemOperation> soList = systemOperationDao.getSystemOperationList(map);
		
		Page<SystemOperation> soPage= new Page<SystemOperation>(soList, totals, pageNum, 15, soList.size());
		return soPage;
	}
	
	public Map<String, Object> getSystemOperationList1(Map<String, Object> map) {
		int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = systemOperationDao.getSystemOperationCount(map);
		List<SystemOperation> systemOperationList = systemOperationDao.getSystemOperationList(map);
		Map<String, Object> systemOperationpages = new HashMap<String, Object>();
		systemOperationpages.put("total", totals);
		systemOperationpages.put("rows", systemOperationList);
		return systemOperationpages;
	}
	

	
	public int getSystemOperationCount(Map<String, Object> map) {
		return systemOperationDao.getSystemOperationCount(map);
	}

	
	//调用backallmission存储过程
	public void backallmission(Map<String, Object> map) {
		systemOperationDao.backallmission(map);
	}
	
	// 备份数据库
	public void backupDatabase(Map<String, Object> map) {
		systemOperationDao.backupDatabase(map);
	}
	
	
	
}
