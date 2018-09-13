package com.org.cygs.controller;


import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.cygs.pojo.AuditRole;
import com.org.cygs.pojo.CheckUnit;
import com.org.cygs.pojo.DataGrid;
import com.org.cygs.pojo.JJMission;
import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.MissionContent;
import com.org.cygs.pojo.MissionType;
import com.org.cygs.pojo.MissionVo;
import com.org.cygs.pojo.PLMission;
import com.org.cygs.pojo.PrIndexPC;
import com.org.cygs.pojo.Project;
import com.org.cygs.pojo.ProjectMission;
import com.org.cygs.pojo.User;
import com.org.cygs.service.AuditInfoService;
import com.org.cygs.service.AuditRoleService;
import com.org.cygs.service.CheckUnitService;
import com.org.cygs.service.MissionContentService;
import com.org.cygs.service.MissionService;
import com.org.cygs.service.MissionTypeService;
import com.org.cygs.service.ProjectIndexSService;
import com.org.cygs.service.ProjectService;
import com.org.cygs.service.SystemParameterService;
import com.org.cygs.util.common.CygsConst;
import com.org.cygs.util.common.Tools;

@Controller
public class MissionController {
	@Autowired
	private MissionService missionService;
	
	@Autowired
	private MissionTypeService missionTypeService;
	
	@Autowired
	private MissionContentService missionContentService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private AuditRoleService auditRoleService;
	
	@Autowired
	private AuditInfoService auditInfoService;
	
	@Autowired
	private CheckUnitService checkUnitService;
	
	@Autowired
	private ProjectIndexSService projectIndexSService;
	
	@Autowired
	private SystemParameterService  systemParameterService;
	
	@RequestMapping("/missionList")
	public String missionList(Model model) {
		List<MissionType> missiontypeList= missionTypeService.getMissionType();
		model.addAttribute("mtList", missiontypeList);
		return "audit/listMission";
	}
	
	//查看任务书、按条件查看任务书
	@RequestMapping("/missionSelectList")  
	public String missionSelectList(Model model) {		
		return "audit/listSelectMission";
	}
	
	//查看已提交待审核
	@RequestMapping("/missionSubmitList")  
	public String missionSubmitList(Model model) {		
		return "audit/listSubmitMission";
	}
		
	//查看未审退修任务书
	@RequestMapping("/missionUnAuditList")  
	public String missionUnAuditList(Model model) {
		Tools.checkStopAudit(model);
		return "audit/listUnAuditMission";
	}
	
	//查看本人已审后续未审任务书、、在审核中ing
	@RequestMapping("/missionAuditingList")  
	public String missionAuditingList(Model model) {
		Tools.checkStopAudit(model);
		return "audit/listAuditingMission";
	}
	
	//查看未处理完成任务书
	@RequestMapping("/missionUnCompletedList")  
	public String missionUnCompletedList(Model model) {		
		return "audit/listUnCompletedMission";
	}
		
	//查看已删除任务书
	@RequestMapping("/missionDeleteList")  
	public String missionDeleteList(Model model,HttpSession session) {
		List<MissionType> missiontypeList= missionTypeService.getMissionType();
		model.addAttribute("mtList", missiontypeList);
		return "audit/listDeleteMission";
	}
	
	
	//变更签证资料状态
	@RequestMapping("/missionBGQZList")  
	public String missionBGQZList(Model model) {
		List<MissionType> missiontypeList= missionTypeService.getMissionType();
		model.addAttribute("mtList", missiontypeList);
		return "audit/listBGQZMission";
	}
	
	
	//查看本人暂不处理任务书
	@RequestMapping("/missionStopAuditList")  
	public String missionStopAuditList(Model model,HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String uId = user.getuId();
		String roId = user.getRoId();
		int count = missionService.getStopAuditMissionCount(uId,roId);
		model.addAttribute("count", count);
		System.out.println("条数："+count);
		Tools.checkStopAudit(model);
		return "audit/listStopAuditMission";
	}

