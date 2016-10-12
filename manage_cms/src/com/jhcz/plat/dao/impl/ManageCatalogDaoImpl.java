package com.jhcz.plat.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.jhcz.base.config.Configuration;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.jdbc.JdbcTemplate;
import com.jhcz.base.util.StringHelper;
import com.jhcz.plat.dao.BaseDao;
import com.jhcz.plat.dao.ManageCatalogDao;
import com.jhcz.plat.domain.ManageCatalog;

/**
 * 描述:
 * 版权:	 Copyright (c) 2007
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-3-27
 * 创建时间: 11:00:27
 */
@Repository
public class ManageCatalogDaoImpl extends BaseDao implements ManageCatalogDao
{
	
	private static Logger logger = Logger.getLogger(ManageCatalogDaoImpl.class);
	
	@Override
    public void addCatalog(ManageCatalog catalog)
	{
		JdbcTemplate jdbcTempate = getJdbcTemplate();
		DataRow dataRow = new DataRow();
		dataRow.putAll(catalog.toMap());
		jdbcTempate.insert("T_MANAGE_CATALOG", dataRow);
		if (1 == Configuration.getInt("system.isAutoIncrement"))
		{
			try
			{
				catalog.setId(Integer.parseInt(jdbcTempate.getGeneratedKeys()));
			}
			catch (Exception ex)
			{
				
			}
		}
	}
	
	@Override
    public void deleteCatalog(int catalogId)
	{
		getJdbcTemplate().delete("T_MANAGE_CATALOG", "catalog_id", new Integer(catalogId));
	}
	
	@Override
    public void deleteCatalog(int catalogId, String siteno)
	{
		ArrayList<Object> argList = new ArrayList<Object>();
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("delete from T_MANAGE_CATALOG where catalog_id=? and siteno=?");
		argList.add(new Integer(catalogId));
		argList.add(siteno);
		getJdbcTemplate().update(sqlBuf.toString(), argList.toArray());
	}
	
	@Override
    public ManageCatalog findCatalogById(int catalogId)
	{
		String sql = "select * from T_MANAGE_CATALOG where catalog_id=?";
		DataRow dataRow = getJdbcTemplate().queryMap(sql, new Object[] { new Integer(catalogId) });
		if (dataRow == null)
			return null;
		ManageCatalog catalog = new ManageCatalog();
		catalog.fromMap(dataRow);
		return catalog;
	}
	
	@Override
    public ManageCatalog findCatalogById(int catalogId, String siteno)
	{
		String sql = "select * from T_MANAGE_CATALOG where catalog_id=?";
		DataRow dataRow = getJdbcTemplate().queryMap(sql, new Object[] { new Integer(catalogId) });
		if (dataRow == null)
			return null;
		ManageCatalog catalog = new ManageCatalog();
		catalog.fromMap(dataRow);
		return catalog;
	}
	
