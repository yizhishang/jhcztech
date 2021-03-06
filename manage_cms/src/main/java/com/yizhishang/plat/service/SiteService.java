package com.yizhishang.plat.service;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.plat.dao.SiteDao;
import com.yizhishang.plat.domain.Site;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-30
 * 创建时间: 16:00:02
 */
@Service
public class SiteService extends BaseService
{

    @Resource
    private SiteDao siteDao;

    /**
     * 添加一个站点
     *
     * @param site 站点对象
     */
    public void addSite(Site site)
    {
        String id = getSequenceGenerator().getNextSequence("T_SITE");
        site.setId(Integer.parseInt(id));
        siteDao.addSite(site);
    }

    /**
     * 描述：关闭站点
     *
     * @param id
     */
    public void closeSite(int id)
    {
        Site site = new Site();
        site.setId(id);
        site.setState(Site.STATE_CLOSE);
        siteDao.updateSite(site);
    }

    /**
     * 根据站点ID，删除一个站点
     *
     * @param id 站点的ID
     */
    public void deleteSite(int id)
    {
        siteDao.deleteSite(id);
    }

    /**
     * 描述：查找所有站点信息
     *
     * @return
     */
    public List<DynaModel> findAllSiteInfo()
    {
        return siteDao.findAllSiteInfo();
    }

    /**
     * 描述：根据ID查找站点信息
     *
     * @param id
     * @return
     */
    public Site findSiteById(int id)
    {
        return siteDao.findSiteById(id);
    }

    /**
     * @param siteName
     * @return
     * @描述：根据sitename查找对应的站点
     * @作者：袁永君
     * @时间：2012-3-2 下午03:10:40
     */
    public Site findSiteBySiteName(String siteName)
    {
        return siteDao.findSiteBySiteName(siteName);
    }

    /**
     * 描述：可以根据一到多个站点编号来查询站点信息
     *
     * @param siteno
     * @return
     */
    public List<DynaModel> findSiteBySiteno(String siteno)
    {
        return siteDao.findSiteBySiteno(siteno);
    }

    /**
     * 根据siteno查找对应的站点
     *
     * @param siteNO 站点的no
     * @return 若查找到，则返回Site对象，若站点不存在，则返回null
     */
    public Site findSiteBySiteNO(String siteNO)
    {
        return siteDao.findSiteBySiteNO(siteNO);
    }

    /**
     * 描述：查找主站点是否存在，如果已经存在就返回true，否则返回flase
     *
     * @param isMain
     * @return
     */
    public boolean findSiteIsMain(String isMain)
    {
        return siteDao.findSiteIsMain(isMain);
    }

    /**
     * 返回所有的站点列表，
     *
     * @return 结果为一个List, 其中每一个元素为一个Site对象
     */
    public List<Site> getAllSite()
    {
        return siteDao.getAllSite();
    }

    /**
     * 描述：根据站点所有信息进行分页处理
     *
     * @param curPage
     * @param numPerPage
     * @param keyword
     * @return
     */
    public DBPage<DynaModel> getPageData(int curPage, int numPerPage, String keyword)
    {
        return siteDao.getPageData(curPage, numPerPage, keyword);
    }

    /**
     * 描述：开放站点
     *
     * @param id
     */
    public void openSite(int id)
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
