package com.jhcz.plat.dao;

import java.util.List;

import com.jhcz.plat.domain.ManageCatalog;

/**
 * 描述:
 * 版权:	 Copyright (c) 2007
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-3-27
 * 创建时间: 10:56:24
 */
public interface ManageCatalogDao
{
    
    public void addCatalog(ManageCatalog catalog);
    
    public void deleteCatalog(int catalogId);
    
    public void deleteCatalog(int catalogId, String siteno);
    
    public ManageCatalog findCatalogById(int catalogId);
    
    public ManageCatalog findCatalogById(int catalogId, String siteno);
    
    public List<Object> findCatalogInfoByParentId(int parentId, String siteno);
    
    public List<Object> findCatalogLikeRoute(int catalogId, String siteno);
    
    public List<Object> findChildrenById(int catalogId);
    
    public List<Object> findChildrenById(int catalogId, String siteNo);
    
    public int findParentCatalogNum(int parentId);
    
    public ManageCatalog findRootCatalog(String siteNo);
    
    public ManageCatalog getParent(int catalogId);
    
    public String getRoute(int catalogId);
    
    public void updateCatalog(ManageCatalog catalog);
    
    /**
    * 
    * 描述:  更新父栏目拥有的节点数
    * 作者:	 
    * 创建日期: 2010-1-7
    * 创建时间: 下午01:48:23
    * @return void
    * @throws
    */
    public void updateParentCatalogNum(int catalogId, int childrennum);
}
