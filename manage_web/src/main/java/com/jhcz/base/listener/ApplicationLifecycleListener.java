package com.jhcz.base.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.jhcz.base.domain.system.Application;

/**
 * 描述: ApplicationLifecycleListener.java
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-2-26
 * 创建时间: 下午4:28:58
 */
public class ApplicationLifecycleListener implements ServletContextListener
{
	
	private ServletContext context = null;
	
	private static Logger logger = Logger.getLogger(ApplicationLifecycleListener.class);
	
    /**
    * 在系统停止时调用
    * @param event a ServletContextEvent instance
    */
    @Override
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
	@Override
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