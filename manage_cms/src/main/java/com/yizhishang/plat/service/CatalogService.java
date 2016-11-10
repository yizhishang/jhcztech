package com.yizhishang.plat.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhishang.base.jdbc.DataRow;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.dao.CatalogDao;
import com.yizhishang.plat.domain.Catalog;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-17
 * 创建时间: 17:43:45
 */
@Service
public class CatalogService extends BaseService
{
	@Autowired
	CatalogDao catalogDao;
	
    /**
    * 
    * @描述：
    * @作者：袁永君
    * @时间：2010-04-02 17:12:52
    * @param data
    */
	public void addAttachCatalog(DataRow data)
	{
		String id = getSeqValue("T_PUBLISH_ATTACH");
		data.set("id", id);
		catalogDao.addAttachCatalog(data);
	}
	
    /**
    * 添加catalog
    *
    * @param catalog catalog对象
    */
	public void addCatalog(Catalog catalog)
	{
		String id = getSeqValue("T_CATALOG");
		catalog.setId(Integer.parseInt(id));catalogDao.addCatalog(catalog);
	}
	
    /**
    * 
    * @描述：
    * @作者：袁永君
    * @时间：2010-04-02 20:22:21
    * @param catalogId
    */
	public void delAttachByCatalogId(int catalogId)
	{
		catalogDao.delAttachByCatalogId(catalogId);
	}
	
    /**
    * 删除catalog
    *
    * @param catalogId catalog的ID
    */
	public void deleteCatalog(int catalogId)
	{
		catalogDao.deleteCatalog(catalogId);
	}
	
    private void docToArr(List<Object> catalogs, List<DataRow> arrayList, boolean isSystemAdmin, DataRow rights)
	{
		if (catalogs != null)
		{
			for (int i = 0; i < catalogs.size(); i++)
			{
				DataRow dataRow = (DataRow) catalogs.get(i);
				String catalogId = dataRow.getString("catalog_id");
				String route = dataRow.getString("route");
                //				String attribute = "";
				if (isSystemAdmin || (rights != null && rights.containsKey(catalogId)))
				{
					dataRow.set("rownum", StringHelper.count(route, '|'));
					
					arrayList.add(dataRow);
                    @SuppressWarnings("unchecked")
                    List<Object> children = (List<Object>) dataRow.getObject("children");
					if (children != null && children.size() > 0)
					{
						docToArr(children, arrayList, isSystemAdmin, rights);
					}
					else
					{
						dataRow.set("isLastTree", "true");
					}
				}
				else
				{
					continue;
				}
				
			}
		}
	}
	
    /**
    * @描述：
    * @作者：袁永君
    * @时间：2010-04-02 17:19:26
    * @param catalogId
    * @return
    */
	public List<Object> findAttachCatalog(int catalogId, String siteNo)
	{
		
		return catalogDao.findAttachCatalog(catalogId, siteNo);
	}
	
    /**
    * 根据ID寻找对应的Catalog
    *
    * @param catalogId Catalog的ID值
    * @return
    */
	public Catalog findCatalogById(int catalogId)
	{
		
		return catalogDao.findCatalogById(catalogId);
	}
	
    /**
    * 根据ID和站点寻找对应的Catalog
    *
    * @param catalogId Catalog的ID值
    * @param siteno    用户所登录的站点
    * @return
    */
	public Catalog findCatalogById(int catalogId, String siteno)
	{
		
		return catalogDao.findCatalogById(catalogId, siteno);
	}
	
    /**
    * 
    * @描述：
    * @作者：袁永君
    * @时间：2011-6-27 下午08:41:27
    * @param catalogNo
    * @param siteNo
    * @return
    */
	public Catalog findCatalogByNo(String catalogNo, String siteNo)
	{
		
		return catalogDao.findCatalogByNo(catalogNo, siteNo);
	}
	
    /**
    * 
    * @描述：查询子栏目下所有栏目
    * @作者：袁永君
    * @时间：2011-3-11 上午11:34:44
    * @param catalogId
    * @return
    */
	public List<Catalog> findCatalogs4Route(int catalogId)
	{
		
		return catalogDao.findCatalogs4Route(catalogId);
	}
	
