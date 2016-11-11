package com.yizhishang.base.domain;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-2
 * 创建时间: 17:00:38
 */
public class Config extends DynaModel
{
	private static final long serialVersionUID = 1L;
	public static int IS_SYSTEM_VALUE = 1;
    public static int IS_NOT_SYSTEM_VALUE = 0;

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

    public String getCaption()
    {
        return getString("caption");
    }

    public void setCaption(String caption)
    {
        set("caption", caption);
    }

    public String getValue()
    {
        return getString("cur_value");
    }

    public void setValue(String value)
    {
        set("cur_value", value);
    }

    public String getDescription()
    {
        return getString("description");
    }

    public void setDescription(String description)
    {
        set("description", description);
    }

    public int getIsSystem()
    {
        return getInt("is_system");
    }

    public void setIsSystem(int isSystem)
    {
        set("is_system", isSystem);
    }

    public void setSiteNo(String siteNo)
    {
        set("siteno", siteNo);
    }

    public String getSiteNo()
    {
        return getString("siteno");
    }
}
