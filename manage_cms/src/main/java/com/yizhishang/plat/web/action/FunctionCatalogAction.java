package com.yizhishang.plat.web.action;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.util.BeanHelper;
import com.yizhishang.base.util.DateHelper;
import com.yizhishang.base.util.ScriptHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.domain.EnumItem;
import com.yizhishang.plat.domain.ManageCatalog;
import com.yizhishang.plat.domain.Result;
import com.yizhishang.plat.service.EnumService;
import com.yizhishang.plat.service.ManageCatalogService;
import com.yizhishang.plat.web.form.DynaForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 描述:  功能目录管理
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:
 * 版本:	 1.0
 * 创建日期: 2010-1-13
 * 创建时间: 下午01:47:16
 */
@Controller
@RequestMapping("/admin/functionCatalogAdmin")
public class FunctionCatalogAction extends BaseAction
{

    private final Logger logger = LoggerFactory.getLogger(FunctionCatalogAction.class);

    @Resource
    EnumService enumService;

    @Resource
    ManageCatalogService manageCatalogService;

    @ResponseBody
    @RequestMapping(value = "/add.action", produces = "text/javascript")
    public void addFunctionCatalog(HttpServletRequest request, HttpServletResponse response)
    {

        //对提交上来的form进行处理
        DynaForm form = normalize(request);
        MESSAGE = "";
        if (StringHelper.isEmpty(form.getString("catalogNo"))) {
            MESSAGE = "目录英文名不能为空";
        }
        if (StringHelper.isEmpty(form.getString("name"))) {
            MESSAGE = "目录名称不能为空";
        }
        if (StringHelper.isNotEmpty(MESSAGE)) {
            logger.error(MESSAGE);
            ScriptHelper.alert(response, MESSAGE);
        }

        ManageCatalog catalog = new ManageCatalog();
        BeanHelper.mapToBean(form, catalog);

        String siteno = getLoginSiteNo();

        //catalog.setSiteNo(getSiteNo());
        catalog.setSiteNo(siteno);
        catalog.setCreateBy(getUID());
        catalog.setCreateDate(DateHelper.formatDate(new Date()));
        catalog.setModifiedBy(getUID());
        catalog.setModifiedDate(DateHelper.formatDate(new Date()));

        manageCatalogService.addCatalog(catalog);
        catalog.setOrderLine(catalog.getId());
        catalog.setRoute(manageCatalogService.getRoute(catalog.getId()));
        manageCatalogService.updateCatalog(catalog);

        //更新该目录中的父目录拥有的节点数
        manageCatalogService.updateParentCatalogNum(catalog.getParentId(), manageCatalogService.findParentCatalogNum
                (catalog.getParentId()));

        addLog("添加功能目录", "添加目录[ID=" + catalog.getId() + ",name=" + catalog.getName() + "]");

        ScriptHelper.eval(response, "parent.catalogLeftFrame.reloadChildrenCatalog();");
        ScriptHelper.alert(response, "添加目录[" + catalog.getName() + "]成功！", "/admin/rightAdmin/default.action");
    }

    /**
     * 删除目录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete.action", produces = "text/javascript")
    public void delete(HttpServletResponse response)
    {
        int catalogId = getIntParameter("catalogId");

        if (manageCatalogService.hasChildren(catalogId)) {
            ScriptHelper.alert(response, "该目录还有子目录，不能删除", "right.action");
        } else {
            ManageCatalog catalog = manageCatalogService.findCatalogById(catalogId);
            if (catalog.getIsSystem() == 1) //系统目录
            {
                ScriptHelper.alert(response, "该目录为系统目录，不能删除", "right.action");
            } else {
                manageCatalogService.deleteCatalog(catalogId);

                //更新该目录中的父目录拥有的节点数
                manageCatalogService.updateParentCatalogNum(catalog.getParentId(), manageCatalogService
                        .findParentCatalogNum(catalog.getParentId()));

                addLog("删除功能目录", "删除目录[ID=" + catalogId + "]");

                ScriptHelper.eval(response, "parent.catalogLeftFrame.reloadChildrenCatalog();");
                ScriptHelper.alert(response, "删除目录成功!", "/admin/rightAdmin/default.action");
            }
        }
    }

    /**
     * 添加子目录
     *
     * @return
     */
    @Override
    @RequestMapping("/doAdd.action")
    public ModelAndView doAdd()
    {

        int parentId = getIntParameter("catalogId");
        DynaForm form = new DynaForm();
        form.set("parentId", parentId);
        DBPage<EnumItem> page = enumService.getEnumItemByType(1, 20, "USER_RIGHT", getSiteNo());

        //阅读权限
        List<EnumItem> user_right = null;
        //目录星级
        List<EnumItem> column_level = null;

        if (page != null) {
            user_right = page.getData();
            page = null;
        }

        page = enumService.getEnumItemByType(1, 20, "COLUMN_LEVEL", getSiteNo());
        if (page != null) {
            column_level = page.getData();
            page = null;
        }

        form.set("user_right", user_right);
        form.set("column_level", column_level);

        mv.setViewName("/WEB-INF/views/function_catalog/add_catalog.jsp");
        mv.addObject("form", form);
        return mv;
    }

    /**
     * 缺省的操作(function=""时调用)
     * 转向目录的首页页面
     *
     * @return
     */
    @Override
    @RequestMapping("/default.action")
    public ModelAndView doDefault()
    {
        mv.setViewName("/WEB-INF/views/function_catalog/default.jsp");
        return mv;
    }

