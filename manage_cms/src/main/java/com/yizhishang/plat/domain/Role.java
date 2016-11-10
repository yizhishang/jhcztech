package com.yizhishang.plat.domain;

import com.yizhishang.base.domain.DynaModel;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-9
 * 创建时间: 14:04:30
 */
public class Role extends DynaModel
{
	
	public int getId()
	{
		return getInt("id");
	}
	
	public void setId(int id)
	{
		set("id", id);
	}
	
	public String getRoleNo()
	{
		return getString("roleno");
	}
	
	public void setRoleNo(String roleNo)
	{
		set("roleno", roleNo);
	}
	
	public String getName()
	{
		return getString("name");
	}
	
	public void setName(String name)
	{
		set("name", name);
	}
	
	public String getSiteNo()
	{
		return getString("siteno");
	}
	
	public void setSiteNo(String siteNo)
	{
		set("siteno", siteNo);
	}
	
	public int getIsSystem()
	{
		return getInt("is_system");
	}
	
	public void setIsSystem(int isSystem)
	{
		set("is_system", isSystem);
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
	
	public String getDescription()
	{
		return getString("description");
	}
	
	public void setDescription(String description)
	{
		set("description", description);
	}
	
}
