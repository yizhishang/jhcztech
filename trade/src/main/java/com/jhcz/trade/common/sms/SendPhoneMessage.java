package com.jhcz.trade.common.sms;

import java.util.HashMap;
import java.util.Map;
import com.jhcz.trade.common.constant.WebContants;

/**
 * @类名: SendPhoneMessage
 * @包名 com.jhcz.trade.common.sms
 * @描述: 发送手机短信
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-3-25 下午2:23:56
 * @版本 V1.0
 */
public class SendPhoneMessage {
	
	/**
	 * 构造函数
	 */
	public SendPhoneMessage(){
		
	}
	
	/**
	  * @方法名: sendMessage
	  * @描述: 短信发送验证码调用
	  * @参数 @param telphone：目标手机号码，多个以“,”分隔，一次性调用最多100个号码
	  * @参数 @param code  验证码
	  * @参数 @param template  短信发送模板 ：【微米】您的验证码是：610912，2分钟内有效。如非您本人操作，可忽略本消息
	  * @参数 @param valid  短信的有效期  默认是2分钟 120秒
	  * @参数 @param  resultType: 接口返回类型：json、xml、txt。默认值为txt
	  * @参数 @return   
	  * @返回类型: String 
	  * @throws
	  * @时间:2016-3-25下午2:26:17
	  * @作者: zhonghang
	 */
	public static String sendCode(String telphone,String message_code,String template,String valid,String resultType) throws Exception{
		Map<String,String> param = new HashMap<String,String>();
		
		/**
		 * 微米账号的接口UID
		 */
		param.put("uid", WebContants.SMS_ACCOUNT);

		/**
		 * 微米账号的接口密码
		 */
		param.put("pas", WebContants.SMS_PASSWORD);
		
		/**
		 * 目标手机号码，多个以“,”分隔，一次性调用最多100个号码，示例：139********,138********
		 */
		param.put("mob", telphone);

		/**
		 * 接口返回类型：json、xml、txt。默认值为txt
		 */
		if ("".equalsIgnoreCase(resultType)) {
			param.put("type", resultType);
		}else{
			param.put("type", "txt");
		}
		
		/**
		 * 短信模板cid，通过微米后台创建，由在线客服审核。必须设置好短信签名，签名规范： <br>
		 * 1、模板内容一定要带签名，签名放在模板内容的最前面；<br>
		 * 2、签名格式：【***】，签名内容为三个汉字以上（包括三个）；<br>
		 * 3、短信内容不允许双签名，即短信内容里只有一个“【】”<br>
		 */
		if ("".equals(template)) {
			template = "【金恒创智】您的验证码是：%P%，%P%分钟内有效。如非您本人操作，可忽略本消息。";
		}
		param.put("cid", template);
		/**
		 * 传入模板参数。<br>
		 * <br>
		 * 短信模板示例：<br>
		 * 【微米】您的验证码是：%P%，%P%分钟内有效。如非您本人操作，可忽略本消息。<br>
		 * <br>
		 * 传入两个参数：p1，p2<br>
		 * 最终发送内容：<br>
		 * 【微米】您的验证码是：610912，3分钟内有效。如非您本人操作，可忽略本消息。
		 */
			param.put("p1", message_code);
			if ("".equals(valid)) {
				param.put("p2", "2");
			}else{
				param.put("p2", valid);
			}
		String result = send(param,"post");
		return result;
	}
	
	/**
	  * @方法名: send
	  * @描述: 短信发送
	  * @参数 @param para  发送参数
	  * @参数 @param requestType，请求类型： post,get.默认是get请求
	  * @参数 @param requestEcode
	  * @参数 @return   
	  * @返回类型: String 
	  * @throws
	  * @时间:2016-3-25下午6:42:51
	  * @作者: zhonghang
	 */
	//结果示例：
	/*
	 *  json格式：
	 *	{"code":0,"msg":"发送成功"}
	 *	xml格式:
	 *	<result>
	 *	<code>-3</code>
	 * 	<msg>IP鉴权失败</msg>
	 *	</result>
	*/
	
	//返回结果：code	Number	是	0	结果代码     
	//      msg	String	是	发送成功	结果描述
	
	//	0	短信提交成功	非常好，继续发送下一条
	//	-1	参数不正确	确认是否遗漏必须传入的参数，以及格式和编码是否正确
	//	-1	参数不正确，短信内容签名不规范	短信内容不规范，没有签名。请参考注意事项，或在常见问题中，查看对于签名规范的要求：请点击
	//	-2	非法账号	UID及密码不正确，或者账号被暂时（或永久）关闭，请联系客服
	//	-3	IP鉴权失败	在合法的IP上调用接口，或将该IP添加为鉴权IP
	//	-4	账号余额不足	请在线充值
	//	-5	下发失败	系统异常造成，请稍后重试
	//	-6	短信内容含有非法关键字	请根据返回结果中提示的非法关键字，重新调整短信内容
	//	-7	同一个号码、同一段短信内容，在同一小时内重复下发	为了避免对手机用户的骚扰，请避免类似下发，或联系客服
	//	-8	拓展特服号码不正确	重新确认号码有效性，并重新设置
	//	-9	非法子账号	子UID不正确，或者账号被暂时（或永久）关闭，请联系客服
	//	-10	定时计划时间不正确	重新确认定时计划时间格式及有效性，并重新设置
	//	-11	CID不正确	CID不正确，请确认短信模板唯一标识
	//	-13	一次性提交手机号码过多	GET方式一次性提交号码不要超过100条，POST方式不要超过1万条
	//	-16	接口调用错误次数太多	接口调用错误次数太多，请联系在线客服
	private static String send(Map<String,String> para,String requestType) {
		String result = "";
		if ("post".equalsIgnoreCase(requestType)){
			result = HttpClientHelper.convertStreamToString(HttpClientHelper.post("http://api.weimi.cc/2/sms/send.html", para),"UTF-8");
		}else if ("get".equalsIgnoreCase(requestType)) {
			result = HttpClientHelper.convertStreamToString(HttpClientHelper.get("http://api.weimi.cc/2/sms/send.html", para),"UTF-8");
		}else{
			result = HttpClientHelper.convertStreamToString(HttpClientHelper.get("http://api.weimi.cc/2/sms/send.html", para),"UTF-8");
		}
		return result;
	}
	
	public static void main(String[] args) {
		SendPhoneMessage spm = new SendPhoneMessage();
		System.out.println("++++++++++++++++手机短信发送开始+++++++++++++++++++");
		String result = "";
		try {
			result = spm.sendCode("13266811623", "123832", WebContants.SMS_TEMPLATE,"","");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("++++++++++++++++手机短信发送结束+++++++++++++++++++返回值为："+result);
	}
}
