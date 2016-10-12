package com.jhcz.plat.service;

import java.util.List;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.plat.domain.Site;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-30
 * 创建时间: 15:58:34
 */
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
    public void closeSite(int id);
    
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
    *
    * @return 结果为一个List,其中每一个元素为一个Site对象
    */
    public List<Object> getAllSite();
    
    /**
    * 描述：根据站点所有信息进行分页处理
    * @param curPage
    * @param numPerPage
    * @param keyword
    * @return
    */
    public DBPage getPageData(int curPage, int numPerPage, String keyword);
    
    /**
    * 描述：开放站点
    * @param id
    */
    public void openSite(int id);
    
    public void updateSite(Site site);
}
