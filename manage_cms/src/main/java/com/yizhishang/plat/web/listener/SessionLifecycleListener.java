package com.yizhishang.plat.web.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2007-3-16
 * 创建时间: 16:07:18
 */
public class SessionLifecycleListener implements HttpSessionListener
{

    private static ArrayList<Object> sessionList = new ArrayList<Object>();

    public SessionLifecycleListener()
    {
    }

    public static ArrayList<Object> getSessionList()
    {
        return sessionList;
    }

    @Override
    public void sessionCreated(HttpSessionEvent se)
    {
        HttpSession session = se.getSession();
        synchronized (sessionList) {
            sessionList.add(session);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se)
    {

        HttpSession session = se.getSession();
        synchronized (sessionList) {
            sessionList.remove(session);
        }
    }
}

