package com.yizhishang.plat.service;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.DateHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.dao.LogDao;
import com.yizhishang.plat.domain.Log;
import com.yizhishang.plat.service.exception.LoginFailedException;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-2
 * 创建时间: 15:54:03
 */
@Service
public class LogService extends BaseService
{
	
	private static Logger logger = LoggerFactory.getLogger(LogService.class);
	
	@Resource
	private LogDao logDao;
	
	/**
	 * 添加一条日志信息
	 *
	 * @param log 日志信息对象
	 */
	public void addLog(Log log)
	{
		String id = getSequenceGenerator().getNextSequence("T_LOG");
		log.setId(Integer.parseInt(id));
		logDao.addLog(log);
	}
	
	/**
	 * 根据日志ID，删除一条日志信息，
	 *
	 * @param id
	 */
	public void deleteLog(int id)
	{
		logDao.deleteLog(id);
	}
	
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
	public DBPage<Log> getPageData(int curPage, int numPerPage, String siteNo, String keyword, String uid)
	{
		return logDao.getPageData(curPage, numPerPage, siteNo, keyword, uid);
	}
	
	/**
	 * 删除全部日志信息
	 */
	public void deleteAllLog()
	{
		logDao.deleteAllLog();
	}
	
	/**
	 * 
	 * @描述：查询用户当天输入错密码的次数
	 * @作者：袁永君
	 * @时间：2011-1-4 下午04:21:47
	 * @param userId
	 * @return
	 */
	public int getNowErrorPwdNum(String loginId)
	{
		return logDao.getNowErrorPwdNum(loginId);
	}
	
	/**
	 * 
	 * @描述：
	 * @作者：袁永君
	 * @时间：2011-1-7 下午02:07:53
	 * @param userId
	 */
	public void clearErrorPwdNum(int userId)
	{
		logDao.clearErrorPwdNum(userId);
	}
	
	/**
	 * 
	 * @描述：
	 * @作者：袁永君
	 * @时间：2011-1-4 下午06:49:08
	 * @param pwdValidity
	 * @param userId
	 * @return
	 * @throws LoginFailedException
	 */
	public String isPasswordValidity(int pwdValidity, String loginId) throws LoginFailedException
	{
		if (pwdValidity > 0)
		{
			//int useNum = logDao.getPwdValidity(loginId);//密码使用天数
			String lastModiyfPwdDate = logDao.getPwdValidity(loginId);//密码最后更新时间 
			if (StringHelper.isEmpty(lastModiyfPwdDate) || lastModiyfPwdDate.length() < 10)
			{
				logger.error("日期【" + lastModiyfPwdDate + "】格式错误");
				throw new LoginFailedException("系统发生异常");
			}
			int useNum = DateHelper.getDateDiff(new Date(), DateHelper.parseString(lastModiyfPwdDate, DateHelper.pattern_date));
			
			int surplus = pwdValidity - useNum;
			
			if (surplus <= 0)
			{
				throw new LoginFailedException("您的密码已过期，请与管理员联系！");
			}
			else if (surplus <= 5)
			{
				return "您的密码将于" + surplus + "日后过期，请尽快更新您的密码！";
			}
			else
			{
				return "";
			}
		}
		else
		{
			return "";
		}
		
	}
	
	public static void main(String[] args)
	{
		String str = "2012-06-06";
		System.out.println(str);
		try
		{
			int useNum = DateHelper.getDateDiff(new Date(), DateHelper.parseString(str, DateHelper.pattern_date));
			System.out.println(useNum);
		}
		catch (Exception ex)
		{
			logger.error("", ex);
		}
	}
}
