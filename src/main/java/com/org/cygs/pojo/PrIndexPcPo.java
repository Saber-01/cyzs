package com.org.cygs.pojo;

import java.math.BigDecimal;

public class PrIndexPcPo {
    private String pcPId;

    private String pcId;

    private String prId;

    private String unitNumber;

    private String uId;

    private BigDecimal budgetSum;

    public String getPcPId() {
        return pcPId;
    }

    public void setPcPId(String pcPId) {
        this.pcPId = pcPId == null ? null : pcPId.trim();
    }

    public String getPcId() {
        return pcId;
    }

    public void setPcId(String pcId) {
        this.pcId = pcId == null ? null : pcId.trim();
    }

    public String getPrId() {
        return prId;
    }

    public void setPrId(String prId) {
        this.prId = prId == null ? null : prId.trim();
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

    public BigDecimal getBudgetSum() {
        return budgetSum;
    }

    public void setBudgetSum(BigDecimal budgetSum) {
        this.budgetSum = budgetSum;
    }

}