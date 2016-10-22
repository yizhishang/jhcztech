package com.jhcz.web.dao;

import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.jdbc.Page;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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

	@Select("SELECT TITLE, BRIEF, PUBLISH_DATE,URL,CATALOG_ID FROM T_ARTICLE WHERE STATE = 3 AND CATALOG_ID = #{CATALOGID} ORDER BY CATALOG_ID, PUBLISH_DATE DESC")
	public List<DataRow> findArticleListByCatalogId(int catalogId);
}
