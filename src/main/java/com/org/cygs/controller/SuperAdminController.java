package com.org.cygs.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.cygs.pojo.AuditInfo;
import com.org.cygs.pojo.AuditInfoDelete;
import com.org.cygs.pojo.CheckUnit;
import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.MissionContent;
import com.org.cygs.pojo.MissionDelete;
import com.org.cygs.pojo.MissionDeleteContent;
import com.org.cygs.pojo.MissionType;
import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.Project;
import com.org.cygs.pojo.SetTime;
import com.org.cygs.pojo.SystemOperation;
import com.org.cygs.pojo.SystemParameter;
import com.org.cygs.pojo.User;
import com.org.cygs.pojo.ZAuditInfo;
import com.org.cygs.pojo.ZMission;
import com.org.cygs.pojo.ZMissionContent;
import com.org.cygs.pojo.ZPrIndexPc;
import com.org.cygs.pojo.ZProject;
import com.org.cygs.service.AuditInfoDeleteService;
import com.org.cygs.service.AuditInfoService;
import com.org.cygs.service.CheckUnitService;
import com.org.cygs.service.MissionContentService;
import com.org.cygs.service.MissionDeleteContentService;
import com.org.cygs.service.MissionDeleteService;
import com.org.cygs.service.MissionService;
import com.org.cygs.service.MissionTypeService;
import com.org.cygs.service.ProjectService;
import com.org.cygs.service.SetTimeService;
import com.org.cygs.service.StopAuditService;
import com.org.cygs.service.SystemOperationService;
import com.org.cygs.service.SystemParameterService;
import com.org.cygs.service.ZAuditInfoService;
import com.org.cygs.service.ZMissionContentService;
import com.org.cygs.service.ZMissionService;
import com.org.cygs.service.ZPrIndexPcService;
import com.org.cygs.service.ZProjectService;
import com.org.cygs.util.common.CygsConst;

@Controller
public class SuperAdminController {
	@Autowired
	private SystemParameterService  systemParameterService;
	@Autowired
	private SystemOperationService systemOperationService;
	@Autowired
	private CheckUnitService checkUnitService;
	@Autowired
	private MissionService missionService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private MissionTypeService missionTypeService;
	@Autowired
	private MissionContentService missionContentService;
	@Autowired
	private AuditInfoService auditInfoService;
	@Autowired
	private StopAuditService stopAuditService;
	@Autowired
	private MissionDeleteService missionDeleteService;
	@Autowired
	private MissionDeleteContentService missionDeleteContentService;
	@Autowired
	private AuditInfoDeleteService auditInfoDeleteService;
	@Autowired
	private SetTimeService setTimeService;
	@Autowired
	private ZAuditInfoService zAuditInfoService;
	@Autowired
	private ZMissionService zMissionService;
	@Autowired
	private ZProjectService zProjectService;
	@Autowired
	private ZPrIndexPcService zPrIndexPcService;
	@Autowired
	private ZMissionContentService zMissionContentService;
	
	
	public void toStop(Model model, String paraName) {
		SystemParameter stopaudit = systemParameterService.selectByParaName(paraName);
		model.addAttribute("stopAudit", stopaudit);
	}
	
