package com.jhcz.trade.account.biz.constants;


/**
 * 描述:  
 * <br/>版权: Copyright (c) 2015
 * <br/>公司: 金恒创智
 * <br/>版本: 1.0
 * <br/>日期: 
 * <br/>时间: 下午10:10:24
 */
public interface Constants
{
	
	
	//虚拟用户名
	public static final String PRINCIPAL = "ots.principal";
	
	//虚拟用户密码
	public static final String CREDENTIAL = "ots.credential";
	
	/**
	 * 网上交易系统访问域名
	 */
	public static final String HOSTURL_KEY = "ots.sys.access.url";
	
	
	/**
	 * 门户网站系统访问域名
	 */
	public static final String NWS_SYS_URL_KEY = "nws.sys.access.url";
	
	/**
	 * 一账通获取验证码地址
	 */
	public static final String TOA_TICKET_URL_KET = "toa.ticket.url";
	/**
	 * 门户网站系统访问域名
	 */
	public static final String TOA_LOGIN_URL_KET = "toa.login.url";
	
	
	
	/**
	 * CYBERARK相关配置
	 */
	public static final String CYBERARK_CREDFILEPATH = "cyberArk.CredFilePath";
	
	public static final String CYBERARK_Safe = "cyberArk.Safe";
	
	public static final String CYBERARK_Folder = "cyberArk.Folder";
	
	public static final String CYBERARK_Object = "cyberArk.Object";
	
	public static final String CYBERARK_Enable = "cyberArk.Enable";
	
	public static final String ISBANKTEST_KEY = "ots.isBankTest";
	
	
	public static final String PARAM_IS_BANK_TEST_KEY = "isBankTest";
	
	/**
	 * 是否连接银行 true 不连接  false 连接
	 */
	//public static final boolean isBankTest = "true".equalsIgnoreCase(PropertiesUtil.getString(ISBANKTEST_KEY));
	
	public static final String ISDEBUG_KEY = "ots.isDebug";
	public static final String ISCheckTicket_KEY = "ots.isCheckTicket";
	/**
	 * 存折
	 */
	public static final String BANK_CARD_TYPE_00 = "0";
	
	/**
	 * 借记卡			
	 */
	public static final String BANK_CARD_TYPE_01 = "1";
	
	/**
	 * 信用卡		
	 */
	public static final String BANK_CARD_TYPE_02 = "2";
	/**
	 * 银行卡类型-信用卡		
	 */
	public static final String BANK_CARD_TYPE_03 = "3";
	
	/**
	 * 功能类型 信用卡还款
	 */
	public static final String SETTING_TYPE_CREDIT = "2";
	
	/**
	 * 登陆密码
	 */
	public static final String PD_TYPE_LOGIN = "0";
	
	/**
	 * 交易密码
	 */
	public static final String PD_TYPE_TRADE = "1";
	
	/**
	 * 电子签名约定书
	 */
	public static final String ESIGN_CONTENT_TYPE = "5";
	
	/**
	 * 网上交易个人证件类型
	 */
	public static final String PERSON_ID_TYPE_ENUM_NO = "9993";
	
	/**
	 * 个人证件类型
	 */
	public static final String DTS_PERSON_ID_TYPE_ENUM_NO = "1018";
	
	/**
	 * 证件类型
	 */
	public static final String ORG_ID_TYPE_ENUM_NO = "1019";
	
	/**
	 * 基金状态数据字典
	 */
	public static final String FUND_STATUS_ENUM_NO = "1008";
	
	/**
	 * 基金类型数据字典ID
	 */
	public static final String FUND_TYPE_ENUM_NO = "1091";
	
	/**
	 * 省份数据字典
	 */
	public static final String PROVINCE_ENUM_NO = "1146";
	
	/**
	 * 风险类型数据字典
	 */
	public static final String CUST_RISK_NAME = "1170";
	
	/**
	 * 下一个交易日的参数类型
	 */
	public static final String NEXT_WORKDAY = "next_workday";
	
	/**
	 * 问卷ID参数
	 */
	public static final String QUESTION_NO = "question_no";
	
