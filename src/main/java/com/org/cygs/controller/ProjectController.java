package com.org.cygs.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.org.cygs.pojo.CheckUnit;
import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.PrIndexPC;
import com.org.cygs.pojo.Project;
import com.org.cygs.pojo.User;
import com.org.cygs.service.ProjectService;

@Controller
@RequestMapping(value="/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(value="/showProject")
	public ModelAndView showAllProject(){
		ModelAndView mv =new ModelAndView();
		mv.setViewName("listProject");
		return mv;
	}
	
	@RequestMapping(value="/selectProjectList")
	@ResponseBody
	public Page<Project> selectProjectList(@RequestBody Map<String,Object>map){
		Page<Project> projectPage = this.projectService.selectProjectList(map);
		return projectPage;
	}
	
	@RequestMapping(value="/selectProjectList1")
	public @ResponseBody Map<String, Object> selectProjectList1(@RequestParam Map<String, Object> map){
		
		return projectService.getProjectList(map);
	}	
	
	@RequestMapping(value="/delete/{prId}")
	public String projectDelete(@PathVariable String prId){
		this.projectService.projectDelete(prId);
		return "redirect:/project/showProject";
	}
	@RequestMapping(value="/deleteProject1")
	@ResponseBody
	public String projectDelete1(@RequestBody String prId){
		this.projectService.projectDelete(prId);
		return "1";
	}
	@RequestMapping(value="/edit/{prId}")
	public ModelAndView projectEdit(@PathVariable String prId){
		ModelAndView mv = new ModelAndView();
		Project pr = this.projectService.getProjectById(prId);
		List<User> xmjingli = this.projectService.getAllRole("��Ŀ����");
		List<User> shenji = this.projectService.getAllRole("���");
		List<User> ckbjl = this.projectService.getAllRole("�ɿز�����");
		List<User> ckbfjl = this.projectService.getAllRole("�ɿز�������");
		List<User> zgys = this.projectService.getAllRole("����Ԥ��");
		mv.addObject("pr", pr);
		mv.addObject("xmjingli", xmjingli);
		mv.addObject("shenji", shenji);
		mv.addObject("ckbjl", ckbjl);
		mv.addObject("ckbfjl", ckbfjl);
		mv.addObject("zgys", zgys);
		mv.setViewName("editProject");
		return mv;
	}
	
	@RequestMapping(value="/editAndSubmit/{prId}")
	public String projectEditAndSubmit(@PathVariable String prId,HttpServletRequest request) throws ParseException{
		//�ڲ�����޸Ĺ�����Ϣʱ���ǶԹ����������жϣ��жϰ����������Ƿ�Ϊ�ա��������ȣ��������ظ�����������ѡ������ж�
		String prName = (String)request.getParameter("prName");
		//String prCode = (String)request.getParameter("prCode");
		String uId = (String)request.getParameter("xmjingli");
		String prTime = (String)request.getParameter("prTime");
		String shenjiId = (String)request.getParameter("shenji");
		String ckjlId = (String)request.getParameter("ckbjl");
		String ckfjlId = (String)request.getParameter("ckbfjl");
		String zgysId = (String)request.getParameter("zgys");
		if(prName==null || prName=="" || prName.length()>50){
			System.out.println("ERROR--->Project �޸Ĺ�����Ϊ�ջ򳬳����ȣ�����");
		}else{
			Project pr = new Project();
			pr.setPrName(prName);
			pr.setPrId(prId);
			pr.setuId(uId);
			pr.setShenjiId(shenjiId);
			pr.setCkfjlId(ckfjlId);
			pr.setCkjlId(ckjlId);
			pr.setZgysId(zgysId);
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(prTime);
			pr.setPrTime(date);
			List<Project> res = this.projectService.judgeProject(pr);
			if(res.size()!=0){
				//�ж�δ���޸Ĺ�����ʱ����ù�����ƥ��Ĺ���id�Ƿ����Լ��Ĺ���id
				if(res.get(0).getPrId().equals(prId)){
					//��δ���޸Ĺ�������ֻ�޸��������ط���������޸ģ�
					this.projectService.projectEdit(pr);					
				}else{
					System.out.println("ERROR--->Project �������ظ�������");
				}
			}else{
				this.projectService.projectEdit(pr);
			}
		}
		return "listProject";
	}
	//��ӻ��޸Ĺ���ʱ���жϹ������Ƿ��ظ�
	@RequestMapping(value="/editTest")
	@ResponseBody
	public String editTest(String prName,String prId){
		Project pr = new Project();
		pr.setPrName(prName);
		pr.setPrId(prId);
		List<Project> res = this.projectService.judgeProject(pr);
		if(res.size()>0){
			if(res.get(0).getPrId().equals(prId)){
				return "true";
			}else{
				return "false";
			}
		}else{
			return "true";
		}
	}
	//���¥���Ż��޸�ʱ���ж��Ƿ��ظ�
	@RequestMapping(value="/addDetailTest")
	@ResponseBody
	public String addDetailTest(String prId,String unitNumber){
		PrIndexPC pip = new PrIndexPC();
		pip.setUnitNumber(unitNumber);
		pip.setPrId(prId);
		List<PrIndexPC> res = this.projectService.judgeProjectDetail(pip);
		if(res.size()>0){
			return "false";
		}else{
			return "true";
		}
	}
	@RequestMapping(value="/editDetailTest")
	@ResponseBody
	public String editDetailTest(String pcPId,String unitNumber){
		PrIndexPC pip = new PrIndexPC();
		pip.setUnitNumber(unitNumber);
		pip.setPcPId(pcPId);
		List<PrIndexPC> res = this.projectService.judgeProjectDetail(pip);
		if(res.size()>0){
			if(res.get(0).getPcPId().equals(pcPId)){
				return "true";
			}else{
				return "false";
			}
		}else{
			return "true";
		}
	}
	@RequestMapping(value="/addTest")
	@ResponseBody
	public String addTest(String prName){
		Project pr = new Project();
		pr.setPrName(prName);
		List<Project> res = this.projectService.judgeProject(pr);
		if(res.size()>0){
			return "false";
		}else{
			return "true";
		}
	}
	@RequestMapping(value="/editProject1")
	@ResponseBody
	public String projectEditAndSubmit1(Project pr){
		//�ڲ�����޸Ĺ�����Ϣʱ���ǶԹ����������жϣ��жϰ����������Ƿ�Ϊ�ա��������ȣ��������ظ�����������ѡ������ж�
		String prName = pr.getPrName();
		if(prName==null || prName=="" || prName.length()>50){
			System.out.println("ERROR--->Project �޸Ĺ�����Ϊ�ջ򳬳����ȣ�����");
			return "format error!";
		}else{
			List<Project> res = this.projectService.judgeProject(pr);
			if(res.size()!=0){
				//�ж�δ���޸Ĺ�����ʱ����ù�����ƥ��Ĺ���id�Ƿ����Լ��Ĺ���id
				String prId = pr.getPrId();
				if(res.get(0).getPrId().equals(prId)){
					//��δ���޸Ĺ�������ֻ�޸��������ط���������޸ģ�
					this.projectService.projectEdit(pr);
					return "1";
				}else{
					System.out.println("ERROR--->Project �������ظ�������");
					return "Project Name duplicate!";
				}
			}else{
				this.projectService.projectEdit(pr);
				return "1";
			}
		}
	}
	@RequestMapping(value="/goBack")
	public String goBack(){
		return "listProject";
	}
	@RequestMapping(value="/add")
	public String projectAdd(Model model){
		List<User> xmjingli = this.projectService.getAllRole("��Ŀ����");
		List<User> shenji = this.projectService.getAllRole("���");
		List<User> ckbjl = this.projectService.getAllRole("�ɿز�����");
		List<User> ckbfjl = this.projectService.getAllRole("�ɿز�������");
		List<User> zgys = this.projectService.getAllRole("����Ԥ��");
		model.addAttribute("xmjingli", xmjingli);
		model.addAttribute("shenji", shenji);
		model.addAttribute("ckbjl", ckbjl);
		model.addAttribute("ckbfjl", ckbfjl);
		model.addAttribute("zgys", zgys);
		return "addProject";
	}
	
	@RequestMapping(value="/addAndSubmit")
	public String projectAdd(HttpServletRequest request) throws ParseException{
		String prName = (String)request.getParameter("prName");
		String xmjingli = (String)request.getParameter("xmjingli");
		String shenji = (String)request.getParameter("shenji");
		String ckbjl = (String)request.getParameter("ckbjl");
		String ckbfjl = (String)request.getParameter("ckbfjl");
		String zgys = (String)request.getParameter("zgys");
		String prTime = (String)request.getParameter("prTime");
		//String prCode = String.valueOf(this.projectService.getCount());
		String prCode = String.valueOf(projectService.getPrCode() + 1);
		if(prName=="" || prName==null || prName.length()>50){
			System.out.println("ERROR--->Project ����������Ϊ�ջ򳬳����ȣ�����");
		}else{
			Project pr = new Project();
			pr.setPrName(prName);
			pr.setuId(xmjingli);
			pr.setShenjiId(shenji);
			pr.setCkjlId(ckbjl);
			pr.setCkfjlId(ckbfjl);
			pr.setPrCode(prCode);//���ݹ�����Ŀ�Ķ������ñ��
			pr.setZgysId(zgys);
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(prTime);
			pr.setPrTime(date);
			List<Project> res = this.projectService.judgeProject(pr);
			if(res.size()!=0){
				System.out.println("ERROR--->Project �ظ����������Ѵ��ڣ�����");
			}else{
				this.projectService.projectAdd(pr);
			}
		}
		return "listProject";
	}
	@RequestMapping(value="/addProject1")
	@ResponseBody
	public String projectAdd1(Project pr){
		String prName = pr.getPrName();
		//String prCode = String.valueOf(this.projectService.getCount());
		String prCode = String.valueOf(projectService.getPrCode() + 1);
		if(prName=="" || prName==null || prName.length()>50){
			System.out.println("ERROR--->Project ����������Ϊ�ջ򳬳����ȣ�����");
			return "format error!";
		}else{
			pr.setPrCode(prCode);//���ݹ�����Ŀ�Ķ������ñ��
			List<Project> res = this.projectService.judgeProject(pr);
			if(res.size()!=0){
				System.out.println("ERROR--->Project �ظ����������Ѵ��ڣ�����");
				return "project name exist!";
			}else{
				this.projectService.projectAdd(pr);
				return "1";
			}
		}
	}
	
	
	@RequestMapping(value="/detail/{prId}")
	public String projectDetail(@PathVariable String prId,Model model,HttpSession session){
		List<PrIndexPC> detail = this.projectService.getProjectDetailById(prId);
		session.setAttribute("projectDetailId", prId);
		model.addAttribute("pdetail", detail);
		Project project = this.projectService.getProjectById(prId);
		model.addAttribute("project", project);
		return "listProjectDetail";		
	}
	@RequestMapping(value="/detail1/{prId}")
	public String projectDetail1(@PathVariable String prId,Model model,HttpSession session){
		session.setAttribute("projectDetailId", prId);
		model.addAttribute("prId", prId);
		return "listProjectDetail";		
	}
	@RequestMapping(value="/selectProjectDetailList1")
	@ResponseBody
	public Map<String,Object> getProjectDetailList1(@RequestParam Map<String,Object> map){
		return this.projectService.getPrIndexPCList(map);
	}
	@RequestMapping(value="/detailDelete/{pcPId}")
	public String projectDetailDeleteById(@PathVariable String pcPId,HttpSession session){
		String prId = (String)session.getAttribute("projectDetailId");
		this.projectService.projectDetailDeleteById(pcPId);
		return "redirect:/project/detail/"+prId;
		
	}
	@RequestMapping(value="/deleteDetail1")
	@ResponseBody
	public String projectDetailDeleteById(@RequestBody String pcPId){
		this.projectService.projectDetailDeleteById(pcPId);
		return "1";
		
	}
	@RequestMapping(value="/detailEdit/{pcPId}")
	public String projectDetailEdit(@PathVariable String pcPId,Model model){
		PrIndexPC pp = this.projectService.getPrIndexPCById(pcPId);
		model.addAttribute("pdetail", pp);
		return "editProjectDetail";
	}
	@RequestMapping(value="/editDetail1/{pcPId}")
	public String projectDetailEdit1(@PathVariable String pcPId,Model model){
		PrIndexPC pp = this.projectService.getPrIndexPCById(pcPId);
		model.addAttribute("pdetail", pp);
		return "editProjectDetail";
	}
	@RequestMapping(value="/detailEditAndSubmit/{pcPId}")
	public String projectDetailEditAndSubmit(@PathVariable String pcPId,HttpServletRequest request,HttpSession session){
		String unitNumber = (String)request.getParameter("unitNumber");
		String prId = (String)session.getAttribute("projectDetailId");
		if(unitNumber==null || unitNumber=="" || unitNumber.length()>20){
			System.out.println("ERROR--->ProjectDetail �޸����ݲ���Ϊ�գ�����");
		}else{
			//�޸Ĺ���¥����ʱ���ж��Ƿ���ԭ�����д��ڵ�¥������ͬ�������δ�޸�¥����ʱ���ж�Ԥ����Ա�Ƿ��޸�
			PrIndexPC temp;
			PrIndexPC pp = new PrIndexPC();
			pp.setPcPId(pcPId);
			pp.setPrId(prId);
			pp.setUnitNumber(unitNumber);
			List<PrIndexPC> res = this.projectService.judgeProjectDetail(pp);
			if(res.size()!=0){
				temp = res.get(0);
				if(pp.getPcPId().equals(temp.getPcPId())){
					this.projectService.projectDetailEdit(pp);
					System.out.println("ERROR--->ProjectDetail δ���޸ģ�����");
				}else{
					System.out.println("ERROR--->ProjectDetail �����ظ�������");
				}
			}else{
				this.projectService.projectDetailEdit(pp);
			}
			
		}
		return "redirect:/project/detail/"+prId;
	}
	@RequestMapping(value="/editProjectDetail1")
	@ResponseBody
	public String projectDetailEditAndSubmit1(PrIndexPC pip){
		System.out.println(pip.getPcPId()+"  "+pip.getUnitNumber());
		String unitNumber = pip.getUnitNumber();
		if(unitNumber==null || unitNumber=="" || unitNumber.length()>20){
			System.out.println("ERROR--->ProjectDetail �޸����ݲ���Ϊ�գ�����");
			return "format error!";
		}else{
			//�޸Ĺ���¥����ʱ���ж��Ƿ���ԭ�����д��ڵ�¥������ͬ�������δ�޸�¥����ʱ���ж�Ԥ����Ա�Ƿ��޸�
			PrIndexPC temp;
			temp = this.projectService.getPrIndexPCById(pip.getPcPId());
			temp.setUnitNumber(pip.getUnitNumber());
			List<PrIndexPC> res = this.projectService.judgeProjectDetail(temp);
			if(res.size()>0){
				temp = res.get(0);
				if(pip.getPcPId().equals(temp.getPcPId())){
					this.projectService.projectDetailEdit(pip);
					System.out.println("ERROR--->ProjectDetail δ���޸ģ�����");
					return "1";
				}else{
					System.out.println("ERROR--->ProjectDetail �����ظ�������");
					return "duplicate!";
				}
			}else{
				this.projectService.projectDetailEdit(pip);
				return "1";
			}
			
		}
	}
	@RequestMapping(value="/detailAdd/{prId}")
	public String projectDetailAdd(@PathVariable String prId,Model model){
		model.addAttribute("prId", prId);
		return "addProjectDetail";
	}
	
	@RequestMapping(value="/detailAddAndSubmit/{prId}")
	public String projectDetailAddAndSubmit(HttpServletRequest request,@PathVariable String prId){
		String unitNumber = (String)request.getParameter("unitNumber");
		if(unitNumber==null || unitNumber=="" || unitNumber.length()>20){
			System.out.println("ERROR--->ProjectDetail ����¥���Ų���Ϊ�ջ򳬳����ȣ�����");
		}else{
			PrIndexPC pp = new PrIndexPC();
			pp.setPrId(prId);
			pp.setUnitNumber(unitNumber);
			List<PrIndexPC> res = this.projectService.judgeProjectDetail(pp);
			if(res.size()!=0){
				System.out.println("ERROR--->ProjectDetail ���������ظ�������");
			}else{
				this.projectService.projectDetailAdd(pp);
			}
		}
		return "redirect:/project/detail/"+prId;
	}
	@RequestMapping(value="/addDetail1/{prId}")
	public String projectDetailAdd1(@PathVariable String prId,Model model){
		model.addAttribute("prId", prId);
		return "addProjectDetail";
	}
	@RequestMapping(value="/addProjectDetail1")
	@ResponseBody
	public String projectDetailAddAndSubmit(PrIndexPC pip){
		String unitNumber = pip.getUnitNumber();
		if(unitNumber==null || unitNumber=="" || unitNumber.length()>20){
			System.out.println("ERROR--->ProjectDetail ����¥���Ų���Ϊ�ջ򳬳����ȣ�����");
			return "format error!";
		}else{
			List<PrIndexPC> res = this.projectService.judgeProjectDetail(pip);
			if(res.size()!=0){
				System.out.println("ERROR--->ProjectDetail ���������ظ�������");
				return "add duplicate!";
			}else{
				this.projectService.projectDetailAdd(pip);
				return "1";
			}
		}
	}
}
