package com.org.cygs.pojo;

import java.math.BigDecimal;

public class Contract {
	private String opId;  //��ͬ���۱�����
	private Float price;     //��ͬ����
	private String jobKey;
	private String jobName;    //������Ŀ����������
	private String psId;
	private String psName;   //���̲�λ����������
	private String prId;
	private String prName;   //��������������
	private String unId;
	private String unName;   //������λ����������
	private String fsId;
	private String fsName;   //��������ע����������
	private String cuId;
	private String cuName;   //���㵥λ����������
	private String pcpId;    
	private String pcpNumber;    //������𣬶�����ر���������������
	private String pId;          
	private String pName;        //�ֲ�����������
	private String remark;       //��ע
    private String bId;
    private Integer isbudget;
    private BigDecimal accumulateSum;
    private BigDecimal budgetSum;
    private float ykl;
    private float ysl;
    private String budgetPos;
    private String budgetName;
    private String classOrNot;
    
    
    
    
    
    public String getClassOrNot() {
		return classOrNot;
	}
	public void setClassOrNot(String classOrNot) {
		this.classOrNot = classOrNot;
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
	public float getYkl() {
		return ykl;
	}
	public void setYkl(float ykl) {
		this.ykl = ykl;
	}
	public float getYsl() {
		return ysl;
	}
	public void setYsl(float ysl) {
		this.ysl = ysl;
	}
	public String getbId() {
		return bId;
	}
	public void setbId(String bId) {
		this.bId = bId;
	}
	public Integer getIsbudget() {
		return isbudget;
	}
	public void setIsbudget(Integer isbudget) {
		this.isbudget = isbudget;
	}
	public BigDecimal getAccumulateSum() {
		return accumulateSum;
	}
	public void setAccumulateSum(BigDecimal accumulateSum) {
		this.accumulateSum = accumulateSum;
	}
	public BigDecimal getBudgetSum() {
		return budgetSum;
	}
	public void setBudgetSum(BigDecimal budgetSum) {
		this.budgetSum = budgetSum;
	}
	public String getJobKey() {
		return jobKey;
	}
	public void setJobKey(String jobKey) {
		this.jobKey = jobKey;
	}
	public String getPsId() {
		return psId;
	}
	public void setPsId(String psId) {
		this.psId = psId;
	}
	public String getPrId() {
		return prId;
	}
	public void setPrId(String prId) {
		this.prId = prId;
	}
	public String getUnId() {
		return unId;
	}
	public void setUnId(String unId) {
		this.unId = unId;
	}
	public String getFsId() {
		return fsId;
	}
	public void setFsId(String fsId) {
		this.fsId = fsId;
	}
	public String getCuId() {
		return cuId;
	}
	public void setCuId(String cuId) {
		this.cuId = cuId;
	}
	public String getPcpId() {
		return pcpId;
	}
	public void setPcpId(String pcpId) {
		this.pcpId = pcpId;
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
	



	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getOpId() {
		return opId;
	}
	public void setOpId(String opId) {
		this.opId = opId;
	}
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getPsName() {
		return psName;
	}
	public void setPsName(String psName) {
		this.psName = psName;
	}
	public String getPrName() {
		return prName;
	}
	public void setPrName(String prName) {
		this.prName = prName;
	}
	public String getUnName() {
		return unName;
	}
	public void setUnName(String unName) {
		this.unName = unName;
	}
	public String getFsName() {
		return fsName;
	}
	public void setFsName(String fsName) {
		this.fsName = fsName;
	}
	public String getCuName() {
		return cuName;
	}
	public void setCuName(String cuName) {
		this.cuName = cuName;
	}

	public String getPcpNumber() {
		return pcpNumber;
	}
	public void setPcpNumber(String pcpNumber) {
		this.pcpNumber = pcpNumber;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Contract [opId=" + opId + ", price=" + price + ", jobKey="
				+ jobKey + ", jobName=" + jobName + ", psId=" + psId
				+ ", psName=" + psName + ", prId=" + prId + ", prName="
				+ prName + ", unId=" + unId + ", unName=" + unName + ", fsId="
				+ fsId + ", fsName=" + fsName + ", cuId=" + cuId + ", cuName="
				+ cuName + ", pcpId=" + pcpId + ", pcpNumber=" + pcpNumber
				+ ", pId=" + pId + ", pName=" + pName + ", remark=" + remark
				+ ", bId=" + bId + ", isbudget=" + isbudget
				+ ", accumulateSum=" + accumulateSum + ", budgetSum="
				+ budgetSum + ",classOrNot="+classOrNot+"]";
	}
	
	
	
	

}
