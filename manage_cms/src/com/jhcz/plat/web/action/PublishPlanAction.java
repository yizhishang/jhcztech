package com.jhcz.plat.web.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jhcz.base.config.Configuration;
import com.jhcz.base.config.SysConfig;
import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.util.RequestHelper;
import com.jhcz.base.util.ScriptHelper;
import com.jhcz.base.util.StringHelper;
import com.jhcz.plat.domain.Result;
import com.jhcz.plat.service.PublishPlanService;
import com.jhcz.plat.service.WebCatalogService;
import com.jhcz.plat.web.form.DynaForm;

/**
 * 描述: 发布计划管理
 * 版权: Copyright (c) 2016
 * 公司: 思迪科技 yuanyj@jhcz.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-2-23
 * 创建时间: 下午2:25:44
 */
@Controller
@RequestMapping("/admin/PublishPlanAdmin")
public class PublishPlanAction extends BaseAction
{
    
    @Resource
    PublishPlanService publishPlanService;
    
    @Resource
    WebCatalogService webCatalogService;
    
    @ResponseBody
    @RequestMapping("/add.action")
    public Result add(HttpServletRequest request)
    {
        //对提交上来的form进行处理
        DynaForm form = normalize(request);
        DataRow data = new DataRow();
        data.putAll(form);
        
        String machineId = Configuration.getString("system.machineId");
        
        String[] roelRightTree = getStrArrayParameter("id_fun");
        for (int i = 0; i < roelRightTree.length; i++)
        {
            data.set("catalog_id", roelRightTree[i]);
            data.set("machine_id", machineId);
            publishPlanService.addPublishPlan(data);
        }
        addLog(getUID(), getSiteNo(), "添加发布计划", "添加发布计划成功！");
        result.setErrorInfo("添加发布计划成功！");
        result.setErrorNo(0);
        return result;
    }
    
    /**
    * 描述:  比较权限
    * 作者:	 
    * 创建日期: 2010-1-3
    * 创建时间: 下午11:29:01
    * @return String
    * @throws
    */
    public String compareRole(int catalogId, Map<String, String> catalogPlanMap)
    {
        String result = "";
        if (catalogPlanMap.containsKey(String.valueOf(catalogId)))
        {
            result = "disabled=\"disabled\"";
            
        }
        return result;
    }
    
    /**
     * 描述:  添加发布计划
     * 作者:	 
     * 创建日期: 2010-3-10
     * 创建时间: 上午11:12:07
     * @return String
     * @throws
     */
    @Override
    @RequestMapping("/doAdd.action")
    public ModelAndView doAdd()
    {
        return new ModelAndView("/WEB-INF/views/publish_plan/add_publish_plan.jsp");
    }
    
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(value = "/ajaxShowCatalogTree.action", produces = "text/html;charset=UTF-8")
    public String doAjaxShowCatalogTree(HttpServletRequest request, HttpServletResponse response)
    {
        String type = RequestHelper.getString(request, "type", "0");
        
        //查询当前类别下，哪个栏目生成了计划
        List<Object> planList = publishPlanService.findPublishPlanByType(type);
        
        //将已经生成计划的栏目放入map中，一个栏目只能生成一种计划
        Map<String, String> catalogPlanMap = new HashMap<String, String>();
        for (Iterator<Object> iter = planList.iterator(); iter.hasNext();)
        {
            DataRow data = (DataRow) iter.next();
            catalogPlanMap.put(data.getString("catalog_id"), data.getString("catalog_id"));
        }
        
        //取所有子栏目信息
        DataRow wholeCatalogs = webCatalogService.findWholeCatalogById("1");
        StringBuffer html = new StringBuffer();
        if (wholeCatalogs != null)
        {
            List<Object> children = (List<Object>) wholeCatalogs.getObject("children");
            html.append("<ul>");
            html.append("<li>");
            DataRow data = new DataRow();
            data.set("catalog_id", "1");
            data.set("route", "1");
            data.set("name", "根目录");
            
            html.append(getRoleFunction(data, catalogPlanMap));
            html.append(getRoleTreeHtml(children, catalogPlanMap));
            html.append("</li>");
            html.append("</ul>");
        }
        return html.toString();
        //        ResponseHelper.print(response, html.toString());
        //		return NONE;
    }
    
