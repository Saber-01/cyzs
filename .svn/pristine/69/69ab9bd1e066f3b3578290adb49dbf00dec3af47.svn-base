package com.org.cygs.controller;


import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.cygs.pojo.DataGrid;
import com.org.cygs.pojo.Role;
import com.org.cygs.pojo.User;
import com.org.cygs.pojo.UserRole;
import com.org.cygs.service.UserService;
import com.org.cygs.service.RoleService;
import com.org.cygs.util.common.StringUtil;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
    //跳转到userList。jsp界面
	@RequestMapping("/userList")
	public String userList(Model model) {
		List<Role> roleList = roleService.getAllRole();
		model.addAttribute("rList", roleList);
		return "listUser";
	}
	
	//取出当前页面信息
	@RequestMapping("/userSelect")
	public @ResponseBody DataGrid<User> pp(@RequestParam Map<String, Object> map){
		return userService.getUserListpp(map);
	  } 
	
	
    //查看用户详情
	@RequestMapping("/viewUser/{uId}")
	public String viewUser(@PathVariable("uId") String uId,Model model) {
		
		User user = userService.getUserByUId(uId);
	    List<Role> userRoleList = roleService.getRoleByUId(uId);
	    model.addAttribute("rList", userRoleList);
		model.addAttribute("user", user);	
		
		return "viewUserInfo";
	}
	
	//删除用户
	@RequestMapping("/deleteUser")
	public @ResponseBody String deleteUser(@RequestBody String uId) {
		if (uId != null) {
			try {
				userService.deleteUserRoleByUId(uId);
				userService.deleteUser(uId);
				return "1";
			} catch (Exception e) {
				e.printStackTrace();
				return "0";
			}
		} else {
			return "0";
		}
	}
	
	

	//转到修改页面
		@RequestMapping("/toEditUser/{uId}")
		public String toEditUser(@PathVariable("uId") String uId,Model model){
			
			User user = userService.getUserByUId(uId);
			List<Role> userRoleList = roleService.getRoleByUId(uId);
			List<Role> userNotRoleList = roleService.getUserNotHasRole(uId);
			model.addAttribute("urList", userRoleList);
			model.addAttribute("nurList", userNotRoleList);
			model.addAttribute("user", user);
			return "editUser";
		}
		
	//修改用户  以及对应角色
	@RequestMapping("/editUser")
	public @ResponseBody String editUser(@RequestBody UserRole ur){
		User user = ur.getUser();
		List<String> roIdList = ur.getRoIdList();
		String u_Id = user.getuId();
		try{
			if(u_Id != null){
				   userService.deleteUserRoleByUId(u_Id);
				}
			if(roIdList != null && roIdList.size()>0){
				for (int i = 0; i < roIdList.size(); i++) {
					String roId= roIdList.get(i);
					userService.addUserRole(u_Id, roId);
				}
			}
		userService.updateUser(user);
		return "1";
		} catch (Exception e){
			e.printStackTrace();
			return "0";
	  }		
	}
	
	//验证登录名是否已经存在
	@RequestMapping("/vfyLgNameAdd")
	public @ResponseBody String vfyLgNameAdd(String userLoginName){
		String uId = userService.getUIdByLoginName(userLoginName);
		//当前登录名已存在       返回false 
		if(uId != null && uId.length()>0){
			return "false";
		}else{
			return "true";
		}		
	}
	
	//验证登录名是否已经存在
	@RequestMapping("/vfyLgNameEdit")
	public @ResponseBody String vfyLgNameEdit(String userLoginName,String uId){
		String u_Id = userService.getUIdByLoginName(userLoginName);
			//u_Id存在 且不是当前修改的用户     返回false 
		if(u_Id != null && !(u_Id.equals(uId))){
				return "false";
		}else{
				return "true";
		}		
	}
	
	
	
	//转到添加用户页面
	@RequestMapping("/toAddUser")
	public String toAddUser(Model model){
		List<Role> roleList = roleService.getAllRole();
		int int_userId = 0;
		String userId = userService.getMaxUserId();
		
		int_userId = StringUtil.parseInt(userId) + 1;
		String temp = "";
		for (int i = 0; i< userId.length() - StringUtil.trans(int_userId).length(); i ++){
			temp += "0";
		}
		userId = temp + StringUtil.trans(int_userId);
		System.out.println(userId);
		model.addAttribute("userId", userId);
		model.addAttribute("rList", roleList);
		return "addUser";
	}

	//添加用户页面点击提交后
   @RequestMapping("/addUser")
	public @ResponseBody String addUser(User user) {	
		System.out.println(user.getRoId());
		System.out.println(user.getUserName());
		//添加用户到 用户表	
        if(user != null){
			try{
				userService.addUser(user);
			} catch (Exception e){
				e.printStackTrace();
			}
			String roId = user.getRoId();
			if(roId != null && roId != ""){
				//添加用户选择的角色
				String userId = user.getUserId();
				String u_Id = userService.getUIdByuserId(userId);
				if(u_Id != null)
				{
					System.out.println(u_Id);
					System.out.println(roId);
					userService.addUserRole(u_Id, roId);
				}	
			}
			
		}
      return "1";
	}

	
}