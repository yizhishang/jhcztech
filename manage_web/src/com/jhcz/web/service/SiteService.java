package com.jhcz.web.service;

import java.util.List;

import com.jhcz.base.pojo.Site;

public interface SiteService
{
	
	/**
	* 添加一个站点
	*
	* @param site
	*/
	public void addSite(Site site);
	
	/**
	* 描述：关闭站点
	* @param id
	*/
	public void closeSite(String id);
	
	/**
	* 根据站点ID，删除一个站点
	*
	* @param id
	*/
	public void deleteSite(int id);
	
	/**
	* 描述：查找所有站点信息
	* @return
	*/
	public List<Object> findAllSiteInfo();
	
	/**
	* 描述：根据编号查找站点信息
	* @param id
	* @return
	*/
	public Site findSiteById(int id);
	
	/**
	* 根据siteno查找对应的站点
	* @param siteNO
	* @return
	*/
	public Site findSiteBySiteName(String siteName);
	
	/**
	* 描述：可以根据一到多个站点编号来查询站点信息
	* @param siteno
	* @return
	*/
	public List<Object> findSiteBySiteno(String siteno);
	
	/**
	* 根据siteno查找对应的站点
	* @param siteNO
	* @return
	*/
	public Site findSiteBySiteNO(String siteNO);
	
	/**
	* 描述：根据编号查找主站点是否存在，存在就返回true，否则就返回false
	* @param isMain
	* @return
	*/
	public boolean findSiteIsMain(String isMain);
	
	/**
	* 返回所有的站点列表，
	* @return 结果为一个List,其中每一个元素为一个Site对象
	*/
	public List<Site> getAllSite();
	
	/**
	* 描述：开放站点
	* @param id
	*/
	public void openSite(String id);
	
	
}
