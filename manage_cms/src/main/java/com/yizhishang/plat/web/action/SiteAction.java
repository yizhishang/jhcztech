package com.yizhishang.plat.web.action;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yizhishang.base.config.SysConfig;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.util.BeanHelper;
import com.yizhishang.base.util.DateHelper;
import com.yizhishang.base.util.ScriptHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.domain.Catalog;
import com.yizhishang.plat.domain.Result;
import com.yizhishang.plat.domain.Site;
import com.yizhishang.plat.service.CatalogService;
import com.yizhishang.plat.service.SiteService;
import com.yizhishang.plat.web.form.DynaForm;

/**
 * 描述:  站点管理类
 * 版权:	 Copyright (c) 2007
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-14
 * 创建时间: 10:37:17
 */
@Controller
@RequestMapping("/admin/siteAdmin")
public class SiteAction extends BaseAction
{
	
	private DynaForm form = new DynaForm();
	
    private Logger logger = LoggerFactory.getLogger(SiteAction.class);

    @Resource
    CatalogService catalogService;
    
    @Resource
    SiteService siteService;

    @ResponseBody
    @RequestMapping("/add.action")
    public Result add(HttpServletRequest request)
	{
    	Result result = new Result();
        DynaForm form = normalize(request);
        MESSAGE = "";
        if (StringHelper.isEmpty(form.getString("siteNo")))
        {
            MESSAGE = "站点编号不能为空！";
        }
        if (StringHelper.isEmpty(form.getString("name")))
        {
            MESSAGE = "站点名称不能为空！";
        }
        

        if (form.getString("isMain").equals("1"))
        {
            if (siteService.findSiteIsMain(form.getString("isMain")))
            {
                MESSAGE = "主站点已存在，请添加子站点";
            }
        }
        if (StringHelper.isNotEmpty(MESSAGE))
        {
            logger.error(MESSAGE);
            result.setErrorNo(-1);
            result.setErrorInfo(MESSAGE);
            return result;
        }
        
        /***************************** 添加站点信息开始 ******************************/
        Site site = new Site();
        BeanHelper.mapToBean(form, site);
        //将信息添加到站点发布表中
        site.setSiteNo(form.getString("siteNo"));
        site.setName(form.getString("name"));
        site.setState(Site.STATE_OPEN);
        site.setIsMain(form.getInt("isMain"));
        site.setPublishPath(form.getString("publishPath"));
        site.setDescription(form.getString("description"));
        site.setCreateBy(getUID());
        site.setCreateDate(DateHelper.formatDate(new Date()));
        site.setModifiedBy(getUID());
        site.setModifiedDate(DateHelper.formatDate(new Date()));
        siteService.addSite(site);
        //同时还要增加到管理栏目表中
        //          ManageCatalogService manageService = (ManageCatalogService) getService(ManageCatalogService.class);
        //          ManageCatalog manageCatalog = new ManageCatalog();
        //          
        //          manageCatalog.setParentId(0);
        //          manageCatalog.setName(form.getString("name"));
        //          manageCatalog.setCatalogNo("root");
        //          manageCatalog.setSiteNo(form.getString("siteNo"));
        //          manageCatalog.setDescription(form.getString("description"));
        //          manageCatalog.setLayer(0);
        //          manageCatalog.setState(1);
        //          //manageCatalog.setNeedPublish(1);
        //          // manageCatalog.setNeedApprove(1);
        //          manageCatalog.setIsRoot(0);
        //          manageCatalog.setIsSystem(0);
        //          // manageCatalog.setType(0);
        //          manageCatalog.setCreateBy(getUID());
        //          manageCatalog.setCreateDate(DateHelper.formatDate(new Date()));
        //          manageCatalog.setModifiedBy(getUID());
        //          manageCatalog.setModifiedDate(DateHelper.formatDate(new Date()));
        //          manageService.addCatalog(manageCatalog);
        /***************************** 添加站点信息结束 ******************************/
        /***************************** 修改栏目信息开始 ******************************/
                                                                                                                                                                                                        /**
        * 修改目的：如果增加了新站点，而栏目信息没有添加新站点编号的话。当用户登陆到新站点的时候，就会出现站点加载
        * 失败的情况。所以当添加新站点时候就得要将改站点信息一起添加到栏目信息里面
        */
        //查找栏目信息
        Catalog catalog = catalogService.findCatalogById(1);
        //获得栏目编号
        String siteno = catalog.getSiteNo();
        //将新站点编号添加到栏目中
        siteno += "|" + form.getString("siteNo");
        //添加跟栏目编号0
        catalog.setId(1);
        //添加栏目编号
        catalog.setSiteNo(siteno);
        //跟新栏目信息
        catalogService.updateCatalog(catalog);
        /***************************** 修改栏目信息结束 ******************************/
        addLog("添加站点", "添加站点[siteno=" + site.getSiteNo() + "]");
        result.setErrorInfo("添加站点成功");
        return result;
	}
	
