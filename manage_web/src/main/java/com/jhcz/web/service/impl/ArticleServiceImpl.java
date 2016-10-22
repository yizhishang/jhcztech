package com.jhcz.web.service.impl;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.mybatis.util.BeanUtil;
import com.jhcz.base.mybatis.util.PagedResult;
import org.springframework.stereotype.Service;

import com.jhcz.base.jdbc.Page;
import com.jhcz.web.dao.ArticleDao;
import com.jhcz.web.service.ArticleService;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService
{

	@Resource
	ArticleDao articleDao;
	
	public Page findArticlePageByCatalogId(int catalogId,int curPage, int numPerPage)
	{
		return articleDao.findArticlePageByCatalogId(catalogId, curPage, numPerPage);
	}

	public PagedResult<DataRow> findArticleListByCatalogId(int catalogId,int curPage, int numPerPage){
		List<DataRow> list =  articleDao.findArticleListByCatalogId(catalogId);
		PageHelper.startPage(curPage, numPerPage);
		PagedResult<DataRow> page = BeanUtil.toPagedResult(list);
		return page;
	}
	
}
