package com.org.cygs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.ProjectDao;
import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.PrIndexPC;
import com.org.cygs.pojo.Project;
import com.org.cygs.pojo.ProjectIndexS;
import com.org.cygs.pojo.User;
import com.org.cygs.service.ProjectService;

@Service("ProjectService")
public class ProjectServiceImpl implements ProjectService{

	@Resource
	private ProjectDao projectDao;
	@Override
	public List<Project> getAllProject() {
		List<Project> pr = this.projectDao.getAllProject();
		int prSize = pr.size();
		String uName=null;
		String shenjiName=null;
		String ckjlName=null;
		String ckfjlName=null;
		String zgysName=null;
	
		for(int i=0;i<prSize;i++){
			Project project = pr.get(i);
			uName = this.projectDao.getPersonNameById(project.getuId());
			shenjiName = this.projectDao.getPersonNameById(project.getShenjiId());
			ckjlName = this.projectDao.getPersonNameById(project.getCkjlId());
			ckfjlName = this.projectDao.getPersonNameById(project.getCkfjlId());
			zgysName = this.projectDao.getPersonNameById(project.getZgysId());
			project.setuName(uName);
			project.setShenjiName(shenjiName);
			project.setCkjlName(ckjlName);
			project.setCkfjlName(ckfjlName);
			project.setZgysName(zgysName);
			pr.set(i, project);
		}
		return pr;
	}
	
	//查看项目经理对应的工程
	public List<Project> getProjectByUId(String uId){
		return projectDao.getProjectByUId(uId);
	}
	//查看成控部经理对应的工程
	public List<Project> getProjectByChengkonId(String uId){
		return projectDao.getProjectByChengkonId(uId);
	}
	
	//查看成控部--副--经理对应的工程
	public List<Project> getProjectByChengkonFuId(String uId){
		return projectDao.getProjectByChengkonFuId(uId);
	}
	
	
	 //查看主管预算对应的工程
	public List<Project> getProjectByZhuguanId(String uId){
		return projectDao.getProjectByZhuguanId(uId);
	}
	  //查看审计对应的工程
	public List<Project> getProjectByShenJiId(String uId){
		return projectDao.getProjectByShenJiId(uId);
	}
	//查看工程对应的项目经理名称
	public String getUserName(String prId){
		return projectDao.getUserName(prId);
	}
	public String getShenJiName(String prId){
		//查看工程对应的审计名称
		return projectDao.getShenJiName(prId);
	}
	public String getZhuGuanName(String prId){
		return projectDao.getZhuGuanName(prId);
		//查看工程对应的主管预算
	}
	// 查看工长对应的工程
	public List<Project> getProjectByGongzhang(String uId) {
		return projectDao.selectProjectByGongzhang(uId);
	}
	// 查看预算人员对应的工程
	public List<Project> getProjectByYusuan(String uId) {
		return projectDao.selectProjectByYusuan(uId);
	}
	
	

	@Override
	public Project getProjectById(String prId) {
		Project project = this.projectDao.getProjectById(prId);
		String uName=null;
		String shenjiName=null;
		String ckjlName=null;
		String ckfjlName=null;
		String zgysName=null;
		if(project.getuId()!=null){
			uName = this.projectDao.getPersonNameById(project.getuId());
			project.setuName(uName);
		}
		if(project.getShenjiId()!=null){
			shenjiName = this.projectDao.getPersonNameById(project.getShenjiId());
			project.setShenjiName(shenjiName);
		}
		if(project.getCkjlId()!=null){
			ckjlName = this.projectDao.getPersonNameById(project.getCkjlId());
			project.setCkjlName(ckjlName);
		}
		if(project.getCkfjlId()!=null){
			ckfjlName = this.projectDao.getPersonNameById(project.getCkfjlId());
			project.setCkfjlName(ckfjlName);
		}
		if(project.getZgysId()!=null){
			zgysName = this.projectDao.getPersonNameById(project.getZgysId());
			project.setZgysName(zgysName);
		}
		
		return project;
	}

	@Override
	public int projectAdd(Project pr) {
		
		return this.projectDao.projectAdd(pr);
	}

	@Override
	public int projectEdit(Project pr) {
		
		return this.projectDao.projectEdit(pr);
	}

	@Override
	public int projectDelete(String prId) {
		this.projectDao.deletePrIndexPCByPrId(prId);
		this.projectDao.deletePrIndexSByPrId(prId);
		return this.projectDao.projectDelete(prId);
	}

	@Override
	public List<User> getAllRole(String roleType) {
		
		return this.projectDao.getAllRole(roleType);
	}

	@Override
	public List<PrIndexPC> getProjectDetailById(String prId) {
		
		return this.projectDao.getProjectDetailById(prId);
	}

	@Override
	public int projectDetailDeleteById(String pcPId) {
		
		return this.projectDao.projectDetailDeleteById(pcPId);
	}

