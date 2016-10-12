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
import com.jhcz.plat.dao.CatalogDao;
import com.jhcz.plat.domain.Catalog;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-17
 * 创建时间: 17:34:01
 */
@Repository
public class CatalogDaoImpl extends BaseDao implements CatalogDao
{
	
	private static Logger logger = Logger.getLogger(CatalogDaoImpl.class);
	
	                                                                                                                                                                            /**
    * 
    * @描述：插入附带发布栏目信息
    * @作者：袁永君
    * @时间：2010-04-02 17:11:33
    * @param data
    */
	@Override
    public void addAttachCatalog(DataRow data)
	{
		getJdbcTemplate().insert("T_PUBLISH_ATTACH", data);
	}
	
	@Override
    public void addCatalog(Catalog catalog)
	{
		JdbcTemplate jdbcTempate = getJdbcTemplate();
		DataRow dataRow = new DataRow();
		dataRow.putAll(catalog.toMap());
		jdbcTempate.insert("T_CATALOG", dataRow);
		if (1 == Configuration.getInt("system.isAutoIncrement"))
		{
			try
			{
				catalog.setId(Integer.parseInt(jdbcTempate.getGeneratedKeys()));
			}
			catch (Exception ex)
			{
				logger.error(ex);
			}
		}
	}
	
	                                                                                                                                                                            /**
    * 
    * @描述：删除档前栏目下的所有附带发布信息
    * @作者：袁永君
    * @时间：2010-04-02 20:21:18
    * @param catalogId
    */
	@Override
    public void delAttachByCatalogId(int catalogId)
	{
		getJdbcTemplate().delete("T_PUBLISH_ATTACH", "catalog_id", new Integer(catalogId));
	}
	
	@Override
    public void deleteCatalog(int catalogId)
	{
		getJdbcTemplate().delete("T_CATALOG", "catalog_id", new Integer(catalogId));
	}
	
	                                                                                                                                                                            /**
    * 
    * @描述：
    * @作者：袁永君
    * @时间：2010-04-02 17:16:39
    * @param catalogId
    * @return
    */
	@Override
    public List<Object> findAttachCatalog(int catalogId, String siteNo)
	{
		List<Object> argList = new ArrayList<Object>();
		String sql = "SELECT * FROM T_PUBLISH_ATTACH WHERE CATALOG_ID = ? AND SITENO = ?";
		argList.add(new Integer(catalogId));
		argList.add(siteNo);
        return getJdbcTemplate().query(sql, argList.toArray());
	}
	
	@Override
    public Catalog findCatalogById(int catalogId)
	{
		String sql = "select * from T_CATALOG where catalog_id=?";
		DataRow dataRow = getJdbcTemplate().queryMap(sql, new Object[] { new Integer(catalogId) });
		if (dataRow == null)
			return null;
		Catalog catalog = new Catalog();
		catalog.fromMap(dataRow);
		return catalog;
	}
	
	@Override
    public Catalog findCatalogById(int catalogId, String siteno)
	{
		ArrayList<Object> argList = new ArrayList<Object>();
		String sql = "select * from T_CATALOG where catalog_id=?";
		argList.add(new Integer(catalogId));
		if (StringHelper.isNotEmpty(siteno))
		{
			sql += " and siteno like ?";
			argList.add("%" + siteno + "%");
		}
		//DataRow dataRow = getJdbcTemplate().queryMap(sql, new Object[]{new Integer(catalogId)});
		DataRow dataRow = getJdbcTemplate().queryMap(sql, argList.toArray());
		if (dataRow == null)
			return null;
		Catalog catalog = new Catalog();
		catalog.fromMap(dataRow);
		return catalog;
	}
	
	                                                                                                                                                                            /**
    * 
    * @描述：
    * @作者：袁永君
    * @时间：2011-6-27 下午08:39:05
    * @param catalogNo
    * @param siteNo
    * @return
    */
	@Override
    public Catalog findCatalogByNo(String catalogNo, String siteNo)
	{
		List<Object> argList = new ArrayList<Object>();
		String sql = "SELECT * FROM T_CATALOG WHERE CATALOG_NO = ? AND SITENO = ?";
		argList.add(catalogNo);
		argList.add(siteNo);
		DataRow dataRow = getJdbcTemplate().queryMap(sql, argList.toArray());
		
		if (dataRow == null)
			return null;
		Catalog catalog = new Catalog();
		catalog.fromMap(dataRow);
		return catalog;
	}
	
