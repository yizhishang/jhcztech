package com.yizhishang.base.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * 描述:	 Response工具类
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-17
 * 创建时间: 14:42:31
 */
public class ResponseHelper
{
    /**
     * 把内容打印到HttpServletResponse中
     *
     * @param response HttpServletResponse对象
     * @param content  需要输出的内容
     */
    public static void print(HttpServletResponse response, String content)
    {
        try
        {
            response.getWriter().print(content);
        }
        catch (IOException ex)
        {
        }
    }
    
    /**
     * 把内容打印到HttpServletResponse中
     *
     * @param response HttpServletResponse对象
     * @param content  需要输出的内容
     */
    public static void print(HttpServletResponse response, Object content)
    {
    	try
    	{
    		response.getWriter().print(content);
    	}
    	catch (IOException ex)
    	{
    	}
    }
    
    /**
     * 把内容打印到HttpServletResponse中
     *
     * @param response HttpServletResponse对象
     * @param content  需要输出的内容
     * @param contentType  http输出类型内容
     */
    public static void print(HttpServletResponse response, Object content, String contentType)
    {
        try
        {
        	response.setContentType(contentType);
            response.getWriter().print(content);
        }
        catch (IOException ex)
        {
        }
    }

    /**
     * 把内容打印到HttpServletResponse中
     *
     * @param response HttpServletResponse对象
     * @param content  需要输出的内容
     */
    public static void println(HttpServletResponse response, Object content)
    {
        try
        {
            response.getWriter().print(content);
        }
        catch (IOException ex)
        {
        }
    }
    
    /**
     * 把内容打印到HttpServletResponse中
     *
     * @param response HttpServletResponse对象
     * @param content  需要输出的内容
     */
    public static void println(HttpServletResponse response, String content)
    {
    	try
    	{
    		response.getWriter().print(content);
    	}
    	catch (IOException ex)
    	{
    	}
    }
}
