package com.jhcz.trade.account.biz.bean;

import com.jhcz.trade.common.vo.BaseVO;

public class UserInfo extends BaseVO{

	private static final long serialVersionUID = 7556429293966131713L;
	
	
	private String custNo; //客户号
	private String custNoOfSale;   //直销返回的客户号（账户系统）
	private String identityType; //证件类型
	private String identityNum;  //证件号码
	private String name; //姓名
	private String custType;  //客户类型，0-机构、1-个人
	private String isTrade; //是否开户 0-未开户、1-已开户
	private String tradeAccount;//交易账号
	private String mobile; //手机号码
	private String email;//邮箱
	private String state;//账户状态
	private String loginByMobile;
	private String loginByEmail;
	private String riskGrade; //风险等级
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustNoOfSale() {
		return custNoOfSale;
	}
	public void setCustNoOfSale(String custNoOfSale) {
		this.custNoOfSale = custNoOfSale;
	}
	public String getIdentityType() {
		return identityType;
	}
	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}
	public String getIdentityNum() {
		return identityNum;
	}
	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCustType() {
		return custType;
	}
	public void setCustType(String custType) {
		this.custType = custType;
	}
	public String getIsTrade() {
		return isTrade;
	}
	public void setIsTrade(String isTrade) {
		this.isTrade = isTrade;
	}
	public String getTradeAccount() {
		return tradeAccount;
	}
	public void setTradeAccount(String tradeAccount) {
		this.tradeAccount = tradeAccount;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLoginByMobile() {
		return loginByMobile;
	}
	public void setLoginByMobile(String loginByMobile) {
		this.loginByMobile = loginByMobile;
	}
	public String getLoginByEmail() {
		return loginByEmail;
	}
	public void setLoginByEmail(String loginByEmail) {
		this.loginByEmail = loginByEmail;
	}
	public String getRiskGrade() {
		return riskGrade;
	}
	public void setRiskGrade(String riskGrade) {
		this.riskGrade = riskGrade;
	}
}
