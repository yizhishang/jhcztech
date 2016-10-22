package com.jhcz.web.dao;

import com.jhcz.base.jdbc.Page;


public interface ArticleDao
{
	/**
	 * 描述: 获取文章分页数据
	 * 作者: 袁永君
	 * 创建日期: 2016-3-21
	 * 创建时间: 上午11:18:30
	 * @param catalogId
	 * @param curPage
	 * @param numPerPage
	 * @return
	 */
	public Page findArticlePageByCatalogId(int catalogId,int curPage, int numPerPage);
}
