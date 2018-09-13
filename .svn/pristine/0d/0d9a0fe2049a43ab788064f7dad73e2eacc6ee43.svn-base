package com.org.cygs.service;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.Contract;
import com.org.cygs.pojo.Mission;
import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.PagePojo;

public interface ContractService {
	public void updateContract(Contract contract);
	
	public void deleteContract(String opId);
	
	public void insertContract(List<Contract> contracts);
	
	public Contract selectContractByOpId(String opId);
		
	public List<Contract>  selectContracts(Contract contract);
    
	public Page<Contract> selectContractList(Map<String, Object> map);
	
	public Page<Contract> selectPriceAndYkl(Map<String, Object> map);
	
	public PagePojo<Contract> selectContract(int pageNum, int pageSize, Contract contract);
	
	public void updateBIdNull(String opId);
	
	public void updateBIdNullByBId(String bId);
	
	public void addContract(Contract contract);
	
	public Map<String, Object> selectPrice(Map<String, Object> map);
	
	public Map<String, Object> selectPriceAndYkl1(Map<String, Object> map);
	
	public int getContractCount(Map<String, Object> map);
	
	public List<Contract> selectContractListNew(Map<String, Object> map);
	
	public List<Contract> selectContractByBID(String bId);
	public void updateBIdOfPrice(Contract contract);
	
	public void updateBIdByBID(String bIdNew,String bIdOld);
	
}
