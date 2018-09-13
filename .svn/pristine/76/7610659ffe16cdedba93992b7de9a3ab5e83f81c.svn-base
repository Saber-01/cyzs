package com.org.cygs.controller;

import java.util.List;
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

import com.org.cygs.pojo.Permission;
import com.org.cygs.pojo.PermissionGroup;
import com.org.cygs.service.PermissionGroupService;
import com.org.cygs.service.PermissionService;
import com.org.cygs.util.common.StringUtil;

@Controller
@RequestMapping("/permission")
public class PermisssionController {
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private PermissionGroupService permissionGroupService;

	@RequestMapping("/permissionList")
	public String permissionList(Model model) {
		List<PermissionGroup> permissionGroupList = permissionGroupService.getAllPermissionGroup();
		model.addAttribute("pgList", permissionGroupList);
		return "listPermission";
	}
	
	@RequestMapping("/permissionSelect")
    public @ResponseBody Map<String, Object> permissionSelect1(@RequestParam Map<String, Object> map) {
        return permissionService.getPermissionList(map);
    }
	
	@RequestMapping("/vfyPerNameAdd")
	public @ResponseBody String vfyPerNameAdd(String permissionName){
		String perId = permissionService.getPerIdByPerName(permissionName);
		//当前权限名称已存在       返回false 
		if(perId != null && perId.length()>0){
			return "false";
		}else{
			return "true";
		}		
	}
	
	@RequestMapping("/vfyPerNameEdit")
	public @ResponseBody String vfyPerNameEdit(String permissionName, String perId){
		String per_Id = permissionService.getPerIdByPerName(permissionName);
		if(per_Id != null && !(per_Id.equals(perId))){
			return "false";
		}else{
			return "true";
		}		
	}
	
	
	//查看权限基本信息
		@RequestMapping("/viewPermission/{perId}")
		public String viewUser(@PathVariable("perId") String perId,Model model) {
			Permission permission = permissionService.getPermissionById(perId);
			model.addAttribute("permission", permission);
			return "viewPermissionInfo";
		}
	
	
	// 添加权限页面跳转
	@RequestMapping("/toAddPermission")
	public String toAddPermission(Model model) {
		List<PermissionGroup> permissionGroupList = permissionGroupService.getAllPermissionGroup();
		model.addAttribute("pgList", permissionGroupList);		
		return "addPermission";
	}
	
	// 添加权限信息--管理员功能
	@RequestMapping("/addPermission")
	public @ResponseBody String addPermission1(Permission permission) {
		String pgId = permission.getPgId();
		String permissionId = permissionService.getMaxPermissIdByPgId(pgId);
		if(permissionId!= null && permissionId.length()>0){
			permissionId = StringUtil.autoIncrement(permissionId);
		}else{
			String permissionGrouoId = permissionGroupService.getPermissionGroupBypgId(pgId).getPermissionGroupId();
			permissionId = permissionGrouoId+"000001";
		}
		permission.setPermissionId(permissionId);			
		if(permission != null){
			
			try{
				//permissionService.addPermission(permission);
				return "1";
			} catch (Exception e){
				e.printStackTrace();
				return "0";
			}
		}else{return "0";}	
		
	}
	
	
	
	// 修改权权限页面跳转
		@RequestMapping(value="/toeditPermissionById/{perId}", method=RequestMethod.GET)
		public String toeditPermissionById(@PathVariable("perId") String perId,Model model) {
			
			List<PermissionGroup> permissionGroupList = permissionGroupService.getAllPermissionGroup();
			model.addAttribute("pgList", permissionGroupList);
			
			Permission permission = permissionService.getPermissionById(perId);
			model.addAttribute("permission", permission);
			return "editPermission";
		}
		

	// 修改权限信息--管理员功能
	@RequestMapping(value="/editPermission", method=RequestMethod.POST)
	public @ResponseBody String editPermission(Permission permission) {
		System.out.println(permission.getPageAddress());
		if(permission != null){
			try{
				permissionService.updatePermission(permission);
			} catch (Exception e){
				e.printStackTrace();
			}
				return "1";
		}else{
				return "0";
		}
	}
		
		
		// 删除权限
	@RequestMapping("/delPermission")
	public @ResponseBody String delPermission(@RequestBody String perId){
	 if(perId != null){
		try{
			permissionService.deletePermission(perId);
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return "1";
		
	}else{
		return "0";
	}
  }
	

}
