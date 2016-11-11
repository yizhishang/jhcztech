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

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.util.BeanHelper;
import com.yizhishang.base.util.DateHelper;
import com.yizhishang.base.util.ScriptHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.business.domain.Ad_Group;
import com.yizhishang.business.other.service.AdGroupService;
import com.yizhishang.plat.domain.Result;
import com.yizhishang.plat.web.action.BaseAction;
import com.yizhishang.plat.web.form.DynaForm;

/**
 * 描述:  广告组Action处理类
 * 版权:	 Copyright (c) 2007
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-28
 * 创建时间: 10:29:37
 */
@Controller
@RequestMapping("/admin/adGroupAdmin")
public class AdGroupAction extends BaseAction
{
    
    @Resource
    AdGroupService adGroupService;
    
    private final DynaForm form = new DynaForm();
    
    /**
     * 描述：默认分页处理方法
     * @return
     */
    @Override
    @RequestMapping("/default.action")
    public ModelAndView doDefault()
    {
        int curPage = this.getIntParameter("page");
        String keyword = getStrParameter("keyword");
        String siteno = getSiteNo();
        curPage = (curPage <= 0) ? 1 : curPage;
        keyword = StringHelper.trim(keyword);
        AdGroupService service = new AdGroupService();
        DBPage page = service.getPageData(curPage, 20, siteno, keyword);
        dataMap.put("page", page);
        mv.addObject("data", dataMap);
        mv.setViewName("/WEB-INF/views/adGroup/ad_group_list.jsp");
        return mv;
    }
    
    /**
     * 描述：添加广告组信息
     * @return
     */
    @Override
    @RequestMapping("/doAdd.action")
    public ModelAndView doAdd()
    {
        mv.setViewName("/WEB-INF/views/adGroup/ad_group_add.jsp");
        return mv;
    }
    
    @Override
    @ResponseBody
    @RequestMapping("/add.action")
    public Result add(HttpServletRequest request, HttpServletResponse response)
    {
        DynaForm form = normalize(request);
        String message = "";
        if (StringHelper.isEmpty(form.getString("name")))
        {
            message = "广告组名称不为空";
            result.setErrorInfo(message);
            result.setErrorNo(-1);
            return result;
        }
        
        Ad_Group ad_group = new Ad_Group();
        BeanHelper.mapToBean(form, ad_group);
        
        ad_group.setSiteNo(getSiteNo());
        ad_group.setCreateBy(getUID());
        ad_group.setCreateDate(DateHelper.formatDate(new Date()));
        ad_group.setModifiedBy(getUID());
        ad_group.setModifiedDate(DateHelper.formatDate(new Date()));
        
        adGroupService.addAdGroup(ad_group);
        addLog("添加广告信息", "添加广告信息[ID=" + ad_group.getId() + ",name=" + ad_group.getName() + "]");
        return super.edit(request, response);
    }
    
    /**
     * 描述：修改广告组信息
     * @return
     */
    @Override
    @RequestMapping("/doEdit.action")
    public ModelAndView doEdit(HttpServletResponse response)
    {
        int id = this.getIntParameter("id");
        String siteno = getSiteNo();
        
        Ad_Group ad_group = adGroupService.findAdGroupById(id, siteno);
        
        if (id == 0 || ad_group == null)
        {
            ScriptHelper.alert(response, "该栏目不存在或已经被删除", "close");
            return null;
        }
        BeanHelper.beanToMap(ad_group, form);
        mv.addObject("form", form);
        mv.setViewName("/WEB-INF/views/adGroup/ad_group_edit.jsp");
        return mv;
    }
    
    @Override
    @ResponseBody
    @RequestMapping("/edit.action")
    public Result edit(HttpServletRequest request, HttpServletResponse response)
    {
        DynaForm form = normalize(request);
        String message = "";
        if (StringHelper.isEmpty(form.getString("name")))
        {
            message = "广告组名称不为空";
            result.setErrorInfo(message);
            result.setErrorNo(-1);
            return result;
        }
        
        Ad_Group ad_group = new Ad_Group();
        BeanHelper.mapToBean(form, ad_group);
        
        ad_group.setSiteNo(getSiteNo());
        ad_group.setModifiedBy(getUID());
        ad_group.setModifiedDate(DateHelper.formatDate(new Date()));
        
        adGroupService.updateAdGroup(ad_group);
        addLog("修改广告信息", "修改广告信息[ID=" + ad_group.getId() + ",name=" + ad_group.getName() + "]");
        return super.edit(request, response);
    }
    
    /**
     * 描述：删除广告组信息
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
            List<DynaModel> list = adGroupService.findAdById(idArray[i], getSiteNo());
            if (list.size() > 0)
            {
                request.setAttribute("error", "请删除该广告组下的广告在进行删除");
                return new Result(-1, "请删除该广告组下的广告在进行删除!");
            }
            adGroupService.deleteAdGroup(idArray[i], getSiteNo());
            addLog("删除广告组信息", "删除广告组信息[id=" + idArray[i] + "]");
        }
        return super.delete(request, response);
    }
}
