package com.org.cygs.pojo;

import java.util.Date;

public class ZProject {
    private String zPrId;

    private String prName;

    private String prCode;

    private String uId;

    private Date prTime;

    private Date prEndTime;

    private String shenjiId;

    public String getzPrId() {
        return zPrId;
    }

    public void setzPrId(String zPrId) {
        this.zPrId = zPrId == null ? null : zPrId.trim();
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName == null ? null : prName.trim();
    }

    public String getPrCode() {
        return prCode;
    }

    public void setPrCode(String prCode) {
        this.prCode = prCode == null ? null : prCode.trim();
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId == null ? null : uId.trim();
    }

    public Date getPrTime() {
        return prTime;
    }

    public void setPrTime(Date prTime) {
        this.prTime = prTime;
    }

    public Date getPrEndTime() {
        return prEndTime;
    }

    public void setPrEndTime(Date prEndTime) {
        this.prEndTime = prEndTime;
    }

    public String getShenjiId() {
        return shenjiId;
    }

    public void setShenjiId(String shenjiId) {
        this.shenjiId = shenjiId == null ? null : shenjiId.trim();
    }

}