package com.yizhishang.plat.domain;

import com.yizhishang.base.domain.DynaModel;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-21
 * 创建时间: 15:13:54
 */
public class Link extends DynaModel
{
	private static final long serialVersionUID = 1L;
    public int getLinkid()
    {
        return getInt("lnk_id");
    }

    public void setLinkid(int lnkid)
    {
        set("lnk_id", lnkid);
    }

    public String getSiteno()
    {
        return getString("siteno");
    }

    public void setSiteno(String siteno)
    {
        set("siteno", siteno);
    }


    public int getGroup_id()
    {
        return getInt("group_id");
    }

public void setGroup_id(int group_id)
    {
         set("group_id",group_id);
    }

    public String getName()
    {
        return getString("name");
    }

    public void setName(String name)
    {
        set("name", name);
    }

    public String getUrl()
    {
        return getString("url");
    }

    public void setUrl(String url)
    {
        set("url", url);
    }

    public String getPicture()
    {
        return getString("picture");
    }

    public void setPicture(String picture)
    {
        set("picture", picture);
    }

    public int getOrderline()
    {
        return getInt("orderline");
    }

    public void setOrderline(int orderline)
    {
        set("orderline", orderline);
    }

    public int getState()
    {
        return getInt("state");
    }

    public void setState(int state)
    {
        set("state", state);
    }

    public String getCreate_by()
    {
        return getString("create_by");
    }

    public void setCreate_by(String create_by)
    {
        set("create_by", create_by);
    }

    public String getCreate_date()
    {
        return getString("create_date");
    }

    public void setCreate_date(String create_date)
    {
        set("create_date", create_date);
    }

    public String getModified_by()
    {
        return getString("modified_by");
    }

    public void setModified_by(String modified_by)
    {
        set("modified_by", modified_by);
    }

    public String getModified_date()
    {
        return getString("modified_date");
    }

    public void setModified_date(String modified_date)
    {
        set("modified_date", modified_date);
    }


}