	/**
	 * 默认问卷ID
	 */
	public static final String DEFAULT_QUESTION_NO = "Q001";
	
	/**
	 * 当前交易的参数类型
	 */
	public static final String WORKDAY = "workday";
	
	/**
	 * 机构类型-代销
	 */
	public static final String ORG_TYPE_AGENT = "0";
	
	/**
	 * 证件类型-身份证
	 */
	public static final String IDENTITY_TYPE_CN = "0";
	
	/**
	 * 手机验证码找回
	 */
	public static final String RESET_WAY_MOBILE = "0";
	
	/**
	 * 资料上传方式找回
	 */
	public static final String RESET_WAY_UPLOAD = "1";
	
	/**
	 * 开户协议
	 */
	public static final String AGREEMENT_TYPE_01 = "1";
	
	/**
	 * 个人注册协议
	 */
	public static final String AGREEMENT_TYPE_02 = "2";
	
	/**
	 * 花生宝取现协议
	 */
	public static final String AGREEMENT_TYPE_03 = "3";
	
	/**
	 * 信用卡还款协议
	 */
	public static final String AGREEMENT_TYPE_04 = "4";
	
	/**
	 * 电子签名约定书
	 */
	public static final String AGREEMENT_TYPE_05 = "5";
	
	/**
	 * 风险提示函
	 */
	public static final String AGREEMENT_TYPE_06 = "6";
	
	/**
	 * 平安大华基金销售适用性规则
	 */
	public static final String AGREEMENT_TYPE_07 = "7";
	
	/**
	 * 取现方式  T+0
	 */
	public static final String TAKE_CASH_TYPE_0 = "0";
	
	/**
	 * 取现方式  T+1
	 */
	public static final String TAKE_CASH_TYPE_1 = "1";
	
	/**
	 * 客户类型 个人
	 */
	public static final String CUST_TYPE_01 = "1";
	
	/**
	 * 客户类型机构
	 */
	public static final String CUST_TYPE_00 = "0";
	
	/**
	 * 正常
	 */
	public static final String USER_INFO_STATE_NORMAL = "0";
	
	/**
	 * 锁定
	 */
	public static final String USER_INFO_STATE_LOCK = "1";
	
	/**
	 * 未激活
	 */
	public static final String USER_INFO_STATE_ACTIVATE = "2";
	
	/**
	 * 注销
	 */
	public static final String USER_INFO_STATE_NUSER = "3";
	
	/**
	 * toa公钥
	 */
	public static final String TOA_RSA_PUBLICKEY = "rsa.publicKey";
	
	/**
	 * 重置手机号码
	 */
	public static final String USERINFO_REST_TYPE_00 = "0"; //重置手机号码
	
	/**
	 * 重置登录密码
	 */
	public static final String USERINFO_REST_TYPE_01 = "1"; // 重置登录密码
	
	/**
	 * 重置交易密码
	 */
	public static final String USERINFO_REST_TYPE_02 = "2"; // 重置交易密码
	
	/**
	 * 换卡
	 */
	public static final String USERINFO_REST_TYPE_03 = "3"; //换卡
	
	public static final String TOA_KEY_FILE_PATH = "D:\\CCshare\\PUF-OTS\\ots_j2ee\\tk_fund_tradeweb\\config\\keystore.p7b";
	
	/**
	 * 网上交易网点号
	 */
	public static final String BRANCH_CODE_OTS = "002001";
	
	/**
	 * 直销柜台网点号
	 */
	public static final String BRANCH_CODE_OTC = "001001";
	
	/**
	 * 工商银行编号
	 */
	public static final String ICBC_BANK_NO = "002";
	
	/**
	 * 农业银行编号
	 */
	public static final String ABC_BANK_NO = "003";
	
	/**
	 * 中国银行编号
	 */
	public static final String BOC_BANK_NO = "004";
	
	/**
	 * 建设银行编号
	 */
	public static final String CCB_BANK_NO = "005";
	
	/**
	 * 交通银行编号
	 */
	public static final String JT_BANK_NO = "006";
	
	/**
	 * 招商银行编号
	 */
	public static final String CMB_BANK_NO = "007";
	
