package com.org.cygs.dao;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.MissionContent;


public interface MissionContentDao {
	// 查询已开量
	Float selectRealQuantity(String prId, String cuId, String pcpId);
	// 查询已结量
	Float queryaccQuantity(Map<String, Object> map);
	
	
	//查询项目累计已结量
	public Double queryYjaccQuantity(Map<String, Object> map);
	//查询项目累计已结量
	public Double queryLjaccQuantity(Map<String, Object> map);
	
	Float selectYKRealQuantity(String cuId, String pcpId, String oId);
	
	Float selectYSRealQuantity( String cuId, String pcpId, String oId);
	
	void insertMissionContent(MissionContent missionContent);
	
	void updateMissionContent(MissionContent missionContent);
	
	void delMissionContentBymId(String mId);
	
	void delDMissionContentBymId(String mId);
	
	//通过任务书id查询任务书内容 详细信息
	public List<MissionContent> getMissionContentByMId(String mId);
	//查询合同内其他任务书内容
	public List<MissionContent> getHTNQTMissionContentByMId(String mId);
	
	public List<MissionContent> getDMissionContentByMId(String mId);
	public List<MissionContent>  getDelHTNQTMissionContentByMId(String mId);
	//只查询任务书内容一张表
	public List<MissionContent> getDMissionContentInfoByMId(String mId);
	
	//只查询任务书内容一张表
	public List<MissionContent> getMissionContentInfoByMId(String mId);
	
	//通过任务书id，查询员工工资情况
	public List<MissionContent> getMissionContentByList(List<String> midList);
	
	//合同内其他任务书--没有单价
	public List<MissionContent> getMCByMId(String mId);
	
	
	// 查询内编号
	public List<MissionContent> getInnerIdByPrId(String prId);
	public Integer getInnerIdCount(String prId);
}