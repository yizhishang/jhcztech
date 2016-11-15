package com.yizhishang.plat.domain;

import com.yizhishang.base.domain.DynaModel;

/**
 * 描述:
 * 版权:	 Copyright (c) 2007
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-4-7
 * 创建时间: 13:05:01
 */
public class SurveyLog extends DynaModel
{

    public int getId()
    {
        return getInt("log_num");
    }

    public void setId(int log_num)
    {
        set("log_num", log_num);
    }

    public String getClientID()
    {
        return getString("client_id");
    }

    public void setClientID(String client_id)
    {
        set("client_id", client_id);
    }

    public String getClientName()
    {
        return getString("client_name");
    }

    public void setClientName(String client_name)
    {
        set("client_name", client_name);
    }

    public String getPostType()
    {
        return getString("posttype");
    }

    public void setPostType(String posttype)
    {
        set("posttype", posttype);
    }

    public String getClientType()
    {
        return getString("client_type");
    }

    public void setClientType(String client_type)
    {
        set("client_type", client_type);
    }

    public String getIp()
    {
        return getString("ip_address");
    }

    public void setIp(String ip_address)
    {
        set("ip_address", ip_address);
    }

    public String getUserId()
    {
        return getString("userid");
    }

    public void setUserId(String userId)
    {
        set("userid", userId);
    }

    public String getSubmitted()
    {
        return getString("submitted");
    }

    public void setSubmitted(String submitted)
    {
        set("submitted", submitted);
    }

    public String getSubmitDate()
    {
        return getString("submit_date");
    }

    public void setSubmitDate(String submit_date)
    {
        set("submit_date", submit_date);
    }

    public String getSiteNo()
    {
        return getString("siteno");
    }

    public void setSiteNo(String siteno)
    {
        set("siteno", siteno);
    }
}
