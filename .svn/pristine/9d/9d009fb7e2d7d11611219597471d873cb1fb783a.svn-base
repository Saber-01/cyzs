package com.org.cygs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.PartPositionDao;
import com.org.cygs.pojo.Part;
import com.org.cygs.pojo.PartPosition;
import com.org.cygs.service.PartPositionService;

@Service
public class PartPositionServiceImpl implements PartPositionService {
	@Resource
	private PartPositionDao partPositionDao;
	
	@Override
	public void insertPartPosition(PartPosition partPosition) {
		partPositionDao.insertPartPosition(partPosition);
	}

	@Override
	public PartPosition selectPartPosition(String id) {
		return partPositionDao.selectPartPosition(id);
	}

	@Override
	public void deletePartPosition(String id) {
		partPositionDao.deletePartPosition(id);
	}

	@Override
	public void updatePartPosition(PartPosition partPosition) {
		partPositionDao.updatePartPosition(partPosition);
	}

	@Override
	public List<PartPosition> getPartPositionByPId(String id) {
		return partPositionDao.getPartPositionByPId(id);
	}

	@Override
	public List<PartPosition> selectPartPositionByPId(String pId) {
		return partPositionDao.selectPartPositionByPId(pId);
	}

	@Override
	public List<PartPosition> getPartPositionList1(PartPosition partPosition) {
		return partPositionDao.getPartPositionList1(partPosition);
	}

	public Map<String, Object> getPartPositionList(Map<String, Object> map) {
		int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = partPositionDao.getPartPositionCount(map);
		List<PartPosition> partPostionList = partPositionDao.getPartPositionList(map);
		Map<String, Object> partPostionpages = new HashMap<String, Object>();
		partPostionpages.put("total", totals);
		partPostionpages.put("rows", partPostionList);
		return partPostionpages;
	}
	
}
