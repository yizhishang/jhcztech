package com.yizhishang.plat.service;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.StringHelper;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:
 * 版本:	 1.0
 * 创建日期: 2010-3-10
 * 创建时间: 上午10:45:16
 */
@Service
public class PublishPlanService extends BaseService
{

    public void addPublishPlan(DynaModel data)
    {
        String id = getSeqValue("T_PUBLISH_PLAN");
        data.set("id", id);
        getJdbcTemplateUtil().insert("T_PUBLISH_PLAN", data);
    }

    public void delAllPublishPlan()
    {
        getJdbcTemplateUtil().update("DELETE FROM T_PUBLISH_PLAN");
    }

    public void delPublishPlan(int id)
    {
        getJdbcTemplateUtil().delete("T_PUBLISH_PLAN", "id", new Integer(id));
    }

    public void editPublishPlan(DynaModel data)
    {
        getJdbcTemplateUtil().update("T_PUBLISH_PLAN", data, "id", data.getString("id"));
    }

    public DBPage<DynaModel> findPublishPlan(int curPage, int numPerPage, String catalogId, String name)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        StringBuffer strBuf = new StringBuffer("SELECT A.*,B.NAME FROM T_PUBLISH_PLAN A,T_CATALOG B WHERE A" + "" +
                ".CATALOG_ID = B.CATALOG_ID");
        if (StringHelper.isNotEmpty(catalogId)) {
            strBuf.append(" AND A.CATALOG_ID = ?");
            argList.add(catalogId);
        }
        if (StringHelper.isNotEmpty(name)) {
            strBuf.append(" AND B.NAME LIKE ?");
            argList.add("%" + name + "%");
        }
        strBuf.append("  ORDER BY B.ORDERLINE");

        return getJdbcTemplateUtil().queryPage(strBuf.toString(), argList.toArray(), curPage, numPerPage);
    }

    public DynaModel findPublishPlanById(String id)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        String sql = "SELECT A.*,B.NAME FROM T_PUBLISH_PLAN A,T_CATALOG B WHERE A.CATALOG_ID = B.CATALOG_ID AND A.ID " +
                "" + "= ?";
        argList.add(id);
        try {
            return getJdbcTemplateUtil().queryMap(sql, argList.toArray());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public List<DynaModel> findPublishPlanByType(String type)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        String sql = "SELECT * FROM T_PUBLISH_PLAN WHERE TYPE = ?";
        argList.add(type);
        return getJdbcTemplateUtil().queryForList(sql, argList.toArray());
    }
}
