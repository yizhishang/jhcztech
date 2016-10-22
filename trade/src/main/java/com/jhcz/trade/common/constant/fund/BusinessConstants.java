/*
 * Copyright (c) 2014 Your Corporation. All Rights Reserved.
 */

package com.jhcz.trade.common.constant.fund;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者:	 艾波
 * 版本:	 1.0
 * 创建日期: 
 * 创建时间: 13:28:54
 */
public final class BusinessConstants
{
	
	/**
	 * *****************************************************************
	 * //初始化系统全局参数变量
	 * //所有变量名称以 G_ 开头，使用全大写
	 * //G_DB_ 开头的为数据库配置
	 * //G_IS_ 开头的为布尔值
	 * ******************************************************************
	 */
	
	/**
	 * ***************************************************************
	 * *********************   系统参数名称定义区域  ************************
	 * *****************************************************************
	 */
	
	public final static String G_FUND_STOP_CFG_RETURN_CODE_NORMAL = "0000";//成功
	
	/**
	 * ***************************************************************
	 * *********************   枚举变量定义区域  ************************
	 * *****************************************************************
	 */
	/**
	 * 客户类型
	 */
	public final static String G_CUST_TYPE_PUBLIC = "0";//机构客户
	
	public final static String G_CUST_TYPE_PERSON = "1";//个人客户
	
	/**
	 * 币种
	 */
	public final static String G_CURRENCY_RMB = "156";//人民币
	
	/**
	 * 银行卡标识
	 */
	public final static String G_BANK_NOT_MAIN_FLAG = "0";//银行卡非主卡标识
	
	public final static String G_BANK_MAIN_FLAG = "1";//银行卡主卡标识
	
	/**
	 * 银行卡状态
	 */
	public final static String G_BANK_CARD_STATUS_NORMAL = "0";//银行卡状态-正常
	
	public final static String G_BANK_CARD_STATUS_FREEZE = "1";//银行卡状态-冻结
	
	public final static String G_BANK_CARD_STATUS_CLOSING = "2";//银行卡状态-销户
	
	/**
	 * 银行卡认证标志
	 */
	public final static String G_BANK_CARD_NOT_AUTH = "0";//未认证
	
	public final static String G_BANK_CARD_ALREADY_AUTH = "1";//已认证
	
	/**
	 * 电子协议签署标志
	 */
	public final static String G_BOOK_TYPE_NOSIGN = "0";//未签署
	
	public final static String G_BOOK_TYPE_SIGN = "1";//已签署
	
	/**
	 * 分红方式
	 */
	public final static String G_DIVIDEND_METHOD_REINVESTMENT = "0";//红利再投资
	
	public final static String G_DIVIDEND_METHOD_CASH = "1"; //现金红利
	
	/**
	 * 是否超级管理员
	 */
	public final static String G_USER_IS_NOT_ADMIN = "0";//否
	
	public final static String G_USER_IS_ADMIN = "1";//是
	
	/**
	 * 是否管理台用户
	 */
	public final static String G_USER_IS_NOT_MANAGE = "0";//否
	
	public final static String G_USER_IS_MANAGE = "1";//是
	
	/**
	 * TA状态
	 */
	public final static String G_TAINFO_STATUS_OK = "0";//正常
	
	public final static String G_TAINFO_STATUS_STOP = "1";//停用
	
	/**
	 * 电子签名签署查询标记
	 */
	public final static String G_PROTOCOL_BOOKTYPE_0 = "0";//未签署
	
	public final static String G_PROTOCOL_BOOKTYPE_1 = "1";//已签署
	
	/**
	 * 用户风险揭示书签署标志
	 */
	public final static String G_USER_ONRISKPROTOCOL_BOOK_0 = "0";//0:未签署
	
	public final static String G_USER_ONRISKPROTOCOL_BOOK_1 = "1";//1:已签署
	
	/**
	 * 基金产品是否可销售
	 */
	public final static String C_FUND_DISSTATUS_0 = "0";//不可销售
	
	public final static String C_FUND_DISSTATUS_1 = "1";//可销售
	
	/**
	 * 基金产品定时定额状态
	 */
	public final static String C_FUND_PLAN_STATUS_F = "0";//不可定投
	
	public final static String C_FUND_PLAN_STATUS_A = "1";//可定投
	
	/**
	 * 是否需要签署风险揭示书
	 */
	public final static String C_FUND_ONRISKPROTOCOL_0 = "0";//不需要
	
	public final static String C_FUND_ONRISKPROTOCOL_1 = "1";//需要
	
	/**
	 * 用户电子合同签署标志
	 */
	public final static String G_USER_CONTRACT_BOOK_0 = "0";//0:未签署
	
	public final static String G_USER_CONTRACT_BOOK_1 = "1";//1:已签署
	
	/**
	 * 交易日期标识
	 */
	public final static String G_TRADE_DATE_FLAG = "1";
	
	/**
	 * 默认交易日历标识
	 */
	public final static String G_TRADE_DATE_DEFAULT = "default";
	
	/**
	 * 银行签约协议签约标志
	 */
	public final static String G_USER_BANK_ROTOCOL_UNSIGNED = "0";//未签约
	
	public final static String G_USER_BANK_ROTOCOL_SIGN = "1";//已签约
	
	public final static String G_USER_BANK_ROTOCOL_UNWIND = "2";//已解约
	
	/**
	 * 基金是否可更改分红方式
	 */
	public final static String G_IS_DIVIDENDMODIFY_0 = "0";//0:可更改
	
	public final static String G_IS_DIVIDENDMODIFY_1 = "1";//不可更改
	
	/**
	 * 业务代码
	 */
	public final static String G_FUND_BUSINESSCODE_001 = "001";//开户
	
	public final static String G_FUND_BUSINESSCODE_002 = "002";//销户
	
	public final static String G_FUND_BUSINESSCODE_003 = "003";//账户信息修改申请
	
	public final static String G_FUND_BUSINESSCODE_004 = "004";//基金账户冻结
	
	public final static String G_FUND_BUSINESSCODE_005 = "005";//基金账户解冻
	
	public final static String G_FUND_BUSINESSCODE_006 = "006";//基金账号挂失申请
	
	public final static String G_FUND_BUSINESSCODE_007 = "007";//基金账号解挂申请
	
	public final static String G_FUND_BUSINESSCODE_008 = "008";//增开
	
	public final static String G_FUND_BUSINESSCODE_009 = "009";//撤销(最后一个就走销户)
	
	public final static String G_FUND_BUSINESSCODE_020 = "020";//认购
	
	public final static String G_FUND_BUSINESSCODE_022 = "022";//申购
	
	public final static String G_FUND_BUSINESSCODE_023 = "023";//申购（预约申购、下单）
	
	public final static String G_FUND_BUSINESSCODE_024 = "024";//赎回
	
	public final static String G_FUND_BUSINESSCODE_025 = "025";//预约赎回
	
	public final static String G_FUND_BUSINESSCODE_031 = "031"; //基金份额冻结申请业务标识 031
	
	public final static String G_FUND_BUSINESSCODE_032 = "032";//基金份额解冻申请业务标识 032
	
	public final static String G_FUND_BUSINESSCODE_036 = "036";//基金转换申请
	
	public final static String G_FUND_BUSINESSCODE_038 = "038";//基金转换转出申请(该代码是用于跨TA基金转换使用，暂不使用)
	
	public final static String G_FUND_BUSINESSCODE_039 = "039";//基金定投
	
	public final static String G_FUND_BUSINESSCODE_029 = "029";//分红方式变更
	
	public final static String G_FUND_BUSINESSCODE_026 = "026";//一步转托管
	
