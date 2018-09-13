package com.org.cygs.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.ZMissionDao;
import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.Unit;
import com.org.cygs.pojo.ZMission;
import com.org.cygs.service.ZMissionService;

@Service
public class ZMissionServiceImpl implements ZMissionService {
	@Resource
	private ZMissionDao zMissionDao;
	
	
	public int setPageStatus(Map<String, Object> map){
		int pageNo = (int) map.get("pageNo");
		int offset = (pageNo-1)*15;
		map.put("offset", offset);
		map.put("pageSize", 15);
		return pageNo;
	}
	
	// 按条件查询
	public Page<ZMission> getZMissionList(Map<String, Object> map) {
		int pageNum = setPageStatus(map);
		int totals = zMissionDao.getZCount(map);
		System.out.println(totals);
		List<ZMission> zmissionList = zMissionDao.getZMissionList(map);
		
		Page<ZMission> missionPage= new Page<ZMission>(zmissionList, totals, pageNum, 15, zmissionList.size());
		return missionPage;
	}

	public Map<String, Object> getZMissionList1(Map<String, Object> map) {
		int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = zMissionDao.getZCount(map);
		List<ZMission> zmissionList = zMissionDao.getZMissionList(map);
		Map<String, Object> zmissionpages = new HashMap<String, Object>();
		zmissionpages.put("total", totals);
		zmissionpages.put("rows", zmissionList);
		return zmissionpages;
	}
	
	// 统计数量
	public int getZCount(Map<String, Object> map) {
		return zMissionDao.getZCount(map);
	}

	
	public ZMission getZMissionByMId(String zMId) {
		return zMissionDao.getZMissionByMId(zMId);
	}

}
