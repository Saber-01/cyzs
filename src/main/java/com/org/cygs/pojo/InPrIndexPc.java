package com.org.cygs.pojo;

import java.io.Serializable;

public class InPrIndexPc implements Serializable {
    private String inPcPId;

    private String inPrId;

    private String unitNumber;

    private String uId;

    private static final long serialVersionUID = 1L;

    public String getInPcPId() {
        return inPcPId;
    }

    public void setInPcPId(String inPcPId) {
        this.inPcPId = inPcPId == null ? null : inPcPId.trim();
    }

    public String getInPrId() {
        return inPrId;
    }

    public void setInPrId(String inPrId) {
        this.inPrId = inPrId == null ? null : inPrId.trim();
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber == null ? null : unitNumber.trim();
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId == null ? null : uId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", inPcPId=").append(inPcPId);
        sb.append(", inPrId=").append(inPrId);
        sb.append(", unitNumber=").append(unitNumber);
        sb.append(", uId=").append(uId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}