    /**
    * 缺省的操作(function=""时调用)
    * 转向栏目的首页页面
    * @return
    */
    @Override
    @RequestMapping("/doDefault.action")
    public ModelAndView doDefault()
    {
        String catalogId = getStrParameter("catalogId");
        String name = getStrParameter("name");
        
        //		String startDate = getStrParameter("startDate");
        //		String endDate = getStrParameter("endDate");
        
        int curPage = getIntParameter("page");
        curPage = (curPage <= 0) ? 1 : curPage;
        
        DBPage page = publishPlanService.findPublishPlan(curPage, SysConfig.getRowOfPage(), catalogId, name);
        dataMap.put("page", page);
        ModelAndView mv = new ModelAndView("/WEB-INF/views/publish_plan/list_publish_plan.jsp");
        mv.addObject("data", dataMap);
        return mv;
    }
    
    @ResponseBody
    @RequestMapping("/delete.action")
    public Result doDelete(HttpServletResponse response)
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++)
        {
            publishPlanService.delPublishPlan(idArray[i]);
            addLog("删除发布计划", "删除发布计划[id=" + idArray[i] + "]");
        }
        
        result.setErrorNo(0);
        result.setErrorInfo("删除发布计划成功！");
        return result;
    }
    
    @ResponseBody
    @RequestMapping("/deleteAll.action")
    public Result doDeleteAll()
    {
        publishPlanService.delAllPublishPlan();
        addLog("删除所有发布计划", "删除所有发布计划");
        result.setErrorNo(0);
        result.setErrorInfo("删除所有发布计划成功！");
        return result;
    }
    
    /**
    * 描述:  
    * 创建日期: 2010-3-11
    * 创建时间: 下午06:08:02
    * @return String
    * @throws
    */
    @RequestMapping("/doEdit.action")
    public ModelAndView doEdit(HttpServletRequest request)
    {
        String id = RequestHelper.getString(request, "id");
        DataRow data = publishPlanService.findPublishPlanById(id);
        DynaForm form = new DynaForm();
        form.putAll(data);
        
        String time = data.getString("time");
        if (time.indexOf(":") != -1)
        {
            String[] temp = StringHelper.split(time, ":");
            if (temp.length > 1)
            {
                form.put("fixedHour", temp[0]);
                form.put("fixedMinute", temp[1]);
            }
        }
        ModelAndView mv = new ModelAndView("/WEB-INF/views/publish_plan/edit_publish_plan.jsp");
        mv.addObject("form", form);
        return mv;
    }
    
    @Override
    @ResponseBody
    @RequestMapping("/edit.action")
    public Result edit(HttpServletRequest request, HttpServletResponse response)
    {
        DynaForm form = normalize(request);
        DataRow data = new DataRow();
        data.putAll(form);
        publishPlanService.editPublishPlan(data);
        
        ScriptHelper.alert(response, "编辑发布计划成功！");
        ScriptHelper.refreshParentWin(response, true);
        
        //        addLog("编辑发布计划", "编辑发布计划");
        //        result.setErrorNo(0);
        //        result.setErrorInfo("编辑发布计划成功！");
        //        return result;
        return null;
    }
    
    public String getRoleFunction(DataRow data, Map<String, String> catalogPlanMap)
    {
        StringBuffer buffer = new StringBuffer();
        int catalogId = data.getInt("catalog_id");
        buffer.append("<input type=\"checkbox\"  name=\"id_fun\" id=\"node_" + data.getString("route").replaceAll("\\|", "_") + "\" value=\"" + catalogId
                + "\"  " + compareRole(catalogId, catalogPlanMap) + "/><label>" + data.getString("name") + "</label>");
        return buffer.toString();
    }
    
    /**
    * 描述：获取树状html代码
    * 作者：袁永君
    * 时间：2010-1-12 上午11:31:53
    * @param list
    * @param strRights
    * @return
    */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private String getRoleTreeHtml(List<Object> list, Map<String, String> map)
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
                buffer.append(getRoleTreeHtml((List) data.getObject("children"), map));
                buffer.append("</li>");
            }
            buffer.append("</ul>");
        }
        return buffer.toString();
    }
}
