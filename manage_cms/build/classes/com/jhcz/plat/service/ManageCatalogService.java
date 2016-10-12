package com.jhcz.plat.service;

import java.util.List;

import com.jhcz.plat.domain.ManageCatalog;

/**
 * 描述:
 * 版权:	 Copyright (c) 2007
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-3-27
 * 创建时间: 10:49:48
 */
public interface ManageCatalogService
{
    
    /**
    * 添加catalog
    *
    * @param catalog catalog对象
    */
    public void addCatalog(ManageCatalog catalog);
    
    /**
    * 删除catalog
    *
    * @param catalogId catalog的ID
    */
    public void deleteCatalog(int catalogId);
    
    /**
    * 删除catalog
    *
    * @param catalogId catalog的ID
    */
    public void deleteCatalog(int catalogId, String siteno);
    
    /**
    * 描述：查询catalogNo的子目录，并以树状形式返回，子栏目List以chlidren的set/get方法获得
    * 作者：袁永君
    * 时间：2010-1-7 下午07:16:28
    * @param catalogNo
    * @return
    */
    List<Object> findAllChildrenCatalogsById(int parentId, String siteno);
    
    /**
    * 根据ID寻找对应的Catalog
    *
    * @param catalogId Catalog的ID值
    * @return
    */
    public ManageCatalog findCatalogById(int catalogId);
    
    /**
    * 根据ID和站点寻找对应的Catalog
    * @param catalogId Catalog的ID值
    * @param siteno    用户所登陆的站点
    * @return
    */
    public ManageCatalog findCatalogById(int catalogId, String siteno);
    
    /**
    * 查找该栏目编号下的子栏目
    * @param parentId
    * @param siteno
    * @return
    */
    public List<Object> findCatalogInfoByParentId(int parentId, String siteno);
    
    /**
    * 
    * 描述：
    * 作者：袁永君
    * 时间：May 25, 2013 3:41:59 PM
    * @param catalogId
    * @param siteno
    * @return
    */
    public List<Object> findCatalogLikeRoute(int catalogId, String siteno);
    
    /**
    * 得到一个栏目的所有子栏目,list中的每一个对象为Catalog对象
    *
    * @param catalogId 当前栏目的ID
    * @return
    */
    public List<Object> findChildrenById(int catalogId);
    
    /**
    * 得到一个栏目的所有子栏目,list中的每一个对象为Catalog对象
    * @param catalogId 当前栏目的ID
    * @param siteNo    栏目所属站点
    * @return
    */
    public List<Object> findChildrenById(int catalogId, String siteNo);
    
    /**
    * 描述：查询所有以catalogNo开头的子目录
    * 作者：袁永君
    * 时间：2010-1-7 下午07:16:30
    * @return
    */
    List<Object> findManageCatalogLikePId(int parentId, String siteno);
    
    /**
    * 
    * 描述:  查询父栏目下的节点数
    * 作者:	 
    * 创建日期: 2010-1-7
    * 创建时间: 下午01:48:28
    * @return int
    * @throws
    */
    public int findParentCatalogNum(int parentId);
    
    /**
    * 得到某站点的根目录
    *
    * @param siteNo 站点编号
    * @return
    */
    public ManageCatalog findRootCatalog(String siteNo);
    
    /**
    * 返回某栏目的父目录，若是根目录，则其父目录为null
    *
    * @param catalogId
    * @return
    */
    public ManageCatalog getParent(int catalogId);
    
    /**
    * 返回某栏目的route
    *
    * @param catalogId
    * @return
    */
    public String getRoute(int catalogId);
    
    /**
    * 判断某目录是否有子目录
    *
    * @param catalogId 栏目ID
    * @return
    */
    public boolean hasChildren(int catalogId);
    
    /**
    * 描述：将list格式的tree转换成树状结构，实际上findAllSubMenuCatalogsByNo方法是findMenuCatalogLikeNo方法和prepareCatalogTree方法的组合
    * 作者：袁永君
    * 时间：2010-1-7 下午07:36:32
    * @param list list格式的tree
    * @return
    */
    public List<Object> prepareCatalogTree(@SuppressWarnings("rawtypes") List list);
    
    /**
    * 更新catalog
    * @param catalog catalog对象
    */
    public void updateCatalog(ManageCatalog catalog);
    
    /**
    * 描述:  更新父栏目拥有的节点数
    * 作者:	 
    * 创建日期: 2010-1-7
    * 创建时间: 下午01:48:23
    * @return void
    * @throws
    */
    public void updateParentCatalogNum(int catalogId, int childrennum);
}
