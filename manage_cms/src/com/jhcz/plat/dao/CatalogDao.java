package com.jhcz.plat.dao;

import java.util.List;

import com.jhcz.base.jdbc.DataRow;
import com.jhcz.plat.domain.Catalog;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-17
 * 创建时间: 17:31:29
 */
public interface CatalogDao
{
	
	public void addAttachCatalog(DataRow data);
	
	public void addCatalog(Catalog catalog);
	
	public void delAttachByCatalogId(int catalogId);
	
	public void deleteCatalog(int catalogId);
	
    public List<Object> findAttachCatalog(int catalogId, String siteNo);
	
	public Catalog findCatalogById(int catalogId);
	
	public Catalog findCatalogById(int catalogId, String siteno);
	
    /**
    * 
    * @描述：根据栏目英文名获取栏目信息
    * @作者：袁永君
    * @时间：2011-6-27 下午08:39:24
    * @param catalogNo
    * @param siteNo
    * @return
    */
	public Catalog findCatalogByNo(String catalogNo, String siteNo);
	
    /**
    * @描述：
    * @作者：袁永君
    * @时间：2011-3-11 上午11:33:43
    * @param catalogId
    * @return
    */
    public List<Catalog> findCatalogs4Route(int catalogId);
	
    public List<Catalog> findChildrenById(int catalogId);
	
	public List<Catalog> findChildrenById(int catalogId, String siteNo);
	
	public int findParentCatalogNum(int parentId);
	
	public Catalog findRootCatalog(String siteNo);
	
    public List<Object> findRouteCatalogById(int catalogId, String siteNo);
	
	public Catalog getParent(int catalogId);
	
	public String getRoute(int catalogId);
	
	public void updateCatalog(Catalog catalog);
	
	public void updateParentCatalogNum(int catalogId, int childrennum);
}
