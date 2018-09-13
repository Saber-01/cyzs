package com.org.cygs.controller;

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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.org.cygs.pojo.MissionType;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.Role;
import com.org.cygs.service.MissionTypeService;
import com.org.cygs.util.common.StringUtil;

@Controller
@RequestMapping(value="/missionType")
public class MissionTypeController {
	@Autowired
	private MissionTypeService missionTypeService;
	
	@RequestMapping(value="/showMissionType")
	public String showMissionType(){
		return "listMissionType";
	}

	
	@RequestMapping(value="/selectMissionTypeList")
	@ResponseBody
	public PagePojo selectMissionTypeList(@RequestBody Map<String,String> map){
		int pageNo = Integer.parseInt(map.get("pageNo"));
		PagePojo<MissionType> mtList = this.missionTypeService.selectMissionTypeList(pageNo, 15);
		return mtList;
	}
	
	@RequestMapping(value="/selectMissionTypeList1")
	public @ResponseBody Map<String, Object> selectMissionTypeList1(@RequestParam Map<String, Object> map){
		
		return missionTypeService.getMissionTypeList(map);
	}
	
	@RequestMapping(value="/delete/{mtId}")
	public String missionDelete(@PathVariable String mtId){
		this.missionTypeService.missionDelete(mtId);
		return "redirect:/missionType/showMissionType";
	}
	
	@RequestMapping(value="/delete1/{mtId}")
	@ResponseBody
	public String missionDelete1(@PathVariable String mtId){
		this.missionTypeService.missionDelete(mtId);
		return "1";
	}
	
	@RequestMapping(value="/edit/{mtId}")
	public String missionGotoEdit(@PathVariable String mtId,Model model){

		MissionType mt = this.missionTypeService.getMissionTypeById(mtId);
		model.addAttribute("mt", mt);
		return "editMissionType";
	}
	@RequestMapping(value="/editAndSubmit/{mtId}")
	public String missionEdit(HttpServletRequest request,@PathVariable String mtId){
		String mtName = (String)request.getParameter("mtName");
		MissionType mt = new MissionType();
		mt.setMtId(mtId);
		mt.setMtName(mtName);
		//判断修改的任务书类型是否含  空数据，且是否未修改
		if(mtName == null || mtName=="" || mtName.length()>50){
			System.out.println("ERROR--->MissionTye 修改格式出错！！！");
		}else{
			List<MissionType> res = this.missionTypeService.judgeMissionType(mt);
			if(res.size()!=0){
				//修改时，查看任务书名称是否修改
				if(res.get(0).getMtName().equals(mtName)){
					System.out.println("ERROR--->MissionType未修改！！！");
				}else{
					this.missionTypeService.missionEdit(mt);
				}
			}else{
				this.missionTypeService.missionEdit(mt);
			}
		}
		return "redirect:/missionType/showMissionType";
	}
	
	@RequestMapping(value="/editAndSubmit1/{mtId}")
	@ResponseBody
	public String missionEdit1(HttpServletRequest request,@PathVariable String mtId){
		String mtName = (String)request.getParameter("mtName");
		MissionType mt = new MissionType();
		mt.setMtId(mtId);
		mt.setMtName(mtName);
		//判断修改的任务书类型是否含  空数据，且是否未修改
		if(mtName == null || mtName=="" || mtName.length()>50){
			System.out.println("ERROR--->MissionTye 修改格式出错！！！");
			return "format probelm!";
		}else{
			int mtCode = mt.getMtCode();
			mt.setMtCode(-1);
			List<MissionType> res = this.missionTypeService.judgeMissionType(mt);
			if(res.size()!=0){
				//修改时，查看任务书名称是否修改，只判断任务书名称，因为这是修改，而非新增！
					System.out.println("ERROR--->MissionType未修改！！！");
					return "modify duplicate!";
			}else{
				mt.setMtCode(mtCode);
				this.missionTypeService.missionEdit(mt);
				return "1";
			}
		}
	}
	@RequestMapping(value="/addTest")
	@ResponseBody
	public String addTest(String mtCode,String mtName){
		MissionType mt = new MissionType();
		mt.setMtCode(Integer.parseInt(mtCode));
		mt.setMtName(mtName);
		List<MissionType> res = this.missionTypeService.judgeMissionType(mt);
		if(res.size()>0){
			return "false";
		}else{
			return "true";
		}
	}
	@RequestMapping(value="/editTest")
	@ResponseBody
	public String editTest(String mtName){
		MissionType mt = new MissionType();
		mt.setMtCode(-1);
		mt.setMtName(mtName);
		List<MissionType> res = this.missionTypeService.judgeMissionType(mt);
		if(res.size()>0){
			return "false";
		}else{
			return "true";
		}
	}
	@RequestMapping(value="/addMission")
	public String missionAdd(HttpServletRequest request){
		String code = (String)request.getParameter("mtCode");
		String mtName = (String)request.getParameter("mtName");

		//判断插入的任务书类型是否为空，且是否重复，如编号、内容重复
		if(code == null || code=="" || mtName == null || mtName=="" || code.length()>2 || mtName.length()>50){
			//有一为空，则不能插入
			System.out.println("ERROR--->MissionType 新增数据为空或超出长度，不能插入！！！");
		}else{
			//判断是否重复
			int mtCode = Integer.parseInt(code);
			MissionType mt = new MissionType();
			mt.setMtCode(mtCode);
			mt.setMtName(mtName); 
			List<MissionType> res = this.missionTypeService.judgeMissionType(mt);
			if(res.size()!=0){
				//插入重复
				System.out.println("ERROR--->MssionType 插入数据重复，不能插入！！！");
			}else{
				this.missionTypeService.missionAdd(mt);
			}
		}
		return "redirect:showMissionType";
	}
	@RequestMapping(value="/addMissionType1")
	@ResponseBody
	public String missionTypeAdd1(MissionType mt){
		String code = String.valueOf(mt.getMtCode());
		String mtCode = StringUtil.addZeroToString(mt.getMtCode(), 4);
		mt.setMtCode(Integer.parseInt(mtCode));
		String mtName = mt.getMtName();
		//判断插入的任务书类型是否为空，且是否重复，如编号、内容重复
		if(code == null || code=="" || mtName == null || mtName=="" || code.length()>2 || mtName.length()>50){
			//有一为空，则不能插入
			System.out.println("ERROR--->MissionType 新增数据为空或超出长度，不能插入！！！");
			return "format probelm!";
		}else{
			//判断是否重复
			List<MissionType> res = this.missionTypeService.judgeMissionType(mt);
			if(res.size()!=0){
				//插入重复
				System.out.println("ERROR--->MssionType 插入数据重复，不能插入！！！");
				return "add duplicate!";
			}else{
				this.missionTypeService.missionAdd(mt);
				return "1";
			}
		}
	}
	@RequestMapping(value="/add")
	public String missionGotoAdd(){
		return "addMissionType";
	}
}
