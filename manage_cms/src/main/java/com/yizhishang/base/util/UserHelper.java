package com.yizhishang.base.util;

import com.yizhishang.plat.Constants;

import javax.servlet.http.HttpSession;

public class UserHelper
{

    /**
     * 获得当前后登录用户的userId
     *
     * @return
     */
    public static int getUserId()
    {
        HttpSession session = RequestHelper.getSession();
        return ((Integer) session.getAttribute(Constants.ADMIN_USER_ID)).intValue();
    }

    public static String getUserName()
    {
        HttpSession session = RequestHelper.getSession();
        return ((String) session.getAttribute(Constants.ADMIN_USER_NAME));
    }

    /**
     * 获得当前后台登录用户的UID
     *
     * @return
     */
    public static String getUID()
    {
        HttpSession session = RequestHelper.getSession();
        return (String) session.getAttribute(Constants.ADMIN_UID);
    }

    /**
     * 获得当前后台用户登录的站点
     *
     * @return
     */
    public static String getSiteNo()
    {
        HttpSession session = RequestHelper.getSession();
        return (String) session.getAttribute(Constants.ADMIN_SITENO);
    }

    /**
     * 获得当前后台登录用户的营业部编号
     *
     * @return
     */
    public static String getUserBranch()
    {
        HttpSession session = RequestHelper.getSession();
        return (String) session.getAttribute(Constants.USER_BRANCHNO);
    }
}
