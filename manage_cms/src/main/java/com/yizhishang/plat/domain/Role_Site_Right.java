package com.yizhishang.plat.domain;

import com.yizhishang.base.domain.DynaModel;

/**
 * 描述:  
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2011-3-14
 * 创建时间: 下午08:40:31
 */
public class Role_Site_Right extends DynaModel
{

	private static final long serialVersionUID = 1L;
	public int getId()
	{
		return getInt("id");
	}
	
	public void setId(int id)
	{
		set("id", id);
	}
	
	public int getRole_id()
	{
		return getInt("role_id");
	}
	
	public void setRole_id(int role_id)
	{
		set("role_id", role_id);
	}
	
	public String getSiteNoList()
	{
		return getString("site_no_list");
	}
	
	public void setSiteNoList(String site_no_list)
	{
		set("site_no_list", site_no_list);
	}
	
}
