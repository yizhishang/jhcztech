package com.jhcz.web.controller;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jhcz.base.domain.User;
import com.jhcz.base.domain.system.Result;
import com.jhcz.base.jdbc.Page;
import com.jhcz.web.aspect.MethodLog;
import com.jhcz.web.service.ArticleService;
import com.jhcz.web.service.UserService;

@Controller
public class HelloController
{
	
	@Resource
	ArticleService articleService;
	
	@Resource
	UserService userService;
	
	@RequestMapping("/greeting.action")
	@MethodLog(remark="欢迎")
	public ModelAndView greeting()
	{
		User user = userService.getUserById(1);
		ModelAndView mv = new ModelAndView("/test/hello1");
		mv.addObject("user", user);
		return mv;
	}
	

	@ResponseBody
	@MethodLog(remark="测试获取文章分页数据")
	@RequestMapping("/queryArticlePageByCatalogId.action")
	public Result queryArticlePageByCatalogId(int catalogId,int curPage, int numPerPage)
	{
		Page page = articleService.findArticlePageByCatalogId(catalogId, curPage, numPerPage);
		Result result = new Result();
		result.setObj(page);
		return result;
	}
}
