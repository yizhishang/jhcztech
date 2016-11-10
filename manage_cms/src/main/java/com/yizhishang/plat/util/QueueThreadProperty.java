package com.yizhishang.plat.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yizhishang.base.config.Configuration;
import com.yizhishang.base.util.FileHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.system.Application;

/**
 * 描述: 记录队列工作纯种的日志
 * 版权: Copyright (c) 2012 
 * 公司:  
 * 作者: 袁永君
 * 版本: 1.0 
 * 创建日期: Nov 15, 2013 
 * 创建时间: 4:33:06 PM
 */
public class QueueThreadProperty
{
	
	private static Logger logger = LoggerFactory.getLogger(QueueThreadProperty.class);
	
	private static Properties props = null;
	
	private static String CONFIG_FILE_PATH = "";
	
	private static String CONFIG_FILE_NAME = "ThreadQueueLog.properties";
	
	static
	{
		String threadQueueLogPath = Configuration.getString("publish.threadQueueLogPath");
		if (StringHelper.isEmpty(threadQueueLogPath))
		{
			CONFIG_FILE_PATH = Application.getRootPath() + "/" + CONFIG_FILE_NAME;
		}
		else
		{
			CONFIG_FILE_PATH = threadQueueLogPath + "/" + CONFIG_FILE_NAME;
		}
		CONFIG_FILE_PATH = FileHelper.normalize(CONFIG_FILE_PATH);
		loadProperty();
	}
	
	public static void loadProperty()
	{
		
		FileInputStream in = null;
		try
		{
			props = new Properties();
			in = new FileInputStream(CONFIG_FILE_PATH);
			props.load(in);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
		}
		finally
		{
			if (in != null)
			{
				try
				{
					in.close();
					in = null;
				}
				catch (Exception e)
				{
					logger.error(e.getMessage());
				}
			}
			
		}
		
	}
	
	public static String getPropertyString(String index)
	{
		String reValue = "";
		reValue = props.getProperty(index);
		return reValue;
		
	}
	
	public static void setPropertyString(String Parameter, String values)
	{
		props.setProperty(Parameter, values);
		try
		{
			props.store(new FileOutputStream(CONFIG_FILE_PATH), null);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
		}
	}
}
