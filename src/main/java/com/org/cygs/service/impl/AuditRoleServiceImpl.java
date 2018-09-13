package com.org.cygs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.org.cygs.dao.AuditRoleDao;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.AuditRole;
import com.org.cygs.pojo.MissionType;
import com.org.cygs.service.AuditRoleService;

@Service("AuditRoleService")
public class AuditRoleServiceImpl implements AuditRoleService {

	@Resource
	private AuditRoleDao auditRoleDao;

	@Override
	public int deleteByPrimaryKey(String arId) {
		
		return this.auditRoleDao.deleteByPrimaryKey(arId);
	}

	@Override
	public int insertSelective(AuditRole record) {
		
		return this.auditRoleDao.insertSelective(record);
	}

	@Override
	public AuditRole selectByPrimaryKey(String arId) {
		
		return this.auditRoleDao.selectByPrimaryKey(arId);
	}

	@Override
	public int updateByPrimaryKeySelective(AuditRole record) {
		
		return this.auditRoleDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<AuditRole> getAuditRoleListByMtId(String mtId) {
		
		return this.auditRoleDao.getAuditRoleListByMtId(mtId);
	}

	@Override
	public int getCountById(String mtId) {
		
		return this.auditRoleDao.getCountById(mtId);
	}

	@Override
	public PagePojo<AuditRole> selectAuditRoleListByMtId(int pageNo,int pageSize,String mtId) {
		PageHelper.startPage(pageNo, pageSize);
		List<AuditRole> arList = this.auditRoleDao.getAuditRoleListByMtId(mtId);
		return new PagePojo<>(arList);
	}

	public Map<String, Object> getAuditRoleList(Map<String, Object> map) {
		int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = auditRoleDao.getAuditRoleCount(map);
		List<AuditRole> auditRoleList = auditRoleDao.getAuditRoleList(map);
		Map<String, Object> auditRolepages = new HashMap<String, Object>();
		auditRolepages.put("total", totals);
		auditRolepages.put("rows", auditRoleList);
		return auditRolepages;
	}	
	
	@Override
	public AuditRole getAuditRoleById(String arId) {
		
		return this.auditRoleDao.getAuditRoleById(arId);
	}
	
	public List<AuditRole> getArByRoAndAstep(Map<String, Object> map){
		return auditRoleDao.getArByRoAndAstep(map);
	}
	
	 public String getRoleNameByMtAndAstep(int mt_code, int steps){
		 return auditRoleDao.getRoleNameByMtAndAstep(mt_code, steps);
	}
	//查找当前任务书审核的最大步骤
	public int getMaxStepByMtId(String mtId){
		return auditRoleDao.getMaxStepByMtId(mtId);
	}
	
	//通过任务书类型和角色 查询当前步骤
    public int getCexStepByMtIdAndRoId(String mtId, String RoId){
    	return auditRoleDao.getCexStepByMtIdAndRoId(mtId, RoId);
    }

	@Override
	public List<AuditRole> getAuditRoleByMtCode(int mtCode) {
		
		return this.auditRoleDao.getAuditRoleByMtCode(mtCode);
	}
	
	

}