    /**
    * 描述：添加站点
    * @return
    */
    @Override
    @RequestMapping("/doAdd.action")
    public ModelAndView doAdd()
    {
        return new ModelAndView("/WEB-INF/views/site/add_site.jsp");
    }

	                                                                                                                                                                                                                                                                                                                                                                                    /**
    * 描述：关闭站点
    * @return
    */
	public String doClose()
	{
		int[] idArray = getIntArrayParameter("id");
		for (int i = 0; i < idArray.length; i++)
		{
            siteService.closeSite(idArray[i]);
            addLog("开放用户", "开放用户[id=" + idArray[i] + "]");
		}
		return MAIN;
	}
	
	                                                                                                                                                                                                                                                                                                                                                                                            /**
    * 缺省的操作(function=""时调用)
    * 列出所有的用户信息
    * 描述：本方法主要是对所有站点进行分页显示处理
    * @return
    */
    @Override
    @RequestMapping("/doDefault.action")
    public ModelAndView doDefault()
	{
		int curPage = this.getIntParameter("page");
		String keyword = getStrParameter("keyword");
		curPage = (curPage <= 0) ? 1 : curPage;
		keyword = StringHelper.trim(keyword);
        DBPage page = siteService.getPageData(curPage, SysConfig.getRowOfPage(), keyword);
		dataMap.put("page", page);
        ModelAndView mv = new ModelAndView("/WEB-INF/views/site/list_site.jsp");
        mv.addObject("data", dataMap);
        return mv;
	}
	
	                                                                                                                                                                                                                                                                                                                                                                                        /**
    * 描述：删除站点
    * @return
    */
    @ResponseBody
    @RequestMapping("/delete.action")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public Result delete(HttpServletRequest request, HttpServletResponse response)
	{
    	try
		{
    		int[] idArray = getIntArrayParameter("id");
    		for (int i = 0; i < idArray.length; i++)
    		{
                siteService.deleteSite(idArray[i]);
                addLog("删除站点", "删除站点[id=" + idArray[i] + "]");
    		}
    		
            List<Site> sites = siteService.getAllSite();
    		String siteno = "";
            for (Iterator<Site> iter = sites.iterator(); iter.hasNext();)
    		{
                Site site = (Site)iter.next();
    			siteno += site.getSiteNo();
    			if (iter.hasNext())
    			{
    				siteno += "|";
    			}
    		}
            //查找栏目信息
    		Catalog catalog = catalogService.findCatalogById(1);
            //添加跟栏目编号0
    		catalog.setId(1);
            //添加栏目编号
    		catalog.setSiteNo(siteno);
            //跟新栏目信息
    		catalogService.updateCatalog(catalog);
    		MESSAGE = "删除站点成功";
    		result.setErrorNo(0);
		}
		catch (Exception e)
		{
			MESSAGE = "删除站点失败";
			result.setErrorNo(-1);
			logger.error(e.getMessage());
		}
    	result.setErrorInfo(MESSAGE);
        return result;
	}
	
	                                                                                                                                                                                                                                                                                                                                                                                        /**
    * 描述：编辑站点
    * @return
    */
    @RequestMapping("/doEdit.action")
    public ModelAndView doEdit(HttpServletResponse reponse)
	{
        int id = getIntParameter("id");
        Site site = siteService.findSiteById(id);
        if (site != null)
        {
            BeanHelper.beanToMap(site, form);
        }
		else
		{
            ScriptHelper.alert(reponse, "子站不存在或已经被删除", "close");
            return null;
		}
        ModelAndView mv = new ModelAndView("/WEB-INF/views/site/edit_site.jsp");
        mv.addObject("form", form);
        return mv;
	}
    
    @ResponseBody
    @RequestMapping("/edit.action")
    public Result edit(HttpServletRequest request, HttpServletResponse reponse)
	{
    	form = normalize(request);
        Site site = new Site();
        BeanHelper.mapToBean(form, site);
        
        siteService.updateSite(site);
        addLog("编辑站点信息", "编辑站点信息[name=" + site.getName() + "]成功");
        
        return super.edit(request, reponse);
	}
    
    /**
    * 开放站点
    * @return
    */
    public String doOpen()
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++)
        {
            siteService.openSite(idArray[i]);
            addLog("开放用户", "开放用户[id=" + idArray[i] + "]");
        }
        return MAIN;
    }
}
