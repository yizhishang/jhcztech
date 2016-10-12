package com.jhcz.plat.domain;

import com.jhcz.base.domain.DynaModel;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-23
 * 创建时间: 16:31:21
 */
public class Log extends DynaModel
{
	
	public int getId()
	{
		return getInt("log_id");
	}
	
	public void setId(int id)
	{
		set("log_id", id);
	}
	
	public String getSiteNo()
	{
		return getString("siteno");
	}
	
	public void setSiteNo(String siteNo)
	{
		set("siteno", siteNo);
	}
	
	public String getOperate()
	{
		return getString("operate");
	}
	
	public void setOperate(String operate)
	{
		set("operate", operate);
	}
	
	public String getDescription()
	{
		return getString("description");
	}
	
	public void setDescription(String description)
	{
		set("description", description);
	}
	
	public String getIp()
	{
		return getString("ip");
	}
	
	public void setIp(String ip)
	{
		set("ip", ip);
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
	
	public String getBranchno()
	{
		return getString("branchno");
	}
	
	public void setBranchno(String branchno)
	{
		set("branchno", branchno);
	}
	
	public int getCatalogId()
	{
		return getInt("catalog_id");
	}
	
	public void setCatalogId(int catalogId)
	{
		set("catalog_id", catalogId);
	}
	
}
