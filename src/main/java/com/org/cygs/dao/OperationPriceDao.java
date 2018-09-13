package com.org.cygs.dao;

import java.util.List;
import java.util.Map;

import com.org.cygs.pojo.BudgetVo;
import com.org.cygs.pojo.OperationPrice;
import com.org.cygs.pojo.OperationPriceVo;
import com.org.cygs.pojo.YklHistory;

public interface OperationPriceDao {
	
	//根据prId cuId pcpId 查询单价
	List<OperationPriceVo> selectOperationPrice(String prId, String cuId, String pcpId);
	
	OperationPrice selectByPrimaryKey(String opId);
	List<OperationPriceVo> selectAllByOpid(String opId);
	
	//根据工程和楼栋号获取预控量信息
	List<OperationPriceVo> getYKL(Map<String,Object> map);
	
	//根据工程、楼栋号等获取预控量数目
	int getYKLCount(Map<String,Object> map);
	//修改预控量
	int editBudgetSum(Map<String,Object>map);

	//根据opId获取OperetionPriceVo
	OperationPriceVo getOperationPriceVoByKey(String opId);
	
	//新增预控量修改记录
	int addYKLmendRecord(YklHistory yh);
	
	//根据工程、楼栋号与结算单位，获取所有的预控量修改记录
	List<YklHistory> getYklHistory(Map<String,Object> map);
	
	//获取预控量修改记录条目数
	int getYKLHistoryCount(Map<String,Object> map);
	
	//根据工程、楼栋号以及分部，获取所有的BudgetVo
	List<BudgetVo> getBudgetVo(Map<String,String> map);
	//根据budgetVo获取所有的operationPrice
	List<OperationPrice> getOperationPriceByBudget(BudgetVo bv);
	//根据OperationPrice 获取计件已开量
	Double getJjyk(OperationPrice op);
	//根据OperationPrice 获取计件已审量
	Double getJjys(OperationPrice op);
	
	// 根据O_ID查询单价
	List<OperationPrice> selectPriceByOid(String oId);
	//获取每一个分项3的已开工程量
	Double getYkgcl(OperationPriceVo op);
	
	// 根据oId 查询单位信息--新建任务书时使用
	//List<OperationPriceVo> queryUnitInfoByOid(String oId);
	
	// 根据oId 查询单位信息--新建任务书时使用
	//List<OperationPriceVo> selectUnitPriceByOid(String oId);
	
	List<OperationPriceVo> queryOperationPriceUnit(String prId, String pcpId, String pId, String psId, String oId, String cuId);
	
	// 新建任务书--查询已结累计工程量--查单价
	List<OperationPriceVo> queryUnitBySeven(String prId, String pcpId, String pId, String psId, String oId, String unId, String cuId);

}