	public final static String G_FUND_BUSINESSCODE_027 = "027";//两部转托管
	
	public final static String G_FUND_BUSINESSCODE_028 = "028";//托管转出
	
	public final static String G_FUND_BUSINESSCODE_033 = "033";//非交易过户申请
	
	public final static String G_FUND_BUSINESSCODE_059 = "059";//定投设置
	
	public final static String G_FUND_BUSINESSCODE_060 = "060";//删除定投
	
	public final static String G_FUND_BUSINESSCODE_061 = "061";//修改定投
	
	public final static String G_FUND_BUSINESSCODE_098 = "098";//快速赎回
	
	//public final static String G_FUND_BUSINESSCODE_98 = "98";//快速赎回（实时TA）
	public final static String G_FUND_BUSINESSCODE_052 = "052";//交易撤单
	
	public final static String G_FUND_BUSINESSCODE_143 = "143";//红利/红利再投资发放
	
	//业务代码扩展
	public final static String G_FUND_BUSINESSCODE_A02 = "A02";//登记交易账户
	
	public final static String G_FUND_BUSINESSCODE_A03 = "A03";//撤消交易类申请
	
	public final static String G_FUND_BUSINESSCODE_A04 = "A04";//增开TA账号
	
	public final static String G_FUND_BUSINESSCODE_A06 = "A06";//交易账号资料修改
	
	public final static String G_FUND_BUSINESSCODE_A40 = "A40";//添加银行卡
	
	public final static String G_FUND_BUSINESSCODE_A41 = "A41";//删除银行卡
	
	public final static String G_FUND_BUSINESSCODE_A42 = "A42";//修改银行卡
	
	public final static String G_FUND_BUSINESSCODE_A43 = "A43";//资金存入
	
	public final static String G_FUND_BUSINESSCODE_A44 = "A44";//资金取出
	
	public final static String G_FUND_BUSINESSCODE_A45 = "A45";//资金调整
	
	public final static String G_FUND_BUSINESSCODE_A22 = "A22";//取消基金账号登记
	
	public final static String G_FUND_BUSINESSCODE_A23 = "A23";//交易账号冻结
	
	public final static String G_FUND_BUSINESSCODE_A24 = "A24";//交易账号解冻
	
	public final static String G_FUND_BUSINESSCODE_A30 = "A30";//电子合同签名
	
	public final static String G_FUND_BUSINESSCODE_A35 = "A35";//客户风险承受能力评估
	
	public final static String G_FUND_BUSINESSCODE_T24 = "T24";//基金强赎
	
	/**
	 * 实时TA更新客户资料的业务类型
	 */
	public static final String G_TA_MODIFY_CLIENTINFO_BUSI = "03";
	
	/**
	 * 操作来源
	 */
	public final static String G_OP_SOURCE_CENTER = "0";//综合中心
	
	public final static String G_OP_SOURCE_MANAGE = "1";//直销柜台
	
	public final static String G_OP_SOURCE_ONLINE = "2";//网上交易
	
	/**
	 * 工作线程标志
	 */
	public final static String G_WORK_THREAD_MAIN_FLAG = "0";//主线程标志
	
	public final static String G_WORK_THREAD_NOT_MAIN_FLAG = "1";//非主线程标志
	
	/**
	 * 资金存入支出
	 */
	public final static String G_ASSETS_TYPE_IN_AMOUNT = "0";//存入
	
	public final static String G_ASSETS_TYPE_OUT_AMOUNT = "1";//支出
	
	/**
	 * 票据确认标志
	 */
	public final static String G_BILL_NOTAFFIRM = "0";//未确认
	
	public final static String G_BILL_AFFIRM = "1";//确认有效
	
	public final static String G_BILL_INVALID = "2";//确认无效
	
	/**
	 * 当日流水冻结原因 1-柜台冻结
	 */
	public final static String G_CUST_TRANS_LOG_FROZENCAUSE_BAR = "1";
	
	/**
	 * 销售机构类型
	 */
	public static final String G_ORG_TYPE_ZX = "0";//直销
	
	public static final String G_ORG_TYPE_DX = "1";//代销
	
	/**
	 * TA类型
	 */
	public static final String G_TA_TYPE_ZJ = "0";//自建TA
	
	public static final String G_TA_TYPE_ZD = "1";//中登TA
	
	public static final String G_TA_TYPE_OUT = "2";//外部TA
	
	/**
	 * 多渠道开户标志
	 */
	public final static String G_MULTIACCTFLAG_FIRST = "0";//多渠道开户标志-首次
	
	public final static String G_MULTIACCTFLAG_ADD = "1";//多渠道开户标志-增开
	
	/**
	 * 转托管类型
	 */
	public final static String G_TRANSFERTYPE_SINGLE_STEP = "1";//一步转托管
	
	public final static String G_TRANSFERTYPE_TWO_STEP = "2";//两步转托管
	
	public final static String G_TRANSFERTYPE_ALL = "3";//都支持
	
	/**
	 * 开户标志
	 */
	public final static String G_OPEN_ACCOUNT_FLAG_ZXKH = "1";//开户标识-直销开户
	
	public final static String G_OPEN_ACCOUNT_FLAG_ZXDJ = "0";//开户标识-直销登记
	
	/**
	 * 转托管状态
	 */
	public final static String G_TRANSFER_OF_CUSTODY_ALLOW_ALL = "0";//允许所有转托管
	
	public final static String G_TRANSFER_OF_CUSTODY_SCW = "1";//仅允许场外转托管
	
	public final static String G_TRANSFER_OF_CUSTODY_KSC = "2";//仅允许跨市场转托管
	
	public final static String G_TRANSFER_OF_CUSTODY_FORBID_ALL = "3";//禁止所有转托管
	
	/**
	 * 问卷题目状态
	 */
	public final static String G_QUSTION_TITLE_STATUS_USE = "0";//使用
	
	public final static String G_QUSTION_TITLE_STATUS_STOP = "1";//停用
	
	/**
	 * 合同签署类型
	 */
	public final static String G_CONTRACT_TYPE_NEW = "1";//新签合同
	
	public final static String G_CONTRACT_TYPE_OLD = "2";//补签
	
	/**
	 * 客户已签署风险揭示书
	 */
	public final static String G_RISK_DISCLOSURE_LETTER_NOTSIGNED = "0";//未签署风险揭示书
	
	public final static String G_RISK_DISCLOSURE_LETTER_SIGNED = "1";//已签署风险揭示书
	
	public final static String G_RISK_DISCLOSURE_LETTER_SIGNEDPAPER = "2";//已签署纸质风险揭示书
	
	/**
	 * 网点类别-1078
	 */
	public final static String G_BRANCH_TYPE_COMMON = "0";//普通网点
	
	public final static String G_BRANCH_TYPE_AGENCY = "1";//代办网点
	
	public final static String G_BRANCH_TYPE_WEB = "9";//网上交易网点
	
	/**
	 * 柜台资金方式-1102
	 */
	public final static String G_COUNTER_MONEY_MODE_COMMON = "1";//普通方式
	
	/**
	 * 资金方式-1003
	 */
	public final static String G_FUND_WAY_NORMAL = "1";//普通方式
	
	public final static String G_FUND_WAY_CHINAPAY = "3";//银联通
	
	public final static String G_FUND_WAY_ICBC_INTERNETBANK = "4";//工行网银
	
	public final static String G_FUND_WAY_E_UNIONPAY = "5"; //好易联
	
	public final static String G_FUND_WAY_CHINAPNR = "6";//汇付天下
	
	public final static String G_FUND_WAY_ICBC = "7"; //工行银基通
	
