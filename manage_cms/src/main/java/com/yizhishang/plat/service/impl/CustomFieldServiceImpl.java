package com.yizhishang.plat.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yizhishang.base.jdbc.DataRow;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.service.ServiceLocator;
import com.yizhishang.plat.domain.Catalog;
import com.yizhishang.plat.service.CatalogService;
import com.yizhishang.plat.service.CustomFieldService;

/**
 * 描述: CustomFieldServiceImpl.java
 * 版权: Copyright (c) 2015
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2015-12-3
 * 创建时间: 下午11:08:22
 */
@Service
public class CustomFieldServiceImpl extends BaseService implements CustomFieldService
{
	
	private static Logger logger = LoggerFactory.getLogger(CustomFieldServiceImpl.class);
	
    /**
    * @描述：插入扩展字段信息
    * @作者：袁永君
    * @时间：2015-12-17 15:02:46
    * @param data
    */
	@Override
    public boolean addExtendField(DataRow data)
	{
		try
		{
			String id = getSeqValue("T_ARTICLE_EXTEND_INFO");
			data.set("orderline", id);
			
			getJdbcTemplate().insert("T_ARTICLE_EXTEND_INFO", data);
			return true;
		}
		catch (Exception ex)
		{
            logger.error(ex.getMessage(), ex);
			return false;
		}
	}
	
    /**
    * 
    * @描述：添加扩展字段的详细值
    * @作者：袁永君
    * @时间：2015-12-17 17:32:59
    * @param data
    */
	@Override
    public void addExtendFieldContent(DataRow data)
	{
		try
		{
			getJdbcTemplate().insert("T_ARTICLE_EXTEND_FIELD", data);
		}
		catch (Exception ex)
		{
			logger.error("", ex);
		}
	}
	
	                                                                                                /**
    * 根据栏目ID，查找当前栏目下的扩展字段信息，如果扩展字段为空，则向上找父栏目的扩展信息
    * 
    * @param catalogId
    * @return
    */
	@Override
    public List<Object> cycFindExtendFieldInfo(int catalogId)
	{
		CatalogService catalogService = (CatalogService) ServiceLocator.getService(CatalogService.class);
        List<Object> dataList = findExtendFieldInfo(catalogId);
        if (dataList != null && dataList.size() > 0) //找到了，直接返回
		{
			return dataList;
		}
		else
        //没找到，往父目录中寻找
		{
			Catalog catalog = catalogService.getParent(catalogId);
            if (catalog == null) //已经找到了最顶层，还是没有找到，直接返回空
			{
				return null;
			}
			else
			{
				return cycFindExtendFieldInfo(catalog.getId());
			}
		}
	}
	
	                                                                                                /**
    * 
    * @描述：根据字段代码删除扩展字段
    * @作者：袁永君
    * @时间：2015-12-17 15:53:27
    * @param code
    */
	@Override
    public boolean delExtendFieldById(int id)
	{
		try
		{
			getJdbcTemplate().delete("T_ARTICLE_EXTEND_INFO", "id", new Integer(id));
			return true;
		}
		catch (Exception ex)
		{
			logger.error(ex.getMessage());
			return false;
		}
	}
	
	                                                                                                /**
    * 
    * @描述：修改扩展信息的详细值
    * @作者：袁永君
    * @时间：2015-12-17 19:19:36
    * @param data
    */
	@Override
    public void editExtendFieldContent(DataRow data)
	{
		try
		{
			getJdbcTemplate().update("T_ARTICLE_EXTEND_FIELD", data, "article_id", data.getString("article_id"));
		}
		catch (Exception ex)
		{
			logger.error("", ex);
		}
	}
	
	                                                                                                    /**
    * 
    * @描述：修改字段信息
    * @作者：袁永君
    * @时间：2015-12-17 16:35:34
    * @param data
    */
	@Override
    public void editFieldInfo(DataRow data)
	{
		getJdbcTemplate().update("T_ARTICLE_EXTEND_INFO", data, "id", data.getString("id"));
	}
	
	                                                                                                /**
    * 
    * @描述：根据栏目ID查询当前栏目所有的代码
    * @作者：袁永君
    * @时间：2015-12-17 17:58:41
    * @param catalogId
    * @return
    */
	@Override
    public String[] findExtendFieldCodeById(int catalogId)
	{
		String sql = "SELECT CODE FROM T_ARTICLE_EXTEND_INFO ORDER BY CREATE_DATE DESC";
		return getJdbcTemplate().queryStringArray(sql);
	}
	
