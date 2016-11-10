package com.yizhishang.plat.service;

import java.util.List;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.jdbc.DataRow;

/**
 * 描述:  
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 
 * 版本:	 1.0
 * 创建日期: 2010-3-10
 * 创建时间: 上午10:43:43
 */
public interface PublishPlanService
{
	
	
	public void addPublishPlan(DataRow data);
	
	
	public void delAllPublishPlan();
	
	
	public void delPublishPlan(int id);
	
	
	public void editPublishPlan(DataRow data);
	
	
	public DBPage findPublishPlan(int curPage, int numPerPage, String catalogId, String name);
	
	
	public DataRow findPublishPlanById(String id);
	
	
    public List<Object> findPublishPlanByType(String type);
}