	/**
	 * 浦发银行编号
	 */
	public static final String SB_BANK_NO = "011";
	
	/**
	 * 民生银行编号
	 */
	public static final String CMBC_BANK_NO = "014";
	
	/**
	 * 平安银行编号
	 */
	public static final String PA_BANK_NO = "920";
	
	/**
	 * 银联银行编号
	 */
	public static final String CUP_BANK_NO = "999";
	
	/**
	 * 汇付天下编号
	 */
	public static final String AAC_BANK_NO = "AAC";
	
	/**
	 * 普通
	 */
	public static final String G_BANK_PAY_MODE0 = "0"; //普通
	
	/**
	 * 网银
	 */
	public static final String G_BANK_PAY_MODE1 = "1"; //网银
	
	/**
	 * 快捷
	 */
	public static final String G_BANK_PAY_MODE2 = "2"; //快捷
	
	/**
	 * 银联
	 */
	public static final String G_BANK_PAY_MODE4 = "4"; //银联
	
	/**
	 * 汇付
	 */
	public static final String G_BANK_PAY_MODE5 = "5"; //汇付
	
	/**
	 * 通联
	 */
	public static final String G_BANK_PAY_MODE6 = "6"; //通联
	
	/**
	 * 信用卡还款结果广告类型
	 */
	public static final String REPAY_RESULT_ACTIVE_TYPE = "R";
	
	/**
     * 信用卡开通结果广告类型
     */
    public static final String OPENCREDIT_RESULT_ACTIVE_TYPE = "O";
    
	/**
	 * 开户结果广告类型
	 */
	public static final String OPENACCOUNT_RESULT_ACTIVE_TYPE = "1";
	
	/**
	 * 充值结果广告类型
	 */
	public static final String RECHARGE_RESULT_ACTIVE_TYPE = "2";
	
	/**
	 * 取现结果广告类型
	 */
	public static final String TACKCASH_RESULT_ACTIVE_TYPE = "3";
	
	/**
	 * 基金申购结果广告类型
	 */
	public static final String FUNDBUY_RESULT_ACTIVE_TYPE = "4";
	
	/**
	 * 基金赎回结果广告类型
	 */
	public static final String REEDEM_RESULT_ACTIVE_TYPE = "5";
	
	/**
	 * 基金转换结果广告类型
	 */
	public static final String FUNDTRANS_RESULT_ACTIVE_TYPE = "6";
	
	/**
	 * 交易撤单结果广告类型
	 */
	public static final String FUNDCANCEL_RESULT_ACTIVE_TYPE = "7";
	
	/**
	 * 基金修改分红方式结果广告类型
	 */
	public static final String FUNDIVIDENDS_RESULT_ACTIVE_TYPE = "8";
	
	/**
	 * 基金首页广告图 类型
	 */
	public static final String FUND_INDEX_TYPE = "9";
	
	/**
	 * 高端首页广告图 类型
	 */
	public static final String VIPFUND_INDEX_TYPE = "V";
	
	/**
	 * 账户首页广告图 类型
	 */
	public static final String ACCOUNT_INDEX_TYPE = "I";
	/**
	 * 登陆页面广告图类型
	 */
	public static final String LOGIN_INDEX_TYPE = "L";
	
	/**
	 * 签约成功
	 */
	public static final String SIGN_STATUS_01 = "1";
	
	/**
	 * 签约失败
	 */
	public static final String SIGN_STATUS_02 = "2";
	
	/**
	 * 支付成功
	 */
	public static final String BANK_PAY_STATUS_01 = "1";
	
	/**
	 * 快速添加
	 */
	public static final String BANK_BIND_MODE_01 = "1";
	
	/**
	 * 专业添加
	 */
	public static final String BANK_BIND_MODE_02 = "2";
	
	/**
	 * 银联 资金方式
	 */
	public static final String CAPITAL_MODE_CUP = "3";
	
	/**
	 * 汇付天下  资金方式
	 */
	public static final String CAPITAL_MODE_AAC = "6";
	
