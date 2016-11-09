package com.jhcz.plat.web.action;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.util.BeanHelper;
import com.jhcz.base.util.DateHelper;
import com.jhcz.base.util.RequestHelper;
import com.jhcz.base.util.ResponseHelper;
import com.jhcz.base.util.ScriptHelper;
import com.jhcz.base.util.StringHelper;
import com.jhcz.plat.CatalogTreeManage;
import com.jhcz.plat.ExtendFieldManage;
import com.jhcz.plat.domain.Catalog;
import com.jhcz.plat.domain.Result;
import com.jhcz.plat.service.CatalogService;
import com.jhcz.plat.service.CustomFieldService;
import com.jhcz.plat.service.EnumService;
import com.jhcz.plat.service.SeoService;
import com.jhcz.plat.web.form.DynaForm;

/**
 * 描述: 网站栏目管理
 * 版权: Copyright (c) 2015
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2015-12-3
 * 创建时间: 下午11:06:39
 */
@Controller
@RequestMapping("/admin/catalogAdmin")
public class CatalogAction extends BaseAction
{
    
    private static Logger logger = LoggerFactory.getLogger(CatalogAction.class);
    
    @Resource
    CatalogService catalogService;
    
    @Resource
    CustomFieldService customFieldService;
    
    @Resource
    EnumService enumService;
    
    @Resource
    SeoService seoService;
    
    private final DynaForm form = new DynaForm();
    
    @ResponseBody
    @RequestMapping(value = "/add.action", produces = "text/javascript")
    public void add(HttpServletResponse response)
    {
        //对提交上来的form进行处理
        DynaForm form = normalize(getRequest());
        MESSAGE = "";
        if (StringHelper.isEmpty(form.getString("catalogNo")))
        {
            MESSAGE = "栏目英文名不能为空";
        }
        if (StringHelper.isEmpty(form.getString("name")))
        {
            MESSAGE = "栏目名称不能为空";
        }
        if (StringHelper.isNotEmpty(MESSAGE))
        {
            logger.error(MESSAGE);
            ScriptHelper.alert(response, MESSAGE);
            return;
        }
        
        Catalog catalog = new Catalog();
        BeanHelper.mapToBean(form, catalog);
        
        String siteno = getLoginSiteNo();
        
        //catalog.setSiteNo(getSiteNo());
        catalog.setSiteNo(siteno);
        catalog.setCreateBy(getUID());
        catalog.setCreateDate(DateHelper.formatDate(new Date()));
        catalog.setModifiedBy(getUID());
        catalog.setModifiedDate(DateHelper.formatDate(new Date()));
        catalog.setUserRight(form.getInt("userRight"));
        catalog.setChildrennum(0);
        
        catalogService.addCatalog(catalog);
        catalog.setOrderLine(catalog.getId());
        catalog.setRoute(catalogService.getRoute(catalog.getId()));
        catalogService.updateCatalog(catalog);
        
        //更新该栏目中的父栏目拥有的节点数
        catalogService.updateParentCatalogNum(catalog.getParentId(), catalogService.findParentCatalogNum(catalog.getParentId()));
        
        addLog("添加栏目", "添加栏目[ID=" + catalog.getId() + ",name=" + catalog.getName() + "]");
        
        ScriptHelper.eval(response, "parent.catalogLeftFrame.reloadChildrenCatalog();");
        ScriptHelper.alert(response, "添加栏目[" + catalog.getName() + "]成功！", "/admin/rightAdmin/default.action");
    }
    
    @ResponseBody
    @RequestMapping("/addField.action")
    public Result addField(HttpServletRequest request)
    {
        DynaForm form = normalize(request);
        DataRow data = new DataRow();
        data.putAll(form);
        
        data.set("code", "EXT_FIELD" + data.getInt("id"));
        
        String extendContent = data.getString("extend_content");
        String newExtendContent = "";
        if (StringHelper.isNotEmpty(extendContent))
        {
            String[] contents = StringHelper.split(extendContent, "\r\n");
            for (int i = 0; i < contents.length; i++)
            {
                newExtendContent += contents[i].trim();
                newExtendContent += "$";
            }
            newExtendContent = newExtendContent.substring(0, newExtendContent.lastIndexOf("$"));
            data.set("extend_content", newExtendContent);
        }
        
        data.set("create_by", getUID());
        data.set("create_date", DateHelper.formatDate(new Date()));
        data.set("modify_by", getUID());
        data.set("modify_date", DateHelper.formatDate(new Date()));
        
        Result result = new Result(-1);
        if (customFieldService.findExtendFieldInfoByCode(data.getString("code")) != null)
        {
            result.setErrorInfo("该字段代码已被扩展,请重新选择！");
            return result;
        }
        if (customFieldService.addExtendField(data))
        {
        	//添加字段成功，移出扩展字段池中相应的信息
        	ExtendFieldManage.getInstance().removeField(new Integer(data.getInt("id")));
        	addLog(getUID(), getSiteNo(), "添加扩展字段成功", "添加扩展字段池中相应的信息");
        	result.setErrorNo(0);
        	result.setErrorInfo("添加字段成功");
        }else{
        	result.setErrorInfo("添加扩展字段失败");
        }
        return result;
    }
    
