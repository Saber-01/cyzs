package com.org.cygs.pojo;

public class AuditInfoDelete {
    private String aIDd;
    
    private String mdId;

    private AuditInfo auditInfo;

    public String getaIDd() {
        return aIDd;
    }

    public void setaIDd(String aIDd) {
        this.aIDd = aIDd == null ? null : aIDd.trim();
    }

	public String getMdId() {
		return mdId;
	}

	public void setMdId(String mdId) {
		this.mdId = mdId;
	}

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}
    

}