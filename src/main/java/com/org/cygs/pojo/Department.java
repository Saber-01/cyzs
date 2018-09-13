package com.org.cygs.pojo;

import java.io.Serializable;

public class Department implements Serializable {
    private String dpId;

    private String dpName;

    private String dpCode;

    private String uId;

    private Integer dpStatus;

    private static final long serialVersionUID = 1L;

    public String getDpId() {
        return dpId;
    }

    public void setDpId(String dpId) {
        this.dpId = dpId == null ? null : dpId.trim();
    }

    public String getDpName() {
        return dpName;
    }

    public void setDpName(String dpName) {
        this.dpName = dpName == null ? null : dpName.trim();
    }

    public String getDpCode() {
        return dpCode;
    }

    public void setDpCode(String dpCode) {
        this.dpCode = dpCode == null ? null : dpCode.trim();
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId == null ? null : uId.trim();
    }

    public Integer getDpStatus() {
        return dpStatus;
    }

    public void setDpStatus(Integer dpStatus) {
        this.dpStatus = dpStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dpId=").append(dpId);
        sb.append(", dpName=").append(dpName);
        sb.append(", dpCode=").append(dpCode);
        sb.append(", uId=").append(uId);
        sb.append(", dpStatus=").append(dpStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}