package com.org.cygs.pojo;

public class ProjectClass {
    private String pcId;

    private String pcCode;

    private String pcName;

    private String pcRemark;

    public String getPcId() {
        return pcId;
    }

    public void setPcId(String pcId) {
        this.pcId = pcId == null ? null : pcId.trim();
    }

    public String getPcCode() {
        return pcCode;
    }

    public void setPcCode(String pcCode) {
        this.pcCode = pcCode == null ? null : pcCode.trim();
    }

    public String getPcName() {
        return pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName == null ? null : pcName.trim();
    }

    public String getPcRemark() {
        return pcRemark;
    }

    public void setPcRemark(String pcRemark) {
        this.pcRemark = pcRemark == null ? null : pcRemark.trim();
    }
}