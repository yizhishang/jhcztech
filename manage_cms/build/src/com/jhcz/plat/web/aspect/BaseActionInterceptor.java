package com.jhcz.plat.web.aspect;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jhcz.base.util.RequestHelper;
import com.jhcz.plat.domain.ManageCatalog;
import com.jhcz.plat.service.ManageCatalogService;
import com.jhcz.plat.system.SysLibrary;

/**
 * 描述: 日志方面组件
 * 版权: Copyright (c) 2015
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2015-10-18
 * 创建时间: 下午10:32:11
 */
@Component
@Aspect
public class BaseActionInterceptor
{
	
	@Resource
	protected ManageCatalogService manageCatalogService;
	
	private Logger logger ;
	
	@After(value = "pointCut()")
	public void after()
	{
		ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ra.getRequest();
		int manageCatalogId = RequestHelper.getInt(request, "manageCatalogId");
		int subManageCatalogId = RequestHelper.getInt(request, "subManageCatalogId");
		if (manageCatalogId > 0)
		{
			ManageCatalog manageCatalog = manageCatalogService.findCatalogById(manageCatalogId);
			request.setAttribute("menu", manageCatalog);
			if (subManageCatalogId > 0)
			{
				List<Object> children = manageCatalogService.findChildrenById(manageCatalogId, SysLibrary.getCurrentSiteNo(request.getSession()));
				if (children != null && children.size() > 0)
				{
					request.setAttribute("subManageCatalogId", String.valueOf(manageCatalogId));
					request.setAttribute("subMenus", children);
				}
			}
		}
		
	}
	
	@Around(value = "pointCut()")
	public Object AroundExcute(ProceedingJoinPoint pjp) throws Throwable
	{
		logger = Logger.getLogger(pjp.getTarget().getClass());
		try
		{
			String className = pjp.getTarget().getClass().getName();
			String method = pjp.getSignature().getName();
			
			if (logger.isInfoEnabled())
			{
                logger.info("开始执行 [" + className + "." + method + "]");
			}
			long startTime = System.currentTimeMillis();
			
            //在进入相应的function对应的方法前，先调用before，在调用相应的方法后，再调用after
			Object result = pjp.proceed();;
			if (logger.isInfoEnabled())
			{
                logger.info("执行完成 [" + className + "." + method + "] times=" + String.valueOf(System.currentTimeMillis() - startTime) + " millisecond");
			}
			return (result == null) ? "none" : result;
		}
		catch (InvocationTargetException ex)
		{
			logger.error("", ex.getCause());
		}
		catch (Exception ex)
		{
			logger.error("", ex);
		}
		return "error";
	}
	
	@Pointcut("execution(* com.jhcz.plat.web.action.*.*(..))")
	public void pointCut()
	{
		
	}
}
