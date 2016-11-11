package com.yizhishang.plat.web.action;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.domain.Right_Url;
import com.yizhishang.base.util.BeanHelper;
import com.yizhishang.base.util.DateHelper;
import com.yizhishang.base.util.RequestHelper;
import com.yizhishang.base.util.ScriptHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.base.util.UserHelper;
import com.yizhishang.plat.domain.ManageCatalog;
import com.yizhishang.plat.domain.Result;
import com.yizhishang.plat.domain.Right_Function;
import com.yizhishang.plat.domain.Right_Module;
import com.yizhishang.plat.domain.User;
import com.yizhishang.plat.service.ManageCatalogService;
import com.yizhishang.plat.service.RoleService;
import com.yizhishang.plat.service.SiteService;
import com.yizhishang.plat.service.UserService;
import com.yizhishang.plat.system.SysLibrary;
import com.yizhishang.plat.web.form.DynaForm;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-26
 * 创建时间: 12:06:16
 */
@Controller
@RequestMapping("/admin/leftAdmin")
public class LeftAction extends BaseAction
{
    
    @Resource
    ManageCatalogService manageCatalogService;
    
    @Resource
    RoleService roleService;
    
    @Resource
    SiteService siteService;
    
    @Resource
    UserService userService;
    
    @Override
    public Result add(HttpServletRequest request, HttpServletResponse reponse)
    {
        //对提交上来的form进行处理
        form = normalize(request);
        
        if (StringHelper.isEmpty(form.getString("catalogNo")))
        {
            MESSAGE = "栏目英文名不能为空";
            result.setErrorNo(-1);
            result.setErrorInfo(MESSAGE);
            return result;
        }
        if (StringHelper.isEmpty(form.getString("name")))
        {
            MESSAGE = "栏目名称不能为空";
            result.setErrorNo(-1);
            result.setErrorInfo(MESSAGE);
            return result;
        }

        ManageCatalog catalog = new ManageCatalog();
        Right_Function right_function = new Right_Function();
        Right_Module right_module = new Right_Module();
        Right_Url right_url = new Right_Url();
        
        BeanHelper.mapToBean(form, catalog);
        
        //栏目名称
        String name1 = form.getString("name");
        String ename1 = form.getString("catalogNo");
        String linkUrl = form.getString("linkUrl");
        String siteno = getLoginSiteNo();
        
        catalog.setSiteNo(siteno);
        catalog.setCreateBy(getUID());
        catalog.setCreateDate(DateHelper.formatDate(new Date()));
        catalog.setModifiedBy(getUID());
        catalog.setModifiedDate(DateHelper.formatDate(new Date()));
        
        manageCatalogService.addCatalog(catalog);
        catalog.setOrderLine(catalog.getId());
        catalog.setRoute(manageCatalogService.getRoute(catalog.getId()));
        manageCatalogService.updateCatalog(catalog);
        
        /************** 查找t_right_modulen表中是否存在父级栏目，有就删掉 **********************/
        //获得该栏目的父级栏目编号
        int parentId = catalog.getParentId();
        //判断权限模块中是否存在改栏目的父级栏目信息，如果存在返回true，否则返回false;
        boolean bool = checkModuleIsEmpty(parentId, siteno);
        //如果权限模块中存在父级栏目数据，则删除该数据
        if (bool)
        {
            roleService.deleteRoleModule(parentId, siteno);
        }
        
        /*********************** 添加默认跳转页 *************************/
        right_function.setFunction_code(catalog.getId() + "0000");
        right_function.setName(name1);
        right_function.setEname(ename1);
        right_function.setModule_code(String.valueOf(catalog.getId()));
        right_function.setSiteno(siteno);
        roleService.addRightFunction(right_function);
        right_url.setRight_url(checkUrl(linkUrl));
        right_url.setFunction_code_list(catalog.getId() + "0000");
        right_url.setSiteno(siteno);
        roleService.addRightUrl(right_url);
        
        //获得添加栏目功能编号
        String checkStr = this.getStrParameter("checkboxvalue");
        if (StringHelper.isNotEmpty(checkStr))
        {
            String[] str = checkStr.split(",");
            /*********************** 添加其他跳转功能 ***************************/
            //功能编号
            String function_code = "";
            //功能名称
            String name = "";
            //英文名称
            String ename = "";
            //跳转路径
            String url = "";
            //模块编号
            String module_code = "";
            for (int i = 0; i < str.length; i++)
            {
                function_code = catalog.getId() + str[i];
                name = this.getStrParameter(str[i] + "_name");
                ename = this.getStrParameter(str[i] + "_ename");
                url = this.getStrParameter(str[i] + "_url");
                module_code = String.valueOf(catalog.getId());
                /******************** 添加权限功能 ***********************/
                right_function.setFunction_code(function_code);
                right_function.setName(name);
                right_function.setEname(ename);
                right_function.setModule_code(module_code);
                right_function.setSiteno(siteno);
                roleService.addRightFunction(right_function);
                /************************ 添加跳转路径 *******************/
                right_url.setRight_url(url);
                right_url.setFunction_code_list(function_code);
                right_url.setSiteno(siteno);
                roleService.addRightUrl(right_url);
            }
        }
        
        /****************** 添加本栏目信息到权限模块表中去 *************************/
        //添加模块编号
        right_module.setModule_code(String.valueOf(catalog.getId()));
        //模块的名称
        right_module.setName(catalog.getName());
        //模块英文名称
        right_module.setEname(catalog.getCatalogNo());
        //排序值
        right_module.setOrderline(catalog.getOrderLine());
        //显示状态
        right_module.setEnable("1");
        //站点编号
        right_module.setSiteno(siteno);
        roleService.addModule(right_module);
        
        addLog("添加栏目", "添加栏目[ID=" + catalog.getId() + ",name=" + catalog.getName() + "]");
        ScriptHelper.alert(getResponse(), "添加栏目[" + catalog.getName() + "]成功,请刷新被添加栏目的父目录");
        
        return result;
    }
    