	// 关停操作
	public String stop(HttpServletRequest request, RedirectAttributes attr, Model model, String paraName, String addr) throws ParseException {
		SystemParameter stopaudit = systemParameterService.selectByParaName(paraName);
		
		String paraValue = request.getParameter("value");
		// 如果是已关停的状态
		if (Integer.parseInt(paraValue) == 0) {
			stopaudit.setParaValue(1);
			systemParameterService.updateSystemParam(stopaudit);
		}
		else {
			Date tdate = new Date();
			Date ldate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dates = request.getParameter("opendate");
			System.out.println(dates);
			String lhour = request.getParameter("openhour");
			String lminute = request.getParameter("openminute");
			if (lhour.length() == 1) {
				lhour = "0" + lhour;
			}
			if (lminute.length() == 1) {
				lminute = "0" + lminute;
			}
			dates = dates + " " + lhour + ":" + lminute + ":" + "00";
			ldate = sdf.parse(dates);	// 合成页面上输入的时间
			if (ldate.before(tdate)) {	// 失效时间应该在此时之后
				attr.addFlashAttribute("error", "失效时间应该在此时之后!");
				return "redirect:" + addr;
			}
			stopaudit.setParaValue(0);
			stopaudit.setParaLosetime(new java.sql.Timestamp(ldate.getTime()));
			systemParameterService.updateSystemParam(stopaudit);
		}
		model.addAttribute("stopAudit", stopaudit);
		return "redirect:" + addr;
	}
	
	
	// 关停审核功能
	@RequestMapping("toStopAudit")
	public String toStopAudit(Model model) {
		toStop(model, "stopaudit");
		
		return "admin/stopAudit";
	}
	@RequestMapping("stopAudit")
	public String stopAudit(HttpServletRequest request, RedirectAttributes attr, Model model) throws ParseException {
		
		return stop(request, attr, model, "stopaudit", "toStopAudit");
	}
	
	
	// 关停批量审核功能
	@RequestMapping("toStopBatchAudit")
	public String toStopBatchAudit(Model model) {
		toStop(model, "stopbatchaudits");
		
		return "admin/stopBatchAudit";
	}
	@RequestMapping("stopBatchAudit")
	public String stopBatchAudit(HttpServletRequest request, RedirectAttributes attr, Model model) throws ParseException {
		
		return stop(request, attr, model, "stopbatchaudits", "toStopBatchAudit");
	}
	
	// 关停新建任务书功能
	@RequestMapping("toStopCreateMission")
	public String toStopCreateMission(Model model) {
		toStop(model, "stopadd");
		
		return "admin/stopCreateMission";
	}
	@RequestMapping("stopCreateMission")
	public String stopCreateMission(HttpServletRequest request, RedirectAttributes attr, Model model) throws ParseException {
		return stop(request, attr, model, "stopadd", "toStopCreateMission");
	}
	
	
	// 关停查看预算权限
	@RequestMapping("toStopViewBudget")
	public String toStopViewBudget(Model model) {
		SystemParameter stopbudgetview = systemParameterService.selectByParaName("stopbudgetview");
		model.addAttribute("stopbudgetview", stopbudgetview);
		
		return "admin/stopViewBudget";
	}
	@RequestMapping("stopViewBudget")
	public String stopViewBudget(HttpServletRequest request, RedirectAttributes attr, Model model) throws ParseException {
		SystemParameter stopaudit = systemParameterService.selectByParaName("stopbudgetview");
		
		String paraValue = request.getParameter("value");
		if (Integer.parseInt(paraValue) == 0) {
			stopaudit.setParaValue(1);
			systemParameterService.updateSystemParam(stopaudit);
		}
		else {
			stopaudit.setParaValue(0);
			systemParameterService.updateSystemParam(stopaudit);
		}
		return "redirect:toStopViewBudget";
	}
	
	// 删除回退任务书页面
	@RequestMapping("toDelBackMission")
	public String toDelBackMission(Model model) {
		
		return "admin/delBackMission";
	}
	
