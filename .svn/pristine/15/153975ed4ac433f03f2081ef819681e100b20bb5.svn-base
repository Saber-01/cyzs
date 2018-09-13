package com.org.cygs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;  

import org.springframework.stereotype.Service;  

import com.github.pagehelper.PageHelper;
import com.org.cygs.dao.PermissionDao;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.Permission;
import com.org.cygs.pojo.User;
import com.org.cygs.service.PermissionService;  


@Service("PermissionService") 
public class PermissionServiceImpl  implements PermissionService{
	 @Resource  
	 private PermissionDao permissionDao;
	 
	 
	 
	 public List<Permission> getAllPermission() {
		
		return permissionDao.getAllPermission();
	    } 
	 
	 
		public List<Permission> getPermission(Permission permission){
			return permissionDao.getPermission(permission);
		}
		
		public void addPermission(Permission permission){
			permissionDao.addPermission(permission);
		}
		
		public void deletePermission(String perId){
			permissionDao.deletePermission(perId);
		}
		
		public Permission getPermissionById(String perId){
			return permissionDao.getPermissionById(perId);		
		}
		
		public void updatePermission(Permission Permission){
			permissionDao.updatePermission(Permission);
		}
		
		public PagePojo<Permission> selectPermissionList(int pageNum, int pageSize, Permission permission){
			PageHelper.startPage(pageNum, pageSize);
	    	List<Permission> permissionList = permissionDao.getPermission(permission);
	    	return new PagePojo<>(permissionList);
		}
		
		public Map<String, Object> getPermissionList(Map<String, Object> map){
			int pageNo = Integer.parseInt((String) map.get("page"));
			int pageSize = Integer.parseInt((String) map.get("rows"));
			int offset = (pageNo-1)*pageSize;
			map.put("offset", offset);
			map.put("pageSize", pageSize);
			int totals = permissionDao.getPermissionCount(map);
			List<Permission> permissionList = permissionDao.getPermissionList(map);
			Map<String, Object> permissionpages = new HashMap<String, Object>();
			permissionpages.put("total", totals);
			permissionpages.put("rows", permissionList);
			return permissionpages;
		}
	 
		public String getPerIdByPerName(String permissionName){
			return permissionDao.getPerIdByPerName(permissionName);
		}
		
		//获得相关权限组下最大权限编号
		public String getMaxPermissIdByPgId(String pgId){
			return permissionDao.getMaxPermissIdByPgId(pgId);
		}

}
