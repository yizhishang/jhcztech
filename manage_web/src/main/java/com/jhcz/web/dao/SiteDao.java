package com.jhcz.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.jhcz.base.domain.Site;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-30
 * 创建时间: 15:45:41
 */
@Repository
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

    @Select("select * from t_site")
    public List<Site> getAllSite();

    public void updateSite(Site site);
}
