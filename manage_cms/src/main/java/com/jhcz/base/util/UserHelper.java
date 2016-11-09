package com.jhcz.base.util;

import javax.servlet.http.HttpSession;

import com.jhcz.plat.Constants;


public class UserHelper
{

	/**
     * 获得当前后台登录用户的UID
     * @return
     */
	public static String getUID()
	{
		HttpSession session = RequestHelper.getSession();
        return (String) session.getAttribute(Constants.ADMIN_UID);
	}
	
	/**
     * 获得当前后台用户登录的站点
     * @return
     */
    public static String getSiteNo()
    {
        HttpSession session = RequestHelper.getSession();
        return (String) session.getAttribute(Constants.ADMIN_SITENO);
    }
}
