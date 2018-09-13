package com.org.cygs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.ZMissionContentDao;
import com.org.cygs.pojo.ZMissionContent;
import com.org.cygs.service.ZMissionContentService;

@Service
public class ZMissionContentServiceImpl implements ZMissionContentService {
	@Resource
	private ZMissionContentDao zMissionContentDao;
	
	
	public List<ZMissionContent> selectZMissionContent(String zMId) {
		return zMissionContentDao.selectZMissionContent(zMId);
	}
	
	public List<ZMissionContent> selectZHTNQTMissionContent(String zMId) {
		return zMissionContentDao.selectZHTNQTMissionContent(zMId);
	}

}
