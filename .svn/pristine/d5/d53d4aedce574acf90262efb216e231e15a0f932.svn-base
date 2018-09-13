package com.org.cygs.service;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.BudgetVo;
import com.org.cygs.pojo.OperationPrice;
import com.org.cygs.pojo.OperationPriceVo;
import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.YklHistory;

public interface OperationPriceService {
	public OperationPrice selectByPrimaryKey(String opId);
	public List<OperationPriceVo> selectAllByOpid(String opId);
	
	//根据工程和楼栋号获取预控量信息
	List<OperationPriceVo> getYKL(Map<String,Object> map);
	
	//修改预控量
	int editBudgetSum(Map<String,Object>map);

	//根据opId获取OperetionPriceVo
	OperationPriceVo getOperationPriceVoByKey(String opId);
	
	//新增预控量修改记录
	int addYKLmendRecord(YklHistory yh);
	
	//根据工程、楼栋号与结算单位，获取所有的预控量修改记录
	List<YklHistory> getYklHistory(Map<String,Object> map);
	//easyUI
	Map<String,Object> getYKLList(Map<String,Object> map);
	
	public int setPageStatus(Map<String, Object> map);   //设置分页数据,返回当前页码
	
	public Page<YklHistory> selectYKLHistoryList(Map<String, Object> map);
	
	public Map<String,Object> getYKLHistoryList(Map<String,Object> map);
	//获取预控量修改记录的条数
	public int getYKLHistoryCount(Map<String,Object> map);
	
	//获取已开已审工程量 信息
	List<OperationPriceVo> getYklBudgetSum(Map<String,Object> map);
	//easyUI，获取已开已审工程量信息
	Map<String,Object> selectYklBudgetSum(Map<String,Object> map);

	//根据工程、楼栋号以及分部，获取所有的BudgetVo
	List<BudgetVo> getBudgetVo(Map<String,String> map);
	//根据budgetVo获取所有的operationPrice
	List<OperationPrice> getOperationPriceByBudget(BudgetVo bv);
	
	List<OperationPrice> selectPriceByOid(String oId);
	
	public List<OperationPriceVo> selectOperationPrice(String prId, String cuId, String pcpId);
	
	//public List<OperationPriceVo> queryUnitInfoByOid(String oId);
	
	//public List<OperationPriceVo> selectUnitPriceByOid(String oId);
	
	public List<OperationPriceVo> queryOperationPriceUnit(String prId, String pcpId, String pId, String psId, String oId, String cuId);
	
	public List<OperationPriceVo> queryUnitBySeven(String prId, String pcpId, String pId, String psId, String oId, String unId, String cuId);

}
