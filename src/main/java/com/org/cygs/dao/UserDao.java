package com.org.cygs.dao;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.Role;
import com.org.cygs.pojo.User;

public interface UserDao {

	
	
	public String getUIdByuserId(String userId);
	
	//通过登录名和密码查询用户
	public User selectByLoginnameAndPassword(String loginname,String password);
	
	//通过登录名和密码以及角色查询用户
	//public User selectByLoginnameAndPassword(String loginname,String password,String roId);
	
	public List<User> getRoleByUserLoginName(String userLoginName);
	
	//通过登录名和密码,角色查询用户
	public User selectByLoginnameAndPasswordandRole(String loginname,String password,String roId);
    
    //添加用户
    public void addUser(User user);
    public String getMaxUserId();
   
    //删除用户
    public void deleteUser(String UID);
    
    //更新用户信息  
    public void updateUser(User user);  
    
    //更新用户信角色 
    public void updateUserRole(String uroId, String roId);
    
    //删除用户已有角色 
    public void deleteUserRoleByUId(String uId);
    
     //添加用户角色 
    public void addUserRole(String uId, String roId);
    
    //修改密码
    public void alterPassword(String UId,String userPassword);

    //通过用户主键查询特定用户
    public User getUserByUId(String u_ID);
    
  //通过用户角色主键查询特定用户
    public User getUserByURId(String uro_ID);
    
    //通过筛选条件查询
    public List<User> getUserList(User user);  
    
    //通过角色类型获取角色列表
    public List<User> getAllUserByType(String roleType);
    
    
    
    public List<User> getUserListpp(Map<String, Object> map);
    public int getUserListppCount(Map<String, Object> map);
    
    //添加用户时验证当前用户是否已经存在
    public String getUIdByLoginName(String userLoginName);
    

}