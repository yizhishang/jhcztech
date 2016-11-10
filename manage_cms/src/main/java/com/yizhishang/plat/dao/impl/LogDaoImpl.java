package com.yizhishang.plat.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.jdbc.DataRow;
import com.yizhishang.base.util.DateHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.dao.BaseDao;
import com.yizhishang.plat.dao.LogDao;
import com.yizhishang.plat.domain.Log;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-2
 * 创建时间: 15:42:54
 */
@Repository
public class LogDaoImpl extends BaseDao implements LogDao
{
	
	@Override
    public void addLog(Log log)
	{
		DataRow dataRow = new DataRow();
		dataRow.putAll(log.toMap());
		getJdbcTemplate().insert("T_LOG", dataRow);
	}
	
	/**
	 * 
	 * @描述：清空用户当日密码输错的次数
	 * @作者：袁永君
	 * @时间：2011-1-7 下午02:04:02
	 * @param userId
	 */
	@Override
    public void clearErrorPwdNum(int userId)
	{
		String sql = "DELETE FROM T_LOG WHERE CREATE_BY = (SELECT UID2 FROM T_USER WHERE ID = ?) AND CREATE_DATE > ? AND DESCRIPTION LIKE '%密码错误%'";
		getJdbcTemplate().update(sql, new Object[] { new Integer(userId), DateHelper.formatDate(new Date(), DateHelper.pattern_date) });
	}
	
	@Override
    public void deleteAllLog()
	{
		getJdbcTemplate().update("delete from T_LOG");
	}
	
	@Override
    public void deleteLog(int id)
	{
		getJdbcTemplate().delete("T_LOG", "ID", new Integer(id));
	}
	
	    /**
     * 
     * @描述：查询用户当天输入错密码的次数
     * @作者：袁永君
     * @时间：2011-1-4 下午04:18:45
     * @param userId
     * @return
     */
	@Override
    public int getNowErrorPwdNum(String loginId)
	{
        String sql = "SELECT COUNT(ID) FROM T_LOG WHERE CREATE_BY = ? AND CREATE_DATE > ? AND DESCRIPTION LIKE '%密码错误%'";
		return getJdbcTemplate().queryInt(sql, new Object[] { loginId, DateHelper.formatDate(new Date(), DateHelper.pattern_date) });
		
	}
	
	@Override
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword, String uid)
	{
		DBPage page = null;
		
		StringBuffer sqlBuffer = new StringBuffer();
		ArrayList<Object> argList = new ArrayList<Object>();
		sqlBuffer.append("select * from T_LOG where 1=1 ");
		if (!StringHelper.isEmpty(siteNo))
		{
			sqlBuffer.append(" and siteno = ? ");
			argList.add(siteNo);
		}
		if (!StringHelper.isEmpty(keyword))
		{
			sqlBuffer.append(" and operate like ? ");
			argList.add("%" + keyword + "%");
		}
		if (!StringHelper.isEmpty(uid))
		{
			sqlBuffer.append(" and create_by like ? ");
			argList.add("%" + uid + "%");
		}
		sqlBuffer.append(" order by id desc ");
		page = getJdbcTemplate().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);
		
		if (page != null)
		{
			List<Object> dataList = page.getData();
			ArrayList<Object> newDataList = new ArrayList<Object>();
			for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
			{
				Log log = new Log();
				DataRow row = (DataRow) iter.next();
				log.fromMap(row);
				newDataList.add(log);
			}
			page.setData(newDataList);
		}
		
		return page;
	}
	
	    /**
     * 
     * @描述：查询密码最后更新时间 
     * @作者：袁永君
     * @时间：2011-1-4 下午06:30:10
     * @return
     */
	@Override
    public String getPwdValidity(String loginId)
	{
        //String sql = "SELECT TRUNC(SYSDATE - TO_DATE(CREATE_DATE,'yyyy-MM-dd HH24:mi:ss')) FROM T_LOG WHERE CREATE_BY = ? AND DESCRIPTION LIKE '修改密码成功%' ORDER BY CREATE_DATE DESC";
        String sql = "SELECT CREATE_DATE FROM T_LOG WHERE CREATE_BY = ? AND DESCRIPTION LIKE '修改密码成功%' ORDER BY CREATE_DATE DESC";
		return getJdbcTemplate().queryString(sql, new Object[] { loginId });
	}
}
