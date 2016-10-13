package com.jhcz.plat.dao;

import java.util.List;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.plat.domain.Article;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-24
 * 创建时间: 11:18:44
 */
public interface ArticleDao
{
	
	
	public int addArticle(Article article);
	
	
	public void addAuthor(DataRow data);
	
	
	public void delAuthor(String id);
	
	
	public void deleteArticle(int articleId);
	
	
	public Article findArticleById(int articleId);
	
	
	public DBPage findAuthor(int curPage, int numPerPage, String siteno, String keyword);
	
	
    public List<DataRow> findPublishArticleById(int catalogId, int rows, int sortType);
	
	
	public List<Object> findUnionArtilceByCatalog(int catalogId);
	
	
	public DBPage getPageData(int curPage, int numPerPage, String siteNo, int catalogId, String title, int state, String author, String startDate, String endDate);
	
	
	public DBPage getPageDataByEmail(int curPage, int numPerPage, String siteNo, int catalogId, String title, int state, String author, String branchno);
	
	
	public boolean isAuthorExist(String name);
	
	
	public boolean isEndNode(int catalogId);
	
	
    public boolean isTitleExist(String title);
	
	
	public void updateArticle(Article article);


	public DataRow findUpAndDown(String curArticleId, String catalog_id);
}
