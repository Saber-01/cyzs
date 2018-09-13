package com.org.cygs.dao;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.JJMission;
import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.MissionVo;

public interface MissionDao {
	// ��ѯ���Ƶ���������
	public List<String> selectLikedMCode(String mCode);
	// �½�������
	public void insertMission(Mission mission);
	// ɾ��������
	public void delMissionById(String mId);
	// ����������
	public void updateMission(Mission mission);
	
	public String getMIdByMcode(String mCode);
	
	//��������ѯ������
	public List<Mission> getMissionList(Map<String, Object> map);
	public int getCount(Map<String, Object> map);
	
	//�鿴���ύ�����������
	public List<Mission> getSubmitMissionList(Map<String, Object> map);
	public int getSubmitMissionCount(Map<String, Object> map);
	
	//�鿴δ��/����������
	public List<Mission> getUnAuditMissionList(Map<String, Object> map);
	public int getUnAuditMissionCount(Map<String, Object> map);
	
	
	//�鿴δ�������������
	public List<Mission> getUnCompletedMissionList(Map<String, Object> map);
	public int getUnCompletedMissionCount(Map<String, Object> map);
	
	
	//�����������δ���ѯ
	public List<Mission> getAuditingMissionList(Map<String, Object> map);
    public int getAuditingMissionCount(Map<String, Object> map);
    
    
    //��ɾ���������ѯ
  	public List<Mission> getDeleteMissionList(Map<String, Object> map);
    public int getDeleteMissionCount(Map<String, Object> map);
    public Mission getDelMissionBymId(String mId);
    public Mission getDMissionInfoByMId(String mId);
    public void delDMissionById(String mId);
  //���ǩ֤�������ѯ
  	public List<Mission> getBGQZMissionList(Map<String, Object> map);
    public int getBGQZMissionCount(Map<String, Object> map);
    
    //�鿴�����ݲ�����������
    public List<Mission> getStopAuditMissionList(String uId,String roId);
    public int getStopAuditMissionCount(String uId,String roId);
    
    //���������                        ���������id�Լ���Ӧ�Ĺ�������
    public List<Mission> getToAuditMissionList(Map<String, Object> map);
    public int getToAuditMissionCount(Map<String, Object> map);
    
    //ͨ��������mId��ѯ������
    public Mission getMissionByMId(String mId);
    
    //
    public Mission getMissionInfoBymId(String mId);
    
    public List<Mission> getToAuditMissionListByPrId(Map<String, Object> map);
    
    //���֮���޸����״̬ 
    public void updateMissionStatus(Mission mission);

    // ����������ǰ�Ĳ�ѯ����, ����listallmissionnew�洢����
    public List<MissionVo> preDownloadMission(Map<String, Object> map);

    //ͨ������id��������id������id�����������ͣ����״̬�Ȼ�ȡ������
    public List<Mission> getMissionByMap(Map<String,Object> map);

    //�Ƽ�������ͳ�ƣ���ȡ���̡��ֲ������¥���ţ��Լ��۸��ܹ��������������ݴ���JJMission������
    public List<JJMission> getJJMissionList1(String prId,int status);
    
    //�Ƽ�������ͳ�ƣ���ȡ���̡�����۸��Լ���¥���Ŷ�Ӧ�Ĺ��������������ݴ���JJMission��
    public List<JJMission> getJJMissionList2(String prId,int status);
}