	public final static String G_FUND_WAY_E_UNIONPAY_TS = "8"; //好易联托收
	
	public final static String G_FUND_WAY_BANK_DS = "9"; //银行代收
	
	public final static String G_FUND_WAY_ABC_INTERNETBANK = "A";//农行网银
	
	public final static String G_FUND_WAY_CCB_INTERNETBANK = "B";//建行网银
	
	public final static String G_FUND_WAY_BANK_OF_COMMUNICATIONS_INTERNETBANK = "C";//交行网银
	
	public final static String G_FUND_WAY_BEI_JING = "D";//北京银行
	
	public final static String G_FUND_WAY_ALIPAY = "E";//支付宝
	
	public final static String G_FUND_WAY_SPDB_INTERNETBANK = "F";//浦发网银
	
	public final static String G_FUND_WAY_CMBC_INTERNETBANK = "G";//招行网银
	
	public final static String G_FUND_WAY_OPEN_UNION_INTERNETBANK = "H";//开联网银
	
	public final static String G_FUND_WAY_FUIOU = "I";//富友
	
	public final static String G_FUND_WAY_MS_INTERNETBANK = "J";//民生网银
	
	public final static String G_FUND_WAY_OFF_LINE = "K";//网下转账
	
	public final static String G_FUND_WAY_PA_INTERNETBANK = "L";//平安网银
	
	public final static String G_FUND_WAY_ALLINPAY = "M";//通联
	
	public final static String G_FUND_WAY_BOC_INTERNETBANK = "N";//中行网银
	
	public final static String G_FUND_WAY_YEEPAY = "P";//易宝
	
	public final static String G_FUND_WAY_TENPAY = "Q";//财富通
	
	public final static String G_FUND_WAY_OTHER_PAY = "AA"; //自带支付渠道来源
	
	public final static String G_FUND_WAY_PA_PAY = "BB";//平安付
	
	public final static String G_FUND_WAY_XJB = "CC"; //花生宝
	
	/**
	 * 网点状态-1103
	 */
	public final static String G_BRANCH_STATUS_STOPLOGIN = "0";//停止登录
	
	public final static String G_BRANCH_STATUS_NORMAL = "1";//正常
	
	/**
	 * 银行卡类型-1113
	 */
	public final static String G_BANK_CARD_TYPE_BANKBOOK = "0";//存折
	
	public final static String G_BANK_CARD_TYPE_DEBITCARD = "1";//借记卡
	
	public final static String G_BANK_CARD_TYPE_CREDITCARD = "2";//信用卡
	
	/**
	 * 基金转换状态-1203
	 */
	public final static String G_FUND_SWITCHING_STATE_KZRZC = "0";//可转入转出
	
	public final static String G_FUND_SWITCHING_STATE_ZKZR = "1";//只可转入
	
	public final static String G_FUND_SWITCHING_STATE_ZKZC = "2";//只可转出
	
	public final static String G_FUND_SWITCHING_STATE_BKZRZC = "3";//不可转入转出
	
	/**
	 * 基金类别-1091
	 */
	//public final static String G_FUND_TYPE_BBJJ = "4";//保本基金
	public final static String G_FUND_TYPE_COMMON = "01";//股票
	
	public final static String G_FUND_TYPE_BONDS = "02";//债卷
	
	public final static String G_FUND_TYPE_MIXED = "03";//混合
	
	public final static String G_FUND_TYPE_CURRENCY = "04";//货币
	
	public final static String G_FUND_TYPE_ZHCP = "08";//专户产品
	
	public final static String G_FUND_TYPE_QDII = "06";//QDII基金
	
	/**
	 * 风险限制类型-1172
	 */
	public final static String G_RISK_LIMIT_TYPE_CAN_TRANS = "0";//可交易
	
	public final static String G_RISK_LIMIT_TYPE_REMINDER = "1";//提示
	
	public final static String G_RISK_LIMIT_TYPE_MANAGERS_AUTHORIZATION = "2";//主管授权
	
	public final static String G_RISK_LIMIT_TYPE_TRANS_ASTRICT = "3";//交易限制
	
	/**
	 * 调整方向-1011
	 */
	public final static String G_ADJUST_DIRECTION_INCREASE = "0";//调增
	
	public final static String G_ADJUST_DIRECTION_REDUCE = "1";//调减
	
	/**
	 * 交易账户状态，交易账号状态和基金账号状态通用常量
	 * 0－正常、1－新开户、5－正在销户、6－销户、7－正在冻结 8－冻结 A－挂失 Z－锁定
	 */
	public final static String G_ACCT_STATE_NORMAL = "0";//正常
	
	public final static String G_ACCT_STATE_NEW = "1";//新开户
	
	public final static String G_ACCT_STATE_CLOSING_UNDERWAY = "5";//正在销户
	
	public final static String G_ACCT_STATE_CLOSING = "6";//销户
	
	public final static String G_ACCT_STATE_FREEZING_UNDERWAY = "7";//正在冻结
	
	public final static String G_ACCT_STATE_FREEZE = "8";//冻结
	
	public final static String G_ACCT_STATE_REPORT_THE_LOSS_OF = "A";//挂失
	
	public final static String G_ACCT_STATE_LOCK = "Z";//锁定
	
	/**
	 * 份额类别-1007
	 */
	public final static String G_SHARE_TYPE_BEFORE_CHARGE = "0";//前收费
	
	public final static String G_SHARE_TYPE_CHARGE_LATER = "1";//后收费
	
	public final static String G_SHARE_TYPE_ALL = "2";//前后收费共用（基金代码）
	
	public final static String G_SHARE_TYPE_NONE = "3"; //无申购费，按保有量收费
	
	/**
	 * 基金状态-1008
	 */
	public final static String G_FUND_STATUS_NORMAL = "0";//正常
	
	public final static String G_FUND_STATUS_IN_ISSUE = "1";//发行中
	
	public final static String G_FUND_STATUS_ISSUE_SUCCESS = "2";//发行成功
	
	public final static String G_FUND_STATUS_ISSUE_FAIL = "3";//发行失败
	
	public final static String G_FUND_STATUS_SHUT_THE_BOOK_ = "4";//停止交易
	
	public final static String G_FUND_STATUS_SUBSCRIBE_STOP = "5";//停止申购
	
	public final static String G_FUND_STATUS_REDEMPTION_STOP = "6";//停止赎回
	
	public final static String G_FUND_STATUS_REGISTER = "7";//权益登记
	
	public final static String G_FUND_STATUS_BONUS = "8";//红利发放
	
	public final static String G_FUND_STATUS_CLOSE = "9";//基金封闭
	
	public final static String G_FUND_STATUS_END = "a";//基金终止
	
	public final static String G_FUND_STATUS_FREEZE = "D";//冻结
	
	/**
	 * 基金账户类型-1017
	 */
	public final static String G_FUNDACCT_NORMAL = "A";//普通账户
	
	/**
	 * 个人证件类型-1018
	 */
	public final static String G_PERSONAL_DOCUMENTATION_IDENTITY_CARD = "0";//身份证
	
	public final static String G_PERSONAL_DOCUMENTATION_PASSPORT = "1";//护照
	
	public final static String G_PERSONAL_DOCUMENTATION_CERTIFICATE_OF_OFFICERS = "2";//军官证
	
	public final static String G_PERSONAL_DOCUMENTATION_SOLDIERS = "3";//士兵证
	
	public final static String G_PERSONAL_DOCUMENTATION_HK_MACAO_TRAFFIC_PERMIT = "4";//港澳居民来往内地通行证
	
	public final static String G_PERSONAL_DOCUMENTATION_HOUSEHOLD_REGISTER = "5";//户口本
	
