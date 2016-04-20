package com.jhcz.trade.fundManage.bean;

import java.math.BigDecimal;

import com.jhcz.trade.common.vo.BaseVO;

public class FundInfo extends BaseVO{

	//基金代码
	private String fundCode;
	//基金名称
	private String fundName;
	//基金类型
	private String fundType;
	//基金类型
	private String fundTypeDesc;
	//基金英文名称
	private String fundNameEng;
	//基金总份数
	private BigDecimal totalFundVol;
	//基金状态
	private String fundStatus;
	//基金状态
	private String fundStatusDesc;
	//净值日期
	private String navDat;
	//份额净值
	private String nav;
	//转换状态
	private String convertStatus;
	//tano
	private String tano;
	//定期定额状态
	private String periodicStatus;
	//转托管状态
	private String transferAgencyStatus;
	//结算币种
	private String currencyType;
	//基金规模
	private String fundSize;
	//基金默认分红方式
	private String defDividendMethod;
	//托管人代码
	private String custOdianCode;
	//基金募集开始日期
	private String ipoStartDate;
	//基金募集结束日期
	private String ipoEndDate;
	//管理人代码
	private String managerCode;
	//基金发起人
	private String fundSponsor;
	//交易价格
	private BigDecimal tradingPrice;
	//基金面额
	private BigDecimal faceValue;
	//分红日
	private String dividentDate;
	//权益登记日期
	private String regiStartionDate;
	//认购方式，0-金额认购、1-分数认购
	private String subsType;
	//产品价值线数值
	private BigDecimal valueLine;
	//基金信息修改日期
	private String updateDate;
	//风险等级
	private String fundRiskLevel;
	//风险等级
	private String fundRiskLevelDesc;
	//费用洗漱
	private BigDecimal feeFactor;
	//累计净值
	private BigDecimal accumulativeNav;
	//货币基金万份收益
	private BigDecimal totalIncome;
	//基金当日总收益
	private BigDecimal imcomeYield;
	//基金分红方式是否可以更改，0-可更改、1-不可更改
	private String dividendModify;
	//是否需要签署风险揭示书，0-不需要、1-需要
	private String onRiskProtocol;
	//是否签署电子合同，0-不需要、1-需要
	private String onCountractSystem;
	//基金首次上线销售日期
	private String disdate;
	//数据确认日
	private BigDecimal redemptionArrivectDate;
	//是否可预约
	private String isSubscribe;
	//是否快速到账
	private String isQuick;
	//申购交收天数
	private BigDecimal buypayPeriod;
	//赎回交收天数
	private BigDecimal redemptionPayPeriod;
	//基金转换交收天数
	private BigDecimal conversionPayPeriod;
	//认购退款交收天数
	private BigDecimal subpaybackPeriod;
	//分红交收天数
	private BigDecimal dividendpayPeriod;
	//收费方式，0-前收费、1-后收费
	private String shareClass;
	//收费方式
	private String shareClassName;
	//目标基金
	private String targetFundcode;
	//收费标志，0-扎差交收、1-金额交收
	private String agencyFeeFlag;
	//是否保本基金，0-否、1-是
	private String isGuaranteedFund;
	//是否LOF基金，0-否、1-是
	private String isLofFund;
	//是否QUII基金，0-否、1-是
	private String isqdiiFund;
	//是否ETF基金，0-否、1-是
	private String isetfFund;
	//认购交收天数
	private String subscribe;
	//清盘交收天数
	private String liquidation;
	//认购确认交收天数
	private String subscribeAffirm;
	//定投交收天数
	private String fixedInvestment;
	//强行赎回
	private String mandatoryRedemption;
	//是否允许打折
	private String isDiscount;
	//是否允许预收市
	private String expectclose;
	//收费类型，0-折扣率方式、1-指定费率、2-指定费用
	private String chargeType;
	//指定费率时的手续费
	private BigDecimal specifyRateFee;
	//指定费用时的手续费
	private BigDecimal specifyFee;
	//资金交易账号
	private String cashAccountid;
	//月销售额
	private BigDecimal monthlySales;
	//人气（点击量）
	private BigDecimal populararity;
	//是否推荐产品
	private String isRecommend;
	//最后更新时间
	private String lastDate;
	//近一年涨幅
	private String annualGain;
	//0:  最近一周 1: 最近一月 2: 最近三月 3: 最近六月 4: 今年以来  5: 最近一年 6:  最近两年 7: 最近三 8: 成立以来
	private String intervalT;
	//基金介绍连接
	private String fundUrl;
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public String getFundType() {
		return fundType;
	}
	public void setFundType(String fundType) {
		this.fundType = fundType;
	}
	public String getFundNameEng() {
		return fundNameEng;
	}
	public void setFundNameEng(String fundNameEng) {
		this.fundNameEng = fundNameEng;
	}
	public BigDecimal getTotalFundVol() {
		return totalFundVol;
	}
	public void setTotalFundVol(BigDecimal totalFundVol) {
		this.totalFundVol = totalFundVol;
	}
	public String getFundStatus() {
		return fundStatus;
	}
	public void setFundStatus(String fundStatus) {
		this.fundStatus = fundStatus;
	}
	public String getNavDat() {
		return navDat;
	}
	public void setNavDat(String navDat) {
		this.navDat = navDat;
	}
	public String getNav() {
		return nav;
	}
	public void setNav(String nav) {
		this.nav = nav;
	}
	public String getConvertStatus() {
		return convertStatus;
	}
	public void setConvertStatus(String convertStatus) {
		this.convertStatus = convertStatus;
	}
	public String getTano() {
		return tano;
	}
	public void setTano(String tano) {
		this.tano = tano;
	}
	public String getPeriodicStatus() {
		return periodicStatus;
	}
	public void setPeriodicStatus(String periodicStatus) {
		this.periodicStatus = periodicStatus;
	}
	public String getTransferAgencyStatus() {
		return transferAgencyStatus;
	}
	public void setTransferAgencyStatus(String transferAgencyStatus) {
		this.transferAgencyStatus = transferAgencyStatus;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getFundSize() {
		return fundSize;
	}
	public void setFundSize(String fundSize) {
		this.fundSize = fundSize;
	}
	public String getDefDividendMethod() {
		return defDividendMethod;
	}
	public void setDefDividendMethod(String defDividendMethod) {
		this.defDividendMethod = defDividendMethod;
	}
	public String getCustOdianCode() {
		return custOdianCode;
	}
	public void setCustOdianCode(String custOdianCode) {
		this.custOdianCode = custOdianCode;
	}
	public String getIpoStartDate() {
		return ipoStartDate;
	}
	public void setIpoStartDate(String ipoStartDate) {
		this.ipoStartDate = ipoStartDate;
	}
	public String getIpoEndDate() {
		return ipoEndDate;
	}
	public void setIpoEndDate(String ipoEndDate) {
		this.ipoEndDate = ipoEndDate;
	}
	public String getManagerCode() {
		return managerCode;
	}
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
	public String getFundSponsor() {
		return fundSponsor;
	}
	public void setFundSponsor(String fundSponsor) {
		this.fundSponsor = fundSponsor;
	}
	public BigDecimal getTradingPrice() {
		return tradingPrice;
	}
	public void setTradingPrice(BigDecimal tradingPrice) {
		this.tradingPrice = tradingPrice;
	}
	public BigDecimal getFaceValue() {
		return faceValue;
	}
	public void setFaceValue(BigDecimal faceValue) {
		this.faceValue = faceValue;
	}
	public String getDividentDate() {
		return dividentDate;
	}
	public void setDividentDate(String dividentDate) {
		this.dividentDate = dividentDate;
	}
	public String getRegiStartionDate() {
		return regiStartionDate;
	}
	public void setRegiStartionDate(String regiStartionDate) {
		this.regiStartionDate = regiStartionDate;
	}
	public String getSubsType() {
		return subsType;
	}
	public void setSubsType(String subsType) {
		this.subsType = subsType;
	}
	public BigDecimal getValueLine() {
		return valueLine;
	}
	public void setValueLine(BigDecimal valueLine) {
		this.valueLine = valueLine;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getFundRiskLevel() {
		return fundRiskLevel;
	}
	public void setFundRiskLevel(String fundRiskLevel) {
		this.fundRiskLevel = fundRiskLevel;
	}
	public String getFundRiskLevelDesc() {
		return fundRiskLevelDesc;
	}
	public void setFundRiskLevelDesc(String fundRiskLevelDesc) {
		this.fundRiskLevelDesc = fundRiskLevelDesc;
	}
	public BigDecimal getFeeFactor() {
		return feeFactor;
	}
	public void setFeeFactor(BigDecimal feeFactor) {
		this.feeFactor = feeFactor;
	}
	public BigDecimal getAccumulativeNav() {
		return accumulativeNav;
	}
	public void setAccumulativeNav(BigDecimal accumulativeNav) {
		this.accumulativeNav = accumulativeNav;
	}
	public BigDecimal getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}
	public BigDecimal getImcomeYield() {
		return imcomeYield;
	}
	public void setImcomeYield(BigDecimal imcomeYield) {
		this.imcomeYield = imcomeYield;
	}
	public String getDividendModify() {
		return dividendModify;
	}
	public void setDividendModify(String dividendModify) {
		this.dividendModify = dividendModify;
	}
	public String getOnRiskProtocol() {
		return onRiskProtocol;
	}
	public void setOnRiskProtocol(String onRiskProtocol) {
		this.onRiskProtocol = onRiskProtocol;
	}
	public String getOnCountractSystem() {
		return onCountractSystem;
	}
	public void setOnCountractSystem(String onCountractSystem) {
		this.onCountractSystem = onCountractSystem;
	}
	public String getDisdate() {
		return disdate;
	}
	public void setDisdate(String disdate) {
		this.disdate = disdate;
	}
	public BigDecimal getRedemptionArrivectDate() {
		return redemptionArrivectDate;
	}
	public void setRedemptionArrivectDate(BigDecimal redemptionArrivectDate) {
		this.redemptionArrivectDate = redemptionArrivectDate;
	}
	public String getIsSubscribe() {
		return isSubscribe;
	}
	public void setIsSubscribe(String isSubscribe) {
		this.isSubscribe = isSubscribe;
	}
	public String getIsQuick() {
		return isQuick;
	}
	public void setIsQuick(String isQuick) {
		this.isQuick = isQuick;
	}
	public BigDecimal getBuypayPeriod() {
		return buypayPeriod;
	}
	public void setBuypayPeriod(BigDecimal buypayPeriod) {
		this.buypayPeriod = buypayPeriod;
	}
	public BigDecimal getRedemptionPayPeriod() {
		return redemptionPayPeriod;
	}
	public void setRedemptionPayPeriod(BigDecimal redemptionPayPeriod) {
		this.redemptionPayPeriod = redemptionPayPeriod;
	}
	public BigDecimal getConversionPayPeriod() {
		return conversionPayPeriod;
	}
	public void setConversionPayPeriod(BigDecimal conversionPayPeriod) {
		this.conversionPayPeriod = conversionPayPeriod;
	}
	public BigDecimal getSubpaybackPeriod() {
		return subpaybackPeriod;
	}
	public void setSubpaybackPeriod(BigDecimal subpaybackPeriod) {
		this.subpaybackPeriod = subpaybackPeriod;
	}
	public BigDecimal getDividendpayPeriod() {
		return dividendpayPeriod;
	}
	public void setDividendpayPeriod(BigDecimal dividendpayPeriod) {
		this.dividendpayPeriod = dividendpayPeriod;
	}
	public String getShareClass() {
		return shareClass;
	}
	public void setShareClass(String shareClass) {
		this.shareClass = shareClass;
	}
	public String getShareClassName() {
		return shareClassName;
	}
	public void setShareClassName(String shareClassName) {
		this.shareClassName = shareClassName;
	}
	public String getTargetFundcode() {
		return targetFundcode;
	}
	public void setTargetFundcode(String targetFundcode) {
		this.targetFundcode = targetFundcode;
	}
	public String getAgencyFeeFlag() {
		return agencyFeeFlag;
	}
	public void setAgencyFeeFlag(String agencyFeeFlag) {
		this.agencyFeeFlag = agencyFeeFlag;
	}
	public String getIsGuaranteedFund() {
		return isGuaranteedFund;
	}
	public void setIsGuaranteedFund(String isGuaranteedFund) {
		this.isGuaranteedFund = isGuaranteedFund;
	}
	public String getIsLofFund() {
		return isLofFund;
	}
	public void setIsLofFund(String isLofFund) {
		this.isLofFund = isLofFund;
	}
	public String getIsqdiiFund() {
		return isqdiiFund;
	}
	public void setIsqdiiFund(String isqdiiFund) {
		this.isqdiiFund = isqdiiFund;
	}
	public String getIsetfFund() {
		return isetfFund;
	}
	public void setIsetfFund(String isetfFund) {
		this.isetfFund = isetfFund;
	}
	public String getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}
	public String getLiquidation() {
		return liquidation;
	}
	public void setLiquidation(String liquidation) {
		this.liquidation = liquidation;
	}
	public String getSubscribeAffirm() {
		return subscribeAffirm;
	}
	public void setSubscribeAffirm(String subscribeAffirm) {
		this.subscribeAffirm = subscribeAffirm;
	}
	public String getFixedInvestment() {
		return fixedInvestment;
	}
	public void setFixedInvestment(String fixedInvestment) {
		this.fixedInvestment = fixedInvestment;
	}
	public String getMandatoryRedemption() {
		return mandatoryRedemption;
	}
	public void setMandatoryRedemption(String mandatoryRedemption) {
		this.mandatoryRedemption = mandatoryRedemption;
	}
	public String getIsDiscount() {
		return isDiscount;
	}
	public void setIsDiscount(String isDiscount) {
		this.isDiscount = isDiscount;
	}
	public String getExpectclose() {
		return expectclose;
	}
	public void setExpectclose(String expectclose) {
		this.expectclose = expectclose;
	}
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public BigDecimal getSpecifyRateFee() {
		return specifyRateFee;
	}
	public void setSpecifyRateFee(BigDecimal specifyRateFee) {
		this.specifyRateFee = specifyRateFee;
	}
	public BigDecimal getSpecifyFee() {
		return specifyFee;
	}
	public void setSpecifyFee(BigDecimal specifyFee) {
		this.specifyFee = specifyFee;
	}
	public String getCashAccountid() {
		return cashAccountid;
	}
	public void setCashAccountid(String cashAccountid) {
		this.cashAccountid = cashAccountid;
	}
	public BigDecimal getMonthlySales() {
		return monthlySales;
	}
	public void setMonthlySales(BigDecimal monthlySales) {
		this.monthlySales = monthlySales;
	}
	public BigDecimal getPopulararity() {
		return populararity;
	}
	public void setPopulararity(BigDecimal populararity) {
		this.populararity = populararity;
	}
	public String getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public String getAnnualGain() {
		return annualGain;
	}
	public void setAnnualGain(String annualGain) {
		this.annualGain = annualGain;
	}
	public String getIntervalT() {
		return intervalT;
	}
	public void setIntervalT(String intervalT) {
		this.intervalT = intervalT;
	}
	public String getFundUrl() {
		return fundUrl;
	}
	public void setFundUrl(String fundUrl) {
		this.fundUrl = fundUrl;
	}
	public String getFundTypeDesc() {
		return fundTypeDesc;
	}
	public void setFundTypeDesc(String fundTypeDesc) {
		this.fundTypeDesc = fundTypeDesc;
	}
	public String getFundStatusDesc() {
		return fundStatusDesc;
	}
	public void setFundStatusDesc(String fundStatusDesc) {
		this.fundStatusDesc = fundStatusDesc;
	}
	@Override
	public String toString()
	{
		return "FundInfo [fundCode=" + fundCode + ", fundName=" + fundName + ", fundType=" + fundType + ", fundTypeDesc=" + fundTypeDesc + ", fundNameEng=" + fundNameEng + ", totalFundVol="
				+ totalFundVol + ", fundStatus=" + fundStatus + ", fundStatusDesc=" + fundStatusDesc + ", navDat=" + navDat + ", nav=" + nav + ", convertStatus=" + convertStatus + ", tano=" + tano
				+ ", periodicStatus=" + periodicStatus + ", transferAgencyStatus=" + transferAgencyStatus + ", currencyType=" + currencyType + ", fundSize=" + fundSize + ", defDividendMethod="
				+ defDividendMethod + ", custOdianCode=" + custOdianCode + ", ipoStartDate=" + ipoStartDate + ", ipoEndDate=" + ipoEndDate + ", managerCode=" + managerCode + ", fundSponsor="
				+ fundSponsor + ", tradingPrice=" + tradingPrice + ", faceValue=" + faceValue + ", dividentDate=" + dividentDate + ", regiStartionDate=" + regiStartionDate + ", subsType=" + subsType
				+ ", valueLine=" + valueLine + ", updateDate=" + updateDate + ", fundRiskLevel=" + fundRiskLevel + ", fundRiskLevelDesc=" + fundRiskLevelDesc + ", feeFactor=" + feeFactor
				+ ", accumulativeNav=" + accumulativeNav + ", totalIncome=" + totalIncome + ", imcomeYield=" + imcomeYield + ", dividendModify=" + dividendModify + ", onRiskProtocol="
				+ onRiskProtocol + ", onCountractSystem=" + onCountractSystem + ", disdate=" + disdate + ", redemptionArrivectDate=" + redemptionArrivectDate + ", isSubscribe=" + isSubscribe
				+ ", isQuick=" + isQuick + ", buypayPeriod=" + buypayPeriod + ", redemptionPayPeriod=" + redemptionPayPeriod + ", conversionPayPeriod=" + conversionPayPeriod + ", subpaybackPeriod="
				+ subpaybackPeriod + ", dividendpayPeriod=" + dividendpayPeriod + ", shareClass=" + shareClass + ", shareClassName=" + shareClassName + ", targetFundcode=" + targetFundcode
				+ ", agencyFeeFlag=" + agencyFeeFlag + ", isGuaranteedFund=" + isGuaranteedFund + ", isLofFund=" + isLofFund + ", isqdiiFund=" + isqdiiFund + ", isetfFund=" + isetfFund
				+ ", subscribe=" + subscribe + ", liquidation=" + liquidation + ", subscribeAffirm=" + subscribeAffirm + ", fixedInvestment=" + fixedInvestment + ", mandatoryRedemption="
				+ mandatoryRedemption + ", isDiscount=" + isDiscount + ", expectclose=" + expectclose + ", chargeType=" + chargeType + ", specifyRateFee=" + specifyRateFee + ", specifyFee="
				+ specifyFee + ", cashAccountid=" + cashAccountid + ", monthlySales=" + monthlySales + ", populararity=" + populararity + ", isRecommend=" + isRecommend + ", lastDate=" + lastDate
				+ ", annualGain=" + annualGain + ", intervalT=" + intervalT + ", fundUrl=" + fundUrl + "]";
	}
	
	
}
