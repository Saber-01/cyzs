package com.org.cygs.dao;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.Contract;

public interface ContractDao {
	public void updateContract(Contract contract );
	
	public void deleteContractByBatch(String opId);
	
	public void insertContractByBatch(List<Contract> contracts);
	
	
	public List<Contract>  selectContracts(Contract contract);
	
	public List<Contract>  selectContractList(Map<String, Object> map);
	public int  getContractCount(Map<String, Object> map);
	
	public void updateBIdNull(String opId);
	public void updateBIdNullByBId(String bId);
	public void addContract(Contract contract);

	public List<Contract> selectContractByBID(String bId);
	
	public void updateBIdOfPrice(Contract contract);
	
	public void updateBIdByBID(String bIdNew,String bIdOld);
}
