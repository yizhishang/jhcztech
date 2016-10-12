package com.jhcz.plat.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.util.StringHelper;
import com.jhcz.plat.dao.BaseDao;
import com.jhcz.plat.dao.SiteDao;
import com.jhcz.plat.domain.Site;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-30
 * 创建时间: 15:47:24
 */
@Repository
public class SiteDaoImpl extends BaseDao implements SiteDao
{
    private static String GET_ALL = "select * from T_SITE order by id";
    private static String FIND_SITE_BY_SITENO = "select * from T_SITE where siteno=?";
    private static String FIND_SITE_BY_SITENAME = "select * from T_SITE where name=?";
    private static String FIND_SITE_BY_ID = "select * from T_SITE where id=?";


    @Override
    public void addSite(Site site)
    {
        DataRow dataRow = new DataRow();
        dataRow.putAll(site.toMap());
        getJdbcTemplate().insert("T_SITE", dataRow);
    }

    @Override
    public void deleteSite(int id)
    {
        getJdbcTemplate().delete("T_SITE", "id", new Integer(id));
    }

    @Override
    public List<Object> findAllSiteInfo()
    {
        String sql = "select * from t_site where state='1' order by id";
        List<Object> list = getJdbcTemplate().query(sql);
        return list;
    }

    /**
     * 描述：根据ID查找站点信息
     * @param id
     * @return
     */
    @Override
    public Site findSiteById(int id)
    {
        DataRow dataRow = getJdbcTemplate().queryMap(FIND_SITE_BY_ID, new Object[]{new Integer(id)});
        if (dataRow == null)
            return null;
        Site site = new Site();
        site.fromMap(dataRow);
        return site;
    }
    
    /**
     * 
     * @描述：
     * @作者：袁永君
     * @时间：2012-3-2 下午03:09:52
     * @param siteName
     * @return
     */
    @Override
    public Site findSiteBySiteName(String siteName)
    {
    	DataRow row = getJdbcTemplate().queryMap(FIND_SITE_BY_SITENAME, new Object[]{siteName});
        if (row != null)
        {
            Site site = new Site();
            site.fromMap(row);
            return site;
        }
        return null;
    }

    @Override
    public List<Object> findSiteBySiteno(String siteno)
    {
        String sql = "select * from t_site where siteno in (" + siteno + ") and state='1' order by id";
        List<Object> list = getJdbcTemplate().query(sql);
        return list;
    }

    @Override
    public Site findSiteBySiteNO(String siteNO)
    {
        DataRow row = getJdbcTemplate().queryMap(FIND_SITE_BY_SITENO, new Object[]{siteNO});
        if (row != null)
        {
            Site site = new Site();
            site.fromMap(row);
            return site;
        }
        return null;
    }

    @Override
    public boolean findSiteIsMain(String isMain)
    {
        String sql = "select is_main from t_site where is_main=?";
        List<Object> list = getJdbcTemplate().query(sql, new Object[] { isMain });
        if(list.size() == 0)
        {
            return false;
        }
        return true;
    }

    @Override
    public List<Object> getAllSite()
    {
        List<Object> siteList = getJdbcTemplate().query(GET_ALL);
        ArrayList<Object> newSiteList = new ArrayList<Object>();
        for (Iterator<Object> iter = siteList.iterator(); iter.hasNext();)
        {
            Site site = new Site();
            DataRow row = (DataRow) iter.next();
            site.fromMap(row);
            newSiteList.add(site);
        }
        return newSiteList;
    }

    @Override
    public DBPage getPageData(int curPage, int numPerPage, String keyword)
    {
        DBPage page = null;

        StringBuffer sqlBuffer = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuffer.append("select * from T_SITE where 1=1 ");
        /*if (!StringHelper.isEmpty(siteNo))
        {
            sqlBuffer.append(" and siteno = ? ");
            argList.add(siteNo);
        }*/
        if (!StringHelper.isEmpty(keyword))
        {
            sqlBuffer.append(" and (siteno like ? or name like ?) ");
            argList.add("%" + keyword + "%");
            argList.add("%" + keyword + "%");
        }
        sqlBuffer.append(" order by id desc ");
        page = getJdbcTemplate().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);

        if (page != null)
        {
            List<Object> dataList = page.getData();
            ArrayList<Object> newDataList = new ArrayList<Object>();
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
            {
                Site site = new Site();
                DataRow row = (DataRow) iter.next();
                site.fromMap(row);
                newDataList.add(site);
            }
            page.setData(newDataList);
        }

        return page;
    }

    /**
     * 描述：更新站点信息
     * @param site
     */
    @Override
    public void updateSite(Site site)
    {
        DataRow dataRow = new DataRow();
        dataRow.putAll(site.toMap());
        getJdbcTemplate().update("T_SITE", dataRow, "id", new Integer(site.getId()));
    }
}