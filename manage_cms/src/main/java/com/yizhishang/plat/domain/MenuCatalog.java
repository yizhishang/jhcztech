package com.yizhishang.plat.domain;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.util.StringHelper;

import java.util.List;

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

    public void setCatalogNo(String catalogNo)
    {
        this.set("catalog_no", catalogNo);
    }

    @SuppressWarnings("unchecked")
    public List<DynaModel> getChildren()
    {
        return (List<DynaModel>) toMap().get("children");
    }

    public void setChildren(List<DynaModel> children)
    {
        this.set("children", children);
    }

    public String getDescription()
    {
        return getString("description");
    }

    public void setDescription(String description)
    {
        this.set("description", description);
    }

    public String getDispImage()
    {
        String dispImage = getString("disp_image");
        if (StringHelper.isEmpty(dispImage)) {
            dispImage = "ico07.gif";
        }
        return dispImage;
    }

    public void setDispImage(String dispImage)
    {
        this.set("disp_image", dispImage);
    }

    public String getEntryPath()
    {
        return getString("entry_path");
    }

    public void setEntryPath(String entryPath)
    {
        this.set("entry_path", entryPath);
    }

    public String getName()
    {
        return getString("name");
    }

    public void setName(String name)
    {
        this.set("name", name);
    }

    public int getOrderline()
    {
        return getInt("orderline");
    }

    public void setOrderline(int orderline)
    {
        this.set("orderline", orderline);
    }

    public String getParentNo()
    {
        return getString("parent_no");
    }

    public void setParentNo(String parentNo)
    {
        this.set("parent_no", parentNo);
    }

    public String getSiteno()
    {
        return getString("siteno");
    }

    public void setSiteno(String siteno)
    {
        this.set("siteno", siteno);
    }

    public int getState()
    {
        return getInt("state");
    }

    public void setState(int state)
    {
        this.set("state", state);
    }
}
