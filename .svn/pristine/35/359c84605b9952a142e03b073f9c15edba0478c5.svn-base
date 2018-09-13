package com.org.cygs.controller;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.cygs.pojo.AuditInfo;
import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.MissionContent;
import com.org.cygs.pojo.MissionType;
import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.Project;
import com.org.cygs.pojo.User;
import com.org.cygs.service.AuditInfoDeleteService;
import com.org.cygs.service.AuditInfoService;
import com.org.cygs.service.MissionContentService;
import com.org.cygs.service.MissionService;
import com.org.cygs.service.MissionTypeService;
import com.org.cygs.util.common.CygsConst;

@Controller
public class MissionDeleteController {
	@Autowired
	private MissionService missionService;
	
	@Autowired
	private MissionTypeService missionTypeService;
	
	@Autowired
	private MissionContentService missionContentService;
	
	@Autowired
	private AuditInfoDeleteService auditInfoDeleteService;
	
	@Autowired
	private AuditInfoService auditInfoService;
	
	//恢复已删除任务书
	@RequestMapping("/missionRecov")
	public @ResponseBody String missionRecov(@RequestBody String mId){
		if(mId != null){
			Mission msn = missionService.getDelMissionBymId(mId);
			msn.setmId(null);
			missionService.insertMission(msn);
			String newmId = missionService.getMIdByMcode(msn.getmCode());
			
			List<MissionContent> mcList = missionContentService.getDMissionContentInfoByMId(mId);
			List<AuditInfo> auditInfoList = auditInfoService.getDAuditInfoByMId(mId);
			if(mcList != null && mcList.size() > 0){
				for(int i=0; i < mcList.size(); i++){
					MissionContent mc = new MissionContent();
					mc = mcList.get(i);
					//mc.setMcId(null);
					mc.setmId(newmId);
					mc.setMcCode(mcList.get(i).getMcCode());
					mc.setPrice(mcList.get(i).getPrice());
					mc.setRealQuantity(mcList.get(i).getRealQuantity());
					mc.setAccumulateSum(mcList.get(i).getAccumulateSum());
					mc.setWageSum(mcList.get(i).getWageSum());
					mc.setFloor(mcList.get(i).getFloor());
					mc.setoId(mcList.get(i).getoId());
					mc.setUnId(mcList.get(i).getUnId());
					mc.setRemark(mcList.get(i).getRemark());
					mc.setPsId(mcList.get(i).getPsId());
					mc.setpId(mcList.get(i).getpId());
					mc.setIsreturn(mcList.get(i).getIsreturn());
					mc.setInnerId("");	//innerId--not null--忘了加这个一直没法插入任务书内容
					
					ObjectMapper mapper = new ObjectMapper();
					try {
						String a = mapper.writeValueAsString(mc);
						System.out.println("恢复内容"+ mcList.size() + a);
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
					missionContentService.insertMissionContent(mc);
				}
			}
			if(auditInfoList != null && auditInfoList.size() > 0){
				for(int i=0; i < auditInfoList.size(); i++){
					AuditInfo ai = new AuditInfo();
					ai = auditInfoList.get(i);
					ai.setaId(null);
					ai.setmId(newmId);
					//ai.setmId(mId);
					ObjectMapper mapper = new ObjectMapper();
					try {
						String b = mapper.writeValueAsString(ai);
						System.out.println("恢复审核信息"+ auditInfoList.size() + b);
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
					auditInfoService.insertAuditInfo(ai);
				}
			}
			missionContentService.delDMissionContentBymId(mId);
			auditInfoDeleteService.delAuditInfoDelete(mId);
			missionService.delDMissionById(mId);
			return "1";
		}else{
			return "0";
		}
       	
	}
	
	//查看已删除任务书详细信息
	@RequestMapping("/viewDelMission")  
	public String viewDelMission(String mId,Model model) {
		
		List<MissionContent> missionContentList;
		Mission mission = missionService.getDMissionInfoByMId(mId);
		String mtName = missionTypeService.getMissionTypeById(mission.getMtId()).getMtName();
		if (mtName.equals("合同内其他任务书")) {
			missionContentList= missionContentService.getDelHTNQTMissionContentByMId(mId);
		} else {
			missionContentList= missionContentService.getDMissionContentByMId(mId);
		}
		//System.out.println("已删除审核状态：" + mission.getMissionStatus());
		List<AuditInfo> auditInfoList= auditInfoService.getDAuditByMId(mId);
		model.addAttribute("missionContentList", missionContentList);
		model.addAttribute("mission", mission);
		model.addAttribute("viewDel", "1");
		model.addAttribute("auditInfoList", auditInfoList);
		int mt_code = mission.getMtCode();
		// 有无附件
		File file=new File("D:/cyjz_file/test/file", mId);
		if(file.exists())
		{
			if(file.isDirectory())
			{
				if(file.listFiles().length!=0)
					model.addAttribute("file", "有附件");
				else
					model.addAttribute("file", "无附件");
			}
		}
		else{
			model.addAttribute("file", "无附件");
		}

		if(mt_code == 1){
			  return "audit/viewJJMissionInfo";
			}
		if(mt_code == 3){
			  return "audit/viewJSMissionInfo";
			}
		if(mt_code == 4){
			  return "audit/viewBGQZMissionInfo";
			}
		if(mt_code == 5){
			  return "audit/viewHTWBCJMissionInfo";
			}
		else{
			  return "audit/viewHTNQTMissionInfo";
			}
	}
	  	  
}
