package com.org.cygs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.ZProjectDao;
import com.org.cygs.pojo.ZProject;
import com.org.cygs.service.ZProjectService;

@Service
public class ZProjectServiceImpl implements ZProjectService {
	@Resource
	private ZProjectDao zProjectDao;
	
	
	public List<ZProject> selectAllZProject() {
		return zProjectDao.selectAllZProject();
	}

}
