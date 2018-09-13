package com.org.cygs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.ProjectIndexSDao;
import com.org.cygs.pojo.MissionType;
import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.ProjectIndexS;
import com.org.cygs.service.ProjectIndexSService;

@Service("ProjectIndexSService")
public class ProjectIndexSServiceImpl implements ProjectIndexSService {

	@Resource
	private ProjectIndexSDao projectIndexSDao;
	@Override
	public List<ProjectIndexS> getAllProjectIndexS() {
		
		return this.projectIndexSDao.getAllProjectIndexS();
	}

	@Override
	public ProjectIndexS getProjectIndexSById(String prSId) {
		
		return this.projectIndexSDao.getProjectIndexSById(prSId);
	}

	@Override
	public int projectIndexSAdd(ProjectIndexS pris) {
		
		return this.projectIndexSDao.projectIndexSAdd(pris);
	}

	@Override
	public int projectIndexSEdit(ProjectIndexS pris) {
		
		return this.projectIndexSDao.projectIndexSEdit(pris);
	}

	@Override
	public int projectIndexSDelete(String prsId) {
		
		return this.projectIndexSDao.projectIndexSDelete(prsId);
	}

	@Override
	public List<ProjectIndexS> getAllGzName() {
		
		return this.projectIndexSDao.getAllGzName();
	}

	@Override
	public List<ProjectIndexS> projectIndexSSearch(ProjectIndexS pis) {
		
		return this.projectIndexSDao.projectIndexSSearch(pis);
	}

	@Override
	public List<ProjectIndexS> judgeProjectIndexS(ProjectIndexS pis) {
		
		return this.projectIndexSDao.judgeProjectIndexS(pis);
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
	public Page<ProjectIndexS> selectProjectIndexSList(Map<String, Object> map) {	
		int pageNum = setPageStatus(map);
		int totals = this.projectIndexSDao.getCount(map);
		System.out.println(totals);
		List<ProjectIndexS> pisList = this.projectIndexSDao.selectProjectIndexS(map);
		Page<ProjectIndexS> pisPage = new Page<ProjectIndexS>(pisList,totals,pageNum,15,pisList.size());
		return pisPage;
	}
	
	public Map<String, Object> getProjectIndexSList(Map<String, Object> map) {
		int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = projectIndexSDao.getCount(map);
		List<ProjectIndexS> projectIndexSList = projectIndexSDao.getProjectIndexSList(map);
		Map<String, Object> projectIndexSpages = new HashMap<String, Object>();
		projectIndexSpages.put("total", totals);
		projectIndexSpages.put("rows", projectIndexSList);
		return projectIndexSpages;
	}
	
	
	public List<ProjectIndexS> selectSProject(String uId) {
		return projectIndexSDao.selectSProject(uId);
	}

}