    /**
    * 
    * @描述：查询文档权限
    * @作者：袁永君
    * @时间：2011-3-12 上午08:48:02
    * @return
    */
	public List<DataRow> findCatalogTrue(int roleId, String siteNo, boolean isSystemAdmin, DataRow catalogRole)
	{
		
		CatalogService catalogService = new CatalogService();
        List<Object> catalogs = catalogService.findWholeCatalogById(1, siteNo);
		
        ArrayList<DataRow> dataList = new ArrayList<DataRow>();
		
		docToArr(catalogs, dataList, isSystemAdmin, catalogRole);
		return dataList;
		
	}
	
    /**
    * 得到一个栏目的所有子栏目,list中的每一个对象为Catalog对象
    *
    * @param catalogId 当前栏目的ID
    * @return
    */
	public List<Catalog> findChildrenById(int catalogId)
	{
		
		return catalogDao.findChildrenById(catalogId);
	}
	
    /**
    * 得到一个栏目的所有子栏目,list中的每一个对象为Catalog对象
    * @param catalogId 当前栏目的ID
    * @param siteNo    栏目所属站点
    * @return
    */
	public List<Catalog> findChildrenById(int catalogId, String siteNo)
	{
		
		return catalogDao.findChildrenById(catalogId, siteNo);
	}
	
    /**
    * 查询父栏目下的节点数
    */
	public int findParentCatalogNum(int parentId)
	{
		
		return catalogDao.findParentCatalogNum(parentId);
	}
	
    /**
    * 得到某站点的根目录
    *
    * @param siteNo 站点编号
    * @return
    */
	public Catalog findRootCatalog(String siteNo)
	{
		
		return catalogDao.findRootCatalog(siteNo);
	}
	
	public List<Object> findRouteCatalogById(int catalogId, String siteNo)
	{
		
		return catalogDao.findRouteCatalogById(catalogId, siteNo);
	}
	
    /**
    *描述：可以根据一个栏目的ID查出该栏目的信息，包括子栏目，子栏目在DataRow里面的key是"children"，值类型是一个List。该方法依赖于方法findRouteCatalogById 
    *作者：袁永君
    *时间：2016-02-24 下午03:44:56
    * @param catalogId 栏目ID
    * @return
    */
	public List<Object> findWholeCatalogById(int catalogId, String siteNo)
	{
		List<Object> list = new ArrayList<Object>();
		
        List<Object> catalogs = catalogDao.findRouteCatalogById(catalogId, siteNo);
		Object[] array = catalogs.toArray();
		Collections.addAll(list, array);
		for (int i = 0; i < array.length; i++)
		{
			DataRow catalog = (DataRow) array[i];
            List<Object> children = new ArrayList<Object>();
			for (int j = 0; j < list.size();)
			{
                //根据正则表达式得到子栏目的在catalogs链表中的位置
				int index = getChildCatalogIndex(catalog, list);
				if (index >= 0)
				{
					children.add(list.remove(index));
				}
				else
				{
					break;
				}
			}
            //如果有子栏目则添加到栏目的DataRow当中，key是children
			if (children.size() > 0)
			{
				catalog.put("children", children);
			}
		}
		return list;
	}
	
    /**
    *描述：通过正则表达式找出data的子栏目在栏目链表中的位置
    *作者：袁永君
    *时间：2016-02-24 下午03:57:25
    * @param data 待查是否存在子栏目的栏目
    * @param catalogs 所有相关栏目的集合
    * @return 返回-1表示不存在子栏目
    */
    private int getChildCatalogIndex(DataRow data, List<Object> catalogs)
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
	
    /**
    * 返回某栏目的父目录，若是根目录，则其父目录为null
    *
    * @param catalogId
    * @return
    */
	public Catalog getParent(int catalogId)
	{
		
		return catalogDao.getParent(catalogId);
	}
	
    /**
    * 返回某栏目的route
    *
    * @param catalogId
    * @return
    */
	public String getRoute(int catalogId)
	{
		
		return catalogDao.getRoute(catalogId);
	}
	
    /**
    * 判断某目录是否有子目录
    *
    * @param catalogId 栏目ID
    * @return
    */
	public boolean hasChildren(int catalogId)
	{
        List<Catalog> childrenList = findChildrenById(catalogId);
		return childrenList != null && childrenList.size() > 0;
	}
	
    /**
    * 更新catalog
    *
    * @param catalog catalog对象
    */
	public void updateCatalog(Catalog catalog)
	{
		catalogDao.updateCatalog(catalog);
	}
	
    /**
    * 更新父栏目拥有的节点数
    */
	public void updateParentCatalogNum(int catalogId, int childrennum)
	{
		catalogDao.updateParentCatalogNum(catalogId, childrennum);
	}
}