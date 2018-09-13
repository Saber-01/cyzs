package com.org.cygs.dao;



import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.Part;
import com.org.cygs.pojo.PartPosition;

public interface PartPositionDao {
	public void insertPartPosition(PartPosition partPosition);
	
	public PartPosition selectPartPosition(String psId);
	
	public void deletePartPosition(String psId);
	
	public void updatePartPosition(PartPosition partPosition);
	// 新建任务书时使用
	public List<PartPosition> getPartPositionByPId(String pId);
	// 现价合同单价时使用
	public List<PartPosition> selectPartPositionByPId(String pId);
	
	public List<PartPosition> getPartPositionList1(PartPosition partPosition);
	
	public List<PartPosition> getDistinctPsName();
	
	public List<PartPosition> getPsNameByPart(Part part);
	
    public List<PartPosition> getPartPositionList(Map<String, Object> map);
    public int getPartPositionCount(Map<String, Object> map);
   
    public PartPosition getNewPsId();
}
