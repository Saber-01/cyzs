package com.org.cygs.dao;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.PrIndexPC;

public interface PrIndexPCDao {
    PrIndexPC selectByPrimaryKey(String pcPId);
    
    // ����prId ��ѯ����
    List<PrIndexPC> selectUnitByPrId(String prId);
    
    // ���ݹ��̼��ϲ�ѯ����
    List<PrIndexPC> getUnitNumberByPrList(Map<String, Object> map);
}