	@RequestMapping("/stopAuditMissionSelect")
	public @ResponseBody List<Mission> stopAuditMissionSelect(HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String uId = user.getuId();
		String roId = user.getRoId();
		List<Mission> missionList = missionService.getStopAuditMissionList(uId, roId);
		return missionList;
	}

	// 更新待处理任务书数量
	@RequestMapping("/updateAuditCount")
	public @ResponseBody Map<String, Object> updatAuditCount(HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);

		String roleName = user.getRoleName();
		String roId = user.getRoId();
		int stopAcount = missionService.getStopAuditMissionCount(user.getuId(), roId);

		Map<String, Object> mapRoAs = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		mapRoAs.put("roId", roId);
		List<AuditRole> auditRoleList = auditRoleService.getArByRoAndAstep(mapRoAs);
		if (auditRoleList != null && auditRoleList.size() > 0) {
			map.put("auditRoleList", auditRoleList);
			List<Project> projectList = Tools.getProjectList(roleName, user.getuId());
			map.put("projectList", projectList);
		}
		map.put("roleName", roleName);
		map.put("userName", user.getUserName());
		int toAuditCount = missionService.getToAuditMissionCount(map);

		map.clear();
		map.put("stopAcount", stopAcount);
		map.put("toAuditCount", toAuditCount);
		return map;
	}

	// 查看任务书列表
	@RequestMapping("/missionSelect")
	public @ResponseBody DataGrid<Mission> missionSelect1(@RequestParam Map<String, Object> map, HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String roleName = user.getRoleName();
		String uId = user.getuId();
		List<Project> projectList = Tools.getProjectList(roleName, uId);
		map.put("projectList", projectList);
		map.put("roleName", roleName);
		map.put("userName", user.getUserName());
		return missionService.getMissionList(map);
	}

	// 查看已提交待审核任务书--------工长
	@RequestMapping("/submitMissionSelect")
	public @ResponseBody DataGrid<Mission> ListSubmitMission1(@RequestParam Map<String, Object> map, HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String roId = user.getRoId();
		String roleName = user.getRoleName();
		Map<String, Object> mapRoAs = new HashMap<String, Object>();
		mapRoAs.put("roId", roId);
		mapRoAs.put("auditStep", 0);
		List<AuditRole> auditRoleList = auditRoleService.getArByRoAndAstep(mapRoAs);
		if (auditRoleList != null && auditRoleList.size() > 0) {
			map.put("auditRoleList", auditRoleList);
			if (roleName.equals("工长")) {
				map.put("roleName", roleName);
				map.put("userName", user.getUserName());
			}
		}
		return missionService.getSubmitMissionList(map);
	}

	// 查看未审/退修任务书------
	@RequestMapping("/unAduditMissionSelect")
	public @ResponseBody DataGrid<Mission> ListUnAuditSubmitMission1(@RequestParam Map<String, Object> map, HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String roId = user.getRoId();
		String roleName = user.getRoleName();
		Map<String, Object> mapRoAs = new HashMap<String, Object>();
		mapRoAs.put("roId", roId);
		List<AuditRole> auditRoleList = auditRoleService.getArByRoAndAstep(mapRoAs);
		if (auditRoleList != null && auditRoleList.size() > 0) {
			map.put("auditRoleList", auditRoleList);
			List<Project> projectList = Tools.getProjectList(roleName, user.getuId());
			map.put("projectList", projectList);
		}

		ObjectMapper mapper = new ObjectMapper();
		String jsonlist = new String();
		try {
			jsonlist = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(jsonlist);
		map.put("roleName", roleName);
		map.put("userName", user.getUserName());
		return missionService.getUnAuditMissionList(map);
	}

	// 本人已审后续未审查询
	@RequestMapping("/auditingMissionSelect")
	public @ResponseBody DataGrid<Mission> ListAuditingMission1(@RequestParam Map<String, Object> map, HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String roId = user.getRoId();
		// String roleName = user.getRoleName();
		Map<String, Object> mapRoAs = new HashMap<String, Object>();
		mapRoAs.put("roId", roId);
		System.out.println("uid：" + user.getuId());
		List<AuditRole> auditRoleList = auditRoleService.getArByRoAndAstep(mapRoAs);

		if (auditRoleList != null && auditRoleList.size() > 0) {
			map.put("auditRoleList", auditRoleList);
			map.put("uId", user.getuId());
		}
		return missionService.getAuditingMissionList(map);
	}

	@RequestMapping("/unCompletedMissionSelect")
	public @ResponseBody DataGrid<Mission> ListUnCompletedMission1(@RequestParam Map<String, Object> map, HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String roleName = user.getRoleName();
		List<Project> projectList = Tools.getProjectList(roleName, user.getuId());
		map.put("projectList", projectList);
		map.put("roleId", user.getRoleId());
		map.put("roleName", user.getRoleName());
		map.put("userName", user.getUserName());
		return missionService.getUnCompletedMissionList(map);
	}

	// 查看已删除任务书
	@RequestMapping("/missionDeleteSelect")
	public @ResponseBody DataGrid<Mission> ListDeleteMission1(@RequestParam Map<String, Object> map, HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String roleName = user.getRoleName();
		List<Project> projectList = Tools.getProjectList(roleName, user.getuId());

		map.put("projectList", projectList);
		map.put("roleName", user.getRoleName());
		map.put("userName", user.getUserName());
		return missionService.getDeleteMissionList(map);
	}

	// 变更签证资料状态
	@RequestMapping("/missionBGQZSelect")
	public @ResponseBody DataGrid<Mission> ListBGQZMission1(@RequestParam Map<String, Object> map, HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String roleName = user.getRoleName();
		List<Project> projectList = Tools.getProjectList(roleName, user.getuId());
		map.put("projectList", projectList);
		map.put("roleName", roleName);
		map.put("userName", user.getUserName());
		return missionService.getBGQZMissionList(map);
	}

	// 显示审核任务书列表
	@RequestMapping("/missionToAuditList")
	public String missionToAuditList(Model model, HttpSession session) {
		Tools.checkStopAudit(model);
		Tools.checkStopPlAudit(model, session);
		return "audit/listToAuditMission";
	}

	 //显示审核任务书列表
	@RequestMapping("/selectMissionToAudit")  
	public @ResponseBody List<ProjectMission> selectMissionToAudit(HttpSession session) {
			User user = (User) session.getAttribute(CygsConst.USER_SESSION);
			String roId = user.getRoId();
			String roleName = user.getRoleName();
			Map<String, Object> mapRoAs = new HashMap<>();
			Map<String, Object> map = new HashMap<>();
			mapRoAs.put("roId", roId);
			List<AuditRole> auditRoleList= auditRoleService.getArByRoAndAstep(mapRoAs);
			if(auditRoleList != null && auditRoleList.size() > 0)
			{
			  map.put("auditRoleList", auditRoleList);
			  List<Project> projectList = Tools.getProjectList(roleName, user.getuId());
			  map.put("projectList", projectList); 
			}
			map.put("roleName", roleName);
			map.put("userName", user.getUserName());
			List<Mission> missionList = missionService.getToAuditMissionList(map);
				
			ObjectMapper mapper = new ObjectMapper();
			String b;
			try {
				b = mapper.writeValueAsString(missionList);
				System.out.println(b);
			} catch (JsonProcessingException e1) {		
				e1.printStackTrace();
				}
			System.out.println("size："+missionList.size());
			Map<String, Object> mapMssion = new HashMap<>();
			List<Map> mapList = new ArrayList<Map>();
			List<ProjectMission> pjMissionList = new ArrayList<ProjectMission>();
			for(int i = 0; i<missionList.size(); i++){
				ProjectMission pjMission = new ProjectMission();
				int count = 1;
				String prId = missionList.get(i).getPrId();
				String prName = missionList.get(i).getPrName();
				//missionList.remove(0);
				for(int j = i+1; j<missionList.size(); j++){
					String prId_j = missionList.get(j).getPrId();
					if(prId.equals(prId_j)){
					    count++;
						missionList.remove(j);
						j--;
					}
				}
					mapMssion.put("prId", prId);
					mapMssion.put("prName", prName);
					mapMssion.put("count", count);
					mapList.add(mapMssion);
					pjMission.setPrId(prId);
					pjMission.setPrName(prName);
					pjMission.setCount(count);
					pjMissionList.add(pjMission);
					
			}
			return pjMissionList;
		}
	
	// 逐条审核
	@RequestMapping("/toAuditByOne")
	public String toAuditByOne(String prId, Model model, HttpSession session) {
		// session.setAttribute("prId", prId);
		System.out.println("工程bian==hao====：" + prId);
		Project pj = projectService.getProjectById(prId);
		List<MissionType> missiontypeList = missionTypeService.getMissionType();
		List<PrIndexPC> prIndexPcList = projectService.getProjectDetailById(prId);
		model.addAttribute("prId", prId);
		model.addAttribute("mtList", missiontypeList);
		model.addAttribute("prIndexPcList", prIndexPcList);
		model.addAttribute("pj", pj);
		return "audit/auditByOne";
	}
	
	// 显示逐条审核任务书列表 已知prid------
	@RequestMapping("/AuditByOne")
	public @ResponseBody List<Mission> AuditByOne(@RequestParam Map<String, Object> map, HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String roId = user.getRoId();
		String roleName = user.getRoleName();
		Map<String, Object> mapRoAs = new HashMap<>();
		mapRoAs.put("roId", roId);
		List<AuditRole> auditRoleList = auditRoleService.getArByRoAndAstep(mapRoAs);
		if (auditRoleList != null && auditRoleList.size() > 0) {
			map.put("auditRoleList", auditRoleList);
			List<Project> projectList = Tools.getProjectList(roleName, user.getuId());
			map.put("projectList", projectList);
			map.put("roleName", roleName);
			map.put("userName", user.getUserName());
		}
		map.put("auditRoleList", auditRoleList);
		map.put("roleName", roleName);
		List<Mission> missionList = missionService.getToAuditMissionListByPrId(map);
		System.out.println("审核任务书：" + missionList.size());
		return missionList;
	}
	

	//显示批量审核任务书列表
	@RequestMapping("/toAuditPL/{prId}")  
	public String toAuditPL(@PathVariable("prId") String prId, Model model, HttpSession session) {
		Project pj = projectService.getProjectById(prId);
		List<MissionType> missiontypeList= missionTypeService.getMissionType();
		List<PrIndexPC> prIndexPcList = projectService.getProjectDetailById(prId);
		//model.addAttribute("plMissionList", plMissionList);		 
		model.addAttribute("mtList", missiontypeList);
		model.addAttribute("prIndexPcList", prIndexPcList);
		model.addAttribute("pj", pj);
		return "audit/auditPL";
	}
	@RequestMapping("/listAuditPL/{prId}")  
	public @ResponseBody List<PLMission> listAuditPL(@PathVariable("prId") String prId, @RequestBody Map<String, Object> map, Model model, HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String roId = user.getRoId();
		String roleName = user.getRoleName();

		Map<String, Object> mapRoAs = new HashMap<>();
		//Map<String, Object> map = new HashMap<>();
		mapRoAs.put("roId", roId);
		List<AuditRole> auditRoleList= auditRoleService.getArByRoAndAstep(mapRoAs);
		map.put("auditRoleList", auditRoleList);
		map.put("roleName", roleName);
		map.put("prId", prId);
		List<Mission> missionList = missionService.getToAuditMissionListByPrId(map);
		List<PLMission> plMissionList = new ArrayList<PLMission>();
		if(missionList != null && missionList.size()>0)
		{
		   for(int i=0; i<missionList.size(); i++){
			  PLMission plMission = new PLMission();
			  Mission mission = missionList.get(i);
			  String mId = mission.getmId();
			  List<MissionContent> missionContentList;
			  if (missionList.get(i).getMtName().equals("合同内其他任务书")) {
				  missionContentList = missionContentService.getHTNQTMissionContentByMId(mId);
			  } else {
				  missionContentList = missionContentService.getMissionContentByMId(mId);
			  }
			  System.out.println("任务书内容个数："+missionContentList.size());
			  plMission.setMission(mission);
			  plMission.setMcList(missionContentList);
			  plMissionList.add(plMission);	
		    }
		}
		System.out.println("任务书个数："+plMissionList.size());
		
		return plMissionList;
	}
	
	
	// 最初版本
	/*@RequestMapping("/toAuditPL/{prId}")  
	public String toAuditPL(@PathVariable("prId") String prId,Model model,HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		Project pj = projectService.getProjectById(prId);
		
		String roId = user.getRoId();
		String roleName = user.getRoleName();

		Map<String, Object> mapRoAs = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		mapRoAs.put("roId", roId);
		List<AuditRole> auditRoleList= auditRoleService.getArByRoAndAstep(mapRoAs);
		map.put("auditRoleList", auditRoleList);
		map.put("roleName", roleName);
		map.put("prId", prId);
		List<Mission> missionList = missionService.getToAuditMissionListByPrId(map);
		List<PLMission> plMissionList = new ArrayList<PLMission>();
		if(missionList != null && missionList.size()>0)
		{
		   for(int i=0; i<missionList.size(); i++){
			  PLMission plMission = new PLMission();
			  Mission mission = missionList.get(i);
			  String mId = mission.getmId();
			  List<MissionContent> missionContentList;
			  if (missionList.get(i).getMtName().equals("合同内其他任务书")) {
				  missionContentList = missionContentService.getHTNQTMissionContentByMId(mId);
			  } else {
				  missionContentList = missionContentService.getMissionContentByMId(mId);
			  }
			  System.out.println("任务书内容个数："+missionContentList.size());
			  plMission.setMission(mission);
			  plMission.setMcList(missionContentList);
			  plMissionList.add(plMission);	
		    }
		}
		System.out.println("任务书个数："+plMissionList.size());
		List<MissionType> missiontypeList= missionTypeService.getMissionType();
		List<PrIndexPC> prIndexPcList = projectService.getProjectDetailById(prId);
		model.addAttribute("plMissionList", plMissionList);		 
		model.addAttribute("mtList", missiontypeList);
		model.addAttribute("prIndexPcList", prIndexPcList);
		model.addAttribute("pj", pj);
		return "audit/auditPL";
	}*/
	
	
	@RequestMapping("toDownloadMission")
	public String toDownloadMission(HttpSession session, Model model) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		List<Project> projectList = new ArrayList<Project>();
		if (user.getRoleName().equals("工长")) {
			projectList = projectService.getProjectByGongzhang(user.getuId());
		}
		else if (user.getRoleName().equals("预算")) {
			projectList = projectService.getProjectByYusuan(user.getuId());
		}
		else if (user.getRoleName().equals("项目经理")) {
			projectList = projectService.getProjectByUId(user.getuId());
			System.out.println("项目经理对应的工程："+projectList.size());
		}
		else {
			projectList = projectService.getAllProjectName();
			System.out.println("管理员对应的工程："+projectList.size());
		}
		model.addAttribute("prList", projectList);
		List<MissionType> mtList = missionTypeService.getMissionType();
		model.addAttribute("mtList", mtList);
		
		List<CheckUnit> cuList = checkUnitService.getAllCheckUnit();
		model.addAttribute("cuList", cuList);
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		model.addAttribute("year", year);
		
		return "downloadMission";
	}
	
	@RequestMapping("downloadMission")
	public String downloadMission(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		String prId = request.getParameter("prId");
		String pcpId = request.getParameter("pcpId");
		String mtId = request.getParameter("mtId");
		String cuId = request.getParameter("cuId");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String isAudited = request.getParameter("isAudited");
		BigDecimal sum_rgf = new BigDecimal("0.00"); //合计
		// 审核状态，空为不选，0为当前操作人员审核的任务书
		// 1、当前操作人员已审核的任务书；2、全部审核通过的任务书；3、未能全部审核通过的任务书。
		// 工长的话，空为不选，0为未审核的任务书
		// 1、已审核的任务书；2、退修的任务书；
		// 1-全部审核通过，2-审核中，3-退修
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mtId", mtId);
		map.put("prId", prId);
		map.put("pcpId", pcpId);
		map.put("year", year);
		map.put("month", month);
		map.put("cuId", cuId);
		map.put("status", isAudited);
		List<MissionVo> mvo = missionService.preDownloadMission(map);
		System.out.println("数量：" + mvo.size());
		// 声明一个工作薄 
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet("任务书明细");
		// 生成一个样式--表头
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式--内容
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.WHITE.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell;
		String[] headers = { "任务书编号", "任务书类型", "工程名称", "工程类别", "栋号", "开始时间", "结束时间", 
				"结算单位", "序号", "分部", "工程部位", "工作项目", "具体部位", "工长", "提交时间", 
				"单位", "单价", "工程量", "工资金额", "已结累计工程量", "合同材料", "罚款", 
				"扣款材料", "其他", "最后审核时间", "状态", "备注", "项目经理意见", "预算意见", 
				"成控部经理意见", "审计意见", "分管领导意见", "资料是否返回"};
		for (int i = 0; i < headers.length; i++) {
			sheet.setColumnWidth(i, 16 * 256);
			cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		
		if (mvo != null && mvo.size() > 0) {
			for (int i = 1; i <= mvo.size(); i++) {
				i = i -1;
				// 写入各条记录，每条记录对应Excel中的一行
				row = sheet.createRow(i + 1);
				// 编号
				cell = row.createCell(0);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getMCode());
				// 任务书类型
				cell = row.createCell(1);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getMissionTypeName());
				// 工程名称
				cell = row.createCell(2);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getProjectName());
				// 工程类别
				cell = row.createCell(3);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getProjectClassName());
				// 栋号
				cell = row.createCell(4);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getDongHao());
				// 开始时间
				cell = row.createCell(5);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getBeginDate());
				// 结束时间
				cell = row.createCell(6);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getEndDate());
				// 结算单位
				cell = row.createCell(7);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getCuName());
				// 序号
				cell = row.createCell(8);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getXuHao());
				// 分部
				cell = row.createCell(9);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getFenBu());
				// 工程部位
				cell = row.createCell(10);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getGongChengBuWei());
				// 工作项目
				cell = row.createCell(11);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getGongZuoXiangMu());
				// 具体部位
				cell = row.createCell(12);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getJuTiBuWei());
				//System.out.println(mvo.get(i).getJuTiBuWei());
				// 工长
				cell = row.createCell(13);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getGongZhang());
				// 提交时间
				cell = row.createCell(14);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getTiJiaoShiJian());
				// 单位
				cell = row.createCell(15);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getDanWei());
				// 单价
				cell = row.createCell(16);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getDanJia());
				// 工程量
				cell = row.createCell(17);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getGongChengLiang());
				// 工资金额
				cell = row.createCell(18);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getGongZiJinE());
				//System.out.println(mvo.get(i).getGongZiJinE());
				// 已结累计工程量
				cell = row.createCell(19);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getYiJieLiang());
				// 合同材料
				cell = row.createCell(20);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getHeTongCaiLiao());
				// 罚款
				cell = row.createCell(21);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getFaKuan());
				// 扣款材料
				cell = row.createCell(22);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getKouKuanCaiLiao());
				// 其他
				cell = row.createCell(23);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getQiTa());
				// 最后审核时间
				cell = row.createCell(24);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getZuiHouShenHe());
				// 状态
				cell = row.createCell(25);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getZhuangTai());
				// 备注
				cell = row.createCell(26);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getRemark());
				// 项目经理意见
				cell = row.createCell(27);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getXiangMuJingLi());
				// 预算意见
				cell = row.createCell(28);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getYuSuan());
				// 成控部经理意见
				cell = row.createCell(29);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getChengkong());
				// 审计意见
				cell = row.createCell(30);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getShenJi());
				// 分管领导意见
				cell = row.createCell(31);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getFenGuanLingDao());
				// 资料是否返回
				cell = row.createCell(32);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				if (mvo.get(i).getMissionTypeName().equals("变更签证任务书")) {
					cell.setCellValue(mvo.get(i).getIsReturn());
				} else {
					cell.setCellValue("");
				}
				i = i + 1;
				// 合计
				/*cell = row.createCell(32);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getHeji());*/
			}
		} else {
			row = sheet.createRow(1);
			// 编号
			cell = row.createCell(0);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellStyle(style2);
			cell.setCellValue("合计");
			// 任务书类型
			cell = row.createCell(1);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellStyle(style2);
			cell.setCellValue(sum_rgf.toString());
		}
		String filename = "所有任务书明细.xls";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
		OutputStream ouputStream = response.getOutputStream();
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
		return null;
	}
	//进入查看计件任务书统计表页面
	@RequestMapping(value="/showJJMission")
	public String showJJMission(Model model,HttpSession session){
		List<Project> prList = null;
		User user = (User)session.getAttribute(CygsConst.USER_SESSION);
		String roleName = user.getRoleName();
		String uId = user.getuId();
		if(roleName.equals("项目经理")){
			prList = this.projectService.getProjectByUId(uId);
		}else if(roleName.equals("工长")){
			prList = this.projectService.getProjectByGongzhang(uId);
		}else if(roleName.equals("预算")){
			prList = this.projectService.getProjectByYusuan(uId);
		}else if(roleName.equals("审计")){
			prList = this.projectService.getProjectByShenJiId(uId);
		}else if(roleName.equals("成控部经理")){
			prList = this.projectService.getProjectByChengkonId(uId);
		}else{
			prList = this.projectService.getAllProjectName();
		}
		model.addAttribute("prList", prList);
		return "listJJMission";
	}
	//查询计件任务书统计表信息
	@RequestMapping(value="/getJJMissionList")
	@ResponseBody
	public Map<String,Object> getJJMissionList(@RequestBody Map<String,Object> map){
		String prId = map.get("prId").toString();
		String status = map.get("status").toString();
		List<PrIndexPC> pipList = null;
		List<JJMission> mission1 = null;//含工程名、分部、分项、单位、价格、总工程量、金额
		List<JJMission> mission2 = null;//含工程名、分部、分项、单位、价格、楼栋号下的工程量
		if(prId != null && prId != ""){
			pipList = this.projectService.getProjectDetailById(prId);
			if(status != null && status.equals("0")){
				//审核未通过
				mission1 = this.missionService.getJJMissionList1(prId, 1);
				mission2 = this.missionService.getJJMissionList2(prId, 1);
			}else{
				mission1 = this.missionService.getJJMissionList1(prId, 100);
				mission2 = this.missionService.getJJMissionList2(prId, 100);
			}
		}
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("pipList", pipList);
		result.put("mission1", mission1);
		result.put("mission2", mission2);
		return result;
		
	}
}
