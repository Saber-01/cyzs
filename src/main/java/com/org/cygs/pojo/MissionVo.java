package com.org.cygs.pojo;

import java.math.BigDecimal;

public class MissionVo {

	private String MId;
	private String MCode;
	private String projectName;
	private String prId;
	private String projectPart;
	private String beginDate;
	private String endDate;
	private String mtCode;
	private String missionTypeName;
	private String checkUnit;
	private String submitTime; 
	private String status;
	private String nextstatus;
	private String remark;
	private String isReturn;
	private String cuName;
	private int contractType;
	
	private String suName;
	private String remark1;
	
	//100321
	private String dongHao;
	private String gongZuoRiQi;
	private String xuHao;
	private String fenBu;
	private String gongChengBuWei;
	private String gongZuoXiangMu;
	private String juTiBuWei;
	private String danWei;
	private String danJia;
	private String gongChengLiang;
	private String gongZiJinE;
	private String heTongCaiLiao;
	private String faKuan;
	private String kouKuanCaiLiao;
	private String qiTa;
	private String yiJieLiang;
	private String gongZhang;
	private String tiJiaoShiJian;
	private String zuiHouShenHe;
	private String zhuangTai;
	private String xiangMuJingLi;
	private String yuSuan;
	private String chengkong;
	private String shenJi;
	private String fenGuanLingDao;
	
	private String isoverdue;//判断逾期
	
    //2011-4-19安装计件yj

    private String debh;
    private String xiangmumingcheng;
//    private String danwei;
    private String gongchengliang;
    private String jiagejijia;
    private String jiagerengongfei;
    private String jiagecailiaofei;
    private String jiagejixiefei;
    private String hejia;
    private String rengongfei;
    private String cailiaofei;
    private String jixiefei;
    private String liyou;
    private String inMcId;
//    private String jutibuwei;
    private String azfzr;
    private String azys;
    private String azsj;
    private String fgld;
    
    private String azxmjl;
    private String azgsfjl;
    private String azgsjl;
    public String getAzxmjl() {
		return azxmjl;
	}
	public void setAzxmjl(String azxmjl) {
		this.azxmjl = azxmjl;
	}

	private String heji;
	
	//HEJI 2017.08.03 --->合计
	private BigDecimal HEJI;
    
    //2011-5-30 新加6个字段 meng
//    XISHU
//    YUSUANLIANG
//    YIKAILIANG
//    KUCUNLIANG
//    RUKULIANG
//    CHUKULIANG

    private String XISHU;
    private String YUSUANLIANG;
    private String YIKAILIANG;
    private String KUCUNLIANG;
    private String RUKULIANG;
    private String CHUKULIANG;
    
    //2011-6-6  存储过程字段
    private String tijiaoren;
    private String xiugairen;
    private String tijiaoshijian;
    private String xiugaishijian;


