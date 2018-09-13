package com.org.cygs.service;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.PermissionGroup;

public interface PermissionGroupService {
	
	public List<PermissionGroup> getPermissionGroupByRoId(String RoId);
	
	public List<PermissionGroup> getAllPermissionGroup();
	//通过pgId获取权限组
	public PermissionGroup getPermissionGroupBypgId(String pgId);
	//修改权限组
	public void editPermissionGroup(PermissionGroup permissionGroup);
	//删除某一权限组
	public void delPermissionGroup(String pgId);
	//添加权限组
	public void addPermissionGroup(PermissionGroup permissionGroup);
	
	//获取当前页的权限组
	public PagePojo<PermissionGroup> selectPermissionnGroupList(int pageNum, int pageSize, PermissionGroup permissionGroup);
	public Map<String, Object> getPermissionGroupList(Map<String, Object> map);
	
	public String getPgIdByPgName(String pgName);
	public String getMaxPGroupId();
}
