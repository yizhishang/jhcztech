package com.jhcz.cms.base.config;


/**
 * 描述: ApplicationLifecycleListener.java
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-2-26
 * 创建时间: 下午4:28:58
 */
public class Application
{
    private static String rootPath = "";

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
}