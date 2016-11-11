package com.yizhishang.plat.domain;

import com.yizhishang.base.domain.DynaModel;


public class ArticleLabels extends DynaModel
{
	private static final long serialVersionUID = 1L;
	public int getId()
	{
		return getInt("id");
	}
	
	public void setId(int id)
	{
		set("id",id);
	}
	
	public String getName()
	{
		return getString("name");
	}
	
	public void setName(String name)
	{
		set("name",name);
	}
	
	public String getCreateDate()
	{
		return getString("create_date");
	}
	
	public void setCreateDate(String create_date)
	{
		set("create_date",create_date);
	}
	
	public String getCreateBy()
	{
		return getString("create_by");
	}
	
	public void setCreateBy(String create_by)
	{
		set("create_by",create_by);
	}
	
}
