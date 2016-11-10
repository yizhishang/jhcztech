package com.yizhishang.plat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.jdbc.DataRow;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.service.PublishPlanService;

/**
 * 描述:  
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 
 * 版本:	 1.0
 * 创建日期: 2010-3-10
 * 创建时间: 上午10:45:16
 */
@Service
public class PublishPlanServiceImpl extends BaseService implements PublishPlanService
{
	
	
	@Override
    public void addPublishPlan(DataRow data)
	{
		String id = getSeqValue("T_PUBLISH_PLAN");
		data.set("id", id);
		getJdbcTemplate().insert("T_PUBLISH_PLAN", data);
	}
	
	@Override
    public void delAllPublishPlan()
	{
		getJdbcTemplate().update("DELETE FROM T_PUBLISH_PLAN");
	}
	
	@Override
    public void delPublishPlan(int id)
	{
		getJdbcTemplate().delete("T_PUBLISH_PLAN", "id", new Integer(id));
	}
	
	@Override
    public void editPublishPlan(DataRow data)
	{
		getJdbcTemplate().update("T_PUBLISH_PLAN", data, "id", data.getString("id"));
	}
	
	@Override
    public DBPage findPublishPlan(int curPage, int numPerPage, String catalogId, String name)
	{
        ArrayList<Object> argList = new ArrayList<Object>();
		StringBuffer strBuf = new StringBuffer("SELECT A.*,B.NAME FROM T_PUBLISH_PLAN A,T_CATALOG B WHERE A.CATALOG_ID = B.CATALOG_ID");
		if (StringHelper.isNotEmpty(catalogId))
		{
			strBuf.append(" AND A.CATALOG_ID = ?");
			argList.add(catalogId);
		}
		if (StringHelper.isNotEmpty(name))
		{
			strBuf.append(" AND B.NAME LIKE ?");
			argList.add("%" + name + "%");
		}
		strBuf.append("  ORDER BY B.ORDERLINE");
		
		return getJdbcTemplate().queryPage(strBuf.toString(), argList.toArray(), curPage, numPerPage);
	}
	
	@Override
    public DataRow findPublishPlanById(String id)
	{
        ArrayList<Object> argList = new ArrayList<Object>();
		String sql = "SELECT A.*,B.NAME FROM T_PUBLISH_PLAN A,T_CATALOG B WHERE A.CATALOG_ID = B.CATALOG_ID AND A.ID = ?";
		argList.add(id);
		return getJdbcTemplate().queryMap(sql, argList.toArray());
	}
	
	@Override
    public List<Object> findPublishPlanByType(String type)
	{
        ArrayList<Object> argList = new ArrayList<Object>();
		String sql = "SELECT * FROM T_PUBLISH_PLAN WHERE TYPE = ?";
		argList.add(type);
		return getJdbcTemplate().query(sql, argList.toArray());
	}
}
