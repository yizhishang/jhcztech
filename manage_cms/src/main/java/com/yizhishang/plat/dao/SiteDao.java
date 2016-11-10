package com.yizhishang.plat.dao;

import java.util.List;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.plat.domain.Site;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-30
 * 创建时间: 15:45:41
 */
public interface SiteDao
{
    public void addSite(Site site);

    public void deleteSite(int id);

    public List<Object> findAllSiteInfo();

    public Site findSiteById(int id);
    
    public Site findSiteBySiteName(String siteName);

    public List<Object> findSiteBySiteno(String siteno);

    public Site findSiteBySiteNO(String siteNO);

    public boolean findSiteIsMain(String isMain);

    public List<Site> getAllSite();

    public DBPage getPageData(int curPage, int numPerPage, String keyword);

    public void updateSite(Site site);
}