	                                                                                                                                                                            /**
    * 
    * @描述：查询子栏目下所有栏目
    * @作者：袁永君
    * @时间：2011-3-11 上午11:32:39
    * @param catalogId
    * @return
    */
	@Override
    public List<Catalog> findCatalogs4Route(int catalogId)
	{
		ArrayList<Object> argList = new ArrayList<Object>();
		String sql = "SELECT * FROM T_CATALOG WHERE ROUTE LIKE ? ORDER BY CATALOG_ID ";
		argList.add("%" + catalogId + "%");
        List<Object> dataList = getJdbcTemplate().query(sql, argList.toArray());
        ArrayList<Catalog> newDataList = new ArrayList<Catalog>();
		if (dataList != null)
		{
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
			{
                DataRow dataRow = (DataRow) iter.next();
				Catalog catalog = new Catalog();
				catalog.fromMap(dataRow);
				newDataList.add(catalog);
			}
		}
		return newDataList;
	}
	
	@Override
    public List<Catalog> findChildrenById(int catalogId)
	{
		String sql = "select * from T_CATALOG where parent_id=? order by orderline";
		
        List<Object> dataList = getJdbcTemplate().query(sql, new Object[] { new Integer(catalogId) });
        ArrayList<Catalog> newDataList = new ArrayList<Catalog>();
		if (dataList != null)
		{
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
			{
				DataRow dataRow = (DataRow) iter.next();
				Catalog catalog = new Catalog();
				catalog.fromMap(dataRow);
				newDataList.add(catalog);
			}
		}
		return newDataList;
	}
	
	@Override
    public List<Catalog> findChildrenById(int catalogId, String siteNo)
	{
		ArrayList<Object> argList = new ArrayList<Object>();
		String sql = "select * from T_CATALOG where parent_id=?";
		argList.add(new Integer(catalogId));
		if (!StringHelper.isEmpty(siteNo))
		{
			sql += " and siteno=?";
			argList.add(siteNo);
		}
		sql += " order by orderline";
		
        List<Object> dataList = getJdbcTemplate().query(sql, argList.toArray());
		ArrayList<Catalog> newDataList = new ArrayList<Catalog>();
		if (dataList != null)
		{
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
			{
				DataRow dataRow = (DataRow) iter.next();
				Catalog catalog = new Catalog();
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
			String sql = "SELECT COUNT(CATALOG_ID) FROM T_CATALOG WHERE PARENT_ID = ?";
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
    public Catalog findRootCatalog(String siteNo)
	{
		ArrayList<Object> argList = new ArrayList<Object>();
		String sql = "select * from T_CATALOG where siteno like ? and parent_id=0";
		argList.add("%" + siteNo + "%");
		//DataRow dataRow = getJdbcTemplate().queryMap(sql, new Object[]{siteNo});
		DataRow dataRow = getJdbcTemplate().queryMap(sql, argList.toArray());
		if (dataRow == null)
			return null;
		Catalog catalog = new Catalog();
		catalog.fromMap(dataRow);
		return catalog;
	}
	
	                                                                                                                                                                            /**
    * 查询子栏目信息
    * @param route    线路
    * @return
    */
	@Override
    public List<Object> findRouteCatalogById(int catalogId, String siteNo)
	{
		ArrayList<Object> argList = new ArrayList<Object>();
		String sql = "SELECT CATALOG_ID,PARENT_ID,NAME,CATALOG_NO,ROUTE,LINK_URL,CHILDRENNUM FROM T_CATALOG WHERE STATE = 1 AND ROUTE like ? AND SITENO = ? ORDER BY ORDERLINE";
		if (catalogId == 1)
		{
			argList.add("1|%");
		}
		else
		{
			argList.add("%|" + catalogId + "|%");
		}
		
		argList.add(siteNo);
        return getJdbcTemplate().query(sql, argList.toArray());
	}
	
	@Override
    public Catalog getParent(int catalogId)
	{
		String sql = "select parent_id from T_CATALOG where catalog_id=?";
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
		Catalog catalog = null;
		while ((catalog = getParent(catalogId)) != null)
		{
			catalogId = catalog.getId();
			route = String.valueOf(catalogId) + "|" + route;
		}
		return route;
	}
	
	@Override
    public void updateCatalog(Catalog catalog)
	{
		DataRow dataRow = new DataRow();
		dataRow.putAll(catalog.toMap());
		getJdbcTemplate().update("T_CATALOG", dataRow, "catalog_id", new Integer(catalog.getId()));
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
		String sql = "UPDATE T_CATALOG SET CHILDRENNUM = ? WHERE CATALOG_ID = ?";
		List<Object> argList = new ArrayList<Object>();
		argList.add(new Integer(childrennum));
		argList.add(new Integer(catalogId));
		getJdbcTemplate().update(sql, argList.toArray());
	}
}
