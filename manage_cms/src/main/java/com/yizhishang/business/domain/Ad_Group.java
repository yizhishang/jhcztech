package com.yizhishang.business.domain;

import com.yizhishang.base.domain.DynaModel;

/**
 * 描述:  广告发布组
 * 版权:	 Copyright (c) 2007
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-27
 * 创建时间: 19:55:18
 */
public class Ad_Group extends DynaModel
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

    public String getSiteNo()
    {
        return getString("siteno");
    }

    public void setSiteNo(String siteno)
    {
        set("siteno", siteno);
    }

    public String getCreateBy()
    {
        return getString("create_by");
    }

    public void setCreateBy(String create_by)
    {
        set("create_by", create_by);
    }

    public String getCreateDate()
    {
        return getString("create_date");
    }

    public void setCreateDate(String create_date)
    {
        set("create_date", create_date);
    }

    public String getModifiedBy()
    {
        return getString("modified_by");
    }

    public void setModifiedBy(String modified_by)
    {
        set("modified_by", modified_by);
    }

    public String getModifiedDate()
    {
        return getString("modified_date");
    }

    public void setModifiedDate(String modified_date)
    {
        set("modified_date", modified_date);
    }

    public String getOrderline()
    {
        return getString("orderline");
    }

    public void setOrderline(String orderline)
    {
        set("orderline", orderline);
    }
}
