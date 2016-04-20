package com.jhcz.trade.account.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


public class WebConstants
{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	public static final String LOGIN_USER = "loginUser";
	
	/**
	 * 登陆类型
	 */
	public static final String LOGIN_TYPE = "login_type";  //0网上交易登陆1一账通登陆
	/**
	 * 系统名常量
	 */
	public static final String SYSTEM_NAME = "PUF_OTS";
	
	/**
	 * cookie保存客户信息的ID
	 */
	public static final String COOKIE_CLIENT_INFO = "clientinfo";
	
	/**
	 * session保存的验证码信息
	 */
	public static final String SESSION_TICKET = "@session_ticket";
	
	/**
	 * session中保存用户编号
	 */
	public static final String SESSION_USER_ID = "@session_user_id";
	
	/**
	 * session中保存用户的类型
	 */
	public static final String SESSION_USER_TYPE = "@session_user_type";
	
	/**
	 * session中保存登录账号
	 */
	public static final String SESSION_LOGIN_ID = "@session_login_id";
	
	/**
	 * session中保存交易账号
	 */
	public static final String SESSION_TRADE_ACCOUNT = "@session_trade_account";
	/**
	 * session中保存交易密码
	 */
	public static final String SESSION_TRADE_PASSWORD = "@session_trade_password";
	
	/**
	 * session中保存是否交易客户
	 */
	public static final String SESSION_IS_TRADE = "@session_is_trade";
	
	/**
	 * session中保存资金账号 
	 */
	public static final String SESSION_FUND_ACCOUNT = "@session_fund_account";
	
	/**
	 * session中保存客户姓名
	 */
	public static final String SESSION_CLIENT_NAME = "@session_client_name";
	
	/**
	 * session中保存客户营业部
	 */
	public static final String SESSION_BRANCH_NO = "@session_branch_no";
	
	/**
	 * 操作渠道
	 */
	public static final String SESSION_SOURCE_NO = "@session_source_no";
	
	/**
	 * session中保存客户操作站点
	 */
	public static final String SESSION_OP_STATION = "@session_op_station";
	
	/**
	 * session中保存客户委托方式
	 */
	public static final String SESSION_ENTRUST_WAY = "@session_entrust_way";
	
	/**
	 * session中保存客户登录输入的账号
	 */
	public static final String SESSION_INPUT_CONTENT = "@session_input_content";
	
	/**
	 * session中保存接口返回的会话ID
	 */
	public static final String SESSION_SESSION_ID = "@session_session_id";
	
	/**
	 * session中保存客户手机号码
	 */
	public static final String SESSION_MOBILE = "@session_mobile";
	
	/**
	 * session中保存手机验证码的手机号
	 */
	public static final String SESSION_TICKET_MOBILE = "@session_ticket_mobile";
	
	/**
	 * session中保存短信验证码是否通过
	 */
	public static final String SESSION_TICKET_MOBILE_PASS = "@session_ticket_mobile_pass";
	
	/**
	 * session中保存开户证件号验证是否通过
	 */
	public static final String SESSION_TICKET_IDNUMBER = "@session_ticket_idnumber";
	
	/**
	 * session中保存开户证件号验证是否通过
	 */
	public static final String SESSION_TICKET_TRADEPD = "@session_ticket_tradepd";
	
	/**
	 * session验证码发送成功时返回令牌字符串
	 */
	public static final String SESSION_TICKET_TOKEN = "@session_ticket_token";
	
	/**
	 * session中保存手机验证码的类型
	 */
	public static final String SESSION_TICKET_MOBILE_TYPE = "@session_ticket_mobile_type";
	
	/**
	 * session中保存手机验证码获取时间
	 */
	public static final String SESSIOIN_MOBILE_TICKET_TIME = "@sessioin_mobile_ticket_time";
	
	/**
	 * session中保存重置密码的方式
	 */
	public static final String SESSION_RESET_WAY = "@session_reset_way";
	
