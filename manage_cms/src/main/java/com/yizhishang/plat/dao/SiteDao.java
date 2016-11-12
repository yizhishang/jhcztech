package com.yizhishang.plat.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhishang.base.dao.BaseDao;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.domain.Site;

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
public class SiteDao extends BaseDao
{
    private static String GET_ALL = "select * from T_SITE order by id";
    private static String FIND_SITE_BY_SITENO = "select * from T_SITE where siteno=?";
    private static String FIND_SITE_BY_SITENAME = "select * from T_SITE where name=?";
    private static String FIND_SITE_BY_ID = "select * from T_SITE where id=?";


    public void addSite(Site site)
    {
        DynaModel dataRow = new DynaModel();
        dataRow.putAll(site.toMap());
        getJdbcTemplateUtil().insert("T_SITE", dataRow);
    }

    public void deleteSite(int id)
    {
        getJdbcTemplateUtil().delete("T_SITE", "id", new Integer(id));
    }

    public List<DynaModel> findAllSiteInfo()
    {
        String sql = "select * from t_site where state='1' order by id";
        List<DynaModel> list = getJdbcTemplateUtil().queryForList(sql, DynaModel.class);
        return list;
    }

    /**
     * 描述：根据ID查找站点信息
     * @param id
     * @return
     */
    public Site findSiteById(int id)
    {
        DynaModel dataRow = getJdbcTemplateUtil().queryMap(FIND_SITE_BY_ID, new Object[]{new Integer(id)});
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
    public Site findSiteBySiteName(String siteName)
    {
    	DynaModel row = getJdbcTemplateUtil().queryMap(FIND_SITE_BY_SITENAME, new Object[]{siteName});
        if (row != null)
        {
            Site site = new Site();
            site.fromMap(row);
            return site;
        }
        return null;
    }

    public List<DynaModel> findSiteBySiteno(String siteno)
    {
        String sql = "select * from t_site where siteno in (" + siteno + ") and state='1' order by id";
        List<DynaModel> list = getJdbcTemplateUtil().queryForList(sql, DynaModel.class);
        return list;
    }

    public Site findSiteBySiteNO(String siteNO)
    {
    	return getJdbcTemplateUtil().queryMap(FIND_SITE_BY_SITENO, Site.class, new Object[]{siteNO});
    }

    public boolean findSiteIsMain(String isMain)
    {
        String sql = "select is_main from t_site where is_main=?";
        List<DynaModel> list = getJdbcTemplateUtil().queryForList(sql, DynaModel.class, new Object[] { isMain });
        if(list.size() == 0)
        {
            return false;
        }
        return true;
    }

    public List<Site> getAllSite()
    {
        return getJdbcTemplateUtil().queryForList(GET_ALL, Site.class);
    }

    public DBPage<DynaModel> getPageData(int curPage, int numPerPage, String keyword)
    {
        DBPage<DynaModel> page = null;

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
        page = getJdbcTemplateUtil().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);

        if (page != null)
        {
            List<DynaModel> dataList = page.getData();
            ArrayList<DynaModel> newDataList = new ArrayList<DynaModel>();
            for (Iterator<DynaModel> iter = dataList.iterator(); iter.hasNext();)
            {
                Site site = new Site();
                DynaModel row = (DynaModel) iter.next();
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
    public void updateSite(Site site)
    {
        DynaModel dataRow = new DynaModel();
        dataRow.putAll(site.toMap());
        getJdbcTemplateUtil().update("T_SITE", dataRow, "id", new Integer(site.getId()));
    }
}
