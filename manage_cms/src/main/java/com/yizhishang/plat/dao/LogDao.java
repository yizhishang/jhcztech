package com.yizhishang.plat.dao;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.plat.domain.Log;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-2
 * 创建时间: 15:40:37
 */
public interface LogDao
{
	
	public void addLog(Log log);
	
	public void deleteLog(int id);
	
	public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword, String uid);
	
	public void deleteAllLog();
	
	public int getNowErrorPwdNum(String loginId);
	
	public void clearErrorPwdNum(int userId);
	
	public String getPwdValidity(String loginId);
	
}
