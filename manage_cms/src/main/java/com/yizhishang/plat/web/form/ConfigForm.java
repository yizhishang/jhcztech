package com.yizhishang.plat.web.form;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-3
 * 创建时间: 17:49:42
 */
public class ConfigForm
{

    private int id = 0;

    private String name = "";

    private String caption = "";

    private String value = "";

    private String description = "";

    private int isSystem = 0;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCaption()
    {
        return caption;
    }

    public void setCaption(String caption)
    {
        this.caption = caption;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getIsSystem()
    {
        return isSystem;
    }

    public void setIsSystem(int isSystem)
    {
        this.isSystem = isSystem;
    }
}