	// 删除任务书---同时删除内容表、审核表, 不需要存入delete表中
	@RequestMapping(value="delMissionComplete/{mId}")
	public String delMission(@PathVariable(value = "mId") String mId, @PathVariable(value = "prName") String prName, HttpSession session, Model model) throws ParseException {
		Mission mission = missionService.getMissionInfoBymId(mId);
		if (mission != null) {
			// 将任务书加入delete表
			MissionDelete mdDelete = new MissionDelete();
			mdDelete.setMdId("002454311001");
			mdDelete.setMission(mission);
			missionDeleteService.insertMissionDelete(mdDelete);
			
			// 将删除操作存入操作记录表
			SystemOperation so = new SystemOperation();
			so.setSoId("0002124441111001");
			so.setmCode(mission.getmCode());
			so.setPrName(prName);
			so.setUserName(mission.getSupervisor());
			// 设置remark为审核表的comment
			List<AuditInfo> commentList = auditInfoService.getAuditInfoByMId(mId);
			String comment = "";
			if (commentList != null && commentList.size() > 0) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for (int i = 0; i < commentList.size(); i++) {
					comment = "[" + i + "]" + "[" + sdf.parse(commentList.get(i).getAuditTime().toString()) + "]" 
								+ commentList.get(i).getAuditComment() + ";";
				}
			}
			so.setOperationTime(new java.sql.Timestamp(new Date().getTime()));
			so.setRemark1(comment);
			so.setOperationType("删除");
			systemOperationService.insertSysOperation(so);
			
			// 删除暂不处理任务书
			stopAuditService.deleteStopAuditByMid(mId);
			// 删除
			auditInfoService.delAuditInfoByMId(mId);
			missionContentService.delMissionContentBymId(mId);
			missionService.delMissionById(mId);
			
			// 删除相关附件
			File filedir = new File("D:/cyjz_file/test/file/" + mId);
			File[] files = filedir.listFiles();
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			if (filedir.length() > 0) {
				if (filedir.listFiles().length == 0)	// 没有附件则删除文件夹
					filedir.delete();
			}
		}
		model.addAttribute("delcomplete", 1);
		return "admin/delBackMission";
	}

	// 回退任务书---修改任务书表的状态和remark,插入审核记录
	@RequestMapping(value="backMission/{mId}")
	public String backMission(@PathVariable(value = "mId") String mId, RedirectAttributes attr, HttpSession session, Model model) throws ParseException {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		Mission mission = missionService.getMissionInfoBymId(mId);
		
		if (mission != null) {
			if (mission.getStatus() != -1) {
				mission.setStatus(-1);
				mission.setRemark(user.getUserName() + " " + "【退修】");
				missionService.updateMissionStatus(mission);
				// 插入审核记录
				AuditInfo ai = new AuditInfo();
				ai.setaId("00004441111");
				ai.setmId(mission.getmId());
				ai.setAuditResult(0);
				ai.setAuditComment(user.getUserName() + " " + "【退修】");
				ai.setAuditTime(new java.sql.Timestamp(new Date().getTime()));
				ai.setuId(user.getuId());
				auditInfoService.insertAuditInfo(ai);
			}
			else {
				model.addAttribute("backMsg", "该任务书已退修，无需再回退!");
			}
			
		}
		if (user.getRoleName().equals("admin"))
			return "admin/delBackMission1";
		else
			return "audit/listSubmitMission";
	}
	
	@RequestMapping("/tolistSetTime")  
	public String tolistSetTime(Model model) {		
		return "admin/listSetTime";
	}
	// 获取统计月度时间范围
	@RequestMapping("listSetTime")
	public @ResponseBody Page<SetTime> listSetTime(@RequestBody Map<String, Object> map, HttpServletRequest request, Model model) {
		Page<SetTime> setimeList = setTimeService.getSetTime(map);
		
		return setimeList;
	}
	
	@RequestMapping(value="/listSetTime1")
	public @ResponseBody Map<String, Object> listSetTime1(@RequestParam Map<String, Object> map){
		
		return setTimeService.getSetTimeList(map);
	}	
	
	// 添加统计月度时间范围
	@RequestMapping("addSetTime")
	public String addSetTime() {
		SetTime st = setTimeService.getMaxSetTimeYearMonth();
		if (st != null)  {
			SetTime newst = new SetTime();
			
			Calendar c=Calendar.getInstance();
	       	c.setTime(st.getsEndTime());
	    	c.add(Calendar.DATE, 1);	// 开始时间为：结束日期+1天
	    	c.set(Calendar.HOUR_OF_DAY, 0);
	    	c.set(Calendar.MINUTE, 0);
	    	c.set(Calendar.SECOND, 0);
			Date sbegintime=c.getTime();
			newst.setsYear(c.get(Calendar.YEAR));
			newst.setsMonth(c.get(Calendar.MONTH) + 1);
			
			c.add(Calendar.MONTH, 1);	// 结束时间为1个月之后
			c.set(Calendar.DATE, 5);	// 日期为5号
	    	c.set(Calendar.HOUR_OF_DAY, 23);
	    	c.set(Calendar.MINUTE, 59);
	    	c.set(Calendar.SECOND, 59);
	    	Date sendtime=c.getTime();
	    	newst.setSetId("100004242200");
	    	newst.setsBeginTime(new java.sql.Timestamp(sbegintime.getTime()));
	    	newst.setsEndTime(new java.sql.Timestamp(sendtime.getTime()));
	    	newst.setRemark("");
	    	newst.setStatus(1);
	    	
	    	setTimeService.insertSetTime(newst);
		}
		else {
			SetTime ast=new SetTime();
            Calendar c=Calendar.getInstance();
            Calendar d=Calendar.getInstance();
        	c.set(2009,5,6,0,0,0);	//2009-6-6
        	d.set(2009,6,5,23,59,59);	//2009-7-5
        	Date sbegintime=c.getTime();
        	Date sendtime=d.getTime();
        	ast.setSetId("100004242200");
        	ast.setsYear(d.get(Calendar.YEAR));	//2009
        	ast.setsMonth(d.get(Calendar.MONTH));	//6
        	ast.setsBeginTime(new java.sql.Timestamp(sbegintime.getTime()));
        	ast.setsEndTime(new java.sql.Timestamp(sendtime.getTime()));
        	ast.setRemark("");
        	ast.setStatus(1);
		}
		return "admin/listSetTime";
	}
	
	// 修改统计月度时间范围
	@RequestMapping("toEditSetTime/{setId}")
	public String toEditSetTime(@PathVariable(value="setId")String setId, Model model) throws JsonProcessingException, IOException, ParseException {
		SetTime stSetTime = setTimeService.getSetTimeByPrimaryKey(setId);
		model.addAttribute("setTime", stSetTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.addAttribute("begenTime", sdf.format(stSetTime.getsBeginTime()));
		model.addAttribute("endTime", sdf.format(stSetTime.getsEndTime()));
		
		return "admin/editSetTime";
	}
	
	@RequestMapping("editSetTime")
	public String editSetTime(HttpServletRequest request, Model model) throws JsonProcessingException, IOException, ParseException {
		String setId = request.getParameter("setId");
		String enddate = request.getParameter("enddate") + " 23:59:59";
		String remark = request.getParameter("remark");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		SetTime st = new SetTime();	// 若用SetTime st = new SetTime(), int字段会被设置为0, 改为Integer
		st.setSetId(setId);
		st.setsEndTime(new java.sql.Timestamp(sdf.parse(enddate).getTime()));
		st.setRemark(remark);
		setTimeService.updateSetTimeByPrimaryKey(st);
		
		return "admin/listSetTime";
	}
	
	// 已结算工程任务书
	@RequestMapping("listCompletedMission")
	public String listCompletedMission(HttpServletRequest request, Model model) throws JsonProcessingException, IOException, ParseException {
		List<MissionType> mt = missionTypeService.getMissionType();
		List<ZProject> pr = zProjectService.selectAllZProject();
		List<CheckUnit> cu = checkUnitService.getAllCheckUnit();
		model.addAttribute("mtList", mt);
		model.addAttribute("prList", pr);
		model.addAttribute("cuList", cu);
		
		return "admin/listCompletedMission";
	}
	// 获取已结算工程的任务书
	@RequestMapping("listZMission")
	public @ResponseBody Page<ZMission> listZMission(@RequestBody Map<String, Object> map, HttpServletRequest request, Model model) {
		Page<ZMission> zmission = zMissionService.getZMissionList(map);
		
		return zmission;
	}
	
	@RequestMapping("/listZMission1")
	public @ResponseBody Map<String, Object> listZMission1(@RequestParam Map<String, Object> map){
		return zMissionService.getZMissionList1(map);
	}
	
	// 查询工程对应的栋号
	@RequestMapping("queryUnitNumber/{zPrId}")
	public @ResponseBody List<ZPrIndexPc> queryUnitNumber(@PathVariable(value="zPrId") String zPrId, HttpServletRequest request, Model model) throws JsonProcessingException, IOException, ParseException {
		List<ZPrIndexPc> pc = zPrIndexPcService.selectUnitNumberByZprId(zPrId);
		
		return pc;
	}
	
	
	@RequestMapping("viewZMissionInfo/{zMId}")
	public String viewZMissionInfo(@PathVariable(value="zMId")String zMId, Model model) {
		ZMission mission = zMissionService.getZMissionByMId(zMId);
		List<ZMissionContent> missionContentList;
		if (mission.getMtId().equals("合同内其他任务书")) {
			missionContentList = zMissionContentService.selectZHTNQTMissionContent(zMId);
		} else {
			missionContentList = zMissionContentService.selectZMissionContent(zMId);
		}
		List<ZAuditInfo> auditInfoList= zAuditInfoService.getZAuditInfoByMId(zMId);
		System.out.println(missionContentList.size());
		ObjectMapper mapper = new ObjectMapper();
		try {
			String a = mapper.writeValueAsString(missionContentList);
			String b = mapper.writeValueAsString(mission);
			System.out.println(a);
			System.out.println(b);			
			} catch (JsonProcessingException e) {	
			e.printStackTrace();
			}
		model.addAttribute("missionContentList", missionContentList);
		model.addAttribute("mission", mission);
		model.addAttribute("auditInfoList", auditInfoList);
		int mt_code = mission.getMtCode();
		// 有无附件
		File file = new File("D:/cyjz_file/test/file", mission.getzMId());
		if (file.exists()) {
			if (file.isDirectory()) {
				if (file.listFiles().length != 0)
					model.addAttribute("file", "有附件");
				else
					model.addAttribute("file", "无附件");
			}
		} else
			model.addAttribute("file", "无附件");
		if(mt_code == 1){
			  return "admin/viewJJzMissionInfo";
			}
		if(mt_code == 3){
			  return "admin/viewJSzMissionInfo";
			}
		if(mt_code == 4){
			  return "admin/viewBGQZzMissionInfo";
			}
		if(mt_code == 5){
			  return "admin/viewHTWBCJzMissionInfo";
			}
		else{
			  return "admin/viewHTNQTzMissionInfo";
			}
	}
	
	// 获取任务书操作记录
	@RequestMapping("/tolistOperationRecord")  
	public String tolistSysOperation(Model model) {
		List<SystemOperation> prList = systemOperationService.getSysPrList();
		model.addAttribute("prList", prList);
		return "admin/listMissionOperationRecord";
	}
	
	// 获取统计月度时间范围
	@RequestMapping("listOperationRecord")
	public @ResponseBody Page<SystemOperation> listSysOperation(@RequestBody Map<String, Object> map) {
		Page<SystemOperation> sysList = systemOperationService.getSystemOperationList(map);
		
		return sysList;
	}
	
	@RequestMapping(value="/listOperationRecord1")
	public @ResponseBody Map<String, Object> listOperationRecord1(@RequestParam Map<String, Object> map){
		
		return systemOperationService.getSystemOperationList1(map);
	}	
	
	// 土建项目完成结算
	@RequestMapping("preCompleteProject")
	public String listUncompleteProject(Model model) {
		List<Project> prList = projectService.getAllProjectName();
		model.addAttribute("prList", prList);
		return "admin/preCompleteProject";
	}
	
	@RequestMapping("completeProject/{prId}")
	public String completeProject(@PathVariable("prId") String prId, Model model) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("prId", prId);
		systemOperationService.backallmission(map);;
		
		return "admin/preCompleteProject";
	}
	
	
	// 备份数据库
	@RequestMapping("tobackupDatabase")
	public String tobackupDatebase() {
		
		return "admin/backupDatabase";
	}
	@RequestMapping("backupDatabase")
	public String backupDatebase() {
		Date time = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒");
		String BackupDbName = sdf.format(time);
		String path = "D:\\BACK\\"+ "cyjz_new" + BackupDbName +".bak";
		System.out.println("备份数据库");
		Map<String, Object> map=new HashMap<String, Object>();
		System.out.println(path);
		map.put("path", path);
		systemOperationService.backupDatabase(map);
		
		return "redirect:tobackupDatabase";
	}
	
}
