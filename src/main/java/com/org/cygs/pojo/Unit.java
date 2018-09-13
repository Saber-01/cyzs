package com.org.cygs.pojo;

public class Unit {
    private String unId;

    private String unCode;

    private String unName;

    public String getUnId() {
        return unId;
    }

    public void setUnId(String unId) {
        this.unId = unId == null ? null : unId.trim();
    }

    public String getUnCode() {
        return unCode;
    }

    public void setUnCode(String unCode) {
        this.unCode = unCode == null ? null : unCode.trim();
    }

    public String getUnName() {
        return unName;
    }

    public void setUnName(String unName) {
        this.unName = unName == null ? null : unName.trim();
    }

}