package com.yizhishang.base.jdbc.session.Impl;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.yizhishang.base.config.Configuration;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.jdbc.DataRow;
import com.yizhishang.base.jdbc.DatabaseType;
import com.yizhishang.base.jdbc.exception.JdbcException;
import com.yizhishang.base.jdbc.session.Session;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-5
 * 创建时间: 16:04:58
 */
public class SessionImpl implements Session
{
	
	private static Logger logger = LoggerFactory.getLogger(SessionImpl.class);
	
	private Connection conn = null;
	
	private int databaseType = 0;
	
	private String generatedKeys = "";
	
	public SessionImpl(Connection conn)
	{
		this.conn = conn;
		setDataBaseType(conn);
	}
	
    /**
    * 批量执行更新并返回每次的更新记录数
    *
    * @param sql  SQL语句
    * @param args 参数中的值
    * @return
    */
	@Override
    public int[] batchUpdate(String sql, Object[][] args)
	{
		PreparedStatement pstmt = null;
		try
		{
			
            logger.debug("开始执行 [sql= " + sql + "]");
			long beginTime = System.currentTimeMillis();
			
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++)
			{
				Object[] curArgs = args[i];
				for (int j = 1; j <= curArgs.length; j++)
				{
					pstmt.setObject(j, curArgs[j - 1]);
				}
				pstmt.addBatch();
			}
			
			int[] result = pstmt.executeBatch();
			
			long time = System.currentTimeMillis() - beginTime;
            logger.debug("执行完成 [time=" + time + " millisecond]");
			
			return result;
		}
		catch (SQLException ex)
		{
			throw new JdbcException("", ex);
		}
		finally
		{
			closeStatement(pstmt);
		}
	}
	
    /**
    * 批量执行更新并返回每次的更新记录数
    *
    * @param sqlArray SQL语句数组
    * @return 每次执行更新的记录数数组
    */
	@Override
    public int[] batchUpdate(String[] sqlArray)
	{
		Statement stmt = null;
		try
		{
            logger.debug("开始执行");
			long beginTime = System.currentTimeMillis();
			
			stmt = conn.createStatement();
			for (int i = 0; i < sqlArray.length; i++)
			{
				logger.debug("sql= " + sqlArray[i]);
				stmt.addBatch(sqlArray[i]);
			}
			
			int[] result = stmt.executeBatch();
			
			long time = System.currentTimeMillis() - beginTime;
            logger.debug("执行完成 [time=" + time + " millisecond]");
			
			return result;
		}
		catch (SQLException ex)
		{
			throw new JdbcException("", ex);
		}
		finally
		{
			closeStatement(stmt);
		}
	}
	
    /**
    * 开始数据库事务
    */
	@Override
    public void beginTrans()
	{
		try
		{
			if (conn != null && !conn.isClosed())
			{
				conn.setAutoCommit(false);
			}
		}
		catch (Exception ex)
		{
			throw new JdbcException("", ex);
		}
	}
	
    /**
    * 关闭数据库会话
    */
	@Override
    public void close()
	{
		try
		{
			if (conn != null && !conn.isClosed())
			{
				conn.close();
			}
			conn = null;
		}
		catch (Exception ex)
		{
			logger.error("", ex);
		}
	}
	
	/*public void update(String tableName, DataRow data, String identify, Object identifyValue)
	{
	    StringBuffer sqlBuffer = new StringBuffer();
	    sqlBuffer.append("update " + tableName + " set ");

	    int i = 0;
	    ArrayList valueList = new ArrayList();
	    data.remove(identify);
	    for (Iterator iter = data.keySet().iterator(); iter.hasNext();)
	    {
	        i++;
	        String key = (String) iter.next();
	        valueList.add(data.get(key));

	        if (i < data.size())
	        {
	            sqlBuffer.append(key + "=?,");
	        }
	        else
	        {
	            sqlBuffer.append(key + "=?");
	        }

	    }
	    sqlBuffer.append(" where " + identify + "=?");
	    valueList.add(identifyValue);

	    update(sqlBuffer.toString(), valueList.toArray());
	}*/

	private void closeResultSet(ResultSet rs)
	{
		try
		{
			if (rs != null)
			{
				rs.close();
			}
		}
		catch (Exception ex)
		{
			logger.error("", ex);
		}
	}
	
	private void closeStatement(Statement stmt)
	{
		try
		{
			if (stmt != null)
			{
				stmt.close();
			}
		}
		catch (Exception ex)
		{
			logger.error("", ex);
		}
	}
	
    /**
    * 提交数据库事务
    */
	@Override
    public void commitTrans()
	{
		try
		{
			if (conn != null && !conn.isClosed())
			{
				conn.commit();
			}
		}
		catch (Exception ex)
		{
			throw new JdbcException("", ex);
		}
	}
	
    /**
    * 返回会话中的Connection
    *
    * @return
    */
	@Override
    public Connection connection()
	{
		return conn;
	}
	
    /**
    * 删除表中的一条记录
    *
    * @param tableName     需要删除的记录的表名
    * @param identify      识别字段的名称
    * @param identifyValue 识别字段的值
    * @return
    */
	@Override
    public void delete(String tableName, String identify, Object identifyValue)
	{
		String sql = "delete from " + tableName + " where " + identify + "=?";
		update(sql, new Object[] { identifyValue });
	}
	
    /**
    * 返回数据库的类型,类型定义在DataBaseType中
    *
    * @return
    */
	@Override
    public int getDataBaseType()
	{
		return databaseType;
	}
	
    /**
    * 描述：获取自生成主键值
    * 作者：袁永君
    * 时间：2013年7月23日 下午5:49:26
    * @return
    */
	@Override
    public String getGeneratedKeys()
	{
		return generatedKeys;
	}
	
    /**
    * 在相应的表中增加一条记录
    *
    * @param tableName 需要添加的表名
    * @param data      需要添加的信息
    * @return
    */
	@Override
    public void insert(String tableName, DataRow data)
	{
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("insert into " + tableName + "(");
		
		String interrogationStr = "";
		int i = 0;
        ArrayList<Object> valueList = new ArrayList<Object>();
        for (Iterator<String> iter = data.keySet().iterator(); iter.hasNext();)
		{
			i++;
			String key = iter.next();
			valueList.add(data.get(key));
			
			if (i < data.size())
			{
				sqlBuffer.append(key + ",");
				interrogationStr += " ?,";
			}
			else
			{
				sqlBuffer.append(key);
				interrogationStr += "?";
			}
			
		}
		sqlBuffer.append(") values (");
		sqlBuffer.append(interrogationStr);
		sqlBuffer.append(") ");
		
		update(sqlBuffer.toString(), valueList.toArray());
	}
	
    /**
    * 查询一个对象列表结果,列表中的每一行为一个DataRow,若没有查询到，则返回null。
    *
    * @param sql SQL语句
    * @return 查询所有结果并。
    */
	@Override
    public List<Object> query(String sql)
	{
		return query(sql, null);
	}
	
    /**
    * 查询一个对象列表结果,列表中的每一行为一个DataRow,若没有查询到，则返回null。
    *
    * @param sql  SQL语句
    * @param rows 查询结果数量
    * @return 查询所有结果并。
    */
	@Override
    public List<Object> query(String sql, int rows)
	{
		return query(sql, null, rows);
	}
	
    /**
    * 查询一个对象列表结果,列表中的每一行为一个DataRow。
    *
    * @param sql  SQL语句
    * @param rows 返回的记录数
    * @return 查询所有结果并。
    */
	@Override
    public List<Object> query(String sql, int startRow, int rows)
	{
		return query(sql, null, startRow, rows);
	}
	
    /**
    * 查询一个对象列表结果,列表中的每一行为一个DataRow。
    *
    * @param sql  SQL语句
    * @param args 参数中的值
    * @return 查询所有结果。
    */
	@Override
    public List<Object> query(String sql, Object[] args)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        ArrayList<Object> list = new ArrayList<Object>();
		
		try
		{
            logger.debug("开始执行 [sql= " + sql + "]");
			long beginTime = System.currentTimeMillis();
			
			pstmt = conn.prepareStatement(sql);
			if (args != null)
			{
				for (int i = 1; i <= args.length; i++)
				{
					pstmt.setObject(i, args[i - 1]);
				}
			}
			
			rs = pstmt.executeQuery();
			
			long time = System.currentTimeMillis() - beginTime;
            logger.debug("执行完成 [time=" + time + " millisecond]");
			
			if (time > 1000)
			{
                logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[time=" + time + " millisecond]");
			}
			
			ResultSetMetaData metaData = rs.getMetaData();
			while (rs.next())
			{
				list.add(toDataRow(rs, metaData));
			}
			
			return list;
		}
		catch (SQLException ex)
		{
			throw new JdbcException("", ex);
		}
		finally
		{
			closeResultSet(rs);
			closeStatement(pstmt);
		}
	}
	
    /**
    * 查询一个对象列表结果,列表中的每一行为一个DataRow,若没有查询到，则返回null。
    *
    * @param sql  SQL语句
    * @param args 参数中的值
    * @param rows 查询结果数量
    * @return 查询所有结果。
    */
	@Override
    public List<Object> query(String sql, Object[] args, int rows)
	{
		return query(sql, args, 0, rows);
	}
	
    /**
    * 查询一个对象列表结果,列表中的每一行为一个DataRow。
    *
    * @param sql  SQL语句
    * @param args 参数中的值
    * @param rows 返回的记录数
    * @return 查询所有结果。
    */
	@Override
    public List<Object> query(String sql, Object[] args, int startRow, int rows)
	{
        if (databaseType == DatabaseType.ORACLE) //是oracle数据库
		{
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append("select * from ( select row_.*, rownum rownum_ from ( ");
			sqlBuffer.append(sql);
			sqlBuffer.append(" ) row_ where rownum <= " + (startRow + rows) + ") where rownum_ > " + startRow + "");
			return queryFromSpecialDB(sqlBuffer.toString(), args);
		}
        else if (databaseType == DatabaseType.MYSQL) //是mysql数据库
		{
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append(sql);
			sqlBuffer.append(" limit " + startRow + "," + rows + "");
			return queryFromSpecialDB(sqlBuffer.toString(), args);
		}
        else if (databaseType == DatabaseType.MSSQL) //是mssql数据库
		{
			                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            /*
            注意，此处并没有使用MSSQL特有的数据库分页语句，是因为MSSQL的JTDS驱动的滚动结果集是直接
            使用的服务器端的CURSOR，可以直接在服务器端进行记录的移动，所以使用JTDS驱动时，
            可以直接使用滚动结果集，性能不会太差。
            操作SQLServer时要使用JTDS驱动，不要使用MS自己的驱动
            */
			return queryFromOtherDB(sql, args, startRow, rows);
		}
        else if (databaseType == DatabaseType.DB2) //是DB2数据库
		{
			String temp = sql.toUpperCase();
			int fromIdx = temp.lastIndexOf(" FROM ");
			int orderIdx = temp.lastIndexOf(" ORDER ");
			String sFrom = "";
			String sOrderBy = "";
			if (orderIdx == -1)
			{
				sFrom = sql.substring(fromIdx, sql.length());
			}
			else
			{
				sFrom = sql.substring(fromIdx, orderIdx);
				sOrderBy = sql.substring(orderIdx);
			}
			String sSelect = sql.substring(0, fromIdx);
			
			int iBegin = startRow;
			int iEnd = startRow + rows;
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append("SELECT * FROM (" + sSelect + ", ROW_NUMBER() OVER(" + sOrderBy + ") AS rn " + sFrom + ") originTable WHERE rn BETWEEN " + iBegin + " AND " + iEnd);
			return queryFromSpecialDB(sqlBuffer.toString(), args);
		}
        else if (databaseType == DatabaseType.POSTGRESQL) //是PostgreSQL数据库
		{
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append(sql);
			sqlBuffer.append(" limit " + rows + " offset " + (startRow - 1) + "");
			return queryFromSpecialDB(sqlBuffer.toString(), args);
		}
		else
        //是其它数据库，暂时使用滚动结果集进行支持
		{
			return queryFromOtherDB(sql, args, startRow, rows);
		}
	}
	
    private List<Object> queryFromOtherDB(String sql, Object[] args, int startRow, int rows)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        ArrayList<Object> list = new ArrayList<Object>();
		
		try
		{
            logger.debug("开始执行 [sql= " + sql + "]");
			long beginTime = System.currentTimeMillis();
			
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.setFetchSize(50);
			if (args != null)
			{
				for (int i = 1; i <= args.length; i++)
				{
					pstmt.setObject(i, args[i - 1]);
				}
			}
			
			rs = pstmt.executeQuery();
			
			long time = System.currentTimeMillis() - beginTime;
            logger.debug("执行完成 [time=" + time + " millisecond]");
			
			if (time > 1000)
			{
                logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[time=" + time + " millisecond]");
			}
			
            //移动到开始行
			rs.absolute(startRow);
			ResultSetMetaData metaData = rs.getMetaData();
			int count = 0;
			while (rs.next())
			{
				count++;
				list.add(toDataRow(rs, metaData));
				if (count == rows)
				{
					break;
				}
			}
			
			return list;
		}
		catch (SQLException ex)
		{
			throw new JdbcException("", ex);
		}
		finally
		{
			closeResultSet(rs);
			closeStatement(pstmt);
		}
	}
	
    private List<Object> queryFromSpecialDB(String sql, Object[] args)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        ArrayList<Object> list = new ArrayList<Object>();
		
		try
		{
            logger.debug("开始执行 [sql= " + sql + "]");
			long beginTime = System.currentTimeMillis();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setFetchSize(50);
			if (args != null)
			{
				for (int i = 1; i <= args.length; i++)
				{
					pstmt.setObject(i, args[i - 1]);
				}
			}
			
			rs = pstmt.executeQuery();
			
			long time = System.currentTimeMillis() - beginTime;
            logger.debug("执行完成 [time=" + time + " millisecond]");
			
			if (time > 1000)
			{
                logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[time=" + time + " millisecond]");
			}
			
			ResultSetMetaData metaData = rs.getMetaData();
			while (rs.next())
			{
				list.add(toDataRow(rs, metaData));
			}
			
			return list;
		}
		catch (SQLException ex)
		{
			throw new JdbcException("", ex);
		}
		finally
		{
			closeResultSet(rs);
			closeStatement(pstmt);
		}
	}
	
    /**
    * 查询一个整型结果。
    *
    * @param sql SQL语句
    * @return 查询的第一行的第一个字段的整型值。
    */
	@Override
    public int queryInt(String sql)
	{
		return queryInt(sql, null);
	}
	
    /**
    * 查询一个整型结果。
    *
    * @param sql  SQL语句
    * @param args 参数中的值
    * @return 查询的第一行的第一个字段的整型值。
    */
	@Override
    public int queryInt(String sql, Object[] args)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try
		{
            logger.debug("开始执行 [sql= " + sql + "]");
			long beginTime = System.currentTimeMillis();
			
			pstmt = conn.prepareStatement(sql);
			if (args != null)
			{
				for (int i = 1; i <= args.length; i++)
				{
					pstmt.setObject(i, args[i - 1]);
				}
			}
			
			rs = pstmt.executeQuery();
			
			long time = System.currentTimeMillis() - beginTime;
            logger.debug("执行完成 [time=" + time + " millisecond]");
			
			if (time > 1000)
			{
                logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[time=" + time + " millisecond]");
			}
			
			if (rs.next())
			{
				result = rs.getInt(1);
			}
			
			return result;
		}
		catch (SQLException ex)
		{
			throw new JdbcException("", ex);
		}
		finally
		{
			closeResultSet(rs);
			closeStatement(pstmt);
		}
	}
	
    /**
    * 返回一个数字数组
    *
    * @param sql SQL语句
    * @return 查询的多条记录第一个字段的整型值。
    */
	@Override
    public int[] queryIntArray(String sql)
	{
		return queryIntArray(sql, null);
	}
	
    /**
    * 返回一个数字数组
    *
    * @param sql  SQL语句
    * @param args 参数中的值*
    * @return 查询的多条记录第一个字段的整型值。
    */
	@Override
    public int[] queryIntArray(String sql, Object[] args)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
            logger.debug("开始执行 [sql= " + sql + "]");
			long beginTime = System.currentTimeMillis();
			
			pstmt = conn.prepareStatement(sql);
			if (args != null)
			{
				for (int i = 1; i <= args.length; i++)
				{
					pstmt.setObject(i, args[i - 1]);
				}
			}
			
			rs = pstmt.executeQuery();
			
			long time = System.currentTimeMillis() - beginTime;
            logger.debug("执行完成 [time=" + time + " millisecond]");
			
			if (time > 1000)
			{
                logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[time=" + time + " millisecond]");
			}
			
            ArrayList<Integer> resultList = new ArrayList<Integer>();
			while (rs.next())
			{
				int value = rs.getInt(1);
				resultList.add(new Integer(value));
			}
			
			int[] result = null;
			if (resultList.size() > 0)
			{
				result = new int[resultList.size()];
				for (int i = 0; i < resultList.size(); i++)
				{
					result[i] = resultList.get(i).intValue();
				}
			}
			
			return result;
		}
		catch (SQLException ex)
		{
			throw new JdbcException("", ex);
		}
		finally
		{
			closeResultSet(rs);
			closeStatement(pstmt);
		}
	}
	
    /**
    * 查询一个长整型结果。
    *
    * @param sql SQL语句
    * @return 查询的第一行的第一个字段的长整型值。
    */
	@Override
    public long queryLong(String sql)
	{
		return queryLong(sql, null);
	}
	
    /**
    * 查询一个长整型结果。
    *
    * @param sql  SQL语句
    * @param args 参数中的值
    * @return 查询的第一行的第一个字段的长整型值。
    */
	@Override
    public long queryLong(String sql, Object[] args)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long result = 0;
		
		try
		{
            logger.debug("开始执行 [sql= " + sql + "]");
			long beginTime = System.currentTimeMillis();
			
			pstmt = conn.prepareStatement(sql);
			if (args != null)
			{
				for (int i = 1; i <= args.length; i++)
				{
					pstmt.setObject(i, args[i - 1]);
				}
			}
			
			rs = pstmt.executeQuery();
			
			long time = System.currentTimeMillis() - beginTime;
            logger.debug("执行完成 [time=" + time + " millisecond]");
			
			if (time > 1000)
			{
                logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[time=" + time + " millisecond]");
			}
			
			if (rs.next())
			{
				result = rs.getLong(1);
			}
			
			return result;
		}
		catch (SQLException ex)
		{
			throw new JdbcException("", ex);
		}
		finally
		{
			closeResultSet(rs);
			closeStatement(pstmt);
		}
	}
	
    /**
    * 返回一个长整型数组
    *
    * @param sql SQL语句
    * @return 查询的多条记录第一个字段的长整型值。
    */
	@Override
    public long[] queryLongArray(String sql)
	{
		return queryLongArray(sql, null);
	}
	
    /**
    * 返回一个长整型数组
    *
    * @param sql  SQL语句
    * @param args 参数中的值*
    * @return 查询的多条记录第一个字段的长整型值。
    */
	@Override
    public long[] queryLongArray(String sql, Object[] args)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
            logger.debug("开始执行 [sql= " + sql + "]");
			long beginTime = System.currentTimeMillis();
			
			pstmt = conn.prepareStatement(sql);
			if (args != null)
			{
				for (int i = 1; i <= args.length; i++)
				{
					pstmt.setObject(i, args[i - 1]);
				}
			}
			
			rs = pstmt.executeQuery();
			
			long time = System.currentTimeMillis() - beginTime;
            logger.debug("执行完成 [time=" + time + " millisecond]");
			
			if (time > 1000)
			{
                logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[time=" + time + " millisecond]");
			}
			
            ArrayList<Long> resultList = new ArrayList<Long>();
			while (rs.next())
			{
				long value = rs.getLong(1);
				resultList.add(new Long(value));
			}
			
			long[] result = null;
			if (resultList.size() > 0)
			{
				result = new long[resultList.size()];
				for (int i = 0; i < resultList.size(); i++)
				{
					result[i] = resultList.get(i).longValue();
				}
			}
			
			return result;
		}
		catch (SQLException ex)
		{
			throw new JdbcException("", ex);
		}
		finally
		{
			closeResultSet(rs);
			closeStatement(pstmt);
		}
	}
	
    /**
    * 查询一个DataRow结果，若没有查询到，则返回null。
    *
    * @param sql SQL语句
    * @return 查询的第一行的结果,反回结果中的字段名都为小写
    */
	@Override
    public DataRow queryMap(String sql)
	{
		return queryMap(sql, null);
	}
	
    /**
    * 查询一个DataRow结果,若没有查询到，则返回null。
    *
    * @param sql  SQL语句
    * @param args 参数中的值
    * @return 查询的第一行的结果,反回结果中的字段名都为小写。
    */
	@Override
    public DataRow queryMap(String sql, Object[] args)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DataRow result = null;
		
		try
		{
            logger.debug("开始执行 [sql= " + sql + "]");
			long beginTime = System.currentTimeMillis();
			
			pstmt = conn.prepareStatement(sql);
			if (args != null)
			{
				for (int i = 1; i <= args.length; i++)
				{
					pstmt.setObject(i, args[i - 1]);
				}
			}
			
			rs = pstmt.executeQuery();
			
			long time = System.currentTimeMillis() - beginTime;
            logger.debug("执行完成 [time=" + time + " millisecond]");
			
			if (time > 1000)
			{
                logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[time=" + time + " millisecond]");
			}
			
			ResultSetMetaData metaData = rs.getMetaData();
			if (rs.next())
			{
				result = toDataRow(rs, metaData);
			}
			return result;
		}
		catch (SQLException ex)
		{
			throw new JdbcException("", ex);
		}
		finally
		{
			closeResultSet(rs);
			closeStatement(pstmt);
		}
	}
	
    /**
    * 查询一个分页列表结果。
    *
    * @param sql        SQL语句
    * @param curPage    当前页数
    * @param numPerPage 每页显示的记录数
    * @return 分页对象
    */
	@Override
    public DBPage queryPage(String sql, int curPage, int numPerPage)
	{
		return queryPage(sql, null, curPage, numPerPage);
	}
	
    /**
    * 查询一个分页列表结果。
    *
    * @param sql        SQL语句
    * @param args       参数中的值
    * @param curPage    当前页数
    * @param numPerPage 每页显示的记录数
    * @return 分页对象
    */
	@Override
    public DBPage queryPage(String sql, Object[] args, int curPage, int numPerPage)
	{
        //计算总的记录数
		String temp = sql;
		int orderIdx = sql.toUpperCase().lastIndexOf(" ORDER ");
		if (orderIdx != -1)
		{
			//temp = temp.substring(0, orderIdx);
			temp = sql.substring(0, orderIdx);
		}
		StringBuffer totalSQL = new StringBuffer(" SELECT count(1) FROM ( ");
		totalSQL.append(temp);
		totalSQL.append(" ) totalTable ");
		int totalRows = queryInt(totalSQL.toString(), args);
		
        //构造分页对象
		DBPage page = new DBPage(curPage, numPerPage);
        //设置总的记录数
		page.setTotalRows(totalRows);
		
        //查询对应页的数据
		int startIndex = page.getStartIndex();
		int endIndex = page.getLastIndex();
		int rows = endIndex - startIndex;
		rows = (rows < 0) ? 0 : rows;
		
        List<Object> list = query(sql, args, startIndex, rows);
        //设置数据
		page.setData(list);
		return page;
		
	}
	
    /**
    * 查询一个字符串结果。
    *
    * @param sql SQL语句
    * @return 查询的第一行的第一个字段的字符串值。
    */
	@Override
    public String queryString(String sql)
	{
		return queryString(sql, null);
	}
	
    /**
    * 查询一个字符串结果。
    *
    * @param sql  SQL语句
    * @param args 参数中的值
    * @return 查询的第一行的第一个字段的字符串值。
    */
	@Override
    public String queryString(String sql, Object[] args)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "";
		
		try
		{
            logger.debug("开始执行 [sql= " + sql + "]");
			long beginTime = System.currentTimeMillis();
			
			pstmt = conn.prepareStatement(sql);
			if (args != null)
			{
				for (int i = 1; i <= args.length; i++)
				{
					pstmt.setObject(i, args[i - 1]);
				}
			}
			
			rs = pstmt.executeQuery();
			
			long time = System.currentTimeMillis() - beginTime;
            logger.debug("执行完成 [time=" + time + " millisecond]");
			
			if (time > 1000)
			{
                logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[time=" + time + " millisecond]");
			}
			
			if (rs.next())
			{
				result = rs.getString(1);
			}
			
			return result;
		}
		catch (SQLException ex)
		{
			throw new JdbcException("", ex);
		}
		finally
		{
			closeResultSet(rs);
			closeStatement(pstmt);
		}
	}
	
    /**
    * 返回一个字符串数组
    *
    * @param sql SQL语句
    * @return 查询的多条记录第一个字段的字符串值。
    */
	@Override
    public String[] queryStringArray(String sql)
	{
		return queryStringArray(sql, null);
	}
	
    /**
    * 返回一个字符串数组
    *
    * @param sql  SQL语句
    * @param args 参数中的值
    * @return 查询的多条记录第一个字段的字符串值。
    */
	@Override
    public String[] queryStringArray(String sql, Object[] args)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
            logger.debug("开始执行 [sql= " + sql + "]");
			long beginTime = System.currentTimeMillis();
			
			pstmt = conn.prepareStatement(sql);
			if (args != null)
			{
				for (int i = 1; i <= args.length; i++)
				{
					pstmt.setObject(i, args[i - 1]);
				}
			}
			
			rs = pstmt.executeQuery();
			
			long time = System.currentTimeMillis() - beginTime;
            logger.debug("执行完成 [time=" + time + " millisecond]");
			
			if (time > 1000)
			{
                logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[time=" + time + " millisecond]");
			}
			
            ArrayList<String> resultList = new ArrayList<String>();
			while (rs.next())
			{
				String value = rs.getString(1);
				resultList.add(value);
			}
			
			String[] result = null;
			if (resultList.size() > 0)
			{
				result = new String[resultList.size()];
				for (int i = 0; i < resultList.size(); i++)
				{
					result[i] = resultList.get(i);
				}
			}
			
			return result;
		}
		catch (SQLException ex)
		{
			throw new JdbcException("", ex);
		}
		finally
		{
			closeResultSet(rs);
			closeStatement(pstmt);
		}
	}
	
    /**
    * 回滚数据库事务
    */
	@Override
    public void rollbackTrans()
	{
		try
		{
			if (conn != null && !conn.isClosed())
			{
				conn.rollback();
			}
		}
		catch (Exception ex)
		{
			throw new JdbcException("", ex);
		}
	}
	
	private void setDataBaseType(Connection conn)
	{
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
	}
	
	private void setGeneratedKeys(String generatedKeys)
	{
		this.generatedKeys = generatedKeys;
	}
	
	private DataRow toDataRow(ResultSet rs, ResultSetMetaData metaData) throws SQLException
	{
		DataRow dataRow = new DataRow();
		int count = metaData.getColumnCount();
		for (int i = 0; i < count; i++)
		{
			String fieldName = "";
			if (databaseType == DatabaseType.MYSQL)
			{
				fieldName = metaData.getColumnLabel(i + 1);
			}
			else
			{
				fieldName = metaData.getColumnName(i + 1);
				
			}
			Object value = rs.getObject(fieldName);
			if (value instanceof Clob)
			{
				value = rs.getString(fieldName);
			}
			else if (value instanceof Blob)
			{
				value = rs.getBytes(fieldName);
			}
			else if (value instanceof Date)
			{
				value = rs.getTimestamp(fieldName);
			}
            //把字段名转换为小写
			dataRow.set(fieldName.toLowerCase(), value);
		}
		return dataRow;
	}
	
    /**
    * 执行指定的sql并返回更新的记录数。
    *
    * @param sql SQL语句
    * @return 更新的记录数
    */
	@Override
    public int update(String sql)
	{
		return update(sql, null);
	}
	
    /**
    * 更新表中的一条记录信息
    *
    * @param tableName     需要更新的表名
    * @param data          需要更新的信息
    * @param identify      识别字段的名称
    * @param identifyValue 识别字段的值
    * @return
    */
	@Override
    public void update(String tableName, DataRow data, String identify, Object identifyValue)
	{
		update(tableName, data, new String[] { identify }, new Object[] { identifyValue });
	}
	
    /**
    * 更新表中的一条记录信息
    *
    * @param tableName     需要更新的表名
    * @param data          需要更新的信息
    * @param identifys      识别字段的名称
    * @param identifyValues 识别字段的值
    * @return
    */
	@Override
    public void update(String tableName, DataRow data, String[] identifys, Object[] identifyValues)
	{
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("update " + tableName + " set ");
		
		int i = 0;
        ArrayList<Object> valueList = new ArrayList<Object>();
		for (int j = 0; j < identifys.length; j++)
		{
			data.remove(identifys[j]);
		}
        for (Iterator<String> iter = data.keySet().iterator(); iter.hasNext();)
		{
			i++;
			String key = iter.next();
			valueList.add(data.get(key));
			
			if (i < data.size())
			{
				sqlBuffer.append(key + "=?,");
			}
			else
			{
				sqlBuffer.append(key + "=?");
			}
			
		}
		for (int k = 0; k < identifys.length; k++)
		{
			sqlBuffer.append((k == 0 ? " where " : " and ") + identifys[k] + "=?");
			valueList.add(identifyValues[k]);
		}
		
		update(sqlBuffer.toString(), valueList.toArray());
	}
	
    /**
    * 执行指定的sql并返回更新的记录数。
    *
    * @param sql  SQL语句
    * @param args 参数中的值
    * @return 更新的记录数
    */
	@Override
    public int update(String sql, Object[] args)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
            logger.debug("开始执行 [sql= " + sql + "]");
			long beginTime = System.currentTimeMillis();
			
            /**
            * 修改开始
            * 修改人：袁永君
            * 描述：设置自增属性。当使用自增字段配置，并执行插入语句时进行设置。
            */
			if (1 == Configuration.getInt("system.isAutoIncrement") && sql.toLowerCase().startsWith("insert"))
			{
				pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			else
			{
				pstmt = conn.prepareStatement(sql);
			}
            /**
            * 修改结束
            */
			if (args != null)
			{
				for (int i = 1; i <= args.length; i++)
				{
					pstmt.setObject(i, args[i - 1]);
				}
			}
			
			int result = pstmt.executeUpdate();
			
            /**
            * 修改开始
            * 修改人：袁永君
            * 描述：通过数据库自增字段特性，获取自增字段的值
            */
			if (1 == Configuration.getInt("system.isAutoIncrement") && sql.toLowerCase().startsWith("insert"))
			{
				rs = pstmt.getGeneratedKeys();
				if (rs.next())
				{
					setGeneratedKeys(rs.getString(1));
				}
			}
            /**
            * 修改结束
            */
			
			long time = System.currentTimeMillis() - beginTime;
            logger.debug("执行完成 [time=" + time + " millisecond]");
			
			if (time > 1000)
			{
                logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[time=" + time + " millisecond]");
			}
			
			return result;
		}
		catch (SQLException ex)
		{
			
			throw new JdbcException("", ex);
		}
		finally
		{
			closeResultSet(rs);
			closeStatement(pstmt);
		}
	}
	
}
