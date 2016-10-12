package com.jhcz.plat.dao;

import java.util.List;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.plat.domain.Article_Source;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-21
 * 创建时间: 10:47:32
 */
public interface Article_SourceDao
{

    public void addArticle_Source(Article_Source article_source);

    public void deleteArticle_Source(int id);

    public Article_Source findArticle_SourceById(int id);

    public List<Object> findArticle_SourceBySiteNo(String siteNo);

    public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword);

    public void updateArticle_Source(Article_Source article_source);
}
