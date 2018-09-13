package com.org.cygs.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.cygs.pojo.AuditInfo;
import com.org.cygs.pojo.AuditInfoDelete;
import com.org.cygs.pojo.JishiPrice;
import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.MissionContent;
import com.org.cygs.pojo.MissionDelete;
import com.org.cygs.pojo.MissionDeleteContent;
import com.org.cygs.pojo.MissionHistory;
import com.org.cygs.pojo.MissionHistoryContent;
import com.org.cygs.pojo.PartVo;
import com.org.cygs.pojo.StopAudit;
import com.org.cygs.pojo.SystemOperation;
import com.org.cygs.pojo.Unit;
import com.org.cygs.pojo.User;
import com.org.cygs.service.AuditInfoDeleteService;
import com.org.cygs.service.AuditInfoService;
import com.org.cygs.service.AuditRoleService;
import com.org.cygs.service.JishiPriceService;
import com.org.cygs.service.MissionContentService;
import com.org.cygs.service.MissionDeleteContentService;
import com.org.cygs.service.MissionDeleteService;
import com.org.cygs.service.MissionHistoryContentService;
import com.org.cygs.service.MissionHistoryService;
import com.org.cygs.service.MissionService;
import com.org.cygs.service.MissionTypeService;
import com.org.cygs.service.PartService;
import com.org.cygs.service.ProjectService;
import com.org.cygs.service.StopAuditService;
import com.org.cygs.service.SystemOperationService;
import com.org.cygs.service.YUnitService;
import com.org.cygs.util.common.CygsConst;
import com.org.cygs.util.common.StringUtil;

@Controller
public class MissionAuditController {
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
	private MissionHistoryService missionHistoryService;
	
	@Autowired
	private StopAuditService stopAuditService;
	
	@Autowired
	private MissionHistoryContentService missionHistoryContentService;
	
	@Autowired
	private YUnitService unitService;
	
	@Autowired
	private PartService partService;
	
	@Autowired
	private MissionDeleteService missionDeleteService;
	
	@Autowired
	private MissionDeleteContentService missionDeleteContentService;
	
	@Autowired
	private AuditInfoDeleteService auditInfoDeleteService;
	
	@Autowired
	private SystemOperationService systemOperationService;
	
	@Autowired
	private JishiPriceService jishiPriceService;
	
