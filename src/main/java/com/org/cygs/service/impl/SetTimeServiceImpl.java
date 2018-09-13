package com.org.cygs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.SetTimeDao;
import com.org.cygs.pojo.MissionType;
import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.SetTime;
import com.org.cygs.service.SetTimeService;

@Service("SetTimeService")
public class SetTimeServiceImpl implements SetTimeService{

	@Resource
	private SetTimeDao setTimeDao;
	
	@Override
	public List<SetTime> getSetTimeByStatusSYearSMonth(int status,
			int sYear, int sMonth) {
		
		return this.setTimeDao.getSetTimeByStatusSYearSMonth(status, sYear, sMonth);
	}
	
	
	public int setPageStatus(Map<String, Object> map){
		int pageNo = (int) map.get("pageNo");
		int offset = (pageNo-1)*15;
		map.put("offset", offset);
		map.put("pageSize", 15);
		return pageNo;
	}

	// 获取统计月度时间范围
	public Page<SetTime> getSetTime(Map<String, Object> map) {
		int pageNum = setPageStatus(map);
		int totals = setTimeDao.getSetTimeCount();
		System.out.println(totals);
		List<SetTime> setTimeList = setTimeDao.getSetTime(map);
		
		Page<SetTime> missionsetTime = new Page<SetTime>(setTimeList, totals, pageNum, 15, setTimeList.size());
		return missionsetTime;
	}
	
	public Map<String, Object> getSetTimeList(Map<String, Object> map) {
		int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = setTimeDao.getSetTimeCount1(map);
		List<SetTime> setTimeList = setTimeDao.getSetTimeList1(map);
		Map<String, Object> setTimepages = new HashMap<String, Object>();
		setTimepages.put("total", totals);
		setTimepages.put("rows", setTimeList);
		return setTimepages;
	}
	
	public int getSetTimeCount() {
		return setTimeDao.getSetTimeCount();
	}
	
	public SetTime getMaxSetTimeYearMonth() {
		return setTimeDao.getMaxSetTimeYearMonth();
	}
	
	// 插入
	public void insertSetTime(SetTime st) {
		setTimeDao.insertSetTime(st);
	}

	public SetTime getSetTimeByPrimaryKey(String setId) {
		return setTimeDao.getSetTimeByPrimaryKey(setId);
	}

	public void updateSetTimeByPrimaryKey(SetTime st) {
		setTimeDao.updateSetTimeByPrimaryKey(st);
	}


}
