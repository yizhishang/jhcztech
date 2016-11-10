package com.yizhishang.plat.domain;

import com.yizhishang.base.domain.DynaModel;

/**
 * 描述:  关键字过滤
 * 版权:	 Copyright (c) 2007
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-6-4
 * 创建时间: 11:13:06
 */
public class KeyWordFilter extends DynaModel
{
    public void setId(int filter_id)
    {
        set("filter_id", filter_id);
    }

    public int getId()
    {
        return getInt("filter_id");
    }

    public void setKeyword(String keyword)
    {
        set("keyword", keyword);
    }

    public String getkeyword()
    {
        return getString("keyword");
    }

    public void setSiteNo(String siteno)
    {
        set("siteno", siteno);
    }

    public String getSiteNo()
    {
        return getString("siteno");
    }

    public void setCreateBy(String create_by)
    {
        set("create_by", create_by);
    }

    public String getCreateBy()
    {
        return getString("create_by");
    }

    public void setCreateDate(String create_date)
    {
        set("create_date", create_date);
    }

    public String getCreateDate()
    {
        return getString("create_date");
    }

    public void setModifiedBy(String modified_by)
    {
        set("modified_by", modified_by);
    }

    public String getModifiedBy()
    {
        return getString("modified_by");
    }

    public void setModifiedDate(String modified_date)
    {
        set("modified_date", modified_date);
    }

    public String getModifiedDate()
    {
        return getString("modified_date");
    }

    public void setState(String state)
    {
        set("state", state);
    }

    public String getState()
    {
        return getString("state");
    }
}
