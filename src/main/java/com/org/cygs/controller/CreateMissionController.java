package com.org.cygs.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.org.cygs.pojo.AuditInfo;
import com.org.cygs.pojo.JishiPrice;
import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.MissionContent;
import com.org.cygs.pojo.MissionType;
import com.org.cygs.pojo.Operation;
import com.org.cygs.pojo.OperationPriceVo;
import com.org.cygs.pojo.PartPosition;
import com.org.cygs.pojo.PartVo;
import com.org.cygs.pojo.SystemParameter;
import com.org.cygs.pojo.Unit;
import com.org.cygs.pojo.User;
import com.org.cygs.service.AuditInfoService;
import com.org.cygs.service.CheckUnitService;
import com.org.cygs.service.JishiPriceService;
import com.org.cygs.service.LockInfoService;
import com.org.cygs.service.MissionContentService;
import com.org.cygs.service.MissionService;
import com.org.cygs.service.MissionTypeService;
import com.org.cygs.service.OperationPriceService;
import com.org.cygs.service.OperationService;
import com.org.cygs.service.PartPositionService;
import com.org.cygs.service.PartService;
import com.org.cygs.service.ProjectService;
import com.org.cygs.service.SystemParameterService;
import com.org.cygs.service.YUnitService;
import com.org.cygs.util.common.CygsConst;
import com.org.cygs.util.common.StringUtil;

@Controller
public class CreateMissionController {
	@Autowired
	private MissionTypeService missionTypeService;
	@Autowired
	private MissionContentService missionContentService;
	@Autowired
	private MissionService missionService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private LockInfoService lockInfoService;
	@Autowired
	private OperationPriceService operationPriceService;
	@Autowired
	private CheckUnitService checkUnitService;
	@Autowired
	private AuditInfoService auditInfoService;
	@Autowired
	private PartService partService;
	@Autowired
	private PartPositionService partPositionService;
	@Autowired
	private OperationService operationService;
	@Autowired
	private YUnitService unitService;
	@Autowired
	private JishiPriceService jishiPriceService;
	@Autowired
	private SystemParameterService  systemParameterService;
	
