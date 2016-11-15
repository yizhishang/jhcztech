package com.yizhishang.plat.domain;

import com.yizhishang.base.domain.DynaModel;

public class Group extends DynaModel
{

    public int getGroup_Id()
    {
        return getInt("group_id");
    }

    public void setGroup_Id(int group_Id)
    {
        set("group_id", group_Id);
    }

    public String getSiteNo()
    {
        return getString("siteno");
    }

    public void setSiteNo(String siteno)
    {
        set("siteno", siteno);
    }

    public String getDescription()
    {
        return getString("description");
    }

    public void setDescription(String description)
    {
        set("description", description);
    }

    public String getName()
    {
        return getString("name");
    }

    public void setName(String name)
    {
        set("name", name);
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
