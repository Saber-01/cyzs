package com.org.cygs.service;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.MissionContent;


public interface MissionContentService {

	public void insertMissionContent(MissionContent missionContent);
	// 查询已开量
	public Float selectRealQuantity(String prId, String cuId, String pcpId);
	// 查询已结量
	public Float queryaccQuantity(Map<String, Object> map);
	
	//查询项目累计已结量
    public Double queryLjaccQuantity(Map<String, Object> map);
    public Double queryYjaccQuantity(Map<String, Object> map);
	
	public List<MissionContent> getMissionContentByMId(String mId);
	public List<MissionContent> getHTNQTMissionContentByMId(String mId);

	
	Float selectYKRealQuantity( String cuId, String pcpId, String oId);
	
	Float selectYSRealQuantity(String cuId, String pcpId, String oId);
	
	public void delMissionContentBymId(String mId);
	public void delDMissionContentBymId(String mId);
	
	public List<MissionContent> getDMissionContentByMId(String mId);
	public List<MissionContent> getDelHTNQTMissionContentByMId(String mId);
	public List<MissionContent> getDMissionContentInfoByMId(String mId);
	
	public void updateMissionContent(MissionContent missionContent);
	
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
