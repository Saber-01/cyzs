package com.org.cygs.dao;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.JJMission;
import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.MissionVo;

public interface MissionDao {
	// 查询相似的任务书编号
	public List<String> selectLikedMCode(String mCode);
	// 新建任务书
	public void insertMission(Mission mission);
	// 删除任务书
	public void delMissionById(String mId);
	// 更新任务书
	public void updateMission(Mission mission);
	
	public String getMIdByMcode(String mCode);
	
	//按条件查询任务书
	public List<Mission> getMissionList(Map<String, Object> map);
	public int getCount(Map<String, Object> map);
	
	//查看已提交待审核任务书
	public List<Mission> getSubmitMissionList(Map<String, Object> map);
	public int getSubmitMissionCount(Map<String, Object> map);
	
	//查看未审/退修任务书
	public List<Mission> getUnAuditMissionList(Map<String, Object> map);
	public int getUnAuditMissionCount(Map<String, Object> map);
	
	
	//查看未处理完成任务书
	public List<Mission> getUnCompletedMissionList(Map<String, Object> map);
	public int getUnCompletedMissionCount(Map<String, Object> map);
	
	
	//本人已审后续未审查询
	public List<Mission> getAuditingMissionList(Map<String, Object> map);
    public int getAuditingMissionCount(Map<String, Object> map);
    
    
    //已删除任务书查询
  	public List<Mission> getDeleteMissionList(Map<String, Object> map);
    public int getDeleteMissionCount(Map<String, Object> map);
    public Mission getDelMissionBymId(String mId);
    public Mission getDMissionInfoByMId(String mId);
    public void delDMissionById(String mId);
  //变更签证任务书查询
  	public List<Mission> getBGQZMissionList(Map<String, Object> map);
    public int getBGQZMissionCount(Map<String, Object> map);
    
    //查看本人暂不处理任务书
    public List<Mission> getStopAuditMissionList(String uId,String roId);
    public int getStopAuditMissionCount(String uId,String roId);
    
    //审核任务书                        查出任务书id以及对应的工程名称
    public List<Mission> getToAuditMissionList(Map<String, Object> map);
    public int getToAuditMissionCount(Map<String, Object> map);
    
    //通过任务书mId查询任务书
    public Mission getMissionByMId(String mId);
    
    //
    public Mission getMissionInfoBymId(String mId);
    
    public List<Mission> getToAuditMissionListByPrId(Map<String, Object> map);
    
    //审核之后修改审核状态 
    public void updateMissionStatus(Mission mission);

    // 下载任务书前的查询处理, 调用listallmissionnew存储过程
    public List<MissionVo> preDownloadMission(Map<String, Object> map);

    //通过工程id，任务书id，部门id，任务书类型，审核状态等获取任务书
    public List<Mission> getMissionByMap(Map<String,Object> map);

    //计件任务书统计，获取工程、分部、分项、楼栋号，以及价格，总工程量；并将数据存入JJMission对象中
    public List<JJMission> getJJMissionList1(String prId,int status);
    
    //计件任务书统计，获取工程、分项，价格、以及各楼栋号对应的工程量，并将数据存入JJMission中
    public List<JJMission> getJJMissionList2(String prId,int status);
}
