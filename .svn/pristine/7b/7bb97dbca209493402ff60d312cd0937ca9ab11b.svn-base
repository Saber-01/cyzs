package com.org.cygs.service;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.PrIndexPC;
import com.org.cygs.pojo.Project;
import com.org.cygs.pojo.User;

public interface ProjectService {
	
	public List<Project> getAllProject();
	public List<Project> getAllProjectName();//仅仅获取id 与 name
	
	public Project getProjectById(String prId);
	public int projectAdd(Project pr);
	public int projectEdit(Project pr);
	public int projectDelete(String prId);
	public List<User> getAllRole(String roleType);
	public List<PrIndexPC> getProjectDetailById(String prId);
	public int projectDetailDeleteById(String pcPId);
	public int projectDetailEdit(PrIndexPC pp);
	public int projectDetailAdd(PrIndexPC pp);
	public PrIndexPC getPrIndexPCById(String pcPId);
	
	//用于插入或修改时，判断是否重复或空值
	public List<Project> judgeProject(Project pr);
	//用于对工程详细插入、修改时，判断是否重复
	public List<PrIndexPC> judgeProjectDetail(PrIndexPC pp);
	
	public List<Project> getProjectByUId(String uId);
	public List<Project> getProjectByChengkonId(String uId);  //查看成控部经理对应的工程
	public List<Project> getProjectByChengkonFuId(String uId);//查看成控部    --副 --经理对应的工程
	public List<Project> getProjectByZhuguanId(String uId);  //查看主管预算对应的工程
	public List<Project> getProjectByShenJiId(String uId);  //查看审计对应的工程
	public String getUserName(String prId);  //查看工程对应的项目经理名称
	public String getShenJiName(String prId);//查看工程对应的审计名称
	public String getZhuGuanName(String prId); //查看工程对应的主管预算
	public List<Project> getProjectByGongzhang(String uId); // 查看工长对应的工程
	public List<Project> getProjectByYusuan(String uId);	// 查看预算人员对应的工程
	
	
	public int setPageStatus(Map<String, Object> map);   //设置分页数据,返回当前页码
	
	public Page<Project> selectProjectList(Map<String, Object> map);
	public int getPrCode();
	public int getCount();
	
	//根据用户登录名获取与之关联的工程列表
	public List<Project> getProjectListByUserLoginName(String userLoginName);
	

	public List<Project> getProjectByPrName(String prName);  //查看工程名称对应的工程
	

	public Map<String, Object> getProjectList(Map<String, Object> map);

	public List<PrIndexPC> getPrIndexPCByOption(String prId,String unitNumber);  //查看工程名称和栋号对应的工程PrindexPc

	public Map<String,Object> getPrIndexPCList(Map<String,Object> map);
}
