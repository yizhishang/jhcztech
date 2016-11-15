package com.yizhishang.plat.service;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.service.BaseService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述: SeoServiceImpl.java
 * 版权: Copyright (c) 2015
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2015-12-3
 * 创建时间: 下午10:48:44
 */
@Service
public class SeoService extends BaseService
{

    public void addSeo(DynaModel dataRow)
    {
        String id = getSeqValue("T_CATALOG_SEO");
        dataRow.set("id", id);
        getJdbcTemplateUtil().insert("T_CATALOG_SEO", dataRow);
    }

    public void editSeo(DynaModel dataRow)
    {
        getJdbcTemplateUtil().update("T_CATALOG_SEO", dataRow, "id", dataRow.getString("id"));
    }

    public DynaModel findSeoByCatalogid(int catalogId, String siteNo)
    {
        List<Object> argList = new ArrayList<Object>();
        String sql = "SELECT * FROM T_CATALOG_SEO WHERE CATALOG_ID = ? AND SITENO = ?";
        argList.add(new Integer(catalogId));
        argList.add(siteNo);
        try {
            return getJdbcTemplateUtil().queryMap(sql, argList.toArray());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
