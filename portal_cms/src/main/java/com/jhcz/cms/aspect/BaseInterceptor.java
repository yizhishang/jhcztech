package com.jhcz.cms.aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

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
public class BaseInterceptor
{
	
	private Logger logger;
	
	@After(value = "pointCut()")
	public void after()
	{
		//		ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		//		HttpServletRequest request = ra.getRequest();
	}
	
	@Around(value = "pointCut()")
	public Object AroundExcute(ProceedingJoinPoint pjp) throws Throwable
	{
		logger = Logger.getLogger(pjp.getTarget().getClass());
		try
		{
			String className = pjp.getTarget().getClass().getName();
			String method = pjp.getSignature().getName();
			String remark = getMthodRemark(pjp);
			
			if (logger.isInfoEnabled())
			{
				logger.info("开始执行 [" + className + "." + method + remark + "]");
			}
			long startTime = System.currentTimeMillis();
			
			//在进入相应的function对应的方法前，先调用before，在调用相应的方法后，再调用after
			Object result = pjp.proceed();
			if (logger.isInfoEnabled())
			{
				logger.info("执行完成 [" + className + "." + method + remark + "] times=" + String.valueOf(System.currentTimeMillis() - startTime) + " millisecond");
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
	
	@Pointcut("execution(* com.jhcz.web.controller.*.*(..))")
	public void pointCut()
	{
		logger.info("pointCut");
	}
	
	// 获取方法的中文备注____用于记录用户的操作日志描述  
	public static String getMthodRemark(ProceedingJoinPoint joinPoint) throws Exception
	{
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		
		Method[] method = Class.forName(targetName).getMethods();
		String methode = "";
		for (Method m : method)
		{
			if (m.getName().equals(methodName))
			{
				if (m.getParameterTypes().length == arguments.length)
				{
					MethodLog methodCache = m.getAnnotation(MethodLog.class);
					if (methodCache != null)
					{
						methode = methodCache.remark();
					}
					break;
				}
			}
		}
		return methode;
	}
}
