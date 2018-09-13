package com.org.cygs.dao;

import java.util.List;

import com.org.cygs.pojo.Department;

public interface DepartmentDao {
    int deleteByPrimaryKey(String dpId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(String dpId);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
    
    List<Department> selectDpNameandCode();
    
    Department selectDpByCode(String dpCode);
}