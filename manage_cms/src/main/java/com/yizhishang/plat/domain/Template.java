package com.yizhishang.plat.domain;

import com.yizhishang.base.domain.DynaModel;

/**
 * 描述:  
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 
 * 版本:	 1.0
 * 创建日期: 2010-1-18
 * 创建时间: 下午01:11:11
 */
public class Template extends DynaModel
{
	
	
	public int getId()
	{
		return getInt("id");
	}
	
	public void setId(int id)
	{
		set("id", id);
	}
	
	public String getName()
	{
		return getString("name");
	}
	
	public void setName(String name)
	{
		set("name", name);
	}
	
	public int getCatalogId()
	{
		return getInt("catalog_id");
	}
	
	public void setCatalogId(int catalogId)
	{
		set("catalog_id", catalogId);
	}
	
	public String getFilePath()
	{
		return getString("filepath");
	}
	
	public void setFilePath(String filePath)
	{
		set("filepath", filePath);
	}
	
	public String getType()
	{
		return getString("type");
	}
	
	public void setType(String type)
	{
		set("type", type);
	}
	
	public int getState()
	{
		return getInt("state");
	}
	
	public void setState(int state)
	{
		set("state", state);
	}
	
	public String getSiteNo()
	{
		return getString("siteno");
	}
	
	public void setSiteNo(String siteno)
	{
		set("siteno", siteno);
	}
	
	public String getCreateBy()
	{
		return getString("create_by");
	}
	
	public void setCreateBy(String create_by)
	{
		set("create_by", create_by);
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
	
	public String getContent()
	{
		return getString("content");
	}
	
	public void setContent(String content)
	{
		set("content", content);
	}
	
	public String getEncoding()
	{
		return getString("encoding");
	}
	
	public void setEncoding(String encoding)
	{
		set("encoding", encoding);
	}
}
