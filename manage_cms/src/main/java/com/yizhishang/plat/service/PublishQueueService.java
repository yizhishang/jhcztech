package com.yizhishang.plat.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.jdbc.DataRow;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.DateHelper;
import com.yizhishang.base.util.StringHelper;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2010-1-18
 * 创建时间: 10:16:07
 */
@Service
public class PublishQueueService extends BaseService
{
	
	/**
	 * 添加一条发布队列信息
	 * @param data
	 */
	public void add(DataRow data)
	{
		data.set("id", getSeqValue("T_PUBLISH_QUEUE"));
		getJdbcTemplate().insert("T_PUBLISH_QUEUE", data);
	}
	
	/**
	 * 删除队列信息
	 * @param queueId
	 */
	public void delete(int queueId)
	{
        ArrayList<Integer> argList = new ArrayList<Integer>();
		argList.add(new Integer(queueId));
		getJdbcTemplate().update("delete from T_PUBLISH_QUEUE where id=?", argList.toArray());
	}
	
	/**
	 * 删除所有队列信息
	 */
	public void deleteAllLog()
	{
		getJdbcTemplate().update("delete from T_PUBLISH_QUEUE WHERE STATE != 1");
	}
	
	/**
	 * 根据队列ID，寻找命令字串
	 * @param queueId
	 * @return
	 */
	public String findCmdStrByQueueId(int queueId)
	{
        ArrayList<Integer> argList = new ArrayList<Integer>();
		argList.add(new Integer(queueId));
		return getJdbcTemplate().queryString("select cmd_str from T_PUBLISH_QUEUE where id=?", argList.toArray());
	}
	
	/**
	 * 
	 * 查询发布队列  
	 * @return DBPage
	 */
	public DBPage findPublishQueue(int curPage, int numPerPage, String startDate, String endDate, String siteNo)
	{
        List<String> argList = new ArrayList<String>();
		StringBuffer strBuf = new StringBuffer("SELECT * FROM T_PUBLISH_QUEUE WHERE 1 = 1");
		if (StringHelper.isNotEmpty(startDate))
		{
			strBuf.append(" AND CREATE_DATE >= ?");
			argList.add(startDate);
		}
		
		if (StringHelper.isNotEmpty(endDate))
		{
			strBuf.append(" AND CREATE_DATE <= ?");
			argList.add(endDate);
		}
		if (StringHelper.isNotEmpty(siteNo))
		{
			strBuf.append(" AND SITENO = ?");
			argList.add(siteNo);
		}
		strBuf.append(" ORDER BY ID DESC");
		return getJdbcTemplate().queryPage(strBuf.toString(), argList.toArray(), curPage, numPerPage);
	}
	
	public void resetExceptionQueue()
	{
        List<String> argList = new ArrayList<String>();
		String sql = "SELECT ID,CREATE_DATE FROM T_PUBLISH_QUEUE WHERE STATE = 1 ORDER BY ID";
        List<Object> queueList = getJdbcTemplate().query(sql);
        for (Iterator<Object> iter = queueList.iterator(); iter.hasNext();)
		{
			DataRow dataRow = (DataRow) iter.next();
			String id = dataRow.getString("id");
			Date publishDate = DateHelper.parseString(dataRow.getString("create_date"));
			if (publishDate != null)
			{
				long differTime = DateHelper.getDateMiliDispersion(new Date(), publishDate);
				if ((differTime / 1000 / 60) > 10)//判断队列处理时间是否大于10分钟
				{
					argList.add(id);
				}
			}
			else
			{
				//将附件的创建时间更改为正确格式
				getJdbcTemplate().update("UPDATE T_PUBLISH_QUEUE SET CREATE_DATE = ? WHERE ID = ?", new Object[] { DateHelper.formatDate(new Date()), id });
				argList.add(id);
			}
		}
		
		if (argList != null && argList.size() > 0)
		{
			StringBuffer buffer = new StringBuffer("UPDATE T_PUBLISH_QUEUE SET STATE = 0 WHERE ID IN (");
			for (int i = 0; i < argList.size(); i++)
			{
				buffer.append("?");
				if (i < argList.size() - 1)
				{
					buffer.append(",");
				}
			}
			buffer.append(")");
			getJdbcTemplate().update(buffer.toString(), argList.toArray());
		}
		
	}
	
	/**
	 * 更新队列中某条记录的状态
	 *
	 * @param queueId
	 * @param state
	 */
	public void updateState(int queueId, int state)
	{
        ArrayList<Integer> argList = new ArrayList<Integer>();
		argList.add(new Integer(state));
		argList.add(new Integer(queueId));
		getJdbcTemplate().update("update T_PUBLISH_QUEUE set state=? where id=?", argList.toArray());
	}
}
