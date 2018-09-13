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
	
	//�鿴�����顢�������鿴������
	@RequestMapping("/missionSelectList")  
	public String missionSelectList(Model model) {		
		return "audit/listSelectMission";
	}
	
	//�鿴���ύ�����
	@RequestMapping("/missionSubmitList")  
	public String missionSubmitList(Model model) {		
		return "audit/listSubmitMission";
	}
		
	//�鿴δ������������
	@RequestMapping("/missionUnAuditList")  
	public String missionUnAuditList(Model model) {
		Tools.checkStopAudit(model);
		return "audit/listUnAuditMission";
	}
	
	//�鿴�����������δ�������顢���������ing
	@RequestMapping("/missionAuditingList")  
	public String missionAuditingList(Model model) {
		Tools.checkStopAudit(model);
		return "audit/listAuditingMission";
	}
	
	//�鿴δ�������������
	@RequestMapping("/missionUnCompletedList")  
	public String missionUnCompletedList(Model model) {		
		return "audit/listUnCompletedMission";
	}
		
	//�鿴��ɾ��������
	@RequestMapping("/missionDeleteList")  
	public String missionDeleteList(Model model,HttpSession session) {
		List<MissionType> missiontypeList= missionTypeService.getMissionType();
		model.addAttribute("mtList", missiontypeList);
		return "audit/listDeleteMission";
	}
	
	
	//���ǩ֤����״̬
	@RequestMapping("/missionBGQZList")  
	public String missionBGQZList(Model model) {
		List<MissionType> missiontypeList= missionTypeService.getMissionType();
		model.addAttribute("mtList", missiontypeList);
		return "audit/listBGQZMission";
	}
	
	
	//�鿴�����ݲ�����������
	@RequestMapping("/missionStopAuditList")  
	public String missionStopAuditList(Model model,HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String uId = user.getuId();
		String roId = user.getRoId();
		int count = missionService.getStopAuditMissionCount(uId,roId);
		model.addAttribute("count", count);
		System.out.println("������"+count);
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

	// ���´���������������
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

	// �鿴�������б�
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

	// �鿴���ύ�����������--------����
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
			if (roleName.equals("����")) {
				map.put("roleName", roleName);
				map.put("userName", user.getUserName());
			}
		}
		return missionService.getSubmitMissionList(map);
	}

	// �鿴δ��/����������------
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

	// �����������δ���ѯ
	@RequestMapping("/auditingMissionSelect")
	public @ResponseBody DataGrid<Mission> ListAuditingMission1(@RequestParam Map<String, Object> map, HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String roId = user.getRoId();
		// String roleName = user.getRoleName();
		Map<String, Object> mapRoAs = new HashMap<String, Object>();
		mapRoAs.put("roId", roId);
		System.out.println("uid��" + user.getuId());
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

	// �鿴��ɾ��������
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

	// ���ǩ֤����״̬
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

	// ��ʾ����������б�
	@RequestMapping("/missionToAuditList")
	public String missionToAuditList(Model model, HttpSession session) {
		Tools.checkStopAudit(model);
		Tools.checkStopPlAudit(model, session);
		return "audit/listToAuditMission";
	}

	 //��ʾ����������б�
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
			System.out.println("size��"+missionList.size());
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
	
	// �������
	@RequestMapping("/toAuditByOne")
	public String toAuditByOne(String prId, Model model, HttpSession session) {
		// session.setAttribute("prId", prId);
		System.out.println("����bian==hao====��" + prId);
		Project pj = projectService.getProjectById(prId);
		List<MissionType> missiontypeList = missionTypeService.getMissionType();
		List<PrIndexPC> prIndexPcList = projectService.getProjectDetailById(prId);
		model.addAttribute("prId", prId);
		model.addAttribute("mtList", missiontypeList);
		model.addAttribute("prIndexPcList", prIndexPcList);
		model.addAttribute("pj", pj);
		return "audit/auditByOne";
	}
	
	// ��ʾ��������������б� ��֪prid------
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
		System.out.println("��������飺" + missionList.size());
		return missionList;
	}
	

	//��ʾ��������������б�
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
			  if (missionList.get(i).getMtName().equals("��ͬ������������")) {
				  missionContentList = missionContentService.getHTNQTMissionContentByMId(mId);
			  } else {
				  missionContentList = missionContentService.getMissionContentByMId(mId);
			  }
			  System.out.println("���������ݸ�����"+missionContentList.size());
			  plMission.setMission(mission);
			  plMission.setMcList(missionContentList);
			  plMissionList.add(plMission);	
		    }
		}
		System.out.println("�����������"+plMissionList.size());
		
		return plMissionList;
	}
	
	
	// ����汾
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
			  if (missionList.get(i).getMtName().equals("��ͬ������������")) {
				  missionContentList = missionContentService.getHTNQTMissionContentByMId(mId);
			  } else {
				  missionContentList = missionContentService.getMissionContentByMId(mId);
			  }
			  System.out.println("���������ݸ�����"+missionContentList.size());
			  plMission.setMission(mission);
			  plMission.setMcList(missionContentList);
			  plMissionList.add(plMission);	
		    }
		}
		System.out.println("�����������"+plMissionList.size());
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
		if (user.getRoleName().equals("����")) {
			projectList = projectService.getProjectByGongzhang(user.getuId());
		}
		else if (user.getRoleName().equals("Ԥ��")) {
			projectList = projectService.getProjectByYusuan(user.getuId());
		}
		else if (user.getRoleName().equals("��Ŀ����")) {
			projectList = projectService.getProjectByUId(user.getuId());
			System.out.println("��Ŀ�����Ӧ�Ĺ��̣�"+projectList.size());
		}
		else {
			projectList = projectService.getAllProjectName();
			System.out.println("����Ա��Ӧ�Ĺ��̣�"+projectList.size());
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
		BigDecimal sum_rgf = new BigDecimal("0.00"); //�ϼ�
		// ���״̬����Ϊ��ѡ��0Ϊ��ǰ������Ա��˵�������
		// 1����ǰ������Ա����˵������飻2��ȫ�����ͨ���������飻3��δ��ȫ�����ͨ���������顣
		// �����Ļ�����Ϊ��ѡ��0Ϊδ��˵�������
		// 1������˵������飻2�����޵������飻
		// 1-ȫ�����ͨ����2-����У�3-����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mtId", mtId);
		map.put("prId", prId);
		map.put("pcpId", pcpId);
		map.put("year", year);
		map.put("month", month);
		map.put("cuId", cuId);
		map.put("status", isAudited);
		List<MissionVo> mvo = missionService.preDownloadMission(map);
		System.out.println("������" + mvo.size());
		// ����һ�������� 
		HSSFWorkbook workbook = new HSSFWorkbook();
		// ����һ�����
		HSSFSheet sheet = workbook.createSheet("��������ϸ");
		// ����һ����ʽ--��ͷ
		HSSFCellStyle style = workbook.createCellStyle();
		// ������Щ��ʽ
		style.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// ����һ������
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// ������Ӧ�õ���ǰ����ʽ
		style.setFont(font);
		// ���ɲ�������һ����ʽ--����
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.WHITE.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// ������һ������
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// ������Ӧ�õ���ǰ����ʽ
		style2.setFont(font2);
		
		// ������������
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell;
		String[] headers = { "��������", "����������", "��������", "�������", "����", "��ʼʱ��", "����ʱ��", 
				"���㵥λ", "���", "�ֲ�", "���̲�λ", "������Ŀ", "���岿λ", "����", "�ύʱ��", 
				"��λ", "����", "������", "���ʽ��", "�ѽ��ۼƹ�����", "��ͬ����", "����", 
				"�ۿ����", "����", "������ʱ��", "״̬", "��ע", "��Ŀ�������", "Ԥ�����", 
				"�ɿز��������", "������", "�ֹ��쵼���", "�����Ƿ񷵻�"};
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
				// д�������¼��ÿ����¼��ӦExcel�е�һ��
				row = sheet.createRow(i + 1);
				// ���
				cell = row.createCell(0);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getMCode());
				// ����������
				cell = row.createCell(1);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getMissionTypeName());
				// ��������
				cell = row.createCell(2);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getProjectName());
				// �������
				cell = row.createCell(3);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getProjectClassName());
				// ����
				cell = row.createCell(4);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getDongHao());
				// ��ʼʱ��
				cell = row.createCell(5);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getBeginDate());
				// ����ʱ��
				cell = row.createCell(6);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getEndDate());
				// ���㵥λ
				cell = row.createCell(7);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getCuName());
				// ���
				cell = row.createCell(8);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getXuHao());
				// �ֲ�
				cell = row.createCell(9);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getFenBu());
				// ���̲�λ
				cell = row.createCell(10);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getGongChengBuWei());
				// ������Ŀ
				cell = row.createCell(11);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getGongZuoXiangMu());
				// ���岿λ
				cell = row.createCell(12);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getJuTiBuWei());
				//System.out.println(mvo.get(i).getJuTiBuWei());
				// ����
				cell = row.createCell(13);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getGongZhang());
				// �ύʱ��
				cell = row.createCell(14);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getTiJiaoShiJian());
				// ��λ
				cell = row.createCell(15);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getDanWei());
				// ����
				cell = row.createCell(16);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getDanJia());
				// ������
				cell = row.createCell(17);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getGongChengLiang());
				// ���ʽ��
				cell = row.createCell(18);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getGongZiJinE());
				//System.out.println(mvo.get(i).getGongZiJinE());
				// �ѽ��ۼƹ�����
				cell = row.createCell(19);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getYiJieLiang());
				// ��ͬ����
				cell = row.createCell(20);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getHeTongCaiLiao());
				// ����
				cell = row.createCell(21);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getFaKuan());
				// �ۿ����
				cell = row.createCell(22);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getKouKuanCaiLiao());
				// ����
				cell = row.createCell(23);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getQiTa());
				// ������ʱ��
				cell = row.createCell(24);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getZuiHouShenHe());
				// ״̬
				cell = row.createCell(25);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getZhuangTai());
				// ��ע
				cell = row.createCell(26);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getRemark());
				// ��Ŀ�������
				cell = row.createCell(27);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getXiangMuJingLi());
				// Ԥ�����
				cell = row.createCell(28);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getYuSuan());
				// �ɿز��������
				cell = row.createCell(29);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getChengkong());
				// ������
				cell = row.createCell(30);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getShenJi());
				// �ֹ��쵼���
				cell = row.createCell(31);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getFenGuanLingDao());
				// �����Ƿ񷵻�
				cell = row.createCell(32);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				if (mvo.get(i).getMissionTypeName().equals("���ǩ֤������")) {
					cell.setCellValue(mvo.get(i).getIsReturn());
				} else {
					cell.setCellValue("");
				}
				i = i + 1;
				// �ϼ�
				/*cell = row.createCell(32);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(mvo.get(i).getHeji());*/
			}
		} else {
			row = sheet.createRow(1);
			// ���
			cell = row.createCell(0);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellStyle(style2);
			cell.setCellValue("�ϼ�");
			// ����������
			cell = row.createCell(1);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellStyle(style2);
			cell.setCellValue(sum_rgf.toString());
		}
		String filename = "������������ϸ.xls";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
		OutputStream ouputStream = response.getOutputStream();
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
		return null;
	}
	//����鿴�Ƽ�������ͳ�Ʊ�ҳ��
	@RequestMapping(value="/showJJMission")
	public String showJJMission(Model model,HttpSession session){
		List<Project> prList = null;
		User user = (User)session.getAttribute(CygsConst.USER_SESSION);
		String roleName = user.getRoleName();
		String uId = user.getuId();
		if(roleName.equals("��Ŀ����")){
			prList = this.projectService.getProjectByUId(uId);
		}else if(roleName.equals("����")){
			prList = this.projectService.getProjectByGongzhang(uId);
		}else if(roleName.equals("Ԥ��")){
			prList = this.projectService.getProjectByYusuan(uId);
		}else if(roleName.equals("���")){
			prList = this.projectService.getProjectByShenJiId(uId);
		}else if(roleName.equals("�ɿز�����")){
			prList = this.projectService.getProjectByChengkonId(uId);
		}else{
			prList = this.projectService.getAllProjectName();
		}
		model.addAttribute("prList", prList);
		return "listJJMission";
	}
	//��ѯ�Ƽ�������ͳ�Ʊ���Ϣ
	@RequestMapping(value="/getJJMissionList")
	@ResponseBody
	public Map<String,Object> getJJMissionList(@RequestBody Map<String,Object> map){
		String prId = map.get("prId").toString();
		String status = map.get("status").toString();
		List<PrIndexPC> pipList = null;
		List<JJMission> mission1 = null;//�����������ֲ��������λ���۸��ܹ����������
		List<JJMission> mission2 = null;//�����������ֲ��������λ���۸�¥�����µĹ�����
		if(prId != null && prId != ""){
			pipList = this.projectService.getProjectDetailById(prId);
			if(status != null && status.equals("0")){
				//���δͨ��
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