	                                                                                                /**
    * 
    * @描述：根据文章ID查询扩展文章信息
    * @作者：袁永君
    * @时间：2011-3-21 下午09:55:07
    * @param articleId
    * @return
    */
    @Override
    public DataRow findExtendFieldContent(int articleId)
	{
		try
		{
            List<Object> argList = new ArrayList<Object>();
			String sql = "SELECT  * FROM T_ARTICLE_EXTEND_FIELD WHERE ARTICLE_ID = ?";
			argList.add(new Integer(articleId));
			DataRow dataRow = getJdbcTemplate().queryMap(sql, argList.toArray());
			if (dataRow != null)
			{
                HashSet<String> set = new HashSet<String>();
                for (Iterator<String> iter = dataRow.keySet().iterator(); iter.hasNext();)
				{
					String key = iter.next();
					Object obj = dataRow.get(key);
					if (obj == null)
					{
						set.add(key);
					}
				}
				if (set != null && set.size() > 0)
				{
                    for (Iterator<String> iter = set.iterator(); iter.hasNext();)
					{
						String key = iter.next();
						dataRow.remove(key);
					}
				}
				return dataRow;
			}
			else
			{
				return null;
			}
		}
		catch (Exception ex)
		{
			logger.error(ex.getMessage());
			return null;
		}
	}
	
	                                                                                                /**
    * 
    * @描述：查询扩展文章的内容
    * @作者：袁永君
    * @时间：2015-12-17 18:17:14
    * @param articleId
    * @param fileCodes 查询的字段，每个字段间用逗号分隔
    * @return
    */
	@Override
    public DataRow findExtendFieldContent(int articleId, String fileCodes)
	{
		try
		{
            List<Object> argList = new ArrayList<Object>();
			String sql = "SELECT " + fileCodes + " FROM T_ARTICLE_EXTEND_FIELD WHERE ARTICLE_ID = ?";
			argList.add(new Integer(articleId));
			return getJdbcTemplate().queryMap(sql, argList.toArray());
		}
		catch (Exception ex)
		{
			logger.error(ex.getMessage());
			return null;
		}
	}
	
	                                                                                                /**
    * 
    * @描述：查询所有的扩展字段信息
    * @作者：袁永君
    * @时间：2015-12-17 14:53:08
    * @param catalogId
    * @return
    */
	@Override
    public List<Object> findExtendFieldInfo(int catalogId)
	{
		List<Object> argList = new ArrayList<Object>();
		StringBuffer buffer = new StringBuffer("SELECT ID,CATALOG_ID,INPUT_TYPE,NAME,CODE,ISMANDATORY,DEFAULT_VALUE,MAX_NUM,WIDTH,HEIGHT,EXTEND_CONTENT,ORDERLINE,ALIAS ");
		buffer.append(" FROM T_ARTICLE_EXTEND_INFO WHERE 1 = 1");
		if (catalogId > 0)
		{
			buffer.append(" AND CATALOG_ID = ?");
			argList.add(new Integer(catalogId));
		}
		buffer.append(" ORDER BY ORDERLINE DESC,ID DESC");
		
		return getJdbcTemplate().query(buffer.toString(), argList.toArray());
	}
	
	                                                                                                /**
    * 
    * @描述：根据字段代码查询字段信息
    * @作者：袁永君
    * @时间：2015-12-17 15:00:48
    * @param code
    * @return
    */
	@Override
    public DataRow findExtendFieldInfoByCode(String code)
	{
        List<Object> argList = new ArrayList<Object>();
		String sql = "SELECT ID,CATALOG_ID,INPUT_TYPE,NAME,CODE,ISMANDATORY,DEFAULT_VALUE,MAX_NUM,WIDTH,HEIGHT,EXTEND_CONTENT,ORDERLINE FROM T_ARTICLE_EXTEND_INFO WHERE CODE = ?";
		argList.add(code);
		return getJdbcTemplate().queryMap(sql, argList.toArray());
	}
	
	                                                                                                /**
    * 
    * @描述：根据ID查询字段信息
    * @作者：袁永君
    * @时间：2015-12-17 16:33:44
    * @param id
    * @return
    */
	@Override
    public DataRow findExtendFieldInfoById(int id)
	{
        List<Object> argList = new ArrayList<Object>();
		String sql = "SELECT ID,CATALOG_ID,INPUT_TYPE,NAME,CODE,ISMANDATORY,DEFAULT_VALUE,MAX_NUM,WIDTH,HEIGHT,EXTEND_CONTENT,ORDERLINE,ALIAS FROM T_ARTICLE_EXTEND_INFO WHERE ID = ?";
		argList.add(new Integer(id));
		return getJdbcTemplate().queryMap(sql, argList.toArray());
	}
	
	                                                                                                /**
    * 
    * @描述：判断文章扩展信息是否存在
    * @作者：袁永君
    * @时间：2015-12-17 19:17:51
    * @param articleId
    * @return
    */
	@Override
    public boolean isExistsExtendFieldById(int articleId)
	{
		try
		{
            List<Object> argList = new ArrayList<Object>();
			String sql = "SELECT ARTICLE_ID FROM T_ARTICLE_EXTEND_FIELD WHERE ARTICLE_ID = ?";
			argList.add(new Integer(articleId));
			DataRow resultData = getJdbcTemplate().queryMap(sql, argList.toArray());
			return resultData != null;
		}
		catch (Exception ex)
		{
			logger.error(ex.getMessage());
			return false;
		}
	}
}
