package com.org.cygs.controller;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;

import com.org.cygs.pojo.AuditInfo;
import com.org.cygs.pojo.AuditRole;
import com.org.cygs.pojo.CheckUnit;
import com.org.cygs.pojo.Lwfb;
import com.org.cygs.pojo.Lwfbjs;
import com.org.cygs.pojo.Lwgcldb;
import com.org.cygs.pojo.Lwgzzf;
import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.MissionContent;
import com.org.cygs.pojo.MissionVo;
import com.org.cygs.pojo.Operation;
import com.org.cygs.pojo.Part;
import com.org.cygs.pojo.PartPosition;
import com.org.cygs.pojo.PieceworkServiceTotal;
import com.org.cygs.pojo.PrIndexPC;
import com.org.cygs.pojo.Project;
import com.org.cygs.pojo.SetTime;
import com.org.cygs.pojo.Unit;
import com.org.cygs.pojo.User;
import com.org.cygs.service.AuditInfoService;
import com.org.cygs.service.AuditRoleService;
import com.org.cygs.service.CheckUnitService;
import com.org.cygs.service.DepartmentService;
import com.org.cygs.service.LwfbService;
import com.org.cygs.service.MissionContentService;
import com.org.cygs.service.MissionService;
import com.org.cygs.service.OperationService;
import com.org.cygs.service.PartPositionService;
import com.org.cygs.service.PartService;
import com.org.cygs.service.ProjectService;
import com.org.cygs.service.SetTimeService;
import com.org.cygs.service.YUnitService;
import com.org.cygs.util.common.CygsConst;
import com.org.cygs.util.common.GetCurrentDate;



@Controller
@RequestMapping(value="/statistic")
public class StatisticController {

	@Autowired
	private CheckUnitService checkUnitService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private SetTimeService setTimeService;
	
	@Autowired
	private LwfbService lwfbService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private PartService partService;
	
	@Autowired
	private YUnitService unitService;
	
	@Autowired
	private OperationService operationService;
	
	@Autowired
	private PartPositionService partPositionService;
	
	@Autowired
	private AuditInfoService auditInforService;
	
	@Autowired
	private AuditRoleService auditRoleService;
	
	@Autowired
	private MissionService missionService;
	
	@Autowired
	private MissionContentService missionContentService;
	
	//进入班组月度报表页面
	@RequestMapping(value="/showLWFB")
	public ModelAndView showLWFB(HttpSession session){
		ModelAndView mv = new ModelAndView();
		List<CheckUnit> cuList = null;
		//List<Department> dpList = this.departmentService.selectDpNameandCode();
		User user = (User)session.getAttribute(CygsConst.USER_SESSION);
		String roleName = user.getRoleName();
		if(roleName.equals("西诺亚劳务")){
			cuList = this.checkUnitService.selectCheckUnitListByName(roleName);
		}else{
			cuList = this.checkUnitService.getAllCheckUnit();
		}
		mv.addObject("cuList", cuList);
		//mv.addObject("dpList", dpList);
		mv.setViewName("chooseLWFB");
		return mv;
	}
	//获取劳务分包的详细信息，并展示
	@RequestMapping(value="/getLWFB")
	public ModelAndView getLWFB(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		//String dpCode = (String)request.getParameter("dpCode");
		String cuId = (String)request.getParameter("cuId");
		String year = (String)request.getParameter("year");
		String month = (String)request.getParameter("month");
		String status = (String)request.getParameter("status");
		// Department dp = this.departmentService.selectDpByCode(dpCode);
		if(cuId.equals("")){
			cuId = null;
		}
		String bdate = "";
		String edate = "";
		String begindate = "";
		String enddate = "";
		int gsyear=Integer.parseInt(year);
		int gsmonth=Integer.parseInt(month);
        //算出报表日期
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String reportdate = sdf.format(date);
		List<SetTime> stList = this.setTimeService.getSetTimeByStatusSYearSMonth(1, gsyear, gsmonth);
		if(stList!=null && stList.size()>0){
			begindate = sdf.format(stList.get(0).getsBeginTime());
			enddate = sdf.format(stList.get(0).getsEndTime());
		}
		int m,y;
		m = Integer.parseInt(month);
		y = Integer.parseInt(year);
		if(m == 1)
		{
			y = y - 1;
			m = 12;
		}else
		{
			m = m - 1;
		}

		bdate = String.valueOf(y)+"-"+String.valueOf(m)+"-26";
		edate = "";
		if(m==12)
		{
			edate=String.valueOf(y+1)+"-1-25";			
		}
		else
			edate=String.valueOf(y)+"-"+String.valueOf(m+1)+"-25";
	
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println("begindate:" + begindate);
		System.out.println("enddate:" + enddate);
		map.put("begindate", begindate);
		map.put("enddate", enddate);
		// map.put("dpName", dp.getDpName());
		map.put("cuId", cuId);
		map.put("status", status);
		List<Lwfb> lwfbList = this.lwfbService.getLWFB(map);
		int prNum = lwfbList.size();//注：prNum为工程个数
		String prNum2 = new Integer(prNum).toString();
		System.out.println("lwfb size:"+lwfbList.size());
		mv.addObject("lwfbList", lwfbList);
		mv.addObject("reportdate", reportdate);
		mv.addObject("bdate", bdate);
		mv.addObject("edate", edate);
		mv.addObject("year", year);
		mv.addObject("month", month);
		// mv.addObject("dpName", dp.getDpName());
		mv.addObject("prNum", String.valueOf(prNum2));
		mv.setViewName("listLwfb");
		return mv;
	}
	
