package com.org.cygs.util.common;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.org.cygs.pojo.Project;
import com.org.cygs.pojo.SystemParameter;
import com.org.cygs.pojo.User;
import com.org.cygs.service.ProjectService;
import com.org.cygs.service.SystemParameterService;

@Component 
public class Tools {
	@Autowired
	private SystemParameterService  systemParameterService;
	
	@Autowired
	private ProjectService projectService;
	
	private static Tools  tools;
	
	@PostConstruct 
    public void init() { 
		tools = this; 
		tools.systemParameterService = this.systemParameterService;
		tools.projectService = this.projectService;
    }
	
	//根据相关角色获取对应的工程
	public static List<Project> getProjectList(String roleName,String uId){
		List<Project> projectList = null;
		if(roleName.equals("项目经理")){
			projectList = tools.projectService.getProjectByUId(uId);
		}
		if(roleName.equals("主管预算")){
			projectList = tools.projectService.getProjectByZhuguanId(uId);
		}
		if(roleName.equals("成控部经理")){
			projectList = tools.projectService.getProjectByChengkonId(uId);
		}
		if(roleName.equals("成控部副经理")){
			projectList = tools.projectService.getProjectByChengkonFuId(uId);
		}
		if(roleName.equals("审计")){
			projectList = tools.projectService.getProjectByShenJiId(uId);
		}
		return projectList;
	}
	
	
	
	//验证是否关停任务书审核
	public static void checkStopAudit(Model model) {
		SystemParameter stopaudit = tools.systemParameterService.selectByParaName("stopaudit");
		Date tdate = new Date(); // 现在的时间
		Date ldate = stopaudit.getParaLosetime();
		if (stopaudit.getParaValue() == 0) {// ParaValue：0表示关停
			if (ldate.before(tdate)) {// 现在的时间超过了关停的时间，设置ParaValue为1
				stopaudit.setParaValue(1);
				tools.systemParameterService.updateSystemParam(stopaudit);
			}
		}
	    model.addAttribute("stopaudit", stopaudit);	
	}
	
	
	//验证是否关停批量审核-----项目经理
	public static void checkStopPlAudit(Model model,HttpSession session) {
		User user = (User) session.getAttribute(CygsConst.USER_SESSION);
		String roleName = user.getRoleName();
		SystemParameter stopplaudit = tools.systemParameterService.selectByParaName("stopbatchaudits");
		if(roleName.equals("项目经理")){
			Date tdate = new Date(); // 现在的时间
			Date ldate = stopplaudit.getParaLosetime();
			if (stopplaudit.getParaValue() == 0) {// ParaValue：0表示关停
				if (ldate.before(tdate)) {// 现在的时间超过了关停的时间，设置ParaValue为1
					stopplaudit.setParaValue(1);
					tools.systemParameterService.updateSystemParam(stopplaudit);
				}
			}
		}else{
			stopplaudit.setParaValue(1);
		}
		
		model.addAttribute("stopplaudit", stopplaudit);
	}

}
