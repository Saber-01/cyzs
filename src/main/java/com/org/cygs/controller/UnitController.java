package com.org.cygs.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.Unit;
import com.org.cygs.service.YUnitService;


@Controller
@RequestMapping(value="/unit")
public class UnitController {

	@Autowired
	private YUnitService unitService;
	
	@RequestMapping(value="/showUnit")
	public String showUnit(){
		return "listUnit";
	}
	@RequestMapping(value="/selectUnitList")
	public @ResponseBody PagePojo showAllUnit(@RequestBody Map<String, String> map){
		int pageNo = Integer.parseInt(map.get("pageNo"));
		PagePojo<Unit> unitList = this.unitService.selectUnitList(pageNo, 15);
		return unitList;
	}

	@RequestMapping(value="/selectUnitList1")
	public @ResponseBody Map<String, Object> selectUnitList1(@RequestParam Map<String, Object> map){
		
		return unitService.getUnitList(map);
	}	
	
	@RequestMapping(value="/delete/{unId}")
	public String unitDelete(@PathVariable String unId){
		this.unitService.unitDeleteById(unId);
		return "redirect:/unit/showUnit";
	}
	
	@RequestMapping(value="/delete1")
	@ResponseBody
	public String unitDelete1(@RequestBody String unId){
		this.unitService.unitDeleteById(unId);
		return "1";
	}
	@RequestMapping(value="/add")
	public String unitAdd(){
		return "addUnit";
	}
	@RequestMapping(value="/addTest")
	@ResponseBody
	public String addTest(String unName){
		Unit unit = new Unit();
		unit.setUnName(unName);
		List<Unit> res = this.unitService.judgeUnit(unit);
		if(res.size()>0){
			return "false";
		}else{
			return "true";
		}
	}
	@RequestMapping(value="/editTest")
	@ResponseBody
	public String editTest(String unName){
		Unit unit = new Unit();
		unit.setUnName(unName);
		List<Unit> res = this.unitService.judgeUnit(unit);
		if(res.size()>0){
			return "false";
		}else{
			return "true";
		}
	}
	@RequestMapping(value="/addAndSubmit")
	public String unitAdd(HttpServletRequest request){
		int count = this.unitService.getCount()+1;
		String unCode=null;
		if(count<10){
			unCode = "0"+count;
		}else{
			unCode = String.valueOf(count);
		}
		String unName = (String)request.getParameter("unName");
		//添加数据格式判断
		if(unName==null || unName=="" || unName.length()>10){
			System.out.println("ERROR--->Unit 插入数据为空或超出长度!!!");
		}else{
			Unit unit = new Unit();
			unit.setUnCode(unCode);
			unit.setUnName(unName);
			List<Unit> res = this.unitService.judgeUnit(unit);
			if(res.size()!=0){
				System.out.println("ERROR--->Unit 插入数据重复！！！");
			}else{
				this.unitService.unitAdd(unit);
			}
		}
		return "redirect:showUnit";
	}
	@RequestMapping(value="/addUnit1")
	@ResponseBody
	public String unitAdd1(Unit unit){
		int count = this.unitService.getCount()+1;
		String unCode=null;
		if(count<10){
			unCode = "0"+count;
		}else{
			unCode = String.valueOf(count);
		}
		String unName = unit.getUnName();
		//添加数据格式判断
		if(unName==null || unName=="" || unName.length()>10){
			System.out.println("ERROR--->Unit 插入数据为空或超出长度!!!");
			return "format probelm!";
		}else{
			unit.setUnCode(unCode);
			List<Unit> res = this.unitService.judgeUnit(unit);
			if(res.size()!=0){
				System.out.println("ERROR--->Unit 插入数据重复！！！");
				return "add duplicate!";
			}else{
				this.unitService.unitAdd(unit);
				return "1";
			}
		}
	}
	@RequestMapping(value="/edit/{unId}")
	public String unitEdit(@PathVariable String unId,Model model){
		Unit unit = this.unitService.getUnitById(unId);
		model.addAttribute("unit", unit);
		return "editUnit";
	}
	
	@RequestMapping(value="/editAndSubmit/{unId}")
	public String unitEditAndSubmit(@PathVariable String unId,HttpServletRequest request){
		//String unCode = (String)request.getParameter("unCode");
		String unName = (String)request.getParameter("unName");
		//输入格式判断
		if(unName==null || unName=="" || unName.length()>50){
			System.out.println("ERROR--->Unit 修改数据为空或超出长度！！！");
		}else{
			Unit unit = new Unit();
			unit.setUnName(unName);
			unit.setUnId(unId);
			List<Unit> res = this.unitService.judgeUnit(unit);
			if(res.size()!=0){
				System.out.println("ERROR--->修改数据重复！！！");
			}else{
				this.unitService.unitEdit(unit);
			}
		}
		return "redirect:/unit/showUnit";
	}
	
	@RequestMapping(value="/editUnit1")
	@ResponseBody
	public String unitEditAndSubmit1(Unit unit){
		//String unCode = (String)request.getParameter("unCode");
		String unName = unit.getUnName();
		//输入格式判断
		if(unName==null || unName=="" || unName.length()>50){
			System.out.println("ERROR--->Unit 修改数据为空或超出长度！！！");
			return "format probelm!";
		}else{
			String unCode = unit.getUnCode();
			unit.setUnCode("-1");
			//判断是否重复时，只判断单位名称是否重复！
			List<Unit> res = this.unitService.judgeUnit(unit);
			if(res.size()!=0){			
					System.out.println("ERROR--->修改数据重复！！！");
					return "modify duplicate!";			
			}else{
				unit.setUnCode(unCode);
				this.unitService.unitEdit(unit);
				return "1";
			}
		}
	}
}
