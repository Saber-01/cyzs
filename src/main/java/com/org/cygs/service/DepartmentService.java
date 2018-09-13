package com.org.cygs.service;

import java.util.List;

import com.org.cygs.pojo.Department;

public interface DepartmentService {
	
	 List<Department> selectDpNameandCode();
	    
	 Department selectDpByCode(String dpCode);
}
