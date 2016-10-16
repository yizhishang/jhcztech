package com.jhcz.plat.web.interceptors;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jhcz.base.config.Configuration;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.util.IPHelper;
import com.jhcz.base.util.ScriptHelper;
import com.jhcz.base.util.StringHelper;
import com.jhcz.plat.Constants;
import com.jhcz.plat.domain.ManageCatalog;
import com.jhcz.plat.system.SysLibrary;

/**
 * 描述: SecurityInterceptor.java
 * 版权: Copyright (c) 2015
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2015-11-7
 * 创建时间: 上午12:49:57
 */
@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter
{
    
    private static Logger logger = Logger.getLogger(SecurityInterceptor.class);
    
    /**
    * 判断用户对当前操作的功能是否有权限
    * @return
    */
    @SuppressWarnings("rawtypes")
    private boolean hasPermission(HttpServletRequest request)
    {
        //在登录时，权限应该取出来放在session中，在此再从session中取出来，进行相应的判断
        HttpSession session = request.getSession();
        
        /**
        * 是否启动ip匹配拦截0：不启动 1：启动
        * 袁永君 2016-01-05
        */
        if (Configuration.getInt("system.isVerificationIp") == 1)
        {
            //查看当前登录ip与session ip是否匹配
            String sessionIp = session.getAttribute(Constants.SESSION_FIRSTLOGIN_IP).toString();
            String requestIp = IPHelper.getIpAddr(request);
            logger.info("requestIp:" + requestIp + ",sessionIp" + sessionIp);
            if (!sessionIp.equals(requestIp))
            {
                return false;
            }
        }
        
        String uri = request.getRequestURI();
        int pos = uri.lastIndexOf("/");
        String rightUrl = uri.substring(pos + 1);
        if (rightUrl.indexOf("index.action") != -1 || rightUrl.indexOf("top.action") != -1 || rightUrl.indexOf("left.action") != -1
                || rightUrl.indexOf("right.action") != -1 || rightUrl.indexOf("login.action") != -1 || rightUrl.indexOf("getTicket.action") != -1
                || rightUrl.indexOf("loginValidate.action") != -1 || rightUrl.indexOf("reLogin.action") != -1)
        {
            return true;
        }
        
        //将获得的站点存放到session中
        String siteno = request.getParameter("siteno");
        if (StringHelper.isNotEmpty(siteno))
        {
            session.setAttribute(Constants.ADMIN_SITENO, siteno);
        }
        
        if (SysLibrary.isSystemAdmin(session))
        {
            DataRow rights = new DataRow();
            rights.set("all", "1");
            request.setAttribute("catalogRights", rights);
            return true;
        }
        
        List<Object> listRightUrl = SysLibrary.getSecurityCatalog(session);
        
        int codeRight = 0; //权限编码
        for (int i = 0; listRightUrl != null && i < listRightUrl.size(); i++) //如果请求的URL为权限列表中的URL则codeRight获得该URL权限编码
        {
            ManageCatalog rightUrlCode = (ManageCatalog) listRightUrl.get(i);
            String rightUrlCodeOfUrl = rightUrlCode.getLinkUrl();
            if (rightUrlCodeOfUrl.contains("?"))
            {
                rightUrlCodeOfUrl = rightUrlCodeOfUrl.substring(0, rightUrlCodeOfUrl.indexOf("?"));
            }
            //把url统一转换为小写进行比较
            if (rightUrl.toLowerCase().equals(rightUrlCodeOfUrl.toLowerCase()))
            {
                codeRight = rightUrlCode.getId();
                break;
            }
        }
        boolean flag = false;
        //获取缓存中URL权限数据 与当前登录用户URL权限比较，看是否具有权限
        HashSet hs = SysLibrary.getUserRight(session);
        if (hs == null)
        {
            return false;
        }
        Iterator it = hs.iterator();
        while (it.hasNext())
        {
            int userRi = Integer.parseInt((String) it.next());
            if (userRi == codeRight)
            {
                flag = true;
                break;
            }
        }
        
        return flag;
    }
    
    /**
     * 描述: 判断用户是否已经登录
     * 作者: 袁永君
     * 创建日期: 2015-11-9
     * 创建时间: 下午9:27:30
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    private boolean isLogin(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String uri = request.getRequestURI();
        if(uri.indexOf("login.action") > -1 || uri.indexOf("reLogin.action") > -1 || uri.indexOf("getTicket.action") > -1 || uri.indexOf("loginValidate.action") > -1){
        	return true;
        }
        Object obj = request.getSession().getAttribute(Constants.ADMIN_USER_ID);
        if (uri.indexOf("admin") > -1 && null == obj)
        {
            // 未登录  
            return false;
        }
        return true;
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        //先判断用户是否已经登录
        if (!isLogin(request, response))
        {
            // 未登录  
            ScriptHelper.redirect(response, request.getContextPath() + "/loginAdmin/reLogin.action");
            return false;
        }
        
        //再判断是否有相应的权限
        if (!hasPermission(request))
        {
            ScriptHelper.redirect(response, "/WEB-INF/views/noRight.jsp");
            return false;
        }
        
        return super.preHandle(request, response, handler);
    }
}
