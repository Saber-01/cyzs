package com.org.cygs.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.org.cygs.pojo.BudgetVo;
import com.org.cygs.pojo.CheckUnit;
import com.org.cygs.pojo.OperationPriceVo;
import com.org.cygs.pojo.Part;
import com.org.cygs.pojo.PrIndexPC;
import com.org.cygs.pojo.Project;
import com.org.cygs.pojo.User;
import com.org.cygs.pojo.YklHistory;
import com.org.cygs.service.CheckUnitService;
import com.org.cygs.service.OperationPriceService;
import com.org.cygs.service.PartService;
import com.org.cygs.service.ProjectIndexPCService;
import com.org.cygs.service.ProjectService;
import com.org.cygs.util.common.CygsConst;

@Controller
@RequestMapping(value="/ykl")
public class YKLController {

	@Autowired
	private OperationPriceService operationPriceService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private CheckUnitService checkUnitService;
	
	@Autowired
	private ProjectIndexPCService prIndexPCService;
	
	@Autowired
	private PartService partService;
	
	@RequestMapping(value="/showYKL")
	public String getProjectList(Model model){
		List<Project> prList = this.projectService.getAllProjectName();
		List<CheckUnit> cuList = this.checkUnitService.getAllCheckUnit();
		model.addAttribute("prList", prList);
		model.addAttribute("cuList", cuList);
		return "listYKL";
	}
	
	@RequestMapping(value="/getUnitNumberList")
	@ResponseBody
	public List<PrIndexPC> getUnitNumberList(@RequestBody Map<String,String>map){
		String prId = map.get("prId").toString();
		List<PrIndexPC> pipList = this.projectService.getProjectDetailById(prId);
		return pipList;
	}
/*
	//预控量管理中获取预控量
	@RequestMapping(value="/getYKL")
	@ResponseBody
	public List<OperationPriceVo> getYKL(@RequestBody Map<String,Object> map,Model model){
		List<OperationPriceVo> res = this.operationPriceService.getYKL(map);
		Double budget = 0.0;
		Double accu = 0.0;
		Double temp = 0.0;
		for(int i=0;i<res.size();i++){
			if((temp=res.get(i).getAccumulateSum())!=null){
				accu = accu + temp;
			}else{
				res.get(i).setAccumulateSum(0.0);
			}
			if((temp=res.get(i).getBudgetSum())!=null){
				budget = budget + temp;
			}else{
				res.get(i).setBudgetSum(0.0);
			}
		}
		//创建一个OperationPriceVo对象，将合计的已结、预控量总和分别放入accumulateSum、budgetSum属性中，便于数据传输！
		if(res.size()>0){
			OperationPriceVo opo = new OperationPriceVo();
			opo.setAccumulateSum(accu);
			opo.setBudgetSum(budget);
			res.add(opo);
		}
		return res;
	}
*/
	@RequestMapping(value="/getYKL")
	@ResponseBody
	public Map<String,Object> getYKL(@RequestParam Map<String,Object> map){
		return this.operationPriceService.getYKLList(map);
	}
	//修改预控量
	@RequestMapping(value="/editYKL")
	@ResponseBody
	public List<String> editYKL(@RequestBody Map<String,Object> map,HttpSession session){
		System.out.println("test for input :"+map.size());
		Set<Entry<String, Object>> entries = map.entrySet();
		String budget="";
		Map<String,Object> bs = new HashMap<String, Object>();
		OperationPriceVo opo;
		User user = (User)session.getAttribute(CygsConst.USER_SESSION);
		Date d = new Date();
		YklHistory yklHistory=new YklHistory();
		for(Entry<String,Object> entry:entries){
			budget = (String)entry.getValue();
			if(budget != null && budget !=""){
				bs.put("opId",entry.getKey());
				bs.put("budgetSum", Double.valueOf((String)entry.getValue()));
				//将预控量修改记录下来
				opo = this.operationPriceService.getOperationPriceVoByKey(entry.getKey());
				//yklHistory.setYklId("2017072601");
				yklHistory.setProperty(opo);
				yklHistory.setMender(user.getUserName());
				yklHistory.setBudgetSum(Double.valueOf((String)entry.getValue()));
				yklHistory.setAuditTime(d);
				this.operationPriceService.addYKLmendRecord(yklHistory);
				this.operationPriceService.editBudgetSum(bs);
				bs.clear();
			}
			
		}
		List<String> res = new ArrayList<String>();
		res.add("修改成功！");
		return res;
	}
	