	public final static String G_PERSONAL_DOCUMENTATION_FOREIGN_PASSPORT = "6";//外国护照
	
	public final static String G_PERSONAL_DOCUMENTATION_OTHER = "7";//其他
	
	public final static String G_PERSONAL_DOCUMENTATION_CIVILIAN = "8";//文职
	
	public final static String G_PERSONAL_DOCUMENTATION_POLICE = "9";//警官
	
	public final static String G_PERSONAL_DOCUMENTATION_TAIWAN = "A";//台胞证
	
	/**
	 * 委托方式-1013
	 */
	public final static String G_ENTRUST_WAY_COUNTER = "0";//柜台委托
	
	public final static String G_ENTRUST_WAY_PHONE = "1";//电话委托
	
	public final static String G_ENTRUST_WAY_ONLINE = "2";//网上委托
	
	public final static String G_ENTRUST_WAY_SELF_HELP = "3";//自助委托
	
	public final static String G_ENTRUST_WAY_FAX = "4";//传真委托
	
	public final static String G_ENTRUST_WAY_OTHER = "5";//其他委托
	
	public final static String G_ENTRUST_WAY_CRM = "8";//CRM委托
	
	/**
	 * 客户编号状态-1029
	 */
	public final static String G_CUST_NO_STATUS_NORMAL = "0";//正常
	
	public final static String G_CUST_NO_STATUS_LOCK = "1";//锁定
	
	public final static String G_CUST_NO_STATUS_DISABLE = "9";//停用
	
	/**
	 * 清算状态
	 */
	public static final String G_STATE_CLEAN_WAIT = "0"; //待清算
	
	public static final String G_STATE_CLEAN_DOING = "1"; //清算中
	
	public static final String G_STATE_CLEAN_SUCCESS = "2"; //清算成功
	
	public static final String G_STATE_CLEAN_FAIL = "3"; //清算失败
	
	/**
	 * 业务复核标志-1073
	 */
	
	public final static String G_BUSINESS_REVIEW_NOT_CHECK = "0";//未复核
	
	public final static String G_BUSINESS_REVIEW_PASS = "1";//复核通过
	
	public final static String G_BUSINESS_REVIEW_REJECTED_MODIFICATION = "2";//驳回修改
	
	public final static String G_BUSINESS_REVIEW_REJECTED_GIVE_UP = "3";//驳回放弃
	
	public final static String G_BUSINESS_REVIEW_REJECTED_REQUEST = "4";//驳回要求
	
	public final static String G_BUSINESS_REVIEW_GIVE_UP = "5";//驳回修改时放弃
	
	/**
	 * 电子合同核对状态-1220
	 */
	public final static String G_ELECTRON_CONTRACT_NOT_CHECK = "0";//待核对
	
	public final static String G_ELECTRON_CONTRACT_PASS = "1";//核对通过
	
	public final static String G_ELECTRON_CONTRACT_NEED_CORRECT = "2";//核对需补正
	
	public final static String G_ELECTRON_CONTRACT_SPONSOR_CORRECT = "A";//已发起补正
	
	/**
	 * 合同类型-1221
	 */
	public final static String G_CONTRACT_TYPE_ELECTRON = "1";//电子
	
	public final static String G_CONTRACT_TYPE_PAPER = "2";//纸质
	
	/**
	 * 客户基金账户和交易账户关系 账户状态(0:未确认 1:已确认)
	 */
	public final static String G_CORE_RELATION_STATE_UNCONFIRMED = "0";//未确认
	
	public final static String G_CORE_RELATION_STATE_CONFIRM = "1";//确认
	
	/**
	 * 合同签署途径-1222
	 */
	public final static String G_SIGN_CONTRACT_COUNTER = "0";//柜台
	
	public final static String G_SIGN_CONTRACT_PHONE = "1";//电话
	
	public final static String G_SIGN_CONTRACT_NTERNET = "2";//互联网
	
	public final static String G_SIGN_CONTRACT_OTHER = "3";//其他
	
	/**
	 * 帐户状态类别
	 */
	public final static String G_ACCT_STOP_CFG_TYPE_TRANS = "0";//交易账户
	
	public final static String G_ACCT_STOP_CFG_TYPE_FUND = "1";//基金账户
	
	/**
	 * 交易累积限制交易类别
	 */
	public final static String G_CUMU_FUND_LIMIT_TRANS_TYPE_MONEY = "0";//金额申请
	
	public final static String G_CUMU_FUND_LIMIT_TRANS_TYPE_SHARE = "1";//份额申请
	
	/**
	 * 强制类型标志
	 */
	public final static String G_FUND_TRANSITION_MANDATORY_REDEMPTION_1 = "1";//基金转换强制类型标志 1-强制转换，不验证客户风险等级匹配
	
	/**
	 * 电子合同是否有效
	 */
	public final static String G_ELEC_PROTOCOL_INVALID = "0"; //电子合同无效
	
	public final static String G_ELEC_PROTOCOL_VALID = "1"; //电子合同有效
	
	/**
	 * 合同类型
	 */
	public final static String G_PROTOCOLTYPE_ELEC = "0";//合同类型  电子合同
	
	public final static String G_PROTOCOLTYPE_RISK = "1";//合同类型  风险揭示书
	
	/**
	 * 交易流水表交易标志
	 */
	public final static String G_CUST_TRANS_LOG_TRADE_FLAG_0 = "0";//0:普通网点交易
	
	public final static String G_CUST_TRANS_LOG_TRADE_FLAG_1 = "1";//1:花生宝模式网点交易
	
	/**
	 * 网点货基配置信息缺省标志
	 */
	public final static String G_BRANCH_FUNDINFO_FLAG_0 = "0";//0:非缺省货币基金
	
	public final static String G_BRANCH_FUNDINFO_FLAG_1 = "1";//1:网点缺省货币基金
	
	/**
	 * 投资周期
	 */
	public final static String G_INVEST_CYCLE_0 = "0";//0:一个月
	
	public final static String G_INVEST_CYCLE_7 = "7";//7:单周
	
	public final static String G_INVEST_CYCLE_14 = "14";//14:双周
	
	public final static String G_INVEST_CYCLE_31 = "31";//31:预约，单次任务
	
	/**
	 * 金额类型
	 */
	public final static String G_AMOUNTTYPE_0 = "0";//0:固定金额
	
	public final static String G_AMOUNTTYPE_1 = "1";//1:固定留存金额
	
	public final static String G_AMOUNTTYPE_2 = "2";//2:按余额比例
	
	public final static String G_AMOUNTTYPE_3 = "3";//3:按留存金额比例
	
	/**
	 * 节假日是否顺延扣款
	 */
	public final static String G_HOLIDAYCUTPAYMENT_0 = "0";//0:顺延
	
	public final static String G_HOLIDAYCUTPAYMENT_1 = "1";//1:取消
	
	public final static String G_HOLIDAYCUTPAYMENT_2 = "2";//2:提前
	
	/**
	 * 定投类型
	 */
	public final static String G_RATIONCODE_0 = "0";//0:智能申购
	
	public final static String G_RATIONCODE_1 = "1";//1:智能赎回
	
	public final static String G_RATIONCODE_9 = "9";//9:普通定投
	
	public final static String G_RATIONCODE_10 = "10";//10:定期取现
	
	public final static String G_RATIONCODE_11 = "11";//11:定期充值
	
	public final static String G_RATIONCODE_12 = "12";//12:定期还信用卡
	
	/**
	 * 定投标志
	 */
	public final static String G_TIMING_INVESTMENT_0 = "0";//0:未更新
	
