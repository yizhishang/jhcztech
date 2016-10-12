package com.jhcz.plat.service;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.plat.domain.Log;
import com.jhcz.plat.service.exception.LoginFailedException;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-2
 * 创建时间: 15:48:04
 */
public interface LogService
{
	
	/**
	 * 添加一条日志信息
	 *
	 * @param log 日志信息对象
	 */
	public void addLog(Log log);
	
	/**
	 * 根据日志ID，删除一条日志信息，
	 *
	 * @param id
	 */
	public void deleteLog(int id);
	
	/**
	 * 根据条件查询相应的日志信息
	 *
	 * @param curPage    当前第几页
	 * @param numPerPage 每页多少条记录
	 * @param siteNo     站点编号
	 * @param keyword    关键字
	 * @param uid        用户UID
	 * @return
	 */
	public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword, String uid);
	
	/**
	 * 删除全部日志信息
	 */
	public void deleteAllLog();
	
	/**
	 * 
	 * @描述：查询用户当天输入错密码的次数
	 * @作者：袁永君
	 * @时间：2011-1-4 下午04:20:54
	 * @param userId
	 * @return
	 */
	public int getNowErrorPwdNum(String loginId);
	
	/**
	 * 
	 * @描述：清空用户当日密码输错的次数
	 * @作者：袁永君
	 * @时间：2011-1-7 下午02:07:12
	 * @param userId
	 */
	public void clearErrorPwdNum(int userId);
	
	/**
	 * 
	 * @描述：判断密码是否过期
	 * @作者：袁永君
	 * @时间：2011-1-4 下午06:49:23
	 * @param pwdValidity 密码的有效期 
	 * @param userId
	 * @return
	 * @throws LoginFailedException
	 */
	public String isPasswordValidity(int pwdValidity, String loginId) throws LoginFailedException;
}
