package com.jhcz.base.dao.factory;

import org.apache.log4j.Logger;

import com.jhcz.base.service.exception.ServiceException;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-11
 * 创建时间: 14:43:47
 */
public class DAOFactory
{
    private static Logger logger = Logger.getLogger(DAOFactory.class);

    /**
     * 返回数据访问对象
     * @param interfaceClass
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Object getDao(Class interfaceClass)
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
            throw new ServiceException("没有找到相应的Dao实现类[" + serviceImplClassName + "]", ne);
        }
        catch (Exception ex)
        {
            logger.error("", ex);
            throw new ServiceException("创建Dao实现类失败[" + serviceImplClassName + "]", ex);
        }
    }
}