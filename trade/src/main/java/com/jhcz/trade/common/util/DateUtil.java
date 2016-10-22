package com.jhcz.trade.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @类名: DateUtil
 * @包名 com.jhcz.trade.common.util
 * @描述: 时间操作工具类
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-3-26 上午11:20:39
 * @版本 V1.0
 */
public class DateUtil {
	
	/**
	  * @方法名: formatDate
	  * @描述: 格式化时间
	  * @参数 @param date
	  * @参数 @param pattern
	  * @参数 @return   
	  * @返回类型: String 
	  * @throws
	  * @时间:2016-3-26上午11:23:46
	  * @作者: zhonghang
	 */
	public static String formatDate(Date date,String pattern){
		if ("".equals(pattern)) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String formateDate = sdf.format(date);
		return formateDate;
	}
	
	public static String formatDate(Date date){
		
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}
}
