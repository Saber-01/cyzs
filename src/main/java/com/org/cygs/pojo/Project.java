package com.org.cygs.pojo;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Project {

	private String prId;//��Ŀid
	private String prName;//��Ŀ����
	private String prCode;//��Ŀ���Ʊ��
	private String uId;//��Ŀ����id
	private String uName;//��Ŀ��������
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	private Date prTime;//��ʼʱ��
	private String shenjiId;//���id
	private String shenjiName;//�������
	private String ckjlId;//�ɿؾ���id
	private String ckjlName;//��������
	private String ckfjlId;//�ɿظ�����id
	private String ckfjlName;//����������
	private String zgysId;//����Ԥ��id
	private String zgysName;//����Ԥ��Ա����
	private BigDecimal budgetSum;//Ԥ����
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
	public String getPrCode() {
		return prCode;
	}
	public void setPrCode(String prCode) {
		this.prCode = prCode;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getPrTime() {
		return prTime;
	}
	public void setPrTime(Date prTime) {
		this.prTime = prTime;
	}
	public String getShenjiId() {
		return shenjiId;
	}
	public void setShenjiId(String shenjiId) {
		this.shenjiId = shenjiId;
	}
	public String getCkjlId() {
		return ckjlId;
	}
	public void setCkjlId(String ckjlId) {
		this.ckjlId = ckjlId;
	}
	public String getCkfjlId() {
		return ckfjlId;
	}
	public void setCkfjlId(String ckfjlId) {
		this.ckfjlId = ckfjlId;
	}
	public String getZgysId() {
		return zgysId;
	}
	public void setZgysId(String zgysId) {
		this.zgysId = zgysId;
	}
	public BigDecimal getBudgetSum() {
		return budgetSum;
	}
	public void setBudgetSum(BigDecimal budgetSum) {
		this.budgetSum = budgetSum;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getShenjiName() {
		return shenjiName;
	}
	public void setShenjiName(String shenjiName) {
		this.shenjiName = shenjiName;
	}
	public String getCkjlName() {
		return ckjlName;
	}
	public void setCkjlName(String ckjlName) {
		this.ckjlName = ckjlName;
	}
	public String getCkfjlName() {
		return ckfjlName;
	}
	public void setCkfjlName(String ckfjlName) {
		this.ckfjlName = ckfjlName;
	}
	public String getZgysName() {
		return zgysName;
	}
	public void setZgysName(String zgysName) {
		this.zgysName = zgysName;
	}
	
	
}
