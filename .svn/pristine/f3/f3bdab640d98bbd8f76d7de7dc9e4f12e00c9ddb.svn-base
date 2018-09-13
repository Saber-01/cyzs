package com.org.cygs.service;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.Lwfb;
import com.org.cygs.pojo.Lwfbjs;
import com.org.cygs.pojo.Lwgcldb;
import com.org.cygs.pojo.Lwgzzf;
import com.org.cygs.pojo.PieceworkServiceTotal;
import com.org.cygs.pojo.MissionVo;


public interface LwfbService {

	//班组月度报表
	public List<Lwfb> getLWFB(Map<String,Object> map);
	//劳务工资支付明细
	public List<Lwgzzf> getLwgzzf(Map<String,Object> map);
	//劳务工资支付汇总
	public List<Lwgzzf> getLwgzzfhz(Map<String,Object>map);

	//班组计件累计
    public List<PieceworkServiceTotal> getPieceworkServiceTotal(Map<String, Object> map);

	//项目月度报表
	public List<Lwfbjs> getLwfbjs(Map<String,Object> map);
	//项目月度报表中，所有任务书明细信息获取
	public List<MissionVo> getAllMissionNew(Map<String,Object> map);
	
	//获取劳务工程量对比信息
	public List<Lwgcldb> getLwgcldb(Map<String,Object> map);

}