    @ResponseBody
    @RequestMapping("/attachPublish.action")
    public void attachPublish(HttpServletRequest request, HttpServletResponse response)
    {
        int catalogId = getIntParameter("catalogId");
        
        String[] attachs = RequestHelper.getStringArray(request, "id_fun");
        
        if (attachs == null || attachs.length == 0)
        {
            ScriptHelper.alert(response, "请至少选择一个附带发布栏目！", "doAttachPublish.action?catalogId=" + catalogId);
            return;
        }
        
        //          String attachCatalogStr = getStrParameter("attachCatalogStr");
        
        //删除当前栏目下的所有附带发布栏目
        catalogService.delAttachByCatalogId(catalogId);
        
        //          String[] attachs = StringHelper.split(attachCatalogStr, "|");
        DataRow data = null;
        for (int i = 0; i < attachs.length; i++)
        {
            data = new DataRow();
            data.set("catalog_id", new Integer(catalogId));
            data.set("attach_catalog_id", attachs[i]);
            data.set("siteno", getSiteNo());
            catalogService.addAttachCatalog(data);
        }
        ScriptHelper.alert(response, "修改附带发布栏目成功", "doEdit.action?catalogId=" + catalogId);
    }
    
    /**
    * 
    * @描述：
    * @作者：袁永君
    * @时间：2010-04-02 19:58:03
    * @param catalogId
    * @param attachMap
    * @return
    */
    public String compareRole(int catalogId, HashSet<String> attachMap)
    {
        String result = "";
        if (attachMap.contains(String.valueOf(catalogId)))
        {
            result = "checked=\"checked\"";
            
        }
        return result;
    }
    
    /**
    * 删除当前栏目及所有子栏目
    * @param catalogId
    */
    private void deleteCatalog(int catalogId)
    {
        List<Catalog> dataList = catalogService.findChildrenById(catalogId);
        if (dataList != null && dataList.size() > 0) //若有子，则先删除子
        {
            for (Iterator<Catalog> iter = dataList.iterator(); iter.hasNext();)
            {
                Catalog catalog = iter.next();
                deleteCatalog(catalog.getId());
            }
        }
        
        catalogService.deleteCatalog(catalogId);
    }
    
    /**
    * 添加子栏目
    * @return
    */
    @Override
    @RequestMapping("/doAdd.action")
    public ModelAndView doAdd()
    {
        int parentId = getIntParameter("catalogId");
        DynaForm form = new DynaForm();
        form.set("parentId", parentId);
        
        DBPage page = enumService.getEnumItemByType(1, 20, "USER_RIGHT", getSiteNo());
        
        //阅读权限
        List<Object> user_right = null;
        //栏目星级
        List<Object> column_level = null;
        
        if (page != null)
        {
            user_right = page.getData();
            page = null;
        }
        
        page = enumService.getEnumItemByType(1, 20, "COLUMN_LEVEL", getSiteNo());
        if (page != null)
        {
            column_level = page.getData();
            page = null;
        }
        
        form.set("user_right", user_right);
        form.set("column_level", column_level);
        ModelAndView mv = new ModelAndView("/WEB-INF/views/catalog/add_catalog.jsp");
        mv.addObject("form", form);
        return mv;
    }
    
