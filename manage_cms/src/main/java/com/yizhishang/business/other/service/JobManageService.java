package com.yizhishang.business.other.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.StringHelper;

/**
 * 描述:  人才招聘
 * 版权:	 Copyright (c) 2009
 * 版本:	 1.0
 * 创建日期: 2016-3-22
 * 创建时间: 下午1:20:34
 */
@Service
public class JobManageService extends BaseService
{
	
	/**
	 * 分页查询人才招聘信息
	 * 创建日期: 2016-3-19
	 * 创建时间: 下午16:21:21
	 * @param curPage
	 * @param rowOfPage
	 * @param clientname
	 * @param status
	 * @param siteNo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public DBPage<Map> getPageData(int curPage, int rowOfPage, String position, String siteNo)
	{
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("SELECT * FROM T_B_JOB WHERE 1=1");
		ArrayList<String> argList = new ArrayList<String>();
		if (!StringHelper.isEmpty(position)) //若为空则查全部
		{
			sqlBuf.append(" AND POSITION LIKE ? ");
			argList.add("%" + position + "%");
		}
		
		if (StringHelper.isNotEmpty(siteNo))
		{
			sqlBuf.append(" AND SITENO = ?");
			argList.add(siteNo);
		}
		sqlBuf.append(" ORDER BY JOBID DESC ");
		return getJdbcTemplateUtil().queryPage(sqlBuf.toString(), Map.class, argList.toArray(), curPage, rowOfPage);
	}
	
	/**
	 * 描述:  添加招聘信息
	 * 创建日期: 2016-3-22
	 * 创建时间: 下午1:23:21
	 * @param data
	 */
	public void add(DynaModel data)
	{
		String id = getSeqValue("T_B_JOB");
		data.set("jobid", id);
		getJdbcTemplateUtil().insert("T_B_JOB", data);
	}
	
	/**
	 * 描述:  修改招聘信息
	 * 创建日期: 2016-3-22
	 * 创建时间: 下午1:23:21
	 * @param data
	 */
	public void update(DynaModel data)
	{
		getJdbcTemplateUtil().update("T_B_JOB", data, "jobid", data.getString("jobid"));
	}
	
	/**
	 * 删除招聘信息
	 * @param issueId
	 */
	public void delte(int jobid)
	{
		getJdbcTemplateUtil().delete("T_B_JOB", "jobid", new Integer(jobid));
	}
	
	/**
	 * 按照ID查询信息
	 * @param jobid
	 * @return
	 */
	public DynaModel findJobById(String jobid)
	{
		String sql = "SELECT * FROM T_B_JOB WHERE JOBID = ?";
		try
		{
			return getJdbcTemplateUtil().queryMap(sql, new Object[] { jobid });
		}
		catch (SQLException e)
		{
			logger.error(e.getMessage());
			return null;
		}
	}
}
