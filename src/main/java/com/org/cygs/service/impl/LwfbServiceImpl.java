package com.org.cygs.service.impl;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.cygs.dao.LwfbDao;
import com.org.cygs.pojo.Lwfb;
import com.org.cygs.pojo.Lwfbjs;
import com.org.cygs.pojo.Lwgcldb;
import com.org.cygs.pojo.Lwgzzf;
import com.org.cygs.pojo.PieceworkServiceTotal;
import com.org.cygs.pojo.MissionVo;
import com.org.cygs.service.LwfbService;

@Service("LwfbService")
public class LwfbServiceImpl implements LwfbService{

	@Resource
	private LwfbDao lwfbDao;
	
	@Override
	public List<Lwfb> getLWFB(Map<String, Object> map) {
		
		return this.lwfbDao.getLWFB(map);
	}

	@Override
	public List<Lwgzzf> getLwgzzf(Map<String, Object> map) {
		
		return this.lwfbDao.getLwgzzf(map);
	}

	@Override
	public List<Lwgzzf> getLwgzzfhz(Map<String, Object> map) {
		
		return this.lwfbDao.getLwgzzfhz(map);
	}
	
	//班组计件累计
    public List<PieceworkServiceTotal> getPieceworkServiceTotal(Map<String, Object> map){
    	return lwfbDao.getPieceworkServiceTotal(map);
    }

	@Override
	public List<Lwfbjs> getLwfbjs(Map<String, Object> map) {
		
		return this.lwfbDao.getLwfbjs(map);
	}

	@Override
	public List<MissionVo> getAllMissionNew(Map<String, Object> map) {
		
		return this.lwfbDao.getAllMissionNew(map);
	}

	@Override
	public List<Lwgcldb> getLwgcldb(Map<String, Object> map) {
		
		return lwfbDao.getLwgcldb(map);
	}

}
