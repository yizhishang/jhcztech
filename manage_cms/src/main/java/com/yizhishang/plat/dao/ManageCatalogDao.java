package com.yizhishang.plat.dao;

import com.yizhishang.base.dao.BaseDao;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.domain.ManageCatalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * 版权:	 Copyright (c) 2007
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-3-27
 * 创建时间: 11:00:27
 */
@Repository
public class ManageCatalogDao extends BaseDao
{

    private static Logger logger = LoggerFactory.getLogger(ManageCatalogDao.class);

    public void addCatalog(ManageCatalog catalog)
    {
        DynaModel dataRow = new DynaModel();
        dataRow.putAll(catalog.toMap());
        getJdbcTemplateUtil().insert("T_MANAGE_CATALOG", dataRow);
    }

    public void deleteCatalog(int catalogId)
    {
        getJdbcTemplateUtil().delete("T_MANAGE_CATALOG", "catalog_id", new Integer(catalogId));
    }

    public void deleteCatalog(int catalogId, String siteno)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("delete from T_MANAGE_CATALOG where catalog_id=? and siteno=?");
        argList.add(new Integer(catalogId));
        argList.add(siteno);
        getJdbcTemplateUtil().update(sqlBuf.toString(), argList.toArray());
    }

    public ManageCatalog findCatalogById(int catalogId)
    {
        String sql = "select * from T_MANAGE_CATALOG where catalog_id=?";
        try {
            return getJdbcTemplateUtil().queryMap(sql, ManageCatalog.class, new Object[] {new Integer(catalogId)});
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public ManageCatalog findCatalogById(int catalogId, String siteno)
    {
        String sql = "select * from T_MANAGE_CATALOG where catalog_id=?";
        try {
            return getJdbcTemplateUtil().queryMap(sql, ManageCatalog.class, new Object[] {new Integer(catalogId)});
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public List<ManageCatalog> findCatalogInfoByParentId(int parentId, String siteno)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("select * from t_manage_catalog where state='1' ");
        if (parentId != 0) {
            sqlBuf.append(" and parent_id=?");
            argList.add(new Integer(parentId));
        }
        if (StringHelper.isNotEmpty(siteno)) {
            sqlBuf.append(" and siteno=?");
            argList.add(siteno);
        }
        sqlBuf.append(" order by orderline");
        List<ManageCatalog> list = getJdbcTemplateUtil().queryForList(sqlBuf.toString(), ManageCatalog.class, argList
                .toArray());
        return list;
    }

    public List<ManageCatalog> findCatalogLikeRoute(int catalogId, String siteno)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("select * from t_manage_catalog where state='1' ");
        if (catalogId != 0) {
            sqlBuf.append(" and route like ?");
            if (catalogId == 1) {
                argList.add("1|%");
            } else {
                argList.add("%|" + catalogId + "|%");
            }
        }
        if (StringHelper.isNotEmpty(siteno)) {
            sqlBuf.append(" and siteno = ?");
            argList.add(siteno);
        }
        sqlBuf.append(" order by orderline");
        List<ManageCatalog> list = getJdbcTemplateUtil().queryForList(sqlBuf.toString(), ManageCatalog.class, argList
                .toArray());
        return list;
    }

    public List<ManageCatalog> findChildrenById(int catalogId)
    {
        String sql = "select * from T_MANAGE_CATALOG where parent_id=? order by orderline";
        List<ManageCatalog> dataList = getJdbcTemplateUtil().queryForList(sql, ManageCatalog.class, new Object[] {new
                Integer(catalogId)});
        return dataList;
    }

    public List<ManageCatalog> findChildrenById(int catalogId, String siteNo)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        String sql = "select * from T_MANAGE_CATALOG where parent_id=? ";
        argList.add(new Integer(catalogId));
        if (!StringHelper.isEmpty(siteNo)) {
            sql += " and siteno=?";
            argList.add(siteNo);
        }
        sql += " order by orderline";

        List<ManageCatalog> dataList = getJdbcTemplateUtil().queryForList(sql, ManageCatalog.class, argList.toArray());
        return dataList;
    }

    /**
     * 描述:  查询父栏目下的节点数
     * 作者:
     * 创建日期: 2010-1-7
     * 创建时间: 下午01:43:10
     *
     * @return int
     * @throws
     */
    public int findParentCatalogNum(int parentId)
    {
        try {
            String sql = "SELECT COUNT(CATALOG_ID) FROM T_MANAGE_CATALOG WHERE PARENT_ID = ?";
            List<Object> argList = new ArrayList<Object>();
            argList.add(new Integer(parentId));
            return getJdbcTemplateUtil().queryInt(sql, argList.toArray());
        } catch (Exception ex) {
            logger.error("", ex);
            return 0;
        }
    }

    public ManageCatalog findRootCatalog(String siteNo)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        String sql = "select * from T_MANAGE_CATALOG where siteno like ? and parent_id=0";
        argList.add("%" + siteNo + "%");
        try {
            return getJdbcTemplateUtil().queryMap(sql, ManageCatalog.class, argList.toArray());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public ManageCatalog getParent(int catalogId)
    {
        String sql = "select parent_id from T_MANAGE_CATALOG where catalog_id=?";
        int parentId = getJdbcTemplateUtil().queryInt(sql, new Object[] {new Integer(catalogId)});
        if (parentId > 0)
            return findCatalogById(parentId);
        else
            return null;
    }

    public String getRoute(int catalogId)
    {
        if (catalogId <= 0)
            return "";

        String route = String.valueOf(catalogId);
        ManageCatalog catalog = null;
        while ((catalog = getParent(catalogId)) != null) {
            catalogId = catalog.getId();
            route = String.valueOf(catalogId) + "|" + route;
        }
        return route;
    }

    public void updateCatalog(ManageCatalog catalog)
    {
        DynaModel dataRow = new DynaModel();
        dataRow.putAll(catalog.toMap());
        getJdbcTemplateUtil().update("T_MANAGE_CATALOG", dataRow, "catalog_id", new Integer(catalog.getId()));
    }

    /**
     * 描述:  更新父栏目拥有的节点数
     * 作者:
     * 创建日期: 2010-1-7
     * 创建时间: 下午01:46:17
     *
     * @return void
     * @throws
     */
    public void updateParentCatalogNum(int catalogId, int childrennum)
    {
        String sql = "UPDATE T_MANAGE_CATALOG SET CHILDRENNUM = ? WHERE CATALOG_ID = ?";
        List<Object> argList = new ArrayList<Object>();
        argList.add(new Integer(childrennum));
        argList.add(new Integer(catalogId));
        getJdbcTemplateUtil().update(sql, argList.toArray());
    }
}
