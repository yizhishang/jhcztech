package com.jhcz.cms.listener;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.jhcz.cms.base.config.Application;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-11
 * 创建时间: 11:13:50
 */
public class ApplicationLifecycleListener implements ServletContextListener
{
	
	private ServletContext context = null;
	
	private static Logger logger = Logger.getLogger(ApplicationLifecycleListener.class);
	
    /**
    * 在系统停止时调用
    * @param event a ServletContextEvent instance
    */
    public void contextDestroyed(ServletContextEvent event)
	{
		if (logger.isInfoEnabled())
		{
			logger.info("Stopping application......");
		}
	}
	
    /**
    * 在系统启动时调用
    * @param event a ServletContextEvent instance
    */
    public void contextInitialized(ServletContextEvent event)
	{
		if (logger.isInfoEnabled())
		{
			logger.info("Starting application......");
		}
		
		context = event.getServletContext();
		init();
	}
	
    /**
    * 系统启动时初始化相应的数据
    */
    private void init()
	{
        //初始应用程序根目录路径
		Application.setRootPath(context.getRealPath("/"));
		
	}
}
