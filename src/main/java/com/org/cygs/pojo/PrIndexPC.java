package com.org.cygs.pojo;

import java.math.BigDecimal;

public class PrIndexPC {

	private String pcPId;
	private String pcId;
	private String prId;
	private String unitNumber;
	private BigDecimal budgetSum;
	private String uId;//预算人员的id
	private String uName;//预算人员的姓名
	public String getPcPId() {
		return pcPId;
	}
	public void setPcPId(String pcPId) {
		this.pcPId = pcPId;
	}
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public String getPrId() {
		return prId;
	}
	public void setPrId(String prId) {
		this.prId = prId;
	}
	public String getUnitNumber() {
		return unitNumber;
	}
	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}
	public BigDecimal getBudgetSum() {
		return budgetSum;
	}
	public void setBudgetSum(BigDecimal budgetSum) {
		this.budgetSum = budgetSum;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	
	
}
