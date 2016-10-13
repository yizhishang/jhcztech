package com.jhcz.plat.util;

import org.apache.log4j.Logger;


/**
 * 描述: 记录日志
 * 版权: Copyright (c) 2015
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2015-10-9
 * 创建时间: 上午10:32:36
 */
public class LoggerUtil
{
	/**
	 * 描述: info级别日志记录
	 * 作者: 袁永君
	 * 创建日期: 2015-10-9
	 * 创建时间: 上午10:38:18
	 * @param t
	 * @param msg
	 */
	public static <T> void info(Class<T> t, String msg)
	{
		Logger.getLogger(t).info(msg);
	}
	
	/**
	 * 描述: error级别日志记录
	 * 作者: 袁永君
	 * 创建日期: 2015-10-9
	 * 创建时间: 上午10:38:34
	 * @param t
	 * @param ex
	 */
	public static <T> void error(Class<T> t, Exception ex)
	{
		Logger.getLogger(t).error(null, ex);
	}
	
	/**
	 * 描述: error级别日志记录
	 * 作者: 袁永君
	 * 创建日期: 2015-10-12
	 * 创建时间: 下午2:09:21
	 * @param t
	 * @param msg
	 */
	public static <T> void error(Class<T> t, String msg)
	{
		Logger.getLogger(t).error(msg);
	}
	
}
