package com.jhcz.plat.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.service.BaseService;
import com.jhcz.plat.dao.Article_SourceDao;
import com.jhcz.plat.domain.Article_Source;
import com.jhcz.plat.service.Article_SourceService;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-21
 * 创建时间: 10:44:17
 */
@Service
public class Article_SourceServiceImpl extends BaseService implements Article_SourceService
{

    @Override
    public void addArticle_Source(Article_Source article_source)
    {
        getArticle_SourceDao().addArticle_Source(article_source);
    }

    @Override
    public void deleteArticle_Source(int id)
    {
        getArticle_SourceDao().deleteArticle_Source(id);
    }

    @Override
    public Article_Source findArticle_SourceById(int id)
    {
        return getArticle_SourceDao().findArticle_SourceById(id);
    }

    @Override
    public List<Object> findArticle_SourceBySiteNo(String siteNo)
    {
        return getArticle_SourceDao().findArticle_SourceBySiteNo(siteNo);
    }

    public Article_SourceDao getArticle_SourceDao()
    {
        return (Article_SourceDao) getDao(Article_SourceDao.class);
    }

    @Override
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword)
    {
        return getArticle_SourceDao().getPageData(curPage, numPerPage, siteNo, keyword);
    }

    @Override
    public void updateArticle_Source(Article_Source article_source)
    {
        getArticle_SourceDao().updateArticle_Source(article_source);
    }
}