	/**
	 * 通联  资金方式
	 */
	public static final String CAPITAL_MODE_TL = "M";
	
	/**
	 * 订单状态 待确认
	 */
	public static final String ORDER_STATE_00 = "0";
	
	/**
	 * 订单状态 处理中
	 */
	public static final String ORDER_STATE_01 = "1";
	
	/**
	 * 订单状态 处理成功
	 */
	public static final String ORDER_STATE_02 = "2";
	
	/**
	 * 订单状态 失败
	 */
	public static final String ORDER_STATE_03 = "3";
	
	/**
	 * 待激活
	 */
	public static final String TOA_SSO_RESULT_TYPE_UPGRADE = "upgrade";
	
	/**
	 * 未购买
	 */
	public static final String TOA_SSO_RESULT_TYPE_BUY = "buy";
	
	public static final String TOA_SSO_RESULT_YYPE_LOCK = "locked";
	
	/**
	 * 已购买
	 */
	public static final String TOA_SSO_RESULT_TYPE_ACCOUNT = "toaccount";
	
	/**
	 * 未绑定
	 */
	public static final String TOA_SSO_RESULT_TYPE_UNBIND = "unbind";
	/**
	 * 不存在
	 */
	public static final String TOA_SSO_RESULT_TYPE_NOTEXISTS = "notexists";
	/**
	 * 是否需要走激活流程-未激活,需要
	 */
	public static final String TOA_LANDING_IS_ACTIVATE_0 = "0";
	/**
	 * 是否需要走激活流程-已激活,不需要
	 */
	public static final String TOA_LANDING_IS_ACTIVATE_1 = "1";
	
	/**
	 * SSO是否获取到新数据中心客户编号-不是
	 */
	public static final String TOA_LANDING_IS_NEW_CUSTNO_0 = "0";
	/**
	 * SSO是否获取到新数据中心客户编号-是
	 */
	public static final String TOA_LANDING_IS_NEW_CUSTNO_1 = "1";
	
	/**
	 * 对账单寄送方式数据字典NO
	 */
	public static final String BILLWAY_ENUM_NO = "1069";
	
	/**
	 * 签约状态 需要签约
	 */
	public static final String BANK_SIGN_FLAG_00 = "0";
	
	/**
	 * 签约状态  需要鉴权
	 */
	public static final String BANK_SIGN_FLAG_01 = "1";
	
	/**
	 * 签约状态  已经签约拒绝
	 */
	public static final String BANK_SIGN_FLAG_02 = "2";
	
	/**
	 * 对账单寄送方式：邮寄
	 */
	public static final String ENUM_VALUE_BILL_TYPE_POST = "1";
	
	/**
	 * 对账单寄送方式:email
	 */
	public static final String ENUM_VALUE_BILL_TYPE_EMAIL = "2";
	
	/**
	 * 分红方式数据字典ID
	 */
	public static final String DIVIDENDS_ENUM_NO = "1012";
	
	/**
	 * 最低费率      系统参数
	 */
	public static final String MINIRATEVAL = "minirateval";
	
	/**
	 * 建行网上交易充值附加字段
	 */
	public static final String TK_CCB_WSJYCZ = "001";
	
