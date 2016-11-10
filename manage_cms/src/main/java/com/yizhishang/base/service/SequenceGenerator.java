package com.yizhishang.base.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.yizhishang.base.config.Configuration;
import com.yizhishang.base.jdbc.DatabaseType;
import com.yizhishang.base.jdbc.connection.ConnManager;
import com.yizhishang.base.service.exception.ServiceException;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-8
 * 创建时间: 12:01:04
 */
public class SequenceGenerator
{
	
	private static SequenceGenerator instance = new SequenceGenerator();
	
	private final String tableName = "T_SEQUENCE";
	
	private static final String CONFIG_ORACLE_SEQUENCE = "system.isOracleSequence";
	
    //	private static final String CONFIG_AUTO_INCREMENT = "system.isAutoIncrement";
	
	private static Logger logger = LoggerFactory.getLogger(SequenceGenerator.class);
	
	public static SequenceGenerator getInstance()
	{
		return instance;
	}
	
	private SequenceGenerator()
	{
	}
	
	private void closeConnection(Connection conn)
	{
		ConnManager.close(conn);
	}
	
	private void closeResultSet(ResultSet result)
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
	
	private void closeStatement(Statement st)
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
	
	private void createSequence(String seqName, Connection conn) throws SQLException
	{
		PreparedStatement pst = null;
		String sql = "insert into " + tableName + "(name, current_value, step, roll_back, start_value, max_value)" + " values(?,?,?,?,?,?)";
		try
		{
			pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, seqName);
			pst.setString(i++, "2");
			pst.setString(i++, "1");
			pst.setString(i++, "0");
			pst.setString(i++, "1");
			pst.setString(i++, "999999999999");
			pst.executeUpdate();
		}
		finally
		{
			closeStatement(pst);
		}
	}
	
	private int getDatabaseType(Connection conn)
	{
		int databaseType = 0;
		try
		{
			String dataBaseTypeStr = conn.getMetaData().getDatabaseProductName();
			if (dataBaseTypeStr.equalsIgnoreCase("oracle"))
			{
				databaseType = DatabaseType.ORACLE;
			}
			else if (dataBaseTypeStr.equalsIgnoreCase("MySQL"))
			{
				databaseType = DatabaseType.MYSQL;
			}
			else if (dataBaseTypeStr.equalsIgnoreCase("Microsoft SQL Server"))
			{
				databaseType = DatabaseType.MSSQL;
			}
			else if (dataBaseTypeStr.equalsIgnoreCase("DB2"))
			{
				databaseType = DatabaseType.DB2;
			}
			else if (dataBaseTypeStr.equalsIgnoreCase("PostgreSQL"))
			{
				databaseType = DatabaseType.POSTGRESQL;
			}
			else
			{
				databaseType = DatabaseType.OTHER;
			}
		}
		catch (Exception ex)
		{
			logger.error("", ex);
		}
		return databaseType;
	}
	
	private String getNextSequence(Connection conn, String name, int databaseType)
	{
		String seqRet = "", seqCurr = "", nextSeq = "";
		PreparedStatement pst = null;
		ResultSet result = null;
		try
		{
			ConnManager.begin(conn);
			
            //此处要根据不同的数据库进行相应的加锁
			String condition = "SELECT * FROM " + tableName + " WHERE name=? FOR UPDATE";
			if (databaseType == DatabaseType.MSSQL)
			{
				condition = "SELECT * FROM " + tableName + " WHERE name=? ";
			}
			pst = conn.prepareStatement(condition);
			pst.setString(1, name);
			
			result = pst.executeQuery();
			if (!result.next())
			{
                //若没有相应的SEQUENCE存在，则需要建立该SEQUENCE
				createSequence(name, conn);
				ConnManager.commit(conn);
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
			pst = conn.prepareStatement(sql);
			pst.clearParameters();
			pst.setString(1, nextSeq);
			pst.setString(2, name);
			pst.executeUpdate();
			ConnManager.commit(conn);
		}
		catch (Exception ex)
		{
			ConnManager.rollback(conn);
            throw new ServiceException("获取SEQUENCE失败", ex);
		}
		finally
		{
			closeResultSet(result);
			closeStatement(pst);
			closeConnection(conn);
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
		Connection conn = ConnManager.getConnection();
		try
		{
			int databaseType = getDatabaseType(conn);
			if (databaseType == DatabaseType.ORACLE && 1 == Configuration.getInt(CONFIG_ORACLE_SEQUENCE))
			{
				return OracleSequenceGenerator.getNextSequence(conn, name);
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
			            /**
             * 修改结束
             */
			else
			{
				return getNextSequence(conn, name, databaseType);
			}
		}
		finally
		{
			if (conn != null)
			{
				closeConnection(conn);
			}
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
		Connection conn = ConnManager.getConnection(id);
		try
		{
			int databaseType = getDatabaseType(conn);
			if (databaseType == DatabaseType.ORACLE && 1 == Configuration.getInt(CONFIG_ORACLE_SEQUENCE))
			{
				return OracleSequenceGenerator.getNextSequence(conn, name);
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
				return getNextSequence(conn, name, databaseType);
			}
		}
		finally
		{
			if (conn != null)
			{
				closeConnection(conn);
			}
		}
	}
	
	/*
	    public String getSeqValue(String seqName)
	    {
	        if (seqName == null || seqName.equals(""))
	            return "";

	        Session session = null;
	        try
	        {
	            session = SessionFactory.getSession(com.cz.service.Constants.DB_ORACLE_WEB);
	            session.beginTrans();

	            ArrayList<Object> argList = new ArrayList<Object>();
	            argList.add(seqName);
	            session.update("update T_SEQUENCE set CURRENT_VALUE=CURRENT_VALUE+1 where NAME=?", argList.toArray());
	            DataRow dataRow = session.queryMap("select * from T_SEQUENCE where NAME=?", argList.toArray());
	            if (dataRow == null)
	                return "";

	            long max = dataRow.getLong("max_value");
	            long min = dataRow.getLong("start_value");
	            long value = dataRow.getLong("current_value");

	            if (value >= max)
	            {
	                argList.clear();
	                argList.add(new Long(min));
	                argList.add(seqName);
	                session.update("update T_SEQUENCE set CURRENT_VALUE=? where NAME=?", argList.toArray());
	            }

	            session.commitTrans();
	            //return SEQSTARTS + value;
	            return "" + value;
	        }
	        catch (Exception e)
	        {
	            if (session != null)
	            {
	                session.rollbackTrans();
	            }
	        }
	        finally
	        {
	            if (session != null)
	            {
	                session.close();
	                session = null;
	            }
	        }
	        return "";
	    }
	    */
}
