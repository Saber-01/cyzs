package com.org.cygs.dao;

import java.util.List;

import com.org.cygs.pojo.InProject;
import com.org.cygs.pojo.InProjectVo;

public interface InProjectDao {

	List<InProjectVo> selectInPrPcByInPrId(String inPrId);
    
    List<InProjectVo> selectByUid(String uid);
    
    List<InProject> selectInPrByUid(String uid);
}