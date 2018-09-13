package com.org.cygs.service;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.Part;
import com.org.cygs.pojo.PartPosition;
import com.org.cygs.pojo.PartVo;


public interface PartService {
	
	//分部标准
	public void addPart(Part part) ;
	
	public void deletePart(String pId) ;
	
	public void updatePart(Part part) ;
	

	public List<Part> getPartList1(Part part);
	
	public List<Part> getAllPart();  
	    
    public Part getPart(String pId); 
	
    public PagePojo<Part> partPage(int pageNum,int pageSize);
    
    public List<Part> getPartListByPart(Part part);
	//工程部位
    public void addPartPosition(PartPosition partPosition) ;
	
	public void deletePartPosition(String psId) ;
	
	public void updatePartPosition(PartPosition partPosition) ;
	 
	
    public PartPosition getPartPosition(String id); 
    
    public List<PartPosition> getPartPositionByPId(String id);
    
    public List<PartPosition> getDistinctPsName();
    
    public List<PartPosition> getPartPositionList1(PartPosition partPosition); 
    
	public Map<String, Object> getPartPositionList(Map<String, Object> map);
    
    public List<PartPosition> getPsNameByPart(Part part);
    
    public PagePojo<PartPosition> partPositionPage(int pageNum,int pageSize,PartPosition partPosition);
    
    
    // 根据pId查询分部
    public Part getPartById(String pId);
    
    
    public List<PartVo> getPartInfo(String prId, String pcpId, String cuId);
    
    public List<Part> getPartByPcPId(String pcPId);
    
	public Map<String, Object> getPartList(Map<String, Object> map);
	
	public Part getNewPart();
	
	public Part getNewPartByPCode(String pCode);
	
	public String getNewPsId();
	
	public PartPosition getNewPartPosition();
}
