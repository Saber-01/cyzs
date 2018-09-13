package com.org.cygs.controller;

import java.io.File;
import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.org.cygs.pojo.CheckUnit;
import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.MissionContent;
import com.org.cygs.pojo.MissionType;
import com.org.cygs.pojo.OperationPriceVo;
import com.org.cygs.pojo.PrIndexPC;
import com.org.cygs.pojo.ProjectIndexS;
import com.org.cygs.pojo.SystemParameter;
import com.org.cygs.pojo.User;
import com.org.cygs.service.AuditInfoService;
import com.org.cygs.service.CheckUnitService;
import com.org.cygs.service.LockInfoService;
import com.org.cygs.service.MissionContentService;
import com.org.cygs.service.MissionService;
import com.org.cygs.service.MissionTypeService;
import com.org.cygs.service.OperationPriceService;
import com.org.cygs.service.OperationService;
import com.org.cygs.service.PartPositionService;
import com.org.cygs.service.PartService;
import com.org.cygs.service.ProjectIndexPCService;
import com.org.cygs.service.ProjectIndexSService;
import com.org.cygs.service.ProjectService;
import com.org.cygs.service.SystemParameterService;
import com.org.cygs.service.YUnitService;
import com.org.cygs.util.common.CygsConst;

@Controller
public class CopyMissionController {
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
	private ProjectIndexSService prIndexSService;
	@Autowired
	private ProjectIndexPCService prIndexPCervice;
	@Autowired
	private SystemParameterService  systemParameterService;
	
	// 复制计件
	@RequestMapping("preFZJJMission")
	public String preAddJJMission(Model model, HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		model.addAttribute("user", user);
		
		System.out.println("复制计件任务书");
		MissionType mt = missionTypeService.getMissionTypeByName("计件任务书");
		model.addAttribute("mtId", mt.getMtId());
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
			return "audit/listFZJJMission";
		}
		
		List<ProjectIndexS> prList = prIndexSService.selectSProject(user.getuId());
		model.addAttribute("prList", prList);
		
		if (prList != null && prList.size() > 0) {
			List<CheckUnit> cuList = checkUnitService.getAllCheckUnit();
			model.addAttribute("cuList", cuList);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("projectList", prList);
		}
		
		//////////如果上次上传的附件没有添加则删除它//////////////////
		File filedir=new File("D:/cyjz_file/test/file/" + user.getUserLoginName());
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
		
		return "audit/listFZJJMission";
	}
	
	
	@RequestMapping("toCopyMission")
	public String toCopyMission(HttpServletRequest request, RedirectAttributes attr, HttpSession session, Model model) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String mId = request.getParameter("mId");
		
		Mission m = missionService.getMissionInfoBymId(mId);
		//m.setmId("01256542322");
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<MissionContent> mc = missionContentService.getMissionContentByMId(mId);
		if (mc != null && mc.size() > 0) { // 添加任务书每条明细内容
			for (int i = 0; i < mc.size(); i++) {
				// mc.get(i).setMcId("028578531");
				// 查询已开工程量
				Float sumReal = missionContentService.selectRealQuantity(m.getPrId(), m.getCuId(), m.getPcPId());
				mc.get(i).setJjykl((double) (sumReal != null ? sumReal : 0));
				// 查询预控量
				List<OperationPriceVo> op1 = operationPriceService.queryOperationPriceUnit(m.getPrId(), m.getPcPId(), mc.get(i).getpId(), mc.get(i).getPsId(), mc.get(i).getoId(), m.getCuId());
				if (op1 != null && op1.size() > 0) {
					mc.get(i).setYkl(op1.get(0).getBudgetSum() != null ? op1.get(0).getBudgetSum() : 0);
					mc.get(i).setAccumulateSum(op1.get(0).getAccumulateSum()!= null ? new BigDecimal(op1.get(0).getAccumulateSum()) : new BigDecimal(0));
				}/* else {
					List<OperationPriceVo> op2 = operationPriceService.selectUnitPriceByOid(mc.get(i).getoId());
					if (op2 != null && op2.size() > 0) {
						mc.get(i).setYkl(op2.get(0).getBudgetSum() != null ? op2.get(0).getBudgetSum() : 0);
						mc.get(i).setAccumulateSum(new BigDecimal(op2.get(0).getAccumulateSum()));
					}
				}*/
				System.out.println("已结" + mc.get(i).getAccumulateSum());
			}
		}
		model.addAttribute("user", user);
		model.addAttribute("mission", m);
		model.addAttribute("mcList", mc);
		model.addAttribute("map", map);
		
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
		int submityear = syear;
		int submitmonth = smonth;
		model.addAttribute("submityear", submityear);
		model.addAttribute("submitmonth", submitmonth);
		
		MissionType mt = missionTypeService.getMissionTypeByName("计件任务书");
		model.addAttribute("mtid", mt.getMtId());
		model.addAttribute("mtcode", mt.getMtCode());
		
		return "audit/addFZJJMission";
	}
	
	// 查询栋号
	@RequestMapping("selectUnitNumber/{prId}")
	public @ResponseBody List<PrIndexPC> selectUnitNumber(@PathVariable("prId") String prId) {
		List<PrIndexPC> prList = prIndexPCervice.selectUnitByPrId(prId);
		return prList;
	}
	
}
