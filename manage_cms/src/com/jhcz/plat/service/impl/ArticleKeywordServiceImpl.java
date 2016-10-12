package com.jhcz.plat.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.service.BaseService;
import com.jhcz.plat.dao.ArticleKeywordDao;
import com.jhcz.plat.domain.ArticleKeyword;
import com.jhcz.plat.service.ArticleKeywordService;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-20
 * 创建时间: 12:11:47
 */
@Service
public class ArticleKeywordServiceImpl extends BaseService implements ArticleKeywordService
{
    @Override
    public void addArticleKeyword(ArticleKeyword articleKeyword)
    {
        String id = getSeqValue("T_ARTICLE_KEYWORD");
        articleKeyword.setId(Integer.parseInt(id));
        ArticleKeywordDao articleKeywordDao = (ArticleKeywordDao) getDao(ArticleKeywordDao.class);
        articleKeywordDao.addArticleKeyword(articleKeyword);
    }

    @Override
    public void deleteArticleKeyword(int id)
    {
        ArticleKeywordDao articleKeywordDao = (ArticleKeywordDao) getDao(ArticleKeywordDao.class);
        articleKeywordDao.deleteArticleKeyword(id);
    }

    @Override
    public List<ArticleKeyword> findAll()
    {
        ArticleKeywordDao articleKeywordDao = (ArticleKeywordDao) getDao(ArticleKeywordDao.class);
        return articleKeywordDao.findAll();
    }

    @Override
    public ArticleKeyword findById(int id)
    {
        ArticleKeywordDao articleKeywordDao = (ArticleKeywordDao) getDao(ArticleKeywordDao.class);
        return articleKeywordDao.findById(id);
    }

    @Override
    public DBPage getPageData(int curPage, int numPerPage, String name)
    {
        ArticleKeywordDao articleKeywordDao = (ArticleKeywordDao) getDao(ArticleKeywordDao.class);
        return articleKeywordDao.getPageData(curPage, numPerPage, name);
    }

    @Override
    public void updateArticlKeyword(ArticleKeyword articleKeyword)
    {
        ArticleKeywordDao articleKeywordDao = (ArticleKeywordDao) getDao(ArticleKeywordDao.class);
        articleKeywordDao.updateArticlKeyword(articleKeyword);
    }
}
