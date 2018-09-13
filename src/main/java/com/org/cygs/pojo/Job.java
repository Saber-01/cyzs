package com.org.cygs.pojo;

public class Job {
	 private String jobKey;  //工作项目主键
	 private String jobId;   //工作项目编号
	 private String jobName; //工作项目名
	 private String remark;  //备注	
	 private String psName;  //工程项目名
	 private String unName;    //单位名
	 private String psId;      //工程项目主键
	 private String unId;      //结算单位主键
	 
	 
	 
	 
	 public String getPsId() {
		return psId;
	}
	public void setPsId(String psId) {
		this.psId = psId;
	}
	public String getUnId() {
		return unId;
	}
	public void setUnId(String unId) {
		this.unId = unId;
	}
	public String getJobKey() {
		return jobKey;
	}
	public void setJobKey(String jobKey) {
		this.jobKey = jobKey;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPsName() {
		return psName;
	}
	public void setPsName(String psName) {
		this.psName = psName;
	}
	
	public String getUnName() {
		return unName;
	}
	public void setUnName(String unName) {
		this.unName = unName;
	}
	
	@Override
	public String toString() {
		return "Job [jobKey=" + jobKey + ", jobId=" + jobId + ", jobName="
				+ jobName + ", remark=" + remark + ", psName=" + psName
				+ ", unName=" + unName + ", psId=" + psId + ", unId=" + unId
				+ "]";
	}
	
	 

}
