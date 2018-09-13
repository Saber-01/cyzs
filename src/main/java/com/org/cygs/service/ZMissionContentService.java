package com.org.cygs.service;

import java.util.List;

import com.org.cygs.pojo.ZMissionContent;

public interface ZMissionContentService {
	public List<ZMissionContent> selectZMissionContent(String zMId);
	public List<ZMissionContent> selectZHTNQTMissionContent(String zMId);
}
