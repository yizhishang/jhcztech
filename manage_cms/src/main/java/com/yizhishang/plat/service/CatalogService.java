package com.yizhishang.plat.service;

import java.util.List;

import com.yizhishang.base.jdbc.DataRow;
import com.yizhishang.plat.domain.Catalog;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-17
 * 创建时间: 17:41:03
 */
public interface CatalogService
{
    
    /**
    * 描述: 发布附带栏目信息
    * 作者: 袁永君
    * 创建日期: 2015-12-6
    * 创建时间: 下午9:48:35
    * @param data
    */
    public void addAttachCatalog(DataRow data);
    
    /**
     * 描述: 添加catalog
     * 作者: 袁永君
     * 创建日期: 2015-12-6
     * 创建时间: 下午9:48:25
     * @param catalog
     */
    public void addCatalog(Catalog catalog);
    
    /**
     * 描述: 删除档前栏目下的所有附带发布信息
     * 作者: 袁永君
     * 创建日期: 2015-12-6
     * 创建时间: 下午9:48:15
     * @param catalogId
     */
    public void delAttachByCatalogId(int catalogId);
    
    /**
     * 描述: 删除catalog
     * 作者: 袁永君
     * 创建日期: 2015-12-6
     * 创建时间: 下午9:48:07
     * @param catalogId
     */
    public void deleteCatalog(int catalogId);
    
    /**
     * 描述: 查询该栏目需要发布的栏目信息
     * 作者: 袁永君
     * 创建日期: 2015-12-6
     * 创建时间: 下午9:47:50
     * @param catalogId
     * @param siteNo
     * @return
     */
    public List<Object> findAttachCatalog(int catalogId, String siteNo);
    
    /**
     * 描述: 根据ID寻找对应的Catalog
     * 作者: 袁永君
     * 创建日期: 2015-12-6
     * 创建时间: 下午9:48:49
     * @param catalogId
     * @return
     */
    public Catalog findCatalogById(int catalogId);
    
    /**
     * 描述: 根据ID和站点寻找对应的Catalog
     * 作者: 袁永君
     * 创建日期: 2015-12-6
     * 创建时间: 下午9:48:58
     * @param catalogId
     * @param siteno
     * @return
     */
    public Catalog findCatalogById(int catalogId, String siteno);
    
    /**
     * 描述: 根据栏目英文名获取栏目信息
     * 作者: 袁永君
     * 创建日期: 2015-12-6
     * 创建时间: 下午9:49:12
     * @param catalogNo
     * @param siteNo
     * @return
     */
    public Catalog findCatalogByNo(String catalogNo, String siteNo);
    
    /**
    * 
    * @描述：查询子栏目下所有栏目
    * @作者：袁永君
    * @时间：2011-3-11 上午11:34:00
    * @param catalogId
    * @return
    */
    public List<Catalog> findCatalogs4Route(int catalogId);
    
    public List<DataRow> findCatalogTrue(int roleId, String siteNo, boolean isSystemAdmin, DataRow catalogRole);
    
    /**
    * 得到一个栏目的所有子栏目,list中的每一个对象为Catalog对象
    * @param catalogId 当前栏目的ID
    * @return
    */
    public List<Catalog> findChildrenById(int catalogId);
    
    /**
    * 得到一个栏目的所有子栏目,list中的每一个对象为Catalog对象
    * @param catalogId 当前栏目的ID
    * @param siteNo    栏目所属站点
    * @return
    */
    public List<Catalog> findChildrenById(int catalogId, String siteNo);
    
    /**
    * 
    * 描述:  查询父栏目下的节点数
    * 作者:	 袁永君
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
    public Catalog findRootCatalog(String siteNo);
    
    /**
    * 
    * 描述：查询栏目下所有的子栏目
    * 作者：袁永君
    * 时间：May 25, 2013 10:16:26 PM
    * @param catalogId
    * @param siteNo
    * @return
    */
    public List<Object> findRouteCatalogById(int catalogId, String siteNo);
    
    /**
    *描述：可以根据一个栏目的ID查出该栏目的信息，包括子栏目，子栏目在DataRow里面的key是"children"，值类型是一个List。该方法依赖于方法findRouteCatalogById 
    *作者：袁永君
    *时间：2016-02-24 下午03:44:56
    * @param catalogId 栏目ID
    * @return
    */
    public List<Object> findWholeCatalogById(int catalogId, String siteNo);
    
    /**
    * 返回某栏目的父目录，若是根目录，则其父目录为null
    * @param catalogId
    * @return
    */
    public Catalog getParent(int catalogId);
    
    /**
    * 返回某栏目的route
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
    * 更新catalog
    *
    * @param catalog catalog对象
    */
    public void updateCatalog(Catalog catalog);
    
    /**
    * 
    * 描述:  更新父栏目拥有的节点数
    * 作者:	 袁永君
    * 创建日期: 2010-1-7
    * 创建时间: 下午01:48:23
    * @return void
    * @throws
    */
    public void updateParentCatalogNum(int catalogId, int childrennum);
    
}