	public final static String G_TIMING_INVESTMENT_1 = "1";//1:已更新
	
	/**
	 * 定时定额开通申请协议状态
	 */
	public final static String G_SAVE_PLAN_STATUS_0 = "0";//0:失效
	
	public final static String G_SAVE_PLAN_STATUS_1 = "1";//1:有效
	
	public final static String G_SAVE_PLAN_STATUS_2 = "2";//2:暂停
	
	/**
	 * 柜台交易停止标志
	 */
	public final static String G_COUNTER_TRANS_SWITCH_0 = "0";//0:关
	
	public final static String G_COUNTER_TRANS_SWITCH_1 = "1";//1:开
	
	/**
	 * 信用卡自动还款提醒开通情况
	 */
	public final static String G_CREDIT_REMIND_STATE_0 = "0";//0:开通
	
	public final static String G_CREDIT_REMIND_STATE_1 = "1";//1:关闭
	
	/**
	 * 业务权限开通状态
	 */
	public final static String G_BUSINESS_RIGHT_STATE_0 = "0";//0:关闭
	
	public final static String G_BUSINESS_RIGHT_STATE_1 = "1";//1:开通
	
	/**
	 * TA发起标识
	 */
	public final static String G_FROMTAFLAG_0 = "0";//0-由销售人发起
	
	public final static String G_FROMTAFLAG_1 = "1";//1-由注册登记人TA发起
	
	/**
	 * 专户邀请码状态
	 */
	public final static String G_INVITE_CODE_DESTROY = "0";//0:销毁
	
	public final static String G_INVITE_CODE_NORMAL = "1";//1:正常
	
	/**
	 * 柜台开户授权客户姓名和银行卡用户名可以不一致
	 */
	public final static String G_IS_AUTHORIZATION_1 = "1";//柜台开户授权客户姓名和银行卡用户名可以不一致
	
	/**
	 * 交易产品限额限制开关
	 */
	public final static String G_TRANS_LIMIT_SWITCH_OFF = "0";//0:关闭，不校验产品限制
	
	public final static String G_TRANS_LIMIT_SWITCH_ON = "1";//1:开启，校验产品限制
	
	/**
	 * 风险等级匹配标志
	 */
	public final static String G_RISK_NOT_MATCH = "0";//0 不匹配
	
	public final static String G_RISK_MATCH = "1";//1 匹配
	
	/**
	 * 业务类型编码
	 */
	public static final String G_DB_BUSINESS_TYPE_0 = "0"; //快速取现
	
	public static final String G_DB_BUSINESS_TYPE_1 = "1"; //标准取现
	
	public static final String G_DB_BUSINESS_TYPE_2 = "2"; //充值
	
	public static final String G_DB_BUSINESS_TYPE_3 = "3"; //基金赎回到花生宝
	
	public static final String G_DB_BUSINESS_TYPE_4 = "4"; //花生宝申购基金
	
	public static final String G_DB_BUSINESS_TYPE_5 = "5"; //花生宝认购基金
	
	public static final String G_DB_BUSINESS_TYPE_6 = "6"; //柜台认购基金
	
	public static final String G_DB_BUSINESS_TYPE_7 = "7"; //柜台申购基金
	
	public static final String G_DB_BUSINESS_TYPE_8 = "8"; //设置分红方式
	
	public static final String G_DB_BUSINESS_TYPE_9 = "9"; //基金转换
	
	public static final String G_DB_BUSINESS_TYPE_10 = "10";//基金交易撤单
	
	public static final String G_DB_BUSINESS_TYPE_11 = "11";//一步转托管
	
	public static final String G_DB_BUSINESS_TYPE_12 = "12";//托管转出
	
	public static final String G_DB_BUSINESS_TYPE_13 = "13";//托管转入
	
	public static final String G_DB_BUSINESS_TYPE_15 = "15";//基金定投(与信用卡还款 业务代码冲突)
	
	public static final String G_DB_BUSINESS_TYPE_16 = "16";//基金预约申购
	
	public static final String G_DB_BUSINESS_TYPE_17 = "17";//基金预约赎回
	
	public static final String G_DB_BUSINESS_TYPE_18 = "18";//花生宝定期充值
	
	public static final String G_DB_BUSINESS_TYPE_19 = "19";//花生宝定期取现
	
	public static final String G_DB_BUSINESS_TYPE_20 = "20";//柜台赎回
	
	public static final String G_DB_BUSINESS_TYPE_21 = "21";//综合中心强制赎回
	
	public static final String G_DB_BUSINESS_TYPE_22 = "22";//非交易过户
	
	public static final String G_DB_BUSINESS_TYPE_23 = "23";//份额冻结(异常冻结)
	
	public static final String G_DB_BUSINESS_TYPE_24 = "24";//份额解冻(异常冻结解冻)
	
	public static final String G_DB_BUSINESS_TYPE_25 = "25";//柜台资金存入
	
	public static final String G_DB_BUSINESS_TYPE_26 = "26";//柜台资金支出
	
	public static final String G_DB_BUSINESS_TYPE_27 = "27";//柜台资金调整增加
	
	public static final String G_DB_BUSINESS_TYPE_28 = "28";//柜台资金调整减少
	
	public static final String G_DB_BUSINESS_TYPE_29 = "29";//网上开户
	
	public static final String G_DB_BUSINESS_TYPE_30 = "30";//交易账户资料修改
	
	public static final String G_DB_BUSINESS_TYPE_31 = "31";//柜台开户
	
	public static final String G_DB_BUSINESS_TYPE_32 = "32";//增开TA(柜台)
	
	public static final String G_DB_BUSINESS_TYPE_33 = "33";//交易账号销户(柜台)
	
	public static final String G_DB_BUSINESS_TYPE_34 = "34";//基金账号销户(柜台)
	
	public static final String G_DB_BUSINESS_TYPE_35 = "35";//客户资料修改(柜台)
	
	public static final String G_DB_BUSINESS_TYPE_36 = "36";//交易账号资料修改(柜台)
	
	public static final String G_DB_BUSINESS_TYPE_37 = "37";//交易账号冻结(柜台)
	
	public static final String G_DB_BUSINESS_TYPE_38 = "38";//交易账号解冻(柜台)
	
	public static final String G_DB_BUSINESS_TYPE_39 = "39";//基金账号冻结(柜台)
	
	public static final String G_DB_BUSINESS_TYPE_40 = "40";//基金账号解冻(柜台)
	
	public static final String G_DB_BUSINESS_TYPE_41 = "41";//问卷复核(柜台)
	
	public static final String G_DB_BUSINESS_TYPE_42 = "42";//银行卡资料修改(柜台)
	
	public static final String G_DB_BUSINESS_TYPE_50 = "50";//专户产品预约购买
	
	public static final String G_DB_BUSINESS_TYPE_51 = "51";//专户产品认购
	
	public static final String G_DB_BUSINESS_TYPE_52 = "52";//专户产品撤单
	
	public static final String G_DB_BUSINESS_TYPE_53 = "53";//专户产品赎回
	
	public static final String G_DB_BUSINESS_TYPE_54 = "54";//银行卡删除
	
	public static final String G_DB_BUSINESS_TYPE_55 = "55";//银行卡加挂
	
	public static final String G_DB_BUSINESS_TYPE_56 = "56";//银行卡修改
	
	public static final String G_DB_BUSINESS_TYPE_57 = "57";//银行卡换卡
	
	public static final String G_DB_BUSINESS_TYPE_58 = "58";//银行卡申购基金
	
	public static final String G_DB_BUSINESS_TYPE_59 = "59";//银行卡认购基金
	
