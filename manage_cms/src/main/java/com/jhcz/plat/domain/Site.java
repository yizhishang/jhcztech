package com.jhcz.plat.domain;

import com.jhcz.base.domain.DynaModel;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-23
 * 创建时间: 16:13:06
 */
public class Site extends DynaModel
{
	public static int STATE_CLOSE = 0;
    public static int STATE_OPEN = 1;

    public int getId()
    {
        return getInt("id");
    }

    public void setId(int id)
    {
        set("id", id);
    }

    public String getSiteNo()
    {
        return getString("siteno");
    }

    public void setSiteNo(String siteNo)
    {
        set("siteno", siteNo);
    }

    public String getName()
    {
        return getString("name");
    }

    public void setName(String name)
    {
        set("name", name);
    }

    public int getState()
    {
        return getInt("state");
    }

    public void setState(int state)
    {
        set("state", state);
    }

    public int getIsMain()
    {
        return getInt("is_main");
    }

    public void setIsMain(int isMain)
    {
        set("is_main", isMain);
    }

    public String getPublishPath()
    {
        return getString("publish_path");
    }

    public void setPublishPath(String path)
    {
        set("publish_path",path); 
    }

    public String getDescription()
    {
        return getString("description");
    }

    public void setDescription(String description)
    {
        set("description", description);
    }

    public String getCreateBy()
    {
        return getString("create_by");
    }

    public void setCreateBy(String createBy)
    {
        set("create_by", createBy);
    }

    public String getCreateDate()
    {
        return getString("create_date");
    }

    public void setCreateDate(String createDate)
    {
        set("create_date", createDate);
    }

    public String getModifiedBy()
    {
        return getString("modified_by");
    }

    public void setModifiedBy(String modifiedBy)
    {
        set("modified_by", modifiedBy);
    }

    public String getModifiedDate()
    {
        return getString("modified_date");
    }

    public void setModifiedDate(String modifiedDate)
    {
        set("modified_date", modifiedDate);
    }

}
