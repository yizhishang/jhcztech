package com.yizhishang.plat.service;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.plat.dao.ArticleKeywordDao;
import com.yizhishang.plat.domain.ArticleKeyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
public class ArticleKeywordService extends BaseService
{

    @Autowired
    ArticleKeywordDao articleKeywordDao;

    public void addArticleKeyword(ArticleKeyword articleKeyword)
    {
        String id = getSeqValue("T_ARTICLE_KEYWORD");
        articleKeyword.setId(Integer.parseInt(id));

        articleKeywordDao.addArticleKeyword(articleKeyword);
    }

    public void deleteArticleKeyword(int id)
    {
        articleKeywordDao.deleteArticleKeyword(id);
    }

    public List<ArticleKeyword> findAll()
    {
        return articleKeywordDao.findAll();
    }

    public ArticleKeyword findById(int id)
    {
        return articleKeywordDao.findById(id);
    }

    public DBPage<DynaModel> getPageData(int curPage, int numPerPage, String name)
    {
        return articleKeywordDao.getPageData(curPage, numPerPage, name);
    }

    public void updateArticlKeyword(ArticleKeyword articleKeyword)
    {
        articleKeywordDao.updateArticlKeyword(articleKeyword);
    }
}
