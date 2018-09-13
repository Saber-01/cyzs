package com.org.cygs.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.OperationPriceDao;
import com.org.cygs.pojo.BudgetVo;
import com.org.cygs.pojo.OperationPrice;
import com.org.cygs.pojo.OperationPriceVo;
import com.org.cygs.pojo.Page;
import com.org.cygs.pojo.Unit;
import com.org.cygs.pojo.YklHistory;
import com.org.cygs.service.OperationPriceService;

@Service("operationPriceService")
public class OperationPriceServiceImpl implements OperationPriceService {

	@Resource
	OperationPriceDao operationPriceDao;
	
	@Override
	public OperationPrice selectByPrimaryKey(String opId) {
		return operationPriceDao.selectByPrimaryKey(opId);
	}

	@Override
	public List<OperationPriceVo> selectAllByOpid(String opId) {
		return operationPriceDao.selectAllByOpid(opId);
	}

	@Override
	public List<OperationPriceVo> getYKL(Map<String, Object> map) {
		
		return this.operationPriceDao.getYKL(map);
	}

	@Override
	public int editBudgetSum(Map<String, Object> map) {
		
		return this.operationPriceDao.editBudgetSum(map);
	}

	@Override
	public OperationPriceVo getOperationPriceVoByKey(String opId) {
		
		return this.operationPriceDao.getOperationPriceVoByKey(opId);
	}

	@Override
	public int addYKLmendRecord(YklHistory yh) {
		
		return this.operationPriceDao.addYKLmendRecord(yh);
	}

	@Override
	public List<YklHistory> getYklHistory(Map<String, Object> map) {
		
		return this.operationPriceDao.getYklHistory(map);
	}

	@Override
	public int setPageStatus(Map<String, Object> map) {
		int pageNo = (int)map.get("pageNo");
		int offset = (pageNo-1)*15;
		map.put("offset", offset);
		map.put("pageSize", 15);
		return pageNo;
	}

	@Override
	public Page<YklHistory> selectYKLHistoryList(Map<String, Object> map) {
		int pageNum = setPageStatus(map);
		int totals = this.operationPriceDao.getYKLHistoryCount(map);
		List<YklHistory> ykl = this.operationPriceDao.getYklHistory(map);
		Page<YklHistory> yklPage = new Page<YklHistory>(ykl,totals,pageNum,15,ykl.size());
		return yklPage;
	}

	@Override
	public int getYKLHistoryCount(Map<String,Object> map) {
		
		return this.operationPriceDao.getYKLHistoryCount(map);
	}

	@Override
	public List<OperationPrice> selectPriceByOid(String oId) {
		return operationPriceDao.selectPriceByOid(oId);
	}

	@Override
	public List<OperationPriceVo> selectOperationPrice(String prId, String cuId, String pcpId) {
		return operationPriceDao.selectOperationPrice(prId, cuId, pcpId);
	}

	/*@Override
	public List<OperationPriceVo> queryUnitInfoByOid(String oId) {
		return operationPriceDao.queryUnitInfoByOid(oId);
	}

	@Override
	public List<OperationPriceVo> selectUnitPriceByOid(String oId) {
		return operationPriceDao.selectUnitPriceByOid(oId);
	}*/
	
	
	public List<OperationPriceVo> queryUnitBySeven(String prId, String pcpId, String pId, String psId, String oId, String unId, String cuId) {
		return operationPriceDao.queryUnitBySeven(prId, pcpId, pId, psId, oId, unId, cuId);
	}

	@Override
	public List<OperationPriceVo> queryOperationPriceUnit(String prId, String pcpId, String pId, String psId, String oId, String cuId) {
		return operationPriceDao.queryOperationPriceUnit(prId, pcpId, pId, psId, oId, cuId);
	}

	//获取已开已审工程量信息
	@Override
	public List<OperationPriceVo> getYklBudgetSum(Map<String, Object> map) {
		List<OperationPriceVo> opo = this.operationPriceDao.getYKL(map);
		for(OperationPriceVo op:opo){
			op.setRealSum(this.operationPriceDao.getYkgcl(op));
		}
		return opo;
	}

