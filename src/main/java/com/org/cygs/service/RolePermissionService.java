package com.org.cygs.service;

import com.org.cygs.pojo.DataGrid;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.Role;
import com.org.cygs.pojo.RolePermission;

import java.util.List; 
import java.util.Map;
public interface RolePermissionService {
	
	public List<RolePermission> getRolePermissionByRoleId(String RoId);
	
	public List<RolePermission> getAllRolePermission();
	
	public List<RolePermission> getRolePermission(RolePermission rolePermission);
	
	//条件查询并分页
    public PagePojo<RolePermission> selectRolePermissionList(int pageNum, int pageSize, RolePermission rolePermission);
    
    public void deleteRolePermission(String roPerId);
	
	public void addRolePermission(RolePermission rolePermission);
	
	public DataGrid<RolePermission> getRolePermissionList(Map<String, Object> map);

}
