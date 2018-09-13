package com.org.cygs.service;

import java.util.List;  
import java.util.Map;

import com.org.cygs.pojo.DataGrid;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.Role;
import com.org.cygs.pojo.User;  
  
public interface UserService {  
      
    public User login(String loginname, String password);  
  
    public void addUser(User user);
    
    public void alterPassword(String UId, String userPassword);
    
    public List<User> getRoleByUserLoginName(String userLoginName);
    
    public String getUIdByuserId(String userId);
	
	//通过登录名和密码,角色查询用户
	public User selectByLoginnameAndPasswordandRole(String loginname,String password,String roId);
    
   
	 //更新用户信角色 
    public void updateUserRole(String uroId, String roId);
    
    //删除用户已有角色 
    public void deleteUserRoleByUId(String uId);
    

    //添加用户角色
    public void addUserRole(String uId, String roId);
    public String getMaxUserId();
    //删除用户
    public void deleteUser(String UID);
    
    //更新用户信息  
    public void updateUser(User user); 
    
    //通过用户主键查询特定用户
    public User getUserByUId(String u_ID);
    
  //通过用户角色主键查询特定用户
    public User getUserByURId(String uro_ID);
   
    public List<User> getUserList(User user); 
   
    //通过角色类型获取角色列表
    public List<User> getAllUserByType(String roleType);
    
    
    
    public DataGrid<User> getUserListpp(Map<String, Object> map);
    
    
    
  //添加用户时验证当前用户登录名是否已经存在
    public String getUIdByLoginName(String userLoginName);
    
    
}  