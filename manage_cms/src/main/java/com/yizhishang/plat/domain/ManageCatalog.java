package com.yizhishang.plat.domain;

import java.util.List;

import com.yizhishang.base.domain.DynaModel;

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
    
    @SuppressWarnings("unchecked")
    public List<ManageCatalog> getChildren()
    {
        return (List<ManageCatalog>) getObject("children");
    }
    
    public int getChildrennum()
    {
        return getInt("childrennum");
    }
    
    public String getCreateBy()
    {
        return getString("create_by");
    }
    
    public String getCreateDate()
    {
        return getString("create_date");
    }
    
    public String getDescription()
    {
        return getString("description");
    }
    
    public int getId()
    {
        return getInt("catalog_id");
    }
    
    public int getIsRoot()
    {
        return getInt("is_root");
    }
    
    public int getIsSystem()
    {
        return getInt("is_system");
    }
    
    public int getLayer()
    {
        return getInt("layer");
    }
    
    public String getLinkUrl()
    {
        return getString("link_url");
    }
    
    public String getModifiedBy()
    {
        return getString("modified_by");
    }
    
    public String getModifiedDate()
    {
        return getString("modified_date");
    }
    
    public String getName()
    {
        return getString("name");
    }
    
    public int getOrderLine()
    {
        return getInt("orderline");
    }
    
    public int getParentId()
    {
        return getInt("parent_id");
    }
    
    public String getRoute()
    {
        return getString("route");
    }
    
    public String getSiteNo()
    {
        return getString("siteno");
    }
    
    public String getSmallImage()
    {
        return getString("small_image");
    }
    
    public int getState()
    {
        return getInt("state");
    }
    
    public void setCatalogNo(String catalogNo)
    {
        set("catalog_no", catalogNo);
    }
    
    public void setChildren(List<ManageCatalog> children)
    {
        set("children", children);
    }
    
    public void setChildrennum(int childrennum)
    {
        set("childrennum", childrennum);
    }
    
    public void setCreateBy(String createBy)
    {
        set("create_by", createBy);
    }
    
    public void setCreateDate(String createDate)
    {
        set("create_date", createDate);
    }
    
    public void setDescription(String description)
    {
        set("description", description);
    }
    
    public void setId(int id)
    {
        set("catalog_id", id);
    }
    
    public void setIsRoot(int isRoot)
    {
        set("is_root", isRoot);
    }
    
    public void setIsSystem(int isSystem)
    {
        set("is_system", isSystem);
    }
    
    public void setLayer(int layer)
    {
        set("layer", layer);
    }
    
    public void setLinkUrl(String linkUrl)
    {
        set("link_url", linkUrl);
    }
    
    public void setModifiedBy(String modifiedBy)
    {
        set("modified_by", modifiedBy);
    }
    
    public void setModifiedDate(String modifiedDate)
    {
        set("modified_date", modifiedDate);
    }
    
    public void setName(String name)
    {
        set("name", name);
    }
    
    public void setOrderLine(int orderLine)
    {
        set("orderline", orderLine);
    }
    
    public void setParentId(int parentId)
    {
        set("parent_id", parentId);
    }

    public void setRoute(String route)
    {
        set("route", route);
    }
    
    public void setSiteNo(String siteNo)
    {
        set("siteno", siteNo);
    }
    
    public void setSmallImage(String smallImage)
    {
        set("small_image", smallImage);
    }
    
    public void setState(int state)
    {
        set("state", state);
    }

}
