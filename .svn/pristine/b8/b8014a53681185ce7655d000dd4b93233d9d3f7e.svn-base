package com.org.cygs.service;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.Permission;

public interface PermissionService {

    public List<Permission> getAllPermission();
	
	public List<Permission> getPermission(Permission permission);
	
	public void addPermission(Permission permission);
	
	public void deletePermission(String perId);
	
	public Permission getPermissionById(String perId);
	
	public void updatePermission(Permission Permission);
	
	public PagePojo<Permission> selectPermissionList(int pageNum, int pageSize, Permission permission);
	
	public Map<String, Object> getPermissionList(Map<String, Object> map);
	
	public String getPerIdByPerName(String permissionName);
	//获得相关权限组下最大权限编号
	public String getMaxPermissIdByPgId(String pgId);
}
