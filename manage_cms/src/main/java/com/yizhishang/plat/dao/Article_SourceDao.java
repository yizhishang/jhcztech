package com.yizhishang.plat.dao;

import com.yizhishang.base.dao.BaseDao;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.domain.Article_Source;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-21
 * 创建时间: 10:47:32
 */
@Repository
public class Article_SourceDao extends BaseDao
{

    private static String FIND_ARTICLE_SOURCE_ID = "select * from T_ARTICLE_SOURCE where id=?";

    private static String UPDATE_ARTICLE_SOURCE_ID = "update T_ARTICLE_SOURCE set NAME=?,URL=?,MODIFIED_BY=?," +
            "MODIFIED_DATE=? where id=?";

    public void addArticle_Source(Article_Source article_source)
    {
        String id = BaseService.getSequenceGenerator().getNextSequence("T_ARTICLE_SOURCE");
        int id_article_source = Integer.parseInt(id);
        article_source.setId(id_article_source);
        DynaModel dr = new DynaModel();
        dr.putAll(article_source.toMap());
        this.getJdbcTemplateUtil().insert("T_ARTICLE_SOURCE", dr);
    }

    public void deleteArticle_Source(int id)
    {
        this.getJdbcTemplateUtil().delete("T_ARTICLE_SOURCE", "ID", new Integer(id));
    }

    /**
     * 通过ID查找信息来源
     *
     * @param id
     * @return
     */
    public Article_Source findArticle_SourceById(int id)
    {
        DynaModel dw;
        try {
            dw = this.getJdbcTemplateUtil().queryMap(FIND_ARTICLE_SOURCE_ID, new Object[] {new Integer(id)});
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }
        if (dw != null) {
            Article_Source as = new Article_Source();
            as.fromMap(dw);

            return as;
        }
        return null;
    }

    /**
     * 描述：查找信息源数据
     * 作者：袁永君  lanlei@yizhishang.com
     * 2015-9-25 下午09:43:54
     *
     * @param siteNo
     * @return
     */
    public List<Article_Source> findArticle_SourceBySiteNo(String siteNo)
    {
        ArrayList<String> argList = new ArrayList<String>();
        StringBuffer sqlBuf = new StringBuffer();

        sqlBuf.append("SELECT * FROM T_ARTICLE_SOURCE WHERE 1=1");

        if (StringHelper.isNotBlank(siteNo)) {
            sqlBuf.append(" AND SITENO=?");
            argList.add(siteNo);
        }

        sqlBuf.append(" ORDER BY NAME,ID DESC");
        return getJdbcTemplateUtil().queryForList(sqlBuf.toString(), Article_Source.class, argList.toArray());
    }

    public DBPage<DynaModel> getPageData(int curPage, int numPerPage, String siteNo, String keyword)
    {
        DBPage<DynaModel> page = null;
        StringBuffer sqlBuffer = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuffer.append("select * from T_ARTICLE_SOURCE where 1=1 ");
        if (!StringHelper.isEmpty(siteNo)) {
            sqlBuffer.append(" and siteno = ? ");
            argList.add(siteNo);
        }
        if (!StringHelper.isEmpty(keyword)) {
            sqlBuffer.append(" and (url like ? or name like ?) ");
            argList.add("%" + keyword + "%");
            argList.add("%" + keyword + "%");
        }
        sqlBuffer.append(" order by id desc ");
        page = getJdbcTemplateUtil().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);

        if (page != null) {
            List<DynaModel> dataList = page.getData();
            ArrayList<DynaModel> newDataList = new ArrayList<DynaModel>();
            for (Iterator<DynaModel> iter = dataList.iterator(); iter.hasNext(); ) {
                Article_Source article_source = new Article_Source();
                DynaModel row = (DynaModel) iter.next();
                article_source.fromMap(row);
                newDataList.add(article_source);
            }
            page.setData(newDataList);
        }
        return page;
    }

    public void updateArticle_Source(Article_Source article_source)
    {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(article_source.getName());
        list.add(article_source.getUrl());
        list.add(article_source.getModified_by());
        list.add(article_source.getModified_date());
        list.add(new Integer(article_source.getId()));
        this.getJdbcTemplateUtil().update(UPDATE_ARTICLE_SOURCE_ID, list.toArray());
    }
}
