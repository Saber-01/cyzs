package com.org.cygs.dao;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.PrIndexPC;

public interface PrIndexPCDao {
    PrIndexPC selectByPrimaryKey(String pcPId);
    
    // 根据prId 查询栋号
    List<PrIndexPC> selectUnitByPrId(String prId);
    
    // 根据工程集合查询栋号
    List<PrIndexPC> getUnitNumberByPrList(Map<String, Object> map);
}