	@RequestMapping(value="/showYKLHistory")
	public ModelAndView gotoShowYKLHistory(){
		ModelAndView mv = new ModelAndView();
		List<Project> prList = this.projectService.getAllProjectName();
		List<CheckUnit> cuList = this.checkUnitService.getAllCheckUnit();
		mv.addObject("prList", prList);
		mv.addObject("cuList", cuList);
		mv.setViewName("listYklHistory");
		return mv;
	}
/*
	//获取预控量修改记录
	@RequestMapping(value="/getYKLHistory")
	@ResponseBody
	public Page<YklHistory> getYKLHistory(@RequestBody Map<String,Object>map){
		Page<YklHistory> ykl = this.operationPriceService.selectYKLHistoryList(map);
		return ykl;
	}
*/
	@RequestMapping(value="/getYKLHistory")
	@ResponseBody
	public Map<String,Object> getYKLHistory(@RequestParam Map<String,Object> map){
		return this.operationPriceService.getYKLHistoryList(map);
	}
	//预控量已开已审对比
	@RequestMapping(value="/showYKLBudgetSum")
	public ModelAndView gotoShowYKLBudgetSum(){
		ModelAndView mv = new ModelAndView();
		List<Project> prList = this.projectService.getAllProjectName();
		List<CheckUnit> cuList = this.checkUnitService.getAllCheckUnit();
		mv.addObject("prList", prList);
		mv.addObject("cuList", cuList);
		mv.setViewName("listYklAndBudgetSum");
		return mv;
	}
/*	
	//预控量已开已审对比
	@RequestMapping(value="/getYKLBudget")
	@ResponseBody 
	public List<OperationPriceVo> getYKLBudget(@RequestBody Map<String,Object>map){
		List<OperationPriceVo> opo = this.operationPriceService.getYklBudgetSum(map);
		int size = opo.size();
		Double budgetSum=0.0;//求预控量总和
		Double accuSum=0.0;//求已结总和
		Double realQuantity=0.0;//求已开总和
		OperationPriceVo op = new OperationPriceVo();
		for(int i=0;i<size;i++){
			if(opo.get(i).getBudgetSum()!=null){
				budgetSum += opo.get(i).getBudgetSum();
			}else{
				opo.get(i).setBudgetSum(0.0);
			}
			if(opo.get(i).getAccumulateSum()!=null){
				accuSum += opo.get(i).getAccumulateSum();
			}else{
				opo.get(i).setAccumulateSum(0.0);
			}
			if(opo.get(i).getRealSum()!=null){
				realQuantity += opo.get(i).getRealSum();
			}else{
				opo.get(i).setRealSum(0.0);
			}
		}
		if(size>0){
			op.setAccumulateSum(accuSum);
			op.setBudgetSum(budgetSum);
			op.setRealSum(realQuantity);
			opo.add(op);
		}
		System.out.println("yklbudget "+opo.size());
		return opo;
	}
*/
	//预控量中已开与已审对比
	@RequestMapping(value="/getYKLBudget")
	@ResponseBody
	public Map<String,Object> selectYKLBudget(@RequestParam Map<String,Object> map){
		return this.operationPriceService.selectYklBudgetSum(map);
	}
	
	//预算与已开量对比，加载工程名称与分部选择下拉框
	@RequestMapping(value="/showBudgetAndYkl")
	public ModelAndView gotoShowBudgetAndYkl(){
		ModelAndView mv = new ModelAndView();
		List<Part> pList = this.partService.getAllPart();
		List<Project> prList = this.projectService.getAllProjectName();
		mv.addObject("prList", prList);
		mv.addObject("pList", pList);
		mv.setViewName("listBudgetAndYkl");
		return mv;
	}
	
	//获取预算与已开量对比 信息
	@RequestMapping(value="/getBudgetAndYkl")
	@ResponseBody
	public List<BudgetVo> getBudgetAndYkl(@RequestBody Map<String,String> map){
		List<BudgetVo> bvList = this.operationPriceService.getBudgetVo(map);
		return bvList;
	}
}
