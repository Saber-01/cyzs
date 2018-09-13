package com.org.cygs.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.cygs.pojo.MissionType;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.Role;
import com.org.cygs.pojo.AuditRole;
import com.org.cygs.service.MissionTypeService;
import com.org.cygs.service.RoleService;
import com.org.cygs.service.AuditRoleService;

@Controller
@RequestMapping(value="/auditRole")
public class AuditRoleController {

	@Autowired
	private AuditRoleService auditRoleService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private MissionTypeService missionTypeService;
	
	@RequestMapping(value="/showAuditRole")
	public String showYAuditRole(Model model){
		List<MissionType> mtList = this.missionTypeService.getMissionType();
		model.addAttribute("mtList", mtList);
		return "listAuditRole";
	}
	
	@RequestMapping(value="/selectAuditRoleList")
	@ResponseBody
	public PagePojo selectYAuditRoleList(@RequestBody Map<String,String> map){
		String mtId = map.get("mtId").toString();
		int pageNo = Integer.parseInt(map.get("pageNo").toString());
		PagePojo<AuditRole> arList = this.auditRoleService.selectAuditRoleListByMtId(pageNo,15,mtId);
		return arList;
	}
	
	@RequestMapping(value="/selectAuditRoleList1")
	public @ResponseBody Map<String, Object> selectAuditRoleList1(@RequestParam Map<String, Object> map){
		
		return auditRoleService.getAuditRoleList(map);
	}	
	
	@RequestMapping(value="/delete/{arId}")
	public String deleteYAuditRole(@PathVariable String arId){
		this.auditRoleService.deleteByPrimaryKey(arId);
		return "redirect:/auditRole/showAuditRole";
	}
	@RequestMapping(value="/deleteAuditRole1")
	@ResponseBody
	public String deleteYAuditRole1(@RequestBody String arId){
		this.auditRoleService.deleteByPrimaryKey(arId);
		return "1";
	}
	@RequestMapping(value="/add")
	public String gotoAdd(HttpServletRequest request,Model model){
		String mtId = (String)request.getParameter("mtId");
		MissionType mt = this.missionTypeService.getMissionTypeById(mtId);
		int auditStep = this.auditRoleService.getCountById(mtId);
		List<Role> roleList = this.roleService.getAllRole();
		model.addAttribute("mt", mt);
		model.addAttribute("auditStep", auditStep);
		model.addAttribute("roleList", roleList);
		return "addAuditRole";
	}
	@RequestMapping(value="/add1/{mtId}")
	public String gotoAdd1(@PathVariable String mtId,Model model){
		MissionType mt = this.missionTypeService.getMissionTypeById(mtId);
		int auditStep = this.auditRoleService.getCountById(mtId);
		List<Role> roleList = this.roleService.getAllRole();
		model.addAttribute("mt", mt);
		model.addAttribute("auditStep", auditStep);
		model.addAttribute("roleList", roleList);
		return "addAuditRole";
	}
	@RequestMapping(value="/addAndSubmit/{mtId}")
	public String addAndSubmit(HttpServletRequest request,@PathVariable String mtId){
		int auditStep = Integer.parseInt((String)request.getParameter("auditStep"));
		String roId = (String)request.getParameter("roId");
		String remark = (String)request.getParameter("remark");
		if(roId==null || roId==""){
			System.out.println("ERROR--->AuditRole 添加时角色不能为空！");
		}else{
			AuditRole ar = new AuditRole();
			ar.setAuditStep(auditStep);
			ar.setMtId(mtId);
			ar.setRemark(remark);
			ar.setRoId(roId);
		this.auditRoleService.insertSelective(ar);
		}
		return "redirect:/auditRole/showAuditRole";
	}
	@RequestMapping(value="/addAuditRole1")
	@ResponseBody
	public String addAndSubmit1(AuditRole ar){
		int auditStep = ar.getAuditStep();
		String roId = ar.getRoId();
		String remark = ar.getRemark();
		if(roId==null || roId==""){
			System.out.println("ERROR--->AuditRole 添加时角色不能为空！");
			return "format probelm!";
		}else{
		this.auditRoleService.insertSelective(ar);
		return "1";
		}
	}
	@RequestMapping(value="detail/{arId}")
	public String showYAuditRoleDetail(@PathVariable String arId,Model model){
		AuditRole ar = this.auditRoleService.getAuditRoleById(arId);
		model.addAttribute("ar", ar);
		return "listAuditRoleDetail";
	}
	
	@RequestMapping(value="/edit/{arId}")
	public String gotoEdit(@PathVariable String arId,Model model){
		AuditRole ar = this.auditRoleService.getAuditRoleById(arId);
		model.addAttribute("ar", ar);
		return "editAuditRole";
	}
	
	@RequestMapping(value="/editAndSubmit/{arId}")
	public String editAndSubmit(HttpServletRequest request,@PathVariable String arId){
		String remark = (String)request.getParameter("remark");
		String step = (String)request.getParameter("auditStep");
		int auditStep;
		if(step==null || step=="" || step.length()>1){
			System.out.println("ERROR--->AuditRole 修改时，任务书步骤不能为空，或超出长度！");
		}else{
			auditStep = Integer.parseInt(step);
			String mtId = this.auditRoleService.getAuditRoleById(arId).getMtId();
			int count = this.auditRoleService.getCountById(mtId);
			if(auditStep>count || auditStep<0){
				System.out.println("ERROR--->AuditRole 步骤数小于0或超出最大值！");
			}else{
				AuditRole ar = new AuditRole();
				ar.setRemark(remark);
				ar.setArId(arId);
				ar.setAuditStep(auditStep);
				this.auditRoleService.updateByPrimaryKeySelective(ar);
			}
		}	
		return "redirect:/auditRole/showAuditRole";
	}
	
	@RequestMapping(value="/editAuditRole1")
	@ResponseBody
	public String editAndSubmit1(AuditRole ar){
		String remark = ar.getRemark();
		String step = ar.getAuditStep().toString();
		int auditStep;
		if(step==null || step=="" || step.length()>1){
			System.out.println("ERROR--->AuditRole 修改时，任务书步骤不能为空，或超出长度！");
			return "format error1!";
		}else{
			auditStep = Integer.parseInt(step);
			String mtId = this.auditRoleService.getAuditRoleById(ar.getArId()).getMtId();
			int count = this.auditRoleService.getCountById(mtId);
			if(auditStep>count || auditStep<0){
				System.out.println("ERROR--->AuditRole 步骤数小于0或超出最大值！");
				return "format error2!";
			}else{
				this.auditRoleService.updateByPrimaryKeySelective(ar);
				return "1";
			}
		}	
	}
}
