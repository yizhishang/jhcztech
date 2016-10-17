package com.jhcz.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jhcz.web.exception.BusinessException;
import com.jhcz.web.exception.ParameterException;

/**
 * 描述: BaseController.java
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-2-29
 * 创建时间: 上午11:22:58
 */
public class BaseController
{
	
	/** 基于@ExceptionHandler异常处理 */
	@ExceptionHandler
	public String exp(HttpServletRequest request, Exception ex)
	{
		
		request.setAttribute("ex", ex);
		
		// 根据不同错误转向不同页面  
		if (ex instanceof BusinessException)
		{
			return "error-business";
		}
		else if (ex instanceof ParameterException)
		{
			return "error-parameter";
		}
		else
		{
			return "error";
		}
	}
	
	/**
     * 返回HttpSession对象
     * @return
     */
    HttpSession getSession()
    {
        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ra.getRequest();
        return request.getSession();
    }
}
