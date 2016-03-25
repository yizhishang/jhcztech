package com.jhcz.cms.base;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

@SuppressWarnings("rawtypes")
public class Constants extends HashMap
{
    
    private static final long serialVersionUID = 1L;

    private static Logger logger = Logger.getLogger(Constants.class);
    
    /**
    * 登录验证码图片
    */
    public final static String TICKET = "com.jhcz.cms.base.admin.ticket";
    
    /**
    * 后管理管理用的UID
    */
    public final static String ADMIN_UID = "com.jhcz.cms.base.admin.uid";
    
    /**
    * 后台管理用户的userId
    */
    public final static String ADMIN_USER_ID = "com.jhcz.cms.base.admin.userId";
    
    /**
    * 后台管理用户的用户名称
    */
    public final static String ADMIN_USER_NAME = "com.jhcz.cms.base.admin.userName";
    
    /**
    * 后台管理用户是否是超级管理员,超级管理员拥有所有权限
    */
    public final static String ADMIN_IS_SYSTEM = "com.jhcz.cms.base.admin.isSystem";
    
    /**
    * 当前登录的站点
    */
    public final static String ADMIN_SITENO = "com.jhcz.cms.base.admin.siteNo";
    
    /**
    * 后台用户所拥有的站点
    */
    public final static String USER_ALL_SITENO = "com.jhcz.cms.base.admin.userSiteNo";
    
    /**
    * 后台用户登录的站点是否是主站
    */
    public final static String ADMIN_SITE_IS_MAIN = "com.jhcz.cms.base.admin.siteIsMain";
    
    /**
    * 用户登录拥有的权限
    */
    public final static String USER_RIGHT = "com.jhcz.cms.base.admin.userRight";
    
    /**
    * 用户拥有的功能权限
    */
    public final static String USER_CATALOG_RIGHT = "com.jhcz.cms.base.admin.userCatalogRight";
    
    /**
    * 用户拥有的角色
    */
    public final static String USER_ROLE = "com.jhcz.cms.base.admin.userRole";
    
    /**
    * 超级管理员选择登陆的站点
    */
    public final static String SUPER_ADMIN_SITENO = "com.jhcz.cms.base.admin.selectSiteNo";
    
    /**
    * 用户所在营业部
    */
    public final static String USER_BRANCHNO = "com.jhcz.cms.base.admin.userBranch";
    
    /**
    * 用户的应用功能栏目集合
    */
    public final static String USER_MENU_CATALOGS = "com.jhcz.cms.base.user.menuCatalogs";
    
    /**
    * session中保存弹出修改密码提示框时的对话
    */
    public final static String POPUP_MODIFYPWD_MESSAGE = "popup_modifypwd_message";
    
    /**
    * 弹出修改密码对话框
    */
    public final static String POPUP_MODIFYPWD_WINDOW = "popup_modifypwd_window";
    
    /**
    * 首次登录修改密码的uid
    */
    public final static String SESSION_FIRSTLOGIN_UID = "session_firstlogin_uid";
    
    /**
    * 首次登录用户的登录ip
    */
    public final static String SESSION_FIRSTLOGIN_IP = "session_firstlogin_ip";
    
    /**
    * 首次登录修改密码的站点编号
    */
    public final static String SESSION_FIRSTLOGIN_SITENO = "session_firstlogin_siteno";
    
    /**
    * 分页显示的记录数
    */
    public static final int MAIN_ROW_OF_PAGE = 25;
    
    /**
    * 文章发布
    */
    public static final int ARTICLE_STATE_PUBLISHED = 3;
    
    /**
    * 数据库ID
    */
    public static final String DB_WEB = "web";
    
    /**
    * MAIN站点的站点号
    */
    public static final String MAIN_SITENO = "main";
    
    /**
    * 用户中心对应的数据库
    */
    public static final String DB_UCENTER = "ucenter";
    
    /**
    * 空间所对应的数据库
    */
    public static final String DB_SPACE = "space";

    private static Map<Object, Object> map;
    
    /**
    * 使用该构造方法实现一个JSTL可访问的系统常量，
    */
    public Constants()
    {
        // initialize only once...
        if (map != null)
            return;

        map = new HashMap<Object, Object>();
        Class c = this.getClass();
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++)
        {
            Field field = fields[i];
            int modifier = field.getModifiers();
            // 排除private类型
            if (Modifier.isFinal(modifier) && !Modifier.isPrivate(modifier))
            {
                try
                {
                    put(field.getName(), field.get(this));
                }
                catch (IllegalAccessException e)
                {
                    // e.printStackTrace();
                    logger.error("", e);
                }
            }
        }
    }
    
    @Override
    public Object get(Object key)
    {
        return map.get(key);
    }
    
    @Override
    public Object put(Object key, Object value)
    {
        return map.put(key, value);
    }
}
