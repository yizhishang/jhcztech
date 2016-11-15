package com.yizhishang.plat.domain;

import com.yizhishang.base.domain.DynaModel;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2006-12-26
 * 创建时间: 14:05:08
 */
public class Comment extends DynaModel
{

    //文章评论类型
    public int ARTICLE_TYPE = 0;

    public int getId()
    {
        return getInt("id");
    }

    public void setId(int id)
    {
        set("id", id);
    }

    public String getObjectId()
    {
        return getString("object_id");
    }

    public void setObjectId(String objectId)
    {
        set("object_id", objectId);
    }

    public int getType()
    {
        return getInt("type");
    }

    public void setType(int type)
    {
        set("type", type);
    }

    public String getSiteNo()
    {
        return getString("siteno");
    }

    public void setSiteNo(String siteNo)
    {
        set("siteno", siteNo);
    }

    public String getTitle()
    {
        return getString("title");
    }

    public void setTitle(String title)
    {
        set("title", title);
    }

    public String getContent()
    {
        return getString("content");
    }

    public void setContent(String content)
    {
        set("content", content);
    }

    public String getIp()
    {
        return getString("ip");
    }

    public void setIp(String ip)
    {
        set("ip", ip);
    }

    public String getCreator()
    {
        return getString("creator");
    }

    public void setCreator(String creator)
    {
        set("creator", creator);
    }

    public String getCreateTime()
    {
        return getString("create_time");
    }

    public void setCreateTime(String createTime)
    {
        set("create_time", createTime);
    }
}