	// 查看任务书详细信息//审核任务书显示任务书信息
	@RequestMapping("/viewAndToauditMission")
	public String viewMission(String flag, String mId, Model model,HttpServletRequest request, HttpSession session) {
		System.out.println(flag);
		List<MissionContent> missionContentList;
		Mission mission = missionService.getMissionByMId(mId);
		String mtName = missionTypeService.getMissionTypeById(mission.getMtId()).getMtName();
		if (mtName.equals("合同内其他任务书")) {
			missionContentList = missionContentService.getHTNQTMissionContentByMId(mId);
		} else {
			missionContentList = missionContentService.getMissionContentByMId(mId);
		}
		List<AuditInfo> auditInfoList = auditInfoService.getAuditInfoByMId(mId);
		System.out.println(missionContentList.size());
		ObjectMapper mapper = new ObjectMapper();
		try {
			String a = mapper.writeValueAsString(missionContentList);
			String b = mapper.writeValueAsString(auditInfoList);
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
		File file = new File("D:/cyjz_file/test/file", mId);
		if (file.exists()) {
			if (file.isDirectory()) {
				if (file.listFiles().length != 0)
					model.addAttribute("file", "有附件");
				else
					model.addAttribute("file", "无附件");
			}
		} else
			model.addAttribute("file", "无附件");

		// flag = 0 表示查看任务书
		if (flag.equals("0")) {

			model.addAttribute("viewDel", "0");
			if (mt_code == 1) {
				return "audit/viewJJMissionInfo";
			}
			if (mt_code == 3) {
				return "audit/viewJSMissionInfo";
			}
			if (mt_code == 4) {
				return "audit/viewBGQZMissionInfo";
			}
			if (mt_code == 5) {
				return "audit/viewHTWBCJMissionInfo";
			} else {
				return "audit/viewHTNQTMissionInfo";
			}
		}
		// flag = 1 表示审核任务书
		else {
			List<MissionHistory> mhList = missionHistoryService.getMHListByMId(mId);
			model.addAttribute("mhList", mhList);
			User user = (User) session.getAttribute(CygsConst.USER_SESSION);
			model.addAttribute("user", user);
			if (mt_code == 1) {
				System.out.println(missionContentList.size());
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("prId", mission.getPrId());
				map.put("cuId", mission.getCuId());
				map.put("pcPId", mission.getPcPId());
				map.put("mtId", mission.getMtId());
				for (int i = 0; i < missionContentList.size(); i++) {
					MissionContent mc = missionContentList.get(i);
					map.put("unId", mc.getUnId());
					map.put("pId", mc.getpId());
					map.put("psId", mc.getPsId());
					map.put("oId", mc.getoId());
					Double price = mc.getPrice().doubleValue();
					String bc;
					try {
						bc = mapper.writeValueAsString(map);
						System.out.println("map" + bc);
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
					// float f = missionContentService.queryaccQuantity(map);
					// System.out.println("已结量"+f);
					// BigDecimal b = new BigDecimal(String.valueOf(f));
					Double Yjl = missionContentService.queryYjaccQuantity(map);
					if (Yjl != null) {
						System.out.println("已结量" + Yjl);
						Double YjlSum = Yjl * price;
						missionContentList.get(i).setYjl(Yjl);
						missionContentList.get(i).setYjlSum(YjlSum);
					}
					Double Ljgcl = missionContentService.queryLjaccQuantity(map);
					if (Ljgcl != null) {
						Double LjgclSum = Ljgcl * price;
						missionContentList.get(i).setLjgcl(Ljgcl);
						missionContentList.get(i).setLjgclSum(LjgclSum);
					}

				}
				return "audit/auditJJMission";
			}
			if (mt_code == 3) {
				return "audit/auditJSMission";
			}
			if (mt_code == 4) {
				return "audit/auditBGQZMission";
			}
			if (mt_code == 5) {
				return "audit/auditHTWBCJMission";
			} else {
				return "audit/auditHTNQTMission";
			}
		}

	}
   
	
	// 查看历史任务书详细信息//
	@RequestMapping("/viewHMission")
	public String viewHMission(String mhId, Model model) {
		List<MissionHistoryContent> missionContentList;
		MissionHistory mission = missionHistoryService.getMHByMhId(mhId);
		if (mission.getMtName().equals("合同内其他任务书")) {
			missionContentList = missionHistoryContentService.getHTNQTMHCList(mhId);
		} else {
			missionContentList = missionHistoryContentService.getMHCList(mhId);
		}
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
		int mt_code = mission.getMtCode();

		if (mt_code == 1) {
			return "audit/viewJJHMissionInfo";
		}
		if (mt_code == 3) {
			return "audit/viewJSHMissionInfo";
		}
		if (mt_code == 4) {
			return "audit/viewBGQZHMissionInfo";
		}
		if (mt_code == 5) {
			return "audit/viewHTWBCJHMissionInfo";
		} else {
			return "audit/viewHTNQTHMissionInfo";
		}
	}
   
	// 审核任务书
	@RequestMapping("/auditMission")
	public @ResponseBody String auditMission1(@RequestBody Map<String, Object> map, HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String roId = user.getRoId();
		String uId = user.getuId();
		String roleName = user.getRoleName();
		String mId = map.get("mId").toString();
		String auditResult = map.get("auditResult").toString();
		String auditComment = map.get("auditComment").toString();
		String auditPersonal = map.get("auditPersonal").toString();	// 审核隐私
		System.out.println("renwushu" + mId);
		System.out.println(auditResult);
		System.out.println("pingj" + auditComment);

		Mission mission = missionService.getMissionInfoBymId(mId);
		stopAuditService.deleteStopAudit(mId, uId);
		String mtId = mission.getMtId();
		if (auditResult.equals("1")) { // 审核通过
			// 最大审核步骤
			int maxStep = auditRoleService.getMaxStepByMtId(mtId);
			// 当前角色的审核步骤
			int cexStep = auditRoleService.getCexStepByMtIdAndRoId(mtId, roId);
			if (maxStep == cexStep) {
				mission.setStatus(100);
			} else {
				mission.setStatus(cexStep);
				System.out.println("审核状态" + mission.getStatus());
			}
			if (roleName.equals("成控部经理")) {
				mission.setCkPass(53);
			}
			if (roleName.equals("主管预算")) {
				mission.setYsPass(51);
			}

		} else if (auditResult.equals("0")) {
			// 审核不通过保存到历史记录表
			int curStat = mission.getStatus();
			mission.setStatus(curStat - 1);
			if (roleName.equals("成控部经理")) {
				mission.setCkBack(52);
			}
			if (roleName.equals("主管预算")) {
				mission.setYsBack(50);
			}
			missionHistoryService.addMissionHistory(mission);
			String mhId = missionHistoryService.getMaxMhIdBymId(mId);
			List<MissionContent> missionContentList = missionContentService.getMissionContentInfoByMId(mId);
			System.out.println("内容个数" + missionContentList.size());
			if (missionContentList != null && missionContentList.size() > 0) {
				for (int i = 0; i < missionContentList.size(); i++) {
					MissionContent mc = new MissionContent();
					mc = missionContentList.get(i);

					MissionHistoryContent mhc = new MissionHistoryContent();
					mhc.setMcId(mc.getMcId());
					mhc.setMcCode(mc.getMcCode());
					mhc.setPrice(mc.getPrice());
					mhc.setRealQuantity(mc.getRealQuantity());
					mhc.setWageSum(mc.getWageSum());
					mhc.setAccumulateSum(mc.getAccumulateSum());
					mhc.setFloor(mc.getFloor());
					mhc.setoId(mc.getoId());
					mhc.setUnId(mc.getUnId());
					mhc.setRemark(mc.getRemark());
					mhc.setMhId(mhId);
					mhc.setmId(mId);
					mhc.setpId(mc.getpId());
					mhc.setPsId(mc.getPsId());

					missionHistoryContentService.addMissionHitoryContent(mhc);
				}
			}
		} else {
			// 暂不处理
			StopAudit sa = new StopAudit();
			sa.setmId(mId);
			sa.setuId(uId);
			sa.setRoId(roId);
			sa.setRemark(auditComment);
			stopAuditService.addStopAudit(sa);
		}
		Date date = new Date();
		if (!auditResult.equals("2")) {
			// 修改任务书信息
			String auditComment1 = auditComment + "【审核通过】";
			String auditComment2 = auditComment + "【退修】";
			mission.setRemark(auditResult.equals("1") ? auditComment1 : auditComment2);
			mission.setRemark2("1");
			mission.setLastAuditTime(new java.sql.Timestamp(date.getTime()));
			missionService.updateMissionStatus(mission);
			// 添加审核记录信息
			AuditInfo ai = new AuditInfo();
			ai.setmId(mId);
			ai.setuId(uId);
			ai.setRoId(roId);
			ai.setAuditComment(auditResult.equals("1") ? auditComment1 : auditComment2);
			ai.setAuditPersonal(auditPersonal);
			ai.setAuditResult(Integer.valueOf(auditResult));
			ai.setAuditTime(new java.sql.Timestamp(date.getTime()));
			auditInfoService.insertAuditInfo(ai);
		} else {
			mission.setRemark(auditComment + "【暂不处理】");
			mission.setRemark2("0");
			mission.setLastAuditTime(new java.sql.Timestamp(date.getTime()));
			missionService.updateMissionStatus(mission);
			// 添加审核记录信息
			AuditInfo ai = new AuditInfo();
			ai.setmId(mId);
			ai.setuId(uId);
			ai.setRoId(roId);
			ai.setAuditComment(auditComment + "【暂不处理】");
			ai.setAuditPersonal(auditPersonal);
			ai.setAuditResult(Integer.valueOf(auditResult));
			ai.setAuditTime(new java.sql.Timestamp(date.getTime()));
			auditInfoService.insertAuditInfo(ai);
		}
		return "1";
	}
	
	// 批量审核任务书
	@RequestMapping("/auditMissionPL")
	public String auditMissionPL(HttpServletRequest request, Model model, HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String roId = user.getRoId();
		String uId = user.getuId();
		String roleName = user.getRoleName();

		String[] mIdArray = request.getParameterValues("checkboxname");
		String auditResult = String.valueOf(request.getParameter("auditResult"));
		String auditComment = String.valueOf(request.getParameter("auditComment"));

		System.out.println(mIdArray[0]);
		System.out.println("评价结果" + auditResult);
		System.out.println("评价" + auditComment);
		for (int k = 0; k < mIdArray.length; k++) {
			// 遍历所有被复选框选中的mId
			String mId = mIdArray[k];
			Mission mission = missionService.getMissionInfoBymId(mId);
			stopAuditService.deleteStopAudit(mId, user.getuId());

			String mtId = mission.getMtId();
			if (auditResult.equals("1")) {
				// 审核通过
				// 当前任务书最大审核步骤
				int maxStep = auditRoleService.getMaxStepByMtId(mtId);
				// 当前角色的审核步骤
				int cexStep = auditRoleService.getCexStepByMtIdAndRoId(mtId, roId);
				if (maxStep == cexStep) {
					System.out.println("批量审核：最后一步--状态100");
					mission.setStatus(100);
				} else {
					mission.setStatus(cexStep);
					System.out.println("审核状态" + mission.getStatus());
				}
				if (roleName.equals("成控部副经理")) {
					// mission.set
				}
			} else if (auditResult.equals("0")) {
				// 审核不通过保存到历史记录表
				int curStat = mission.getStatus();
				mission.setStatus(curStat - 1);
				missionHistoryService.addMissionHistory(mission);
				String mhId = missionHistoryService.getMaxMhIdBymId(mId);
				List<MissionContent> missionContentList = missionContentService.getMissionContentInfoByMId(mId);
				if (missionContentList != null && missionContentList.size() > 0) {
					for (int i = 0; i < missionContentList.size(); i++) {
						MissionContent mc = new MissionContent();
						mc = missionContentList.get(i);
						MissionHistoryContent mhc = new MissionHistoryContent();
						mhc.setMcId(mc.getMcId());
						mhc.setMcCode(mc.getMcCode());
						mhc.setPrice(mc.getPrice());
						mhc.setRealQuantity(mc.getRealQuantity());
						mhc.setWageSum(mc.getWageSum());
						mhc.setAccumulateSum(mc.getAccumulateSum());
						mhc.setFloor(mc.getFloor());
						mhc.setoId(mc.getoId());
						mhc.setUnId(mc.getUnId());
						mhc.setRemark(mc.getRemark());
						mhc.setMhId(mhId);
						mhc.setmId(mId);
						mhc.setpId(mc.getpId());
						mhc.setPsId(mc.getPsId());
						missionHistoryContentService.addMissionHitoryContent(mhc);
					}
				}
			} else {
				// 暂不处理
				StopAudit sa = new StopAudit();
				sa.setmId(mId);
				sa.setuId(uId);
				sa.setRemark(auditComment);
				stopAuditService.addStopAudit(sa);
			}
			Date date = new Date();
			if (!auditResult.equals("2")) {
				// 修改任务书信息
				String auditComment1 = auditComment + "【审核通过】";
				String auditComment2 = auditComment + "【退修】";
				mission.setRemark(auditResult.equals("1") ? auditComment1 : auditComment2);
				mission.setRemark2("1");
				mission.setLastAuditTime(new java.sql.Timestamp(date.getTime()));
				missionService.updateMissionStatus(mission);
				// 添加审核记录信息
				AuditInfo ai = new AuditInfo();
				ai.setmId(mId);
				ai.setuId(uId);
				ai.setRoId(roId);
				ai.setAuditComment(auditResult.equals("1") ? auditComment1 : auditComment2);
				ai.setAuditResult(Integer.valueOf(auditResult));
				ai.setAuditTime(new java.sql.Timestamp(date.getTime()));
				auditInfoService.insertAuditInfo(ai);
			} else {
				mission.setRemark(auditComment + "【暂不处理】");
				mission.setRemark2("0");
				mission.setLastAuditTime(new java.sql.Timestamp(date.getTime()));
				missionService.updateMissionStatus(mission);
				// 添加审核记录信息
				AuditInfo ai = new AuditInfo();
				ai.setmId(mId);
				ai.setuId(uId);
				ai.setRoId(roId);
				ai.setAuditComment(auditComment + "【暂不处理】");
				ai.setAuditResult(Integer.valueOf(auditResult));
				ai.setAuditTime(new java.sql.Timestamp(date.getTime()));
				auditInfoService.insertAuditInfo(ai);
			}
		}
		return "redirect:/missionToAuditList";
	}
	
	
	// 显示修改任务书界面
	@RequestMapping("/toEditMission")
	public String toEditMission(String mId, String mtName, Model model) {
		List<MissionContent> missionContentList;
		if (mtName.equals("合同内其他任务书")) {	// 没有单位信息
			missionContentList = missionContentService.getMCByMId(mId);
		} else {
			missionContentList = missionContentService.getMissionContentByMId(mId);
		}
		Mission mission = missionService.getMissionByMId(mId);
		List<AuditInfo> auditInfoList = auditInfoService.getAuditInfoByMId(mId);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String a = mapper.writeValueAsString(missionContentList);
			String b = mapper.writeValueAsString(mission);
			System.out.println(a);
			System.out.println(b);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		if (missionContentList != null && missionContentList.size() > 0) {
			model.addAttribute("missionContentList", missionContentList);
		}
		
		model.addAttribute("mission", mission);
		if (auditInfoList != null && auditInfoList.size() > 0) {
			model.addAttribute("auditInfoList", auditInfoList.get(0).getAuditComment());
		} else {
			model.addAttribute("auditInfoList", "无");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("beginDate", sdf.format(mission.getBeginDate()));
		model.addAttribute("endDate", sdf.format(mission.getEndDate()));
		// 有无附件
		File file = new File("D:/cyjz_file/test/file", mId);
		if (file.exists()) {
			if (file.isDirectory()) {
				if (file.listFiles().length != 0)
					model.addAttribute("file", "有附件");
				else
					model.addAttribute("file", "无附件");
			}
		} else
			model.addAttribute("file", "无附件");
		int mt_code = mission.getMtCode();
		if (mt_code == 1) {
			return "audit/editJJMission";
		}
		if (mt_code == 3) {
			return "audit/editJSMission";
		}
		if (mt_code == 4) {
			return "audit/editBGQZMission";
		}
		if (mt_code == 5) {
			return "audit/eidtHTWBCJMission";
		} else {
			return "audit/editHTNQTMission";
		}
	}
	
	// 审核变更签证任务书时，可以修改资料返回状态
	@RequestMapping("editMissionContentIsReturn/{mcId}/{isReturn}")
	public @ResponseBody String editMissionContent(HttpServletRequest request, @PathVariable(value = "mcId") String mcId, @PathVariable(value = "isReturn") String isReturn, Model model) {
		MissionContent mc = new MissionContent();
		mc.setMcId(mcId);
		mc.setIsreturn(isReturn);
		missionContentService.updateMissionContent(mc);
		
		return "1";
	}
	
	// 审核计件任务书时，可以修改本月实际完成工程量
	@RequestMapping("editMissionContentRQ/{mcId}")
	public @ResponseBody String editMissionContentRQ(HttpServletRequest request, @PathVariable(value = "mcId") String mcId, Model model) {
		String realQuantity = request.getParameter("realQuantity");
		String wageSum = request.getParameter("wageSum");
		String remark = request.getParameter("remark");
		
		MissionContent mc = new MissionContent();
		mc.setMcId(mcId);
		mc.setRealQuantity(new BigDecimal(Float.parseFloat(realQuantity)));
		mc.setWageSum(new BigDecimal(Float.parseFloat(wageSum)));
		mc.setRemark(remark);
		missionContentService.updateMissionContent(mc);
		
		return "1";
	}
	
	@RequestMapping("preEditMission")
	public String preEditMission(HttpServletRequest request, Model model) {
		
		String index = request.getParameter("index");
		String prId = request.getParameter("prId");
		String prName = request.getParameter("prName");
		String cuId = request.getParameter("cuId");
		String cuName = request.getParameter("cuName");
		String pcpId = request.getParameter("pcpId");
		String unitNumber = request.getParameter("unitNumber");
		String mtCode = request.getParameter("mtCode");
		String partId = request.getParameter("partId");
		String psId = request.getParameter("psId");
		String oId = request.getParameter("oId");
		String floor = request.getParameter("floor");
		String realQuantity = request.getParameter("realQuantity");
		String remark = request.getParameter("remark");
		String isReturn = request.getParameter("isReturn");
		String price = request.getParameter("price");
		String unitId = request.getParameter("unitId");
		String wageSum = request.getParameter("wageSum");
		String accQuantity = request.getParameter("accQuantity");
		List<Unit> unit = unitService.getAllUnit();
		Unit workday = unitService.getUnitByName("工日");
		model.addAttribute("workday", workday);
		model.addAttribute("units", unit);
		List<PartVo> partList = partService.getPartInfo(prId, pcpId, cuId);
		// model.addAttribute("parts", partList);
		System.out.println("mtCode = " + mtCode);
		if (mtCode.equals("3")) {
			//int jishi = 0;
			for (int i = 0; i < partList.size(); i++) {
				if (partList.get(i).getpName().equals("计时工")) {
					model.addAttribute("pId", partList.get(i).getpId());
					model.addAttribute("pName", partList.get(i).getpName());
					model.addAttribute("js", "no");
					//jishi = 1;
					System.out.println("part = " + partList.get(i).getpName());
					break;
				}
			}
			/*if (jishi == 0) {	// 计时单价表里面有分部计时工，而查询的分部中没有
				List<JishiPrice> jsList = jishiPriceService.selectJishiUnitPrice(prId, cuId, pcpId);
				if (jsList != null && jsList.size() > 0) {
					model.addAttribute("pId", jsList.get(0).getpId());
					model.addAttribute("pName", jsList.get(0).getpName());
					System.out.println("jspart = " + jsList.size());
					model.addAttribute("js", "no");
					model.addAttribute("jsdj", "dj");
				}
			}*/
		} else if (mtCode.equals("4") || mtCode.equals("5") || mtCode.equals("7")) {
			for (int i = 0; i < partList.size(); i++) {
				if (partList.get(i).getpName().equals("其它")) {
					model.addAttribute("pId", partList.get(i).getpId());
					model.addAttribute("pName", partList.get(i).getpName());
					System.out.println("part = " + partList.get(i).getpName());
					model.addAttribute("other", "no");
					break;
				}
			}
			if (mtCode.equals("4")) {
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
		} else {
			model.addAttribute("parts", partList);
		}
		model.addAttribute("index", index);
		model.addAttribute("prId", prId);
		model.addAttribute("prName", prName);
		model.addAttribute("cuId", cuId);
		model.addAttribute("cuName", cuName);
		model.addAttribute("pcpId", pcpId);
		model.addAttribute("unitNumber", unitNumber);
		model.addAttribute("mtCode", mtCode);
		model.addAttribute("partId", partId);
		model.addAttribute("psId", psId);
		model.addAttribute("operationId", oId);
		model.addAttribute("floor", floor);
		model.addAttribute("realQuantity", realQuantity);
		model.addAttribute("remark", remark);
		model.addAttribute("isReturn", isReturn);
		model.addAttribute("price", price);
		model.addAttribute("unitId", unitId);
		model.addAttribute("wageSum", wageSum);
		model.addAttribute("accQuantity", accQuantity);
		
		System.out.println(floor);
		return "audit/preEditMission";
	}
	
	
	@RequestMapping("editMission")
	public String editMission(HttpServletRequest request, HttpSession session) throws Exception {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String mId = request.getParameter("mId");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 跟新任务书信息
		Mission mission = missionService.getMissionInfoBymId(mId);
		if (beginDate != null && endDate != null) {
			mission.setBeginDate(new java.sql.Timestamp(sdf.parse(beginDate + " 00:00:00").getTime()));
			mission.setEndDate(new java.sql.Timestamp(sdf.parse(endDate + " 00:00:00").getTime()));
		}
		mission.setSubmitTime(new java.sql.Timestamp(new Date().getTime()));
		mission.setStatus(0);	// 设置提交人信息
		mission.setEdtionId(mission.getEdtionId() + 1);	// 设置版本号, 初始为0, 每次修改加一
		mission.setRecorderId(user.getuId());
		mission.setSupervisor(user.getUserName());
		missionService.updateMission(mission);
		
		missionContentService.delMissionContentBymId(mId);
		
		String[] oids = request.getParameterValues("oId");
		String[] contentSerials = request.getParameterValues("mcCode");
		String[] partIds = request.getParameterValues("pId");
		String[] psIds = request.getParameterValues("psId");
		String[] floors = request.getParameterValues("floor");
		String[] units = request.getParameterValues("unId");
		String[] prices = request.getParameterValues("price");
		String[] realQuantitys = request.getParameterValues("realQuantity");
		String[] wageSums = request.getParameterValues("wageSum");
		String[] accQuantitys = request.getParameterValues("accQuantity");
		String[] remarks = request.getParameterValues("remark");
		String[] isReturns = request.getParameterValues("isReturn");
		if (realQuantitys != null && realQuantitys.length > 0) { // 添加任务书每条明细内容
			for (int i = 0; i < realQuantitys.length; i++) {
			if (realQuantitys[i] != "" && !realQuantitys[i].equals("0")) {
				int contentSerial = Integer.valueOf(contentSerials[i]);
				String operationId = oids[i];
				String partId = partIds[i];
				String psId = psIds[i];
				String floor = null;
				String unit = null;
				String isReturn = null;
				if (floors != null) 
					floor = StringUtil.trans(floors[i]);
				if (units != null)
					unit = units[i];
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
				
				System.out.println("任务书" + mId);
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
				// mc.setMcId("00004444444441");
				mc.setMcCode(contentSerial);
				mc.setmId(mId);
				mc.setpId(partId);
				mc.setPsId(psId);
				mc.setoId(operationId);
				mc.setFloor(floor);
				mc.setUnId(unit);
				mc.setIsreturn(isReturn);
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
				mc.setRemark(remark);
				mc.setInnerId("");
				missionContentService.insertMissionContent(mc);
				}
			}
			String auditComment = request.getParameter("auditComment");
			// 插入审核记录
			AuditInfo ai = new AuditInfo();
			// ai.setaId("000033333");
			ai.setmId(mId);
			ai.setAuditResult(-1);
			if (auditComment != null) {
				ai.setAuditComment(auditComment + "【修改】");
			} else {
				ai.setAuditComment("【修改】");
			}
			ai.setAuditTime(new java.sql.Timestamp(new Date().getTime()));
			ai.setuId(user.getuId());
			ai.setRoId(user.getRoId());
			auditInfoService.insertAuditInfo(ai);
		}
		return "redirect:missionSubmitList";
	}
	
	// 删除任务书---同时删除内容表、审核表，并将删除的信息存入相应的delete表
	@RequestMapping(value="delMission/{mId}/{prName}")
	public @ResponseBody String delMission(@PathVariable(value = "mId") String mId, @PathVariable(value = "prName") String prName, 
			HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException, ServletException, IOException {
		if(mId != null){ 
			Mission mission = missionService.getMissionInfoBymId(mId);
			System.out.println(prName);
			if (mission != null) {
				// 将任务书加入delete表
				MissionDelete mdDelete = new MissionDelete();
				// mdDelete.setMdId("002454311001");
				mdDelete.setMission(mission);
				missionDeleteService.insertMissionDelete(mdDelete);
				
				String mdId = missionDeleteService.getMdMCode(mission.getmCode());	// 多人操作时会不会有影响?
				
				// 将删除操作存入操作记录表
				SystemOperation so = new SystemOperation();
				// so.setSoId("0002124441111001");
				so.setmCode(mission.getmCode());
				so.setPrName(prName);
				so.setUserName(mission.getSupervisor());
				// 设置remark为审核表的comment
				List<AuditInfo> commentList = auditInfoService.getAuditInfoByMId(mId);
				String comment = "";
				if (commentList != null && commentList.size() > 0) {
					SimpleDateFormat sf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", new Locale("ENGLISH", "CHINA"));	// 取出来是GMT+格式
					for (int i = 0; i < commentList.size(); i++) {
						Date mydate = sf.parse(commentList.get(i).getAuditTime().toString());
						sf.applyPattern("EEE MMM dd HH:mm:ss Z yyyy");
				        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("CHINESE", "CHINA"));
				        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
				        String time = sdf.format(mydate);
				        System.out.println(time);
						comment += "[" + i + "]" + "[" + time + "]" + commentList.get(i).getAuditComment() + ";";
						// 将审核信息插入delete表
						AuditInfoDelete ad = new AuditInfoDelete();
						// ad.setaIDd("01234540001");
						ad.setMdId(mdId);
						ad.setAuditInfo(commentList.get(i));
						auditInfoDeleteService.insertAuditInfoDelete(ad);
					}
				}
				so.setOperationTime(new java.sql.Timestamp(new Date().getTime()));
				so.setRemark1(comment);
				so.setOperationType("删除");
				systemOperationService.insertSysOperation(so);
				
				// 删除暂不处理任务书
				stopAuditService.deleteStopAuditByMid(mId);
				
				// 将任务书内容存入delete表
				List<MissionContent> mcList = missionContentService.getMissionContentInfoByMId(mId);
				if (mcList != null && mcList.size() > 0) {
					for (int i = 0; i < mcList.size(); i++) {
						MissionDeleteContent mcd = new MissionDeleteContent();
						// mcd.setMcdId("0012346660211");
						mcd.setMdId(mdId);
						mcd.setMissionContent(mcList.get(i));
						missionDeleteContentService.insertMissionDeleteContent(mcd);
					}	
				}
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
			return "1";
		} else {
			return "0";
		}
	}

}
