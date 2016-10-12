package com.jhcz.business.other.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.business.other.service.JobManageService;
import com.jhcz.plat.domain.Result;
import com.jhcz.plat.web.action.BaseAction;

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
		DBPage page = jobManageService.getPageData(curPage, 20, position, siteno);
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
		DataRow data = new DataRow();
		data.putAll(form);
		String siteno = getLoginSiteNo();
		String createdby = getUserName();
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
		DataRow data = jobManageService.findJobById(jobid);
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
		normalize(form);
		DataRow data = new DataRow();
		data.putAll(form);
		String siteno = getLoginSiteNo();
		String modifiedby = getUserName();
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
	@RequestMapping("/delete.actoin")
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
