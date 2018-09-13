package com.org.cygs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.org.cygs.dao.ContractDao;
import com.org.cygs.pojo.Budget;
import com.org.cygs.pojo.Contract;
import com.org.cygs.pojo.Job;
import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.PagePojo;
import com.org.cygs.service.ContractService;
import com.org.cygs.service.MissionContentService;

@Service("contractService")
public class ContractServiceImpl implements ContractService{
	@Resource
	private ContractDao  contractDao;
	@Autowired
	private MissionContentService missionContentService;
	
	
	public void updateContract(Contract contract){
		contractDao.updateContract(contract);
	}
	
	public void deleteContract(String opId){
		contractDao.deleteContractByBatch(opId);
	}
	
	public void insertContract(List<Contract> contracts){
		contractDao.insertContractByBatch(contracts);
	}
	
	public Contract selectContractByOpId(String opId){
		Contract contract=new Contract();
		contract.setOpId(opId);
		List<Contract> contracts=contractDao.selectContracts(contract);
		Contract contract2=contracts.get(0);
		return contract2;
	}
	
	public List<Contract>  selectContracts(Contract contract){
		return contractDao.selectContracts(contract);
	}
	
	public Page<Contract> selectContractList(Map<String, Object> map){
		int pageNo = (int) map.get("pageNo");
		int offset = (pageNo-1)*15;
		map.put("offset", offset);
		map.put("pageSize", 15);
		int pageNum = pageNo;
		int totals = contractDao.getContractCount(map);
		System.out.println(totals);
		List<Contract> contractList = contractDao.selectContractList(map);		
		Page<Contract> contractPage= new Page<Contract>(contractList,totals,pageNum,15,contractList.size());
		return contractPage;
	}
	
	public Page<Contract> selectPriceAndYkl(Map<String, Object> map){
		int pageNo = (int) map.get("pageNo");
		int offset = (pageNo-1)*15;
		map.put("offset", offset);
		map.put("pageSize", 15);
		int pageNum = pageNo;
		int totals = contractDao.getContractCount(map);
		System.out.println(totals);
		List<Contract> cList = contractDao.selectContractList(map);
        if (cList !=null&&cList.size()>0) {
        	for (int i = 0; i < cList.size(); i++){
        		
            	String pcpId=cList.get(i).getPcpId();
            	String oId=cList.get(i).getJobKey();
                String cuId=cList.get(i).getCuId();             
                //根据任务书内容查找已开量与已审量,将已开量和已审量写入数组中
                Float ykl=missionContentService.selectYKRealQuantity( cuId, pcpId, oId);
                if(ykl != null)
                	 cList.get(i).setYkl(ykl);
                else
                	 cList.get(i).setYkl(0);
                
                Float ysl=missionContentService.selectYSRealQuantity( cuId, pcpId, oId);               
                if(ysl !=null)
                     cList.get(i).setYsl(ysl);
                else 
                	 cList.get(i).setYsl(0);			
        	}
            
		}
		Page<Contract> contractPage= new Page<Contract>(cList,totals,pageNum,15,cList.size());
		return contractPage;
		
	}
	
	public Map<String, Object> selectPrice(Map<String, Object> map){
		System.out.println(map.toString());
		int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = contractDao.getContractCount(map);
		System.out.println(totals);
		List<Contract> cList = contractDao.selectContractList(map);
		
		if (cList !=null&&cList.size()>0) {
        	for (int i = 0; i < cList.size(); i++){
        		
            	String pcpId=cList.get(i).getPcpId();
            	String oId=cList.get(i).getJobKey();
                String cuId=cList.get(i).getCuId();             
                //根据任务书内容查找已开量与已审量,将已开量和已审量写入数组中
                Float ykl = missionContentService.selectYKRealQuantity( cuId, pcpId, oId);
                if(ykl != null)
                	 cList.get(i).setYkl(ykl);
                else
                	 cList.get(i).setYkl((float) 0);
                
                Float ysl = missionContentService.selectYSRealQuantity( cuId, pcpId, oId);               
                if(ysl !=null)
                     cList.get(i).setYsl(ysl);
                else 
                	 cList.get(i).setYsl((float) 0);			
        	}
            
		}
		
		Map<String, Object> pages = new HashMap<String, Object>();
		pages.put("total", totals);
		pages.put("rows", cList);
		return pages;
	}
	
	public Map<String, Object> selectPriceAndYkl1(Map<String, Object> map){
		System.out.println(map.toString());
		int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = contractDao.getContractCount(map);
		System.out.println(totals);
		List<Contract> cList = contractDao.selectContractList(map);
		if (cList !=null&&cList.size()>0) {
        	for (int i = 0; i < cList.size(); i++){
        		
            	String pcpId=cList.get(i).getPcpId();
            	String oId=cList.get(i).getJobKey();
                String cuId=cList.get(i).getCuId();             
                //根据任务书内容查找已开量与已审量,将已开量和已审量写入数组中
                Float ykl=missionContentService.selectYKRealQuantity( cuId, pcpId, oId);
                if(ykl != null)
                	 cList.get(i).setYkl(ykl);
                else
                	 cList.get(i).setYkl(0);
                
                Float ysl=missionContentService.selectYSRealQuantity( cuId, pcpId, oId);               
                if(ysl !=null)
                     cList.get(i).setYsl(ysl);
                else 
                	 cList.get(i).setYsl(0);			
        	}
            
		}
		Map<String, Object> pages = new HashMap<String, Object>();
		pages.put("total", totals);
		pages.put("rows", cList);
		return pages;
	}
	
	
	public PagePojo<Contract> selectContract(int pageNum, int pageSize, Contract contract){
		PageHelper.startPage(pageNum, pageSize);
    	List<Contract> cList = contractDao.selectContracts(contract);
    	return new PagePojo<>(cList);
	}
	
	public void updateBIdNull(String opId){
		contractDao.updateBIdNull(opId);
	}
	
	public void updateBIdNullByBId(String bId){
		contractDao.updateBIdNullByBId(bId);
	}
	public void addContract(Contract contract){
		contractDao.addContract(contract);
	}
	
	
    public int getContractCount(Map<String, Object> map){
    	return contractDao.getContractCount(map);
    }
	
	public List<Contract> selectContractListNew(Map<String, Object> map){
		return contractDao.selectContractList(map);
	}
	
	public List<Contract> selectContractByBID(String bId){
		return contractDao.selectContractByBID(bId);
	}
	
	public void updateBIdOfPrice(Contract contract){
		contractDao.updateBIdOfPrice(contract);
	}
	
	public void updateBIdByBID(String bIdNew,String bIdOld){
		contractDao.updateBIdByBID(bIdNew, bIdOld);
	}
}
