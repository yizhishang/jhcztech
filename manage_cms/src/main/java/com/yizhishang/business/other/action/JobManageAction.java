package com.yizhishang.business.other.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.util.UserHelper;
import com.yizhishang.business.other.service.JobManageService;
import com.yizhishang.plat.domain.Result;
import com.yizhishang.plat.web.action.BaseAction;

/**
 * 描述:  诚聘英才
 * 版权:	 Copyright (c) 2009
 * 版本:	 1.0
 * 创建日期: 2016-3-22
 * 创建时间: 下午1:20:34
 */
@Controller
@RequestMapping("/admin/jobManageAction")
public class JobManageAction extends BaseAction
{
	@Resource
	JobManageService jobManageService;
	
	/**
	 * 查询列表
	 * @return
	 */
	@RequestMapping("/default.action")
	public ModelAndView doDefault()
	{
		int curPage = this.getIntParameter("page");
		curPage = (curPage <= 0) ? 1 : curPage;
		String position = getStrParameter("position");
		String siteno = getLoginSiteNo();
		@SuppressWarnings("rawtypes")
		DBPage<Map> page = jobManageService.getPageData(curPage, 20, position, siteno);
		dataMap.put("page", page);
		ModelAndView mv = new ModelAndView();
		mv.addObject("data", dataMap);
		mv.setViewName("/WEB-INF/views/business/other/jobs/list_jobs.jsp");
		return mv;
	}
	
	/**
	 * 添加招聘信息
	 * @return
	 */
	@RequestMapping("/doAdd.action")
	public ModelAndView doAdd()
	{
		return new ModelAndView("/WEB-INF/views/business/other/jobs/add_jobs.jsp");
	}
	
	@Override
	@ResponseBody
	@RequestMapping("/add.action")
	public Result add(HttpServletRequest request, HttpServletResponse response)
	{
		form = normalize(request);
		DynaModel data = new DynaModel();
		data.putAll(form);
		String siteno = getLoginSiteNo();
		String createdby = UserHelper.getUserName();
		data.set("siteno", siteno);
		data.set("createdby", createdby);
		data.set("createdtime", new java.sql.Timestamp(System.currentTimeMillis()));//添加创建时间
		jobManageService.add(data);
		return super.add(request, response);
	}

	/**
	 * 修改招聘信息
	 * @return
	 */
	@RequestMapping("/doEdit.action")
	public ModelAndView doEdit()
	{
		String jobid = getStrParameter("jobid");
		DynaModel data = jobManageService.findJobById(jobid);
		form.putAll(data);
		ModelAndView mv = new ModelAndView();
		mv.addObject("form", form);
		mv.setViewName("/WEB-INF/views/business/other/jobs/edit_jobs.jsp");
		return mv;
	}
	
	@Override
	@ResponseBody
	@RequestMapping("/edit.action")
	public Result edit(HttpServletRequest request, HttpServletResponse response)
	{
		form = normalize(request);
		DynaModel data = new DynaModel();
		data.putAll(form);
		String siteno = getLoginSiteNo();
		String modifiedby = UserHelper.getUserName();
		data.set("siteno", siteno);
		data.set("modifiedby", modifiedby);
		data.set("modifiedtime", new java.sql.Timestamp(System.currentTimeMillis()));//添加修改时间
		jobManageService.update(data);
		return super.edit(request, response);
	}

	/**
	 * 删除招聘信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete.action")
	public Result doDelete(HttpServletRequest request, HttpServletResponse response)
	{
		JobManageService jobManageService = new JobManageService();
		int[] idArray = getIntArrayParameter("id");
		for (int i = 0; i < idArray.length; i++)
		{
			jobManageService.delte(idArray[i]);
		}
		return super.delete(request, response);
	}
}
