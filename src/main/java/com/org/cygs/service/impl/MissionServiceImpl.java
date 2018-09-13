package com.org.cygs.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.AuditRoleDao;
import com.org.cygs.dao.MissionDao;
import com.org.cygs.dao.ProjectDao;
import com.org.cygs.pojo.DataGrid;
import com.org.cygs.pojo.JJMission;
import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.MissionVo;
import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.User;
import com.org.cygs.service.MissionService;
import com.org.cygs.util.common.MapUtil;

@Service("missionService")
public class MissionServiceImpl implements MissionService {
	@Resource
	private MissionDao missionDao;
	
	@Resource
	private AuditRoleDao auditRoleDao;
	
	@Resource
	private ProjectDao projectDao;

	public List<String> selectLikedMCode(String mCode) {
		return missionDao.selectLikedMCode(mCode);
	}

	public void insertMission(Mission mission) {
		System.out.println("insert������");
		missionDao.insertMission(mission);
	}
	
	public void delMissionById(String mId) {
		missionDao.delMissionById(mId);
	}

	public String getMIdByMcode(String mCode){
		return missionDao.getMIdByMcode(mCode);
	}
	
	public void updateMission(Mission mission) {
		missionDao.updateMission(mission);
	}

	public Mission getMissionByMId(String mId){
		Mission mission = missionDao.getMissionByMId(mId);
		setMissionStatus(mission);
		return mission;
	}
	
	public Mission getMissionInfoBymId(String mId){
		return missionDao.getMissionInfoBymId(mId);
	}
	
	
	//���֮���޸����״̬ 
    public void updateMissionStatus(Mission mission){
    	missionDao.updateMissionStatus(mission);
    }
    
  //�鿴�����ݲ�����������
    public List<Mission> getStopAuditMissionList(String uId,String roId){
	  List<Mission> missionList = missionDao.getStopAuditMissionList(uId,roId);
	  addMissionStatus(missionList);
	  setDate(missionList);
	  return missionList;
   }
   
   public int getStopAuditMissionCount(String uId,String roId){
	   return missionDao.getStopAuditMissionCount(uId,roId);
   }
	
	//�������������״̬
	public void addMissionStatus(List<Mission> missionList){
	if(missionList != null && missionList.size()>0){
		for(int i = 0; i<missionList.size(); i++)
		{
			setMissionStatus(missionList.get(i));	
		}
	}
  }
	//
	public void setMissionStatus(Mission mission){
		int status = mission.getStatus();
		if(status == 0){
			mission.setMissionStatus(new String("�����"));
		}
		else if(status == -1){
			mission.setMissionStatus(new String("����"));
		}
		else if(status == 100){
			mission.setMissionStatus(new String("ȫ��������"));
		}
		else if(status > 0){
			int mt_code = mission.getMtCode();
			//System.out.println("���"+mt_code);
			//System.out.println("״̬"+status);
			String roleName = auditRoleDao.getRoleNameByMtAndAstep(mt_code, status);
			if(roleName != null && roleName.length()>0)
			{
				String result = roleName + "���ͨ��";
				mission.setMissionStatus(result);
			}
			else
			{
				mission.setMissionStatus(new String("��"));
			}
		}
		else{
			mission.setMissionStatus(new String("��"));
		}
	}
	
	
	
	//δ��������������ѯ������ ��һ����˽�ɫ����Ա
	public void addNextStatus(List<Mission> missionList){
		if(missionList != null && missionList.size()>0){
			for(int i = 0; i<missionList.size(); i++){
				int status = missionList.get(i).getStatus();
				status++;
				int mt_code = missionList.get(i).getMtCode();
				System.out.println("���"+mt_code);
				String prId = missionList.get(i).getPrId();
				System.out.println("���"+prId);
				String pcpId = missionList.get(i).getPcPId();
				String roleName = auditRoleDao.getRoleNameByMtAndAstep(mt_code, status);
				if(roleName!=null){
					if(roleName.equals("��Ŀ����")){
						String result = roleName + ":" + projectDao.getUserName(prId);
						missionList.get(i).setNextstatus(result);
					}
					else if(roleName.equals("���")){
						String result = roleName + ":" + projectDao.getShenJiName(prId);
						missionList.get(i).setNextstatus(result);
					}
					else if(roleName.equals("����Ԥ��")){
						String result = roleName + ":" + projectDao.getZhuGuanName(prId);
						missionList.get(i).setNextstatus(result);
					}
					else{
						missionList.get(i).setNextstatus(roleName);
					}

				}else{
					missionList.get(i).setNextstatus(new String("��"));
				}
				
			}
			
		}
		
	}
	
	void setDate(List<Mission> missionList){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		if(missionList != null && missionList.size()>0){
			for(int i = 0; i<missionList.size(); i++){
				String a = sdf.format(missionList.get(i).getBeginDate());
				String b = sdf.format(missionList.get(i).getEndDate());
				missionList.get(i).setbAndeDate(a+"��"+b);	
			}
		}
	}
	
	
	public void queryFile(List<Mission> missionList){
		if(missionList != null && missionList.size()>0){
			for (int i = 0; i < missionList.size(); i++) {
				// ���޸���
				File file = new File("D:/cyjz_file/test/file", missionList.get(i).getmId());
				if (file.exists()) {
					if (file.isDirectory()) {
						if (file.listFiles().length != 0)
							missionList.get(i).setFile("��");
						else
							missionList.get(i).setFile("��");
					}
				} else
					missionList.get(i).setFile("��");
			}
			
		}	
	}
	
	
	
	public int setPageStatus(Map<String, Object> map){
		int pageNo = (int) map.get("pageNo");
		int offset = (pageNo-1)*15;
		map.put("offset", offset);
		map.put("pageSize", 15);
		return pageNo;
	}
	
