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
	
	//ͨ����¼��������,��ɫ��ѯ�û�
	public User selectByLoginnameAndPasswordandRole(String loginname,String password,String roId);
    
   
	 //�����û��Ž�ɫ 
    public void updateUserRole(String uroId, String roId);
    
    //ɾ���û����н�ɫ 
    public void deleteUserRoleByUId(String uId);
    

    //����û���ɫ
    public void addUserRole(String uId, String roId);
    public String getMaxUserId();
    //ɾ���û�
    public void deleteUser(String UID);
    
    //�����û���Ϣ  
    public void updateUser(User user); 
    
    //ͨ���û�������ѯ�ض��û�
    public User getUserByUId(String u_ID);
    
  //ͨ���û���ɫ������ѯ�ض��û�
    public User getUserByURId(String uro_ID);
   
    public List<User> getUserList(User user); 
   
    //ͨ����ɫ���ͻ�ȡ��ɫ�б�
    public List<User> getAllUserByType(String roleType);
    
    
    
    public DataGrid<User> getUserListpp(Map<String, Object> map);
    
    
    
  //����û�ʱ��֤��ǰ�û���¼���Ƿ��Ѿ�����
    public String getUIdByLoginName(String userLoginName);
    
    
}  