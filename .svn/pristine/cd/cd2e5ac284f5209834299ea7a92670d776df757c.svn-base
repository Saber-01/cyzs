package com.org.cygs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.cygs.dao.BudgetContentDao;
import com.org.cygs.pojo.BudgetContent;
import com.org.cygs.service.BudgetContentService;

@Service("budgetContentService")
public class BudgetContentServiceImp implements BudgetContentService{
	@Resource
	private BudgetContentDao budgetContentDao;
	
	public List<BudgetContent> selectBudgetContents(BudgetContent budgetContent){
		return budgetContentDao.selectBudgetContents(budgetContent);
	}
	
	public List<BudgetContent> selectBudgetContentByBId(String bId) {
		return budgetContentDao.selectBudgetContentByBId(bId);
	}
	
	
	public void addBudgetContent(BudgetContent budgetContent){
		budgetContentDao.addBudgetContent(budgetContent);
	}
	
	public void deleteBudgetContentByBId(String bId){
		budgetContentDao.deleteBudgetContentByBId(bId);
	}
}
