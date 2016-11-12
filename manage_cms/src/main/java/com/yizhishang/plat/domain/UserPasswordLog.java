package com.yizhishang.plat.domain;

import com.yizhishang.base.domain.DynaModel;

/**
 * 描述:  用户密码历史记录表
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-12
 * 创建时间: 16:31:21
 */
public class UserPasswordLog extends DynaModel
{
	
	
	public int getLogId()
	{
		return getInt("log_id");
	}
	
	public void setLogId(int id)
	{
		set("log_id",id);
	}
	
	public String getPassword()
	{
		return getString("password");
	}
	
	public void setPassword(String password)
	{
		set("password",password);
	}
	
	public String getCreateDate()
	{
		return getString("create_date");
	}
	
	public void setCreateDate(String create_date)
	{
		set("create_date",create_date);
	}
	
	public String getDescription()
	{
		return getString("description");
	}
	
	public void setDescription(String description)
	{
		set("description",description);
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
