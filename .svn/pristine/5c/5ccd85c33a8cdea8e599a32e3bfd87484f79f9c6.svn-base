package com.org.cygs.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.AuditInfoDao;
import com.org.cygs.pojo.AuditInfo;
import com.org.cygs.pojo.DataGrid;
import com.org.cygs.service.AuditInfoService;
import com.org.cygs.util.common.MapUtil;

@Service("auditInfoService")
public class AuditInfoServiceImpl implements AuditInfoService {

	@Resource
	AuditInfoDao auditInfoDao;
	
	public void insertAuditInfo(AuditInfo record) {
		//System.out.println("insert审核信息");
		auditInfoDao.insertAuditInfo(record);
	}
	
	public void delAuditInfoByMId(String mId) {
		auditInfoDao.delAuditInfoByMId(mId);
	}

	public List<AuditInfo> getAuditInfoByMId(String mId){
		return auditInfoDao.getAuditInfoByMId(mId);
	}
	
	public List<AuditInfo> getDAuditInfoByMId(String mId){
		return auditInfoDao.getDAuditInfoByMId(mId);
	}
	
	public List<AuditInfo> getDAuditByMId(String mId){
		return auditInfoDao.getDAuditByMId(mId);
	}
	
    //通过条件查询任务书审核历史记录
    public DataGrid<AuditInfo> getAuditInfo(Map<String, Object> map){
    	MapUtil.MapPage(map);
		int totals = auditInfoDao.getAuditInfoCount(map);
		List<AuditInfo> auditInfoList = auditInfoDao.getAuditInfoList(map);
		DataGrid<AuditInfo> dg = new DataGrid<AuditInfo>(totals,auditInfoList);
		return dg;
    }
    
    
    public AuditInfo getAuditInfoByAId(String aId){
    	//return auditInfoDao.getAuditInfoByAId(aId);
    	return auditInfoDao.selectByPrimaryKey(aId);
    	
    }
    public void updateAuditComment(AuditInfo auditInfo){
    	auditInfoDao.updateAuditComment(auditInfo);
    }

	@Override
	public List<AuditInfo> getAuditInfoByAuditTime(String bdate, String edate) {
		
		return this.auditInfoDao.getAuditInfoByAuditTime(bdate, edate);
	}
    
    

}
