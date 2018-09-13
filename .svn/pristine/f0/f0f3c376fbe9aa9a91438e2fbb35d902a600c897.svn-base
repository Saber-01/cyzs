package com.org.cygs.service.impl;

import java.util.List;




import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.DepartmentDao;
import com.org.cygs.pojo.Department;
import com.org.cygs.service.DepartmentService;

@Service("DepartmentService")
public class DepartmentServiceImpl implements DepartmentService{

	@Resource
	private DepartmentDao departmentDao;
	
	@Override
	public List<Department> selectDpNameandCode() {
		
		return this.departmentDao.selectDpNameandCode();
	}

	@Override
	public Department selectDpByCode(String dpCode) {
		
		return this.departmentDao.selectDpByCode(dpCode);
	}

}
