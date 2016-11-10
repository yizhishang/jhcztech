/*
 * Copyright (c) 2010 Your Corporation. All Rights Reserved.
 */

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
 * 创建时间: 16:30:13
 */
public interface PublishQueueService
{
	
	/**
	 * 
	 * 查询发布队列  
	 * 
	 * @return DBPage
	 */
	public DBPage findPublishQueue(int curPage, int numPerPage, String startDate, String endDate, String siteNo);
	
	/**
	 * 添加一条发布队列信息
	 *
	 * @param data
	 */
	public void add(DataRow data);
	
	/**
	 * 更新队列中某条记录的状态
	 *
	 * @param queueId
	 * @param state
	 */
	public void updateState(int queueId, int state);
	
	/**
	 * 根据队列ID，寻找命令字串
	 *
	 * @param queueId
	 * @return
	 */
	public String findCmdStrByQueueId(int queueId);
	
	/**
	 * 删除队列信息
	 *
	 * @param queueId
	 */
	public void delete(int queueId);
	
	/**
	 * 
	 * 描述:  删除所有队列
	 * 作者:	 
	 * 创建日期: 2010-1-19
	 * 创建时间: 上午09:58:07
	 * @return void
	 * @throws
	 */
	public void deleteAllLog();
	
	/**
	 * 
	 * 描述：重置异常队列
	 * 作者：袁永君
	 * 时间：Nov 7, 2013 12:34:44 PM
	 */
	public void resetExceptionQueue();
}
