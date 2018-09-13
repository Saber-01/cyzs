package com.org.cygs.controller;

import java.util.Map;








import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.Role;
import com.org.cygs.service.RoleService;
import com.org.cygs.util.common.StringUtil;

@Controller
public class RoleController {
	@Autowired
	private RoleService roleServcie;

	@RequestMapping("/roleList")
	public String roleList() {
		return "listRole";
	}
	
	@RequestMapping("/roleSelect")
	public @ResponseBody Map<String, Object> roleSelect1(@RequestParam Map<String, Object> map){
		return roleServcie.getRoleList(map);
	}
	
	// 添加角色页面跳转
	@RequestMapping("/toAddRole")
	public String toAddPermission(Model model) {
	 String roleId = StringUtil.autoIncrement(roleServcie.getMaxRoleId());	
	 model.addAttribute("roleId", roleId);	
	 return "addRole";
	}
			
// 添加角色信息
   @RequestMapping("/addRole")
   public @ResponseBody String addRole(Role role) {
	   if(role != null){
		   try{
			   roleServcie.addRole(role);
			   return "1";
		   }catch (Exception e){
			  e.printStackTrace();
			  return "0";
		   }
		   
	   }else{
		   return "0";
	   }
	}
		
	
	@RequestMapping(value= "/toEditRole/{roId}", method=RequestMethod.GET)
	public String toEditRole(@PathVariable("roId") String roId,Model model){
		Role role = roleServcie.getRoleById(roId); 
		model.addAttribute("role", role);	
        return "editRole";
	}
	
	@RequestMapping(value= "/getRole")
	public @ResponseBody Role getRole(@RequestBody String roId){
		System.out.println(roId);
		Role role = roleServcie.getRoleById(roId); 	
        return role;
	}
	
	
	// 修改角色
	@RequestMapping("/editRole")
	public @ResponseBody String editRole(Role role) {
			if(role != null){
				   try{
					   roleServcie.updateRole(role);
				   }catch (Exception e){
						e.printStackTrace();
				   }
				   return "1";
			   }else{
				   return "0";
			   }
	}
	
	// 删除角色
	@RequestMapping("/delRole")
	public @ResponseBody String delPermissionGroup(@RequestBody String roId){
		System.out.println(roId);
		if(roId != null){
			   try{
				   roleServcie.deleteRole(roId);
				   return "1";
			   }catch (Exception e){
					e.printStackTrace();
					return "0";
			   }   
		   }else{
			   return "0";
		   }
	}
	
	@RequestMapping("/vfyRoleNameAdd")
	public @ResponseBody String vfyPgNameAdd(String roleName){
		System.out.println(roleName);
		String roId = roleServcie.getRoIdByRoleName(roleName);
		System.out.println(roId);
		//当前权限组名称已存在       返回false 
		if(roId != null && roId.length()>0){
			return "false";
		}else{
			return "true";
		}		
	}
	
	@RequestMapping("/vfyRoleNameEdit")
	public @ResponseBody String vfyPgNameEdit(String roleName, String roId){
		String ro_Id = roleServcie.getRoIdByRoleName(roleName);
		if(ro_Id != null && !(ro_Id.equals(roId))){
			return "false";
		}else{
			return "true";
		}		
	}
	

}