	public static final String G_DB_BUSINESS_TYPE_Z1 = "Z1";//银行卡直接认购基金
	
	public static final String G_DB_BUSINESS_TYPE_Z2 = "Z2";//银行卡直接申购基金    
	
	public static final String G_DB_BUSINESS_TYPE_A0 = "A0";//定投申购基金
	
	public static final String G_DB_BUSINESS_TYPE_A1 = "A1";//定时充值
	
	public static final String G_DB_BUSINESS_TYPE_A2 = "A2";//定时取现
	
	public static final String G_DB_BUSINESS_TYPE_A3 = "A3";//定时还信用卡
	
	public static final String G_DB_BUSINESS_TYPE_A4 = "A4";//预约申购
	
	public static final String G_DB_BUSINESS_TYPE_A5 = "A5";//预约认购
	
	public static final String G_DB_BUSINESS_TYPE_A6 = "A6";//预约赎回
	
	public static final String G_DB_BUSINESS_TYPE_90 = "90";//购买养老险
	
	public static final String G_DB_BUSINESS_TYPE_91 = "91"; //支付
	
	public static final String G_DB_BUSINESS_TYPE_92 = "92";//信用卡还款
	
	public static final String G_DB_BUSINESS_TYPE_93 = "93";//广州移动充值
	
	public static final String G_DB_BUSINESS_TYPE_94 = "94";//广州移动定时充值
	
	public static final String G_DB_BUSINESS_TYPE_95 = "95";//信用卡预约还款
	
	public static final String G_DB_BUSINESS_TYPE_96 = "96";//信用卡定时还款
	
	public static final String G_DB_BUSINESS_TYPE_97 = "97";//转账（跨卡取现）
	
	/*public static final String G_DB_BUSINESS_TYPE_60 = "60"; //基金认购失败返款（资金在途日志）
	public static final String G_DB_BUSINESS_TYPE_61 = "61"; //基金认购部分确认返款（资金在途日志）
	public static final String G_DB_BUSINESS_TYPE_62 = "62"; //基金募集失败返款（资金在途日志）
	public static final String G_DB_BUSINESS_TYPE_63 = "63"; //基金终止返款（资金在途日志）
	public static final String G_DB_BUSINESS_TYPE_64 = "64"; //基金清盘返款（资金在途日志）
	public static final String G_DB_BUSINESS_TYPE_65 = "65"; //基金申购失败返款（资金在途日志）
	public static final String G_DB_BUSINESS_TYPE_66 = "66"; //基金申购部分确认返款（资金在途日志）
	public static final String G_DB_BUSINESS_TYPE_67 = "67"; //基金募集成功部分确认返款（资金在途日志）
	public static final String G_DB_BUSINESS_TYPE_68 = "68"; //现金分红
	public static final String G_DB_BUSINESS_TYPE_69 = "69"; //TA强制赎回
	public static final String G_DB_BUSINESS_TYPE_70 = "70"; //份额冲正
	*/
	//系统主动发起业务
	public static final String G_DB_BUSINESS_TYPE_60 = "60"; //柜台基金认购失败返款
	
	public static final String G_DB_BUSINESS_TYPE_61 = "61"; //柜台基金认购部分确认返款
	
	public static final String G_DB_BUSINESS_TYPE_62 = "62"; //柜台基金募集失败返款
	
	public static final String G_DB_BUSINESS_TYPE_63 = "63"; //柜台基金终止返款
	
	public static final String G_DB_BUSINESS_TYPE_64 = "64"; //柜台基金清盘返款
	
	public static final String G_DB_BUSINESS_TYPE_65 = "65"; //柜台基金申购失败返款
	
	public static final String G_DB_BUSINESS_TYPE_66 = "66"; //柜台基金申购部分确认返款
	
	public static final String G_DB_BUSINESS_TYPE_67 = "67"; //柜台基金募集成功部分确认返款
	
	public static final String G_DB_BUSINESS_TYPE_68 = "68"; //柜台现金分红
	
	public static final String G_DB_BUSINESS_TYPE_69 = "69"; //TA强制赎回(柜台)
	
	public static final String G_DB_BUSINESS_TYPE_70 = "70"; //网上交易基金认购失败返款
	
	public static final String G_DB_BUSINESS_TYPE_71 = "71"; //网上交易基金认购部分确认返款
	
	public static final String G_DB_BUSINESS_TYPE_72 = "72"; //网上交易基金募集失败返款
	
	public static final String G_DB_BUSINESS_TYPE_73 = "73"; //网上交易基金终止返款
	
	public static final String G_DB_BUSINESS_TYPE_74 = "74"; //网上交易基金清盘返款
	
	public static final String G_DB_BUSINESS_TYPE_75 = "75"; //网上交易基金申购失败返款
	
	public static final String G_DB_BUSINESS_TYPE_76 = "76"; //网上交易基金申购部分确认返款
	
	public static final String G_DB_BUSINESS_TYPE_77 = "77"; //网上交易基金募集成功部分确认返款
	
	public static final String G_DB_BUSINESS_TYPE_78 = "78"; //网上交易现金分红
	
	public static final String G_DB_BUSINESS_TYPE_79 = "79"; //TA强制赎回(网上交易)
	
	public static final String G_DB_BUSINESS_TYPE_80 = "80"; //网上交易现金分红充值
	
	public static final String G_DB_BUSINESS_TYPE_81 = "81"; //基金赎回花生宝充值
	
	public static final String G_DB_BUSINESS_TYPE_82 = "82"; //认购部分确认充值
	
	public static final String G_DB_BUSINESS_TYPE_83 = "83"; //申购部分确认充值
	
	public static final String G_DB_BUSINESS_TYPE_84 = "84"; //T+0划款失败冲正份额
	
	/**
	 * 订单队列处理状态
	 */
	public static final String G_EXE_STATE_WAIT = "0"; //待处理
	
	public static final String G_EXE_STATE_DOING = "1"; //正在处理
	
	public static final String G_EXE_STATE_SUCCESS = "2"; //处理成功
	
	//public static final String G_EXE_STATE_CANCEL = "3";//撤单
	//public static final String G_EXE_STATE_WAIT_ISDO = "5";//等待确认是否执行
	
	//订单交易状态
	public static final String G_TRANS_STATE_WAIT = "0"; //待确认
	
	public static final String G_TRANS_STATE_DOING = "1"; //正在处理
	
	public static final String G_TRANS_STATE_SUCCESS = "2"; //处理成功
	
	public static final String G_TRANS_STATE_FAIL = "3"; //处理失败
	
	public static final String G_TRANS_STATE_CANCEL = "5"; //撤单成功
	
	/**
	 * 账户及交易流水状态
	 */
	public final static String G_CUST_LOG_APPLYST_SUCCESS = "0";//申请成功
	
	public final static String G_CUST_LOG_APPLYST_AFFIRM = "1";//确认中
	
	public final static String G_CUST_LOG_APPLYST_AFFIRM_SUCCESS = "2";//确认成功
	
	public final static String G_CUST_LOG_APPLYST_AFFIRM_FAIL = "3";//确认失败
	
	public final static String G_CUST_LOG_APPLYST_FAIL = "4";//申请失败
	
	public final static String G_CUST_LOG_APPLYST_CANCELLATIONS = "5";//已撤单
	
	//基金交易流水表处理状态
	/*public final static String C_FUND_APPLYST_0 = "0";
	public final static String C_FUND_APPLYST_1 = "1";//申请中
	public final static String C_FUND_APPLYST_2 = "2";//已经撤单
	public final static String C_FUND_APPLYST_3 = "3";//确认成功
	public final static String C_FUND_APPLY	ST_4 = "4";//确认失败
	*/
	/**
	 * 银行划付状态
	 * */
	public static final String G_PAY_TRADE_STATE0 = "0"; //0:款未划出 
	
