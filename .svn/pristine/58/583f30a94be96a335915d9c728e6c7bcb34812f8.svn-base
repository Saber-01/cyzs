package com.org.cygs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.BudgetDao;
import com.org.cygs.pojo.Budget;
import com.org.cygs.service.BudgetService;

@Service("budgetService")
public class BudgetServiceImp implements BudgetService {
	@Resource
	private BudgetDao budgetDao;
	
	public Budget selectBudget(String bId){
		return budgetDao.selectBudget(bId);
	}
	
	public void addBudget(Budget budget){
		System.out.println("wo hao fan a");
		budgetDao.addBudget(budget);
	}
   
	public List<Budget> selectBudgetList(Budget budget){
		return budgetDao.selectBudgetList(budget);
	}
	
	public void deleteBudgetByBatch(String[] ids){
		budgetDao.deleteBudgetByBatch(ids);
	}
	
	public void updateBudget(Budget budget){
		budgetDao.updateBudget(budget);
	}
	
	public Budget getNewBudget(){
		return budgetDao.getNewBudget();
	}
	
	
	public void addBudgetTest(Budget budget){
		budgetDao.addBudgetTest(budget);
	}
}