    /**
    * 
    * @描述：
    * @作者：袁永君
    * @时间：2015-11-15 16:11:52
    * @return
    */
    @RequestMapping("/doAddField.action")
    public ModelAndView doAddField(HttpServletResponse response)
    {
        Set<Object> fieldData = ExtendFieldManage.getInstance().getFieldData();
        if (fieldData != null && fieldData.size() > 0)
        {
            form.set("fieldData", fieldData);
        }
        else
        {
            ScriptHelper.alert(response, "扩展字段已用完，请联系管理员！", "close");
            return null;
        }
        
        ModelAndView mv = new ModelAndView("/WEB-INF/views/catalog/custom_field/add_field.jsp");
        mv.addObject("form", form);
        return mv;
    }
    
    /**
    * 
    * @描述：异步加载节点
    * @作者：袁永君
    * @时间：2010-06-03 20:49:32
    * @return
    */
    public String doAjaxCatalogTree()
    {
        //取所有子栏目信息
        List<Object> wholeCatalogs = catalogService.findWholeCatalogById(1, getSiteNo());
        
        CatalogTreeManage treeManage = new CatalogTreeManage("radio", true);
        String html = treeManage.getChildrenTreeHtml(wholeCatalogs);
        ResponseHelper.print(getResponse(), html);
        return NONE;
    }
    
    /**
    * 
    * @描述：查询栏目路由值
    * @作者：袁永君
    * @时间：2011-2-15 上午09:43:48
    * @return
    */
    public String doAjaxQueryRoute()
    {
        int catalogId = RequestHelper.getInt(getRequest(), "catalogId");
        int parentId = RequestHelper.getInt(getRequest(), "parentId");
        StringBuffer buffer = new StringBuffer();
        if (catalogId == 0)
        {
            return NONE;
        }
        if (catalogId > 0 && parentId > 0)
        {
            Catalog parentCatalog = catalogService.findCatalogById(parentId);
            
            List<Catalog> catalogs = catalogService.findCatalogs4Route(catalogId);
            if (parentCatalog != null && catalogs != null)
            {
                String parentRouteStr = parentCatalog.getRoute();
                //将需要更改的路由值组装成(栏目ID:路由值#栏目ID:路由值)
                for (Iterator<Catalog> iter = catalogs.iterator(); iter.hasNext();)
                {
                    Catalog catalog = iter.next();
                    String route = catalog.getRoute();
                    int startIndex = route.indexOf(String.valueOf(catalogId));
                    if (startIndex > 0)
                    {
                        buffer.append(catalog.getId()).append(":").append(parentRouteStr).append("|").append(route.substring(startIndex));
                        if (iter.hasNext())
                        {
                            buffer.append("#");
                        }
                    }
                    else
                    {
                        continue;
                    }
                }
            }
            
        }
        if (StringHelper.isNotEmpty(buffer.toString()))
        {
            ResponseHelper.print(getResponse(), buffer.toString());
        }
        return NONE;
    }
    
    /**
    * 
    * @描述：需要附带发布的栏目
    * @作者：袁永君
    * @时间：2011-4-3 上午11:49:31
    * @return
    */
    @RequestMapping("/doAttachPublish.action")
    public ModelAndView doAttachPublish()
    {
        int catalogId = getIntParameter("catalogId");
        
        //查询当前类别下，哪个栏目生成了计划
        List<Object> attachList = catalogService.findAttachCatalog(catalogId, getSiteNo());
        
        //将已经生成计划的栏目放入map中，一个栏目只能生成一种计划
        HashSet<String> attachMap = new HashSet<String>();
        for (Iterator<Object> iter = attachList.iterator(); iter.hasNext();)
        {
            DataRow data = (DataRow) iter.next();
            attachMap.add(data.getString("attach_catalog_id"));
        }
        
        StringBuffer html = new StringBuffer();
        html.append("<li>");
        DataRow data = new DataRow();
        data.set("catalog_id", "1");
        data.set("route", "1");
        data.set("name", "根目录");
        html.append(getRoleFunction(data, attachMap));
        
        //取所有子栏目信息
        List<Object> childrenCatalog = catalogService.findWholeCatalogById(1, getSiteNo());
        if (childrenCatalog != null)
        {
            html.append(getRoleTreeHtml(childrenCatalog, attachMap));
        }
        html.append("</li>");
        dataMap.put("tree", html.toString());
        ModelAndView mv = new ModelAndView("/WEB-INF/views/catalog/attachPublish.jsp");
        mv.addObject("data", dataMap);
        return mv;
    }
    
