package com.jhcz.web.service;

import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.jdbc.Page;
import com.jhcz.base.mybatis.util.PagedResult;

import java.util.List;

public interface ArticleService
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

	public PagedResult<DataRow> findArticleListByCatalogId(int catalogId,int curPage, int numPerPage);
}
