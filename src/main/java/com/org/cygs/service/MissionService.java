package com.org.cygs.service;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.DataGrid;
import com.org.cygs.pojo.JJMission;
import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.MissionVo;
import com.org.cygs.pojo.Page;


public interface MissionService {
	public List<String> selectLikedMCode(String mCode);
	public void insertMission(Mission mission);
	// 删除任务书
	public void delMissionById(String mId);
	// 更新任务书
	public void updateMission(Mission mission);
	
	public String getMIdByMcode(String mCode);
	
	public void addMissionStatus(List<Mission> missionList);
	public void setMissionStatus(Mission mission);
	public void queryFile(List<Mission> missionList);
	public int setPageStatus(Map<String, Object> map);   //设置分页数据,返回当前页码
	
	public DataGrid<Mission> getMissionList(Map<String, Object> map);
	public DataGrid<Mission> getSubmitMissionList(Map<String, Object> map);
	public DataGrid<Mission> getUnAuditMissionList(Map<String, Object> map);
	public DataGrid<Mission> getAuditingMissionList(Map<String, Object> map);//本人已审后续未审查询
	public DataGrid<Mission> getUnCompletedMissionList(Map<String, Object> map);
	public DataGrid<Mission> getDeleteMissionList(Map<String, Object> map);
	public DataGrid<Mission> getBGQZMissionList(Map<String, Object> map);//变更签证任务书资料状态查询
	
    public List<Mission> getStopAuditMissionList(String uId,String roId); //查看本人暂不处理任务书
    public int getStopAuditMissionCount(String uId,String roId);
	
	public Mission getDelMissionBymId(String mId);
	public Mission getDMissionInfoByMId(String mId);
	public void delDMissionById(String mId);
	
	
	
	
	
	
	
	//审核任务书                        查出任务书id以及对应的工程名称
    public List<Mission> getToAuditMissionList(Map<String, Object> map);
    public int getToAuditMissionCount(Map<String, Object> map);
    //通过主键查询任务书
    public Mission getMissionByMId(String mId);
    
    
    //只查任务书一张表
    public Mission getMissionInfoBymId(String mId);
    
	//审核之后修改审核状态 
    public void updateMissionStatus(Mission mission);
    
    public List<Mission> getToAuditMissionListByPrId(Map<String, Object> map);

	// 下载任务书前的查询处理, 调用listallmissionnew存储过程
    public List<MissionVo> preDownloadMission(Map<String, Object> map);

    //通过工程id，任务书id，部门id，任务书类型，审核状态等获取任务书
    public List<Mission> getMissionByMap(Map<String,Object> map);

    //计件任务书统计，获取工程、分部、分项、楼栋号，以及价格，总工程量；并将数据存入JJMission对象中（注：总工程量包含各楼栋号下的工程量之和）
    public List<JJMission> getJJMissionList1(String prId,int status);
    
    //计件任务书统计，获取工程、分项，价格、以及各楼栋号对应的工程量，并将数据存入JJMission中
    public List<JJMission> getJJMissionList2(String prId,int status);
}
