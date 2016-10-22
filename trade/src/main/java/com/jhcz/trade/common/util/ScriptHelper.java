package com.jhcz.trade.common.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;

/**
 * 描述:  
 * <br/>版权: Copyright (c) 2016
 * <br/>公司: 金恒创智
 * <br/>作者: 
 * <br/>版本: 1.0
 * <br/>日期: 2016-03-16
 * <br/>时间: 下午05:28:22
 */
public class ScriptHelper
{
	
	/**
	 * 描述：
	 * @param response
	 * @param data
	 */
	public static void callback(HttpServletResponse response, Object data, String function)
	{
		
		try
		{
			StringBuffer bf = new StringBuffer();
			if (StringUtils.isNotEmpty(function))
			{
				String json = JSONObject.fromObject(data).toString();
				bf.append("<script type=\"text/javascript\">");
				bf.append("window.parent.").append(function).append("(").append(json).append(");window.parent.document.getElementById(\"hiddenFrame\").src=\"about:blank\";");
				bf.append("</script>");
			}
			PrintWriter printWriter = response.getWriter();
			printWriter.write(bf.toString());
			printWriter.flush();
			printWriter.close();
		}
		catch (Exception ex)
		{
		}
	}
	
	public static void parentRedirect(HttpServletResponse response, String redirect)
	{
		StringBuffer bf = new StringBuffer();
		bf.append("<script language=\"javascript\">");
		String outStr = "window.parent.location.href=\"" + redirect + "\";window.parent.document.getElementById(\"hiddenFrame\").src=\"about:blank\";";
		bf.append(outStr);
		bf.append("</script>");
		try
		{
			PrintWriter printWriter = response.getWriter();
			printWriter.write(bf.toString());
			printWriter.flush();
			printWriter.close();
		}
		catch (Exception ex)
		{
		}
	}
	
}
