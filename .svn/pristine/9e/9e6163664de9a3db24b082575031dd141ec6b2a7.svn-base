package com.org.cygs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.cygs.pojo.User;
import com.org.cygs.pojo.PermissionGroup;
import com.org.cygs.service.UserService;
import com.org.cygs.util.common.CygsConst;
import com.org.cygs.pojo.RolePermission;
import com.org.cygs.service.MissionService;
import com.org.cygs.service.RolePermissionService;
import com.org.cygs.service.PermissionGroupService;


@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UserService userService;

	@Autowired
	private RolePermissionService rolePermissionService;

	@Autowired
	private PermissionGroupService permissionGroupService;
	
	@Autowired
	private MissionService missionService;
	

	// 登录
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpSession session, RedirectAttributes attr, Model model) {
		String user_loginname = String.valueOf(request.getParameter("name"));
		String user_password = String.valueOf(request.getParameter("password"));
		String user_roId = String.valueOf(request.getParameter("roId"));
		User user = userService.selectByLoginnameAndPasswordandRole(user_loginname, user_password, user_roId);
		//User user = userService.login(user_loginname, user_password);
		if (user != null) {
			session.removeAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS");
			session.setAttribute(CygsConst.USER_SESSION, user);
			model.addAttribute("user", user);
			return "main";
		} 
		else if (user == null && user_loginname != "null" && user_password != "null") {
			session.removeAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS");
			attr.addFlashAttribute("message", "*登录名或密码错误!请重新输入");
			return "redirect:/login.jsp";
		}
		else return "redirect:/login.jsp";
	}
	
	@RequestMapping("/checkRole")
	public @ResponseBody String updateRolePermissionSelect(@RequestBody String userLoginName){
		ObjectMapper mapper = new ObjectMapper();
		List<User> userList = userService.getRoleByUserLoginName(userLoginName);
		System.out.println(userLoginName);
		if(userList.size()>0)
		{
			String jsonlist = new String();
			try {
				jsonlist = mapper.writeValueAsString(userList);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return jsonlist;
		}
		else
		{
			return "error";
		}
		
	}
	
	/**
	 * 处理/main请求
	 * */
	@RequestMapping("/main")
	public String main(HttpSession session, Model model) {
		
		System.out.println(CygsConst.USER_SESSION);
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		List<RolePermission> rolePermissionList = rolePermissionService.getRolePermissionByRoleId(user.getRoId());
		List<PermissionGroup> permissionGroupList = permissionGroupService.getPermissionGroupByRoId(user.getRoId());
		
		model.addAttribute("user", user);
		model.addAttribute("rolePermissionList", rolePermissionList);
		model.addAttribute("permissionGroupList", permissionGroupList);
		return "main";
	}
	
	
	//生成左边菜单栏json数据
	@RequestMapping("/menuSelect")
	public @ResponseBody List<Map<String, Object>> ListMenu(HttpSession session){
		System.out.println(CygsConst.USER_SESSION);
		System.out.println("gggggggggg");
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		List<RolePermission> rolePermissionList = rolePermissionService.getRolePermissionByRoleId(user.getRoId());
		List<PermissionGroup> permissionGroupList = permissionGroupService.getPermissionGroupByRoId(user.getRoId());
		Map<String, Object> mapSystemMenu = new HashMap<String, Object>();
		List<Map<String, Object>> listMenu = new ArrayList<Map<String, Object>>();
		for(int i=0; i<permissionGroupList.size(); i++){
			PermissionGroup pg = permissionGroupList.get(i);
			String pergId = pg.getPermissionGroupId();
			Map<String, Object> mapMenu = new HashMap<String, Object>();
			List<Map<String, Object>> listChildren = new ArrayList<Map<String, Object>>();
			for(int j=0; j<rolePermissionList.size(); j++){
				RolePermission rp = rolePermissionList.get(j);
				String rpergId = rp.getPermissionGroupId();
				if(pergId.equals(rpergId)){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("title", rp.getPermissionName());  
			        map.put("href", rp.getPageAddress());
			        listChildren.add(map); 
				}	
			}
			mapMenu.put("title",pg.getPermissionGroupName());
			mapMenu.put("children",listChildren);	
			listMenu.add(mapMenu);
		}
		mapSystemMenu.put("title", "系统操作菜单");
		mapSystemMenu.put("isCurrent", true);
		mapSystemMenu.put("menu", listMenu);
		List<Map<String, Object>> listSysMenu = new ArrayList<Map<String, Object>>();
		listSysMenu.add(mapSystemMenu);
		 ObjectMapper mapper = new ObjectMapper();
		String jsonfromMap;
		try {
			jsonfromMap = mapper.writeValueAsString(listSysMenu);
			System.out.println(jsonfromMap);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		return listSysMenu;
}
	
	

	/**
	 * 用户注销
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS");
		session.removeAttribute(CygsConst.USER_SESSION);
		return "redirect:/login.jsp";
	}

	/*
	 * public String login(HttpServletRequest request,Model model){
	 * 
	 * String user_loginname = String.valueOf(request.getParameter("name"));
	 * String user_password = String.valueOf(request.getParameter("password"));
	 * User user = userService.login(user_loginname, user_password); if(user !=
	 * null) { //登录成功 //model.addAttribute("user", user); //List<User> uList =
	 * userService.getAllUser(); //model.addAttribute("uList", uList); return
	 * "main"; } else { request.setAttribute("message","用户名密码错误"); return
	 * "redirect:/login.jsp"; } }
	 */

}