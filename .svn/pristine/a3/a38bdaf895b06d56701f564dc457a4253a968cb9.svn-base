package com.org.cygs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.org.cygs.dao.PartDao;
import com.org.cygs.dao.PartPositionDao;
import com.org.cygs.pojo.MissionType;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.pojo.Part;
import com.org.cygs.pojo.PartPosition;
import com.org.cygs.pojo.PartVo;
import com.org.cygs.service.PartService;

@Service
public class PartServiceImpl implements PartService {
	@Resource
	private PartDao partDao;
	
	@Resource
	private PartPositionDao partPositionDao;
	
	
	//分部标准操作
	
	public void addPart(Part part){
		partDao.insertPart(part);	
	}

	public void deletePart(String pId){
		partDao.deletePart(pId);
	}
	
	public void updatePart(Part part){
		partDao.updatePart(part);
		
	}
	
	public List<Part> getAllPart(){
		
		return partDao.getAllPart();
	}
	
	public List<Part> getPartList1(Part part){
		return partDao.getPartList1(part);
	}
	
	
	 public Part getPart(String pId){
		return partDao.selectPart(pId);
	}
	
    public PagePojo<Part> partPage(int pageNum,int pageSize){
    	PageHelper.startPage(pageNum, pageSize);
    	List<Part> partList=partDao.getAllPart();
    	System.out.println(partList.size());
    	return new PagePojo<>(partList);
    }
    
	public Map<String, Object> getPartList(Map<String, Object> map) {
		int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = partDao.getPartCount(map);
		List<Part> partList = partDao.getPartList(map);
		Map<String, Object> partpages = new HashMap<String, Object>();
		partpages.put("total", totals);
		partpages.put("rows", partList);
		return partpages;
	}
	
    
	//根据主键查询分部
	public Part getPartById(String pId) {
		return partDao.getPartById(pId);
	}
	
	public List<Part> getPartListByPart(Part part){
		return partDao.getPartListByPart(part);
	}
	//工程部位操作
	
	
	public void addPartPosition(PartPosition partPosition){
		partPositionDao.insertPartPosition(partPosition);	
	}

	public void deletePartPosition(String psId){
		partPositionDao.deletePartPosition(psId);
	}
	
	public void updatePartPosition(PartPosition partPosition){
		partPositionDao.updatePartPosition(partPosition);
	}
	
	public PartPosition getPartPosition(String psId){
		
		return partPositionDao.selectPartPosition(psId);
	}
	
	
	public List<PartPosition> getPartPositionByPId(String id){
		return partPositionDao.getPartPositionByPId(id);
	}
	
	public List<PartPosition> getPartPositionList1(PartPosition partPosition){
		return partPositionDao.getPartPositionList1(partPosition);
	}

	public Map<String, Object> getPartPositionList(Map<String, Object> map) {
		int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = partPositionDao.getPartPositionCount(map);
		List<PartPosition> partPostionList = partPositionDao.getPartPositionList(map);
		Map<String, Object> partPostionpages = new HashMap<String, Object>();
		partPostionpages.put("total", totals);
		partPostionpages.put("rows", partPostionList);
		return partPostionpages;
	}
	
	public PagePojo<PartPosition> partPositionPage(int pageNum, int pageSize, PartPosition partPosition) {
		PageHelper.startPage(pageNum, pageSize);
		List<PartPosition> pList = partPositionDao.getPartPositionList1(partPosition);
		return new PagePojo<>(pList);
	}
	
	public List<PartVo> getPartInfo(String prId, String pcpId, String cuId) {
		return partDao.getPartInfo(prId, pcpId, cuId);
	}
	
	public List<PartPosition> getPsNameByPart(Part part){
		return partPositionDao.getPsNameByPart(part);
	}

    public List<PartPosition> getDistinctPsName(){
    	return partPositionDao.getDistinctPsName();
    }

	@Override
	public List<Part> getPartByPcPId(String pcPId) {
		
		return this.partDao.getPartByPcPId(pcPId);
	}
	
	public Part getNewPart(){
		return partDao.getNewPart();
	}
	
	public Part getNewPartByPCode(String pCode) {
		return partDao.getNewPartByPCode(pCode);
	}

	public String getNewPsId(){
		return partPositionDao.getNewPsId().getPsId();
	}

	public PartPosition getNewPartPosition(){
		return partPositionDao.getNewPsId();
	}
}
