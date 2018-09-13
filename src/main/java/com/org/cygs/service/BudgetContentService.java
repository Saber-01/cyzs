package com.org.cygs.service;

import java.util.List;

import com.org.cygs.pojo.BudgetContent;

public interface BudgetContentService {
	    public List<BudgetContent> selectBudgetContents(BudgetContent budgetContent) ;
	    
	    public List<BudgetContent> selectBudgetContentByBId(String bId) ;
	    
	    public void addBudgetContent(BudgetContent budgetContent);
	    
	    public void deleteBudgetContentByBId(String bId);
}
