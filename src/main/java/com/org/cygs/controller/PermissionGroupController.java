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

import com.org.cygs.pojo.PermissionGroup;
import com.org.cygs.service.PermissionGroupService;
import com.org.cygs.util.common.StringUtil;

@Controller
public class PermissionGroupController {
	@Autowired
	private PermissionGroupService permissionGroupService;

	//��ת��Ȩ�����б���ʾҳ��
	@RequestMapping("/permissionGroupList")
	public String permissionGroupList() {
		return "listPermissionGroup";
	}
	
	//��ȡȨ�����б���Ϣ������json����
	@RequestMapping("/permissionGroupSelect")
	public @ResponseBody Map<String, Object> permissionGroupSelect(@RequestParam Map<String, Object> map){
		return permissionGroupService.getPermissionGroupList(map);
	}
	
	// �޸�Ȩ�����ҳ����ת
	@RequestMapping(value="/toeditPermissionGroupById/{pgId}", method=RequestMethod.GET)
	public String toeditPermissionGroupById(@PathVariable("pgId") String pgId, Map<String, Object> map) {
		PermissionGroup permissionGroup = permissionGroupService.getPermissionGroupBypgId(pgId);
		map.put("permissionGroupInfo", permissionGroup);
		return "editPermissionGroup";
	}
	
	// �޸�Ȩ������Ϣ--����Ա����
	@RequestMapping("/editPermissionGroup")
	public @ResponseBody String editPermissionGroup(PermissionGroup permissionGroup) {
		if(permissionGroup != null){
			   try{
				   permissionGroupService.editPermissionGroup(permissionGroup);
				   return "1";
			   }catch (Exception e){
				  e.printStackTrace();
				  return "0";
			   }
			   
		   }else{
			   return "0";
		   }
	}
	
	// ɾ��Ȩ����
	@RequestMapping(value="/delPermissionGroup")
	public @ResponseBody String delPermissionGroup(@RequestBody String pgId){
		if(pgId != null){
			   try{
				   permissionGroupService.delPermissionGroup(pgId);
				   return "1";
			   }catch (Exception e){
				  e.printStackTrace();
				  return "0";
			   }
			   
		   }else{
			   return "0";
		   }
	}
	
	// ���Ȩ����ҳ����ת
	@RequestMapping(value = "/toaddPermissionGroup")
	public String toaddPermissionGroup(Model model) {
	 String permissionGroupId = StringUtil.autoIncrement(permissionGroupService.getMaxPGroupId());
	 model.addAttribute("permissionGroupId", permissionGroupId);
	 return "addPermissionGroup";
	}
	// ���Ȩ����
	@RequestMapping("/addPermissionGroup")
	public @ResponseBody String addPermissionGroup(PermissionGroup permissionGroup) {
		if(permissionGroup != null){
			   try{
				   permissionGroupService.addPermissionGroup(permissionGroup);
				   return "1";
			   }catch (Exception e){
				  e.printStackTrace();
				  return "0";
			   } 
			   
		   }else{
			   return "0";
		   }
	}
	
	
	@RequestMapping("/vfyPgNameAdd")
	public @ResponseBody String vfyPgNameAdd(String permissionGroupName){
		System.out.println(permissionGroupName);
		String pgId = permissionGroupService.getPgIdByPgName(permissionGroupName);
		System.out.println(pgId);
		//��ǰȨ���������Ѵ���       ����false 
		if(pgId != null && pgId.length()>0){
			return "false";
		}else{
			return "true";
		}		
	}
	
	@RequestMapping("/vfyPgNameEdit")
	public @ResponseBody String vfyPgNameEdit(String permissionGroupName, String pgId){
		String pg_Id = permissionGroupService.getPgIdByPgName(permissionGroupName);
		if(pg_Id != null && !(pg_Id.equals(pgId))){
			return "false";
		}else{
			return "true";
		}		
	}
	

}