	public static final String G_PAY_TRADE_STATE1 = "1"; //1:确认划款
	
	public static final String G_PAY_TRADE_STATE2 = "2"; //2:正在划款
	
	public static final String G_PAY_TRADE_STATE3 = "3"; //3:划款成功
	
	public static final String G_PAY_TRADE_STATE4 = "4"; //4:划款失败
	
	/**
	 * 调用银行接口返回状态
	 * */
	public static final String G_BANK_RESULT_STATE0 = "0"; //其他状态
	
	public static final String G_BANK_RESULT_STATE1 = "1"; //成功或受理成功
	
	public static final String G_BANK_RESULT_STATE2 = "2"; //失败或受理失败
	
	/**
	 * 资产类型
	 */
	public static final String G_ASSETS_TYPE_0 = "0"; //T日现金
	
	public static final String G_ASSETS_TYPE_1 = "1"; //份额
	
	public static final String G_ASSETS_TYPE_2 = "2"; //在途资金
	
	public static final String G_ASSETS_TYPE_3 = "3"; //未付收益
	
	public static final String G_ASSETS_TYPE_4 = "4"; //T+1日现金
	
	/**
	 * 冻结状态
	 */
	public static final String G_ASSETS_FREEZE_STATE_FREEZEING = "0"; //冻结中
	
	public static final String G_ASSETS_FREEZE_STATE_FREEZE = "1"; //已冻结
	
	public static final String G_ASSETS_FREEZE_STATE_SUCCESS = "2"; //业务完成
	
	public static final String G_ASSETS_FREEZE_STATE_UNFREEZEING = "3"; //解冻中
	
	public static final String G_ASSETS_FREEZE_STATE_UNFREEZE = "4"; //已解冻
	
	/**
	 * 收费类型
	 */
	public static final String G_CHARGE_TYPE_0 = "0"; //折扣率方式
	
	public static final String G_CHARGE_TYPE_1 = "1"; //指定费率方式
	
	public static final String G_CHARGE_TYPE_2 = "2"; //指定费用
	
	/**
	 * 巨额赎回标志
	 */
	public static final String G_LARGE_FLAG_0 = "0"; //取消
	
	public static final String G_LARGE_FLAG_1 = "1"; //顺延
	
	/**
	 * 收费方式
	 */
	public static final String G_CHARGETYPE_0 = "0"; //折扣率方式
	
	public static final String G_CHARGETYPE_1 = "1"; //指定费率
	
	public static final String G_CHARGETYPE_2 = "2"; //指定费用
	
	/**
	 * 自带支付体系
	 */
	public static final String G_IS_SELF_UPFRONT0 = "0"; //无
	
	public static final String G_IS_SELF_UPFRONT1 = "1"; //有
	
	/**
	 * 银行业务交易流水类型
	 */
	public static final String G_BANK_TRADE_TYPE1 = "0"; //快速取现
	
	public static final String G_BANK_TRADE_TYPE2 = "1"; //普通取现
	
	public static final String G_BANK_TRADE_TYPE0 = "2"; //充值
	
	public static final String G_BANK_TRADE_TYPE3 = "68"; //现金分红
	
	/**
	 * 银行交易流水资金方向
	 */
	public static final String G_BANK_DIRECT0 = "0"; //转入
	
	public static final String G_BANK_DIRECT1 = "1"; //转出
	
	/**
	 * 银行交易支付模式
	 */
	public static final String G_BANK_PAY_MODE0 = "0"; //普通
	
	public static final String G_BANK_PAY_MODE1 = "1"; //网银
	
	public static final String G_BANK_PAY_MODE2 = "2"; //快捷
	
	public static final String G_BANK_PAY_MODE3 = "3"; //超级网银
	
	/**
	 * 银行交易对账状态
	 */
	public static final String G_BANK_CHECK_FLAG0 = "0"; //未对账
	
	public static final String G_BANK_CHECK_FLAG1 = "1"; //账已对平
	
	public static final String G_BANK_CHECK_FLAG2 = "2"; //账未对平
	
	/**
	 * 银行交易调账状态
	 */
	public static final String G_BANK_ADJUST_FLAG0 = "0"; //未调账
	
	public static final String G_BANK_ADJUST_FLAG1 = "1"; //已调账
	
	/**
	 * 资金在途类型
	 */
	public static final String G_CAPITAL_PROCESS0 = "0"; //花生宝资产变动
	
	public static final String G_CAPITAL_PROCESS1 = "1"; //基金份额变动
	
	/**
	 * 使用资产优先级
	 */
	public static final String G_DEDUCTION_PRIORITY0 = "0"; //先进先出
	
	public static final String G_DEDUCTION_PRIORITY1 = "1"; //后进先出
	
	/**
	 * 在途资金状态
	 */
	public static final String G_CAPITAL_STATE0 = "0"; //在途
	
	public static final String G_CAPITAL_STATE1 = "1"; //TA确定
	
	public static final String G_CAPITAL_STATE2 = "2"; //业务结束
	
	/**
	 * 订单执行类型
	 */
	public static final String G_ORDER_MODE_SYNCHRONOUS = "0"; //同步
	
	public static final String G_ORDER_MODE_ASYNCHRONOUS = "1"; //异步
	
	/**
	 * 专户产品是否需要邀请码
	 */
	public static final String G_IS_NEED_INVITE0 = "0"; //不需要
	
	public static final String G_IS_NEED_INVITE1 = "1"; //需要
	
	/**
	 * 机构银行账户类型
	 */
	public static final String G_ACCT_USE0 = "0"; //直销清算户
	
	public static final String G_ACCT_USE1 = "1"; //TA清算账户
	
	/**
	 * 需要验证交易密码
	 */
	public static final String G_DB_CHECKPWD = "0";
	
	/**
	 * 更新用户银行卡星级的线程数量
	 */
	public static final int G_USERSAFELEVELUPDATE_THREADNUM = 1;
	
	/**
	 * 安全中心银行卡默认星级
	 */
	public static final String G_DB_SAFECENTER_CARDDEFAULTLEVEL = "1";
	
	/**
	 * 功能是否开通
	 */
	public static final String G_CLOSE_FUNCTION = "0"; //功能关闭
	
	public static final String G_OPEN_FUNCTION = "1"; //功能开通
	
	/**
	 * 邀请码是否生成
	 */
	public static final String G_IS_GENERATE_0 = "0"; //未生成邀请码    
	
	public static final String G_IS_GENERATE_1 = "1"; //已生成邀请码
	
	/**
	 * 基金销售状态
	 */
	public final static String G_DISSTATUS_CANNOT_SELL = "0";//不可销售
	
	public final static String G_DISSTATUS_CAN_SELL = "1";//可销售
	
	/** 银行编号*/
	public static final String G_BANK_CARD_NO_ICBC = "002"; //中国工商银行
	
	public static final String G_BANK_CARD_NO_ABC = "003"; //中国农业银行
	
	public static final String G_BANK_CARD_NO_BOC = "004"; //中国银行
	
	public static final String G_BANK_CARD_NO_CCB = "005"; //中国建设银行
	
	public static final String G_BANK_CARD_NO_BOCOM = "006"; //中国交通银行
	
	public static final String G_BANK_CARD_NO_CMB = "007"; //中国招商银行
	
	public static final String G_BANK_CARD_NO_SPDB = "011"; //上海浦东发展银行
	
	public static final String G_BANK_CARD_NO_CMBC = "014"; //民生银行
	
