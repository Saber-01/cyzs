package com.org.cygs.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BudgetVo {

	private String bId;

    private String prId;
    private String prName;

    private String pcPId;
    private String unitNumber;
    
    private String pId;
    private String pName;

    private String unId;
    private String unName;

    private String budgetPos;//Ԥ�㹤�̲�λ������1��

    private String budgetName;//Ԥ���Զ������ƣ�����2��

    private Double budgetAccount;//Ԥ�㹤����
    private Double jjykAmount;//�Ƽ��ѿ�������
    private Double gclcAmount;//��������
    private Double jjysAmount;//�Ƽ����󹤳���

    private String uId;
    private String uName;
    
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
    private Date budgetTime;

    private String remark;

	public String getbId() {
		return bId;
	}

	public void setbId(String bId) {
		this.bId = bId;
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

	public String getBudgetPos() {
		return budgetPos;
	}

	public void setBudgetPos(String budgetPos) {
		this.budgetPos = budgetPos;
	}

	public String getBudgetName() {
		return budgetName;
	}

	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}

	public Double getBudgetAccount() {
		return budgetAccount;
	}

	public void setBudgetAccount(Double budgetAccount) {
		this.budgetAccount = budgetAccount;
	}

	public Double getJjykAmount() {
		return jjykAmount;
	}

	public void setJjykAmount(Double jjykAmount) {
		this.jjykAmount = jjykAmount;
	}

	public Double getGclcAmount() {
		return gclcAmount;
	}

	public void setGclcAmount(Double gclcAmount) {
		this.gclcAmount = gclcAmount;
	}

	public Double getJjysAmount() {
		return jjysAmount;
	}

	public void setJjysAmount(Double jjysAmount) {
		this.jjysAmount = jjysAmount;
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
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getBudgetTime() {
		return budgetTime;
	}

	public void setBudgetTime(Date budgetTime) {
		this.budgetTime = budgetTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    
}
