package com.yizhishang.plat.web.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.yizhishang.base.config.Configuration;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.util.ConvertHelper;
import com.yizhishang.base.util.DateHelper;
import com.yizhishang.base.util.SpringContextHolder;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.base.util.UserHelper;
import com.yizhishang.plat.Constants;
import com.yizhishang.plat.domain.Log;
import com.yizhishang.plat.domain.Result;
import com.yizhishang.plat.domain.Site;
import com.yizhishang.plat.domain.User;
import com.yizhishang.plat.service.CatalogService;
import com.yizhishang.plat.service.LogService;
import com.yizhishang.plat.service.PublishQueueService;
import com.yizhishang.plat.service.SiteService;
import com.yizhishang.plat.service.UserService;
import com.yizhishang.plat.web.form.DynaForm;

/**
 * 描述: BaseAction.java
 * 版权: Copyright (c) 2015
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2015-10-29
 * 创建时间: 上午9:11:13
 */
@Component
public class BaseAction
{
    
    //缺省页面
    String DEFAULT = "default";
    
    static final String NONE = "none";
    
    String RETURNINFO = "";
    
    String SUCCESS = "success";
    
    String ERROR = "/WEB-INF/views/error.jsp";
    
    String NORIGHT = "noright";
    
    //编辑页面
    String EDIT = "edit";
    
    //添加页面
    String ADD = "add";
    
    String MAIN = "main";
    
    String MESSAGE = "message";
    
    String MESSAGE_JSP = "/WEB-INF/views/messages.jsp";
    
    String NO_RIGHT = "/WEB-INF/views/noRight.jsp";
    
    String ACTIONMESSAGES = "actionMessages";
    
    String ACTIONERRORS = "actionErrors";
    
    HttpServletRequest request;
    
    HttpServletResponse response;
    
    //action运行时抛出的错误消息
    protected String throwMessage = "";
    
    protected HashMap<String, Object> dataMap = new HashMap<String, Object>();
    
    protected DynaForm form = new DynaForm();
    
    protected Result result = new Result();
    
    protected ModelAndView mv = new ModelAndView();
    
    @Resource
    CatalogService cataLogService;
    
    @Resource
    LogService logService;
    
    @Resource
    PublishQueueService publishQueueService;
    
    @Resource
    UserService userService;
    
    public Result add(HttpServletRequest request, HttpServletResponse response)
    {
        result.setErrorNo(0);
        result.setErrorInfo("操作成功");
        return result;
    }
    
    /**
     * 添加日志信息到数据库中
     * @param operate
     * @param description
     */
    public void addLog(String operate, String description)
    {
        String uid = getUID();
        //获得用户所登陆的站点
        String siteNo = getLoginSiteNo();
        String branchNo = UserHelper.getUserBranch();
        
        addLog(uid, siteNo, operate, description, branchNo, 0);
    }
    
    /**
     * 添加日志信息到数据库中
     * @param operate
     * @param description
     */
    public void addLog(String operate, String description, int catalogId)
    {
        String uid = getUID();
        //获得用户所登陆的站点
        String siteNo = getLoginSiteNo();
        String branchNo = UserHelper.getUserBranch();
        
        addLog(uid, siteNo, operate, description, branchNo, catalogId);
    }
    
    public void addLog(String uid, String siteNo, String operate, String description)
    {
        String branchNo = UserHelper.getUserBranch();
        addLog(uid, siteNo, operate, description, branchNo, 0);
    }
    
    /**
     * @描述：添加日志信息到数据库中
     * @作者：袁永君
     * @时间：2011-1-4 下午03:51:56
     * @param uid
     * @param siteNo
     * @param operate
     * @param description
     */
    public void addLog(String uid, String siteNo, String operate, String description, String branchNo, int catalogId)
    {
        String ip = getIp();
        String curDateTime = DateHelper.formatDate(new Date());
        
        Log log = new Log();
        log.setCreateBy(uid);
        log.setCreateDate(curDateTime);
        log.setDescription(description);
        log.setIp(ip);
        log.setOperate(operate);
        log.setSiteNo(siteNo);
        log.setBranchno(branchNo);
        log.setCatalogId(catalogId);
        
        logService.addLog(log);
    }
    
