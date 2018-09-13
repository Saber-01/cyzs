package com.org.cygs.service.impl;


import java.util.HashMap;
import java.util.List; 
import java.util.Map;

import javax.annotation.Resource;  

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.org.cygs.dao.PermissionGroupDao;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.Permission;
import com.org.cygs.pojo.PermissionGroup;  
import com.org.cygs.pojo.User;
import com.org.cygs.service.PermissionGroupService;

@Service("PermissionGroupService") 
public class PermissionGroupServiceImpl implements PermissionGroupService{
	
	@Resource  
	 private PermissionGroupDao permissionGroupDao;
	
	public List<PermissionGroup> getPermissionGroupByRoId(String RoId){
		return permissionGroupDao.getRolePermissionGroupByRoId(RoId);
	}
	
	public List<PermissionGroup> getAllPermissionGroup(){
		return permissionGroupDao.getAllPermissionGroup();
	}
	
	public void editPermissionGroup(PermissionGroup permissionGroup) {
		permissionGroupDao.editPermissionGroup(permissionGroup);
	}
	
	public PermissionGroup getPermissionGroupBypgId(String pgId) {
		return permissionGroupDao.getPermissionGroupBypgId(pgId);
	}
	
	public void delPermissionGroup(String pgId) {
		permissionGroupDao.delPermissionGroup(pgId);
	}

	public void addPermissionGroup(PermissionGroup permissionGroup) {
		permissionGroupDao.addPermissionGroup(permissionGroup);
	}
	
	
	public PagePojo<PermissionGroup> selectPermissionnGroupList(int pageNum, int pageSize, PermissionGroup permissionGroup){
    	PageHelper.startPage(pageNum, pageSize);
    	List<PermissionGroup> permissionGroupList = permissionGroupDao.getPermissionGroup(permissionGroup);
    	return new PagePojo<>(permissionGroupList);
    	
    }
	
	public Map<String, Object> getPermissionGroupList(Map<String, Object> map){
		int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = permissionGroupDao.getPermissionGroupCount(map);
		List<PermissionGroup> permissionGroupList = permissionGroupDao.getPermissionGroupList(map);
		Map<String, Object> permissionGrouppages = new HashMap<String, Object>();
		permissionGrouppages.put("total", totals);
		permissionGrouppages.put("rows", permissionGroupList);
		return permissionGrouppages;
	}
	
	public String getPgIdByPgName(String pgName){
		return permissionGroupDao.getPgIdByPgName(pgName);
	}
	
	public String getMaxPGroupId(){
		return permissionGroupDao.getMaxPGroupId();
	}
}
