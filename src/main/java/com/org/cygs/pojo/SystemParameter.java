package com.org.cygs.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SystemParameter {
    private String paraId;

    private String paraName;

    private String paraCname;

    private Integer paraValue;

    private Integer paraStatus;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date paraLosetime;

    private String paraRemark;

    public String getParaId() {
        return paraId;
    }

    public void setParaId(String paraId) {
        this.paraId = paraId == null ? null : paraId.trim();
    }

    public String getParaName() {
        return paraName;
    }

    public void setParaName(String paraName) {
        this.paraName = paraName == null ? null : paraName.trim();
    }

    public String getParaCname() {
        return paraCname;
    }

    public void setParaCname(String paraCname) {
        this.paraCname = paraCname == null ? null : paraCname.trim();
    }

    public Integer getParaValue() {
        return paraValue;
    }

    public void setParaValue(Integer paraValue) {
        this.paraValue = paraValue;
    }

    public Integer getParaStatus() {
        return paraStatus;
    }

    public void setParaStatus(Integer paraStatus) {
        this.paraStatus = paraStatus;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getParaLosetime() {
        return paraLosetime;
    }

    public void setParaLosetime(Date paraLosetime) {
        this.paraLosetime = paraLosetime;
    }

    public String getParaRemark() {
        return paraRemark;
    }

    public void setParaRemark(String paraRemark) {
        this.paraRemark = paraRemark == null ? null : paraRemark.trim();
    }

}