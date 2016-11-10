package com.yizhishang.plat.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhishang.base.dao.BaseDao;
import com.yizhishang.base.jdbc.DataRow;
import com.yizhishang.plat.domain.UserPasswordLog;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-12
 * 创建时间: 15:42:54
 */
@Repository
public class UserPasswordLogDao extends BaseDao
{
	
	public void addLog(UserPasswordLog log)
	{
		DataRow dataRow = new DataRow();
		dataRow.putAll(log.toMap());
		getJdbcTemplate().insert("T_USER_PASSWORD_LOG", dataRow);
	}
	
	public void deleteLog(int id)
	{
		getJdbcTemplate().delete("T_USER_PASSWORD_LOG", "LOG_ID", new Integer(id));
	}
	
    /**
	 * 
	 * @描述：查询密码最后更新时间 
	 * @作者：袁永君
	 * @时间：2015-12-12 下午06:30:10
	 * @param loginId 用户名
	 * @return String 最后一次创建时间
	 */
	public String getPwdValidity(String loginId)
	{
		String sql = "SELECT CREATE_DATE FROM T_USER_PASSWORD_LOG WHERE CREATE_BY = ? ORDER BY CREATE_DATE DESC";
		return getJdbcTemplate().queryString(sql, new Object[] { loginId });
	}
	
	public List<Object> queryLogByUserId(String uid)
	{
		return getJdbcTemplate().query("SELECT LOG_ID FROM T_USER_PASSWORD_LOG WHERE CREATE_BY = ? ", new Object[]{uid});
	}
}
