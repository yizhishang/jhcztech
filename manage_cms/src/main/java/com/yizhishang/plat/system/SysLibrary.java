package com.yizhishang.plat.system;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.yizhishang.base.config.Configuration;
import com.yizhishang.base.jdbc.DataRow;
import com.yizhishang.base.util.DateHelper;
import com.yizhishang.base.util.SessionHelper;
import com.yizhishang.base.util.SpringContextHolder;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.base.util.UUID;
import com.yizhishang.plat.Constants;
import com.yizhishang.plat.domain.Article;
import com.yizhishang.plat.domain.Catalog;
import com.yizhishang.plat.domain.Template;
import com.yizhishang.plat.service.ArticleService;
import com.yizhishang.plat.service.CatalogService;
import com.yizhishang.plat.service.ManageCatalogService;
import com.yizhishang.plat.service.TemplateService;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-18
 * 创建时间: 16:51:59
 */
public class SysLibrary
{
	
	private static CatalogService catalogService = (CatalogService) SpringContextHolder.getBean("catalogService");
	private static TemplateService templateService = (TemplateService) SpringContextHolder.getBean("templateService");
	private static ArticleService articleService = (ArticleService) SpringContextHolder.getBean("articleService");
    
    /**
    * 判断用户是否存在模块权限(只要有一个该模块内的功能权限，就表示存在模块权限)
    * @param moduleCode
    * @return
    */
    @SuppressWarnings("rawtypes")
    public static boolean existModulePermission(HttpSession session, String moduleCode)
    {
        HashSet rightSet = SysLibrary.getUserRight(session);
        Iterator it = rightSet.iterator();
        while (it.hasNext())
        {
            String functionCode = it.next().toString();
            if (functionCode.startsWith(moduleCode))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
    * 获得文章发布时，保存的文件路径
    * @param articleId
    * @return
    */
    public static String getArticleStorePath(int articleId)
    {
        Article article = articleService.findArticleById(articleId);
        if (article != null)
        {
            String siteNo = article.getSiteNo();
            String createTime = article.getCreateDate();
            int catalogId = article.getCatalogId();
            Catalog catalog = catalogService.findCatalogById(catalogId);
            String fileType = catalog.getFileType();
            
            createTime = createTime.substring(0, 10);
            createTime = StringHelper.replace(createTime, "-", "");
            /**
            * 上传文件保存的地址，从配置文件中取，可以将附件生成在工程外
            *  add by 20131015
            */
            String fileSaveAddr = Configuration.getString("system.fileSaveAddr");
            if (StringHelper.isNotEmpty(fileSaveAddr))
            {
                return fileSaveAddr + "/" + siteNo + "/a/" + createTime + "/" + articleId + "." + fileType;
            }
            else
            {
                return Application.getRootPath() + "/" + siteNo + "/a/" + createTime + "/" + articleId + "." + fileType;
            }
        }
        return "";
    }
    
    /**
    * 获得文章发布后访问时的URL路径
    *
    * @param articleId
    * @return
    */
    public static String getArticleUrlPath(int articleId)
    {
        Article article = articleService.findArticleById(articleId);
        if (article != null)
        {
            String siteNo = article.getSiteNo();
            String createTime = article.getCreateDate();
            int catalogId = article.getCatalogId();
            Catalog catalog = catalogService.findCatalogById(catalogId);
            String fileType = catalog.getFileType();
            
            createTime = createTime.substring(0, 10);
            createTime = StringHelper.replace(createTime, "-", "");
            return "/" + siteNo + "/a/" + createTime + "/" + articleId + "." + fileType;
        }
        return "";
    }
    
    /**
     * 
     *	
     * @param catalogId
     * @param isPublishMain
     * @return
     */
    public static String getCatalogStorePath(int catalogId)
    {
        return getCatalogStorePath(catalogId, "");
    }
    
    /**
    * 
    * @描述：获得栏目发布时，保存的栏目首页文件路径
    * @时间：2011-3-18 下午04:12:02
    * @param catalogId
    * @param siteNo
    * @return
    */
    public static String getCatalogStorePath(int catalogId, String siteNo)
    {
        Catalog catalog = catalogService.findCatalogById(catalogId);
        if (catalog != null)
        {
            String route = catalog.getRoute();
            if (StringHelper.isEmpty(siteNo))
            {
                siteNo = catalog.getSiteNo();
            }
            String publishPath = catalog.getPublishPath();
            String fileType = catalog.getFileType();
            
            StringBuffer buffer = new StringBuffer();
            if (!StringHelper.isEmpty(publishPath)) //用户已经设置了发布路径
            {
                if (!publishPath.startsWith("/"))
                {
                    publishPath = "/" + publishPath;
                }
                if (!publishPath.endsWith("/"))
                {
                    publishPath = publishPath + "/";
                }
                
                buffer.append(publishPath + "index." + fileType);
                
            }
            else
            {
                String[] catalogArray = route.split("\\|");
                
                buffer.append("/" + siteNo);
                for (int i = 1; i < catalogArray.length; i++) //跳过第一个节点,第一个为根目录
                {
                    Catalog tempCatalog = catalogService.findCatalogById(Integer.parseInt(catalogArray[i]));
                    buffer.append("/" + tempCatalog.getCatalogNo());
                }
                buffer.append("/index." + fileType);
                
            }
            /**
            * 上传文件保存的地址，从配置文件中取，可以将附件生成在工程外
            *  add by 20131015
            */
            String fileSaveAddr = Configuration.getString("system.fileSaveAddr");
            if (StringHelper.isNotEmpty(fileSaveAddr))
            {
                return fileSaveAddr + buffer.toString();
            }
            else
            {
                return Application.getRootPath() + buffer.toString();
            }
        }
        return "";
    }
    
    public static String getCatalogUrlPath(int catalogId)
    {
        return getCatalogUrlPath(catalogId, "");
    }
    
    /**
    * 获得栏目发布后访问的URL路径
    *
    * @param catalogId
    * @return
    */
    public static String getCatalogUrlPath(int catalogId, String siteNo)
    {
        Catalog catalog = catalogService.findCatalogById(catalogId);
        if (catalog != null)
        {
            String route = catalog.getRoute();
            if (StringHelper.isEmpty(siteNo))
            {
                siteNo = catalog.getSiteNo();
            }
            
            String publishPath = catalog.getPublishPath();
            String fileType = catalog.getFileType();
            
            StringBuffer buffer = new StringBuffer();
            if (!StringHelper.isEmpty(publishPath)) //用户已经设置了发布路径
            {
                if (!publishPath.startsWith("/"))
                {
                    publishPath = "/" + publishPath;
                }
                if (!publishPath.endsWith("/"))
                {
                    publishPath = publishPath + "/";
                }
                
                buffer.append(publishPath + "index." + fileType);
            }
            else
            {
                String[] catalogArray = route.split("\\|");
                
                buffer.append("/" + siteNo);
                for (int i = 1; i < catalogArray.length; i++) //跳过第一个节点,第一个为根目录
                {
                    Catalog tempCatalog = catalogService.findCatalogById(Integer.parseInt(catalogArray[i]));
                    buffer.append("/" + tempCatalog.getCatalogNo());
                }
                buffer.append("/index." + fileType);
            }
            
            return buffer.toString();
        }
        return "";
    }
    
    /**
    * 描述：获取当前的站点号
    * 作者：袁永君
    * 时间：2010-1-11 下午02:32:10
    *
    * @param session
    * @return
    */
    public static String getCurrentSiteNo(HttpSession session)
    {
        return SessionHelper.getString(Constants.ADMIN_SITENO, session);
    }
    
    /**
    * 作者: chenp
    * 时间: 2015-6-5 下午02:14:48
    * 描述: 返回密码后缀
    *
    * @return
    */
    public static String getSalt()
    {
        UUID result = UUID.randomUUID();
        String str = result.toString();
        str = UUID.getUUIDString(str);
        String salt = str.substring(str.length() - 6, str.length());
        return salt;
        
    }
    
    /**
    * 描述：返回当前应用菜单栏目，链表结构
    * 作者：袁永君
    * 时间：2010-1-11 下午02:32:42
    * @param session
    * @return
    */
    @SuppressWarnings("unchecked")
    public static List<Object> getSecurityCatalog(HttpSession session)
    {
        return (List<Object>) SessionHelper.getObject(Constants.USER_MENU_CATALOGS, session);
    }
    
    /**
    * 描述：获取当前用户的应用菜单栏目，树状结构
    * 作者：袁永君
    * 时间：2010-1-8 上午10:03:37
    *
    * @param session
    * @return
    */
    public static List<Object> getSecurityCatalogTree(HttpSession session)
    {
        @SuppressWarnings("unchecked")
        List<Object> list = (List<Object>) SessionHelper.getObject(Constants.USER_MENU_CATALOGS, session);
        return new ManageCatalogService().prepareCatalogTree(list);
    }
    
    /**
    * 获得模板发布后的文件路径
    *
    * @param templateId
    * @return
    */
    public static String getTemplateStorePath(int templateId)
    {
        Template template = templateService.findTemplateById(templateId);
        if (template != null)
        {
            String filePath = template.getFilePath();
            /**
            * 上传文件保存的地址，从配置文件中取，可以将附件生成在工程外
            *  add by 20131015
            */
            String fileSaveAddr = Configuration.getString("system.fileSaveAddr");
            if (StringHelper.isNotEmpty(fileSaveAddr))
            {
                return fileSaveAddr + filePath;
            }
            else
            {
                return System.getProperty("ctxPath") + filePath;
            }
        }
        return "";
    }
    
    /**
    * 获得模板发布后的URL路径
    *
    * @param templateId
    * @return
    */
    public static String getTemplateUrlPath(int templateId)
    {
        Template template = templateService.findTemplateById(templateId);
        if (template != null)
        {
            String filePath = template.getFilePath();
            return filePath;
        }
        return "";
    }
    
    /**
    * 返回保存上传文件的真实目录路径
    *
    * @return
    */
    public static String getUploadPath()
    {
        String datePath = DateHelper.formatDate(new Date(), "yyyyMMdd");
        /**
        * 上传文件保存的地址，从配置文件中取，可以将附件生成在工程外
        *  add by 20131015
        */
        String fileSaveAddr = Configuration.getString("system.fileSaveAddr");
        if (StringHelper.isNotEmpty(fileSaveAddr))
        {
            return fileSaveAddr + "/upload/" + datePath + "/";
        }
        else
        {
            return Application.getRootPath() + "/upload/" + datePath + "/";
        }
        
    }
    
    /**
    * 返回上传文件的URL路径，不包含context
    *
    * @return
    */
    public static String getUploadUrlPath()
    {
        String datePath = DateHelper.formatDate(new Date(), "yyyyMMdd");
        return "/upload/" + datePath + "/";
    }
    
    /**
    * 获得用户的营业部编号
    *
    * @param session
    * @return
    */
    public static String getUserBranchno(HttpSession session)
    {
        return (String) session.getAttribute(Constants.USER_BRANCHNO);
    }
    
    /**
    * 获得用户的栏目权限
    *
    * @param session
    * @return
    */
    public static DataRow getUserCatalogRight(HttpSession session)
    {
        return (DataRow) session.getAttribute(Constants.USER_CATALOG_RIGHT);
    }
    
    /**
    * 获得用户的权限
    * @param session
    * @return
    */
    @SuppressWarnings("rawtypes")
    public static HashSet getUserRight(HttpSession session)
    {
        return (HashSet) session.getAttribute(Constants.USER_RIGHT);
    }
    
    /**
    * 作者: chen
    * 时间: 2015-6-5 下午02:10:04
    * 描述: 返回处理后的密码
    * @param password
    * @return
    */
    //	public static String getPassword(String password, String salt)
    //	{
    //		//这里的MD5和前台用户注册的MD5一致
    //		WEBMD5 md5 = new WEBMD5();
    //		password = md5.getMD5ofStr(md5.getMD5ofStr(password) + salt);
    //		return password;
    //	}
    
    public static String getUUIDString(String str)
    {
        String result = "";
        str = str.replaceAll("-", "");
        if (result != null)
        {
            for (int i = 0; i < str.length(); i++)
            {
                if (!str.substring(i, i + 1).equals("-"))
                {
                    result += str.substring(i, i + 1);
                }
                else
                {
                    
                }
            }
        }
        return result.toLowerCase();
    }
    
    /**
    * 判断某用户是否具有某功能权限
    * @param functionCode
    * @return
    */
    @SuppressWarnings("rawtypes")
    public static boolean hasFunctionPermission(HttpSession session, String functionCode)
    {
        HashSet rightSet = SysLibrary.getUserRight(session);
        Iterator it = rightSet.iterator();
        while (it.hasNext())
        {
            String code = it.next().toString();
            if (code.equalsIgnoreCase(functionCode))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
    * 判断当前用户是否是系统管理员角色
    *
    * @return
    */
    public static boolean isAdministratorsRole(HttpSession session)
    {
        int[] roleArray = (int[]) session.getAttribute(Constants.USER_ROLE);
        if (roleArray != null && roleArray.length > 0)
        {
            for (int i = 0; i < roleArray.length; i++)
            {
                if (roleArray[i] == 1) //是administrators角色
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
    * 判断用户是否已经登录
    *
    * @param session
    * @return
    */
    public static boolean isLogin(HttpSession session)
    {
        
        String uid = SessionHelper.getString(Constants.ADMIN_UID, session);
        return (StringHelper.isEmpty(uid)) ? false : true;
    }
    
    /**
    * 判断当前登录用户是否是系统管理员
    *
    * @param session
    * @return
    */
    public static boolean isSystemAdmin(HttpSession session)
    {
        int adminIsSystem = SessionHelper.getInt(Constants.ADMIN_IS_SYSTEM, session);
        return (adminIsSystem == 1) ? true : false;
    }
}
