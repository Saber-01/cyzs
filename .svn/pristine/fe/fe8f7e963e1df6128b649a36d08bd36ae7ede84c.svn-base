package com.org.cygs.dao;



import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.MissionType;
import com.org.cygs.pojo.Part;
import com.org.cygs.pojo.PartVo;

public interface PartDao {
	public void insertPart(Part part);
	
	public Part selectPart(String pId);
	
	public void deletePart(String pId);
	
	public void updatePart(Part part);
	
	public List<Part> getAllPart();
	
    public List<Part> getPartList(Map<String, Object> map);
    public int getPartCount(Map<String, Object> map);
	
	public List<Part> getPartList(Part part);
	
	public Part getPartById(String pId);
	
	public List<PartVo> getPartInfo(String prId, String pcpId, String cuId);
	
	public List<Part> getPartByPcPId(String pcPId);
	
	public List<Part> getPartListByPart(Part part);
	
	
	public List<Part> getPartList1(Part part);
	
	public Part getNewPart();
	
	public Part getNewPartByPCode(String pCode);
}
