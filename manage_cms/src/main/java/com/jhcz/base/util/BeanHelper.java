package com.jhcz.base.util;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

/**
 * 描述:	 javabean工具类，bean与map间的互转
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-8
 * 创建时间: 21:36:30
 */
public class BeanHelper
{
    
    /**
     * BeanHelper 日志
     */
    private static Logger logger = Logger.getLogger(BeanHelper.class);

    /**
     * 把一个bean中的属性转化到map中
     * @param bean       bean对象
     * @param properties 存放bean中属性的map对象
     */
    public static void beanToMap(Object bean, Map<String, Object> properties)
    {
        if (bean == null || properties == null)
        {
            return;
        }

        try
        {
            Map<String, String> map = BeanUtils.describe(bean);
            map.remove("class");
            for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext();)
            {
                String key = iter.next();
                Object value = map.get(key);
                properties.put(key, value);
            }
        }
        catch (Exception ex)
        {
            logger.error("", ex);
        }
    }

    /**
     * 把一个map中的所有属性值设置到bean中
     * @param properties 含有属性的map对象
     * @param bean       需要被设置属性的对象
     */
    @SuppressWarnings("rawtypes")
    public static void mapToBean(Map<String, Object> properties, Object bean)
    {
        if (properties == null || bean == null)
        {
            return;
        }
        try
        {
            for (Iterator iter = properties.keySet().iterator(); iter.hasNext();)
            {
                String key = (String) iter.next();
                Object value = properties.get(key);
                BeanUtils.setProperty(bean, key, value);
            }
        }
        catch (Exception ex)
        {
            logger.error("", ex);
        }
    }
}
