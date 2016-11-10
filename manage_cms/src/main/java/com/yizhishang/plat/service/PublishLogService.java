package com.yizhishang.plat.service;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.jdbc.DataRow;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2010-1-5
 * 创建时间: 13:37:49
 */
public interface PublishLogService
{
	
	
	public DBPage findPublishLog(int curPage, int numPerPage, String startDate, String endDate);
	
	
	
	/**
	 * 添加一条日志信息
	 *
	 * @param log
	 */
	public void add(DataRow log);
	
	
	
	/**
	 * 添加一条日志信息
	 *
	 * @param queueId
	 * @param description
	 */
	public void add(int queueId, String description);
	
	
	
	/**
	 * 
	 * 描述:  删除日志信息
	 * 作者:	 
	 * 创建日期: 2010-1-19
	 * 创建时间: 上午09:46:38
	 * @return void
	 * @throws
	 */
	public void delete(int id);
	
	
	public void deleteAllLog();
}