    @RequestMapping("/doEdit.action")
    public String doEdit(Model model, HttpServletResponse response)
    {
        int catalogId = getIntParameter("catalogId");
        ManageCatalog catalog = manageCatalogService.findCatalogById(catalogId);
        if (catalog == null) {
            ScriptHelper.alert(response, "该目录不存在或已经被删除");
            return NONE;
        }
        BeanHelper.beanToMap(catalog, form);

        DBPage<EnumItem> page = enumService.getEnumItemByType(1, 20, "USER_RIGHT", getSiteNo());

        //阅读权限
        List<EnumItem> user_right = null;
        //目录星级
        List<EnumItem> column_level = null;

        if (page != null) {
            user_right = page.getData();
            page = null;
        }

        page = enumService.getEnumItemByType(1, 20, "COLUMN_LEVEL", getSiteNo());
        if (page != null) {
            column_level = page.getData();
            page = null;
        }

        form.set("user_right", user_right);
        form.set("column_level", column_level);

        model.addAttribute("form", form);

        return "/WEB-INF/views/function_catalog/edit_catalog.jsp";
    }

    /**
     * 描述:  更新当前节点的了节点数量
     * 作者:
     * 创建日期: 2010-1-26
     * 创建时间: 上午11:13:31
     *
     * @return String
     * @throws
     */
    public String doEditChildrenNum()
    {
        int catalogId = getIntParameter("catalogId");
        updateChildrenNum(catalogId);
        return NONE;
    }

    /**
     * 显示左侧的页面
     *
     * @return
     */
    @RequestMapping("/left.action")
    public String doLeft()
    {
        return "/WEB-INF/views/function_catalog/left.jsp";
    }

    /**
     * 得到子目录的xml文档
     *
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

        String siteno = getLoginSiteNo();
        List<ManageCatalog> childrenList = manageCatalogService.findChildrenById(parentId, siteno);
        for (Iterator<ManageCatalog> iter = childrenList.iterator(); iter.hasNext(); ) {
            ManageCatalog menuCatalog = (ManageCatalog) iter.next();
            String name = menuCatalog.getName();
            name = StringHelper.replace(name, "\"", "&quot;");
            name = StringHelper.replace(name, "\'", "&quot;");
            String src = "";
            if (menuCatalog.getChildrennum() > 0) {
                src = "showChild.action?parentNo=" + menuCatalog.getId();
            }

            buffer.append("   <tree text=\"" + name + "\" target=\"catalogRightFrame\" action=\"" + request
                    .getContextPath() + "/admin/functionCatalogAdmin/doEdit.action?catalogId=" + menuCatalog.getId()
                    + "\"  src=\"" + src + "\"  value=\"" + menuCatalog.getId() + "\" oncontextmenu=\"true\" />\n");
        }

        buffer.append("</tree>");
        return buffer.toString();
    }

    /**
     * 编辑目录属性
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/edit.action")
    public Result edit(HttpServletRequest request)
    {
        //对提交上来的form进行处理
        form = normalize(request);

        //		if (StringHelper.isEmpty(form.getString("catalogNo")))
        //		{
        //			LoggerUtil.error(this.getClass(), "目录英文名不能为空");
        //			return ADD;
        //		}
        //		if (StringHelper.isEmpty(form.getString("name")))
        //		{
        //			LoggerUtil.error(this.getClass(), "目录名称不能为空");
        //			return ADD;
        //		}

        ManageCatalog catalog = new ManageCatalog();
        BeanHelper.mapToBean(form, catalog);

        //目录名称
        String name = getStrParameter("form.name");
        //目录英文名
        String catalogNo = getStrParameter("form.catalogNo");
        //目录描述
        String description = getStrParameter("form.description");
        String linkUrl = getStrParameter("form.linkUrl");
        try {
            name = URLDecoder.decode(name, "utf-8");
            description = URLDecoder.decode(description, "utf-8");
            linkUrl = URLDecoder.decode(linkUrl, "utf-8");
        } catch (UnsupportedEncodingException e) {
            MESSAGE = "编辑失败";
            addLog("编辑功能目录", MESSAGE);
            result.setErrorNo(-1);
            result.setErrorInfo(MESSAGE);
            return result;
        }
        catalog.setName(name);
        catalog.setCatalogNo(catalogNo);
        catalog.setDescription(description);
        catalog.setLinkUrl(linkUrl);

        catalog.setModifiedBy(getUID());
        catalog.setModifiedDate(DateHelper.formatDate(new Date()));

        catalog.remove("children");
        manageCatalogService.updateCatalog(catalog);

        //更新该目录中的父目录拥有的节点数
        manageCatalogService.updateParentCatalogNum(catalog.getParentId(), manageCatalogService.findParentCatalogNum
                (catalog.getParentId()));

        MESSAGE = "编辑目录[ID=" + catalog.getId() + ",name=" + catalog.getName() + "]";
        addLog("编辑功能目录", MESSAGE);
        result.setErrorInfo("操作成功");
        return result;
    }

    /**
     * 描述:  递归更新子节点数量
     * 作者:
     * 创建日期: 2010-1-26
     * 创建时间: 上午11:40:38
     *
     * @return void
     * @throws
     */
    private void updateChildrenNum(int catalogId)
    {
        //更新该目录中的父目录拥有的节点数
        manageCatalogService.updateParentCatalogNum(catalogId, manageCatalogService.findParentCatalogNum(catalogId));

        List<ManageCatalog> childrenList = manageCatalogService.findChildrenById(catalogId);
        if (childrenList != null && childrenList.size() > 0) {
            for (Iterator<ManageCatalog> iter = childrenList.iterator(); iter.hasNext(); ) {
                ManageCatalog catalog = (ManageCatalog) iter.next();
                if (catalog != null) {
                    updateChildrenNum(catalog.getId());
                }
            }
        }
    }
}