    public void addToPublishQueue(int id, String flag)
    {
        flag = flag.toUpperCase();
        
        DynaModel data = new DynaModel();
        data.put("cmd_str", flag + ":" + id);
        if ("A".equals(flag))
        {
            data.put("show_info", "发布文章[articleId=" + id + "]");
        }
        else if ("T".equals(flag))
        {
            data.put("show_info", "发布模板[catalogId=" + id + "]");
        }
        else if ("C".equals(flag))
        {
            data.put("show_info", "发布栏目[catalogId=" + id + "]");
        }
        else if ("CR".equals(flag))
        {
            data.put("show_info", "发布栏目[catalogId=" + id + "]及其所有子栏目");
        }
        else
        {
            return;
        }
        
        data.set("state", 0);
        data.set("siteno", getLoginSiteNo());
        data.set("create_by", getUID());
        data.set("create_date", DateHelper.formatDate(new Date()));
        data.set("machine_id", Configuration.getString("system.machineId"));
        
        publishQueueService.add(data);
    }
    
    public Result delete(HttpServletRequest request, HttpServletResponse response)
    {
        result.setErrorNo(0);
        result.setErrorInfo("操作成功");
        return result;
    }
    
    public ModelAndView doAdd()
    {
        return mv;
    }
    
    public ModelAndView doDefault()
    {
        return mv;
    }
    
    public ModelAndView doEdit(HttpServletResponse response)
    {
        return mv;
    }
    
    public Result doEditState(HttpServletRequest request, HttpServletResponse response)
    {
        result.setErrorNo(0);
        result.setErrorInfo("操作成功");
        return result;
    }
    
    public Result edit(HttpServletRequest request, HttpServletResponse response)
    {
        result.setErrorNo(0);
        result.setErrorInfo("操作成功");
        return result;
    }
    
    /**
     * 从HttpServletRequest中提取属性值
     * @param attributeName
     * @return
     */
    public Object getAttribute(String attributeName)
    {
        return getRequest().getAttribute(attributeName);
    }
    
    /**
     * 获得当前用户的营业网点编号
     *
     * @return
     */
    public String getBranchNo()
    {
        String uid = getUID();
        User user = userService.findUserByUID(uid);
        return user.getBranchNo();
    }
    
    public Map<String, Object> getData()
    {
        return dataMap;
    }
    
    public DynaForm getForm()
    {
        return form;
    }
    
    /**
     * 返回整数数组，若不存在，则返回长度为0的整型数组
     *
     * @param name
     * @return
     */
    public int[] getIntArrayParameter(String name)
    {
        String[] valueArray = getStrArrayParameter(name);
        int[] result = new int[valueArray.length];
        for (int i = 0; i < valueArray.length; i++)
        {
            result[i] = ConvertHelper.strToInt(valueArray[i]);
        }
        return result;
    }
    
    /**
     * 从HttpServletRequest中提取属性值
     * @param attributeName 属性名称
     * @return
     */
    public int getIntAttribute(String attributeName)
    {
        String value = getStrAttribute(attributeName);
        if (StringHelper.isEmpty(value))
            return 0;
        try
        {
            return new Integer(value).intValue();
        }
        catch (Exception ex)
        {
            return 0;
        }
    }
    
    /**
     * 从HttpServletRequest中提取属性值
     * @param attributeName 属性名称
     * @param defaultValue  缺省值
     * @return
     */
    public int getIntAttribute(String attributeName, int defaultValue)
    {
        int value = getIntAttribute(attributeName);
        if (value == 0)
            value = defaultValue;
        return value;
    }
    
    /**
    * 返回整数，若不存在或转换失败，则返回0
    * @param name
    * @return
    */
    public int getIntParameter(String name)
    {
        return ConvertHelper.strToInt(getStrParameter(name));
    }
    
    /**
    * 返回整数，若不存在或转换失败，则返回缺省值
    * @param name
    * @param defaultValue
    * @return
    */
    public int getIntParameter(String name, int defaultValue)
    {
        String value = getStrParameter(name);
        if (StringHelper.isEmpty(value))
        {
            return defaultValue;
        }
        else
        {
            return ConvertHelper.strToInt(value);
        }
    }
    