	@Override
	public List<BudgetVo> getBudgetVo(Map<String, String> map) {
		List<BudgetVo> bvList = this.operationPriceDao.getBudgetVo(map);
		System.out.println("budgetVo count:"+ bvList.size());
		Double jjyk = 0.0;//计件已开工程量
		Double jjys = 0.0;//计件已审工程量
		Double gclc = 0.0;//工程量差
		Double temp = 0.0;
		for(BudgetVo bv:bvList){
			List<OperationPrice> opList = getOperationPriceByBudget(bv);
			for(OperationPrice op:opList){
				if((temp=this.operationPriceDao.getJjyk(op))!=null){
					jjyk += temp;
				}
				if((temp=this.operationPriceDao.getJjys(op))!=null){
					jjys += temp;
				}
			}
			gclc = bv.getBudgetAccount()-jjyk;
			bv.setGclcAmount(gclc);
			bv.setJjykAmount(jjyk);
			bv.setJjysAmount(jjys);
			jjyk = 0.0;
			jjys = 0.0;
			temp = 0.0;
			gclc = 0.0;
		}
		return bvList;
	}

	@Override
	public List<OperationPrice> getOperationPriceByBudget(BudgetVo bv) {
		
		return this.operationPriceDao.getOperationPriceByBudget(bv);
	}

	@Override
	public Map<String, Object> getYKLList(Map<String, Object> map) {
		List<OperationPriceVo> opList = operationPriceDao.getYKL(map);
		Double budget = 0.0;
		Double accu = 0.0;
		Double temp = 0.0;
		for(int i=0;i<opList.size();i++){
			if((temp=opList.get(i).getAccumulateSum())!=null){
				accu = accu + temp;
			}else{
				opList.get(i).setAccumulateSum(0.0);
			}
			if((temp=opList.get(i).getBudgetSum())!=null){
				budget = budget + temp;
			}else{
				opList.get(i).setBudgetSum(0.0);
			}
		}
		//创建一个OperationPriceVo对象，将合计的已结、预控量总和分别放入accumulateSum、budgetSum属性中，便于数据传输！
		if(opList.size()>0){
			OperationPriceVo opo = new OperationPriceVo();
			opo.setAccumulateSum(accu);
			opo.setBudgetSum(budget);
			opo.setpName("合计");
			opList.add(opo);
		}
		Map<String, Object> yklpages = new HashMap<String, Object>();
		yklpages.put("rows", opList);
		return yklpages;
	}

	@Override
	public Map<String, Object> selectYklBudgetSum(Map<String, Object> map) {
		int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = operationPriceDao.getYKLCount(map);
		List<OperationPriceVo> opList = operationPriceDao.getYKL(map);
		for(OperationPriceVo op:opList){
			op.setRealSum(this.operationPriceDao.getYkgcl(op));
		}
		int size = opList.size();
		Double budgetSum=0.0;//求预控量总和
		Double accuSum=0.0;//求已结总和
		Double realQuantity=0.0;//求已开总和
		OperationPriceVo op = new OperationPriceVo();
		for(int i=0;i<size;i++){
			if(opList.get(i).getBudgetSum()!=null){
				budgetSum += opList.get(i).getBudgetSum();
			}else{
				opList.get(i).setBudgetSum(0.0);
			}
			if(opList.get(i).getAccumulateSum()!=null){
				accuSum += opList.get(i).getAccumulateSum();
			}else{
				opList.get(i).setAccumulateSum(0.0);
			}
			if(opList.get(i).getRealSum()!=null){
				realQuantity += opList.get(i).getRealSum();
			}else{
				opList.get(i).setRealSum(0.0);
			}
		}
		if(size>0){
			op.setAccumulateSum(accuSum);
			op.setBudgetSum(budgetSum);
			op.setRealSum(realQuantity);
			op.setpName("合计");
			opList.add(op);
		}
		Map<String, Object> yklBudgetpages = new HashMap<String, Object>();
		yklBudgetpages.put("total", totals);
		yklBudgetpages.put("rows", opList);
		return yklBudgetpages;
	}

	@Override
	public Map<String, Object> getYKLHistoryList(Map<String, Object> map) {
		int pageNo = Integer.parseInt((String) map.get("page"));
		int pageSize = Integer.parseInt((String) map.get("rows"));
		int offset = (pageNo-1)*pageSize;
		map.put("offset", offset);
		map.put("pageSize", pageSize);
		int totals = this.operationPriceDao.getYKLHistoryCount(map);
		System.out.println("sum:"+totals + "??");
		List<YklHistory> yklHistory = this.operationPriceDao.getYklHistory(map);
		Map<String, Object> ykl = new HashMap<String, Object>();
		ykl.put("total", totals);
		ykl.put("rows", yklHistory);
		return ykl;
	}
	
}
