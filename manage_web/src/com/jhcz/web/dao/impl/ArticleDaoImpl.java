package com.jhcz.web.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jhcz.base.dao.impl.BaseDaoImpl;
import com.jhcz.base.jdbc.Page;
import com.jhcz.web.dao.ArticleDao;

@Repository
public class ArticleDaoImpl extends BaseDaoImpl implements ArticleDao
{
	@Override
	public Page findArticlePageByCatalogId(int catalogId,int curPage, int numPerPage)
	{
		StringBuffer sql = new StringBuffer("SELECT TITLE, BRIEF, PUBLISH_DATE,URL,CATALOG_ID FROM T_ARTICLE WHERE STATE = 3");
    	List<Object> args = new ArrayList<Object>();
    	sql.append(" AND CATALOG_ID = ?");
    	args.add(catalogId);
    	sql.append(" ORDER BY CATALOG_ID, PUBLISH_DATE DESC");
    	return getJdbcTemplate().queryPage(sql.toString(), args.toArray(), curPage, numPerPage);
	}
	
	public static void main(String[] args)
	{
		ArticleDaoImpl daoImpl = new ArticleDaoImpl();
		Page page =  daoImpl.findArticlePageByCatalogId(2920, 1, 2);
		System.out.println(page.getTotalPages());
	}
}
