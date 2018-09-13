package com.org.cygs.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.MissionContentDao;
import com.org.cygs.pojo.MissionContent;
import com.org.cygs.service.MissionContentService;

@Service("missionContentService")
public class MissionContentServiceImpl implements MissionContentService {
	@Resource
	private MissionContentDao missionContentDao;
	
	public void insertMissionContent(MissionContent missionContent) {
		//System.out.println("insert任务书内容");
		missionContentDao.insertMissionContent(missionContent);
	}
	public Float selectRealQuantity(String prId, String cuId, String pcpId) {
		return missionContentDao.selectRealQuantity(prId, cuId, pcpId);
	}
	
	public Float queryaccQuantity(Map<String, Object> map) {
		return missionContentDao.queryaccQuantity(map);
	}
	
	//查询项目累计已结量
	public Double queryLjaccQuantity(Map<String, Object> map){
		return missionContentDao.queryLjaccQuantity(map);
	}
	public Double queryYjaccQuantity(Map<String, Object> map){
		return missionContentDao.queryYjaccQuantity(map);
	}
	
	
	public List<MissionContent> getMissionContentByMId(String mId){
		return missionContentDao.getMissionContentByMId(mId);
	}

	public List<MissionContent> getHTNQTMissionContentByMId(String mId) {
		return missionContentDao.getHTNQTMissionContentByMId(mId);
	}
	
	public Float selectYKRealQuantity( String cuId, String pcpId, String oId){		
		return missionContentDao.selectYKRealQuantity(cuId, pcpId, oId);
	}
	
	public Float selectYSRealQuantity( String cuId, String pcpId, String oId){
		return missionContentDao.selectYSRealQuantity(cuId, pcpId, oId);
	}

	//只查询任务书内容一张表
	public List<MissionContent> getMissionContentInfoByMId(String mId){
	  List<MissionContent> mcList = missionContentDao.getMissionContentInfoByMId(mId);
	  return  mcList;
	}
	
	public List<MissionContent> getDMissionContentByMId(String mId){
		return missionContentDao.getDMissionContentByMId(mId);
	}
	public List<MissionContent> getDMissionContentInfoByMId(String mId){
		return missionContentDao.getDMissionContentInfoByMId(mId);
	}
	
	public List<MissionContent> getDelHTNQTMissionContentByMId(String mId) {
		return missionContentDao.getDelHTNQTMissionContentByMId(mId);
	}
	
	public void delMissionContentBymId(String mId) {
		missionContentDao.delMissionContentBymId(mId);
	}
	
	public void delDMissionContentBymId(String mId){
		missionContentDao.delDMissionContentBymId(mId);
	}
	
	
	public void updateMissionContent(MissionContent missionContent) {
		missionContentDao.updateMissionContent(missionContent);
	}
	
	@Override
	public List<MissionContent> getMissionContentByList(List<String> midList) {
		
		return this.missionContentDao.getMissionContentByList(midList);
	}
	
	//合同内其他任务书--没有单价
	public List<MissionContent> getMCByMId(String mId) {
		return missionContentDao.getMCByMId(mId);
	}
	
	// 查询内编号
	public List<MissionContent> getInnerIdByPrId(String prId) {
		return missionContentDao.getInnerIdByPrId(prId);
	}
	
	public Integer getInnerIdCount(String prId) {
		return missionContentDao.getInnerIdCount(prId);
	}

}
