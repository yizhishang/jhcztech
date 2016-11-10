package com.yizhishang.plat.domain;

import com.yizhishang.base.domain.DynaModel;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-20
 * 创建时间: 11:43:46
 */
public class ArticleKeyword extends DynaModel
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

    public String getCreateDate()
    {
        return getString("create_date");
    }

    public void setCreateDate(String createDate)
    {
        set("create_date", createDate);
    }
}
