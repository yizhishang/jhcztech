package com.yizhishang.base.service;

import org.springframework.transaction.annotation.Transactional;

import com.yizhishang.base.config.Configuration;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DatabaseType;
import com.yizhishang.base.jdbc.JdbcTemplateUtil;
import com.yizhishang.base.service.exception.ServiceException;
import com.yizhishang.base.util.SpringContextHolder;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-8
 * 创建时间: 12:01:04
 */
@Transactional
public class SequenceGenerator
{
	
	private static SequenceGenerator instance = new SequenceGenerator();
	
	private final String tableName = "T_SEQUENCE";
	
	private final int databaseType = 1;
	
	private static final String CONFIG_ORACLE_SEQUENCE = "system.isOracleSequence";
	
	//	private static final String CONFIG_AUTO_INCREMENT = "system.isAutoIncrement";
	
	private static JdbcTemplateUtil jdbcTemplateUtil = SpringContextHolder.getBean("jdbcTemplateUtil");
	
	public static SequenceGenerator getInstance()
	{
		return instance;
	}
	
	private SequenceGenerator()
	{
	}
	
	private String getNextSequence(String name, int databaseType)
	{
		String seqRet = "", seqCurr = "", nextSeq = "";
		try
		{
			//此处要根据不同的数据库进行相应的加锁
			String condition = "SELECT * FROM " + tableName + " WHERE name=? FOR UPDATE";
			if (databaseType == DatabaseType.MSSQL)
			{
				condition = "SELECT * FROM " + tableName + " WHERE name=? ";
			}
			
			DynaModel result = jdbcTemplateUtil.queryMap(condition, DynaModel.class, new Object[] { name });
			if (result.size() == 0)
			{
				//若没有相应的SEQUENCE存在，则需要建立该SEQUENCE
				String sql = "insert into " + tableName + "(name, current_value, step, roll_back, start_value, max_value)" + " values(?,?,?,?,?,?)";
				jdbcTemplateUtil.update(sql, new Object[] { name, "2", "1", "0", "1", "999999999999" });
				return "1";
			}
			
			seqCurr = new String(result.getString("current_value"));
			seqRet = seqCurr;
			
			long curVal = Long.parseLong(seqCurr);
			long step = Long.parseLong(result.getString("step"));
			int rollBack = Integer.parseInt(result.getString("roll_back"));
			
			long maxValue = Long.parseLong(result.getString("max_value"));
			if (Math.abs(curVal) > Math.abs(maxValue))
			{
				if (rollBack == 1)
				{
					
					seqRet = result.getString("start_value");
					nextSeq = String.valueOf(Long.parseLong(seqRet) + step);
				}
				else
				{
					throw new ServiceException("已经超过SEQUENCE的最大值");
				}
			}
			else
			{
				nextSeq = Long.toString(curVal + step);
			}
			
			String sql = "UPDATE " + tableName + " SET current_value=? WHERE name =?";
			jdbcTemplateUtil.update(sql, new Object[] { nextSeq, name });
		}
		catch (Exception ex)
		{
			throw new ServiceException("获取SEQUENCE失败", ex);
		}
		return seqRet;
	}
	
	/**
	* 获得缺省数据源上的表的SEQUENCE
	*
	* @param name 对应SEQUENCE的名称
	* @return
	*/
	public String getNextSequence(String name)
	{
		if (databaseType == DatabaseType.ORACLE && 1 == Configuration.getInt(CONFIG_ORACLE_SEQUENCE))
		{
			return OracleSequenceGenerator.getNextSequence(name);
		}
		/**
		 * 修改开始
		 * 修改人：袁永君
		 * 描述：设置自增属性。当使用自增字段配置，并执行插入语句时进行设置。
		 */
		else if (1 == Configuration.getInt("system.isAutoIncrement"))
		{
			return "0";
		}
		else
		{
			return getNextSequence(name, databaseType);
		}
	}
	
	/**
	* 获得特定数据源上的表的SEQUENCE
	*
	* @param id   对应datasource.xml文件中的数据源ID
	* @param name 对应SEQUENCE的名称
	* @return
	*/
	public String getNextSequence(String id, String name)
	{
		if (databaseType == DatabaseType.ORACLE && 1 == Configuration.getInt(CONFIG_ORACLE_SEQUENCE))
		{
			return OracleSequenceGenerator.getNextSequence(name);
		}
		/**
		 * 修改开始
		 * 修改人：袁永君
		 * 描述：设置自增属性。当使用自增字段配置，并执行插入语句时进行设置。
		 */
		else if (1 == Configuration.getInt("system.isAutoIncrement"))
		{
			return null;
		}
		/**
		 * 修改结束
		 */
		else
		{
			return getNextSequence(name, databaseType);
		}
	}
	
}