	public static final String G_BANK_CARD_NO_PINGAN = "920"; //平安银行
	
	public static final String G_BANK_CARD_NO_CHINAPAY = "999"; //银联
	
	public static final String G_BANK_CARD_NO_AAC = "AAC"; //汇付天下
	
	/**交易账号类型*/
	public static final String G_REAL_ACCT = "0"; //实时TA交易账号
	
	/**机构户类型*/
	public static final String G_ACCT_FUND_COMPANY = "000"; //基金公司交易账号
	
	/**恒生实时TA普通赎回扩展业务编码*/
	public static final String G_BUSINESS_EXP_STOG = "01"; //赎回转购
	
	/**综合操作中心操作其它网点权限*/
	public static final String G_SYNTH_CENTER_OP_LIMIT_ALL = "0"; //可操作所有网点
	
	
	public static final Map<String, String> BusinessType = new HashMap<String, String>();
	
	static
	{
		BusinessType.put(G_DB_BUSINESS_TYPE_0, "快速取现");
		BusinessType.put(G_DB_BUSINESS_TYPE_1, "普通取现"); //标准取现
		BusinessType.put(G_DB_BUSINESS_TYPE_2, "充值"); //充值
		BusinessType.put(G_DB_BUSINESS_TYPE_3, "基金赎回到花生宝"); //基金赎回到花生宝
		BusinessType.put(G_DB_BUSINESS_TYPE_4, "花生宝申购基金"); //花生宝申购基金
		BusinessType.put(G_DB_BUSINESS_TYPE_5, "花生宝认购基金"); //花生宝认购基金
		BusinessType.put(G_DB_BUSINESS_TYPE_6, "柜台认购基金"); //柜台认购基金
		BusinessType.put(G_DB_BUSINESS_TYPE_7, "柜台申购基金"); //柜台申购基金
		BusinessType.put(G_DB_BUSINESS_TYPE_8, "设置分红方式"); //设置分红方式
		BusinessType.put(G_DB_BUSINESS_TYPE_9, "基金转换"); //基金转换
		BusinessType.put(G_DB_BUSINESS_TYPE_10, "基金交易撤单");//基金交易撤单
		BusinessType.put(G_DB_BUSINESS_TYPE_11, "一步转托管");//一步转托管
		BusinessType.put(G_DB_BUSINESS_TYPE_12, "托管转出");//托管转出
		BusinessType.put(G_DB_BUSINESS_TYPE_13, "托管转入");//托管转入
		BusinessType.put(G_DB_BUSINESS_TYPE_15, "基金定投(与信用卡还款 业务代码冲突)");//基金定投(与信用卡还款 业务代码冲突)
		BusinessType.put(G_DB_BUSINESS_TYPE_16, "基金预约申购");//基金预约申购
		BusinessType.put(G_DB_BUSINESS_TYPE_17, "基金预约赎回");//基金预约赎回
		BusinessType.put(G_DB_BUSINESS_TYPE_18, "花生宝定期充值");//花生宝定期充值
		BusinessType.put(G_DB_BUSINESS_TYPE_19, "花生宝定期取现");//花生宝定期取现
		BusinessType.put(G_DB_BUSINESS_TYPE_20, "柜台赎回");//柜台赎回
		BusinessType.put(G_DB_BUSINESS_TYPE_21, "综合中心强制赎回");//综合中心强制赎回
		BusinessType.put(G_DB_BUSINESS_TYPE_22, "非交易过户");//非交易过户
		BusinessType.put(G_DB_BUSINESS_TYPE_23, "份额冻结(异常冻结)");//份额冻结(异常冻结)
		BusinessType.put(G_DB_BUSINESS_TYPE_24, "份额解冻(异常冻结解冻)");//份额解冻(异常冻结解冻)
		BusinessType.put(G_DB_BUSINESS_TYPE_25, "柜台资金存入");//柜台资金存入
		BusinessType.put(G_DB_BUSINESS_TYPE_26, "柜台资金支出");//柜台资金支出
		BusinessType.put(G_DB_BUSINESS_TYPE_27, "柜台资金调整增加");//柜台资金调整增加
		BusinessType.put(G_DB_BUSINESS_TYPE_28, "柜台资金调整减少");//柜台资金调整减少
		BusinessType.put(G_DB_BUSINESS_TYPE_29, "网上开户");//网上开户
		BusinessType.put(G_DB_BUSINESS_TYPE_30, "交易账户资料修改");//交易账户资料修改
		BusinessType.put(G_DB_BUSINESS_TYPE_50, "专户产品预约购买");//专户产品预约购买
		BusinessType.put(G_DB_BUSINESS_TYPE_51, "专户产品认购");//专户产品认购
		BusinessType.put(G_DB_BUSINESS_TYPE_52, "专户产品撤单");//专户产品撤单
		BusinessType.put(G_DB_BUSINESS_TYPE_53, "专户产品赎回");//专户产品赎回
		BusinessType.put(G_DB_BUSINESS_TYPE_54, "银行卡删除");//银行卡删除
		BusinessType.put(G_DB_BUSINESS_TYPE_55, "银行卡加挂");//银行卡加挂
		BusinessType.put(G_DB_BUSINESS_TYPE_56, "银行卡修改");//银行卡修改
		BusinessType.put(G_DB_BUSINESS_TYPE_57, "银行卡换卡");//银行卡换卡
		BusinessType.put(G_DB_BUSINESS_TYPE_58, "银行卡申购基金");//银行卡申购基金
		BusinessType.put(G_DB_BUSINESS_TYPE_59, "银行卡认购基金");//银行卡认购基金
		BusinessType.put(G_DB_BUSINESS_TYPE_Z1, "银行卡直接认购基金");//银行卡直接认购基金
		BusinessType.put(G_DB_BUSINESS_TYPE_Z2, "银行卡直接申购基金    ");//银行卡直接申购基金    
		BusinessType.put(G_DB_BUSINESS_TYPE_A0, "定投申购基金");//定投申购基金
		BusinessType.put(G_DB_BUSINESS_TYPE_A1, "定时充值");//定时充值
		BusinessType.put(G_DB_BUSINESS_TYPE_A2, "定时取现");//定时取现
		BusinessType.put(G_DB_BUSINESS_TYPE_A3, "定时还信用卡");//定时还信用卡
		BusinessType.put(G_DB_BUSINESS_TYPE_A4, "预约申购");//预约申购
		BusinessType.put(G_DB_BUSINESS_TYPE_A5, "预约认购");//预约认购
		BusinessType.put(G_DB_BUSINESS_TYPE_A6, "预约赎回");//预约赎回
		BusinessType.put(G_DB_BUSINESS_TYPE_90, "购买养老险");//购买养老险
		BusinessType.put(G_DB_BUSINESS_TYPE_91, "支付"); //支付
		BusinessType.put(G_DB_BUSINESS_TYPE_92, "信用卡还款");//信用卡还款
		BusinessType.put(G_DB_BUSINESS_TYPE_93, "广州移动充值");//广州移动充值
		BusinessType.put(G_DB_BUSINESS_TYPE_94, "广州移动定时充值");//广州移动定时充值
		BusinessType.put(G_DB_BUSINESS_TYPE_95, "信用卡预约还款");//信用卡预约还款
		BusinessType.put(G_DB_BUSINESS_TYPE_96, "信用卡定时还款");//信用卡定时还款
		BusinessType.put(G_DB_BUSINESS_TYPE_97, "转账（跨卡取现）");//转账（跨卡取现）
	}
	
	public static String getBusinessTypeName(String businessType)
	{
		return BusinessType.get(businessType);
	}
}