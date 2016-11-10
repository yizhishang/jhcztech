package com.yizhishang.plat.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhishang.base.service.BaseService;
import com.yizhishang.plat.dao.ManageCatalogDao;
import com.yizhishang.plat.domain.ManageCatalog;

/**
 * 描述:
 * 版权:	 Copyright (c) 2007
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-3-27
 * 创建时间: 10:53:34
 */
@Service
public class ManageCatalogService extends BaseService
{
    
	@Autowired
	ManageCatalogDao catalogDao;
	
    /**
    * 添加catalog
    *
    * @param catalog catalog对象
    */
    public void addCatalog(ManageCatalog catalog)
    {
        String id = getSequenceGenerator().getNextSequence("T_MANAGE_CATALOG");
        catalog.setId(Integer.parseInt(id));
        catalogDao.addCatalog(catalog);
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
    
    public void deleteCatalog(int catalogId, String siteno)
    {
        catalogDao.deleteCatalog(catalogId, siteno);
    }
    
    public List<Object> findAllChildrenCatalogsById(int parentId, String siteno)
    {
        List<Object> catalogs = catalogDao.findCatalogLikeRoute(parentId, siteno);
        return prepareCatalogTree(catalogs);
    }
    
    /**
    * 根据ID寻找对应的Catalog
    *
    * @param catalogId Catalog的ID值
    * @return
    */
    public ManageCatalog findCatalogById(int catalogId)
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
    public ManageCatalog findCatalogById(int catalogId, String siteno)
    {
        return catalogDao.findCatalogById(catalogId, siteno);
    }
    
    /**
    * 描述：根据该栏目编号，查找其下面所有子栏目信息
    * @param parentId
    * @param siteno
    * @return
    */
    public List<Object> findCatalogInfoByParentId(int parentId, String siteno)
    {
        return catalogDao.findCatalogInfoByParentId(parentId, siteno);
    }
    
    /**
    * 
    * 描述：
    * 作者：袁永君
    * 时间：May 25, 2013 3:41:59 PM
    * @param catalogId
    * @param siteno
    * @return
    */
    public List<Object> findCatalogLikeRoute(int catalogId, String siteno)
    {
        return catalogDao.findCatalogLikeRoute(catalogId, siteno);
    }
    
    /**
    * 得到一个栏目的所有子栏目,list中的每一个对象为Catalog对象
    *
    * @param catalogId 当前栏目的ID
    * @return
    */
    public List<Object> findChildrenById(int catalogId)
    {
        return catalogDao.findChildrenById(catalogId);
    }
    
    /**
    * 得到一个栏目的所有子栏目,list中的每一个对象为Catalog对象
    * @param catalogId 当前栏目的ID
    * @param siteNo    栏目所属站点
    * @return
    */
    public List<Object> findChildrenById(int catalogId, String siteNo)
    {
        return catalogDao.findChildrenById(catalogId, siteNo);
    }
    
    public List<Object> findManageCatalogLikePId(int parentId, String siteno)
    {
        List<Object> catalogs = catalogDao.findCatalogLikeRoute(parentId, siteno);
        return catalogs;
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
    public ManageCatalog findRootCatalog(String siteNo)
    {
        return catalogDao.findRootCatalog(siteNo);
    }
    
    /**
    *描述：通过正则表达式找出data的子栏目在栏目链表中的位置
    *作者：袁永君
    *时间：2016-02-24 下午03:57:25
    * @param data 待查是否存在子栏目的栏目
    * @param catalogs 所有相关栏目的集合
    * @return 返回-1表示不存在子栏目
    */
    private int getChildCatalogIndex(ManageCatalog data, List<Object> catalogs)
    {
        for (int i = 0; i < catalogs.size(); i++)
        {
            ManageCatalog catalog = (ManageCatalog) catalogs.get(i);
            if (data.getId() == catalog.getParentId())
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
    public ManageCatalog getParent(int catalogId)
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
        List<Object> childrenList = findChildrenById(catalogId);
        return childrenList != null && childrenList.size() > 0;
    }
    
    /**
    * 描述 将list的catalog转换成树状结构
    * 作者：袁永君
    * 时间：2010-1-7 下午07:35:29
    * @param list
    * @return
    */
    public List<Object> prepareCatalogTree(@SuppressWarnings("rawtypes")
    List srcList)
    {
        List<Object> list = new ArrayList<Object>();
        Object[] catalogs = srcList.toArray();
        Collections.addAll(list, catalogs);
        for (int k = 0; k < catalogs.length; k++)
        {
            ManageCatalog menuCatalog = (ManageCatalog) catalogs[k];
            List<Object> children = new ArrayList<Object>();
            for (int i = 0; i < list.size();)
            {
                //根据正则表达式得到子栏目的在catalogs链表中的位置
                int index = getChildCatalogIndex(menuCatalog, list);
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
                menuCatalog.setChildren(children);
            }
            
        }
        return list;
    }
    
    /**
    * 更新catalog
    * @param catalog catalog对象
    */
    public void updateCatalog(ManageCatalog catalog)
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