	// 删除上次没有上传的附件
	public void delFile(String loginName) {
		File filedir = new File("D:/cyjz_file/test/file/" + loginName);
		if (filedir.exists()) {
			File[] files = filedir.listFiles();
			if (files != null) {
				for (File file : files) {
					if (file.isFile()) {
						file.delete();
					}
				}
			}
			filedir.delete(); // 目录为空时才能删除目录!!!
		}
	}
	// 判断是否关停新建任务书功能
	public void checkStop(Model model) {
		SystemParameter stopadd = systemParameterService.selectByParaName("stopadd");
		Date tdate = new Date(); // 现在的时间
		Date ldate = stopadd.getParaLosetime();
		if (stopadd.getParaValue() == 0) {// ParaValue：0表示关停
			if (ldate.before(tdate)) {// 现在的时间超过了关停的时间，设置ParaValue为1
				stopadd.setParaValue(1);
				systemParameterService.updateSystemParam(stopadd);
			}
		}
		if (stopadd.getParaValue() == 0 && ldate.after(tdate)) {
			model.addAttribute("error", "当前新建任务书功能关闭，如有问题与管理员联系");
		}
	}
	// 设置任务书工作月度
	public void setStartTime(Model model) {
		Calendar now = Calendar.getInstance();
		int syear = now.get(Calendar.YEAR);
		int smonth = now.get(Calendar.MONTH) + 1;
		int sday = now.get(Calendar.DAY_OF_MONTH);
		// 每月20号为中间点，20日前提交任务书，默认工作月度为上个月。20日后提交任务书，为本月。
		if (sday < 20) {
			int dsmonth = smonth - 1;
			smonth = (dsmonth < 1 ? 12 : dsmonth);
			if (smonth == 12) {
				syear = syear - 1;
			}
		}
		String stime = "";
		int submityear = syear;
		int submitmonth = smonth;
		model.addAttribute("submityear", submityear);
		model.addAttribute("submitmonth", submitmonth);
		model.addAttribute("stime", stime);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("date", sdf.format(new Date()));	// 计时任务书的开始时间
	}
	// 变更签证任务书、合同内其他任务书的 开始时间和结束时间
	public void setStartTimeAlterandOther(Model model) {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DAY_OF_MONTH);
		String begindate = null;
		String endate = null;
		if(day <= 25)//如果今天是25号之前，只能新建上个月的
		{
			if(month > 2)
			{
				begindate = year + "-" + (month-2) + "-" + "26";
				endate = year + "-" + (month-1) + "-" + "25";
			}else if(month == 2)
			{
				begindate = year + "-12-" + "26";
				endate = year + "-1-" + "25";
			}else
			{
				year -= 1;
				begindate = year + "-11-" + "26";
				endate = year + "-12-" + "25";
			}
		}else
		{
			if(month > 1)
			{
				begindate = year + "-"+ (month-1) + "-" + "26";
				endate = year + "-"+ month + "-" + "25";
			}else
			{
				year -= 1;
				begindate = year + "-12-" + "26";
				endate = year + "-1-" + "25";
			}
		}
		model.addAttribute("begindate", begindate);
		model.addAttribute("endate", endate);
	}
	// insert 任务书、任务书内容、审核信息
	public String InsertInfo(HttpServletRequest request, Model model, User user, RedirectAttributes attr, String addr) throws Exception {
		// 任务书表头信息
		String prId = request.getParameter("prId");
		String pcpId = request.getParameter("unitnumber");
		String cuId = request.getParameter("cuId");
		String mtId = request.getParameter("mtid");
		String mtCode = request.getParameter("mtcode");
		String styear = request.getParameter("styear");
		String stmonth = request.getParameter("stmonth");
		String begindate = request.getParameter("beginDate");	//计时任务书的开始时间
		String enddate = request.getParameter("endDate");
		
		Mission mission = new Mission();
		mission.setPrId(prId);
		mission.setPcPId(pcpId);
		mission.setCuId(cuId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateb = "", datee = "";
		if (styear != null && styear != "") {
			// 由工作月度生成时间，为**-25到**-26
			int estyear = Integer.parseInt(styear);
			int estmonth = Integer.parseInt(stmonth);
			Map<String, String> map = setWorkTime(estyear, estmonth, dateb, datee);
			dateb = map.get("dateb");
			datee = map.get("datee");
			mission.setBeginDate(new java.sql.Timestamp(sdf.parse(dateb).getTime()));
			mission.setEndDate(new java.sql.Timestamp(sdf.parse(datee).getTime()));
			
			String StrSubmitTime = sdf.format(new Date());// 这里记录字符串格式的"提交时间"
			String StrEndDate = datee; // 这里记录字符串格式的"结束时间"
			String year = StrEndDate.substring(0, 4);
			String month = StrEndDate.substring(5, 7);
			String day = "01";
			String StrMaxSubmitTime = null; // 这里记录字符串格式的"最大提交时间"--下个月1号
			if (month.equalsIgnoreCase("12")) {
				year = StringUtil.autoIncrement(year);
				month = "01";
			} else {
				month = StringUtil.autoIncrement(month);
			}
			StrMaxSubmitTime = year + "-" + month + "-" + day;
			model.addAttribute("flag", "0");
			// 现在8月2号--新建任务书的工作月度为7月--开始-结束时间为：6-26到7-25--最大提交时间是8月1号--所以逾期
			if ((StrSubmitTime.compareToIgnoreCase(StrMaxSubmitTime) > 0)) {	// 现在时间大于最大提交时间--逾期
				mission.setIsoverdue(1);	// 	逾期
				model.addAttribute("flag", "1");
			} else {
				mission.setIsoverdue(0);
			}
		}
		if (begindate != null && enddate != null) {	// 计时任务书-开始时间和结束时间一样--判断逾期的方式不一样
			mission.setBeginDate(new java.sql.Timestamp(sdf.parse(begindate).getTime()));
			mission.setEndDate(new java.sql.Timestamp(sdf.parse(enddate).getTime()));
			Date date = new Date();
			long dayseconds = 60 * 60 * 24 * 1000;
			String sb = sdf.format(date);
			String sb1 = sb + " 00:00:00";
			model.addAttribute("flag", "0");	// 0:正常
			Date date1 = sdf1.parse(sb1);
			if(date1.getTime() >= (sdf1.parse(begindate + " 00:00:00").getTime() + 2 * dayseconds)) // 超过开始时间两天--逾期
			{
				mission.setIsoverdue(1);	// 1:超期
				model.addAttribute("flag", "1");
			}
			else
				mission.setIsoverdue(0);
			if(date.getTime() < sdf.parse(begindate).getTime()) {
				attr.addFlashAttribute("error", "不能开当日之后的任务书");
				return "redirect:preAddJSMission";
			}
		}
		
		Date date = new Date();
		String serial = createMcode(date, sdf, mtCode);	// 生成流水号
		
		mission.setmCode(serial);
		mission.setSubmitTime(new java.sql.Timestamp(date.getTime()));	// 提交时间
		
		// mission.setIsDelete("0");//设置任务书状态（初始为0，删除时为1）
		mission.setStatus(0);
		// 设置版本号，初始为0
		mission.setEdtionId(0);
		// 设置提交人信息
		mission.setRecorderId(user.getuId());
		// 设置任务书类型
		mission.setMtId(mtId);
		// 存工长登录名字
		mission.setSupervisor(user.getUserName());
		mission.setRemark2("1");
		missionService.insertMission(mission);
		String mId = missionService.getMIdByMcode(serial);
		System.out.println("任务书ID: " + mId);
		//String mId = missionService.getMIdByMcode(serial);
		String[] oids = request.getParameterValues("operationId");
		String[] contentSerials = request.getParameterValues("contentSerial");
		String[] partIds = request.getParameterValues("partId");
		String[] psIds = request.getParameterValues("psId");
		String[] floors = request.getParameterValues("floor");
		String[] units = request.getParameterValues("unitId");
		String[] prices = request.getParameterValues("price");
		String[] realQuantitys = request.getParameterValues("realQuantity");
		String[] wageSums = request.getParameterValues("wageSum");
		String[] accQuantitys = request.getParameterValues("accQuantity");
		String[] innerIds = request.getParameterValues("innerId");
		String[] remarks = request.getParameterValues("remark");
		String[] isReturns = request.getParameterValues("isReturn");
		int contentSerial;
		if (realQuantitys != null && realQuantitys.length > 0) { // 添加任务书每条明细内容
			for (int i = 0; i < realQuantitys.length; i++) {
				if (realQuantitys[i] != "" && !realQuantitys[i].equals("0")) {
					if (contentSerials != null && contentSerials.length > 0)
						contentSerial = Integer.valueOf(contentSerials[i]);
					else 
						contentSerial = i+ 1;
					String operationId = oids[i];
					String partId = partIds[i];
					String psId = psIds[i];
					String floor = null;
					if (floors != null) {
						floor = StringUtil.trans(floors[i]);
					}
					String unit = null;
					if (units != null) {
						unit = units[i];
					}
					String isReturn = null;
					if (isReturns != null)
						isReturn = isReturns[i];
					String remark = StringUtil.trans(remarks[i]);
					
					Double price = Double.parseDouble(prices[i]);
					Double realQuantity = Double.parseDouble(realQuantitys[i]);
					Double wageSum = Double.parseDouble(wageSums[i]);
					BigDecimal bprice = new BigDecimal(price.doubleValue());
					BigDecimal brealQuantity = new BigDecimal(realQuantity.doubleValue());
					BigDecimal bwageSum = new BigDecimal(wageSum.doubleValue());
					// 保留小数位数
					bprice = bprice.setScale(2, RoundingMode.HALF_UP);
					brealQuantity = brealQuantity.setScale(2, RoundingMode.HALF_UP);
					bwageSum = bwageSum.setScale(2, RoundingMode.HALF_UP);
					
					System.out.println("单位" + unit);
					System.out.println("分部" + partId);
					System.out.println("工程部位" + psId);
					System.out.println("工程项目" + operationId);
					System.out.println("price" + bprice);
					System.out.println("oid" + oids[i]);
					System.out.println("realQuantity" + brealQuantity);
					System.out.println("wageSum" + bwageSum);
					System.out.println("remark" + remark);
					
					MissionContent mc = new MissionContent();
					mc.setMcCode(contentSerial);
					mc.setmId(mId);
					mc.setpId(partId);
					mc.setPsId(psId);
					mc.setoId(operationId);
					mc.setFloor(floor);
					mc.setUnId(unit);
					if (accQuantitys != null && accQuantitys.length > 0) {
						Double accQuantity = Double.parseDouble(accQuantitys[i]);
						BigDecimal baccQuantity = new BigDecimal(accQuantity.doubleValue());
						baccQuantity = baccQuantity.setScale(4, RoundingMode.HALF_UP);
						System.out.println("accQuantity" + baccQuantity);
						mc.setAccumulateSum(baccQuantity);
					}
					mc.setPrice(bprice);
					mc.setRealQuantity(brealQuantity);
					mc.setWageSum(bwageSum);
					mc.setIsreturn(isReturn);
					mc.setRemark(remark);
					if (mtCode.equals("4")) {
						mc.setInnerId(innerIds[i]);
						System.out.println("innerId" + innerIds[i]);
					} else {
						mc.setInnerId("");
					}
					missionContentService.insertMissionContent(mc);
				}
			}
				// 插入审核记录
				AuditInfo ai = new AuditInfo();
				ai.setmId(mId);
				ai.setAuditResult(-1);
				MissionType mt = missionTypeService.getMissionTypeById(mtId);
				System.out.println(mt.getMtName());
				ai.setAuditComment("【新建" + mt.getMtName() + "】");
				ai.setAuditTime(new java.sql.Timestamp(new Date().getTime()));
				ai.setuId(user.getuId());
				ai.setRoId(user.getRoId());
				auditInfoService.insertAuditInfo(ai);
		} else {
			attr.addFlashAttribute("error", "你没有添加任务书的内容");	// 但任务书还是添加了
			return "redirect:" + addr;
		}
		
		File srcFile = new File("D:/cyjz_file/test/file/" + user.getUserLoginName());
		if (srcFile.isDirectory()) {
			File destFile = new File("D:/cyjz_file/test/file/" + mId);
			srcFile.renameTo(destFile);
		}
		return "redirect:missionSubmitList";
		
	}
	
	// 由工作月度生成时间，为**-25到**-26
	public Map<String, String> setWorkTime(int estyear, int estmonth, String dateb, String datee) {
		int bstyear, bstmonth;
		if(estmonth == 1){
			bstmonth = 12;
			bstyear = estyear - 1;
		}else{
			bstmonth = estmonth - 1;
			bstyear = estyear;
		}
		String bstmonths = Integer.toString(bstmonth);
		String estmonths = Integer.toString(estmonth);
		if(bstmonth < 10){
			bstmonths = "0" + bstmonth;
		}
		if(estmonth < 10){
			estmonths = "0" + estmonth;
		}
		dateb = bstyear + "-" + bstmonths + "-" + "26";
		datee = estyear + "-" + estmonths + "-" + "25";
		Map<String, String> map = new HashMap<String, String>();
		map.put("dateb", dateb);
		map.put("datee", datee);
		return map;
	}
	
	public String createMcode(Date date, SimpleDateFormat sdf, String mtCode) {
		String sb = sdf.format(date);
		// 取年、月、日
		String year = sb.substring(0, 4);
		String month = sb.substring(5, 7);
		String day = sb.substring(8, 10);
		// 生成流水号
		String fserial = mtCode + year + month + day;	// 任务书编号
		String lserial = null;

		List<String> mList = missionService.selectLikedMCode(fserial);
		
		
		if (mList != null && mList.size() > 0) {
			lserial = (mList.get(mList.size() - 1)).substring(9, 15);
			lserial = StringUtil.autoIncrement(lserial);
			System.out.println("任务书编号：" + lserial);
		} else {
			lserial = "000001";
		}
		// 组合成流水号
		String serial = fserial + lserial;
		return serial;
	}
	
	
	// 计件任务书
	@RequestMapping("preAddJJMission")
	public String preAddJJMission(Model model, RedirectAttributes attr, HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		model.addAttribute("user", user);
		
		System.out.println("计件任务书");
		checkStop(model);
		setStartTime(model);
		MissionType mt = missionTypeService.getMissionTypeByName("计件任务书");
		model.addAttribute("mtid", mt.getMtId());
		model.addAttribute("mtcode", mt.getMtCode());
		delFile(user.getUserLoginName());
		
		return "audit/addJJMission";
	}
	@RequestMapping("addJJMission")
	public String AddJJMission(HttpServletRequest request, RedirectAttributes attr, HttpSession session, Model model) throws Exception {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		
		return InsertInfo(request, model, user, attr, "preAddJJMission");
	}
	
	// 按单价计件任务书
	@RequestMapping("preAddJJMissionDj")
	public String preAddJJMissionDj(Model model, RedirectAttributes attr, HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		model.addAttribute("user", user);
		
		System.out.println("按单价计件任务书");
		checkStop(model);
		setStartTime(model);
		MissionType mt = missionTypeService.getMissionTypeByName("计件任务书");
		model.addAttribute("mtid", mt.getMtId());
		model.addAttribute("mtcode", mt.getMtCode());
		delFile(user.getUserLoginName());
		
		return "audit/addJJMissionDj";
	}
	@RequestMapping("addJJMissionDj")
	public String addJJMissionDj(HttpServletRequest request, RedirectAttributes attr, HttpSession session, Model model) throws Exception {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		
		return InsertInfo(request, model, user, attr, "preAddJJMissionDj");
	}
	// 计时任务书
	@RequestMapping("preAddJSMission")
	public String preAddJSMission(Model model, RedirectAttributes attr, HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		model.addAttribute("user", user);
		
		System.out.println("计时任务书");
		checkStop(model);
		setStartTime(model);
		MissionType mt = missionTypeService.getMissionTypeByName("计时任务书");
		model.addAttribute("mtid", mt.getMtId());
		model.addAttribute("mtcode", mt.getMtCode());
		delFile(user.getUserLoginName());
		
		return "audit/addJSMission";
	}
	@RequestMapping("addJSMission")
	public String addJSMission(HttpServletRequest request, RedirectAttributes attr, HttpSession session, Model model) throws Exception {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		
		return InsertInfo(request, model, user, attr, "preAddJSMission");
	}
	
	// 按单价计时任务书
	@RequestMapping("preAddJSMissionDj")
	public String preAddJSMissionDj(Model model, RedirectAttributes attr, HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		model.addAttribute("user", user);
		
		System.out.println("按单价计时任务书");
		checkStop(model);
		setStartTime(model);
		MissionType mt = missionTypeService.getMissionTypeByName("计时任务书");
		model.addAttribute("mtid", mt.getMtId());
		model.addAttribute("mtcode", mt.getMtCode());
		delFile(user.getUserLoginName());
		
		return "audit/addJSMissionDj";
	}
	@RequestMapping("addJSMissionDj")
	public String addJSMissionDj(HttpServletRequest request, RedirectAttributes attr, HttpSession session, Model model) throws Exception {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		
		return InsertInfo(request, model, user, attr, "preAddJSMissionDj");
	}
	
	// 合同内其他任务书
	@RequestMapping("preAddHTNQTMission")
	public String preAddOtherMission(Model model, RedirectAttributes attr, HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		model.addAttribute("user", user);
		
		System.out.println("合同内其他任务书");
		checkStop(model);
		setStartTimeAlterandOther(model);
		MissionType mt = missionTypeService.getMissionTypeByName("合同内其他任务书");
		model.addAttribute("mtid", mt.getMtId());
		model.addAttribute("mtcode", mt.getMtCode());
		delFile(user.getUserLoginName());
		
		return "audit/addHTNQTMission";
	}
	@RequestMapping("addHTNQTMission")
	public String addOtherMission(HttpServletRequest request, RedirectAttributes attr, HttpSession session, Model model) throws Exception {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		
		return InsertInfo(request, model, user, attr, "preAddHTNQTMission");
	}
	
	// 合同外补差价任务书
	@RequestMapping("preAddHTWBCJMission")
	public String preAddOutMission(Model model, RedirectAttributes attr, HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		model.addAttribute("user", user);
		
		System.out.println("合同外补差价任务书");
		checkStop(model);
		setStartTime(model);
		MissionType mt = missionTypeService.getMissionTypeByName("合同外补差价任务书");
		model.addAttribute("mtid", mt.getMtId());
		model.addAttribute("mtcode", mt.getMtCode());
		delFile(user.getUserLoginName());
		
		return "audit/addHTWBCJMission";
	}
	@RequestMapping("addHTWBCJMission")
	public String addOutMission(HttpServletRequest request, RedirectAttributes attr, HttpSession session, Model model) throws Exception {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		
		return InsertInfo(request, model, user, attr, "preAddHTWBCJMission");
	}
	
	// 变更签证任务书
	@RequestMapping("preAddBGQZMission")
	public String preAddAlterVisaMission(Model model, RedirectAttributes attr, HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		model.addAttribute("user", user);
		
		System.out.println("变更签证任务书");
		checkStop(model);
		setStartTimeAlterandOther(model);
		
		MissionType mt = missionTypeService.getMissionTypeByName("变更签证任务书");
		model.addAttribute("mtid", mt.getMtId());
		model.addAttribute("mtcode", mt.getMtCode());
		delFile(user.getUserLoginName());
		
		return "audit/addBGQZMission";
	}
	@RequestMapping("addBGQZMission")
	public String addAlterVisaMission(HttpServletRequest request, RedirectAttributes attr, HttpSession session, Model model) throws Exception {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		return InsertInfo(request, model, user, attr, "preAddBGQZMission");
	}
	
	
	// 按查询计时单价
	@RequestMapping("queryJSUnitPrice")
	public String preAddJJMission(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		StringBuffer result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		result.append("<all>");
			
		String prId = (String) request.getParameter("prid");
		String pcpId = (String) request.getParameter("unitnumber");
		String cuId = (String) request.getParameter("cuId");
		
		List<JishiPrice> list = jishiPriceService.selectJishiUnitPrice(prId, cuId, pcpId);
		System.out.println("查询计时单价：数量：" + list.size() + " prId=" + prId + " pcpId=" + pcpId + " cuId=" + cuId);
		if(list != null && list.size() > 0)
		{
			for(int i = 0; i < list.size(); i++)
			{
				JishiPrice op = list.get(i);
				
				result.append("<price>");
				result.append("<opId>" + op.getJspId() + "</opId>");
				result.append("<partid>" + op.getpId() + "</partid>");
				result.append("<part>" + op.getpName() + "</part>");
				result.append("<fx1id>" + op.getPsId() + "</fx1id>");
				result.append("<fx1>" + op.getPsName()
						.replaceAll("<", "&lt;")
						.replaceAll(">", "&gt;")
						.replaceAll("&", "&amp;")
						.replaceAll("'", "&apos;")
						.replaceAll("\"", "&quot;") + "</fx1>");
				result.append("<fx2id>" + op.getoId() + "</fx2id>");
				result.append("<fx2>" + op.getoName()
						.replaceAll("<", "&lt;")
						.replaceAll(">", "&gt;")
						.replaceAll("&", "&amp;")
						.replaceAll("'", "&apos;")
						.replaceAll("\"", "&quot;") + "</fx2>");
				result.append("<unitid>" + op.getUnId() + "</unitid>");
				result.append("<unit>" + op.getUnName() + "</unit>");
				result.append("<tprice>" + op.getPrice() + "</tprice>");
				result.append("<cuname>" + op.getCuName() + "</cuname>");
				result.append("</price>");
			} 
		}
		result.append("</all>");
		out.println(result.toString());
		out.close();		
		return null;
	}
	
	// 查询计件单价
	@RequestMapping("queryJJUnitPrice")
	public void queryUnitPrice(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		StringBuffer result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		result.append("<all>");

		String prId = (String) request.getParameter("prid");
		String pcpId = (String) request.getParameter("unitnumber");
		String cuId = (String) request.getParameter("cuId");
		
		List<OperationPriceVo> opList = operationPriceService.selectOperationPrice(prId, cuId, pcpId);
		System.out.println("查询计件单价：数量：" + opList.size() +" prId= " + prId + " pcpId= " + pcpId + " cuId= " + cuId);
		if (opList != null && opList.size() > 0) {
			for (int i = 0; i < opList.size(); i++) {
				OperationPriceVo op = opList.get(i);
				if (op.getpName().equals("计时工")) {
					continue;
				}
				result.append("<price>");
				result.append("<opId>" + op.getOpId() + "</opId>");
				result.append("<partid>" + op.getpId() + "</partid>");
				result.append("<part>" + op.getpName() + "</part>");
				result.append("<fx1id>" + op.getPsId() + "</fx1id>");
				result.append("<fx1>" + op.getPsName()
								.replaceAll("<", "&lt;")
								.replaceAll(">", "&gt;")
								.replaceAll("&", "&amp;")
								.replaceAll("'", "&apos;")
								.replaceAll("\"", "&quot;") + "</fx1>");
				result.append("<fx2id>" + op.getoId() + "</fx2id>");
				result.append("<fx2>" + op.getoName().replaceAll("<", "&lt;")
								.replaceAll(">", "&gt;")
								.replaceAll("&", "&amp;")
								.replaceAll("'", "&apos;")
								.replaceAll("\"", "&quot;") + "</fx2>");
				result.append("<unitid>" + op.getUnId() + "</unitid>");
				result.append("<unit>" + op.getUnName() + "</unit>");
				result.append("<tprice>" + op.getPrice() + "</tprice>");
				if (op.getAccumulateSum() == null)
					result.append("<accsum>" + 0 + "</accsum>");
				else
					result.append("<accsum>" + op.getAccumulateSum() + "</accsum>");
				result.append("<cuid>" + op.getCuId() + "</cuid>");
				result.append("<cuname>" + op.getCuName() + "</cuname>");

				if (op.getBudgetSum() == null) {
					result.append("<bgs>" + 0 + "</bgs>");
				} else {
					result.append("<bgs>" + op.getBudgetSum() + "</bgs>");
				}
				// MissionType mt = missionTypeService.getMissionTypeByName("计件任务书");
				Float sumReal = missionContentService.selectRealQuantity(prId, cuId, pcpId);	//只有计件任务书才需要查询已开量
				if (sumReal != null) {
					result.append("<jjykl>" + sumReal + "</jjykl>");	// 已开工程量
				} else {
					result.append("<jjykl>" + 0 + "</jjykl>");
				}
				
				result.append("</price>");
			}
		}
		result.append("</all>");
		out.println(result.toString());
		out.close();
		
	}
	
	@RequestMapping("toAddMission")
	public String toAddMission(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr, HttpSession session, Model model) throws IOException {
		String index = request.getParameter("index");
		String prId = request.getParameter("prName");
		String cuId = request.getParameter("cuName");
		String mtId = request.getParameter("mtid");
		String pcpId = request.getParameter("unitnumber");
		
		List<PartVo> partList = partService.getPartInfo(prId, pcpId, cuId);
		System.out.println("prId = " + prId);
		System.out.println("pcpId = " + pcpId);
		System.out.println("cuId = " + cuId);
		System.out.println("partList = " + partList.size());
		
		MissionType mt = missionTypeService.getMissionTypeById(mtId);
		model.addAttribute("mtCode", mt.getMtCode());
		model.addAttribute("mtid", mtId);
		
		if (mt.getMtCode() == 4) {
			List<MissionContent> innerId = missionContentService.getInnerIdByPrId(prId);
			if (innerId != null && innerId.size() > 0) {
				model.addAttribute("innerList", innerId);
				System.out.println("innerId = " + innerId.size() + " " + innerId.get(0).getInnerId());
				if (innerId.size() < 9) {
					model.addAttribute("innerId", "内0" + (innerId.size()));
				} else
					model.addAttribute("innerId", "内" + (innerId.size()));
			} else {
				model.addAttribute("innerList", "0");
				System.out.println("innerId = 0");
			}
		}
		System.out.println("mtCode = " + mt.getMtCode());
		if (partList != null && partList.size() > 0) {
			List<Unit> unit = unitService.getAllUnit();
			Unit workday = unitService.getUnitByName("工日");
			model.addAttribute("workday", workday);
			model.addAttribute("units", unit);
			model.addAttribute("index", index);
			model.addAttribute("pcpId", pcpId);
			model.addAttribute("prId", prId);
			model.addAttribute("prName", partList.get(0).getPrName());
			model.addAttribute("cuName", partList.get(0).getCuName());
			model.addAttribute("cuId", partList.get(0).getCuId());
			model.addAttribute("unitNumber", partList.get(0).getUnitNumber());
			if (mt.getMtCode() == 3) {
				for (int i = 0; i < partList.size(); i++) {
					if (partList.get(i).getpName().equals("计时工")) {
						model.addAttribute("pId", partList.get(i).getpId());
						model.addAttribute("pName", partList.get(i).getpName());
						model.addAttribute("js", "no");
						System.out.println("part = " + partList.get(i).getpName());
						break;
					}
				}
			} else if (mt.getMtCode() == 4 || mt.getMtCode() == 5 || mt.getMtCode() == 7) {
				for (int i = 0; i < partList.size(); i++) {
					if (partList.get(i).getpName().equals("其它")) {
						model.addAttribute("pId", partList.get(i).getpId());
						model.addAttribute("pName", partList.get(i).getpName());
						model.addAttribute("other", "no");
						System.out.println("part = " + partList.get(i).getpName());
						break;
					}
				}
			} else {
				model.addAttribute("parts", partList);
			}
		}
		else {
			model.addAttribute("error", "根据所选的栋号、结算单位未查到相关工程信息!");
		}
		return "audit/preAddMission";
	}
	
	// 查分项一：工程部位
	@RequestMapping("listPartPositionByPart")
	public String listPartPositionByPart(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		StringBuffer result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		result.append("<all>");	
		String partId = request.getParameter("partId");
		String index = request.getParameter("index");
		
		result.append("<index>" + index + "</index>");
		System.out.println("查分项一 ： " + partId);
		List<PartPosition> list = partPositionService.getPartPositionByPId(partId);
		System.out.println("数量： " + list.size());
		if(list != null && list.size() > 0)
		{
			for(int i = 0; i < list.size(); i++)
			{
				PartPosition ps = list.get(i);
				result.append("<partPosition>");
				result.append("<psId>" + ps.getPsId() + "</psId>");
				result.append("<psName>" + "<![CDATA["+ps.getPsName()+"]]>" + "</psName>");	
				result.append("</partPosition>");
			}
		}
		result.append("</all>");
		out.println(result.toString());
		out.close();		
		return null;
	}
	
	// 查分项二：工作项目
	@RequestMapping("listOperationByPS")
	public String listOperationByPS(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		StringBuffer result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		result.append("<all>");
		String psId = request.getParameter("psId");
		String index = request.getParameter("index");
		result.append("<index>" + index + "</index>");

		System.out.println("查分项二 ： " + psId);

		List<Operation> list = operationService.selectOperationByPsId(psId);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Operation o = list.get(i);
				result.append("<Operation>");
				result.append("<OId>" + o.getoId() + "</OId>");
				result.append("<OName>" + "<![CDATA[" + o.getoName() + "]]>"
						+ "</OName>"); // "<![CDATA["+ps.getPsName()+"]]>"
				result.append("</Operation>");
			}
		}
		result.append("</all>");
		out.println(result.toString());
		out.close();
		return null;
	}
	
	@RequestMapping("listUnit")
	public String listUnit(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		StringBuffer result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		result.append("<all>");
		
		String operationId = request.getParameter("oId");
		String cuId = request.getParameter("cuId");
		String prId = request.getParameter("prId");
		String pcpId = request.getParameter("pcpId");
		//String mtId = request.getParameter("mtId");
		String index = request.getParameter("index");
		String pId = request.getParameter("pId");
		String psId = request.getParameter("psId");
		result.append("<index>" + index + "</index>");
		System.out.println("查单价: psId=" + psId + "oId=" + operationId + "  prId=" + prId + "cuId=" + cuId + "  pcpId=" + pcpId);
		
		//List<OperationVo> listUnit1 = operationService.selectUnitInfoByOid(operationId);	//通过Operation查询UN_ID不为空的值
		//List<OperationPrice> listprice = operationPriceService.selectPriceByOid(operationId);	// OperationPrice里面O_ID不重复?
		// List<OperationPriceVo> listUnit1 =  operationPriceService.selectUnitPriceByOid(operationId);	// 对应以前有分项3和没有分项3
		// System.out.println("listUnit1=" + listUnit1.size());
		// 无分项3
		/*if (listUnit1 != null && listUnit1.size() > 0) {
			for (int i = 0; i < listUnit1.size(); i++) {
				OperationPriceVo op = listUnit1.get(i);
				
				result.append("<Unit>");
				result.append("<unitId>" + op.getUnId() + "</unitId>");
				result.append("<unitName>" + op.getUnName() + "</unitName>");
				if (null != listUnit1.get(i).getPrice())
					result.append("<price>" + listUnit1.get(i).getPrice() + "</price>");
				else
					result.append("<price>0</price>");
				result.append("</Unit>");
			}
		} else {*/
		// List<OperationPriceVo> listUnit2 = operationPriceService.queryOperationPriceUnit(psId, operationId);	//在Operation_Price里面查单价
		List<OperationPriceVo> listUnit2 = operationPriceService.queryOperationPriceUnit(prId, pcpId, pId, psId, operationId, cuId);
		System.out.println("listUnit2=" + listUnit2.size());
		if (listUnit2 != null && listUnit2.size() > 0) {
			for (int i = 0; i < listUnit2.size(); i++) {
				OperationPriceVo op = listUnit2.get(i);
				System.out.println("listUnit2  单价=" + op.getPrice() + op.getUnName());
				result.append("<Unit>");
				result.append("<unitId>" + op.getUnId() + "</unitId>");
				result.append("<unitName>" + op.getUnName() + "</unitName>");
				if(null != op.getPrice())
					result.append("<price>" + op.getPrice() + "</price>");	
				else
					result.append("<price>0</price>");
				if(op.getBudgetSum() != null)
					result.append("<ykl>" + op.getBudgetSum() + "</ykl>");
				else
					result.append("<ykl>0</ykl>");	// 预控量
				// MissionType mt = missionTypeService.getMissionTypeByName("计件任务书");
				Float sumReal = missionContentService.selectRealQuantity(prId, cuId, pcpId);	// 查询已开量
				if(sumReal!=null)
				{
					result.append("<jjykl>" + sumReal + "</jjykl>");	// 已开量
				}else{						
					result.append("<jjykl>0</jjykl>");
				}
				result.append("</Unit>");
			}
		}
		
		result.append("</all>");
		out.println(result.toString());
		out.close();
		return null;
	}
	
	// 查询已结量
	@RequestMapping("countAccQuantity")
	public String countRealQuantity(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = null;
		String querystr=request.getParameter("querystr");
		System.out.println("查询已结累计工程量");
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(querystr);	// 获取json对象
        Map<String, Object> map = new HashMap<String, Object>();
        String prId = rootNode.get("project").asText();
        String pId = rootNode.get("part").asText();
        String cuId = rootNode.get("checkunit").asText();
        String oId = rootNode.get("operation").asText();
        String unId = rootNode.get("unit").asText();
        String mtId = rootNode.get("mtid").asText();
        String pcpId = rootNode.get("unumber").asText();
        String psId = rootNode.get("psid").asText();
		map.put("prId", prId);
		map.put("pId", pId);
		map.put("psId", psId);
		map.put("oId", oId);
		map.put("pcpId", pcpId);
		map.put("cuId", cuId);
		map.put("mtId", mtId);
		map.put("unId", unId);
        // 查询已结量
        Float sumAcc = missionContentService.queryaccQuantity(map);
        if(sumAcc != null)
        	map.put("accQuantity", sumAcc);
		else
			map.put("accQuantity", 0);
		//查单价
        //如果加了cuId查不出来，就不加cuId再查一遍，在mapper里面加判断
		List<OperationPriceVo> listUnit = operationPriceService.queryUnitBySeven(prId, pcpId, pId, psId, oId, unId, cuId);
		if(listUnit!=null && listUnit.size()>0) {
			for(int i=0; i < listUnit.size(); i++) {
				OperationPriceVo op = listUnit.get(i);
				if (op != null) {
					map.put("price", op.getPrice());
				} else {
					map.put("price", 0);
				}
			}
			System.out.println("加上cuid查询单价");
		} else {
			List<OperationPriceVo> listUnit1 = operationPriceService.queryUnitBySeven(prId, pcpId, pId, psId, oId, unId, null);
			if(listUnit1 != null && listUnit1.size()>0) {
				for(int i=0; i < listUnit1.size(); i++) {
					OperationPriceVo op = listUnit1.get(i);
					if (op != null) {
						map.put("price", op.getPrice());
					} else {
						map.put("price", 0);
					}
				}
				System.out.println("不加cuid查询单价--应该不存在这种情况了");
			}
		}
		String json = mapper.writeValueAsString(map);	// 转为json字符串
		//System.out.println(json);
		request.setCharacterEncoding("UTF-8"); 	      
		out = response.getWriter(); 	    
		out.println(json);
		out.close(); 
		return null;
	}
	
	@RequestMapping("preUploadFile")
	public String preUploadFile(HttpServletRequest request, HttpSession session, Model model) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String mid = request.getParameter("mid");
		String up = request.getParameter("up");
		System.out.println(mid);
		String mCode = request.getParameter("mCode");
		Vector<String> filenamelist = new Vector<String>();
		File downfiledir = new File("D:/cyjz_file/test/file/" + mid);
		if (downfiledir.exists()) {
			File[] tmpfiles = downfiledir.listFiles();
			for (int i = 0; i < tmpfiles.length; i++) {
				if (tmpfiles[i].isFile())
					filenamelist.add(tmpfiles[i].getName());
			}
		}
		model.addAttribute("mid", mid);
		model.addAttribute("mCode", mCode);
		model.addAttribute("filenamelist", filenamelist);
		if (up != null && up.equals("1")) {
			model.addAttribute("user", user);
			return "audit/uploadFileNew";
		}
		else
			return "audit/uploadFile";
	}
	// 上传附件
	@RequestMapping("uploadFile")
	public String uploadFile(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam("uploadfile") MultipartFile uploadfile, RedirectAttributes attr, Model model) throws IOException {
		String mid = request.getParameter("mid");
		String mCode = request.getParameter("mCode");
		String up = request.getParameter("up");
		String filename = uploadfile.getOriginalFilename();
		//设置允许上传文件类型
        String suffixList = ".rar, .zip, .xls, .doc, .ppt, .txt, .jpg";
        // 获取文件后缀
        String suffix = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
        
		String path = "D:/cyjz_file/test/file/" + mid;
		long gfilesize = 0;
		String gfilename = "";
		
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		// 获取文件名称
		int dot = filename.lastIndexOf('.'); 
		gfilename = filename.substring(0, dot);
		gfilesize = uploadfile.getSize();
		// 获取该目录下文件列表
		File f = new File("D:/cyjz_file/test/file/" + mid);
		File[] allfiles = f.listFiles();
		boolean issame = false;
		for (int i = 0; i < allfiles.length; i++) {
			if (filename.equalsIgnoreCase(allfiles[i].getName()))	// 判断是否重名
				issame = true;
		}
		
		if (filename == null || filename == "") {
			System.out.println("上传失败!----文件名为空或路径不正确!");
			attr.addFlashAttribute("msg", "上传失败!<br/>文件名为:" + gfilename + "<br/>大小为:"+ gfilesize + "字节" + "<br/>出错信息为:<br/>--- 文件名为空或路径不正确!");
		}
		else if (uploadfile.getSize() > 20 * 1024 * 1024) {
			System.out.println("上传失败!----所传文件过大!");
			attr.addFlashAttribute("msg", "上传失败!<br/>文件名为:" + gfilename + "<br/>大小为:"+ gfilesize + "字节" + "<br/>出错信息为:<br/>--- 所传文件过大!");
		}
		else if (!suffixList.contains(suffix.trim().toLowerCase())) {
			System.out.println("文件类型不合法!只能为rar,zip,doc,xls,ppt,jpg类型的文件");
			attr.addFlashAttribute("msg", "上传失败!<br/>文件名为:"+ gfilename+ "<br/>大小为:"+ gfilesize+ "字节" + "<br/>出错信息为:<br/>--- 文件类型不合法!只能为rar,zip,doc,xls,ppt,jpg类型的文件");
		}
		else if (issame) {
			System.out.println("上传失败!----与已上传的文件同名,请更改名字后再上传!");
			attr.addFlashAttribute("msg", "上传失败!<br/>文件名为:" + gfilename + "<br/>大小为:"+ gfilesize+ "字节" + "<br/>出错信息为:<br/>--- 与已上传的文件同名,请更改名字后再上传!");
			}
		else {
			uploadfile.transferTo(new File(path + File.separator + filename));
			System.out.println("上传成功!<br>文件名为:" + filename + "<br/>大小为:" + gfilesize + "字节");
			attr.addFlashAttribute("msgsuccess", "上传成功!<br>文件名为:" + filename + "<br/>大小为:" + gfilesize + "字节");
		}
		// 设置重定向url参数
		attr.addAttribute("mid", mid);
		attr.addAttribute("up", up);
		attr.addAttribute("mCode", mCode);
		return "redirect:preUploadFile";
	}
	// 删除附件
	@RequestMapping("deleteFile")
	public String deleteFile(HttpServletRequest request, HttpSession session, RedirectAttributes attr, Model model) throws IOException {
		String mid = request.getParameter("mid");
		String mCode = request.getParameter("mCode");
		String filename = request.getParameter("fn");
		String up = request.getParameter("up");
		File filedir = new File("D:/cyjz_file/test/file/" + mid);
		File file = new File(filedir, filename);
		System.out.println("文件" + filename);
		if (filedir.exists()) {
			if (file.exists()) {
				System.out.println("删除文件" + filename);
				file.delete();
			}
			if (filedir.listFiles().length == 0) // 目录为空时才能删除目录
				filedir.delete();
		}
		attr.addAttribute("mid", mid);
		attr.addAttribute("up", up);
		attr.addAttribute("mCode", mCode);
		return "redirect:preUploadFile";
	}
	
	// 下载附件
	@RequestMapping("downloadFile")
	public void downloadFile(HttpServletRequest request, HttpSession session, HttpServletResponse response, RedirectAttributes attr, Model model) throws IOException {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			String mid = request.getParameter("mid");
			String filename = request.getParameter("fn");
			System.out.println("filename   "+ filename);
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "utf-8"));
			bis = new BufferedInputStream(new FileInputStream(new File("D:/cyjz_file/test/file/" + mid, filename)));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[20480]; // 缓冲20K
			int bytesRead;
			while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	
}
