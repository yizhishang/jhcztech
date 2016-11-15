package com.yizhishang.plat.web.listener;

import com.yizhishang.base.config.Configuration;
import com.yizhishang.base.config.SysConfig;
import com.yizhishang.base.jdbc.connection.Configure;
import com.yizhishang.plat.system.Application;
import com.yizhishang.plat.template.plan.PublishPlan;
import com.yizhishang.plat.template.publish.PublishEngine;
import com.yizhishang.timerengine.TaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-11
 * 创建时间: 11:13:50
 */
@Component
public class ApplicationLifecycleListener implements ApplicationContextAware, ServletContextAware, InitializingBean,
        ApplicationListener<ContextRefreshedEvent>
{

    private static Logger logger = LoggerFactory.getLogger(ApplicationLifecycleListener.class);

    private ServletContext context = null;

    /**
     * 系统启动时初始化相应的数据
     */
    @SuppressWarnings("static-access")
    public void onApplicationEvent(ContextRefreshedEvent evt)
    {
        logger.info("4.1 => MyApplicationListener.onApplicationEvent");
        if (evt.getApplicationContext().getParent() == null) {
            logger.info("4.2 => MyApplicationListener.onApplicationEvent");
        }

        //读入数据库配置文件
        Configure.getInstance();

        //读入所有配置信息
        SysConfig.getInstance().loadConfig();

        /**
         * 是否启动模板发布程序，0：不启动 1：启动
         */
        if (Configuration.getInt("system.isPublish") == 1) {
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

    @Override
    public void afterPropertiesSet() throws Exception
    {
        logger.info("3 => StartupListener.afterPropertiesSet");
    }

    @Override
    public void setServletContext(ServletContext servletContext)
    {

        logger.info("2 => StartupListener.setServletContext");

        //初始应用程序根目录物理路径
        context = servletContext;
        Application.setRootPath(context.getRealPath("/"));

        //初始应用程序根目录
        Application.setContextPath(context.getContextPath());
    }

    /**
     * 在系统启动时调用
     */
    public void setApplicationContext(ApplicationContext ac) throws BeansException
    {
        if (logger.isInfoEnabled()) {
            logger.info("Starting application......");
        }
        logger.info("1 => StartupListener.setApplicationContext");
    }
}