	/**
	 * session中保存客户邮箱地址
	 */
	public static final String SESSION_EMAIL = "@session_email";
	
	/**
	 * session中保存客户上次登录时间
	 */
	public static final String SESSION_LAST_LOGIN_DATE = "@session_last_login_date";
	
	/**
	 * session中保存客户号
	 */
	public static final String SESSION_CUST_NO = "@session_cust_no";
	
	/**
	 * session中保存的客户类型
	 */
	public static final String SESSION_CUST_TYPE = "@session_cust_type";
	
	/**
	 * session中保存的证件类型
	 */
	public static final String SESSION_IDENTITY_TYPE = "@session_identity_type";
	
	/**
	 * session中保存的证件号码
	 */
	public static final String SESSION_IDENTITY_NUM = "@session_identity_num";
	
	/**
	 * session中保存的资料上传重置密码的单号
	 */
	public static final String SESSION_REQUEST_NO = "@session_request_no";
	
	/**
	 * session 中保存客户状态（0、正常，1、锁定，2、未激活查询客户，3、未激活交易客户，4、未激活代销客户 ,5.未激活一账通客户9、注销）
	 */
	public static final String SESSION_CUST_STATE = "@session_cust_state";
	
	/**
	 * session 中保存的TOKEN信息
	 */
	public static final String SESSION_TOKEN = "@session_token";
	
	/**
	 * session 中保存的手机验证码防重发TOKEN信息
	 */
	public static final String SESSION_MOBILE_TICKET_TOKEN = "@session_moible_ticket_token";
	
	/**
	 * session 中保存充值有关的信息
	 */
	public static final String SESSION_RECHARGE = "@session_recharge";
	
	/**
	 * session 中保存取现有关的信息
	 */
	public static final String SESSION_CASH = "@session_cash";
	
	/**
	 * session 中保存的表单提交的一些信息
	 */
	public static final String SESSION_FORM_DATA = "@session_form_data";
	
	/**
	 * session 中保存的页面跳转信息
	 */
	public static final String SESSION_RET_URL = "@session_ret_url";
	
	/**
	 * 换卡-旧卡卡号
	 */
	public static final String SESSION_CHANGE_CARD_OLD = "@session_change_card_old";
	
	/**
	 * 换卡-新卡卡号
	 */
	public static final String SESSION_CHANGE_CARD_NEW = "@session_change_card_new";
	
	/**
	 *  身份校验-临时证件号码
	 */
	public static final String SESSION_TMP_IDENTITY_NUM = "@session_tmp_identity_num";
	
	/**
	 *  身份校验-临时证件类型
	 */
	public static final String SESSION_TMP_IDENTITY_TYPE = "@session_tmp_identity_type";
	
	/**
	 *  身份校验-临时客户姓名
	 */
	public static final String SESSION_TMP_NAME = "@session_tmp_name";
	
	/**
	 * 开户时保存的信息
	 */
	public static final String SESSION_BIND_CARD_DATA = "@session_bind_card_data";
	
	/**
	 * 一账通加解挂partyno
	 */
	public static final String SESSION_TOA_PARTY_NO = "@session_toa_party_no";
	
	/**
	 * 一账通重定向URL
	 */
	public static final String SESSION_TOA_REDIRECT_URL = "@session_toa_redirect_url";
	
	/**
	 * 一账通请求类型
	 */
	public static final String SESSION_TOA_REQ_TYPE = "@session_toa_req_type";
	
