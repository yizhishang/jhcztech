package com.jhcz.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jhcz.base.domain.Site;
import com.jhcz.base.mybatis.service.SequenceService;
import com.jhcz.web.dao.SiteDao;
import com.jhcz.web.service.SiteService;

/**
 * 描述: SiteServiceImpl.java
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-2-29
 * 创建时间: 下午2:27:45
 */
@Service
public class SiteServiceImpl implements SiteService
{
	
	@Resource
	SequenceService sequenceService;
    
    @Resource
    private SiteDao siteDao;
    
    /**
    * 添加一个站点
    * @param site 站点对象
    */
    @Override
    public void addSite(Site site)
    {
        String id = sequenceService.getNextSequence("T_SITE");
        site.setId(id);
        siteDao.addSite(site);
    }
    
    /**
    * 描述：关闭站点
    * @param id
    */
    @Override
    public void closeSite(String id)
    {
        Site site = new Site();
        site.setId(id);
        site.setState(Site.STATE_CLOSE);
        siteDao.updateSite(site);
    }
    
    /**
    * 根据站点ID，删除一个站点
    * @param id 站点的ID
    */
    @Override
    public void deleteSite(int id)
    {
        siteDao.deleteSite(id);
    }
    
    /**
    * 描述：查找所有站点信息
    * @return
    */
    @Override
    public List<Object> findAllSiteInfo()
    {
        return siteDao.findAllSiteInfo();
    }
    
    /**
    * 描述：根据ID查找站点信息
    * @param id
    * @return
    */
    @Override
    public Site findSiteById(int id)
    {
        return siteDao.findSiteById(id);
    }
    
    /**
    * 
    * @描述：根据sitename查找对应的站点
    * @作者：袁永君
    * @时间：2012-3-2 下午03:10:40
    * @param siteName
    * @return
    */
    @Override
    public Site findSiteBySiteName(String siteName)
    {
        return siteDao.findSiteBySiteName(siteName);
    }
    
    /**
    * 描述：可以根据一到多个站点编号来查询站点信息
    * @param siteno
    * @return
    */
    @Override
    public List<Object> findSiteBySiteno(String siteno)
    {
        return siteDao.findSiteBySiteno(siteno);
    }
    
    /**
    * 根据siteno查找对应的站点
    * @param siteNO 站点的no
    * @return 若查找到，则返回Site对象，若站点不存在，则返回null
    */
    @Override
    public Site findSiteBySiteNO(String siteNO)
    {
        return siteDao.findSiteBySiteNO(siteNO);
    }
    
    /**
    * 描述：查找主站点是否存在，如果已经存在就返回true，否则返回flase
    * @param isMain
    * @return
    */
    @Override
    public boolean findSiteIsMain(String isMain)
    {
        return siteDao.findSiteIsMain(isMain);
    }
    
    /**
    * 返回所有的站点列表，
    * @return 结果为一个List,其中每一个元素为一个Site对象
    */
    @Override
    public List<Site> getAllSite()
    {
        return siteDao.getAllSite();
    }
    
    /**
    * 描述：开放站点
    * @param id
    */
    @Override
    public void openSite(String id)
    {
        Site site = new Site();
        site.setId(id);
        site.setState(Site.STATE_OPEN);
        siteDao.updateSite(site);
    }
    
    public void updateSite(Site site)
    {
        siteDao.updateSite(site);
    }
}
