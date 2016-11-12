package com.yizhishang.plat.domain;

import com.yizhishang.base.domain.DynaModel;

/**
 * 描述: 用户组
 * 版权: Copyright (c) 2015 
 * 公司:  
 * 作者: 袁永君 
 * 版本: 1.0 
 * 创建日期: Feb 16, 2009 
 * 创建时间: 5:49:33 PM
 */
public class ClientGroup extends DynaModel
{
	
	/**
	 * 
	 */
	


	public int getId()
	{
		return getInt("id");
	}
	
	
	public String getSiteNo()
	{
		return getString("siteno");
	}
	
	
	public String getDescription()
	{
		return getString("description");
	}
	
	
	public String getName()
	{
		return getString("name");
	}
	
	
	public String getCreate_by()
	{
		return getString("create_by");
	}
	
	
	public String getCreate_date()
	{
		return getString("create_date");
	}
	
	
	public String getModified_by()
	{
		return getString("modified_by");
	}
	
	
	public String getModified_date()
	{
		return getString("modified_date");
	}
	
	
	public void setId(int id)
	{
		set("id", id);
	}
	
	
	public void setSiteNo(String siteno)
	{
		set("siteno", siteno);
	}
	
	
	public void setDescription(String description)
	{
		set("description", description);
	}
	
	
	public void setName(String name)
	{
		set("name", name);
	}
	
	
	public void setCreate_by(String create_by)
	{
		set("create_by", create_by);
	}
	
	
	public void setCreate_date(String create_date)
	{
		set("create_date", create_date);
	}
	
	
	public void setModified_by(String modified_by)
	{
		set("modified_by", modified_by);
	}
	
	
	public void setModified_date(String modified_date)
	{
		set("modified_date", modified_date);
	}
}
