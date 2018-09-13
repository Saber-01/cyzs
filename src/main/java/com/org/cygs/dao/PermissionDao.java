package com.org.cygs.dao;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.Permission;
import com.org.cygs.pojo.Role;

public interface PermissionDao {
	
	

	
    public List<Permission> getAllPermission();
	
	public List<Permission> getPermission(Permission permission);
	
	public void addPermission(Permission permission);
	
	public void deletePermission(String perId);
	
	public Permission getPermissionById(String perId);
	
	public void updatePermission(Permission Permission);
	
	
	public List<Permission> getPermissionList(Map<String, Object> map);
	public int getPermissionCount(Map<String, Object> map);
	
	
	public String getPerIdByPerName(String permissionName);
	//获得相关权限组下最大权限编号
	public String getMaxPermissIdByPgId(String pgId);
}
