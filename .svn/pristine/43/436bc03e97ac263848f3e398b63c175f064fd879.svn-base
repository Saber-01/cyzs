package com.org.cygs.service.impl;


import java.util.HashMap;
import java.util.List;  
import java.util.Map;

import javax.annotation.Resource;  

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.org.cygs.dao.RoleDao;
import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.Role;
import com.org.cygs.pojo.User;
import com.org.cygs.service.RoleService;

@Service("roleService") 
public class RoleServiceImpl implements RoleService{
	@Resource  
    private RoleDao roleDao;
	 
    public List<Role> getAllRole() {  
        return roleDao.getAllRole();  
    }
    
    
    public List<Role> getRole(Role role){
    	return roleDao.getRole(role);
    }
    
    public List<Role> getRoleByUId(String uId){
    	return roleDao.getRoleByUId(uId);
    }
    
    public List<Role> getUserNotHasRole(String uId){
    	return roleDao.getUserNotHasRole(uId);
    }
	
	public void addRole(Role role){
		roleDao.addRole(role);
	}
	
	public void deleteRole(String RoId){
		roleDao.deleteRole(RoId);
	}
	
	public Role getRoleById(String RoId){
		
		return roleDao.getRoleById(RoId);
		
	}
	
	public void updateRole(Role role){
		roleDao.updateRole(role);
	}
	
	public PagePojo<Role> selectRoleList(int pageNum, int pageSize, Role role){
		PageHelper.startPage(pageNum, pageSize);
    	List<Role> roleList = roleDao.getRole(role);
    	//List<Role> roleList = roleDao.getAllRole();
    	return new PagePojo<>(roleList);
	}
	
	
	public Page<Role> selectRoleList1(int pageNum, int pageSize, Role role){
		int offset = (pageNum-1)*pageSize;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageSize", pageSize);
		params.put("offset", offset);
		params.put("roleId", role.getRoleId());
		params.put("roleName", role.getRoleName());
		
		
		int totals = roleDao.getCount(role);
		List<Role> roleList = roleDao.getRole1(params);	
		Page<Role> rolePage1= new Page<Role>(roleList,totals,pageNum,pageSize,roleList.size());
		
		Page<Role> rolePage= new Page<Role>();
		rolePage.setList(roleList);
		rolePage.setPageNum(pageNum);
		rolePage.setPageSize(pageSize);
		rolePage.setSize(15);
		rolePage.setTotalRecord(totals);
		rolePage.setTotalPage(2);
		
		return rolePage1;
		
	}
	
	public Map<String, Object> getRoleList(Map<String, Object> map){
		int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = roleDao.getRoleCount(map);
		List<Role> roleList = roleDao.getRoleList(map);
		Map<String, Object> rolepages = new HashMap<String, Object>();
		rolepages.put("total", totals);
		rolepages.put("rows", roleList);
		return rolepages;
	}
	
	public String getRoIdByRoleName(String roleName){
		return roleDao.getRoIdByRoleName(roleName);
	}
	public String getMaxRoleId(){
		return roleDao.getMaxRoleId();
	}

}
