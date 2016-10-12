package com.jhcz.plat.web.interceptors;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jhcz.plat.Constants;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2009-12-10
 * 创建时间: 10:14:46
 */
@Component
public class SessionInterceptor extends HandlerInterceptorAdapter
{
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        //		String[] noFilters = new String[] { "/loginAdmin/", "veriCode.html","index.html", "logout.html" };  
        
        String uri = request.getRequestURI();
        Object obj = request.getSession().getAttribute(Constants.ADMIN_USER_ID);
        if (uri.matches("^(/admin).*(.action)$") && null == obj)
        {
            // 未登录  
            PrintWriter out = response.getWriter();
            StringBuilder builder = new StringBuilder();
            builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
            builder.append("alert(\"页面过期，请重新登录\");");
            builder.append("if(window!= top){");
            builder.append("top.location.href = '/loginAdmin/loginOut.action';");
            builder.append("}else{");
            builder.append("window.location.href = '/loginAdmin/loginOut.action';}");
            builder.append("</script>");
            out.print(builder.toString());
            out.close();
            return false;
        }
        return super.preHandle(request, response, handler);
    }
    
}
