package com.jhcz.plat.service;

import java.util.List;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.plat.domain.ArticleKeyword;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-20
 * 创建时间: 12:11:23
 */
public interface ArticleKeywordService
{
    public void addArticleKeyword(ArticleKeyword articleKeyword);

    public void deleteArticleKeyword(int id);

    public List<ArticleKeyword> findAll();

    public ArticleKeyword findById(int id);

    public DBPage getPageData(int curPage, int numPerPage, String name);

    public void updateArticlKeyword(ArticleKeyword articleKeyword);
}
