package com.org.cygs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.org.cygs.dao.MissionTypeDao;
import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.MissionType;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.service.MissionTypeService;

@Service("missionTypeService")
public class MissionTypeServiceImpl implements MissionTypeService {

	@Resource
	private MissionTypeDao missionTypeDao;
	
	public List<MissionType> getMissionType(){
		return this.missionTypeDao.getMissionType();
	}
	@Override
	public int missionDelete(String mtId) {
		
		return this.missionTypeDao.missionDelete(mtId);
	}
	@Override
	public int missionEdit(MissionType mt) {
		
		return this.missionTypeDao.missionEdit(mt);
	}
	@Override
	public MissionType getMissionTypeById(String mtId) {
		
		return this.missionTypeDao.getMissionTypeById(mtId);
	}
	@Override
	public int missionAdd(MissionType mt) {
	
		return this.missionTypeDao.missionAdd(mt);
	}
	@Override
	public List<MissionType> judgeMissionType(MissionType mt) {
		
		return this.missionTypeDao.judgeMissionType(mt);
	
	}
	@Override
	public MissionType getMissionTypeByName(String mtName) {
		return missionTypeDao.getMissionTypeByName(mtName);
	}
	@Override
	public PagePojo<MissionType> selectMissionTypeList(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<MissionType> mtList = this.missionTypeDao.getMissionType();
		return new PagePojo<>(mtList);
	}
	
	public Map<String, Object> getMissionTypeList(Map<String, Object> map) {
		int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = missionTypeDao.getMissionTypeCount(map);
		List<MissionType> missionTypeList = missionTypeDao.getMissionTypeList(map);
		Map<String, Object> missionTypepages = new HashMap<String, Object>();
		missionTypepages.put("total", totals);
		missionTypepages.put("rows", missionTypeList);
		return missionTypepages;
	}
	
	
}
