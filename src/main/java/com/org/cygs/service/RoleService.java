package com.org.cygs.service;


import java.util.List;  
import java.util.Map;

import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.Role;

public interface RoleService {
	
    public List<Role> getAllRole();
	
	public List<Role> getRole(Role role);
	
	public List<Role> getRoleByUId(String uId);
	
	//取出用户没有的角色
	public List<Role> getUserNotHasRole(String uId);
	
	public void addRole(Role role);
	
	public void deleteRole(String RoId);
	
	public Role getRoleById(String RoId);
	
	public void updateRole(Role role);
	
	//条件查询并分页
	public PagePojo<Role> selectRoleList(int pageNum, int pageSize, Role role);
	
	public Page<Role> selectRoleList1(int pageNum, int pageSize, Role role);
	
	public Map<String, Object> getRoleList(Map<String, Object> map);
	public String getRoIdByRoleName(String roleName);
	public String getMaxRoleId();
}