	@Override
    public List<Object> findCatalogInfoByParentId(int parentId, String siteno)
	{
		ArrayList<Object> argList = new ArrayList<Object>();
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("select * from t_manage_catalog where state='1' ");
		if (parentId != 0)
		{
			sqlBuf.append(" and parent_id=?");
			argList.add(new Integer(parentId));
		}
		if (StringHelper.isNotEmpty(siteno))
		{
			sqlBuf.append(" and siteno=?");
			argList.add(siteno);
		}
		sqlBuf.append(" order by orderline");
        List<Object> list = getJdbcTemplate().query(sqlBuf.toString(), argList.toArray());
        List<Object> rlist = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++)
		{
			DataRow dr = (DataRow) list.get(i);
			ManageCatalog menuCatalog = new ManageCatalog();
			menuCatalog.fromMap(dr);
			rlist.add(menuCatalog);
		}
		return rlist;
	}
	
	@Override
    public List<Object> findCatalogLikeRoute(int catalogId, String siteno)
	{
		ArrayList<Object> argList = new ArrayList<Object>();
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("select * from t_manage_catalog where state='1' ");
		if (catalogId != 0)
		{
			sqlBuf.append(" and route like ?");
			if (catalogId == 1)
			{
				argList.add("1|%");
			}
			else
			{
				argList.add("%|" + catalogId + "|%");
			}
		}
		if (StringHelper.isNotEmpty(siteno))
		{
			sqlBuf.append(" and siteno = ?");
			argList.add(siteno);
		}
		sqlBuf.append(" order by orderline");
		List<Object> list = getJdbcTemplate().query(sqlBuf.toString(), argList.toArray());
        List<Object> rlist = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++)
		{
			DataRow dr = (DataRow) list.get(i);
			ManageCatalog menuCatalog = new ManageCatalog();
			menuCatalog.fromMap(dr);
            rlist.add(menuCatalog);
		}
		return rlist;
	}
	
	@Override
    public List<Object> findChildrenById(int catalogId)
	{
		String sql = "select * from T_MANAGE_CATALOG where parent_id=? order by orderline";
		
		List<Object> dataList = getJdbcTemplate().query(sql, new Object[] { new Integer(catalogId) });
		ArrayList<Object> newDataList = new ArrayList<Object>();
		if (dataList != null)
		{
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
			{
				DataRow dataRow = (DataRow) iter.next();
				ManageCatalog catalog = new ManageCatalog();
				catalog.fromMap(dataRow);
				newDataList.add(catalog);
			}
		}
		return newDataList;
	}
	
	@Override
    public List<Object> findChildrenById(int catalogId, String siteNo)
	{
		ArrayList<Object> argList = new ArrayList<Object>();
		String sql = "select * from T_MANAGE_CATALOG where parent_id=? ";
		argList.add(new Integer(catalogId));
		if (!StringHelper.isEmpty(siteNo))
		{
			sql += " and siteno=?";
			argList.add(siteNo);
		}
		sql += " order by orderline";
		
        List<Object> dataList = getJdbcTemplate().query(sql, argList.toArray());
		ArrayList<Object> newDataList = new ArrayList<Object>();
		if (dataList != null)
		{
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
			{
				DataRow dataRow = (DataRow) iter.next();
				ManageCatalog catalog = new ManageCatalog();
				catalog.fromMap(dataRow);
				newDataList.add(catalog);
			}
		}
		return newDataList;
	}
	
	                                /**
    * 
    * 描述:  查询父栏目下的节点数  
    * 作者:	 
    * 创建日期: 2010-1-7
    * 创建时间: 下午01:43:10
    * @return int
    * @throws
    */
	@Override
    public int findParentCatalogNum(int parentId)
	{
		try
		{
			String sql = "SELECT COUNT(CATALOG_ID) FROM T_MANAGE_CATALOG WHERE PARENT_ID = ?";
			List<Object> argList = new ArrayList<Object>();
			argList.add(new Integer(parentId));
			return getJdbcTemplate().queryInt(sql, argList.toArray());
		}
		catch (Exception ex)
		{
			logger.error("", ex);
			return 0;
		}
	}
	
	@Override
    public ManageCatalog findRootCatalog(String siteNo)
	{
		ArrayList<Object> argList = new ArrayList<Object>();
		String sql = "select * from T_MANAGE_CATALOG where siteno like ? and parent_id=0";
		argList.add("%" + siteNo + "%");
		//DataRow dataRow = getJdbcTemplate().queryMap(sql, new Object[]{siteNo});
		DataRow dataRow = getJdbcTemplate().queryMap(sql, argList.toArray());
		if (dataRow == null)
			return null;
		ManageCatalog catalog = new ManageCatalog();
		catalog.fromMap(dataRow);
		return catalog;
	}
	
	@Override
    public ManageCatalog getParent(int catalogId)
	{
		String sql = "select parent_id from T_MANAGE_CATALOG where catalog_id=?";
		int parentId = getJdbcTemplate().queryInt(sql, new Object[] { new Integer(catalogId) });
		if (parentId > 0)
			return findCatalogById(parentId);
		else
			return null;
	}
	
	@Override
    public String getRoute(int catalogId)
	{
		if (catalogId <= 0)
			return "";
		
		String route = String.valueOf(catalogId);
		ManageCatalog catalog = null;
		while ((catalog = getParent(catalogId)) != null)
		{
			catalogId = catalog.getId();
			route = String.valueOf(catalogId) + "|" + route;
		}
		return route;
	}
	
	@Override
    public void updateCatalog(ManageCatalog catalog)
	{
		DataRow dataRow = new DataRow();
		dataRow.putAll(catalog.toMap());
		getJdbcTemplate().update("T_MANAGE_CATALOG", dataRow, "catalog_id", new Integer(catalog.getId()));
	}
	
	                                    /**
    * 
    * 描述:  更新父栏目拥有的节点数
    * 作者:	 
    * 创建日期: 2010-1-7
    * 创建时间: 下午01:46:17
    * @return void
    * @throws
    */
	@Override
    public void updateParentCatalogNum(int catalogId, int childrennum)
	{
		String sql = "UPDATE T_MANAGE_CATALOG SET CHILDRENNUM = ? WHERE CATALOG_ID = ?";
		List<Object> argList = new ArrayList<Object>();
		argList.add(new Integer(childrennum));
		argList.add(new Integer(catalogId));
		getJdbcTemplate().update(sql, argList.toArray());
	}
}
