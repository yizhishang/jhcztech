package com.yizhishang.plat.domain;

import com.yizhishang.base.domain.DynaModel;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:  袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-23
 * 创建时间: 15:53:51
 */
public class Role_Catalog_Right extends DynaModel
{

	
	
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
	
	
	public String getCatalogIdList()
	{
		return getString("catalog_id_list");
	}
	
	
	public void setCatalogIdList(String catalog_id_list)
	{
		set("catalog_id_list", catalog_id_list);
	}
	
	
	public String getSiteNo()
	{
		return getString("siteno");
	}
	
	
	public void setSiteNo(String siteno)
	{
		set("siteno", siteno);
	}
	
	
	public String getType()
	{
		return getString("type");
	}
	
	
	public void setType(String type)
	{
		set("type", type);
	}
}
