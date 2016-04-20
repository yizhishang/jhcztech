package com.jhcz.trade.common.constant;

/**
 * @类名: WebContants
 * @包名 com.jhcz.trade.common.constant
 * @描述: 项目业务中常量的定义
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-3-25 下午2:43:42
 * @版本 V1.0
 */
public class WebContants {
	/**
	 * 短信发送接口----账号
	 */
	public static final  String SMS_ACCOUNT = "";
	
	/**
	 * 短信发送接口----密码
	 */
	public static final  String SMS_PASSWORD = "";
	
	/**
	 * 短信发送签名：  其中 %P%变量占位符
	 */
	public static final String SMS_TEMPLATE = "【金恒创智】您的验证码是：%P%，%P%分钟内有效。如非您本人操作，可忽略本消息。";
	
	
	/**
	 * 文件上传的路径
	 */
	public static final String UPLOAD_PATH = "";
	
	/**
	 * 发行中
	 */
	public static final String FUND_STATUS_INISSURE = "1";

	/**
	 * 正常交易
	 */
	public static final String FUND_STATUS_TRADING = "2";
}
