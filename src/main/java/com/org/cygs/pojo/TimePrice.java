package com.org.cygs.pojo;



public class TimePrice {
	private String jspId;  //��ͬ���۱�����
	private String unId;
	private String unName;   //������λ����������

	private Double price;     //��ͬ����
	private String jobKey;
	private String jobName;    //������Ŀ����������
	private String psId;
	private String psName;   //���̲�λ����������
	private String prId;
	private String prName;   //��������������	
	
	private String cuId;
	private String cuName;   //���㵥λ����������
	private String pcpId;    
	private String pcpNumber;    //������𣬶�����ر���������������
	private String pId;          
	private String pName;        //�ֲ�����������

    
    
    
    
    
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

	public String getJspId() {
		return jspId;
	}
	public void setJspId(String jspId) {
		this.jspId = jspId;
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
	



	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
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
	@Override
	public String toString() {
		return "TimePrice [jspId=" + jspId + ", unId=" + unId + ", unName="
				+ unName + ", price=" + price + ", jobKey=" + jobKey
				+ ", jobName=" + jobName + ", psId=" + psId + ", psName="
				+ psName + ", prId=" + prId + ", prName=" + prName + ", cuId="
				+ cuId + ", cuName=" + cuName + ", pcpId=" + pcpId
				+ ", pcpNumber=" + pcpNumber + ", pId=" + pId + ", pName="
				+ pName + "]";
	}

	
	
	
	
	
	
	
	

}
