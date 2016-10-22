package com.jhcz.trade.account.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 日期工具类
 * @author aibo
 *
 */
public class DateUtils {

	public static String parseDate(Date date,String pattern){
		
		DateFormat df = new SimpleDateFormat(pattern);
		
		return df.format(date);
		
	}
	
	public static Date parse(String strDate, String pattern)  
            throws ParseException  
    {  
        return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(pattern).parse(strDate);  
    }  
	
	public static void main(String[] args) throws ParseException {
		System.out.println(parse("2016-03-18","yyyy-MM-dd"));
	}
	
}
