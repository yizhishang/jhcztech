package com.yizhishang.plat.domain;

import com.yizhishang.base.domain.DynaModel;

import java.util.List;

/**
 * 描述: 功能栏目表
 * 版权: Copyright (c) 2015
 * 公司:
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2010-1-13
 * 创建时间: 下午02:54:48
 */
public class ManageCatalog extends DynaModel
{

    public String getCatalogNo()
    {
        return getString("catalog_no");
    }

    public void setCatalogNo(String catalogNo)
    {
        set("catalog_no", catalogNo);
    }

    @SuppressWarnings("unchecked")
    public List<ManageCatalog> getChildren()
    {
        return (List<ManageCatalog>) getObject("children");
    }

    public void setChildren(List<ManageCatalog> children)
    {
        set("children", children);
    }

    public int getChildrennum()
    {
        return getInt("childrennum");
    }

    public void setChildrennum(int childrennum)
    {
        set("childrennum", childrennum);
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

    public String getDescription()
    {
        return getString("description");
    }

    public void setDescription(String description)
    {
        set("description", description);
    }

    public int getId()
    {
        return getInt("catalog_id");
    }

    public void setId(int id)
    {
        set("catalog_id", id);
    }

    public int getIsRoot()
    {
        return getInt("is_root");
    }

    public void setIsRoot(int isRoot)
    {
        set("is_root", isRoot);
    }

    public int getIsSystem()
    {
        return getInt("is_system");
    }

    public void setIsSystem(int isSystem)
    {
        set("is_system", isSystem);
    }

    public int getLayer()
    {
        return getInt("layer");
    }

    public void setLayer(int layer)
    {
        set("layer", layer);
    }

    public String getLinkUrl()
    {
        return getString("link_url");
    }

    public void setLinkUrl(String linkUrl)
    {
        set("link_url", linkUrl);
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

    public String getName()
    {
        return getString("name");
    }

    public void setName(String name)
    {
        set("name", name);
    }

    public int getOrderLine()
    {
        return getInt("orderline");
    }

    public void setOrderLine(int orderLine)
    {
        set("orderline", orderLine);
    }

    public int getParentId()
    {
        return getInt("parent_id");
    }

    public void setParentId(int parentId)
    {
        set("parent_id", parentId);
    }

    public String getRoute()
    {
        return getString("route");
    }

    public void setRoute(String route)
    {
        set("route", route);
    }

    public String getSiteNo()
    {
        return getString("siteno");
    }

    public void setSiteNo(String siteNo)
    {
        set("siteno", siteNo);
    }

    public String getSmallImage()
    {
        return getString("small_image");
    }

    public void setSmallImage(String smallImage)
    {
        set("small_image", smallImage);
    }

    public int getState()
    {
        return getInt("state");
    }

    public void setState(int state)
    {
        set("state", state);
    }

}
