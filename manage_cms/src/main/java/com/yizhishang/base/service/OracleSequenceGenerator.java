package com.yizhishang.base.service;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.transaction.annotation.Transactional;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.JdbcTemplateUtil;
import com.yizhishang.base.service.exception.ServiceException;
import com.yizhishang.base.util.SpringContextHolder;
import com.yizhishang.base.util.StringHelper;

@Transactional
public class OracleSequenceGenerator
{
	
	static Logger logger = LoggerFactory.getLogger(OracleSequenceGenerator.class);
	
	private static final String SEQUENCE_PREFIX = "seq";
	
	private static final String TABLE_PREFIX = "t_";
	
	private static final int SEQUENCE_LENTH_LIMIT = 30;
	
	private static JdbcTemplateUtil jdbcTemplateUtil = SpringContextHolder.getBean("jdbcTemplateUtil");
	
	private OracleSequenceGenerator()
	{
		
	}
	
	public static String getSequenceNameByTableName(String tableName)
	{
		if (StringHelper.isNotBlank(tableName))
		{
			String sequenceName = tableName.toLowerCase();
			if (sequenceName.startsWith(TABLE_PREFIX))
			{
				sequenceName = sequenceName.replaceFirst("^t", SEQUENCE_PREFIX);
			}
			else if (sequenceName.startsWith(SEQUENCE_PREFIX + "_"))
			{
				// modify by 20140508 允许传入一个seq名称，不作转换
			}
			else
			{
				sequenceName = SEQUENCE_PREFIX + "_" + sequenceName;
			}
			return sequenceName.length() < SEQUENCE_LENTH_LIMIT ? sequenceName : sequenceName.substring(0, SEQUENCE_LENTH_LIMIT);
		}
		else
		{
			throw new NullPointerException("tableName is null!");
		}
	}
	
	public static String getNextSequence(String tableName)
	{
		String nextVal = null;
		String seqName = "";
		try
		{
			seqName = getSequenceNameByTableName(tableName);
			String sql = "select " + seqName + ".nextval from dual";
			DynaModel result = jdbcTemplateUtil.queryMap(sql);
			if (result != null && result.size() > 0)
			{
				nextVal = result.getString("nextval");
			}
			else
			{
				throw new SQLException("fail to get sequence");
			}
		}
		catch (BadSqlGrammarException e)
		{
			logger.error(e.getMessage());
		}
		catch (SQLException e)
		{
			
			if (e.getErrorCode() == 2289)//序列不存在
			{
				String sql = "create sequence " + seqName;
				sql += " minvalue 1";
				sql += " maxvalue 999999999999";
				sql += " start with 2";
				sql += " increment by 1";
				sql += " nocache";
				jdbcTemplateUtil.update(sql);
				return "1";
			}
			throw new ServiceException("fail to get sequence", e);
		}
		return nextVal;
	}
}