		/**
		 * 一账通入参姓名
		 */
		public static final String SESSION_TOA_NAME	="@session_toa_name";
		/**
		 * 一账通入参证件类型
		 */
		public static final String SESSION_TOA_IDTYPE = "@session_toa_idtype";
		/**
		 * 一账通入参证件号码
		 */
		public static final String SESSION_TOA_IDNUM = "@session_toa_idnum";
		/**
		 * 一账通入参性别
		 */
		public static final String SESSION_TOA_SEX = "@session_toa_sex";
		/**
		 * 一账通partyno
		 */
		public static final String SESSION_TOA_PARTYNO ="@session_toa_partyno";
		/**
		 * 一账通客户号
		 */
		public static final String SESSION_TOA_CLIENTNO ="@session_toa_clientno";
		/**
		 * 一账通手机号码
		 */
		public static final String SESSION_TOA_MOBILE ="@session_toa_mobile";
		/**
		 * 一账通入参生日
		 */
		public static final String SESSION_TOA_BIRTHDAY ="@session_toa_birthday";
		/**
		 * 手机号是否已注册
		 */
		public static final String SESSION_TOA_TYPE ="@session_toa_type";
		/**
		 * 是否通过一账通验证
		 */
		public static final String SESSION_TOA_CHECK ="@session_toa_check";
		
		/**
		 * 数据中心客户号
		 */
		public static final String SESSION_DATACENTER_CUSTNO ="@session_datacenter_custno";
		/**
		 * 客户购买基金代码
		 */
		public static final String SESSION_DATA_FUND ="@session_data_fund";

	/**
	 * 网点是否开通信用卡还款功能
	 */
	public static final String SESSION_BRANCH_OPENCREDIT = "@session_branch_opencredit";	
	    
	    /**
	     * 客户是否有权限开通信用卡还款功能
	     */
	    public static final String SESSION_USER_POWER_OPEN = "@session_user_power_open";
	    
	    /**
	     * 客户在有权限开通的情况下，是否开通信用卡还款功能
	     */
	    public static final String SESSION_USER_IS_OPEN = "@session_user_is_open";
	
	/**
	 * 登录密码错误次数
	 */
	public static final String SESSION_LOGIN_PWD_ERROR_TIMES = "loginPwdErrorTimes";
	
	/**
	 * 机构注册表单信息
	 */
	public static final String SESSION_ORG_REGISTER_FORM_DATE = "@session_org_register_form_date";
	
	/**
	 * 找回手机号码数据
	 */
	public static final String SESSION_FORGET_LOGIN_PWD_FORM_DATE = "@SESSION_FORGET_LOGIN_PWD_FORM_DATE";
	
	/**
	 * 单点登录入参cust_no
	 */
	public static final String SESSION_TOA_SSO_CUST_NO = "@session_toa_sso_cust_no";
	
	
	/**
	 * 单点登录客户类型 0:待升级 1:未购买 2：已购买 
	 */
	public static final String SESSION_TOA_SSO_RESULT_TYPE = "@session_toa_sso_result_type";
	
	/**
	 * 问题编号
	 */
	public static final String SESSION_QUSTION_NO = "@session_qustion_no";
	/**
	 * 一账通SSO获取客户编号是否新客户编号
	 */
	public static final String SESSION_TOA_IS_NEW_CUSTNO = "@session_toa_is_new_custno";
	/**
	 * 一账通SSO客户是否需要激活流程
	 */
	public static final String SESSION_TOA_SSO_CUST_TYPE = "@session_toa_sso_cust_type";
	/**
	 * 一账通SSO过程partyno
	 */
	public static final String SESSION_TOA_SSO_PARTY_NO = "@session_toa_sso_party_no";
	/**
	 * 一账通SSO过程交易账号
	 */
	public static final String SESSION_TOA_SSO_TRADE_ACCOUNT = "@session_toa_sso_trade_account";
	/**
	 * 一账通加挂过程中的网上交易客户编号
	 */
	public static final String SESSION_TOA_BIND_CUST_NO = "@session_toa_bind_cust_no";
	/**
	 * 一账通加挂过程中的客户类型
	 */
	public static final String SESSION_TOA_BIND_CUST_TYPE = "@session_toa_bind_cust_type";
	/**
	 * 一账通SSO/加挂过程中的客户证件证号
	 */
	public static final String SESSION_TOA_SSO_ID_CODE = "@session_toa_sso_id_code";
	/**
	 * 一账通SSO/加挂过程中的客户证件类型
	 */
	public static final String SESSION_TOA_SSO_ID_TYPE = "@session_toa_sso_id_type";
	/**
	 * 一账通加挂过程中的手机号码
	 */
	public static final String SESSION_TOA_BIND_MOBILE= "@session_toa_bind_mobile";
	/**
	 * 一账通SSO过程中的手机号码
	 */
	public static final String SESSION_TOA_SSO_MOBILE= "@session_toa_sso_mobile";
	/**
	 * 一账通缓存处理用的cust_no
	 */
	public static final String SESSION_TOA_CACHE_CUST_NO = "@session_toa_cache_cust_no";
	/**
	 * 金融超市门店id
	 */
	public static final String SESSION_REGIST_SHOP_ID = "@session_regist_shop_id";
	/**
	 * 金融超市注册推荐人
	 */
	public static final String SESSION_REGIST_RECOMMEND = "@session_regist_recommend";
	
