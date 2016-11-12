package com.yizhishang.plat.domain;

import com.yizhishang.base.domain.DynaModel;

/**
 * 描述: 信息来源MODEL
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-21
 * 创建时间: 10:10:13
 */
public class Article_Source extends DynaModel
{
    public int getId()
    {
        return getInt("id");
    }

    public void setId(int id)
    {

        set("id", id);
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

    public String getSiteno()
    {

        return getString("siteno");
    }

    public void setSiteno(String siteno)
    {
        set("siteno", siteno);
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
