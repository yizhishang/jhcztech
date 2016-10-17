package com.jhcz.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jhcz.base.jdbc.Page;
import com.jhcz.web.dao.ArticleDao;
import com.jhcz.web.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService
{

	@Resource
	ArticleDao articleDao;
	
	@Override
	public Page findArticlePageByCatalogId(int catalogId,int curPage, int numPerPage)
	{
		return articleDao.findArticlePageByCatalogId(catalogId, curPage, numPerPage);
	}
	
}