	//进入劳务工资支付情况明细前的数据提取
	@RequestMapping(value="/showLWGZZF")
	public String showLWGZZF(Model model,HttpSession session){
		List<Project> prList = null;
		List<CheckUnit> cuList = null;
		//List<Department> dpList = this.departmentService.selectDpNameandCode();
		User user = (User)session.getAttribute(CygsConst.USER_SESSION);
		String roleName = user.getRoleName();
		String uId = user.getuId();
		String loginName = user.getUserLoginName();
		if(roleName.equals("西诺亚劳务")){
			cuList = this.checkUnitService.selectCheckUnitListByName("西诺亚劳务");
		}else{
			cuList = this.checkUnitService.getAllCheckUnit();
		}
		if(roleName.equals("项目经理")){
			prList = this.projectService.getProjectByUId(uId);
		}else if(roleName.equals("工长")){
			prList = this.projectService.getProjectListByUserLoginName(loginName);
		}else{
			prList = this.projectService.getAllProjectName();
		}
		model.addAttribute("prList", prList);
		model.addAttribute("cuList", cuList);
		//model.addAttribute("dpList", dpList);
		model.addAttribute("roleName", roleName);
		return "chooseLWGZZF";
	}
	
	//查询劳务工资支付情况明细
	@RequestMapping(value="/getLWGZZF")
	public ModelAndView getLWGZZF(HttpServletRequest request){
		System.out.println("哈哈，我来到了这里！");
		ModelAndView mv = new ModelAndView();
		//String dpCode = (String)request.getParameter("dpCode");
		String prId = (String)request.getParameter("prId");
		String cuId = (String)request.getParameter("cuId");
		String status = (String)request.getParameter("status");
		String year = (String)request.getParameter("year");
		String month = (String)request.getParameter("month");
		/*String dpName = null;
		Department department = this.departmentService.selectDpByCode(dpCode);
		if(department != null){
			dpName = department.getDpName();
		}*/
		if(cuId.equals("")){
			cuId = null;
		}
		if(prId.equals("")){
			prId = null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		//报表日期
		String reportdate = sdf.format(date);
		
		String sqbegindate="";
		String sqenddate="";
		int gsyear=Integer.parseInt(year);
		int gsmonth=Integer.parseInt(month);
		int smonth,syear;
		if(gsmonth==1)
		{	
			smonth=12;
			syear=gsyear-1;
		}else{
			smonth=gsmonth-1;
			syear=gsyear;
		}
		List<SetTime> stList = this.setTimeService.getSetTimeByStatusSYearSMonth(1, syear, smonth);
		if(stList!=null && stList.size()>0){
			SetTime st = stList.get(0);
			sqbegindate = sdf.format(st.getsBeginTime());
			sqenddate = sdf.format(st.getsEndTime());
		}
		String bdate = "";
		String edate = "";
		String begindate=""; //构建开始日期
		String enddate=""; //构建结束日期
		List<SetTime> stList2 = this.setTimeService.getSetTimeByStatusSYearSMonth(1, gsyear, gsmonth);
		if(stList2 != null && stList2.size()>0){
			SetTime st = stList2.get(0);
			begindate = sdf.format(st.getsBeginTime());
			enddate = sdf.format(st.getsEndTime());
		}
		int m,y;
		m = Integer.parseInt(month);
		y = Integer.parseInt(year);
		if(m == 1)
		{
			y = y - 1;
			m = 12;
		}else
		{
			m = m - 1;
		}

		bdate = String.valueOf(y)+"-"+String.valueOf(m)+"-26";
		edate = "";
		if(m==12)
		{
			edate=String.valueOf(y+1)+"-1-25";			
		}
		else
			edate=String.valueOf(y)+"-"+String.valueOf(m+1)+"-25";

		Map<String,Object> map = new HashMap<String,Object>();
		//map.put("dpName", dpName);
		map.put("begindate", begindate);
		map.put("enddate", enddate);
		map.put("sqbegindate", sqbegindate);
		map.put("sqenddate", sqenddate);
		map.put("cuId", cuId);
		map.put("prId", prId);
		map.put("status", status);
		BigDecimal sumBqjs = new BigDecimal("0.00");
		BigDecimal sumSqjs = new BigDecimal("0.00");		
		BigDecimal sumFk = new BigDecimal("0.00");	
		BigDecimal sumCl = new BigDecimal("0.00");	
		BigDecimal sumQt = new BigDecimal("0.00");	
		BigDecimal sumLjjs = new BigDecimal("0.00");
		Lwgzzf lwgzzfSum = new Lwgzzf();
		List<Lwgzzf> lwgzzfList = this.lwfbService.getLwgzzf(map);
		System.out.println("lwgzzfList size:"+lwgzzfList.size());
		if(lwgzzfList != null && lwgzzfList.size()>0){
			for(Lwgzzf lf:lwgzzfList){
				sumBqjs=sumBqjs.add(lf.getBqjs());
				sumSqjs=sumSqjs.add(lf.getSqjs());
				sumFk=sumFk.add(lf.getFk());
				sumCl=sumCl.add(lf.getCl());
				sumQt=sumQt.add(lf.getQt());
				sumLjjs=sumLjjs.add(lf.getLjjs());	
			}
			lwgzzfSum.setBqjs(sumBqjs);
			lwgzzfSum.setSqjs(sumSqjs);
			lwgzzfSum.setFk(sumFk);
			lwgzzfSum.setCl(sumCl);
			lwgzzfSum.setQt(sumQt);
			lwgzzfSum.setLjjs(sumLjjs);
		}else{
			lwgzzfSum = null;
			lwgzzfList = null;
		}
		mv.addObject("reportdate", reportdate);
		mv.addObject("bdate", bdate);
		mv.addObject("edate", edate);
		mv.addObject("lwgzzfList", lwgzzfList);
		mv.addObject("lwgzzfSum", lwgzzfSum);
		mv.addObject("year", year);
		mv.addObject("month", month);
		//mv.addObject("dpName", dpName);
		mv.setViewName("listLWGZZF");
		return mv;
	}

	//进入劳务工资支付汇总页面前的数据提取，包含部门和工程名称
	@RequestMapping(value="/showLWGZZFHZ")
	public ModelAndView showLWGZHZ(HttpSession session){
		ModelAndView mv = new ModelAndView();
		List<Project> prList = null;
		//List<Department> dpList = null;
		User user = (User)session.getAttribute(CygsConst.USER_SESSION);
		String roleName = user.getRoleName();
		String uId = user.getuId();
		if(roleName.equals("项目经理")){
			prList = this.projectService.getProjectByUId(uId);
		}else{
			prList = this.projectService.getAllProjectName();
		}
		//dpList = this.departmentService.selectDpNameandCode();
		mv.addObject("prList", prList);
		//mv.addObject("dpList", dpList);
		mv.addObject("roleName", roleName);
		mv.setViewName("chooseLWGZZFHZ");
		return mv;
	}
	
	//查询劳务工资支付汇总信息
	@RequestMapping(value="/getLWGZZFHZ")
	public ModelAndView getLWGZZFHZ(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		String prId = (String)request.getParameter("prId");
		//String dpCode = (String)request.getParameter("dpCode");
		String year = (String)request.getParameter("year");
		String month = (String)request.getParameter("month");
		String status = (String)request.getParameter("status");
		/*String dpName = null;
		Department department = this.departmentService.selectDpByCode(dpCode);
		if(department != null){
			dpName = department.getDpName();
		}*/
		if(prId.equals("")){
			prId = null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String sqbegindate="";
		String sqenddate="";
		
		int gsyear=Integer.parseInt(year);
		int gsmonth=Integer.parseInt(month);
		int smonth,syear;
		if(gsmonth==1)
		{	
			smonth=12;
			syear=gsyear-1;
		}else{
			smonth=gsmonth-1;
			syear=gsyear;
		}
		List<SetTime> stList = this.setTimeService.getSetTimeByStatusSYearSMonth(1, syear, smonth);
		if(stList != null && stList.size()>0){
			
			sqbegindate = sdf.format(stList.get(0).getsBeginTime());
			sqenddate = sdf.format(stList.get(0).getsEndTime());
		}
		String bdate = "";
		String edate = "";
		String begindate=""; //构建开始日期
		String enddate=""; //构建结束日期
		List<SetTime> stList2 = this.setTimeService.getSetTimeByStatusSYearSMonth(1, gsyear, gsmonth);
		if(stList2 != null && stList2.size()>0){
			begindate = sdf.format(stList2.get(0).getsBeginTime());
			enddate = sdf.format(stList2.get(0).getsEndTime());
		}
//		updat by yzy 100401   获得bdate和edate用于界面显示,26-下月25之间
		int m,y;
		m = Integer.parseInt(month);
		y = Integer.parseInt(year);
		if(m == 1)
		{
			y = y - 1;
			m = 12;
		}else
		{
			m = m - 1;
		}

		bdate = String.valueOf(y)+"-"+String.valueOf(m)+"-26";
		edate = "";
		if(m==12)
		{
			edate=String.valueOf(y+1)+"-1-25";			
		}
		else{
			edate=String.valueOf(y)+"-"+String.valueOf(m+1)+"-25";
		}
		Date date = new Date();
		//报表日期
		String reportdate = sdf.format(date);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("begindate", begindate);
		map.put("enddate", enddate);
		//map.put("dpName", dpName);
		map.put("sqbegindate", sqbegindate);
		map.put("sqenddate", sqenddate);
		map.put("prId", prId);
		map.put("status", status);
		List<Lwgzzf> lwgzzfList = this.lwfbService.getLwgzzfhz(map);
		Lwgzzf lwgzzfSum = new Lwgzzf();
		if(lwgzzfList != null && lwgzzfList.size()>0){
			BigDecimal sumBqjs = new BigDecimal("0.00");
			BigDecimal sumSqjs = new BigDecimal("0.00");		
			BigDecimal sumFk = new BigDecimal("0.00");	
			BigDecimal sumCl = new BigDecimal("0.00");	
			BigDecimal sumQt = new BigDecimal("0.00");	
			BigDecimal sumLjjs = new BigDecimal("0.00");
			for(Lwgzzf lf:lwgzzfList){
				sumBqjs=sumBqjs.add(lf.getBqjs());
				sumSqjs=sumSqjs.add(lf.getSqjs());
				sumFk=sumFk.add(lf.getFk());
				sumCl=sumCl.add(lf.getCl());
				sumQt=sumQt.add(lf.getQt());
				sumLjjs=sumLjjs.add(lf.getLjjs());
			}
			lwgzzfSum.setBqjs(sumBqjs);
			lwgzzfSum.setSqjs(sumSqjs);
			lwgzzfSum.setFk(sumFk);
			lwgzzfSum.setCl(sumCl);
			lwgzzfSum.setQt(sumQt);
			lwgzzfSum.setLjjs(sumLjjs);
		}else{
			lwgzzfSum = null;
			lwgzzfList = null;
		}
		mv.addObject("reportdate", reportdate);
		mv.addObject("bdate", bdate);
		mv.addObject("edate", edate);
		mv.addObject("lwgzzfList", lwgzzfList);
		mv.addObject("lwgzzfSum", lwgzzfSum);
		//mv.addObject("dpName", dpName);
		mv.addObject("year", year);
		mv.addObject("month", month);
		
		mv.setViewName("listLWGZZFHZ");
		return mv;
	}

	
	
	//跳转到班组计件
	@RequestMapping("/preSettledPieceWork")
	public String preSettledPieceWork(Model model){
		List<Project> prList=projectService.getAllProjectName();
		List<CheckUnit> cuList=checkUnitService.getAllCheckUnit();
		model.addAttribute("prList", prList);
		model.addAttribute("cuList", cuList);
		return "preSettledPieceWork";
	}
	
	//显示班主计件列表
	@RequestMapping("/listSettledPieceWork")
	public String listSettledPieceWork(HttpServletRequest request,Model model) throws  Exception {
		String prId=request.getParameter("prId");
		String pcpId="";
		String[] pcpIds=request.getParameterValues("pcpIdIsSelect");
		System.out.println(pcpIds.length);
		String pName=request.getParameter("pName");
		String psName="";
		String[] psNames=request.getParameterValues("psNameIsSelect");
		System.out.println(psNames.length);
		String cuId=request.getParameter("cuId");
		
		for(int i=0;i<psNames.length;i++)
		{   
			System.out.println(psNames[i]);
			if (i==psNames.length-1) {
				psName+=psNames[i];
			}else {
				psName+=psNames[i]+",";
			}
			
			System.out.println(psName);
		}
		for(int i=0;i<pcpIds.length;i++)
		{   
			System.out.println(pcpIds[i]);
			if (i==pcpIds.length-1) {
				pcpId+=pcpIds[i];
			}else {
				pcpId+=pcpIds[i]+",";
			}
			System.out.println(pcpId);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("prId", prId);
		map.put("pcpId", pcpId);
		map.put("pName", pName);
		map.put("psName", psName);
		map.put("cuId", cuId);
		
		System.out.println(map.toString());
		List<PieceworkServiceTotal> pstList=lwfbService.getPieceworkServiceTotal(map);
		System.out.println(pstList.size());
		model.addAttribute("pstList", pstList);
		return "listSettledPieceWork";
	}
	

	
	//显示班主计件列表NEW
	@RequestMapping("/listSettledPieceWork1")
	public String listSettledPieceWork1(HttpServletRequest request,Model model) throws  Exception {
		String prId=request.getParameter("prId");
		
		String[] pcpIds=request.getParameterValues("pcpIdIsSelect");
		//System.out.println(pcpIds.length);
		String pName=request.getParameter("pName");
		
		String[] psNames=request.getParameterValues("psNameIsSelect");
		//System.out.println(psNames.length);
		String cuId=request.getParameter("cuId");
		List<PieceworkServiceTotal> pstList=new ArrayList<PieceworkServiceTotal>();
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("prId", prId);
		map.put("pName", pName);		
		map.put("cuId", cuId);

		for(int i=0;i<pcpIds.length;i++)
		{   
			map.put("pcpId", pcpIds[i]);
			for(int j=0;j<psNames.length;j++)
			{   
				map.put("psName", psNames[j]);
				//System.out.println(map.toString());
				List<PieceworkServiceTotal> pst=lwfbService.getPieceworkServiceTotal(map);
			    pstList.addAll(pst);
			}
		}
		//System.out.println(pstList.size());
		model.addAttribute("pstList", pstList);
		return "listSettledPieceWork";
	}
	
	//进入项目月度报表前的数据提取，如部门、结算单位、工程等
	@RequestMapping(value="/showLWFBJS")
	public ModelAndView showLWFBJS(HttpSession session){
		ModelAndView mv = new ModelAndView();
		User user = (User)session.getAttribute(CygsConst.USER_SESSION);
		String roleName = user.getRoleName();
		String uId = user.getuId();
		String userLoginName = user.getUserLoginName();
		List<Project> prList = null;
		List<CheckUnit> cuList = null;
		//List<Department> dpList = null;
		if(roleName.equals("项目经理")){
			prList = this.projectService.getProjectByUId(uId);
		}else if(roleName.equals("工长")){
			prList = this.projectService.getProjectListByUserLoginName(userLoginName);
		}else{
			prList = this.projectService.getAllProjectName();
		}
		
		if(roleName.equals("西诺亚劳务")){
			cuList = this.checkUnitService.selectCheckUnitListByName("西诺亚劳务");
		}else{
			cuList = this.checkUnitService.getAllCheckUnit();
		}
		
		//dpList = this.departmentService.selectDpNameandCode();
		mv.addObject("cuList", cuList);
		//mv.addObject("dpList", dpList);
		mv.addObject("prList", prList);
		mv.setViewName("chooseLWFBJS");
		return mv;
	}

	//返回项目月度报表查询结果
	@RequestMapping(value="/getLWFBJS")
	public ModelAndView getLWFBJS(HttpServletRequest request) {
		System.out.println("项目月度报表");
		ModelAndView mv = new ModelAndView();
		String year = (String)request.getParameter("year");
		String month = (String)request.getParameter("month");
		String cuId = (String)request.getParameter("cuId");
		String prId = (String)request.getParameter("prId");
		//String dpCode = (String)request.getParameter("dpCode");
		String status = (String)request.getParameter("status");
		/*Department department = this.departmentService.selectDpByCode(dpCode);
		String dpName = null;
		if(department != null){
			dpName = department.getDpName();
		}*/
		if(cuId == ""){
			cuId = null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String bdate = "";
		String edate = "";
		
		String begindate=""; //构建开始日期
		String enddate=""; //构建结束日期
		int gsyear=Integer.parseInt(year);
		int gsmonth=Integer.parseInt(month);
		List<SetTime> stList = this.setTimeService.getSetTimeByStatusSYearSMonth(1, gsyear, gsmonth);
		if(stList != null && stList.size()>0){
			begindate = sdf.format(stList.get(0).getsBeginTime());
			enddate = sdf.format(stList.get(0).getsEndTime());
		}
//		updat by yzy 100401   获得bdate和edate用于界面显示,26-下月25之间
		int m,y;
		m = Integer.parseInt(month);
		y = Integer.parseInt(year);
		if(m == 1)
		{
			y = y - 1;
			m = 12;
		}else
		{
			m = m - 1;
		}

		bdate = String.valueOf(y)+"-"+String.valueOf(m)+"-26";
		edate = "";
		if(m==12)
		{
			edate=String.valueOf(y+1)+"-1-25";			
		}
		else
			edate=String.valueOf(y)+"-"+String.valueOf(m+1)+"-25";
		//报表日期
		Date date = new Date();
		String reportdate = sdf.format(date);
		Project pr = this.projectService.getProjectById(prId);
		String prName = pr.getPrName();
		String prManager = pr.getuName();
		Lwfbjs lwfbjsSum = new Lwfbjs();
		BigDecimal sum_jjgz=new BigDecimal("0.00");
		BigDecimal sum_jsgz=new BigDecimal("0.00");
		BigDecimal sum_bgqzgz=new BigDecimal("0.00");
		BigDecimal sum_htwbcjgz=new BigDecimal("0.00");		
		//ls 09-08-04
		BigDecimal sum_htcl=new BigDecimal("0.00");
		BigDecimal temp=new BigDecimal("0.00");		
		BigDecimal sum_fk=new BigDecimal("0.00");
		BigDecimal sum_cl=new BigDecimal("0.00");
		BigDecimal sum_qt=new BigDecimal("0.00");
		BigDecimal sum_rgf = new BigDecimal("0.00"); //合计
		int sum_ma=0;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("prId", prId);
		map.put("begindate", begindate);
		map.put("enddate", enddate);
		//map.put("dpName", dpName);
		map.put("cuId", cuId);
		map.put("status", status);
		List<Lwfbjs> lwfbjsList = this.lwfbService.getLwfbjs(map);
		if(lwfbjsList != null && lwfbjsList.size()>0){
			for(Lwfbjs lfjs:lwfbjsList){
				 sum_jjgz=sum_jjgz.add(lfjs.getJjgz());
				 sum_jsgz=sum_jsgz.add(lfjs.getJsgz());
				 sum_bgqzgz=sum_bgqzgz.add(lfjs.getBgqzgz());
				 sum_htwbcjgz=sum_htwbcjgz.add(lfjs.getHtwbgz());
				 sum_htcl=sum_htcl.add(lfjs.getHtcl());
				 BigDecimal bqjsvalue=new BigDecimal("0.00");
				 bqjsvalue = bqjsvalue.add(lfjs.getJjgz());
				 bqjsvalue = bqjsvalue.add(lfjs.getJsgz());
				 bqjsvalue = bqjsvalue.add(lfjs.getBgqzgz());
				 bqjsvalue = bqjsvalue.add(lfjs.getHtwbgz());
				 bqjsvalue = bqjsvalue.add(lfjs.getHtcl());
				 lfjs.setBqjs(bqjsvalue);
				 sum_fk=sum_fk.add(lfjs.getFk());
				 sum_cl=sum_cl.add(lfjs.getCl());
				 sum_qt=sum_qt.add(lfjs.getQt());
				 BigDecimal bqyfvalue = new BigDecimal("0.00");
				 bqyfvalue = bqyfvalue.add(lfjs.getFk());
				 bqyfvalue = bqyfvalue.add(lfjs.getCl());
				 bqyfvalue = bqyfvalue.add(lfjs.getQt());
				 bqyfvalue = bqyfvalue.add(bqjsvalue);
				 lfjs.setBqyf(bqyfvalue);
				 sum_ma += lfjs.getMissionAmount();
			}
			lwfbjsSum.setJjgz(sum_jjgz);
			lwfbjsSum.setJsgz(sum_jsgz);
			lwfbjsSum.setBgqzgz(sum_bgqzgz);
			lwfbjsSum.setHtwbgz(sum_htwbcjgz);
			lwfbjsSum.setHtcl(sum_htcl);
			//temp=sum_jjgz+sum_jsgz+sum_bgqzgz+sum_htwbcjgz+sum_htcl
			temp=temp.add(sum_jjgz);
	   		temp=temp.add(sum_jsgz);
	   		temp=temp.add(sum_bgqzgz);
	   		temp=temp.add(sum_htwbcjgz);
	   		temp=temp.add(sum_htcl);
	   		//setBqjs(sum_jjgz+sum_jsgz+sum_bgqzgz+sum_htwbcjgz+sum_htcl)
			lwfbjsSum.setBqjs(temp);
			
			lwfbjsSum.setFk(sum_fk);
			lwfbjsSum.setCl(sum_cl);
			lwfbjsSum.setQt(sum_qt);
			//setBqyf(sum_fk+sum_cl+sum_qt+temp)
			temp=temp.add(sum_fk);
	   		temp=temp.add(sum_cl);
	   		temp=temp.add(sum_qt);
	   		lwfbjsSum.setBqyf(temp);
	   		lwfbjsSum.setMissionAmount(sum_ma);
		}else{
			lwfbjsSum = null;
			lwfbjsList = null;
		}
		mv.addObject("prName", prName);
		mv.addObject("prManager", prManager);
		mv.addObject("lwfbjsList", lwfbjsList);
		mv.addObject("lwfbjsSum", lwfbjsSum);
		mv.addObject("reportdate", reportdate);
		mv.addObject("bdate", bdate);
		mv.addObject("edate", edate);
		mv.addObject("year", year);
		mv.addObject("month", month);
		//mv.addObject("dpName", dpName);
		
		//查任务书明细
		String isAudited = status;
		if(status.equals("0")){
			isAudited = "2";
		}else if(status.equals("2")){
			isAudited = "";
		}
		Map<String,Object> map2 = new HashMap<String, Object>();
		map2.put("temp1", "");
		map2.put("prId", prId);
		map2.put("temp2", "");
		map2.put("year", year);
		map2.put("month", month);
		map2.put("temp3", "");
		map2.put("cuId", cuId);
		map2.put("isAudited", isAudited);
		List<MissionVo> missionVoList = this.lwfbService.getAllMissionNew(map2);
		System.out.println("missionVoList:"+missionVoList.size());
		if(missionVoList.size()>0 && missionVoList != null){
			for(MissionVo mission:missionVoList){
				sum_rgf = sum_rgf.add(mission.getHEJI());
			}
			
		}else{
			//missionVoList = null;
		}
		MissionVo mvo = new MissionVo();
		mvo.setMCode("合计");
		mvo.setMissionTypeName(sum_rgf.toString());
		missionVoList.add(mvo);
		mv.addObject("missionVoList", missionVoList);
		mv.setViewName("listLWFBJS");
		return mv;
	}

	//工程量对比，提取工程名、分部以及单位
	@RequestMapping(value="/showLWGCLDB")
	public ModelAndView showLWGCLDB(HttpSession session){
		ModelAndView mv = new ModelAndView();
		User user = (User)session.getAttribute(CygsConst.USER_SESSION);
		String roleName = user.getRoleName();
		String uId = user.getuId();
		List<Project> prList = null;
		List<Unit> unList = null;
		List<Part> pList = null;
		if(roleName.equals("项目经理")){
			prList = this.projectService.getProjectByUId(uId);
		}else{
			prList = this.projectService.getAllProjectName();
		}
		pList = this.partService.getAllPart();
		unList = this.unitService.getAllUnit();
		mv.addObject("prList", prList);
		mv.addObject("pList", pList);
		mv.addObject("unList", unList);
		mv.setViewName("chooseLWGCLDB");
		return mv;
	}
	//获取一系列的楼栋号、分部、工作项目等列表
	@RequestMapping(value="/getUnitNumberList")
	@ResponseBody
	public List<PrIndexPC> getUnitNumber(@RequestBody Map<String,String> map){
		//获取楼栋号
		String prId = map.get("prId").toString();
		List<PrIndexPC> pipList = this.projectService.getProjectDetailById(prId);
		return pipList;
	}
	@RequestMapping(value="/getPartList")
	@ResponseBody
	public List<Part> getPartList(@RequestBody Map<String,String> map){
		String pcPId = map.get("pcPId").toString();
		//获取分部列表
		List<Part> pList = this.partService.getPartByPcPId(pcPId);
		return pList;
	}
	@RequestMapping(value="/getPartPositionList")
	@ResponseBody
	public List<PartPosition> getPartPositionList(@RequestBody Map<String,String> map){
		String pId = map.get("pId").toString();
		//获取工程部位列表
		List<PartPosition> ppList = this.partPositionService.getPartPositionByPId(pId);
		return ppList;
	}
	@RequestMapping(value="/getOperationList")
	@ResponseBody
	public List<Operation> getOperationList(@RequestBody Map<String,String> map){
		String psId = map.get("psId").toString();
		List<Operation> opList = this.operationService.selectOperationByPsId(psId);
		return opList;
	}

	// 查询工程量对比表
	@RequestMapping(value = "/getLWGCLDB")
	public ModelAndView getLWGCLDB(HttpServletRequest request) throws SQLException {
		ModelAndView mv = new ModelAndView();
		String pcPId = (String) request.getParameter("pcPId");
		//String unitNumber = pcPId;
		String prId = (String) request.getParameter("prId");
		String unId = (String) request.getParameter("unId");
		String pId = (String) request.getParameter("pId");
		String psId = (String) request.getParameter("psId");
		String oId = (String) request.getParameter("oId");
		if (unId.equals("")) {
			unId = null;
		}
		if (pId.equals("")) {
			pId = null;
		}
		if (psId.equals("")) {
			psId = null;
		}
		if (oId.equals("")) {
			oId = null;
		}
		if (pcPId.equals("")) {
			pcPId = null;
		}
		
		if (pcPId != null) {
			pcPId = this.projectService.getPrIndexPCById(pcPId).getUnitNumber();
		}
		float sum_budgetamount = 0;
		float sum_budgetmoney = 0;
		float sum_amountgap = 0;
		float sum_yikaiamount = 0;
		float sum_yikaimoney = 0;
		float sum_moneygap = 0;
		Project project = this.projectService.getProjectById(prId);
		if (project != null) {
			String prName = project.getPrName();
			String uName = project.getuName();
			mv.addObject("prName", prName);
			mv.addObject("prManager", uName);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 报表日期
		Date date = new Date();
		String reportdate = sdf.format(date);
		mv.addObject("reportdate", reportdate);
		List<Lwgcldb> lwgcldbList = new ArrayList<Lwgcldb>();
		Lwgcldb lwgcldbSum = new Lwgcldb();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("prId", prId);
		map.put("pcPId", pcPId);
		map.put("pId", pId);
		map.put("psId", psId);
		map.put("oId", oId);
		map.put("unId", unId);
		System.out.println("prId:"+prId+",pcpid:"+pcPId+",pId:"+pId+",psId:"+psId+",oId:"+oId+",unId:"+unId);
		//ResultSet rs = null;
		//rs = lwfbService.getLwgcldb(map);
		List<Lwgcldb> rs = lwfbService.getLwgcldb(map);
		if (rs != null) {
			int id = 0;
			//ResultSetMetaData rsmd = rs.getMetaData();
			//int condition = rsmd.getColumnCount();
			//System.out.println("列数为:" + condition);
			//while (rs.next()) {
			for(int i = 0; i < rs.size(); i++) {
				Lwgcldb gcldbVO = new Lwgcldb();
				gcldbVO.setId(++id);
				gcldbVO.setBuildNO(rs.get(i).getBuildNO());
				String temp = null;
				if (rs.get(i).getPart() != null){
					temp = rs.get(i).getPart();
					System.out.println(temp);
					if (temp == ""){
						gcldbVO.setPart("N/A");
					}else{
						Part part = this.partService.getPart(temp);
						if (part != null){
							gcldbVO.setPart(part.getpName());
						}
					}
					if (rs.get(i).getPartposition() != null) {
						temp = rs.get(i).getPartposition();
						System.out.println(temp);
						if (temp == "") {
							gcldbVO.setPartposition("N/A");
						} else {
							PartPosition ps = this.partPositionService.selectPartPosition(temp);
							if (ps != null) {				
								gcldbVO.setPartposition(ps.getPsName());
							}
						}
						if (rs.get(i).getOperation() != null) {
							temp = rs.get(i).getOperation();
							System.out.println(temp);
							if (temp == "") {
								gcldbVO.setOperation("N/A");
							} else {
								Operation op = this.operationService.getOperationByOid(temp);	
								if (op != null){
									gcldbVO.setOperation(op.getoName());
								}
							}
							if (rs.get(i).getUnit() != null) {
								temp = rs.get(i).getUnit();
								System.out.println(temp);
								if (temp == "") {
									gcldbVO.setUnit("N/A");
								} else {
									Unit unit = this.unitService.getUnitById(temp);
									if (unit != null) {
										gcldbVO.setUnit(unit.getUnName());
									}
								}
								if (rs.get(i).getPrice() != null) {
									gcldbVO.setPrice(rs.get(i).getPrice());
								}
							}
						}
					}
				}
				
				gcldbVO.setPrice(rs.get(i).getPrice());
				// 第一行作统计,第二行显示.VOlist保存最后的结果向量数组,gcldbVO是向量
				sum_budgetamount += rs.get(i).getBudgetamount() == null ? 0 : rs.get(i).getBudgetamount();
				gcldbVO.setBudgetamount(rs.get(i).getBudgetamount()==null ? 0 : rs.get(i).getBudgetamount());
				sum_yikaiamount += rs.get(i).getYikaiamount() == null ? 0 : rs.get(i).getYikaiamount();
				gcldbVO.setYikaiamount(rs.get(i).getYikaiamount() == null ? 0 : rs.get(i).getYikaiamount());
				sum_budgetmoney += rs.get(i).getBudgetmoney() == null ? 0 : rs.get(i).getBudgetmoney();
				gcldbVO.setBudgetmoney(rs.get(i).getBudgetmoney() == null ? 0 : rs.get(i).getBudgetmoney());
				sum_yikaimoney += rs.get(i).getYikaimoney() == null ? 0 : rs.get(i).getYikaimoney();
				gcldbVO.setYikaimoney(rs.get(i).getYikaimoney() == null ? 0 : rs.get(i).getYikaimoney());
				sum_amountgap += rs.get(i).getAmountgap() == null ? 0 : rs.get(i).getAmountgap();
				gcldbVO.setAmountgap(rs.get(i).getAmountgap() == null ? 0 : rs.get(i).getAmountgap());
				sum_moneygap += rs.get(i).getMoneygap() == null ? 0 : rs.get(i).getMoneygap();
				gcldbVO.setMoneygap(rs.get(i).getMoneygap() == null ? 0 : rs.get(i).getMoneygap());
				lwgcldbList.add(gcldbVO);
			}
			lwgcldbSum.setBudgetamount(sum_budgetamount);
			lwgcldbSum.setYikaiamount(sum_yikaiamount);
			lwgcldbSum.setBudgetmoney(sum_budgetmoney);
			lwgcldbSum.setYikaimoney(sum_yikaimoney);
			lwgcldbSum.setAmountgap(sum_amountgap);
			lwgcldbSum.setMoneygap(sum_moneygap);
		} else {
			lwgcldbList = null;
			//lwgcldbSum = null;
		}
		mv.addObject("lwgcldbList", lwgcldbList);
		mv.addObject("lwgcldbSum", lwgcldbSum);
		mv.setViewName("listLWGCLDB");
		return mv;
	}

	//员工工资发放汇总，进入页面前的数据提取
	@RequestMapping(value="/showYGGZFFHZ")
	public ModelAndView showYGGZFFHZ(){
		ModelAndView mv = new ModelAndView();
		List<Project> prList = this.projectService.getAllProjectName();
		//List<Department> dpList = this.departmentService.selectDpNameandCode();
		mv.addObject("prList", prList);
		//mv.addObject("dpList", dpList);
		mv.setViewName("chooseYGGZFFHZ");
		return mv;
	}
	
	@RequestMapping(value="/getYGGZFFHZ")
	public ModelAndView getYGGZFFHZ(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		String dpCode = (String)request.getParameter("dpCode");
		String prId = (String)request.getParameter("prId");
		String year = (String)request.getParameter("year");
		String month = (String)request.getParameter("month");
		String status = (String)request.getParameter("status");
		int m,y;
		m = Integer.parseInt(month);
		y = Integer.parseInt(year);
		if(m == 1)
		{
			y = y - 1;
			m = 12;
		}else
		{
			m = m - 1;
		}
		month = String.valueOf(m);
		year = String.valueOf(y);
		String bdate = year+"-"+month+"-26";
		String edate = "";
		if(Integer.parseInt(month)==12)
		{
			edate=new Integer(Integer.parseInt(year)+1).toString()+"-1-25";
		}
		else
			edate=year+"-"+new Integer(Integer.parseInt(month)+1).toString()+"-25";
		
		String begindate=bdate+" 0:00:00"; //构建开始日期
		String enddate=edate+" 23:59:59"; //构建结束日期
		List<Mission> missionList = null;
		List<AuditInfo> auditInfoList = this.auditInforService.getAuditInfoByAuditTime(bdate, edate);
		if(auditInfoList.size()>0){
			Map<String,Object> map = new HashMap<String, Object>();
			List<String> mIdList = new ArrayList<String>();
			for(int i=0;i<auditInfoList.size();i++){
				mIdList.add(auditInfoList.get(0).getmId());
			}
			map.put("mIdList", mIdList);
			map.put("prId", prId);
			map.put("dpCode", dpCode);
			map.put("mtCode", 9);
			map.put("status", status);
			List<AuditRole> arList = this.auditRoleService.getAuditRoleByMtCode(9);
			Integer maxStep = null;
			if(arList.size()>0){
				maxStep = arList.get(0).getAuditStep();
			}
			
			map.put("maxStep", maxStep);
			missionList = this.missionService.getMissionByMap(map);
		}
		if(missionList != null && missionList.size()>0){
			List<String> midList = new ArrayList<String>();
			for(int i =0;i<missionList.size();i++){
				midList.add(missionList.get(i).getmId());
			}
			List<MissionContent> mcList = this.missionContentService.getMissionContentByList(midList);
			mv.addObject("mcList", mcList);
		}
		String prName = this.projectService.getProjectById(prId).getPrName();
		//String dpName = this.departmentService.selectDpByCode(dpCode).getDpName();
		String reportDate = new GetCurrentDate().getCurrentYear() + "-" + new GetCurrentDate().getCurrentMonth() 
				+ "-" + new GetCurrentDate().getCurrentDay();
		if(Integer.parseInt(month) == 12)
		{
			year = new Integer(Integer.parseInt(year)+1).toString();;
			month = "1";
		}else
		{
			month = new Integer(Integer.parseInt(month)+1).toString();
		}
		mv.addObject("year", year);
		mv.addObject("month", month);
		mv.addObject("reportDate", reportDate);
		mv.addObject("prName", prName);
		//mv.addObject("dpName", dpName);
		mv.addObject("bdate", bdate);
		mv.addObject("edate", edate);
		mv.setViewName("listYGGZFFHZ");
		return mv;
	}

}
