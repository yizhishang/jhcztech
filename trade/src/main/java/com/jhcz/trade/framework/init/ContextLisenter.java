package com.jhcz.trade.framework.init;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class ContextLisenter implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent event)
    {
        WebApplicationContext ctx = WebApplicationContextUtils
                .getRequiredWebApplicationContext(event.getServletContext());
        ContextUtil.setCrasContext(ctx);
        
      
        
        //初始化application
        this.initApplication(event);
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0)
    {

    }

    /**
     * 初始化application环境.<br/>
     * 
     * @param event
     *            servlet上下文事件对象
     */
    private void initApplication(ServletContextEvent event)
    {
        if (null != event)
        {

            ServletContext sc = event.getServletContext();
			/*
			 初始化application级别的变量。 
            sc.setAttribute("mainServer", WebConstants.MAIN_SERVER);
            sc.setAttribute("cssFileServer", WebConstants.CSS_FILE_SERVER);
            sc.setAttribute("jsFileServer", WebConstants.JS_FILE_SERVER);
            sc.setAttribute("imgFileServer", WebConstants.IMG_FILE_SERVER);

            // 后台服务请求路径
            sc.setAttribute("backServer", WebConstants.BACK_SERVER);

            // 前端页面资源路径
            sc.setAttribute("resource_path", WebConstants.RESOURCE_PATH);

            // 后台 资源文件路径
            sc.setAttribute("backCssFile", WebConstants.BACK_CSS_FILE_SERVER);
            sc.setAttribute("backJsFile", WebConstants.BACK_JS_FILE_SERVER);
            sc.setAttribute("backImgFile", WebConstants.BACK_IMG_FILE_SERVER);
            sc.setAttribute("mallBackServer", WebConstants.MALL_BACK_SERVER);
            
            //电商的 图片上传路径
            sc.setAttribute("uploadPath", WebConstants.UPLOAD_PATH);*/
            

            String webRootPath = event.getServletContext().getRealPath("/");
            String classesPath = event.getServletContext().getRealPath(
                    "WEB-INF/classes");

            AppServerUtil.setClassesPath(classesPath);
            AppServerUtil.setWebRootPath(webRootPath);
        }
    }

}
