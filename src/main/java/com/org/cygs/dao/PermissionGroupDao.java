package com.org.cygs.dao;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.PermissionGroup;
public interface PermissionGroupDao {
	//获取当前角色拥有的权限组
   public List<PermissionGroup> getRolePermissionGroupByRoId(String RoId);
   
   //取得所有权限组
   public List<PermissionGroup> getAllPermissionGroup();
   
   //筛选权限组
   public List<PermissionGroup> getPermissionGroup(PermissionGroup permissionGroup);
   
   //通过pgId获取权限组
   public PermissionGroup getPermissionGroupBypgId(String pgId);
   
   //修改权限组
   public void editPermissionGroup(PermissionGroup permissionGroup);
   
   //删除某一权限组
   public void delPermissionGroup(String pgId);
   
   //添加权限组
   public void addPermissionGroup(PermissionGroup permissionGroup);
   
   public List<PermissionGroup> getPermissionGroupList(Map<String, Object> map);
   public int getPermissionGroupCount(Map<String, Object> map);
   
   
   public String getPgIdByPgName(String pgName);
   public String getMaxPGroupId();

}