	//�鿴������//�������鿴�����鲢��ҳ
	public DataGrid<Mission> getMissionList(Map<String, Object> map){
		MapUtil.MapPage(map);
		int totals = missionDao.getCount(map);
		List<Mission> missionList = missionDao.getMissionList(map);
		queryFile(missionList);
		addMissionStatus(missionList);
		setDate(missionList);
		DataGrid<Mission> dg = new DataGrid<Mission>(totals,missionList);
		return dg;
	}
	
	//�鿴���ύ�����������----����
	public DataGrid<Mission> getSubmitMissionList(Map<String, Object> map){
		MapUtil.MapPage(map);
		int totals = missionDao.getSubmitMissionCount(map);
		List<Mission> missionList = missionDao.getSubmitMissionList(map);
		addMissionStatus(missionList);
		setDate(missionList);
		DataGrid<Mission> dg = new DataGrid<Mission>(totals,missionList);
		return dg;
	}
	
	//�鿴δ��/����������
	public DataGrid<Mission> getUnAuditMissionList(Map<String, Object> map){
		MapUtil.MapPage(map);
		int totals = missionDao.getUnAuditMissionCount(map);
		List<Mission> missionList = missionDao.getUnAuditMissionList(map);
		addMissionStatus(missionList);
		setDate(missionList);
		DataGrid<Mission> dg = new DataGrid<Mission>(totals,missionList);
		return dg;
	}
		
	//�����������δ���ѯ
   public DataGrid<Mission> getAuditingMissionList(Map<String, Object> map){
	   MapUtil.MapPage(map);
	   int totals = missionDao.getAuditingMissionCount(map);
		List<Mission> missionList = missionDao.getAuditingMissionList(map);
		addMissionStatus(missionList);
		setDate(missionList);
		DataGrid<Mission> dg = new DataGrid<Mission>(totals,missionList);
		return dg;
	}
	
	//�鿴δ�������������
	public DataGrid<Mission> getUnCompletedMissionList(Map<String, Object> map){
		 MapUtil.MapPage(map);
		int totals = missionDao.getUnCompletedMissionCount(map);
		List<Mission> missionList = missionDao.getUnCompletedMissionList(map);
		queryFile(missionList);
		addMissionStatus(missionList);
		addNextStatus(missionList);
		setDate(missionList);
		DataGrid<Mission> dg = new DataGrid<Mission>(totals,missionList);
		return dg;
		
	}
	
	//�鿴��ɾ���������ѯ
	public DataGrid<Mission> getDeleteMissionList(Map<String, Object> map){
		MapUtil.MapPage(map);
		int totals = missionDao.getDeleteMissionCount(map);
		List<Mission> missionList = missionDao.getDeleteMissionList(map);
		addMissionStatus(missionList);
		setDate(missionList);
		DataGrid<Mission> dg = new DataGrid<Mission>(totals,missionList);
		return dg;
	}
	public Mission getDelMissionBymId(String mId){
		return missionDao.getDelMissionBymId(mId);
	}
	public Mission getDMissionInfoByMId(String mId){
		List<Mission> missionList = new ArrayList<Mission>();
		Mission m = missionDao.getDMissionInfoByMId(mId);
		missionList.add(m);
		addMissionStatus(missionList);
		return m;
	}
	public void delDMissionById(String mId){
		missionDao.delDMissionById(mId);
	}
	
	
	//���ǩ֤����������״̬��ѯ
	public DataGrid<Mission> getBGQZMissionList(Map<String, Object> map){
		MapUtil.MapPage(map);
		int totals = missionDao.getBGQZMissionCount(map);
		List<Mission> missionList = missionDao.getBGQZMissionList(map);
		addMissionStatus(missionList);
		setDate(missionList);
		DataGrid<Mission> dg = new DataGrid<Mission>(totals,missionList);
		return dg;
	}
	
	
	//���������                        ���������id�Լ���Ӧ�Ĺ�������
    public List<Mission> getToAuditMissionList(Map<String, Object> map){
    	return missionDao.getToAuditMissionList(map);
    }
    
    public int getToAuditMissionCount(Map<String, Object> map){
    	return missionDao.getToAuditMissionCount(map);
    }
    
    
    
    //ͨ������id�鿴��Ӧ������
    public List<Mission> getToAuditMissionListByPrId(Map<String, Object> map){
    	List<Mission> missionList = missionDao.getToAuditMissionListByPrId(map);
    	setDate(missionList);
    	return missionList;
    	
    }


	// ����������ǰ�Ĳ�ѯ����, ����listallmissionnew�洢����
	public List<MissionVo> preDownloadMission(Map<String, Object> map) {
		return missionDao.preDownloadMission(map);
	}


	@Override
	public List<Mission> getMissionByMap(Map<String, Object> map) {
		
		return this.missionDao.getMissionByMap(map);
	}

	@Override
	public List<JJMission> getJJMissionList1(String prId,int status) {
		List<JJMission> jjList = this.missionDao.getJJMissionList1(prId,status);
		for(JJMission jj:jjList){
			//���ܹ�����Ϊ�գ�������Ϊ0
			if(jj.getGcl() == null){
				jj.setGcl(0.0);
			}
			jj.setAccountSum(jj.getPrice() * jj.getGcl());
		}
		return jjList;
	}

	@Override
	public List<JJMission> getJJMissionList2(String prId,int status) {
		List<JJMission> jjList = this.missionDao.getJJMissionList2(prId, status);
		for(JJMission jj:jjList){
			//��¥�����µĹ�����Ϊ�գ�������Ϊ0
			if(jj.getGcl() == null){
				jj.setGcl(0.0);
			}
		}
		return jjList;
	}


}
