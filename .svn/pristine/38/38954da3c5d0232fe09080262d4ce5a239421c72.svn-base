package com.org.cygs.service.impl;

import java.util.HashMap;
import java.util.List; 
import java.util.Map;

import javax.annotation.Resource;  

import org.springframework.stereotype.Service;










import com.github.pagehelper.PageHelper;
import com.org.cygs.dao.RolePermissionDao;
import com.org.cygs.pojo.DataGrid;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.Role;
import com.org.cygs.pojo.RolePermission;  
import com.org.cygs.pojo.User;
import com.org.cygs.service.RolePermissionService;
import com.org.cygs.util.common.MapUtil;


@Service("RolePermissionService") 
public class RolePermissionServiceImpl implements RolePermissionService{
	
	 @Resource  
	 private RolePermissionDao rolePermissionDao;
	 
	 public List<RolePermission> getRolePermissionByRoleId(String RoId){
		 return rolePermissionDao.getRolePermissionByRoleId(RoId);
	 }
	 
	 public List<RolePermission> getAllRolePermission(){
		 
		 return rolePermissionDao.getAllRolePermission();
	 }
	 
	 public PagePojo<RolePermission> selectRolePermissionList(int pageNum, int pageSize, RolePermission rolePermission){
		 List<RolePermission> te = rolePermissionDao.getRolePermission(rolePermission);
		 System.out.println(te.size());
		 String roID = new String();
		 String perID = new String();
		 RolePermission rpm = new RolePermission();
		 PageHelper.startPage(pageNum, pageSize);
		 List<RolePermission> rolePermissionList = rolePermissionDao.getRolePermission(rolePermission);
		 
		 for(int i=0; i<rolePermissionList.size(); i++){
			 roID = rolePermissionList.get(i).getRoId();
			 perID = rolePermissionList.get(i).getPerId();
			 rpm = rolePermissionDao.getRolePermissionByRoIdAndPerID(roID, perID);
			 if(rpm != null ){
				 rolePermissionList.get(i).setRoPerId(rpm.getRoPerId());
				 rolePermissionList.get(i).setIsDisplay(rpm.getIsDisplay());
			 }
		 }
		 
	    return new PagePojo<>(rolePermissionList);
	 }
	 
	 public List<RolePermission> getRolePermission(RolePermission rolePermission){
		 return rolePermissionDao.getRolePermission(rolePermission);
     }
	 
	 public void deleteRolePermission(String roPerId){
		 rolePermissionDao.deleteRolePermission(roPerId);
	 }
		
	 public void addRolePermission(RolePermission rolePermission){
		 rolePermissionDao.addRolePermission(rolePermission);
	}
	 
	public DataGrid<RolePermission> getRolePermissionList(Map<String, Object> map){
		MapUtil.MapPage(map);
		int totals = rolePermissionDao.getRolePermissionCount(map);
		List<RolePermission> rolePermissionList = rolePermissionDao.getRolePermissionList(map);
		DataGrid<RolePermission> dg = new DataGrid<RolePermission>(totals,rolePermissionList);
		return dg;
	 }

}
