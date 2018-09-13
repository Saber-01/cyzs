package com.org.cygs.dao;

import java.util.List;

import com.org.cygs.pojo.ZMissionContent;

public interface ZMissionContentDao {

    void insertSelective(ZMissionContent record);
    
    List<ZMissionContent> selectZMissionContent(String zMId);
    
    List<ZMissionContent> selectZHTNQTMissionContent(String zMId);
    
}