package com.org.cygs.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class YklHistory {

	private String yklId;
	private String opId;
	
	private String unId;
	private String unName;
	
	private String psId;
	private String psName;
	
	private String pId;
	private String pName;
	
	private String pcPId;
	private String unitNumber;
	
	private String oId;
	private String oName;
	
	private String prId;
	private String prName;
	
	private String cuId;
	private String cuName;
	
	private String bId;
	
	private Double price;
	private Integer isBudget;
	private Double accumulateSum;
	private Double budgetSum;
	private String mender;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	private Date auditTime;
	
	
	public String getUnId() {
		return unId;
	}
	public void setUnId(String unId) {
		this.unId = unId;
	}
	public String getUnName() {
		return unName;
	}
	public void setUnName(String unName) {
		this.unName = unName;
	}
	public String getPsId() {
		return psId;
	}
	public void setPsId(String psId) {
		this.psId = psId;
	}
	public String getPsName() {
		return psName;
	}
	public void setPsName(String psName) {
		this.psName = psName;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getPcPId() {
		return pcPId;
	}
	public void setPcPId(String pcPId) {
		this.pcPId = pcPId;
	}
	public String getUnitNumber() {
		return unitNumber;
	}
	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}
	public String getoId() {
		return oId;
	}
	public void setoId(String oId) {
		this.oId = oId;
	}
	public String getoName() {
		return oName;
	}
	public void setoName(String oName) {
		this.oName = oName;
	}
	public String getPrId() {
		return prId;
	}
	public void setPrId(String prId) {
		this.prId = prId;
	}
	public String getPrName() {
		return prName;
	}
	public void setPrName(String prName) {
		this.prName = prName;
	}
	public String getCuId() {
		return cuId;
	}
	public void setCuId(String cuId) {
		this.cuId = cuId;
	}
	public String getCuName() {
		return cuName;
	}
	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	public String getbId() {
		return bId;
	}
	public void setbId(String bId) {
		this.bId = bId;
	}
	public String getYklId() {
		return yklId;
	}
	public void setYklId(String yklId) {
		this.yklId = yklId;
	}
	public String getOpId() {
		return opId;
	}
	public void setOpId(String opId) {
		this.opId = opId;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getIsBudget() {
		return isBudget;
	}
	public void setIsBudget(Integer isBudget) {
		this.isBudget = isBudget;
	}
	public Double getAccumulateSum() {
		return accumulateSum;
	}
	public void setAccumulateSum(Double accumulateSum) {
		this.accumulateSum = accumulateSum;
	}
	public Double getBudgetSum() {
		return budgetSum;
	}
	public void setBudgetSum(Double budgetSum) {
		this.budgetSum = budgetSum;
	}
	public String getMender() {
		return mender;
	}
	public void setMender(String mender) {
		this.mender = mender;
	}  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	
	public void setProperty(OperationPriceVo opo){
		this.unId = opo.getUnId();
		this.unName = opo.getUnName();
		this.bId = opo.getbId();
		this.cuId = opo.getCuId();
		this.cuName = opo.getCuName();
		this.oId = opo.getoId();
		this.oName = opo.getoName();
		this.pcPId = opo.getPcPId();
		this.unitNumber = opo.getUnitNumber();
		this.prId = opo.getPrId();
		this.prName = opo.getPrName();
		this.price = opo.getPrice();
		this.pId = opo.getpId();
		this.pName = opo.getpName();
		this.accumulateSum = opo.getAccumulateSum();
		this.isBudget = opo.getIsbudget();
		this.opId = opo.getOpId();
		this.psId = opo.getPsId();
		this.psName = opo.getPsName();
		
	}
}
