package com.yizhishang.plat.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.yizhishang.base.dao.BaseDao;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.domain.Catalog;

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
public class CatalogDao extends BaseDao
{
	
	private static Logger logger = LoggerFactory.getLogger(CatalogDao.class);
	
	/**
	* 
	* @描述：插入附带发布栏目信息
	* @作者：袁永君
	* @时间：2010-04-02 17:11:33
	* @param data
	*/
	public void addAttachCatalog(DynaModel data)
	{
		getJdbcTemplateUtil().insert("T_PUBLISH_ATTACH", data);
	}
	
	public void addCatalog(Catalog catalog)
	{
		DynaModel dataRow = new DynaModel();
		dataRow.putAll(catalog.toMap());
		getJdbcTemplateUtil().insert("T_CATALOG", dataRow);
	}
	
	/**
	* 
	* @描述：删除档前栏目下的所有附带发布信息
	* @作者：袁永君
	* @时间：2010-04-02 20:21:18
	* @param catalogId
	*/
	public void delAttachByCatalogId(int catalogId)
	{
		getJdbcTemplateUtil().delete("T_PUBLISH_ATTACH", "catalog_id", new Integer(catalogId));
	}
	
	public void deleteCatalog(int catalogId)
	{
		getJdbcTemplateUtil().delete("T_CATALOG", "catalog_id", new Integer(catalogId));
	}
	
	/**
	* 
	* @描述：
	* @作者：袁永君
	* @时间：2010-04-02 17:16:39
	* @param catalogId
	* @return
	*/
	public List<DynaModel> findAttachCatalog(int catalogId, String siteNo)
	{
		List<Object> argList = new ArrayList<Object>();
		String sql = "SELECT * FROM T_PUBLISH_ATTACH WHERE CATALOG_ID = ? AND SITENO = ?";
		argList.add(new Integer(catalogId));
		argList.add(siteNo);
		return getJdbcTemplateUtil().queryForList(sql, DynaModel.class, argList.toArray());
	}
	
	public Catalog findCatalogById(int catalogId)
	{
		String sql = "select * from T_CATALOG where catalog_id=?";
		try
		{
			return getJdbcTemplateUtil().queryMap(sql, Catalog.class, new Object[] { new Integer(catalogId) });
		}
		catch (SQLException e)
		{
			logger.error(e.getMessage());
			return null;
		}
	}
	
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
		try
		{
			return getJdbcTemplateUtil().queryMap(sql, Catalog.class, argList.toArray());
		}
		catch (SQLException e)
		{
			logger.error(e.getMessage());
			return null;
		}
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
	public Catalog findCatalogByNo(String catalogNo, String siteNo)
	{
		List<Object> argList = new ArrayList<Object>();
		String sql = "SELECT * FROM T_CATALOG WHERE CATALOG_NO = ? AND SITENO = ?";
		argList.add(catalogNo);
		argList.add(siteNo);
		try
		{
			return getJdbcTemplateUtil().queryMap(sql, Catalog.class, argList.toArray());
		}
		catch (SQLException e)
		{
			logger.error(e.getMessage());
			return null;
		}
	}
	
	/**
	* 
	* @描述：查询子栏目下所有栏目
	* @作者：袁永君
	* @时间：2011-3-11 上午11:32:39
	* @param catalogId
	* @return
	*/
	public List<Catalog> findCatalogs4Route(int catalogId)
	{
		ArrayList<Object> argList = new ArrayList<Object>();
		String sql = "SELECT * FROM T_CATALOG WHERE ROUTE LIKE ? ORDER BY CATALOG_ID ";
		argList.add("%" + catalogId + "%");
		return getJdbcTemplateUtil().queryForList(sql, Catalog.class, argList.toArray());
	}
	
	public List<Catalog> findChildrenById(int catalogId)
	{
		String sql = "select * from T_CATALOG where parent_id=? order by orderline";
		return getJdbcTemplateUtil().queryForList(sql, Catalog.class, new Object[] { new Integer(catalogId) });
	}
	
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
		
		List<Catalog> dataList = getJdbcTemplateUtil().queryForList(sql, Catalog.class, argList.toArray());
		return dataList;
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
	public int findParentCatalogNum(int parentId)
	{
		try
		{
			String sql = "SELECT COUNT(CATALOG_ID) FROM T_CATALOG WHERE PARENT_ID = ?";
			List<Object> argList = new ArrayList<Object>();
			argList.add(new Integer(parentId));
			return getJdbcTemplateUtil().queryInt(sql, argList.toArray());
		}
		catch (Exception ex)
		{
			logger.error("", ex);
			return 0;
		}
	}
	
	public Catalog findRootCatalog(String siteNo)
	{
		ArrayList<Object> argList = new ArrayList<Object>();
		String sql = "select * from T_CATALOG where siteno like ? and parent_id=0";
		argList.add("%" + siteNo + "%");
		//DynaModel dataRow = getJdbcTemplateUtil().queryMap(sql, new Object[]{siteNo});
		DynaModel dataRow;
		try
		{
			dataRow = getJdbcTemplateUtil().queryMap(sql, argList.toArray());
		}
		catch (SQLException e)
		{
			logger.error(e.getMessage());
			return null;
		}
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
	public List<DynaModel> findRouteCatalogById(int catalogId, String siteNo)
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
		return getJdbcTemplateUtil().queryForList(sql, DynaModel.class, argList.toArray());
	}
	
	public Catalog getParent(int catalogId)
	{
		String sql = "select parent_id from T_CATALOG where catalog_id=?";
		int parentId = getJdbcTemplateUtil().queryInt(sql, new Object[] { new Integer(catalogId) });
		if (parentId > 0)
			return findCatalogById(parentId);
		else
			return null;
	}
	
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
	
	public void updateCatalog(Catalog catalog)
	{
		DynaModel dataRow = new DynaModel();
		dataRow.putAll(catalog.toMap());
		getJdbcTemplateUtil().update("T_CATALOG", dataRow, "catalog_id", new Integer(catalog.getId()));
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
	public void updateParentCatalogNum(int catalogId, int childrennum)
	{
		String sql = "UPDATE T_CATALOG SET CHILDRENNUM = ? WHERE CATALOG_ID = ?";
		List<Object> argList = new ArrayList<Object>();
		argList.add(new Integer(childrennum));
		argList.add(new Integer(catalogId));
		getJdbcTemplateUtil().update(sql, argList.toArray());
	}
}
