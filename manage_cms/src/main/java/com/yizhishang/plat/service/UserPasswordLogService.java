package com.yizhishang.plat.service;

import com.yizhishang.plat.domain.UserPasswordLog;
import com.yizhishang.plat.service.exception.LoginFailedException;

public interface UserPasswordLogService
{
	
	/**
	 * 添加一条用户密码日志信息
	 *
	 * @param log 日志信息对象
	 */
	public void addLog(UserPasswordLog log);
	
	/**
	 * 根据日志ID，删除一条日志信息，
	 *
	 * @param id
	 */
	public void deleteLog(int id);
	/**
	 * 根据用户ID，删除该用户所有的密码修改记录
	 *
	 * @param uid
	 */
	public void deleteLogByUserId(String uid);
	/**
	 * 
	 * @描述：
	 * @作者：袁永君
	 * @时间：2011-1-4 下午06:49:08
	 * @param pwdValidity 密码有效期 
	 * @param userId 用户名
	 * @return String 返回密码到期提示语
	 * @throws LoginFailedException
	 */
	public String isPasswordValidity(int pwdValidity, String loginId) throws LoginFailedException;
	
}