package com.org.cygs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.org.cygs.dao.CheckUnitDao;
import com.org.cygs.pojo.CheckUnit;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.Permission;
import com.org.cygs.service.CheckUnitService;

@Service("CheckUnitService")
public class CheckUnitServiceImpl implements CheckUnitService{

	@Resource
	private CheckUnitDao checkUnitDao;
	
	@Override
	public List<CheckUnit> getAllCheckUnit() {
	
		return this.checkUnitDao.getAllCheckUnit();
	}

	@Override
	public int checkUnitAdd(CheckUnit cu) {
		
		return this.checkUnitDao.checkUnitAdd(cu);
	}

	@Override
	public int checkUnitEdit(CheckUnit cu) {
	
		return this.checkUnitDao.checkUnitEdit(cu);
	}

	@Override
	public int checkUnitDelete(String cuId) {
		
		return this.checkUnitDao.checkUnitDelete(cuId);
	}

	@Override
	public CheckUnit getCheckUnitById(String cuId) {
		
		return this.checkUnitDao.getCheckUnitById(cuId);
	}

	@Override
	public List<CheckUnit> judgeCheckUnit(CheckUnit cu) {
		
		return this.checkUnitDao.judgeCheckUnit(cu);
	}

	@Override
	public PagePojo<CheckUnit> selectCheckUnitListByName(int pageNo,
			int pageSize, String cuName) {
		PageHelper.startPage(pageNo, pageSize);
		List<CheckUnit> cuList = this.checkUnitDao.selectCheckUnitListByName(cuName);
		return new PagePojo<>(cuList);
	}

	public Map<String, Object> getCheckUnitList(Map<String, Object> map){
		int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = checkUnitDao.getCheckUnitCount(map);
		List<CheckUnit> checkUnitList = checkUnitDao.getCheckUnitList(map);
		Map<String, Object> checkUnitpages = new HashMap<String, Object>();
		checkUnitpages.put("total", totals);
		checkUnitpages.put("rows", checkUnitList);
		return checkUnitpages;
	}
 
 	
	
	
	public List<CheckUnit> selectAllCheckUnit() {
		return checkUnitDao.selectAllCheckUnit();
	}

	@Override
	public List<CheckUnit> selectCheckUnitListByName(String cuName) {
		
		return this.checkUnitDao.selectCheckUnitListByName(cuName);
	}

	@Override
	public int getCheckUnitCount(Map<String, Object> map) {
	
		return this.checkUnitDao.getCheckUnitCount(map);
	}

}
