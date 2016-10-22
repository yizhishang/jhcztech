package com.jhcz.trade.bankcard.bean;

/**
 * @类名: UserBankCardBean
 * @包名 com.jhcz.trade.bankcard.bean
 * @描述:用户绑卡信息
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-3-22 下午1:34:04
 * @版本 V1.0
 */
public class UserBankCardBean {
	  private String id_tuserbankcard ;  // 记录编号(唯一)
	  private String cashaccountid  ; //'资金交易账号(银行卡的虚拟交易账号，主要用来做流量控制)'    
	  private String transactionaccountid; //交易账号
	  private String branch_code  ; //  网点编号     
	  private String cust_no   ;   //  客户编号      
	  private String bank_acct ;  //银行账号        
	  private String bank_acct_name;  //银行户名     
	  private String bank_no;    // 银行编号         
	  private String capital_mode;  //资金方式       
	  private String main_flag;  // 主卡标志(一个网点只能有一个主卡)        
	  private String card_type ; //卡类型(0:储蓄卡   1:信用卡)        
	  private String auth_flag;  // 认证标志(0:未认证  1:已认证(已经通过密码校验))         
	  private String state ;      //卡状态(0:正常 1:冻结 2:注销)        
	  private String province_code ; //省      
	  private String city_no;   // 城市          
	  private String county ;  //县            
	  private String area_code ;//地区代码           
	  private String last_date; //最后更新日期          
	  private String fcu  ;  //录入人员              
	  private String fcd ; // 创建日期               
	  private String lmu ; // 更新人员               
	  private String lmd;  // 更新日期  
	  
	 public String getId_tuserbankcard() {
		return id_tuserbankcard;
	}
	public void setId_tuserbankcard(String id_tuserbankcard) {
		this.id_tuserbankcard = id_tuserbankcard;
	}
	public String getCashaccountid() {
		return cashaccountid;
	}
	public void setCashaccountid(String cashaccountid) {
		this.cashaccountid = cashaccountid;
	}
	public String getTransactionaccountid() {
		return transactionaccountid;
	}
	public void setTransactionaccountid(String transactionaccountid) {
		this.transactionaccountid = transactionaccountid;
	}
	public String getBranch_code() {
		return branch_code;
	}
	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}
	public String getCust_no() {
		return cust_no;
	}
	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
	}
	public String getBank_acct() {
		return bank_acct;
	}
	public void setBank_acct(String bank_acct) {
		this.bank_acct = bank_acct;
	}
	public String getBank_acct_name() {
		return bank_acct_name;
	}
	public void setBank_acct_name(String bank_acct_name) {
		this.bank_acct_name = bank_acct_name;
	}
	public String getBank_no() {
		return bank_no;
	}
	public void setBank_no(String bank_no) {
		this.bank_no = bank_no;
	}
	public String getCapital_mode() {
		return capital_mode;
	}
	public void setCapital_mode(String capital_mode) {
		this.capital_mode = capital_mode;
	}
	public String getMain_flag() {
		return main_flag;
	}
	public void setMain_flag(String main_flag) {
		this.main_flag = main_flag;
	}
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	public String getAuth_flag() {
		return auth_flag;
	}
	public void setAuth_flag(String auth_flag) {
		this.auth_flag = auth_flag;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getProvince_code() {
		return province_code;
	}
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
	public String getCity_no() {
		return city_no;
	}
	public void setCity_no(String city_no) {
		this.city_no = city_no;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public String getLast_date() {
		return last_date;
	}
	public void setLast_date(String last_date) {
		this.last_date = last_date;
	}
	public String getFcu() {
		return fcu;
	}
	public void setFcu(String fcu) {
		this.fcu = fcu;
	}
	public String getFcd() {
		return fcd;
	}
	public void setFcd(String fcd) {
		this.fcd = fcd;
	}
	public String getLmu() {
		return lmu;
	}
	public void setLmu(String lmu) {
		this.lmu = lmu;
	}
	public String getLmd() {
		return lmd;
	}
	public void setLmd(String lmd) {
		this.lmd = lmd;
	}
}
