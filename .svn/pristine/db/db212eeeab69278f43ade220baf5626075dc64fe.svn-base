package com.org.cygs.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.cygs.pojo.DataGrid;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.PermissionGroup;
import com.org.cygs.pojo.Role;
import com.org.cygs.pojo.RolePermission;
import com.org.cygs.service.PermissionGroupService;
import com.org.cygs.service.RolePermissionService;
import com.org.cygs.service.RoleService;

@Controller
public class RolePermissionController {
	@Autowired
	private RolePermissionService rolePermissionService;
	
	@Autowired
	private PermissionGroupService PermissionGroupService;
	
	@Autowired
	private RoleService roleService;

	@RequestMapping("/rolePermissionList")
	public String rolePermissionList(Model model) {
		List<PermissionGroup> permissionGroupList = PermissionGroupService.getAllPermissionGroup();
		List<Role> roleList = roleService.getAllRole();
		
		model.addAttribute("pgList", permissionGroupList);
		model.addAttribute("rList", roleList);
		return "listRolePermission";
	}
	
	//取出当前页面信息
	@RequestMapping("/rolePermissionSelect")
		public @ResponseBody DataGrid<RolePermission> rolePermissionSelect1(@RequestParam Map<String, Object> map){
		/*Map<String, Object> a = rolePermissionService.getRolePermissionList(map);
		ObjectMapper mapper = new ObjectMapper();
		String jsonfromMap;
		try {
			jsonfromMap = mapper.writeValueAsString(a);
			System.out.println(jsonfromMap);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/ 	
	    return rolePermissionService.getRolePermissionList(map);	
	} 
	
	
	
	
	
	@RequestMapping("/updateRolePermissionSelect")
	public @ResponseBody String updateRolePermissionSelect(@RequestBody String jsonString) throws JsonParseException, JsonMappingException, IOException{		
		 RolePermission roPer = new RolePermission();	 
		 String isSelect = new String();
		 ObjectMapper mapper = new ObjectMapper(); 
		 List<RolePermission> rolePermissionList = mapper.readValue(jsonString, new TypeReference<List<RolePermission>>() {});
		 for(int i=0;i<rolePermissionList.size();i++){
			 roPer = rolePermissionList.get(i);
			 isSelect = roPer.getIsSelect();
			 if(roPer.getRoPerId() != null && roPer.getRoPerId().length()>6){		
				 if(isSelect.equals("false")){
					 rolePermissionService.deleteRolePermission(roPer.getRoPerId());
					 System.out.println("删除表格第"+(i+1)+"条记录删除权限");
				 } 
			 }
			 else
			 {
				 if(isSelect.equals("true"))
				 {	 
		            System.out.println("添加表格第"+(i+1)+"条记录添加权限");
				    rolePermissionService.addRolePermission(roPer); 	 
				 } 
			 }
			 
		 }
		return isSelect;
	}

}
