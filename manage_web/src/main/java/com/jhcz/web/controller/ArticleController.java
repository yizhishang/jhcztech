package com.jhcz.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jhcz.base.jdbc.Page;
import com.jhcz.web.aspect.MethodLog;
import com.jhcz.web.service.ArticleService;


/**
 * 描述: 文章
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-3-21
 * 创建时间: 上午10:02:35
 */

@Controller
@RequestMapping("/articleController")
public class ArticleController
{
	@Resource
	ArticleService articleService;
	
	@MethodLog(remark="获取文章分页数据")
	@RequestMapping("/findArticlePageByCatalogId.action")
	public ModelAndView findArticlePageByCatalogId(int catalogId,int curPage, int numPerPage)
    {
    	Page page = articleService.findArticlePageByCatalogId(catalogId, curPage, numPerPage);
    	ModelAndView  mv = new ModelAndView("/article/ajaxArticleList");
    	mv.addObject("page", page);
    	mv.addObject("catalogId", catalogId);
    	return mv;
    }
}
