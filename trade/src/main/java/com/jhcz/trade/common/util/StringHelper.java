  package com.jhcz.trade.common.util;

import java.math.RoundingMode;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * 描述:字符串相关工具类  
 * <br/>版本: 1.0
 * <br/>时间: 下午03:37:58
 */
public class StringHelper
{
	
	/**
	 * 获取参数
	 * @param request
	 * @param filed
	 * @param defaultValue
	 * @return
	 */
	 public static String getParameter(HttpServletRequest request,String filed,String defaultValue){
		 
		 return request.getParameter(filed) == null ? defaultValue : request.getParameter(filed);
	 }
	 
	 /**
	  * 从request中获取参数
	  * @param request
	  * @param filed
	  * @return
	  */
	 public static String getParameter(HttpServletRequest request,String filed){
		 
		 return getParameter(request, filed,"");
	 }
	
	
	/**
	 * 描述：手机号中间4位用*处理
	 * @param mobile
	 * @return
	 */
	public static String buildMobile(String mobile)
	{
		if (StringUtils.isEmpty(mobile) || mobile.length() != 11)
		{
			return mobile;
		}
		return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
	}
	
	/**
	 * 描述：证件号码*处理
	 * @param idcard
	 * @return
	 */
	public static String buildIDCard(String idcard)
	{
		if (StringUtils.isEmpty(idcard))
		{
			return idcard;
		}
		int length = idcard.length();
		if (length <= 8)
		{
			return idcard.replaceAll("\\d*(\\d{4})", "****$1");
		}
		return idcard.replaceAll("(\\d{4})\\d*(\\d{4})", "$1****$2");
	}
	
	public static String buildBankAcct(String bankacct)
	{
		if (StringUtils.isEmpty(bankacct))
		{
			return bankacct;
		}
		return bankacct.replaceAll("\\s", "").replaceAll("(\\d{4})(?=\\d)","$1 ");
	}
	
	/**
	 * 描述：
	 * @param number
	 * @return
	 */
	public static String milliFormatStr(String number)
	{
		return milliFormatStr(number, ",##0.00");
	}
	
	public static String milliFormatStr(String number, String format)
	{
		try
		{
			if (StringUtils.isEmpty(number))
			{
				return number;
			}
			double num = Double.valueOf(number);
			return milliFormat(num, format);
		}
		catch (Exception e)
		{
		}
		return number;
	}
	
	/**
	 * 描述：增加千位分隔符
	 * @param number
	 * @return
	 */
	public static String milliFormat(double number)
	{
		return milliFormat(number, ",##0.00");
	}
	
	public static String milliFormat(double number, String format)
	{
		try
		{
			number = Math.abs(number);
			NumberFormat nf = new DecimalFormat(format);
			nf.setRoundingMode(RoundingMode.DOWN);
			return nf.format(number);
		}
		catch (Exception e)
		{
		}
		return String.valueOf(number);
	}
	
	
	
	/**
	 * 描述：
	 * @param number
	 * @return
	 */
	public static String milliFormatStr2(String number)
	{
		return milliFormatStr2(number, ",##0.00");
	}
	
	public static String milliFormatStr2(String number, String format)
	{
		try
		{
			if (StringUtils.isEmpty(number))
			{
				return number;
			}
			double num = Double.valueOf(number);
			return milliFormat2(num, format);
		}
		catch (Exception e)
		{
//			SysLogUtils.error("", e);
		}
		return number;
	}
	
	/**
	 * 描述：增加千位分隔符
	 * @param number
	 * @return
	 */
	public static String milliFormat2(double number)
	{
		return milliFormat2(number, ",##0.00");
	}
	
	public static String milliFormat2(double number, String format)
	{
		try
		{
			number = Math.abs(number);
			NumberFormat nf = new DecimalFormat(format);
			nf.setRoundingMode(RoundingMode.HALF_UP);
			return nf.format(number);
		}
		catch (Exception e)
		{
//			SysLogUtils.error("", e);
		}
		return String.valueOf(number);
	}
	
	/**
	 * 不取绝对值
	 */
	public static String milliFormatStr3(String number)
	{
		return milliFormatStr3(number, ",##0.00");
	}
	
	public static String milliFormatStr3(String number, String format)
	{
		try
		{
			if (StringUtils.isEmpty(number))
			{
				return number;
			}
			double num = Double.valueOf(number) * 100;
			return milliFormat3(num, format);
		}
		catch (Exception e)
		{
//			SysLogUtils.error("", e);
		}
		return number;
	}
	
	public static String milliFormat3(double number, String format)
	{
		try
		{
			//number = Math.abs(number);
			NumberFormat nf = new DecimalFormat(format);
			nf.setRoundingMode(RoundingMode.HALF_UP);
			return nf.format(number);
		}
		catch (Exception e)
		{
//			SysLogUtils.error("", e);
		}
		return String.valueOf(number);
	}
	
	
	
	/**
	 * 描述：最银行卡号最后4位
	 * @param bankcard_acc 银行卡号
	 */
	public static String getBankCardLast4Number(String bankcard_acc)
	{
		if (StringUtils.isEmpty(bankcard_acc) || bankcard_acc.length() <= 4)
		{
			return bankcard_acc;
		}
		return bankcard_acc.substring(bankcard_acc.length() - 4);
	}
	
	/**
	 * 描述：
	 * @param str
	 * @param start
	 * @param end
	 * @return
	 */
	public static String subString(String str, int start, int end)
	{
		try
		{
			if (StringUtils.isEmpty(str) || start < 0 || end > str.length())
			{
				return str;
			}
			return str.substring(start, end);
		}
		catch (Exception e)
		{
//			SysLogUtils.error("", e);
		}
		return str;
	}
	
	public static String subString2(String str, int start, int end)
	{
		try
		{
			String result = "";
			if (StringUtils.isEmpty(str) || start > 0 || end > str.length())
			{
				return str;
			}
			result = str.substring(start, end);
			if (end < str.length())
			{
				result += "...";
			}
			return result;
		}
		catch (Exception e)
		{
//			SysLogUtils.error("", e);
		}
		return str;
	}
	
	public static String RandomNumber(int num)
	{
		SecureRandom random = new SecureRandom();
		return String.valueOf(random.nextInt(num));
	}
	
	//格式化日期
	public static String dateFormat(String str)
	{
		return dateFormat(str, "yyyy-MM-dd");
		
	}
	
	public static String dateFormat(String str, String format)
	{
		SimpleDateFormat sim = new SimpleDateFormat("yyyyMMdd");
		try
		{
			if (StringUtils.isEmpty(str))
			{
				return str;
			}
			else
			{
				return DateUtil.formatDate(sim.parse(str), format);
			}
		}
		catch (Exception e)
		{
//			SysLogUtils.error("", e);
			return "";
		}
	}
	
	// 过滤特殊字符  
	public static String StringFilter(String str) 
	{
		// 只允许字母和数字
		// String   regEx  =  "[^a-zA-Z0-9]";                     
		// 清除掉所有特殊字符  
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
	
	public static String StringFilterNew(String str) throws PatternSyntaxException
	{
		// 只允许字母和数字       
		// String   regEx  =  "[^a-zA-Z0-9]";                     
		// 清除掉所有特殊字符  
		String regEx = "[！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
}