    /**
    * 描述：检查刚添加的栏目所属的父级栏目是否在权限模块表中存在，如果存在返回true，反之返回false
    * @param parentId
    * @param siteno
    * @return
    */
    public boolean checkModuleIsEmpty(int parentId, String siteno)
    {
        List<DynaModel> argList = roleService.findModuleByParentId(parentId, siteno);
        //如果list里面的没有数据，则表示权限模块表中不存在父级栏目的数据，返回false，反之表示有数据，
        //返回true
        if (argList.isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
    * 描述：检测跳转路径里面是否存在跳转的方法，如果没有，则加上默认方法，否则就直接返回
    * @param linkUrl
    * @return
    */
    public String checkUrl(String linkUrl)
    {
        //如果路径里面有“？”表示后面会跟有跳转的方法，如果返回值为-1则表示没有，需要添加上
        if (linkUrl.indexOf("?") == -1)
        {
            linkUrl = linkUrl + "?function=default";
            return linkUrl;
        }
        else
        {
            //判断路径里面是否有"function"这个参数，返回-1表示没有，则截取并将function添加进去
            if (linkUrl.indexOf("function") == -1)
            {
                String uri = linkUrl.substring(0, linkUrl.indexOf("?") - 1);
                String param = linkUrl.substring(linkUrl.indexOf("?") + 1, linkUrl.length());
                linkUrl = uri + "?function=default" + "&" + param;
                return linkUrl;
            }
            else
            {
                //否则直接返回路径;
                return linkUrl;
            }
        }
    }
    
    /**
    * 添加子栏目
    * @return
    */
    @Override
    public ModelAndView doAdd()
    {
        int parentId = getIntParameter("catalogId");
        form.set("parentId", parentId);
        mv.addObject("form", form);
        mv.setViewName("/WEB-INF/views/manage_catalog/add_catalog.jsp");
        return mv;
    }
    
    @RequestMapping("/bar.action")
    public String doBar()
    {
        return "/WEB-INF/views/bar.jsp";
    }
    
    /**
    * 缺省的操作(function=""时调用)
    * @return
    */
    @RequestMapping("/default.action")
    public ModelAndView doDefault(HttpServletRequest request)
    {
        DynaForm form = normalize(request);
        //获得相关数据并放主dataMap中
        int manageCatalogId = form.getInt("manageCatalogId");
        if (manageCatalogId == 0)
        {
            //默认是系统管理菜单1766
            manageCatalogId = 1766;
        }
        List<ManageCatalog> menus = SysLibrary.getSecurityCatalogTree(getSession());
        dataMap.put("rootMenus", menus);
        for (Iterator<ManageCatalog> iter = menus.iterator(); iter.hasNext();)
        {
            ManageCatalog menu = (ManageCatalog) iter.next();
            if (menu.getId() == manageCatalogId)
            {
                dataMap.put("menuCatalogs", menu.getChildren());
                break;
            }
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("data", dataMap);
        ManageCatalog catalog = manageCatalogService.findCatalogById(manageCatalogId);
        //业务管理跳转到相应的栏目树
        String viewName = "/WEB-INF/views/left.jsp";
        if ("BUSINESS_MANAGE".equals(catalog.getCatalogNo()))
        {
            viewName = "/WEB-INF/views/businessLeft.jsp";
        }
        mv.setViewName(viewName);
        return mv;
    }
    
    /**
    * 删除栏目
    *
    * @return
    */
    public String doDelete()
    {
        //获得栏目编号
        int catalogId = getIntParameter("catalogId");
        //获得登陆的站点
        String siteno = getLoginSiteNo();

        if (manageCatalogService.hasChildren(catalogId))
        {
            ScriptHelper.alert(getResponse(), "该栏目还有子目录，不能删除");
        }
        else
        {
            ManageCatalog catalog = manageCatalogService.findCatalogById(catalogId);
            if (catalog.getIsSystem() == 1) //系统目录
            {
                ScriptHelper.alert(getResponse(), "该栏目为系统栏目，不能删除");
            }
            else
            {
                manageCatalogService.deleteCatalog(catalogId, siteno);
                roleService.delRightUrl(catalogId, siteno);
                roleService.delRightFunction(catalogId, siteno);
                roleService.deleteRoleModule(catalogId, siteno);
                addLog("删除栏目", "删除栏目[ID=" + catalogId + "]");
                ScriptHelper.alert(getResponse(), "删除栏目成功，请刷新被删除栏目的父目录");
            }
        }
        return NONE;
    }
    
    /**
    * 编辑栏目属性
    * @return
    */
    @Override
    public ModelAndView doEdit(HttpServletResponse response)
    {
        int catalogId = getIntParameter("catalogId");
        ManageCatalog catalog = manageCatalogService.findCatalogById(catalogId);
        if (catalog == null)
        {
            ScriptHelper.alert(getResponse(), "该栏目不存在或已经被删除");
            return null;
        }
        BeanHelper.beanToMap(catalog, form);
        String siteno = getLoginSiteNo();
        List<DynaModel> list = roleService.findRightFunction(catalogId, siteno);
        //查找本栏目下是否还有子栏目
        List<ManageCatalog> valiList = manageCatalogService.findCatalogInfoByParentId(catalogId, siteno);
        RequestHelper.setAttribute(getRequest(), "list", list);
        //查找登陆的人是否是管理员
        boolean sysAdmin = isSystemAdmin();
        form.set("sysAdmin", Boolean.valueOf(sysAdmin));
        
        //如果还有子栏目就返回true，如果没有子栏目就返回false
        //        boolean catalogBool = false;
        //        if (valiList.isEmpty())
        //        {
        //            catalogBool = false;
        //        }
        //        else
        //        {
        //            catalogBool = true;
        //        }
        //        form.set("catalogBool", catalogBool);
        form.set("catalogBool", !valiList.isEmpty());
        if (!sysAdmin)
        {
            return new ModelAndView("/WEB-INF/views/noRight.jsp");
        }
        mv.addObject("form", form);
        mv.setViewName("/WEB-INF/views/manage_catalog/add_catalog.jsp");
        return mv;

    }
    
    /**
    * 得到子目录的xml文档
    * @return
    */
    @ResponseBody
    @RequestMapping(value = "/showChild.action", produces = "text/xml;charset=GBK")
    public String doShowChild(HttpServletRequest request, HttpServletResponse response)
    {
        int parentId = getIntParameter("parentNo", 1);
        StringBuffer buffer = new StringBuffer();
        response.setContentType("text/xml;charset=GBK");
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setDateHeader("expires", -10);
        
        buffer.append("<?xml version=\"1.0\" encoding=\"GBK\"?>\n");
        buffer.append("<tree>\n");
        
        //		String siteno = getLoginSiteNo();
        List<ManageCatalog> childrenList = manageCatalogService.findChildrenById(parentId, "");
        for (Iterator<ManageCatalog> iter = childrenList.iterator(); iter.hasNext();)
        {
            ManageCatalog menuCatalog = (ManageCatalog) iter.next();
            
            //不显示状态为关闭的栏目  modify by 2010-01-25
            if (menuCatalog.getState() == 0)
            {
                continue;
            }
            
            //获取缓存中URL权限数据 与当前登录用户URL权限比较，看是否具有权限
            boolean boole = false;
            if (SysLibrary.isSystemAdmin(getSession()))
            {
                boole = true;
            }
            else
            {
                @SuppressWarnings("rawtypes")
                HashSet hs = SysLibrary.getUserRight(getSession());
                if (hs.contains(String.valueOf(menuCatalog.getId())))
                {
                    boole = true;
                }
            }
            
            if (boole)
            {
                String name = menuCatalog.getName();
                name = StringHelper.replace(name, "\"", "&quot;");
                name = StringHelper.replace(name, "\'", "&quot;");
                String src = "";
                if (menuCatalog.getChildrennum() > 0)
                {
                    src = "showChild.action?parentNo=" + menuCatalog.getId();
                }
                String linkUrl = menuCatalog.getLinkUrl();
                String url = request.getContextPath() + linkUrl;
                if (StringHelper.isEmpty(linkUrl) || linkUrl.startsWith("#"))
                {
                    url = "javascript:void(0);";
                }
                if (url.indexOf("javascript:void(0)") == -1)
                {
                    if (menuCatalog.getLinkUrl().indexOf("?") == -1)
                    {
                        url += "?manageCatalogId=" + menuCatalog.getId();
                    }
                    else
                    {
                        url += "&amp;manageCatalogId=" + menuCatalog.getId();
                    }
                }
                
                buffer.append("   <tree text=\"" + name + "\" target=\"mainFrame\" action=\"" + url + "\"  src=\"" + src + "\"  value=\"" + menuCatalog.getId()
                        + "\" oncontextmenu=\"true\" />\n");
            }
        }
        
        buffer.append("</tree>");
        return buffer.toString();
    }
    
    /**
    * 描述：查找菜单信息
    * @return
    */
    public List<DynaModel> findMenuInfo()
    {
        List<DynaModel> list = null;
        //获得登陆用的ID
        int user_id = UserHelper.getUserId();
        //查找其相应的信息
        User user = userService.findUserById(user_id);
        //获得其拥有的站点信息
        String siteno = user.getSiteNo();
        //判断用户是不是超级系统管理员
        boolean bool = isSuperSysAdmin(siteno);
        if (bool)
        {
            list = siteService.findAllSiteInfo();
        }
        else
        {
            siteno = StringHelper.replace(siteno, "|", "','");
            siteno = "'" + siteno + "'";
            list = siteService.findSiteBySiteno(siteno);
        }
        return list;
    }
    
    /**
    * 描述：判断是不是超级系统管理员
    * @param siteno
    * @return
    */
    public boolean isSuperSysAdmin(String siteno)
    {
        //如果站点返回值是all表示该用户是超级系统管理员，则返回true，否则就不是，返回false;
        if (siteno.equals("all"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