    /**
     * 描述: getIp    获取ip
     * 作者: 袁永君
     * 创建日期: 2015-12-22
     * 创建时间: 下午10:08:55
     */
    public String getIp()
    {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader("x-forwarded-for");
        
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    /*
     * 判断是否是站点系统管理员
     * @return
     */
    public boolean getIsSystem()
    {
        boolean isSystem = isSystemAdmin();
        return isSystem;
    }
    
    /**
     * 描述：获得用户登陆时的站点编号
     * @return
     */
    public String getLoginSiteNo()
    {
        //首先声明变量，是用来存放用户所选择的登陆站点
        //String siteno = "";
        /**
         * 判断用户使用那种账号登陆。判断的方法是：如果session中保存的站点为"all"，表示用户是使用的超级管理员登陆的。
         * 哪么，就将超级管理员登陆时选择的站点，赋值给变量。否则就是为普通客户登陆，就直接将改用户所属的站点直接赋
         * 值给变量。
         */
        //if(getSiteNo().equals("all"))//如果登陆后的站点为"all",，则表示用户是使用超级管理员账号进行登陆
        //{
        //将超级管理员登陆时所选择的站点赋值给变量
        //    siteno = getSpuerSiteNo();
        //}
        //else//否则是普通用户登陆，就直接将登陆的站点赋值给变量
        //{
        //普通客户就直接将登陆时的站点赋值给变量
        //    siteno = getSiteNo();
        //}
        return getSiteNo();
    }
    
    /**
     * 返回长整数数组，若不存在，则返回长度为0的长整型数组
     * @param name
     * @return
     */
    public long[] getLongArrayParameter(String name)
    {
        String[] valueArray = getStrArrayParameter(name);
        long[] result = new long[valueArray.length];
        for (int i = 0; i < valueArray.length; i++)
        {
            result[i] = ConvertHelper.strToLong(valueArray[i]);
        }
        return result;
    }
    
    /**
     * 返回长整数，若不存在或转换失败，则返回0
     *
     * @param name
     * @return
     */
    public long getLongParameter(String name)
    {
        return ConvertHelper.strToLong(getStrParameter(name));
    }
    
    /**
     * 返回长整数，若不存在或转换失败，则返回0
     *
     * @param name
     * @return
     */
    public long getLongParameter(String name, long defaultValue)
    {
        String value = getStrParameter(name);
        if (StringHelper.isEmpty(value))
        {
            return defaultValue;
        }
        else
        {
            return ConvertHelper.strToLong(value);
        }
    }
    
    /**
     * 返回HttpServletRequest对象
     * @return
     */
    public HttpServletRequest getRequest()
    {
        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ra.getRequest();
        return request;
    }
    
    /**
     * 返回HttpServletResponse对象
     *
     * @return
     */
    public HttpServletResponse getResponse()
    {
        return response;
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
    
    /*
     * 获得站点名称
     * @return
     */
    public String getSiteName()
    {
        String siteNo = getSiteNo();
        SiteService service = (SiteService) SpringContextHolder.getBean("siteService");
        Site site = service.findSiteBySiteNO(siteNo);
        String siteName = site.getName();
        String uId = getUID();
        return uId + "," + siteName;
    }
    
    /**
     * 设置当前后台用户使用的站点
     * @param siteno
     */
    /*public void setSiteNo(String siteno)
     {
     Map session = ActionContext.getContext().getSession();
     session.put(Constants.ADMIN_SITENO, siteno);
     }*/
    /**
     * 获得当前后台用户登录的站点
     * @return
     */
    public String getSiteNo()
    {
        HttpSession session = getRequest().getSession();
        return (String) session.getAttribute(Constants.ADMIN_SITENO);
    }
    
    /**
     * 描述：获得超级管理员所登录时选择的站点
     * @return
     */
    public String getSpuerSiteNo()
    {
        HttpSession session = getRequest().getSession();
        return (String) session.getAttribute(Constants.SUPER_ADMIN_SITENO);
    }
    
    /**
     * 返回字串数组Parameter，若不存在，则返回空字符串数组
     * @param name
     * @return
     */
    public String[] getStrArrayParameter(String name)
    {
        @SuppressWarnings("unchecked")
        Map<String, String[]> paramMap = getRequest().getParameterMap();
        String[] valueArray = paramMap.get(name);
        if (valueArray != null && valueArray.length > 0)
        {
            return valueArray;
        }
        else
        {
            return new String[0];
        }
    }
    
    /**
     * 从HttpServletRequest中提取属性值
     *
     * @param attributeName 属性名称
     * @return
     */
    public String getStrAttribute(String attributeName)
    {
        String value = (String) getRequest().getAttribute(attributeName);
        return value == null ? "" : value;
    }
    
    /**
     * 从HttpServletRequest中提取属性值
     *
     * @param attributeName 属性名称
     * @param defaultValue  缺省值
     * @return
     */
    public String getStrAttribute(String attributeName, String defaultValue)
    {
        String value = (String) getRequest().getAttribute(attributeName);
        return value == null ? defaultValue : value;
    }
    
    /*public String getSiteNo()
     {
     //Map session = ActionContext.getContext().getSession();
     HttpSession session = ServletActionContext.getRequest().getSession();
     return (String) session.getAttribute("siteno");
     }*/
    
    /**
     * 返回字串Parameter,若不存在，则返回空字串
     * @param name
     * @return
     */
    public String getStrParameter(String name)
    {
        String value = "";
        @SuppressWarnings("unchecked")
        Map<String, String[]> paramMap = getRequest().getParameterMap();
        String[] valueArray = paramMap.get(name);
        if (valueArray != null && valueArray.length > 0)
        {
            value = valueArray[0];
        }
        return (value == null) ? "" : (String) value;
    }
    
    //	/**
    //	 * 获得当前后台登录用户的用户名
    //	 *
    //	 * @return
    //	 */
    //	public String getUserName()
    //	{
    //		HttpSession session = getRequest().getSession();
    //		return (String) session.getAttribute(Constants.ADMIN_USER_NAME);
    //	}
    
    /**
     * 返回字串Parameter,若不存在，则返回缺省值
     * @param name
     * @param defaultValue
     * @return
     */
    public String getStrParameter(String name, String defaultValue)
    {
        String value = "";
        @SuppressWarnings("unchecked")
        Map<String, String[]> paramMap = getRequest().getParameterMap();
        String[] valueArray = paramMap.get(name);
        if (valueArray != null && valueArray.length > 0)
        {
            value = valueArray[0];
        }
        return (value == null) ? defaultValue : (String) value;
    }
    
    /**
     * 返回错误信息
     *
     * @return
     */
    public String getThrowMessage()
    {
        return throwMessage;
    }
    
    /**
     * 获得当前后台登录用户的UID
     * @return
     */
    public String getUID()
    {
        HttpSession session = getRequest().getSession();
        return (String) session.getAttribute(Constants.ADMIN_UID);
    }
    
    /**
     * 判断当前用户是否是系统管理员角色
     *
     * @return
     */
    public boolean isAdministratorsRole()
    {
        HttpSession session = getRequest().getSession();
        int[] roleArray = (int[]) session.getAttribute(Constants.USER_ROLE);
        if (roleArray != null && roleArray.length > 0)
        {
            for (int i = 0; i < roleArray.length; i++)
            {
                if (roleArray[i] == 1) //是administrators角色
                    return true;
            }
        }
        return false;
    }
    
    /*
     Map request = (Map) ActionContext.getContext().get("request");
     request.put("myId",myProp);

     Map application = (Map) ActionContext.getContext().get("application");
     application.put("myId",myProp);

     Map session = (Map) ActionContext.getContext().get("session");
     session.put("myId", myProp);

     Map attr = (Map) ActionContext.getContext().get("attr");
     attr.put("myId",myProp);
     */
    
    /**
     * 判断当前用户登录的是不是主站点
     *
     * @return
     */
    public boolean isMainSite()
    {
        HttpSession session = getRequest().getSession();
        Integer isMain = (Integer) session.getAttribute(Constants.ADMIN_IS_SYSTEM);
        if (isMain.intValue() == 1)
            return true;
        else
            return false;
    }
    
    /**
     * 判断是否是使用post方式提交回数据
     * @return
     */
    public boolean isPostBack()
    {
        String method = getRequest().getMethod();
        return method.equalsIgnoreCase("POST");
    }
    
    /**
     * 判断当前用户是否是系统管理员
     * @return
     */
    public boolean isSystemAdmin()
    {
        HttpSession session = getRequest().getSession();
        Integer isSystem = (Integer) session.getAttribute(Constants.ADMIN_IS_SYSTEM);
        if (isSystem.intValue() == 1)
            return true;
        else
            return false;
    }
    
    /**
     * 规整提交上来的DynaForm对象,因为如果接受数据的是一个Map对象，
     * 则webwork总是会把参数值设为字串数组，所以在此需要把只有一个
     * 数值的对象转换为非数组形式。
     */
    public void normalize(DynaForm form)
    {
        for (Iterator<String> iter = form.keySet().iterator(); iter.hasNext();)
        {
            String key = iter.next();
            Object value = form.get(key);
            if (value instanceof String[]) //若是字符串数组
            {
                String[] strArray = (String[]) value;
                if (strArray.length == 1)
                {
                    form.put(key, strArray[0]);
                }
            }
        }
    }
    
    /**
     * 描述: 获取form表单参数
     * 作者: 袁永君
     * 创建日期: 2015-10-29
     * 创建时间: 上午9:11:16
     * @param request
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public DynaForm normalize(HttpServletRequest request)
    {
        DynaForm form = new DynaForm();
        
        Enumeration<String> paramNames = request.getParameterNames();
        for (Enumeration e = paramNames; e.hasMoreElements();)
        {
            String thisName = e.nextElement().toString();
            String thisValue = request.getParameter(thisName);
            
            if (thisName.indexOf("form.") > -1)
            {
                thisName = thisName.split("form.")[1];
                try
                {
                    thisValue = thisValue.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
                    thisValue = thisValue.replaceAll("\\+", "%2B");
                    thisValue = URLDecoder.decode(thisValue, "utf-8");
                }
                catch (UnsupportedEncodingException e1)
                {
                    e1.printStackTrace();
                    break;
                }
                form.set(thisName, thisValue);
            }
        }
        return form;
    }
    
    /**
     * 描述: 获取form表单参数
     * 作者: 袁永君
     * 创建日期: 2015-10-29
     * 创建时间: 上午9:11:16
     * @param request
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public DynaModel[] normalizeList(HttpServletRequest request)
    {
        Enumeration<String> paramNames = request.getParameterNames();
        int length = 1;
        List<String> thisNameList = new ArrayList<String>();
        for (Enumeration e = paramNames; e.hasMoreElements();)
        {
            String thisName = e.nextElement().toString();
            thisNameList.add(thisName);
            length = request.getParameterValues(thisName).length;
        }
        
        DynaModel[] forms = new DynaModel[length];
        for (int i = 0; i < length; i++)
        {
            forms[i] = new DynaModel();
        }
        for (String thisName : thisNameList)
        {
            if (thisName.indexOf("form.") > -1)
            {
                String[] thisValues = request.getParameterValues(thisName);
                thisName = thisName.split("form\\.")[1];
                try
                {
                    int i = 0;
                    for (String thisValue : thisValues)
                    {
                        thisValue = URLDecoder.decode(thisValue, "utf-8");
                        forms[i++].put(thisName, thisValue);
                    }
                }
                catch (UnsupportedEncodingException e1)
                {
                    e1.printStackTrace();
                    break;
                }
            }
        }
        
        return forms;
    }
    
    /**
     * 描述: 获取form表单参数
     * 作者: 袁永君
     * 创建日期: 2015-10-29
     * 创建时间: 上午9:11:16
     * @param request
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public DynaForm normalize(HttpServletRequest request, String key)
    {
        DynaForm form = new DynaForm();
        Enumeration<String> paramNames = request.getParameterNames();
        for (Enumeration e = paramNames; e.hasMoreElements();)
        {
            String thisName = e.nextElement().toString();
            String thisValue = request.getParameter(thisName);
            if (thisName.indexOf(key + ".") > -1)
            {
                thisName = thisName.split(key + ".")[1];
                try
                {
                    thisValue = URLDecoder.decode(thisValue, "utf-8");
                }
                catch (UnsupportedEncodingException e1)
                {
                    e1.printStackTrace();
                    break;
                }
                form.set(thisName, thisValue);
            }
        }
        return form;
    }
    
    /**
     * 向HttpServletRequest中设置对象
     * @param attributeName
     * @param object
     */
    public void setAttribute(String attributeName, Object object)
    {
        getRequest().setAttribute(attributeName, object);
    }
    
    /**
     * 向HttpServletRequest中设置属性值
     *
     * @param attributeName 属性名称
     * @param value         属性值
     */
    public void setIntAttribute(String attributeName, int value)
    {
        getRequest().setAttribute(attributeName, new Integer(value));
    }
    
    void setRequest(HttpServletRequest request)
    {
        this.request = request;
    }
    
    void setResponse(HttpServletResponse response)
    {
        this.response = response;
    }
    
}
