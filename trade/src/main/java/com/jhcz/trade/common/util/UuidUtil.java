package com.jhcz.trade.common.util;

import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

/**
 * @类名: UuidUtil
 * @包名 com.jhcz.trade.common.util
 * @描述: UUID
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-4-5 上午10:31:28
 * @版本 V1.0
 */
public class UuidUtil {
	
	/**
	  * @方法名: createUuid
	  * @描述: 生成jdk内部的uuid
	  * @参数 @return   
	  * @返回类型: String 
	  * @throws
	  * @时间:2016-4-5上午10:38:33
	  * @作者: zhonghang
	 */
	public static String createUuid(){
		UUID uuid = UUID.randomUUID();
		String strUuid = uuid.toString();
		return strUuid;
	}
	
	/**
	  * @方法名: createSerialNumber
	  * @描述: 年月日时分秒+6位随机码
	  * @参数 @return   
	  * @返回类型: String 
	  * @throws
	  * @时间:2016-4-5上午11:09:27
	  * @作者: zhonghang
	 */
	public static String createSerialNumber(){
		StringBuffer sb = new StringBuffer();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		String month = addZero(cal.get(Calendar.MONTH)+1);
		String date = addZero(cal.get(Calendar.DATE));
		String hour = addZero(cal.get(Calendar.HOUR));
		String min = addZero(cal.get(Calendar.MINUTE));
		String ss = addZero(cal.get(Calendar.SECOND));
		String val = "";
		Random random = new Random();
		 for(int i = 0; i < 6; i++) {  
             
	            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
	            //输出字母还是数字  
	            if( "char".equalsIgnoreCase(charOrNum) ) {  
	                //输出是大写字母还是小写字母  
	                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
	                val += (char)(random.nextInt(26) + temp);  
	            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
	                val += String.valueOf(random.nextInt(10));  
	            }  
	        }  
		 sb.append(year).append(month).append(date).append(hour).append(min).append(ss).append(val);
		return sb.toString();
	}
	
	
	/**
	  * @方法名: addZero
	  * @描述: 数字小于10的时候补零
	  * @参数 @param value
	  * @参数 @return   
	  * @返回类型: String 
	  * @throws
	  * @时间:2016-4-5上午10:57:49
	  * @作者: zhonghang
	 */
	public static String addZero(int value){
		String str_value = "";
		if (value < 10) {
			str_value = "0"+value;
		}else{
			str_value = value+"";
		}
		return str_value;
	}
	
	public static void main(String[] args) {
		String uuid = UuidUtil.createUuid();
		String serialNum = UuidUtil.createSerialNumber();
		System.out.println("uuid=="+uuid+",===serialNum=="+serialNum);
	}
}