	@Override
	public int projectDetailEdit(PrIndexPC pp) {
	
		return this.projectDao.projectDetailEdit(pp);
	}

	@Override
	public int projectDetailAdd(PrIndexPC pp) {
		
		return this.projectDao.projectDetailAdd(pp);
	}

	@Override
	public PrIndexPC getPrIndexPCById(String pcPId) {
		
		return this.projectDao.getPrIndexPCById(pcPId);
	}

	@Override
	public List<Project> judgeProject(Project pr) {
		
		return this.projectDao.judgeProject(pr);
	}

	@Override
	public List<PrIndexPC> judgeProjectDetail(PrIndexPC pp) {
		
		return this.projectDao.judgeProjectDetail(pp);
	}

	@Override
	public int setPageStatus(Map<String, Object> map) {
		int pageNo = (int) map.get("pageNo");
		int offset = (pageNo-1)*15;
		map.put("offset", offset);
		map.put("pageSize", 15);
		return pageNo;
	}

	@Override
	public Page<Project> selectProjectList(Map<String, Object> map) {
		int pageNum = setPageStatus(map);
		int totals = this.projectDao.getCount();
		System.out.println(totals);
		List<Project> prList = this.projectDao.selectProjectList(map);
		int prSize = prList.size();
		String uName=null;
		String shenjiName=null;
		String ckjlName=null;
		String ckfjlName=null;
		String zgysName=null;
	
		for(int i=0;i<prSize;i++){
			Project project = prList.get(i);
			uName = this.projectDao.getPersonNameById(project.getuId());
			shenjiName = this.projectDao.getPersonNameById(project.getShenjiId());
			ckjlName = this.projectDao.getPersonNameById(project.getCkjlId());
			ckfjlName = this.projectDao.getPersonNameById(project.getCkfjlId());
			zgysName = this.projectDao.getPersonNameById(project.getZgysId());
			project.setuName(uName);
			project.setShenjiName(shenjiName);
			project.setCkjlName(ckjlName);
			project.setCkfjlName(ckfjlName);
			project.setZgysName(zgysName);
			prList.set(i, project);
		}
		Page<Project> projectPage = new Page<Project>(prList,totals,pageNum,15,prSize);
		return projectPage;
	}
	
	public Map<String, Object> getProjectList(Map<String, Object> map) {
		int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = projectDao.getProjectCount(map);
		List<Project> projectList = projectDao.getProjectList(map);
		int prSize = projectList.size();
		String uName=null;
		String shenjiName=null;
		String ckjlName=null;
		String ckfjlName=null;
		String zgysName=null;	
		for(int i=0;i<prSize;i++){
			Project project = projectList.get(i);
			uName = this.projectDao.getPersonNameById(project.getuId());
			shenjiName = this.projectDao.getPersonNameById(project.getShenjiId());
			ckjlName = this.projectDao.getPersonNameById(project.getCkjlId());
			ckfjlName = this.projectDao.getPersonNameById(project.getCkfjlId());
			zgysName = this.projectDao.getPersonNameById(project.getZgysId());
			project.setuName(uName);
			project.setShenjiName(shenjiName);
			project.setCkjlName(ckjlName);
			project.setCkfjlName(ckfjlName);
			project.setZgysName(zgysName);
			projectList.set(i, project);
		}
		Map<String, Object> projectpages = new HashMap<String, Object>();
		projectpages.put("total", totals);
		projectpages.put("rows", projectList);
		return projectpages;
	}
	@Override
	public Map<String, Object> getPrIndexPCList(Map<String, Object> map) {
		int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = this.projectDao.getPrIndexPCCount(map);
		List<PrIndexPC> pipList = this.projectDao.getPrIndexPCList(map);
		Map<String, Object> projectDetailpages = new HashMap<String, Object>();
		projectDetailpages.put("total", totals);
		projectDetailpages.put("rows", pipList);
		return projectDetailpages;
	}
	@Override
	public int getCount() {
	
		return this.projectDao.getCount();
	}

	@Override
	public int getPrCode() {
		return this.projectDao.getPrCode();
	}

	@Override
	public List<Project> getAllProjectName() {
		
		return this.projectDao.getAllProjectName();
	}

	@Override
	public List<Project> getProjectListByUserLoginName(String userLoginName) {
		
		return this.projectDao.getProjectListByUserLoginName(userLoginName);
	}

	//查看工程名称对应的工程
	public List<Project> getProjectByPrName(String prName){
		return projectDao.getProjectByPrName(prName);
	}
	
	//查看工程名称和栋号对应的工程PrindexPc
	public List<PrIndexPC> getPrIndexPCByOption(String prId,String unitNumber){
		return projectDao.getPrIndexPCByOption(prId, unitNumber);
	}

}
