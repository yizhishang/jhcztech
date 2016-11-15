package com.yizhishang.plat.web.aspect;

import com.yizhishang.base.util.RequestHelper;
import com.yizhishang.plat.domain.ManageCatalog;
import com.yizhishang.plat.service.ManageCatalogService;
import com.yizhishang.plat.system.SysLibrary;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    private Logger logger;

    @After(value = "pointCut()")
    public void after()
    {
        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ra.getRequest();
        int manageCatalogId = RequestHelper.getInt(request, "manageCatalogId");
        int subManageCatalogId = RequestHelper.getInt(request, "subManageCatalogId");
        if (manageCatalogId > 0) {
            ManageCatalog manageCatalog = manageCatalogService.findCatalogById(manageCatalogId);
            request.setAttribute("menu", manageCatalog);
            if (subManageCatalogId > 0) {
                List<ManageCatalog> children = manageCatalogService.findChildrenById(manageCatalogId, SysLibrary
                        .getCurrentSiteNo(request.getSession()));
                if (children != null && children.size() > 0) {
                    request.setAttribute("subManageCatalogId", String.valueOf(manageCatalogId));
                    request.setAttribute("subMenus", children);
                }
            }
        }

    }

    @Around(value = "pointCut()")
    public Object AroundExcute(ProceedingJoinPoint pjp) throws Throwable
    {
        logger = LoggerFactory.getLogger(pjp.getTarget().getClass());
        try {
            //			String className = pjp.getTarget().getClass().getName();
            String method = pjp.getSignature().getName();

            if (logger.isInfoEnabled()) {
                logger.info("开始执行 [" + method + "]");
            }
            long startTime = System.currentTimeMillis();

            //在进入相应的function对应的方法前，先调用before，在调用相应的方法后，再调用after
            Object result = pjp.proceed();
            ;
            if (logger.isInfoEnabled()) {
                logger.info("执行完成 [" + method + "] times=" + String.valueOf(System.currentTimeMillis() - startTime) +
                        " millisecond");
            }
            return (result == null) ? "none" : result;
        } catch (Exception ex) {
            logger.error("捕捉到了异常，嘎嘎嘎嘎", ex);
        }
        return "error";
    }

    @Pointcut("execution(* com.yizhishang.plat.web.action.*.*(..))")
    public void pointCut()
    {

    }
}
