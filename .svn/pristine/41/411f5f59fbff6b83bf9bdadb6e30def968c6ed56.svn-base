package com.org.cygs.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ZMission {
    private String zMId;

    private String zPrId;
    private String prName;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date beginDate;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    private String mCode;

    private String cuId;	// 这里cuId就是姓名
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date submitTime;

    private Integer status;

    private String remark;

    private String mtId;	// mtId也是名称
    private int  mtCode;

    private Integer edtionId;

    private String zPcPId;
    private String unitNumber;

    private String remark1;

    private String remark2;

    private Integer isoverdue;

    private String supervisor;

    private Date lastAuditTime;
    
    private String file;
    

    public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getzMId() {
        return zMId;
    }

    public void setzMId(String zMId) {
        this.zMId = zMId == null ? null : zMId.trim();
    }

    public String getzPrId() {
        return zPrId;
    }

    public void setzPrId(String zPrId) {
        this.zPrId = zPrId == null ? null : zPrId.trim();
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")  
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")  
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode == null ? null : mCode.trim();
    }

    public String getCuId() {
        return cuId;
    }

    public void setCuId(String cuId) {
        this.cuId = cuId == null ? null : cuId.trim();
    }

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getMtId() {
        return mtId;
    }

    public void setMtId(String mtId) {
        this.mtId = mtId == null ? null : mtId.trim();
    }

    public Integer getEdtionId() {
        return edtionId;
    }

    public void setEdtionId(Integer edtionId) {
        this.edtionId = edtionId;
    }

    public String getPrName() {
		return prName;
	}

	public void setPrName(String prName) {
		this.prName = prName;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public String getzPcPId() {
        return zPcPId;
    }

    public void setzPcPId(String zPcPId) {
        this.zPcPId = zPcPId == null ? null : zPcPId.trim();
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    public Integer getIsoverdue() {
        return isoverdue;
    }

    public void setIsoverdue(Integer isoverdue) {
        this.isoverdue = isoverdue;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor == null ? null : supervisor.trim();
    }

    public Date getLastAuditTime() {
        return lastAuditTime;
    }

    public void setLastAuditTime(Date lastAuditTime) {
        this.lastAuditTime = lastAuditTime;
    }

	public int getMtCode() {
		return mtCode;
	}

	public void setMtCode(int mtCode) {
		this.mtCode = mtCode;
	}

}