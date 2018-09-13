package com.org.cygs.dao;

import com.org.cygs.pojo.MissionDelete;

public interface MissionDeleteDao {
	
    void insertMissionDelete(MissionDelete record);
    
    String getMdIdByMax();
    
    String getMdMCode(String mCode);
    
}