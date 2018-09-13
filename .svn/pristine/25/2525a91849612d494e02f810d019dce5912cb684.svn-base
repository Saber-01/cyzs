package com.org.cygs.controller;


import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.org.cygs.pojo.AuditInfo;
import com.org.cygs.pojo.DataGrid;
import com.org.cygs.pojo.MissionType;
import com.org.cygs.pojo.Project;
import com.org.cygs.pojo.User;
import com.org.cygs.service.AuditInfoService;
import com.org.cygs.service.MissionTypeService;
import com.org.cygs.service.ProjectService;
import com.org.cygs.util.common.CygsConst;
import com.org.cygs.util.common.Tools;


@Controller
public class AuditInfoController {
	
	@Autowired
	private AuditInfoService auditInfoService;
	
	@Autowired
	private MissionTypeService missionTypeService;
	
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping("/auditInfoList")
	public String missionList(Model model,HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		List<MissionType> missiontypeList= missionTypeService.getMissionType();
		List<Project> projectList = projectService.getAllProject();
		model.addAttribute("user", user);
		model.addAttribute("mtList", missiontypeList);
		model.addAttribute("projectList", projectList);
		return "audit/listAuditInfo";
	}
	
	//查看审核信息列表
	@RequestMapping("/auditInfoSelect")
	public @ResponseBody DataGrid<AuditInfo> ListAuditInfo1(@RequestParam Map<String, Object> map,HttpSession session){
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);	
		String roleName = user.getRoleName();
		List<Project> projectList = Tools.getProjectList(roleName, user.getuId());
		if(projectList != null && projectList.size()>0){
			map.put("projectList", projectList);
		}
		map.put("roleName", user.getRoleName());
		map.put("userName", user.getUserName());
		return auditInfoService.getAuditInfo(map);	
	}
	
	
	
	
	//查看意见
	@RequestMapping("/viewAuditComment")
	public String viewAuditComment(String aId,Model model) {
		AuditInfo auditInfo = auditInfoService.getAuditInfoByAId(aId);
		model.addAttribute("auditInfo", auditInfo);
		return "audit/viewAuditComment";
	}
	
	
	//跳转到补充意见对话框
	@RequestMapping("/toAddAuditComment")
	public String toAddAuditComment(String aId,Model model) {
		AuditInfo auditInfo = auditInfoService.getAuditInfoByAId(aId);
		model.addAttribute("auditInfo", auditInfo);
		return "audit/addAuditComment";
	}
	//补充意见
	@RequestMapping("/addAuditComment")
	public @ResponseBody String addAuditComment(AuditInfo auditInfo) {
		if(auditInfo != null){
		 System.out.println("修改后的意见"+auditInfo.getAuditComment());
		 auditInfoService.updateAuditComment(auditInfo);
		}
	 return "1";
	}

}