	//保存微信用户的openid
	public static final String SESSION_WX_OPEN_ID="@session_wx_open_id";
	
	//保存微信用户的openid
		public static final String SESSION_WX_NICK_NAME="@session_wx_nick_name";
	// 认证成功
	public static final String LOGIN_SUCCESS = "0000";
	   
	   // 用户名、密码不正确（密码输错5次会被锁定）
	   public static final String LOGIN_USER_PASSWORD_INVALID = "0011";
	   
	   // 账号被暂停
	   public static final String LOGIN_USER_STOP = "0012"; 
	   
	   // 账号被锁定
	   public static final String LOGIN_USER_LOCKED = "0013";
	   
	   // 未激活账号，不允许登录，需要通过PC端激活
	   public static final String LOGIN_USER_UNACTIVE = "0014";
	   
	   // 系统异常
	   public static final String LOGIN_SYSTEM_ERROR = "0015";
	   
	   // 显示以下其中某条错误提示：请您输入一账通用户名。请您输入一账通密码。验证码的一些错误信息。（验证码的错误提示分多种：验证码不能为空。验证码已过期，请重新输入。您输入的验证码有误，请您重新输入。）
	   public static final String LOGIN_VALIDATE_ERROE = "0016";
	   
	   // 用户名错误或用户名在黑名单中
	   public static final String LOGIN_USERID_ERROR = "0017";

		
		/**
		 * 网上交易网点号
		 */
		public static final String FUND_ONLINE_BRACH_CODE = "002001";

	/**
	 * 内部map，保存所有的常量信息
	 */
	private static Map map;
	
	/**
	 * 使用该构造方法实现一个JSTL可访问的系统常量，
	 */
	public WebConstants()
	{
		// initialize only once...
		if (map != null)
		{
			return;
		}
		map = new HashMap();
		Class c = this.getClass();
		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++)
		{
			Field field = fields[i];
			int modifier = field.getModifiers();
			// 排除private类型
			if (Modifier.isFinal(modifier) && !Modifier.isPrivate(modifier))
			{
				try
				{
					this.put(field.getName(), field.get(this));
				}
				catch (IllegalAccessException ex)
				{
					logger.error("", ex);
				}
			}
		}
	}
	
	/**
	 * 描述：继承map的get方法 
	 * 作者：岳知之 
	 * 时间：2008-11-21 上午10:01:54
	 * @param key 键
	 * @return
	 */
	public Object get(Object key)
	{
		return map.get(key);
	}
	
	/**
	 * 描述：继承map的put方法 
	 * 作者：岳知之 
	 * 时间：2008-11-21 上午10:01:57
	 * @param key  键
	 * @param value 值
	 * @return
	 */
	public Object put(Object key, Object value)
	{
		return map.put(key, value);
	}
}
