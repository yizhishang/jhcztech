package com.jhcz.business.domain;

import com.jhcz.base.domain.DynaModel;

/**
 * 描述:  广告发布
 * 版权:	 Copyright (c) 2007
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-26
 * 创建时间: 10:46:22
 */
public class Ad extends DynaModel
{
    
    public void setId(int id)
    {
        set("ad_id", id);
    }
    
    public int getId()
    {
        return getInt("ad_id");
    }
    
    public void setSiteno(String siteno)
    {
        set("siteno", siteno);
    }
    
    public String getSiteno()
    {
        return getString("siteno");
    }
    
    public void setGroupId(String group_id)
    {
        set("group_id", group_id);
    }
    
    public String getGroupId()
    {
        return getString("group_id");
    }
    
    public void setType(String type)
    {
        set("type", type);
    }
    
    public String getType()
    {
        return getString("type");
    }
    
    public void setName(String name)
    {
        set("name", name);
    }
    
    public String getName()
    {
        return getString("name");
    }
    
    public void setUrl(String url)
    {
        set("url", url);
    }
    
    public String getUrl()
    {
        return getString("url");
    }
    
    public void setPicture(String picture)
    {
        set("picture", picture);
    }
    
    public String getPicture()
    {
        return getString("picture");
    }
    
    public void setDescription(String description)
    {
        set("description", description);
    }
    
    public String getDescription()
    {
        return getString("description");
    }
    
    public void setWidth(int width)
    {
        set("width", width);
    }
    
    public int getWidth()
    {
        return getInt("width");
    }
    
    public void setHeight(int height)
    {
        set("height", height);
    }
    
    public int getHeight()
    {
        return getInt("height");
    }
    
    public void setStartTime(String start_time)
    {
        set("start_time", start_time);
    }
    
    public String getStartTime()
    {
        return getString("start_time");
    }
    
    public void setEndTime(String end_time)
    {
        set("end_time", end_time);
    }
    
    public String getEndTime()
    {
        return getString("end_time");
    }
    
    public void setFileType(String file_type)
    {
        set("file_type", file_type);
    }
    
    public String getFileType()
    {
        return getString("file_type");
    }
    
    public void setOrderline(String orderline)
    {
        set("orderline", orderline);
    }
    
    public String getOrderline()
    {
        return getString("orderline");
    }
    
    public void setState(int state)
    {
        set("state", state);
    }
    
    public int getState()
    {
        return getInt("state");
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
    
    public void setFileState(String file_state)
    {
        set("file_state", file_state);
    }
    
    public String getFileState()
    {
        return getString("file_state");
    }
    
    public void setTemplate(String template)
    {
        set("template", template);
    }
    
    public String getTemplate()
    {
        return getString("template");
    }
    
    public void setTemplateSize(String template_size)
    {
        set("template_size", template_size);
    }
    
    public String getTemplateSize()
    {
        return getString("template_size");
    }
    
    public void setGroupName(String groupName)
    {
        set("groupname", groupName);
    }
    
    public String getGroupName()
    {
        return getString("groupname");
    }
}
