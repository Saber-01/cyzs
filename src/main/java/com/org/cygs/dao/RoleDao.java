package com.org.cygs.dao;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.Role;
public interface RoleDao {
	
	public List<Role> getAllRole();
	
	public List<Role> getRole(Role role);
	
	public List<Role> getRole1(Map<String, Object> map);
	
	public int getCount(Role role);
	
	public List<Role> getRoleByUId(String uId);
	
	public List<Role> getUserNotHasRole(String uId);
	
	public void addRole(Role role);
	
	public void deleteRole(String RoId);
	
	public Role getRoleById(String RoId);
	
	public void updateRole(Role role);
	
	
	public List<Role> getRoleList(Map<String, Object> map);
	public int getRoleCount(Map<String, Object> map);
	
	
	public String getRoIdByRoleName(String roleName);
	public String getMaxRoleId();
	

}
