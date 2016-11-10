package com.yizhishang.plat.domain;

import java.io.Serializable;
import java.util.List;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DataRow;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-17
 * 创建时间: 16:45:31
 */
public class Catalog extends DynaModel implements Serializable
{
	
	private static final long serialVersionUID = 4365773128538542564L;

	public int getId()
	{
		return getInt("catalog_id");
	}
	
	public void setId(int id)
	{
		set("catalog_id", id);
	}
	
	public int getParentId()
	{
		return getInt("parent_id");
	}
	
	public void setParentId(int parentId)
	{
		set("parent_id", parentId);
	}
	
	public String getName()
	{
		return getString("name");
	}
	
	public void setName(String name)
	{
		set("name", name);
	}
	
	public String getCatalogNo()
	{
		return getString("catalog_no");
	}
	
	public void setCatalogNo(String catalogNo)
	{
		set("catalog_no", catalogNo);
	}
	
	public String getSiteNo()
	{
		return getString("siteno");
	}
	
	public void setSiteNo(String siteNo)
	{
		set("siteno", siteNo);
	}
	
	public String getDescription()
	{
		return getString("description");
	}
	
	public void setDescription(String description)
	{
		set("description", description);
	}
	
	public int getOrderLine()
	{
		return getInt("orderline");
	}
	
	public void setOrderLine(int orderLine)
	{
		set("orderline", orderLine);
	}
	
	public int getLayer()
	{
		return getInt("layer");
	}
	
	public void setLayer(int layer)
	{
		set("layer", layer);
	}
	
	public String getRoute()
	{
		return getString("route");
	}
	
	public void setRoute(String route)
	{
		set("route", route);
	}
	
	public int getState()
	{
		return getInt("state");
	}
	
	public void setState(int state)
	{
		set("state", state);
	}
	
	public int getNeedPublish()
	{
		return getInt("need_publish");
	}
	
	public void setNeedPublish(int needPublish)
	{
		set("need_publish", needPublish);
	}
	
	public int getNeedApprove()
	{
		return getInt("need_approve");
	}
	
	public void setNeedApprove(int needApprove)
	{
		set("need_approve", needApprove);
	}
	
	public int getInheritMode()
	{
		return getInt("inherit_mode");
	}
	
	public void setInheritMode(int inheritMode)
	{
		set("inherit_mode", inheritMode);
	}
	
	public int getInheritField()
	{
		return getInt("inherit_field");
	}
	
	public void setInheritField(int inheritField)
	{
		set("inherit_field", inheritField);
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
	
	public int getPageType()
	{
		return getInt("page_type");
	}
	
	public void setPageType(int pageType)
	{
		set("page_type", pageType);
	}
	
	public String getLinkUrl()
	{
		return getString("link_url");
	}
	
	public void setLinkUrl(String linkUrl)
	{
		set("link_url", linkUrl);
	}
	
	public String getPublishPath()
	{
		return getString("publish_path");
	}
	
	public void setPublishPath(String publishPath)
	{
		set("publish_path", publishPath);
	}
	
	public String getFileType()
	{
		return getString("file_type");
	}
	
	public void setFileType(String fileType)
	{
		set("file_type", fileType);
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
	
	public String getSmallImage()
	{
		return getString("small_image");
	}
	
	public void setSmallImage(String smallImage)
	{
		set("small_image", smallImage);
	}
	
	public String getLargeImage()
	{
		return getString("large_image");
	}
	
	public void setLargeImage(String largeImage)
	{
		set("large_image", largeImage);
	}
	
	public int getType()
	{
		return getInt("type");
	}
	
	public void setType(int type)
	{
		set("type", type);
	}
	
	public int getUserRight()
	{
		return getInt("user_right");
	}
	
	public void setUserRight(int userRight)
	{
		set("user_right", userRight);
	}
	
	//栏目星级
	public int getColumnLevel()
	{
		return getInt("column_level");
	}
	
	public void setColumnLevel(int columnLevel)
	{
		set("column_level", columnLevel);
	}
	
	public int getChildrennum()
	{
		return getInt("childrennum");
	}
	
	public void setChildrennum(int childrennum)
	{
		set("childrennum", childrennum);
	}
	
	public int getAttribute()
	{
		return getInt("attribute");
	}
	
	public void setAttribute(int attribute)
	{
		set("attribute", attribute);
	}
	
	public int getSourceId()
	{
		return getInt("source_id");
	}
	
	public void setSourceId(int sourceId)
	{
		set("source_id", sourceId);
	}
	
	public String getExtField1()
	{
		return getString("ext_field1");
	}
	
	public void setExtField1(String ext_field1)
	{
		set("ext_field1", ext_field1);
	}
	
	public String getExtField2()
	{
		return getString("ext_field2");
	}
	
	public void setExtField2(String ext_field2)
	{
		set("ext_field2", ext_field2);
	}
	
	@SuppressWarnings("unchecked")
	public List<DataRow> getDataList()
	{
		return ((List<DataRow>) getObject("dataList"));
	}
	
	public void setDataList(List<DataRow> dataList)
	{
		set("dataList", dataList);
	}
}
