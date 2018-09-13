package com.org.cygs.service.impl;

import java.util.HashMap;
import java.util.List;  
import java.util.Map;

import javax.annotation.Resource;  

import org.springframework.stereotype.Service;  
 























import com.github.pagehelper.PageHelper;
import com.org.cygs.dao.UserDao;
import com.org.cygs.pojo.DataGrid;
import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.Role;
import com.org.cygs.pojo.User;  
import com.org.cygs.service.UserService;  
import com.org.cygs.util.common.MapUtil;
  
@Service("userService")  
public class UserServiceImpl implements UserService {  
    @Resource  
    private UserDao userDao;  
      
    public User getUserByUId(String u_ID) {  
        return userDao.getUserByUId(u_ID);  
    } 
    
    public String getUIdByuserId(String userId){
    	return userDao.getUIdByuserId(userId);
    }
    
    public User getUserByURId(String uro_ID){
    	return userDao.getUserByURId(uro_ID);
    }
    
    public User login(String loginname, String password){
    	return userDao.selectByLoginnameAndPassword(loginname,password);
    	
    }
  
    public List<User> getRoleByUserLoginName(String userLoginName){
    	return userDao.getRoleByUserLoginName(userLoginName);
    }
	
	//通过登录名和密码,角色查询用户
	public User selectByLoginnameAndPasswordandRole(String loginname,String password,String roId){
		return userDao.selectByLoginnameAndPasswordandRole(loginname, password, roId);
	}
    
    
  
    public void addUser(User user) {  
        userDao.addUser(user);  
    }
    public String getMaxUserId(){
    	return userDao.getMaxUserId();
    }
    
    //添加用户角色
    public void addUserRole(String uId, String roId){
    	userDao.addUserRole(uId, roId);
    }
    
    public void deleteUser(String UID){
    	userDao.deleteUser(UID);
    }
    
    public void updateUser(User user){  	
    	userDao.updateUser(user);
    }
    
    public List<User> getUserList(User user) {  
        return userDao.getUserList(user);  
    }
    
	public void alterPassword(String UId, String userPassword) {
		System.out.println("serviceImpl  修改密码" + userPassword);
		userDao.alterPassword(UId, userPassword);
	}
	
	 //更新用户信角色 
    public void updateUserRole(String uroId, String roId){
    	userDao.updateUserRole(uroId, roId);
    }
    
    //删除用户已有角色 
    public void deleteUserRoleByUId(String uId){
    	userDao.deleteUserRoleByUId(uId);
    	}

	@Override
	public List<User> getAllUserByType(String roleType) {
		return this.userDao.getAllUserByType(roleType);
	}
	
	
	public DataGrid<User> getUserListpp(Map<String, Object> map){
		MapUtil.MapPage(map);
		int totals = userDao.getUserListppCount(map);
		List<User> userList = userDao.getUserListpp(map);
		DataGrid<User> dg = new DataGrid<User>(totals,userList);
		return dg;
	}
	
	
	public String getUIdByLoginName(String userLoginName){
		return userDao.getUIdByLoginName(userLoginName);
	}
	
    
	
}  