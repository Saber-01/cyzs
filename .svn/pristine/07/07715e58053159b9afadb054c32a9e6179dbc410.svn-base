package com.org.cygs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.org.cygs.pojo.CheckUnit;
import com.org.cygs.pojo.MissionType;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.service.CheckUnitService;
import com.org.cygs.util.common.StringUtil;

@Controller
@RequestMapping(value="/checkUnit")
public class CheckUnitController {

	@Autowired
	private CheckUnitService checkUnitService;
	
	@RequestMapping(value="/showCheckUnit")
	public String showCheckUnit(){
		return "listCheckUnit";
	}
	
	@RequestMapping(value="/selectCheckUnitList")
	@ResponseBody
	public PagePojo selectCheckUnitList(@RequestBody Map<String,String> map){
		int pageNo = Integer.parseInt(map.get("pageNo"));
		String cuName = map.get("cuName").toString();
		PagePojo<CheckUnit> cuList = this.checkUnitService.selectCheckUnitListByName(pageNo, 15, cuName);
		return cuList;
	}

	@RequestMapping(value="/selectCheckUnitList1")
    public @ResponseBody Map<String, Object> selectCheckUnitList1(@RequestParam Map<String, Object> map) {
        return checkUnitService.getCheckUnitList(map);
    }	
	
	@RequestMapping(value="/delete/{cuId}")
	public String checkUnitDelete(@PathVariable String cuId){
		this.checkUnitService.checkUnitDelete(cuId);
		return "redirect:/checkUnit/showCheckUnit";
	}
	
	@RequestMapping(value="/delete1")
	@ResponseBody
	public String checkUnitDelete1(@RequestBody String cuId){
		this.checkUnitService.checkUnitDelete(cuId);
		return "1";
	}
	@RequestMapping(value="/addTest")
	@ResponseBody
	public String addTest(String cuName){
		CheckUnit cu = new CheckUnit();
		cu.setCuName(cuName);
		List<CheckUnit> res = this.checkUnitService.judgeCheckUnit(cu);
		if(res.size()>0){
			return "false";
		}else{
			return "true";
		}
	}
	@RequestMapping(value="/editTest")
	@ResponseBody
	public String editTest(String cuName,String cuId){
		CheckUnit cu = new CheckUnit();
		cu.setCuName(cuName);
		List<CheckUnit> res = this.checkUnitService.judgeCheckUnit(cu);
		if(res.size()>0){
			if(res.get(0).getCuId().equals(cuId)){
				return "true";
			}else{
				return "false";
			}
		}else{
			return "true";
		}
	}
	@RequestMapping(value="/add")
	public ModelAndView checkUnitAdd(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("addCheckUnit");
		return mv;
	}
	
	@RequestMapping(value="/addAndSubmit")
	public String checkUnitAddAndSubmit(HttpServletRequest request){
		String cuCode = (String)request.getParameter("cuCode");
		String cuName = (String)request.getParameter("cuName");
		//对输入的数据进行判断：重复、空值、超出长度等
		if(cuCode==null || cuCode=="" || cuName==null || cuName=="" || cuCode.length()>4 || cuCode.length()>50){
			System.out.println("ERROR--->CheckUnit 插入为空或超出长度！！！");
		}else{
			CheckUnit checkUnit = new CheckUnit();
			checkUnit.setCuCode(cuCode);
			checkUnit.setCuName(cuName);
			List<CheckUnit> res = this.checkUnitService.judgeCheckUnit(checkUnit);
			if(res.size()!=0){
				System.out.println("ERROR--->CheckUnit 插入重复！！！");
			}else{
				this.checkUnitService.checkUnitAdd(checkUnit);
			}
		}
		return "redirect:/checkUnit/showCheckUnit";
	}
	
	@RequestMapping(value="/addCheckUnit1")
	@ResponseBody
	public String checkUnitAddAndSubmit1(CheckUnit cu){
		String cuName = cu.getCuName();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("cuName", "");
		int count = this.checkUnitService.getCheckUnitCount(map);
		//对输入的数据进行判断：重复、空值、超出长度等
		if(cuName==null || cuName=="" || cuName.length()>50){
			System.out.println("ERROR--->CheckUnit 插入为空或超出长度！！！");
			return "format problem!";
		}else{
			cu.setCuCode(StringUtil.addZeroToString(count+1, 4));
			List<CheckUnit> res = this.checkUnitService.judgeCheckUnit(cu);
			if(res.size()!=0){
				System.out.println("ERROR--->CheckUnit 插入重复！！！");
				return "duplicate!";
			}else{
				this.checkUnitService.checkUnitAdd(cu);
				return "1";
			}
		}
	}
	@RequestMapping(value="/edit/{cuId}")
	public ModelAndView checkUnitEdit(@PathVariable String cuId){
		ModelAndView mv = new ModelAndView();
		CheckUnit checkUnit = this.checkUnitService.getCheckUnitById(cuId);
		mv.addObject("checkUnit", checkUnit);
		mv.setViewName("editCheckUnit");
		return mv;
	}
	
	@RequestMapping(value="/editAndSubmit/{cuId}")
	public String checkUnitEditAndSubmit(@PathVariable String cuId,HttpServletRequest request){

		String cuName = (String)request.getParameter("cuName");
		//判断输入值是否合乎规范
		if(cuName ==null || cuName=="" || cuName.length()>50){
			System.out.println("ERROR--->CheckUnit 修改不能为空或超出长度！！！");
		}else{
			CheckUnit checkUnit = new CheckUnit();
			checkUnit.setCuId(cuId);
			checkUnit.setCuName(cuName);
			List<CheckUnit> res = this.checkUnitService.judgeCheckUnit(checkUnit);
			if(res.size()!=0){
				System.out.println("ERROR--->CheckUnit 修改重复！！！");
			}else{
				this.checkUnitService.checkUnitEdit(checkUnit);
			}
		}
		return "redirect:/checkUnit/showCheckUnit";
	}
	
	@RequestMapping(value="/editCheckUnit1")
	@ResponseBody
	public String checkUnitEditAndSubmit1(CheckUnit cu){

		String cuName = cu.getCuName();
		//判断输入值是否合乎规范
		if(cuName ==null || cuName=="" || cuName.length()>50){
			System.out.println("ERROR--->CheckUnit 修改不能为空或超出长度！！！");
			return "format problem!";
		}else{
			String cuCode = cu.getCuCode();
			cu.setCuCode("-1");
			//判断修改时只判断cuName；
			List<CheckUnit> res = this.checkUnitService.judgeCheckUnit(cu);
			if(res.size()!=0){
				System.out.println("ERROR--->CheckUnit 修改重复！！！");
				return "modify duplicate!";
			}else{
				cu.setCuCode(cuCode);
				this.checkUnitService.checkUnitEdit(cu);
				return "1";
			}
		}
	}
	
}