	public String getChengkong() {
		return chengkong;
	}
	public void setChengkong(String chengkong) {
		this.chengkong = chengkong;
	}
	public String getNextstatus() {
		return nextstatus;
	}
	public void setNextstatus(String nextstatus) {
		this.nextstatus = nextstatus;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getSuName() {
		return suName;
	}
	public void setSuName(String suName) {
		this.suName = suName;
	}

	//09-09-06
	private String projectClassName;
	
	public String getProjectClassName() {
		return projectClassName;
	}
	public void setProjectClassName(String projectClassName) {
		this.projectClassName = projectClassName;
	}

	private String unitnumber;
	
	public String getUnitnumber() {
		return unitnumber;
	}
	public void setUnitnumber(String unitnumber) {
		this.unitnumber = unitnumber;
	}
	private String unitId;
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	public String getCuName() {
		return cuName;
	}
	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	//department
	private String departmentName;
	private String dpCode;
	
	

	public String getDpCode() {
		return dpCode;
	}
	public void setDpCode(String dpCode) {
		this.dpCode = dpCode;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsReturn() {
		return isReturn;
	}
	public void setIsReturn(String isReturn) {
		this.isReturn = isReturn;
	}
	public String getPrId() {
		return prId;
	}
	public void setPrId(String prId) {
		this.prId = prId;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getCheckUnit() {
		return checkUnit;
	}
	public void setCheckUnit(String checkUnit) {
		this.checkUnit = checkUnit;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getMCode() {
		return MCode;
	}
	public void setMCode(String code) {
		MCode = code;
	}
	public String getMId() {
		return MId;
	}
	public void setMId(String id) {
		MId = id;
	}
	public String getMissionTypeName() {
		return missionTypeName;
	}
	public void setMissionTypeName(String missionTypeName) {
		this.missionTypeName = missionTypeName;
	}
	public String getMtCode() {
		return mtCode;
	}
	public void setMtCode(String mtCode) {
		this.mtCode = mtCode;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectPart() {
		return projectPart;
	}
	public void setProjectPart(String projectPart) {
		this.projectPart = projectPart;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	/**
	 * @return the dongHao
	 */
	public String getDongHao() {
		return dongHao;
	}
	/**
	 * @param dongHao the dongHao to set
	 */
	public void setDongHao(String dongHao) {
		this.dongHao = dongHao;
	}
	/**
	 * @return the fenBu
	 */
	public String getFenBu() {
		return fenBu;
	}
	/**
	 * @param fenBu the fenBu to set
	 */
	public void setFenBu(String fenBu) {
		this.fenBu = fenBu;
	}
	/**
	 * @return the gongZuoRiQi
	 */
	public String getGongZuoRiQi() {
		return gongZuoRiQi;
	}
	/**
	 * @param gongZuoRiQi the gongZuoRiQi to set
	 */
	public void setGongZuoRiQi(String gongZuoRiQi) {
		this.gongZuoRiQi = gongZuoRiQi;
	}
	/**
	 * @return the xuHao
	 */
	public String getXuHao() {
		return xuHao;
	}
	/**
	 * @param xuHao the xuHao to set
	 */
	public void setXuHao(String xuHao) {
		this.xuHao = xuHao;
	}
	/**
	 * @return the danJia
	 */
	public String getDanJia() {
		return danJia;
	}
	/**
	 * @param danJia the danJia to set
	 */
	public void setDanJia(String danJia) {
		this.danJia = danJia;
	}
	/**
	 * @return the danWei
	 */
	public String getDanWei() {
		return danWei;
	}
	/**
	 * @param danWei the danWei to set
	 */
	public void setDanWei(String danWei) {
		this.danWei = danWei;
	}
	/**
	 * @return the faKuan
	 */
	public String getFaKuan() {
		return faKuan;
	}
	/**
	 * @param faKuan the faKuan to set
	 */
	public void setFaKuan(String faKuan) {
		this.faKuan = faKuan;
	}
	/**
	 * @return the fenGuanLingDao
	 */
	public String getFenGuanLingDao() {
		return fenGuanLingDao;
	}
	/**
	 * @param fenGuanLingDao the fenGuanLingDao to set
	 */
	public void setFenGuanLingDao(String fenGuanLingDao) {
		this.fenGuanLingDao = fenGuanLingDao;
	}
	/**
	 * @return the gongChengBuWei
	 */
	public String getGongChengBuWei() {
		return gongChengBuWei;
	}
	/**
	 * @param gongChengBuWei the gongChengBuWei to set
	 */
	public void setGongChengBuWei(String gongChengBuWei) {
		this.gongChengBuWei = gongChengBuWei;
	}
	/**
	 * @return the gongChengLiang
	 */
	public String getGongChengLiang() {
		return gongChengLiang;
	}
	/**
	 * @param gongChengLiang the gongChengLiang to set
	 */
	public void setGongChengLiang(String gongChengLiang) {
		this.gongChengLiang = gongChengLiang;
	}
	/**
	 * @return the gongZhang
	 */
	public String getGongZhang() {
		return gongZhang;
	}
	/**
	 * @param gongZhang the gongZhang to set
	 */
	public void setGongZhang(String gongZhang) {
		this.gongZhang = gongZhang;
	}
	/**
	 * @return the gongZiJinE
	 */
	public String getGongZiJinE() {
		return gongZiJinE;
	}
	/**
	 * @param gongZiJinE the gongZiJinE to set
	 */
	public void setGongZiJinE(String gongZiJinE) {
		this.gongZiJinE = gongZiJinE;
	}
	/**
	 * @return the gongZuoXiangMu
	 */
	public String getGongZuoXiangMu() {
		return gongZuoXiangMu;
	}
	/**
	 * @param gongZuoXiangMu the gongZuoXiangMu to set
	 */
	public void setGongZuoXiangMu(String gongZuoXiangMu) {
		this.gongZuoXiangMu = gongZuoXiangMu;
	}
	/**
	 * @return the heTongCaiLiao
	 */
	public String getHeTongCaiLiao() {
		return heTongCaiLiao;
	}
	/**
	 * @param heTongCaiLiao the heTongCaiLiao to set
	 */
	public void setHeTongCaiLiao(String heTongCaiLiao) {
		this.heTongCaiLiao = heTongCaiLiao;
	}
	/**
	 * @return the juTiBuWei
	 */
	public String getJuTiBuWei() {
		return juTiBuWei;
	}
	/**
	 * @param juTiBuWei the juTiBuWei to set
	 */
	public void setJuTiBuWei(String juTiBuWei) {
		this.juTiBuWei = juTiBuWei;
	}
	/**
	 * @return the kouKuanCaiLiao
	 */
	public String getKouKuanCaiLiao() {
		return kouKuanCaiLiao;
	}
	/**
	 * @param kouKuanCaiLiao the kouKuanCaiLiao to set
	 */
	public void setKouKuanCaiLiao(String kouKuanCaiLiao) {
		this.kouKuanCaiLiao = kouKuanCaiLiao;
	}
	/**
	 * @return the qiTa
	 */
	public String getQiTa() {
		return qiTa;
	}
	/**
	 * @param qiTa the qiTa to set
	 */
	public void setQiTa(String qiTa) {
		this.qiTa = qiTa;
	}
	/**
	 * @return the shenJi
	 */
	public String getShenJi() {
		return shenJi;
	}
	/**
	 * @param shenJi the shenJi to set
	 */
	public void setShenJi(String shenJi) {
		this.shenJi = shenJi;
	}
	/**
	 * @return the tiJiaoShiJian
	 */
	public String getTiJiaoShiJian() {
		return tiJiaoShiJian;
	}
	/**
	 * @param tiJiaoShiJian the tiJiaoShiJian to set
	 */
	public void setTiJiaoShiJian(String tiJiaoShiJian) {
		this.tiJiaoShiJian = tiJiaoShiJian;
	}
	/**
	 * @return the xiangMuJingLi
	 */
	public String getXiangMuJingLi() {
		return xiangMuJingLi;
	}
	/**
	 * @param xiangMuJingLi the xiangMuJingLi to set
	 */
	public void setXiangMuJingLi(String xiangMuJingLi) {
		this.xiangMuJingLi = xiangMuJingLi;
	}
	/**
	 * @return the yiJieLiang
	 */
	public String getYiJieLiang() {
		return yiJieLiang;
	}
	/**
	 * @param yiJieLiang the yiJieLiang to set
	 */
	public void setYiJieLiang(String yiJieLiang) {
		this.yiJieLiang = yiJieLiang;
	}
	/**
	 * @return the yuSuan
	 */
	public String getYuSuan() {
		return yuSuan;
	}
	/**
	 * @param yuSuan the yuSuan to set
	 */
	public void setYuSuan(String yuSuan) {
		this.yuSuan = yuSuan;
	}
	/**
	 * @return the zhuangTai
	 */
	public String getZhuangTai() {
		return zhuangTai;
	}
	/**
	 * @param zhuangTai the zhuangTai to set
	 */
	public void setZhuangTai(String zhuangTai) {
		this.zhuangTai = zhuangTai;
	}
	/**
	 * @return the zuiHouShenHe
	 */
	public String getZuiHouShenHe() {
		return zuiHouShenHe;
	}
	/**
	 * @param zuiHouShenHe the zuiHouShenHe to set
	 */
	public void setZuiHouShenHe(String zuiHouShenHe) {
		this.zuiHouShenHe = zuiHouShenHe;
	}
	/**
	 * @return the isoverdue
	 */
	public String getIsoverdue() {
		return isoverdue;
	}
	/**
	 * @param isoverdue the isoverdue to set
	 */
	public void setIsoverdue(String isoverdue) {
		this.isoverdue = isoverdue;
	}
	public int getContractType() {
		return contractType;
	}
	public void setContractType(int contractType) {
		this.contractType = contractType;
	}
	public String getDebh() {
		return debh;
	}
	public void setDebh(String debh) {
		this.debh = debh;
	}
	public String getXiangmumingcheng() {
		return xiangmumingcheng;
	}
	public void setXiangmumingcheng(String xiangmumingcheng) {
		this.xiangmumingcheng = xiangmumingcheng;
	}
	
	public String getGongchengliang() {
		return gongchengliang;
	}
	public void setGongchengliang(String gongchengliang) {
		this.gongchengliang = gongchengliang;
	}
	public String getJiagejijia() {
		return jiagejijia;
	}
	public void setJiagejijia(String jiagejijia) {
		this.jiagejijia = jiagejijia;
	}
	public String getJiagerengongfei() {
		return jiagerengongfei;
	}
	public void setJiagerengongfei(String jiagerengongfei) {
		this.jiagerengongfei = jiagerengongfei;
	}
	public String getJiagecailiaofei() {
		return jiagecailiaofei;
	}
	public void setJiagecailiaofei(String jiagecailiaofei) {
		this.jiagecailiaofei = jiagecailiaofei;
	}
	public String getJiagejixiefei() {
		return jiagejixiefei;
	}
	public void setJiagejixiefei(String jiagejixiefei) {
		this.jiagejixiefei = jiagejixiefei;
	}
	public String getHejia() {
		return hejia;
	}
	public void setHejia(String hejia) {
		this.hejia = hejia;
	}
	public String getRengongfei() {
		return rengongfei;
	}
	public void setRengongfei(String rengongfei) {
		this.rengongfei = rengongfei;
	}
	public String getCailiaofei() {
		return cailiaofei;
	}
	public void setCailiaofei(String cailiaofei) {
		this.cailiaofei = cailiaofei;
	}
	public String getJixiefei() {
		return jixiefei;
	}
	public void setJixiefei(String jixiefei) {
		this.jixiefei = jixiefei;
	}
	public String getLiyou() {
		return liyou;
	}
	public void setLiyou(String liyou) {
		this.liyou = liyou;
	}
	public String getInMcId() {
		return inMcId;
	}
	public void setInMcId(String inMcId) {
		this.inMcId = inMcId;
	}
	
	public String getXISHU() {
		return XISHU;
	}
	public void setXISHU(String xishu) {
		XISHU = xishu;
	}
	public String getYUSUANLIANG() {
		return YUSUANLIANG;
	}
	public void setYUSUANLIANG(String yusuanliang) {
		YUSUANLIANG = yusuanliang;
	}
	public String getYIKAILIANG() {
		return YIKAILIANG;
	}
	public void setYIKAILIANG(String yikailiang) {
		YIKAILIANG = yikailiang;
	}
	public String getKUCUNLIANG() {
		return KUCUNLIANG;
	}
	public void setKUCUNLIANG(String kucunliang) {
		KUCUNLIANG = kucunliang;
	}
	public String getRUKULIANG() {
		return RUKULIANG;
	}
	public void setRUKULIANG(String rukuliang) {
		RUKULIANG = rukuliang;
	}
	public String getCHUKULIANG() {
		return CHUKULIANG;
	}
	public void setCHUKULIANG(String chukuliang) {
		CHUKULIANG = chukuliang;
	}
	public String getTijiaoren() {
		return tijiaoren;
	}
	public void setTijiaoren(String tijiaoren) {
		this.tijiaoren = tijiaoren;
	}
	public String getXiugairen() {
		return xiugairen;
	}
	public void setXiugairen(String xiugairen) {
		this.xiugairen = xiugairen;
	}
	public String getTijiaoshijian() {
		return tijiaoshijian;
	}
	public void setTijiaoshijian(String tijiaoshijian) {
		this.tijiaoshijian = tijiaoshijian;
	}
	public String getXiugaishijian() {
		return xiugaishijian;
	}
	public void setXiugaishijian(String xiugaishijian) {
		this.xiugaishijian = xiugaishijian;
	}
	public String getAzfzr() {
		return azfzr;
	}
	public void setAzfzr(String azfzr) {
		this.azfzr = azfzr;
	}
	public String getAzys() {
		return azys;
	}
	public void setAzys(String azys) {
		this.azys = azys;
	}
	public String getAzsj() {
		return azsj;
	}
	public void setAzsj(String azsj) {
		this.azsj = azsj;
	}
	public String getFgld() {
		return fgld;
	}
	public void setFgld(String fgld) {
		this.fgld = fgld;
	}
	/**
	 * @return the azgsfjl
	 */
	public String getAzgsfjl() {
		return azgsfjl;
	}
	/**
	 * @param azgsfjl the azgsfjl to set
	 */
	public void setAzgsfjl(String azgsfjl) {
		this.azgsfjl = azgsfjl;
	}
	/**
	 * @return the azgsjl
	 */
	public String getAzgsjl() {
		return azgsjl;
	}
	/**
	 * @param azgsjl the azgsjl to set
	 */
	public void setAzgsjl(String azgsjl) {
		this.azgsjl = azgsjl;
	}
	/**
	 * @return the heji
	 */
	public String getHeji() {
		return heji;
	}
	/**
	 * @param heji the heji to set
	 */
	public void setHeji(String heji) {
		this.heji = heji;
	}
	public BigDecimal getHEJI() {
		return HEJI;
	}
	public void setHEJI(BigDecimal hEJI) {
		HEJI = hEJI;
	}
}