	/**
	 * 建行网上交易申购附加字段
	 */
	public static final String TK_CCB_WSJYSG = "002";
	/**
	 * 新直销网上交易交易用户-未激活
	 */
	public static final String SSO_CUST_TYPE_TOA_1 = "1";
	/**
	 * 新直销网上交易交易用户-已激活
	 */
	public static final String SSO_CUST_TYPE_TOA_2 = "2";
	/**
	 * 新直销查询-新直销网上交易非交易用户但有身份信息即快速查询通道进来的客户
	 */
	public static final String SSO_CUST_TYPE_TOA_3 = "3";
	/**
	 * 柜台/合作伙伴/代销
	 */
	public static final String SSO_CUST_TYPE_TOA_4 = "4";
	/**
	 * 不存在
	 */
	public static final String SSO_CUST_TYPE_TOA_5 = "5";
	/**
	 * 发送验证码：您正在升级平安大华基金帐户
	 */
	public static final String TOA_MFA_UPGRADE = "升级";
	/**
	 * 发送验证码：您正在添加平安大华基金帐户
	 */
	public static final String TOA_MFA_BIND = "添加";
	/**
	 * 日志类型(业务类型) 0、登录 1、登出 2、激活 3、邮箱验证 4、首页引导 5、注册 6、登录密码 7、修改手机号码 8、开户、绑卡 9、交易密码
	 */
	public static final String USER_LOG_TYPE_0 = "0";
	/**
	 * 1、登出
	 */
	public static final String USER_LOG_TYPE_1 = "1";
	/**
	 * 2、激活
	 */
	public static final String USER_LOG_TYPE_2 = "2";
	/**
	 * 3、邮箱验证 
	 */
	public static final String USER_LOG_TYPE_3 = "3";
	/**
	 * 4、首页引导
	 */
	public static final String USER_LOG_TYPE_4 = "4";
	/**
	 *  5、注册
	 */
	public static final String USER_LOG_TYPE_5 = "5";
	/**
	 * 6、登录密码 
	 */
	public static final String USER_LOG_TYPE_6 = "6";
	/**
	 * 7、修改手机号码
	 */
	public static final String USER_LOG_TYPE_7 = "7";
	/**
	 * 8、开户、绑卡
	 */
	public static final String USER_LOG_TYPE_8 = "8";
	/**
	 * 9、交易密码
	 */
	public static final String USER_LOG_TYPE_9 = "9";
	/**
	 * 10、一账通加挂
	 */
	public static final String USER_LOG_TYPE_10 = "10";
	/**
	 * 11、注销
	 */
	public static final String USER_LOG_TYPE_11 = "11";

   /*
    * NPS需求签名的约定码：OTS-CEM-5924    BY ZHUJICHENG  2015-11-30 
    */
	public static final String NPS_SIGN_YDMM = "OTS-CEM-5924";
	
	/*
	 * NPS需求 合作者编码,基金：PA004：PartnerName    BY ZHUJICHENG  2015-11-30 
	 */
	public static final String NPS_PARTNER_NAME = "PA004";
	
	/*
	 * NPS需求  01：PC;02:APP. 基金为PC填写:01   BY ZHUJICHENG  2015-11-30 
	 */
	public static final String NPS_CHANNEL = "01";

	/*
	 * NPS需求   CEM提供，模板编号：JJ_NO000000000   BY ZHUJICHENG  2015-11-30 
	 */
	public static final String NPS_CEM_NO_APPLY = "JJ_NO0000000005";
	
	public static final String NPS_CEM_NO_REDEEM = "JJ_NO0000000001";
	
	
	/*
	 * NPS需求  nps系统url地址   BY ZHUJICHENG  2015-11-30 
	 */
	public static final String NPS_SYS_URL_KEY = "nps.sys.access.url";
	
	/**
	 * 证券市场指数数据字典
	 */
	public static final String STOCK_INDEX_ENUM_NO = "stock_index";
	
	/**
	 * 指数均线数据字典
	 */
	public static final String INDEXOFAVERAGE_ENUM_NO = "indexofaverage";
	
	/**
	 * 浮动比例
	 */
	public static final String DEVIATE_GEARS_ENUM_NO = "deviate_gears";
	
	/**
	 * 花生宝的cashaccountid
	 */
	public static final String HSB_CASHACCOUNTID= "HSB";
	/**
	 * 机构注册校验号码
	 */
	public static final String CHECKREGIST_Y2 = "y2";
	/**
	 * 个人注册校验号码-流程需与某经办人用户校验
	 */
	public static final String CHECKREGIST_Y1 = "y1";
	/**
	 * 修改/激活验证手机号码
	 */
	public static final String CHECKREGIST_Y = "y";
	/**
     * 银行卡非主卡标识
     */
    public final static String BANK_NOT_MAIN_FLAG = "0";                     
    /**
     * 银行卡主卡标识
     */
    public final static String BANK_MAIN_FLAG = "1";          
    
    /**
     * 线下上传
     */
    public final static String UPLOAD_WAY_UNDERLINE	="1";
}
