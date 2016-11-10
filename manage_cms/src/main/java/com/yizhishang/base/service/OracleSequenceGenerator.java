package com.yizhishang.base.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.yizhishang.base.jdbc.connection.ConnManager;
import com.yizhishang.base.service.exception.ServiceException;
import com.yizhishang.base.util.StringHelper;

public class OracleSequenceGenerator
{
	
	static Logger logger = LoggerFactory.getLogger(OracleSequenceGenerator.class);
	
	private static final String SEQUENCE_PREFIX = "seq";
	
	private static final String TABLE_PREFIX = "t_";
	
	private static final int SEQUENCE_LENTH_LIMIT = 30;
	
	private OracleSequenceGenerator()
	{
		
	}
	
	/*public static String getSequenceNameByTableName(String tableName)
	{
		String tName = tableName.toUpperCase();
		if (tName.startsWith(PREFIX))
		{
			tName = tName.substring(PREFIX.length());
		}
		return "SEQ_" + tName;
	}*/

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
			//            String sequenceName = tableName.toLowerCase().replaceFirst("^t", SEQUENCE_PREFIX);
			return sequenceName.length() < SEQUENCE_LENTH_LIMIT ? sequenceName : sequenceName.substring(0, SEQUENCE_LENTH_LIMIT);
		}
		else
		{
			throw new NullPointerException("tableName is null!");
		}
	}
	
	public static String getNextSequence(Connection conn, String tableName)
	{
		String nextVal = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		String seqName = "";
		try
		{
			ConnManager.begin(conn);
			seqName = getSequenceNameByTableName(tableName);
			String sql = "select " + seqName + ".nextval from dual";
			pst = conn.prepareStatement(sql);
			result = pst.executeQuery();
			if (result != null && result.next())
			{
				nextVal = result.getString("nextval");
			}
			else
			{
				throw new SQLException("fail to get sequence");
			}
			ConnManager.commit(conn);
		}
		catch (SQLException e)
		{
			if (e.getErrorCode() == 2289)//序列不存在
			{
				try
				{
					createSequence(seqName, conn);
					ConnManager.commit(conn);
					return "1";
				}
				catch (SQLException e2)
				{
					e = e2;
				}
			}
			ConnManager.rollback(conn);
			throw new ServiceException("fail to get sequence", e);
		}
		finally
		{
			closeResultSet(result);
			closeStatement(pst);
			closeConnection(conn);
		}
		return nextVal;
	}
	
	private static void createSequence(String seqName, Connection conn) throws SQLException
	{
		PreparedStatement pst = null;
		String sql = "create sequence " + seqName;
		sql += " minvalue 1";
		sql += " maxvalue 999999999999";
		sql += " start with 2";
		sql += " increment by 1";
		sql += " nocache";
		try
		{
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
		}
		finally
		{
			closeStatement(pst);
		}
	}
	
	private static void closeResultSet(ResultSet result)
	{
		try
		{
			if (result != null)
			{
				result.close();
			}
		}
		catch (Exception ex)
		{
			logger.error("", ex);
		}
	}
	
	private static void closeStatement(Statement st)
	{
		try
		{
			if (st != null)
			{
				st.close();
			}
		}
		catch (Exception ex)
		{
			logger.error("", ex);
		}
	}
	
	private static void closeConnection(Connection conn)
	{
		ConnManager.close(conn);
	}
}
