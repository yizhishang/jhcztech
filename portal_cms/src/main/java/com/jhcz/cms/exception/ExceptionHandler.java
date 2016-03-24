package com.jhcz.cms.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


/**
 * 描述: ExceptionHandler.java
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-2-29
 * 创建时间: 上午11:23:05
 */
public class ExceptionHandler implements HandlerExceptionResolver
{
	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	{
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex);
		
		// 根据不同错误转向不同页面  
		if (ex instanceof BusinessException)
		{
			return new ModelAndView("/error-business", model);
		}
		else if (ex instanceof ParameterException)
		{
			return new ModelAndView("/error-parameter", model);
		}
		else
		{
			return new ModelAndView("/error", model);
		}
	}

}
