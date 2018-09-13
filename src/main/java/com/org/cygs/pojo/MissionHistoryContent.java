package com.org.cygs.pojo;

import java.math.BigDecimal;

public class MissionHistoryContent {
	
	private String mchId; //历史任务书内容主键
	private String mhId;

	private String mcId;

    private Integer mcCode;

    private BigDecimal price;

    private BigDecimal realQuantity;

    private BigDecimal wageSum;

    private BigDecimal accumulateSum;

    private String floor;
    
    private String oName;
    private String oId;
    
    private String unName;
    private String unId;

    private String remark;
    
    private String mId;

    private String psName;
    private String psId;
    
    private String pName;
    private String pId;
    
    private String partPos;

    private String isreturn;

    private String innerId;
	
	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
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
	
	public String getMhId() {
		return mhId;
	}

	public void setMhId(String mhId) {
		this.mhId = mhId;
	}

}
