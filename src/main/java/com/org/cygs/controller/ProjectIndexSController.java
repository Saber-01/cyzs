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
import com.org.cygs.dao.UserDao;
import com.org.cygs.pojo.CheckUnit;
import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.Project;
import com.org.cygs.pojo.ProjectIndexS;
import com.org.cygs.pojo.User;
import com.org.cygs.service.ProjectIndexSService;
import com.org.cygs.service.ProjectService;

@Controller
@RequestMapping(value="/projectIndexS")
public class ProjectIndexSController {

	@Autowired
	private ProjectIndexSService projectIndexSService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/showProjectIndexS")
	public ModelAndView getAllProjectIndexS(){
		ModelAndView mv = new ModelAndView();
		List<Project> prName = this.projectService.getAllProjectName();
		mv.addObject("prName", prName);
		mv.setViewName("listProjectIndexS");
		return mv;
	}
	
	@RequestMapping(value="/selectProjectIndexSList")
	@ResponseBody
	public Page<ProjectIndexS> selectProjectIndexS(@RequestBody Map<String,Object> map){
			Page<ProjectIndexS> pisPage = this.projectIndexSService.selectProjectIndexSList(map);
			return pisPage;		
	}
	
	@RequestMapping(value="/selectProjectIndexSList1")
	public @ResponseBody Map<String, Object> selectProjectIndexSList1(@RequestParam Map<String, Object> map){
		
		return projectIndexSService.getProjectIndexSList(map);
	}	
	
	@RequestMapping(value="/delete/{prSId}")
	public String projectIndexSDelete(@PathVariable String prSId){
		this.projectIndexSService.projectIndexSDelete(prSId);
		return "redirect:/projectIndexS/showProjectIndexS";
	}
	@RequestMapping(value="/delete1")
	@ResponseBody
	public String projectIndexSDelete1(@RequestBody String prSId){
		this.projectIndexSService.projectIndexSDelete(prSId);
		return "1";
	}
	@RequestMapping(value="/add")
	public String projectIndexSAdd(Model model){
		List<User> gzName = this.userDao.getAllUserByType("工长");
		List<Project> prName = this.projectService.getAllProjectName();
		model.addAttribute("gzName", gzName);
		model.addAttribute("prName", prName);
		return "addProjectIndexS";
	}
	
	@RequestMapping(value="/addAndSubmit")
	public String projectIndexSAddAndSubmit(HttpServletRequest request){
		String uId = (String)request.getParameter("uId");
		String prId = (String)request.getParameter("prId");
		if(uId==null || uId=="" || prId==null || prId==""){
			System.out.println("ERROR--->ProjectIndexS 新增不能为空");
		}else{
			ProjectIndexS pis = new ProjectIndexS();
			pis.setPrId(prId);
			pis.setuId(uId);
			List<ProjectIndexS> res = this.projectIndexSService.judgeProjectIndexS(pis);
			if(res.size()!=0){
				System.out.println("ERROR--->ProjectDetail 新增数据重复！！！");
			}else{
				this.projectIndexSService.projectIndexSAdd(pis);
			}
		}
		return "redirect:/projectIndexS/showProjectIndexS";
	}
	//在修改或添加工程辖工长信息时，判断是否存在重复的情况
	@RequestMapping(value="/editTest")
	@ResponseBody
	public String editTest(String prId,String uId){
		ProjectIndexS pis = new ProjectIndexS();
		pis.setPrId(prId);
		pis.setuId(uId);
		List<ProjectIndexS> res = this.projectIndexSService.judgeProjectIndexS(pis);
		if(res.size()>0){
			return "false";
		}else{
			return "true";
		}
	}
	@RequestMapping(value="/addTest")
	@ResponseBody
	public String addTest(String prId,String uId){
		ProjectIndexS pis = new ProjectIndexS();
		pis.setPrId(prId);
		pis.setuId(uId);
		List<ProjectIndexS> res = this.projectIndexSService.judgeProjectIndexS(pis);
		if(res.size()>0){
			return "false";
		}else{
			return "true";
		}
	}
	@RequestMapping(value="/addPrIndexS1")
	@ResponseBody
	public String projectIndexSAddAndSubmit1(ProjectIndexS pis){
		String uId = pis.getuId();
		String prId = pis.getPrId();
		if(uId==null || uId=="" || prId==null || prId==""){
			System.out.println("ERROR--->ProjectIndexS 新增不能为空");
			return "format probelm!";
		}else{
			List<ProjectIndexS> res = this.projectIndexSService.judgeProjectIndexS(pis);
			if(res.size()!=0){
				System.out.println("ERROR--->ProjectDetail 新增数据重复！！！");
				return "add duplicate!";
			}else{
				this.projectIndexSService.projectIndexSAdd(pis);
				return "1";
			}
		}
	}
	@RequestMapping(value="/edit/{prSId}")
	public String projectIndexSEdit(@PathVariable String prSId,Model model){
		ProjectIndexS pis = this.projectIndexSService.getProjectIndexSById(prSId);
		List<User> gzName = this.userDao.getAllUserByType("工长");
		List<Project> prName = this.projectService.getAllProjectName();
		model.addAttribute("gzName", gzName);
		model.addAttribute("prName", prName);
		model.addAttribute("pis", pis);
		return "editProjectIndexS";
	}
	
	@RequestMapping(value="/editAndSubmit/{prSId}")
	public String projectIndexSEditAndSubmit(@PathVariable String prSId,HttpServletRequest request){
		String prId = (String)request.getParameter("prId");
		String uId = (String)request.getParameter("uId");
		if(prId==null || prId=="" || uId==null || uId==""){
			System.out.println("ERROR--->ProjectIndexS 修改不能为空！！！");
		}else{
			ProjectIndexS pis = new ProjectIndexS();
			pis.setPrId(prId);
			pis.setPrSId(prSId);
			pis.setuId(uId);
			List<ProjectIndexS> res = this.projectIndexSService.judgeProjectIndexS(pis);
			if(res.size()!=0){
				System.out.println("ERROR--->ProjectIndexS 修改不能重复！！！");
			}else{
				this.projectIndexSService.projectIndexSEdit(pis);
			}
		}	
		return "redirect:/projectIndexS/showProjectIndexS";
	}
	
	@RequestMapping(value="/editPrIndexS1")
	@ResponseBody
	public String projectIndexSEditAndSubmit1(ProjectIndexS pis){
		String prId = pis.getPrId();
		String uId = pis.getuId();
		if(prId==null || prId=="" || uId==null || uId==""){
			System.out.println("ERROR--->ProjectIndexS 修改不能为空！！！");
			return "format probelm!";
		}else{
			List<ProjectIndexS> res = this.projectIndexSService.judgeProjectIndexS(pis);
			if(res.size()!=0){
				System.out.println("ERROR--->ProjectIndexS 修改不能重复！！！");
				return "modify duplicate!";
			}else{
				this.projectIndexSService.projectIndexSEdit(pis);
				return "1";
			}
		}	
	}
}
