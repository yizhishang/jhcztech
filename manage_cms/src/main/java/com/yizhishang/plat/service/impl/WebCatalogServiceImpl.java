package com.yizhishang.plat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yizhishang.base.jdbc.DataRow;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.ConvertHelper;
import com.yizhishang.plat.Constants;
import com.yizhishang.plat.service.WebCatalogService;

/**
 * 文件标题：CatalogService
 * 描述: 用于查询前台页面所需要的栏目信息
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君  lanlei@yizhishang.com
 * 版本:	 1.0
 * 创建日期: 2015-10-28
 * 创建时间: 下午04:39:03
 */
@Service
public class WebCatalogServiceImpl extends BaseService implements WebCatalogService
{
	        
    /**
    *描述：根据栏目id获取栏目信息
    *作者：袁永君
    *时间：2016-02-21 下午01:59:05
    * @param catalogId 栏目id
    * @return
    */
	@Override
    public DataRow findCatalogById(String catalogId)
	{
		String sql = "SELECT CATALOG_ID,NAME,CATALOG_NO, SITENO,LINK_URL,PARENT_ID,USER_RIGHT FROM T_CATALOG WHERE CATALOG_ID=? AND STATE=1 AND SITENO=? order  BY ORDERLINE";
		return getJdbcTemplate().queryMap(sql, new Object[] { new Integer(ConvertHelper.strToInt(catalogId)), Constants.MAIN_SITENO });
	}
	
	/**
	 * 描述: 根据栏目id获取栏目信息
	 * 作者: 袁永君
	 * 创建日期: 2016-1-3
	 * 创建时间: 上午3:50:54
	 * @param catalogNo
	 * @return
	 */
	@Override
	public DataRow findCatalogByNo(String catalogNo)
	{
	    String sql = "SELECT CATALOG_ID,NAME,CATALOG_NO, SITENO,LINK_URL,PARENT_ID,USER_RIGHT FROM T_CATALOG WHERE CATALOG_ID=? AND STATE=1 AND CATALOG_NO=? order  BY ORDERLINE";
	    return getJdbcTemplate().queryMap(sql, new Object[] { new Integer(ConvertHelper.strToInt(catalogNo)), Constants.MAIN_SITENO });
	}
	                            
    /**
    *描述：根据栏目id查询子栏目信息
    *作者：袁永君
    *时间：2016-02-21 下午01:56:52
    * @param catalogId 栏目id
    * @return
    */
	@Override
    public List<Object> findChildrenById(String catalogId)
	{
		String sql = "SELECT CATALOG_ID,NAME,CATALOG_NO FROM T_CATALOG WHERE PARENT_ID=? ORDER BY ORDERLINE";
        List<Object> dataList = getJdbcTemplate().query(sql, new Object[] { new Integer(catalogId) });
		return dataList;
	}
	                                
    /**
    * 查询子栏目信息
    * @param route    线路
    * @return
    */
	@Override
    public List<Object> findRouteCatalogById(String route)
	{
		String sql = "SELECT CATALOG_ID,NAME,CATALOG_NO,ROUTE,LINK_URL,CHILDRENNUM FROM T_CATALOG WHERE STATE = 1 AND ROUTE like ? ORDER BY ORDERLINE";
        List<Object> dataList = getJdbcTemplate().query(sql, new Object[] { "%" + route + "%" });
		return dataList;
	}
	                                        
    /**
    *描述：可以根据一个栏目的ID查出该栏目的信息，包括子栏目，子栏目在DataRow里面的key是"children"，值类型是一个List。该方法依赖于方法findRouteCatalogById 
    *作者：袁永君
    *时间：2016-02-24 下午03:44:56
    * @param catalogId 栏目ID
    * @return
    */
	@Override
    public DataRow findWholeCatalogById(String catalogId)
	{
		DataRow wholeCatalog = new DataRow();
        List<Object> catalogs = findRouteCatalogById(catalogId);
		Object[] array = catalogs.toArray();
		for (int i = 0; i < array.length; i++)
		{
			DataRow catalog = (DataRow) array[i];
            List<Object> children = new ArrayList<Object>();
			for (int j = 0; j < catalogs.size();)
			{
                //根据正则表达式得到子栏目的在catalogs链表中的位置
				int index = getChildCatalogIndex(catalog, catalogs);
				if (index >= 0)
				{
					children.add(catalogs.remove(index));
				}else{
					break;
				}
			}
            //如果有子栏目则添加到栏目的DataRow当中，key是children
			if (children.size() > 0)
			{
				catalog.put("children", children);
			}
            //最后将所查的栏目返回
			if (catalog.getString("catalog_id").equals(catalogId))
			{
				wholeCatalog = catalog;
			}
		}
		return wholeCatalog;
	}
	                                            
    /**
    *描述：通过正则表达式找出data的子栏目在栏目链表中的位置
    *作者：袁永君
    *时间：2016-02-24 下午03:57:25
    * @param data 待查是否存在子栏目的栏目
    * @param catalogs 所有相关栏目的集合
    * @return 返回-1表示不存在子栏目
    */
	@Override
    public int getChildCatalogIndex(DataRow data, List<Object> catalogs)
	{
		for (int i = 0; i < catalogs.size(); i++)
		{
			DataRow catalog = (DataRow) catalogs.get(i);
			String regex = data.getString("route").replaceAll("\\|", "\\\\|") + "\\|\\d+";
			if (catalog.getString("route").matches(regex))
			{
				return i;
			}
		}
		return -1;
	}
	 
}
