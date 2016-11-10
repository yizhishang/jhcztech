package com.yizhishang.business.other.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.jdbc.DataRow;
import com.yizhishang.base.util.DateHelper;
import com.yizhishang.base.util.ScriptHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.business.other.service.AdManageService;
import com.yizhishang.plat.domain.Result;
import com.yizhishang.plat.service.WebCatalogService;
import com.yizhishang.plat.web.action.BaseAction;
import com.yizhishang.plat.web.form.DynaForm;

/**
 * 描述:  首页广告信息发布管理类
 * 版权:	 Copyright (c) 2007
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-26
 * 创建时间: 10:37:38
 */
@Controller
@RequestMapping("/admin/adManageAdmin")
public class AdManageAction extends BaseAction
{
    
    @Resource
    AdManageService adManageService;
    
    @Resource
    WebCatalogService WebCatalogService;
    
    @Override
    @ResponseBody
    @RequestMapping("/add.action")
    public Result add(HttpServletRequest request, HttpServletResponse response)
    {
        //对提交上来的form进行处理
        DynaForm form = normalize(request);
        
        DataRow data = new DataRow();
        data.putAll(form);
        data.set("group_no", data.getString("type"));
        data.set("siteno", getSiteNo());
        data.set("create_by", getUID());
        data.set("create_date", DateHelper.formatDate(new Date()));
        adManageService.addAd(data);
        addLog("添加广告", "添加广告成功");
        publishAdCatalog("home");
        publishAdCatalog("products");
        publishAdCatalog("software");
        return super.add(request, response);
    }
    
    /**
     * 描述：删除广告信息
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping("/delete.action")
    public Result delete(HttpServletRequest request, HttpServletResponse response)
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++)
        {
            adManageService.deteleAdInfo(idArray[i], getSiteNo());
            addLog("删除广告信息", "删除广告信息[id=" + idArray[i] + "]");
        }
        publishAdCatalog("home");
        publishAdCatalog("products");
        publishAdCatalog("software");
        return super.delete(request, response);
    }
    
    /**
     * 描述：添加广告发布信息
     * @return
     */
    @Override
    @RequestMapping("/doAdd.action")
    public ModelAndView doAdd()
    {
        mv.addObject("list", adManageService.findAllAdGroup());
        mv.setViewName("/WEB-INF/views/adManage/ad_add.jsp");
        return mv;
    }
    
    /**
     * 缺省的操作(function=""时调用)
     * 列出所有的广告信息
     * @return
     */
    @Override
    @RequestMapping("/default.action")
    public ModelAndView doDefault()
    {
        int curPage = this.getIntParameter("page");
        String keyword = getStrParameter("keyword");
        String type = getStrParameter("type");
        String siteno = getSiteNo();
        curPage = (curPage <= 0) ? 1 : curPage;
        keyword = StringHelper.trim(keyword);
        
        DBPage page = adManageService.getPageData(curPage, 20, siteno, type, keyword);
        dataMap.put("page", page);
        
        //查询广告组
        List<Object> groupList = adManageService.findAllAdGroup();
        dataMap.put("groupList", groupList);
        
        mv.addObject("data", dataMap);
        mv.setViewName("/WEB-INF/views/adManage/ad_list.jsp");
        return mv;
    }
    
    /**
     * 描述：编辑广告发布信息
     * @return
     */
    @Override
    @RequestMapping("/doEdit.action")
    public ModelAndView doEdit(HttpServletResponse response)
    {
        int id = this.getIntParameter("id");
        String siteno = getSiteNo();
        DataRow data = adManageService.findAdById(id, siteno);
        
        if (data == null)
        {
            ScriptHelper.alert(response, "该栏目不存在或已经被删除", "close");
            return null;
        }
        DynaForm form = new DynaForm();
        form.putAll(data);
        mv.addObject("form", form);
        mv.addObject("list", adManageService.findAllAdGroup());
        
        mv.setViewName("/WEB-INF/views/adManage/ad_edit.jsp");
        return mv;
    }
    
    /**
     * 描述：链接状态无效
     * @return
     */
    @ResponseBody
    @RequestMapping("/linkNo.action")
    public void doLinkNo(HttpServletResponse response)
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++)
        {
            adManageService.linkNo(idArray[i], getSiteNo());
            addLog("修改广告链接无效状态", "修改广告链接无效状态[id=" + idArray[i] + "]");
        }
        publishAdCatalog("home");
        publishAdCatalog("products");
        publishAdCatalog("software");
        String successPage = getStrParameter("successPage");
        ScriptHelper.redirect(response, successPage);
    }
    
    /**
     * 描述：链接状态有效
     * @return
     */
    @RequestMapping("/linkYes.action")
    public void doLinkYes(HttpServletResponse response)
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++)
        {
            adManageService.linkYes(idArray[i], getSiteNo());
            addLog("修改广告链接有效状态", "修改广告链接有效状态[id=" + idArray[i] + "]");
        }
        publishAdCatalog("home");
        publishAdCatalog("products");
        publishAdCatalog("software");
        String successPage = getStrParameter("successPage");
        ScriptHelper.redirect(response, successPage);
    }
    
    /**
     * 描述：设置展示状态无效
     * @return
     */
    public void doShowNo(HttpServletResponse response)
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++)
        {
            adManageService.showNo(idArray[i], getSiteNo());
            addLog("修改广告展示无效状态", "修改广告展示无效状态[id=" + idArray[i] + "]");
        }
        publishAdCatalog("home");
        publishAdCatalog("products");
        publishAdCatalog("software");
        String successPage = getStrParameter("successPage");
        ScriptHelper.redirect(response, successPage);
    }
    
    /**
     * 描述：设置展示状态无效
     * @return
     */
    @ResponseBody
    @RequestMapping("/showYes.actoin")
    public void doShowYes(HttpServletResponse response)
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++)
        {
            adManageService.showYes(idArray[i], getSiteNo());
            addLog("修改广告展示有效状态", "修改广告展示有效状态[id=" + idArray[i] + "]");
        }
        publishAdCatalog("home");
        publishAdCatalog("products");
        publishAdCatalog("software");
        String successPage = getStrParameter("successPage");
        ScriptHelper.redirect(response, successPage);
    }
    
    @Override
    @ResponseBody
    @RequestMapping("/edit.action")
    public Result edit(HttpServletRequest request, HttpServletResponse response)
    {
        //对提交上来的form进行处理
        DynaForm form = normalize(request);
        DataRow data = new DataRow();
        data.putAll(form);
        data.set("group_no", data.getString("type"));
        data.set("siteno", getSiteNo());
        data.set("modified_by", getUID());
        data.set("modified_date", DateHelper.formatDate(new Date()));
        adManageService.updateAd(data);
        addLog("修改广告", "修改广告信息成功");
        publishAdCatalog("home");
        publishAdCatalog("products");
        publishAdCatalog("software");
        return super.edit(request, response);
    }
    
    /**
     * @描述：发布广告下栏目
     */
    private void publishAdCatalog(String name)
    {
        DataRow catalog = WebCatalogService.findCatalogByNo(name);
        if (catalog != null)
        {
            addToPublishQueue(catalog.getInt("catalog_id"), "C");
        }
    }
}
