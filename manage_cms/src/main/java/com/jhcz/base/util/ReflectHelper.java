package com.jhcz.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述:	 反射工具类
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-6
 * 创建时间: 21:51:56
 */
public class ReflectHelper
{
    
    /**
     * ReflectHelper 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ReflectHelper.class);

    /**
     * 提指定的类载入以系统中
     * @param name 类名称
     * @return 类对象
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("rawtypes")
    public static Class classForName(String name) throws ClassNotFoundException
    {
        try
        {
            return Thread.currentThread().getContextClassLoader().loadClass(name);
        }

        catch (ClassNotFoundException e)
        {
        }
        catch (SecurityException e)
        {
        }
        return Class.forName(name);
    }

    /**
     * 根据名称生成指定的对象
     * @param name 类名称
     * @return 具体的对象,若发生异常，则返回null
     */
    public static Object objectForName(String name)
    {
        try
        {
            return Class.forName(name).newInstance();
        }
        catch (Exception ex)
        {
           logger.error("",ex);
        }
        return null;
    }
}
