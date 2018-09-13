package com.org.cygs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.DepartmentDao;
import com.org.cygs.dao.InCheckUnitPoDao;
import com.org.cygs.dao.InProjectDao;
import com.org.cygs.dao.ProjectDao;
import com.org.cygs.dao.ProjectIndexSDao;
import com.org.cygs.pojo.Department;
import com.org.cygs.pojo.InCheckUnit;
import com.org.cygs.pojo.InProject;
import com.org.cygs.pojo.InProjectVo;
import com.org.cygs.pojo.Project;
import com.org.cygs.service.LockInfoService;

@Service("lockInfoService") 
public class LockInfoServiceImpl implements LockInfoService {

	@Resource
	private ProjectIndexSDao projectIndexSDao;
	@Resource
	private InProjectDao inProjectDao;
	@Resource
	private ProjectDao projectDao;
	@Resource
	private DepartmentDao departmentDao;
	@Resource
	private InCheckUnitPoDao inCheckUnitPoDao;
	

	/*
	public Project selectPrByPrimaryKey(String prId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Department> selectDpNameandCode() {
		return departmentDao.selectDpNameandCode();
	}

	public Department selectDpByCode(String dpCode) {
		return departmentDao.selectDpByCode(dpCode);
	}
*/
	public List<Project> selectAllProject() {
		return projectDao.getAllProject();
	}

	public List<InProjectVo> selectByUid(String uid) {
		return inProjectDao.selectByUid(uid);
	}

	public List<InProject> selectInPrByUid(String uid) {
		return inProjectDao.selectInPrByUid(uid);
	}
	
	public List<InProjectVo> selectInPrPcByInPrId(String inPrId) {
		return inProjectDao.selectInPrPcByInPrId(inPrId);
	}

	public List<InCheckUnit> selectAllInCu() {
		return inCheckUnitPoDao.selectAllInCu();
	}

	public InCheckUnit selectInCuByPrimaryKey(String inCuId) {
		return inCheckUnitPoDao.selectInCuByPrimaryKey(inCuId);
	}

}
