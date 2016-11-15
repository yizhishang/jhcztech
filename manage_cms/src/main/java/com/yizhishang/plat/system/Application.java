package com.yizhishang.plat.system;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-18
 * 创建时间: 17:04:22
 */
public class Application
{

    private static String rootPath = "";

    private static String contextPath = "";

    /**
     * 获得当前应用程序的根目录的路径
     *
     * @return
     */
    public static String getRootPath()
    {
        return rootPath;
    }

    public static void setRootPath(String rootPath)
    {
        Application.rootPath = rootPath;
    }

    public static String getContextPath()
    {
        return contextPath;
    }

    public static void setContextPath(String contextPath)
    {
        Application.contextPath = contextPath;
    }
}
