package com.jhcz.base.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * 描述:	 Session工具类
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-31
 * 创建时间: 11:37:13
 */
public final class SessionHelper
{
    /**
     * 向SESSION中设置字符串
     *
     * @param name    名称
     * @param value   值
     * @param session session
     */
    public static void setString(String name, String value, HttpSession session)
    {
        if (StringHelper.isEmpty(name))
        {
            return;
        }

        session.setAttribute(name, value);
    }

    /**
     * 向SESSION中设置数字
     *
     * @param name    名称
     * @param value   值
     * @param session session
     */
    public static void setInt(String name, int value, HttpSession session)
    {
        if (StringHelper.isEmpty(name))
        {
            return;
        }
        session.setAttribute(name, new Integer(value));
    }
    
    /**
     * 向SESSION中设置数字
     *
     * @param name    名称
     * @param value   值
     * @param session session
     */
    public static void setLong(String name, long value, HttpSession session)
    {
    	if (StringHelper.isEmpty(name))
    	{
    		return;
    	}
    	session.setAttribute(name, new Long(value));
    }
    
    /**
     * 向SESSION中设置数字
     *
     * @param name    名称
     * @param value   值
     * @param session session
     */
    public static void setDouble(String name, double value, HttpSession session)
    {
    	if (StringHelper.isEmpty(name))
    	{
    		return;
    	}
    	session.setAttribute(name, new Double(value));
    }

    /**
     * 向SESSION中设置对象
     *
     * @param name    名称
     * @param value   值
     * @param session session
     */
    public static void setObject(String name, Object value, HttpSession session)
    {
        if (StringHelper.isEmpty(name))
        {
            return;
        }
        session.setAttribute(name, value);
    }

    /**
     * 向SESSION中设置BOOL值
     *
     * @param name    名称
     * @param value   值
     * @param session session
     */
    public static void setBoolean(String name, boolean value, HttpSession session)
    {
        if (StringHelper.isEmpty(name))
        {
            return;
        }
        session.setAttribute(name, Boolean.valueOf(value));
    }

    /**
     * 从SESSION中提取字符串
     *
     * @param name    键值名称
     * @param session HttpSession对象
     * @return
     */
    public static String getString(String name, HttpSession session)
    {
        Object value = session.getAttribute(name);
        if (value == null)
        {
            return "";
        }
        return value.toString().trim();
    }

    /**
     * 从SESSION中提取数值
     *
     * @param name    键值名称
     * @param session HttpSession对象
     * @return
     */
    public static int getInt(String name, HttpSession session)
    {
        Object value = session.getAttribute(name);
        if (value == null)
        {
            return 0;
        }
        return ((Integer) value).intValue();
    }
    
    /**
     * 从SESSION中提取数值
     *
     * @param name    键值名称
     * @param session HttpSession对象
     * @return
     */
    public static long getLong(String name, HttpSession session)
    {
    	Object value = session.getAttribute(name);
    	if (value == null)
    	{
    		return 0;
    	}
    	return ((Long) value).longValue();
    }
    
    /**
     * 从SESSION中提取数值
     *
     * @param name    键值名称
     * @param session HttpSession对象
     * @return
     */
    public static double getDouble(String name, HttpSession session)
    {
    	Object value = session.getAttribute(name);
    	if (value == null)
    	{
    		return 0;
    	}
    	return ((Double) value).doubleValue();
    }

    /**
     * 从SESSION中提取对象
     *
     * @param name    键值名称
     * @param session HttpSession对象
     * @return
     */
    public static Object getObject(String name, HttpSession session)
    {
        return session.getAttribute(name);
    }

    /**
     * 从SESSION中提取BOOL值
     *
     * @param name    名称
     * @param session session
     * @return
     */
    public static boolean getBoolean(String name, HttpSession session)
    {
        Object object = session.getAttribute(name);
        if (object == null)
        {
            return false;
        }
        return ((Boolean) object).booleanValue();
    }

    /**
     * 描述：清除session中的所有值
     * 作者：
     * 时间：Oct 29, 2015 4:24:34 PM
     * @param session session
     */
    public static void removeAllAttribute(HttpSession session)
    {
        @SuppressWarnings("unchecked")
		Enumeration<String> e = session.getAttributeNames();
        List<String> attrName = new ArrayList<String>();
        while (e.hasMoreElements())
        {
            String name = e.nextElement();
            attrName.add(name);
        }
        for (String name : attrName)
		{
        	session.removeAttribute(name);
		}
    }
}