    /**
    * 缺省的操作(function=""时调用)
    * 转向栏目的首页页面
    * @return
    */
    @Override
    @RequestMapping("/default.action")
    public ModelAndView doDefault()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/WEB-INF/views/catalog/default.jsp");
        return mv;
    }
    
    /**
    * 删除栏目
    * @return
    */
    @ResponseBody
    @RequestMapping(value = "/doDelete.action", produces = "text/javascript")
    public void doDelete(HttpServletResponse response)
    {
        int catalogId = getIntParameter("catalogId");
        
        if (catalogService.hasChildren(catalogId))
        {
            ScriptHelper.alert(getResponse(), "该栏目还有子目录，不能删除", "/admin/rightAdmin/default.action");
            return;
        }
        else
        {
            Catalog catalog = catalogService.findCatalogById(catalogId);
            if (catalog.getIsSystem() == 1) //系统目录
            {
                ScriptHelper.alert(getResponse(), "该栏目为系统栏目，不能删除", "/admin/rightAdmin/default.action");
                return;
            }
            else
            {
                catalogService.deleteCatalog(catalogId);
                //更新该栏目中的父栏目拥有的节点数
                catalogService.updateParentCatalogNum(catalog.getParentId(), catalogService.findParentCatalogNum(catalog.getParentId()));
                
                addLog("删除栏目", "删除栏目[ID=" + catalogId + "]");
                ScriptHelper.eval(response, "parent.catalogLeftFrame.reloadChildrenCatalog();");
                ScriptHelper.alert(response, "删除栏目成功！", "/admin/rightAdmin/default.action");
            }
        }
    }
    
    /**
    * 
    * @描述：
    * @作者：袁永君
    * @时间：2015-11-15 18:28:01
    * @return
    */
    @ResponseBody
    @RequestMapping("/deleteField.action")
    public Result doDeleteField(HttpServletRequest request, HttpServletResponse reponse)
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++)
        {
            if (customFieldService.delExtendFieldById(idArray[i]))
            {
                //删除字段成功，添加扩展字段池中相应的信息
                ExtendFieldManage.getInstance().addField(new Integer(idArray[i]));
            }
        }
        addLog(getUID(), getSiteNo(), "删除字段", "删除字段成功");
        result.setErrorInfo("删除字段成功");
        result.setErrorNo(0);
        return result;
    }
    
    /**
    * 编辑栏目属性
    * @return
    */
    @Override
    @RequestMapping("/doEdit.action")
    public ModelAndView doEdit(HttpServletResponse response)
    {
        ModelAndView mv = new ModelAndView();
        int catalogId = getIntParameter("catalogId");
        
        //只有系统管理员才能修改根栏目信息
        if (catalogId == 1 && !isSystemAdmin())
        {
            mv.setViewName(NO_RIGHT);
        }
        
        Catalog catalog = catalogService.findCatalogById(catalogId, getSiteNo());
        if (catalog == null)
        {
            ScriptHelper.alert(getResponse(), "该栏目不存在或已经被删除");
            return mv;
        }
        BeanHelper.beanToMap(catalog, form);
        
        /**
        * 查询源栏目ID名称
        */
        if (catalog.getAttribute() > 0 && catalog.getSourceId() > 0)
        {
            Catalog sourceCatalog = catalogService.findCatalogById(catalog.getSourceId());
            if (sourceCatalog != null)
            {
                String sourceRoute = sourceCatalog.getRoute();
                String[] sourceRoutes = sourceRoute.split("\\|");
                String sourceParentCatalogs = "";
                for (int i = 0; i < sourceRoutes.length; i++)
                {
                    Catalog paramCatalog = catalogService.findCatalogById(Integer.parseInt(sourceRoutes[i]));
                    if (paramCatalog != null)
                    {
                        sourceParentCatalogs += paramCatalog.getName();
                        if (i < sourceRoutes.length - 1)
                        {
                            sourceParentCatalogs += "|";
                        }
                    }
                }
                
                form.set("sourceName", sourceParentCatalogs + "(" + sourceCatalog.getId() + ")");
            }
        }
        
        DBPage page = enumService.getEnumItemByType(1, 20, "USER_RIGHT", getSiteNo());
        
        //阅读权限
        List<Object> user_right = null;
        //栏目星级
        List<Object> column_level = null;
        
        if (page != null)
        {
            user_right = page.getData();
            page = null;
        }
        
        page = enumService.getEnumItemByType(1, 20, "COLUMN_LEVEL", getSiteNo());
        if (page != null)
        {
            column_level = page.getData();
            page = null;
        }
        
        form.set("user_right", user_right);
        form.set("column_level", column_level);
        mv.addObject("form", form);
        mv.setViewName("/WEB-INF/views/catalog/edit_catalog.jsp");
        
        return mv;
    }
    
    /**
    * 
    * 描述:  更新当前节点的了节点数量  
    * 作者:	 
    * 创建日期: 2010-1-26
    * 创建时间: 上午11:13:31
    * @return String
    * @throws
    */
    public String doEditChildrenNum()
    {
        int catalogId = getIntParameter("catalogId");
        updateChildrenNum(catalogId, getSiteNo());
        return NONE;
    }
    
    /**
    * 
    * @描述：
    * @作者：袁永君
    * @时间：2015-11-15 18:02:31
    * @return
    */
    public String doEditField()
    {
        if (isPostBack())
        {
            normalize(form);
            DataRow data = new DataRow();
            data.putAll(form);
            
            String extendContent = data.getString("extend_content");
            if (StringHelper.isNotEmpty(extendContent))
            {
                extendContent = StringHelper.replace(extendContent, "\r\n", "$");
                data.set("extend_content", extendContent);
            }
            
            data.set("modify_by", getUID());
            data.set("modify_date", DateHelper.formatDate(new Date()));
            
            customFieldService.editFieldInfo(data);
            
            return MESSAGE;
        }
        else
        {
            int id = RequestHelper.getInt(getRequest(), "id");
            DataRow data = customFieldService.findExtendFieldInfoById(id);
            if (data != null)
            {
                String extend_content = data.getString("extend_content");
                if (StringHelper.isNotEmpty(extend_content))
                {
                    extend_content = StringHelper.replace(extend_content, "$", "\r\n");
                    data.set("extend_content", extend_content);
                }
            }
            else
            {
                ScriptHelper.alert(getResponse(), "该扩展字段信息不存在，请勿非法操作！", "close");
                return NONE;
            }
            
            form.putAll(data);
            
            return "edit_field";
        }
    }
    
    /**
    * 显示左侧的页面
    * @return
    */
    @RequestMapping("/left.action")
    public ModelAndView doLeft()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/WEB-INF/views/catalog/left.jsp");
        return mv;
    }
    
    /**
    * 
    * @描述：显示文档自定义字段
    * @作者：袁永君
    * @时间：2015-11-15 13:58:08
    * @return
    */
    @RequestMapping("/doListField.action")
    public ModelAndView doListField(HttpServletRequest request)
    {
        int catalogId = RequestHelper.getInt(request, "catalogId");
        List<Object> fieldList = customFieldService.findExtendFieldInfo(catalogId);
        setAttribute("fieldList", fieldList);
        ModelAndView mv = new ModelAndView("/WEB-INF/views/catalog/custom_field/list_field.jsp");
        return mv;
    }
    
    /**
    * 递归删除当前栏目下的所有子栏目
    *
    * @return
    */
    public String doRecuDelete()
    {
        int catalogId = getIntParameter("catalogId");
        
        if (catalogService.hasChildren(catalogId)) //若有子目录，则删除所有子目录
        {
            List<Catalog> dataList = catalogService.findChildrenById(catalogId);
            for (Iterator<Catalog> iter = dataList.iterator(); iter.hasNext();)
            {
                Catalog catalog = iter.next();
                deleteCatalog(catalog.getId());
            }
            
            //更新当前栏目中的子栏目拥有的节点数
            catalogService.updateParentCatalogNum(catalogId, catalogService.findParentCatalogNum(catalogId));
            ScriptHelper.eval(getResponse(), "parent.catalogLeftFrame.reloadChildrenCatalog();");
            ScriptHelper.alert(getResponse(), "删除所有子栏目成功！", "right.action");
        }
        else
        {
            ScriptHelper.alert(getResponse(), "当前栏目没有子栏目，不需要删除！", "right.action");
        }
        
        return NONE;
    }
    
    /**
    * 
    * @描述：页面seo设置
    * @作者：袁永君
    * @时间：2015-12-05 下午01:55:08
    * @return
    */
    @RequestMapping("/doSeo.action")
    public ModelAndView doSeo()
    {
        int catalogId = getIntParameter("catalogId");
        DataRow dataRow = seoService.findSeoByCatalogid(catalogId, getSiteNo());
        if (dataRow != null)
        {
            form.putAll(dataRow);
        }
        ModelAndView mv = new ModelAndView("/WEB-INF/views/catalog/seo.jsp");
        mv.addObject("form", form);
        return mv;
    }
    
    public String doShowCatalogTree()
    {
        return "show_tree";
    }
    
    /**
    * 得到子目录的xml文档
    * @return
    */
    @RequestMapping(value = "/showChild.action", produces = "text/xml;charset=GBK")
    @ResponseBody
    public String doShowChild(HttpServletResponse response)
    {
        int catalogId = getIntParameter("catalogId", 0);
        StringBuffer buffer = new StringBuffer();
        response.setContentType("text/xml;charset=GBK");
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setDateHeader("expires", -10);
        
        buffer.append("<?xml version=\"1.0\" encoding=\"GBK\"?>\n");
        buffer.append("<tree>\n");
        
        String siteno = getLoginSiteNo();
        if (catalogId <= 0) //显示根目录
        {
            //Catalog rootCatalog = catalogService.findRootCatalog(getSiteNo());
            Catalog rootCatalog = catalogService.findRootCatalog(siteno);
            buffer.append("   <tree text=\"" + rootCatalog.getName() + "\" target=\"catalogRightFrame\" action=\"doEdit.action?catalogId="
                    + rootCatalog.getId() + "\" src=\"showChild.action?catalogId=" + rootCatalog.getId() + "\"  value=\"" + rootCatalog.getId()
                    + "\" oncontextmenu=\"true\" />\n");
        }
        else
        {
            List<Catalog> childrenList = catalogService.findChildrenById(catalogId, siteno);
            for (Iterator<Catalog> iter = childrenList.iterator(); iter.hasNext();)
            {
                Catalog catalog = iter.next();
                String name = catalog.getName();
                name = StringHelper.replace(name, "\"", "&quot;");
                name = StringHelper.replace(name, "\'", "&quot;");
                String src = "";
                if (catalog.getChildrennum() > 0)
                {
                    src = "showChild.action?catalogId=" + catalog.getId();
                }
                buffer.append("   <tree text=\"" + name + "\" target=\"catalogRightFrame\" action=\"doEdit.action?catalogId=" + catalog.getId() + "\"  src=\""
                        + src + "\"  value=\"" + catalog.getId() + "\" oncontextmenu=\"true\" />\n");
            }
        }
        
        buffer.append("</tree>");
        return buffer.toString();
    }
    
    @ResponseBody
    @RequestMapping(value = "/edit.action", produces = "text/javascript")
    public void edit(HttpServletResponse response)
    {
        //对提交上来的form进行处理
        DynaForm form = normalize(getRequest());
        MESSAGE = "";
        if (StringHelper.isEmpty(form.getString("catalogNo")))
        {
            MESSAGE = "栏目英文名不能为空";
        }
        if (StringHelper.isEmpty(form.getString("name")))
        {
            MESSAGE = "栏目名称不能为空";
        }
        
        if (StringHelper.isNotEmpty(MESSAGE))
        {
            logger.error(MESSAGE);
            ScriptHelper.alert(response, MESSAGE);
        }
        
        //修改子栏目路由值
        String routeTempStr = RequestHelper.getString(getRequest(), "routeTempStr");
        if (StringHelper.isEmpty(routeTempStr))
        {
            form.remove("parentId");
        }
        Catalog catalog = new Catalog();
        BeanHelper.mapToBean(form, catalog);
        
        catalog.setModifiedBy(getUID());
        catalog.setModifiedDate(DateHelper.formatDate(new Date()));
        catalog.setUserRight(form.getInt("userRight"));
        
        catalogService.updateCatalog(catalog);
        
        if (StringHelper.isNotEmpty(routeTempStr))
        {
            String[] temp = StringHelper.split(routeTempStr, "#");
            String id = "";
            String route = "";
            catalog = null;
            for (int i = 0; i < temp.length; i++)
            {
                String[] routes = StringHelper.split(temp[i], ":");
                id = routes[0];
                route = routes[1];
                if (StringHelper.isNotEmpty(id) && StringHelper.isNotEmpty(route))
                {
                    catalog = new Catalog();
                    catalog.setId(new Integer(id).intValue());
                    catalog.setRoute(route);
                    catalogService.updateCatalog(catalog);
                }
            }
            //更新栏目的子栏目数量
            updateChildrenNum(1, getSiteNo());
        }
        
        MESSAGE = "编辑栏目[ID=" + catalog.getId() + ",name=" + catalog.getName() + "]";
        addLog("编辑栏目", MESSAGE);
        
        ScriptHelper.eval(response, "parent.catalogLeftFrame.reloadChildrenCatalog();");
        ScriptHelper.alert(response, "编辑栏目[" + catalog.getName() + "]成功！", "/admin/rightAdmin/default.action");
    }
    
    /**
     * 描述: getRoleFunction
     * 作者: 袁永君
     * 创建日期: 2015-12-3
     * 创建时间: 下午10:58:13
     * @param data
     * @param attachMap
     * @return
     */
    public String getRoleFunction(DataRow data, HashSet<String> attachMap)
    {
        StringBuffer buffer = new StringBuffer();
        int catalogId = data.getInt("catalog_id");
        buffer.append("<input type=\"checkbox\"  name=\"id_fun\" id=\"node_" + data.getString("route").replaceAll("\\|", "_") + "\" value=\"" + catalogId
                + "\"  " + compareRole(catalogId, attachMap) + "/><label>" + data.getString("name") + "</label>");
        return buffer.toString();
    }
    
    /**
     * 描述: getRoleTreeHtml
     * 作者: 袁永君
     * 创建日期: 2015-12-3
     * 创建时间: 下午10:58:18
     * @param list
     * @param map
     * @return
     */
    @SuppressWarnings("unchecked")
    private String getRoleTreeHtml(List<Object> list, HashSet<String> map)
    {
        StringBuffer buffer = new StringBuffer();
        if (list != null)
        {
            buffer.append("<ul>");
            for (Iterator<Object> iter = list.iterator(); iter.hasNext();)
            {
                buffer.append("<li>");
                DataRow data = (DataRow) iter.next();
                buffer.append(getRoleFunction(data, map));
                buffer.append(getRoleTreeHtml((List<Object>) data.getObject("children"), map));
                buffer.append("</li>");
            }
            buffer.append("</ul>");
        }
        return buffer.toString();
    }
    
    @ResponseBody
    @RequestMapping("/seo.action")
    public void seo(HttpServletRequest request, HttpServletResponse response)
    {
        int catalogId = getIntParameter("catalogId");
        //对提交上来的form进行处理
        DynaForm form = normalize(request);
        
        DataRow dataRow = seoService.findSeoByCatalogid(catalogId, getSiteNo());
        if (dataRow == null)
        {
            dataRow = new DataRow();
            dataRow.putAll(form);
            
            dataRow.set("catalog_id", catalogId);
            dataRow.set("create_by", getUID());
            dataRow.set("create_date", DateHelper.formatDate(new Date()));
            dataRow.set("modify_by", getUID());
            dataRow.set("modify_date", DateHelper.formatDate(new Date()));
            dataRow.set("siteno", getSiteNo());
            seoService.addSeo(dataRow);
        }
        else
        {
            dataRow.putAll(form);
            dataRow.set("modify_by", getUID());
            dataRow.set("modify_date", DateHelper.formatDate(new Date()));
            seoService.editSeo(dataRow);
        }
        addLog(getUID(), getSiteNo(), "SEO设置", "修改页面SEO设置成功");
        ScriptHelper.alert(response, "修改页面SEO设置成功", "doEdit.action?catalogId=" + catalogId);
    }
    
    /**
     * 描述: 递归更新子节点数量  
     * 作者: 袁永君
     * 创建日期: 2015-12-3
     * 创建时间: 下午11:02:28
     * @param catalogId
     * @param siteNo
     */
    private void updateChildrenNum(int catalogId, String siteNo)
    {
        //更新该目录中的父目录拥有的节点数
        catalogService.updateParentCatalogNum(catalogId, catalogService.findParentCatalogNum(catalogId));
        
        List<Catalog> childrenList = catalogService.findChildrenById(catalogId, siteNo);
        if (childrenList != null && childrenList.size() > 0)
        {
            for (Iterator<Catalog> iter = childrenList.iterator(); iter.hasNext();)
            {
                Catalog catalog = iter.next();
                if (catalog != null)
                {
                    updateChildrenNum(catalog.getId(), siteNo);
                }
            }
        }
    }
}
