package com.yizhishang.plat.domain;

import java.util.List;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.util.StringHelper;

/**
 * 描述: 菜单栏目对象
 * 版权: Copyright (c) 2015 
 * 公司:  
 * 作者: 袁永君 
 * 版本: 1.0 
 * 创建日期: 2009-12-16 
 * 创建时间: 下午10:34:34
 */
public class MenuCatalog extends DynaModel
{

	
    public String getCatalogNo()
    {
        return getString("catalog_no");
    }
    
    @SuppressWarnings("unchecked")
    public List<DynaModel> getChildren()
    {
        return (List<DynaModel>) toMap().get("children");
    }
    
    public String getDescription()
    {
        return getString("description");
    }
    
    public String getDispImage()
    {
        String dispImage = getString("disp_image");
        if (StringHelper.isEmpty(dispImage))
        {
            dispImage = "ico07.gif";
        }
        return dispImage;
    }
    
    public String getEntryPath()
    {
        return getString("entry_path");
    }
    
    public String getName()
    {
        return getString("name");
    }
    
    public int getOrderline()
    {
        return getInt("orderline");
    }
    
    public String getParentNo()
    {
        return getString("parent_no");
    }
    
    public String getSiteno()
    {
        return getString("siteno");
    }
    
    public int getState()
    {
        return getInt("state");
    }
    
    public void setCatalogNo(String catalogNo)
    {
        this.set("catalog_no", catalogNo);
    }
    
    public void setChildren(List<DynaModel> children)
    {
        this.set("children", children);
    }
    
    public void setDescription(String description)
    {
        this.set("description", description);
    }
    
    public void setDispImage(String dispImage)
    {
        this.set("disp_image", dispImage);
    }
    
    public void setEntryPath(String entryPath)
    {
        this.set("entry_path", entryPath);
    }
    
    public void setName(String name)
    {
        this.set("name", name);
    }
    
    public void setOrderline(int orderline)
    {
        this.set("orderline", orderline);
    }
    
    public void setParentNo(String parentNo)
    {
        this.set("parent_no", parentNo);
    }
    
    public void setSiteno(String siteno)
    {
        this.set("siteno", siteno);
    }
    
    public void setState(int state)
    {
        this.set("state", state);
    }
}
