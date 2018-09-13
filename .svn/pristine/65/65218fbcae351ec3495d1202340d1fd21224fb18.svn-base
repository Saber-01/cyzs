package com.org.cygs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.org.cygs.dao.YUnitDao;
import com.org.cygs.pojo.MissionType;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.Permission;
import com.org.cygs.pojo.Unit;
import com.org.cygs.service.YUnitService;

@Service("YUnitService")
public class YUnitServiceImpl implements YUnitService{

	@Resource
	private YUnitDao yUnitDao;
	@Override
	public int unitAdd(Unit unit) {
		
		return this.yUnitDao.unitAdd(unit);
	}

	@Override
	public int unitDeleteById(String unId) {
		
		return this.yUnitDao.unitDeleteById(unId);
	}

	@Override
	public int unitEdit(Unit unit) {
		
		return this.yUnitDao.unitEdit(unit);
	}

	@Override
	public List<Unit> getAllUnit() {
		
		return this.yUnitDao.getAllUnit();
	}

	@Override
	public Unit getUnitById(String unId) {
		
		return this.yUnitDao.getUnitById(unId);
	}

	@Override
	public List<Unit> judgeUnit(Unit unit) {
		
		return this.yUnitDao.judgeUnit(unit);
	}

	@Override
	public PagePojo<Unit> selectUnitList(int pageNum, int pageSize) {
		
		PageHelper.startPage(pageNum, pageSize);
		List<Unit> unitList = this.yUnitDao.getAllUnit();
    	return new PagePojo<>(unitList);
	}

	public Map<String, Object> getUnitList(Map<String, Object> map) {
		int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = yUnitDao.getUnitCount(map);
		List<Unit> unitList = yUnitDao.getUnitList(map);
		Map<String, Object> unitpages = new HashMap<String, Object>();
		unitpages.put("total", totals);
		unitpages.put("rows", unitList);
		return unitpages;
	}
	
	@Override
	public int getCount() {
		
		return this.yUnitDao.getCount();
	}

	@Override
	public Unit getUnitByName(String unName) {
		return yUnitDao.getUnitByName(unName);
	}

}
