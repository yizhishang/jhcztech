package com.jhcz.plat.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.jhcz.base.config.Configuration;
import com.jhcz.base.config.SysConfig;
import com.jhcz.base.jdbc.connection.Configure;
import com.jhcz.plat.system.Application;
import com.jhcz.plat.template.plan.PublishPlan;
import com.jhcz.plat.template.publish.PublishEngine;
import com.jhcz.timerengine.TaskManager;

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
    @Override
    @SuppressWarnings("static-access")
    public void contextDestroyed(ServletContextEvent event)
	{
        //关闭数据源的连接
		Configure.getInstance().destroyDataSource();
		
		if (logger.isInfoEnabled())
		{
			logger.info("Stopping application......");
		}
	}
	
    /**
    * 在系统启动时调用
    *
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
    @SuppressWarnings("static-access")
    private void init()
	{
        //初始应用程序根目录路径
		Application.setRootPath(context.getRealPath("/"));
		
        //读入数据库配置文件
		Configure.getInstance();
		
        //读入所有配置信息
		SysConfig.getInstance().loadConfig();
		
        /**
         * 是否启动模板发布程序，0：不启动 1：启动
         */
		if (Configuration.getInt("system.isPublish") == 1)
		{
            //启动模板发布引擎
            PublishEngine pe = new PublishEngine();
            pe.run();
			
            //启动发布计划
			PublishPlan publishPlan = new PublishPlan();
			publishPlan.start();
			
            //启动任务管理器
            TaskManager tm = new TaskManager();
            tm.run();
		}
		
	}
}