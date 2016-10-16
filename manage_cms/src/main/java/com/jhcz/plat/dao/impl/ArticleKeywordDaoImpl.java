package com.jhcz.plat.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.util.StringHelper;
import com.jhcz.plat.dao.ArticleKeywordDao;
import com.jhcz.plat.dao.BaseDao;
import com.jhcz.plat.domain.ArticleKeyword;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-20
 * 创建时间: 11:55:19
 */
public class ArticleKeywordDaoImpl extends BaseDao implements ArticleKeywordDao
{
    @Override
    public void addArticleKeyword(ArticleKeyword articleKeyword)
    {
        DataRow dataRow = new DataRow();
        dataRow.putAll(articleKeyword.toMap());
        getJdbcTemplate().insert("T_ARTICLE_KEYWORD", dataRow);
    }

    @Override
    public void deleteArticleKeyword(int id)
    {
        getJdbcTemplate().delete("T_ARTICLE_KEYWORD", "ID", new Integer(id));
    }

    @Override
    public List<ArticleKeyword> findAll()
    {
        List<Object> dataList = getJdbcTemplate().query("select * from T_ARTICLE_KEYWORd");
        ArrayList<ArticleKeyword> newDataList = new ArrayList<ArticleKeyword>();
        for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
        {
            ArticleKeyword articleKeyword = new ArticleKeyword();
            DataRow row = (DataRow) iter.next();
            articleKeyword.fromMap(row);
            newDataList.add(articleKeyword);
        }
        return newDataList;
    }

    @Override
    public ArticleKeyword findById(int id)
    {
        DataRow dataRow = getJdbcTemplate().queryMap("select * from T_ARTICLE_KEYWORD where id=? ", new Object[]{new Integer(id)});
        if (dataRow == null)
            return null;

        ArticleKeyword articleKeyword = new ArticleKeyword();
        articleKeyword.fromMap(dataRow);
        return articleKeyword;
    }

    @Override
    public DBPage getPageData(int curPage, int numPerPage, String name)
    {
        DBPage page = null;
        StringBuffer sqlBuffer = new StringBuffer();
        ArrayList<String> argList = new ArrayList<String>();
        sqlBuffer.append("select * from T_ARTICLE_KEYWORD where 1=1 ");
        if (!StringHelper.isEmpty(name))
        {
            sqlBuffer.append(" and name like ? ");
            argList.add("%" + name + "%");
        }
        sqlBuffer.append(" order by id desc ");
        page = getJdbcTemplate().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);

        if (page != null)
        {
            List<Object> dataList = page.getData();
            ArrayList<Object> newDataList = new ArrayList<Object>();
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
            {
                ArticleKeyword articleKeyword = new ArticleKeyword();
                DataRow row = (DataRow) iter.next();
                articleKeyword.fromMap(row);
                newDataList.add(articleKeyword);
            }
            page.setData(newDataList);
        }

        return page;
    }

    @Override
    public void updateArticlKeyword(ArticleKeyword articleKeyword)
    {
        int id = articleKeyword.getId();
        DataRow dataRow = new DataRow();
        dataRow.putAll(articleKeyword.toMap());
        getJdbcTemplate().update("T_ARTICLE_KEYWORD", dataRow, "ID", new Integer(id));
    }
}