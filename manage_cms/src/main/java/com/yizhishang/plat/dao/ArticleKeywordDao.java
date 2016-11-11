package com.yizhishang.plat.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhishang.base.dao.BaseDao;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.domain.ArticleKeyword;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-20
 * 创建时间: 11:55:19
 */
@Repository
public class ArticleKeywordDao extends BaseDao
{
    public void addArticleKeyword(ArticleKeyword articleKeyword)
    {
        DynaModel dataRow = new DynaModel();
        dataRow.putAll(articleKeyword.toMap());
        getJdbcTemplateUtil().insert("T_ARTICLE_KEYWORD", dataRow);
    }

    public void deleteArticleKeyword(int id)
    {
        getJdbcTemplateUtil().delete("T_ARTICLE_KEYWORD", "ID", new Integer(id));
    }

    public List<ArticleKeyword> findAll()
    {
        List<ArticleKeyword> dataList = getJdbcTemplateUtil().queryForList("select * from T_ARTICLE_KEYWORd", ArticleKeyword.class);
        return dataList;
    }

    public ArticleKeyword findById(int id)
    {
        DynaModel dataRow = getJdbcTemplateUtil().queryMap("select * from T_ARTICLE_KEYWORD where id=? ", new Object[]{new Integer(id)});
        if (dataRow == null)
            return null;

        ArticleKeyword articleKeyword = new ArticleKeyword();
        articleKeyword.fromMap(dataRow);
        return articleKeyword;
    }

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
        page = getJdbcTemplateUtil().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);

        if (page != null)
        {
            List<DynaModel> dataList = page.getData();
            ArrayList<DynaModel> newDataList = new ArrayList<DynaModel>();
            for (Iterator<DynaModel> iter = dataList.iterator(); iter.hasNext();)
            {
                ArticleKeyword articleKeyword = new ArticleKeyword();
                DynaModel row = (DynaModel) iter.next();
                articleKeyword.fromMap(row);
                newDataList.add(articleKeyword);
            }
            page.setData(newDataList);
        }

        return page;
    }

    public void updateArticlKeyword(ArticleKeyword articleKeyword)
    {
        int id = articleKeyword.getId();
        DynaModel dataRow = new DynaModel();
        dataRow.putAll(articleKeyword.toMap());
        getJdbcTemplateUtil().update("T_ARTICLE_KEYWORD", dataRow, "ID", new Integer(id));
    }
}
