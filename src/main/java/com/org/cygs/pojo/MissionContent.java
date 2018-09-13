package com.org.cygs.pojo;

import java.math.BigDecimal;

public class MissionContent {
    private String mcId;

    private Integer mcCode;

    private BigDecimal price;

    private BigDecimal realQuantity;

    private BigDecimal wageSum;

    private BigDecimal accumulateSum; //

    private String floor;
    
    private String oId;    //工作项目    分项二
    private String oName;
    
    private String unId;   //单位
    private String unName;

    private String remark;
    
    private String mId;   //任务书id

    
    private String psId;
    private String psName;   //工程部位               分项一
    
    private String pId;
    private String pName;    //分部
    

    private String partPos;

    private String partItemone;

    private String partItemtwo;

    private String partItemthree;

    private String isreturn;

    private String innerId;
    
    private Double jjykl;
    private Double ykl;
    
    
    private Double yjl; //已结量
    private Double yjlSum;//已结量金额
    
    private Double ljgcl;//项目累计已结量
    private Double ljgclSum;//项目累计已结金额
    

	public Double getJjykl() {
		return jjykl;
	}

	public void setJjykl(Double jjykl) {
		this.jjykl = jjykl;
	}

	public Double getYkl() {
		return ykl;
	}

	public void setYkl(Double ykl) {
		this.ykl = ykl;
	}



	public Double getYjl() {
		return yjl;
	}

	public void setYjl(Double yjl) {
		this.yjl = yjl;
	}

	public Double getYjlSum() {
		return yjlSum;
	}

	public void setYjlSum(Double yjlSum) {
		this.yjlSum = yjlSum;
	}

	public Double getLjgcl() {
		return ljgcl;
	}

	public void setLjgcl(Double ljgcl) {
		this.ljgcl = ljgcl;
	}

	public Double getLjgclSum() {
		return ljgclSum;
	}

	public void setLjgclSum(Double ljgclSum) {
		this.ljgclSum = ljgclSum;
	}

	public String getoName() {
		return oName;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}

	public String getUnName() {
		return unName;
	}

	public void setUnName(String unName) {
		this.unName = unName;
	}

	public String getPsName() {
		return psName;
	}

	public void setPsName(String psName) {
		this.psName = psName;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getUnId() {
		return unId;
	}

	public void setUnId(String unId) {
		this.unId = unId;
	}

	public String getoId() {
		return oId;
	}

	public void setoId(String oId) {
		this.oId = oId;
	}

	public String getPsId() {
		return psId;
	}

	public void setPsId(String psId) {
		this.psId = psId;
	}


	public String getMcId() {
		return mcId;
	}

	public void setMcId(String mcId) {
		this.mcId = mcId;
	}

	public Integer getMcCode() {
		return mcCode;
	}

	public void setMcCode(Integer mcCode) {
		this.mcCode = mcCode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getRealQuantity() {
		return realQuantity;
	}

	public void setRealQuantity(BigDecimal realQuantity) {
		this.realQuantity = realQuantity;
	}

	public BigDecimal getWageSum() {
		return wageSum;
	}

	public void setWageSum(BigDecimal wageSum) {
		this.wageSum = wageSum;
	}

	public BigDecimal getAccumulateSum() {
		return accumulateSum;
	}

	public void setAccumulateSum(BigDecimal accumulateSum) {
		this.accumulateSum = accumulateSum;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getPartPos() {
		return partPos;
	}

	public void setPartPos(String partPos) {
		this.partPos = partPos;
	}

	public String getPartItemone() {
		return partItemone;
	}

	public void setPartItemone(String partItemone) {
		this.partItemone = partItemone;
	}

	public String getPartItemtwo() {
		return partItemtwo;
	}

	public void setPartItemtwo(String partItemtwo) {
		this.partItemtwo = partItemtwo;
	}

	public String getPartItemthree() {
		return partItemthree;
	}

	public void setPartItemthree(String partItemthree) {
		this.partItemthree = partItemthree;
	}

	public String getIsreturn() {
		return isreturn;
	}

	public void setIsreturn(String isreturn) {
		this.isreturn = isreturn;
	}

	public String getInnerId() {
		return innerId;
	}

	public void setInnerId(String innerId) {
		this.innerId = innerId;
	}

    
}