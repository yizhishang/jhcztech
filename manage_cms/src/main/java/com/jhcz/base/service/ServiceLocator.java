package com.jhcz.base.service;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.jhcz.base.service.exception.ServiceException;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-11
 * 创建时间: 14:49:14
 */
public class ServiceLocator
{
    private static Logger logger = LoggerFactory.getLogger(ServiceLocator.class);

    /**
     * 返回服务对象
     * @param interfaceClass
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Object getService(Class interfaceClass)
    {

        String serviceClassName = interfaceClass.getName();
        int idx = serviceClassName.lastIndexOf(".");
        String packageName = "";
        String shortName = "";
        if (idx == -1)
        {
            shortName = serviceClassName;
        }
        else
        {
            packageName = serviceClassName.substring(0, idx);
            shortName = serviceClassName.substring(idx + 1);
        }


        String serviceImplClassName = packageName + ".impl." + shortName + "Impl";
        Class clazz = null;
        try
        {
            clazz = Class.forName(serviceImplClassName);
            return clazz.newInstance();
        }
        catch (ClassNotFoundException ne)
        {
            logger.error("", ne);
            throw new ServiceException("没有找到相应的service实现类[" + serviceImplClassName + "]", ne);
        }
        catch (Exception ex)
        {
            logger.error("", ex);
            throw new ServiceException("创建service实现类失败[" + serviceImplClassName + "]", ex);
        }
    }
}
