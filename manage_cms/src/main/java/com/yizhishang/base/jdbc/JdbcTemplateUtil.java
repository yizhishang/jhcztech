package com.yizhishang.base.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.exception.JdbcException;

/**
 * 描述:	 JDBC操作模板
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-4
 * 创建时间: 23:11:24
 */
@Component
public class JdbcTemplateUtil
{
	
	final static int databaseType = 1;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * 删除表中的记录
	 * @param tableName     需要删除的记录的表名
	 * @param identify      识别字段的名称
	 * @param identifyValue 识别字段的值
	 * @return
	 */
	public int delete(String tableName, String identify, Object identifyValue)
	{
		String sql = "delete from " + tableName + " where " + identify + "=?";
		return update(sql, new Object[] { identifyValue });
	}
	
	/**
	 * 执行指定的sql并返回更新的记录数。
	 * @param sql  SQL语句
	 * @param args 参数中的值
	 * @return 更新的记录数
	 */
	public int update(String sql, Object[] args)
	{
		logger.debug("开始执行 [sql= " + sql + "]");
		long beginTime = System.currentTimeMillis();
		
		int result = jdbcTemplate.update(sql, args);
		
		long time = System.currentTimeMillis() - beginTime;
		logger.debug("执行完成 [time=" + time + " millisecond]");
		
		if (time > 1000)
		{
			logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[time=" + time + " millisecond]");
		}
		
		return result;
	}
	
	/**
	 * 在相应的表中增加一条记录
	 *
	 * @param tableName 需要添加的表名
	 * @param data      需要添加的信息
	 * @return
	 */
	public void insert(String tableName, DynaModel data)
	{
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("insert into " + tableName + "(");
		
		String interrogationStr = "";
		int i = 0;
		ArrayList<Object> valueList = new ArrayList<Object>();
		for (Iterator<String> iter = data.toMap().keySet().iterator(); iter.hasNext();)
		{
			i++;
			String key = iter.next();
			valueList.add(data.toMap().get(key));
			
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
	 * 查询一个对象列表结果,列表中的每一行为一个DynaModel。
	 * @param sql SQL语句
	 * @return 查询所有结果列表。
	 */
	public List<DynaModel> queryForList(String sql)
	{
		return queryForList(sql, DynaModel.class);
	}
	
	/**
	 * 查询一个对象列表结果,列表中的每一行为一个DynaModel。
	 * @param sql SQL语句
	 * @return 查询所有结果列表。
	 */
	public <T> List<T> queryForList(String sql, Class<T> cls)
	{
		return queryForList(sql, cls, null);
	}
	
	/**
	 * 查询一个对象列表结果,列表中的每一行为一个DynaModel。
	 * @param rows 返回的记录数量
	 * @return 查询固定的记录数
	 */
	public List<DynaModel> queryForList(String sql, int rows)
	{
		return queryForList(sql, DynaModel.class, null, rows);
	}
	
	/**
	 * 查询一个对象列表结果,列表中的每一行为一个DynaModel。
	 * @param rows 返回的记录数量
	 * @return 查询固定的记录数
	 */
	public <T> List<T> queryForList(String sql, Class<T> cls, int rows)
	{
		return queryForList(sql, cls, null, rows);
	}
	
	/**
	 * 查询一个对象列表结果,列表中的每一行为一个DynaModel。
	 *
	 * @param sql      SQL语句
	 * @param startRow 起始的行数
	 * @param rows     记录的数量
	 * @return 查询所有结果并。
	 */
	public <T> List<T> queryForList(String sql, Class<T> cls, int startRow, int rows)
	{
		return queryForList(sql, cls, null, startRow, rows);
	}
	
	/**
	 * 查询一个对象列表结果,列表中的每一行为一个DynaModel。
	 * @param sql  SQL语句
	 * @param args 参数中的值
	 * @return 查询所有结果。
	 */
	public List<DynaModel> queryForList(String sql, Object[] args)
	{
		return queryForList(sql, DynaModel.class, args);
	}
	
	/**
	 * 查询一个对象列表结果,列表中的每一行为一个DynaModel。
	 * @param sql  SQL语句
	 * @param args 参数中的值
	 * @return 查询所有结果。
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> queryForList(String sql, Class<T> cls, Object[] args)
	{
		try
		{
			logger.debug("开始执行 [sql= " + sql + "]");
			long beginTime = System.currentTimeMillis();
			
			List<T> list = Lists.newArrayList();
			List<Map<String, Object>> list0 = jdbcTemplate.queryForList(sql, args);
			
			for (Iterator<Map<String, Object>> iterator = list0.iterator(); iterator.hasNext();)
			{
				Map<String, Object> map = (Map<String, Object>) iterator.next();
				Map<String, Object> temp = Maps.newHashMap();
				for (Iterator<String> keys = map.keySet().iterator(); keys.hasNext();)
				{
					String key = keys.next();
					temp.put(key.toLowerCase(), map.get(key));
					
				}
				if(cls.getName().equals(Map.class.getName())){
					list.add((T) temp);
					continue;
				}
				T t = cls.newInstance();
				if (t instanceof DynaModel)
				{
					((DynaModel) t).putAll(temp);
				}else{
					BeanUtils.populate(t, temp);
				}
				list.add(t);
			}
			
			long time = System.currentTimeMillis() - beginTime;
			logger.debug("执行完成 [time=" + time + " millisecond]");
			
			if (time > 1000)
			{
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[time=" + time + " millisecond]");
			}
			
			return list;
		}
		catch (Exception ex)
		{
			logger.error(ex.getMessage());
			return null;
		}
	}
	
	/**
	 * 查询一个对象列表结果,列表中的每一行为一个DynaModel。
	 *
	 * @param sql  SQL语句
	 * @param args 参数中的值
	 * @param rows 返回的记录数量*
	 * @return 查询固定的记录数
	 */
	public <T>List<T> queryForList(String sql, Class<T> cls, Object[] args, int rows)
	{
		return queryForList(sql, cls, args, 0, rows);
	}
	
	/**
	 * 查询一个对象列表结果,列表中的每一行为一个DynaModel。
	 * @param sql      SQL语句
	 * @param args     参数中的值
	 * @param startRow 起始的行数
	 * @param rows     记录的数量
	 * @return 查询所有结果并。
	 */
	public <T>List<T> queryForList(String sql, Class<T> cls, Object[] args, int startRow, int rows)
	{
		if (databaseType == DatabaseType.ORACLE) //是oracle数据库
		{
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append("select * from ( select row_.*, rownum rownum_ from ( ");
			sqlBuffer.append(sql);
			sqlBuffer.append(" ) row_ where rownum <= " + (startRow + rows) + ") where rownum_ > " + startRow + "");
			return queryForList(sqlBuffer.toString(), cls, args);
		}
		else if (databaseType == DatabaseType.MYSQL) //是mysql数据库
		{
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append(sql);
			sqlBuffer.append(" limit " + startRow + "," + rows + "");
			return queryForList(sqlBuffer.toString(), cls, args);
		}
		else if (databaseType == DatabaseType.MSSQL) //是mssql数据库
		{
			/*
			            注意，此处并没有使用MSSQL特有的数据库分页语句，是因为MSSQL的JTDS驱动的滚动结果集是直接
			            使用的服务器端的CURSOR，可以直接在服务器端进行记录的移动，所以使用JTDS驱动时，
			            可以直接使用滚动结果集，性能不会太差。
			            操作SQLServer时要使用JTDS驱动，不要使用MS自己的驱动
			*/
			return queryForList(sql, cls, args, startRow, rows);
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
			return queryForList(sqlBuffer.toString(), cls, args);
		}
		else if (databaseType == DatabaseType.POSTGRESQL) //是PostgreSQL数据库
		{
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append(sql);
			sqlBuffer.append(" limit " + rows + " offset " + (startRow - 1) + "");
			return queryForList(sqlBuffer.toString(), cls, args);
		}
		else
		{
			//是其它数据库，暂时使用滚动结果集进行支持
			return queryForList(sql, cls, args, startRow, rows);
		}
	}
	
	/**
	 * 查询一个整型结果。
	 *
	 * @param sql SQL语句
	 * @return 查询的第一行的第一个字段的整型值。
	 */
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
	public int queryInt(String sql, Object[] args)
	{
		try
		{
			logger.debug("开始执行 [sql= " + sql + "]");
			long beginTime = System.currentTimeMillis();
			
			int result = jdbcTemplate.queryForInt(sql, args);
			
			long time = System.currentTimeMillis() - beginTime;
			logger.debug("执行完成 [time=" + time + " millisecond]");
			
			if (time > 1000)
			{
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[time=" + time + " millisecond]");
			}
			
			return result;
		}
		catch (Exception ex)
		{
			throw new JdbcException("", ex);
		}
	}
	
	/**
	 * 返回一个数字数组
	 *
	 * @param sql SQL语句
	 * @return 查询的多条记录第一个字段的整型值。
	 */
	public Integer[] queryIntArray(String sql)
	{
		return queryIntArray(sql, null);
	}
	
	/**
	 * 返回一个数字数组
	 * @param sql  SQL语句
	 * @param args 参数中的值*
	 * @return 查询的多条记录第一个字段的整型值。
	 */
	public Integer[] queryIntArray(String sql, Object[] args)
	{
		try
		{
			logger.debug("开始执行 [sql= " + sql + "]");
			long beginTime = System.currentTimeMillis();
			
			List<Integer> resultList = jdbcTemplate.queryForList(sql, args, Integer.class);
			long time = System.currentTimeMillis() - beginTime;
			logger.debug("执行完成 [time=" + time + " millisecond]");
			
			if (time > 1000)
			{
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[time=" + time + " millisecond]");
			}
			Integer[] result = new Integer[resultList.size()];
	    	int i = 0;
	    	for (Iterator<Integer> item = resultList.iterator(); item.hasNext();)
			{
				result[i++] = item.next();
				
			}
			return result;
		}
		catch (Exception ex)
		{
			throw new JdbcException("", ex);
		}
	}
	
	/**
	 * 查询一个长整型结果。
	 *
	 * @param sql SQL语句
	 * @return 查询的第一行的第一个字段的长整型值。
	 */
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
	public long queryLong(String sql, Object[] args)
	{
		try
		{
			logger.debug("开始执行 [sql= " + sql + "]");
			long beginTime = System.currentTimeMillis();
			
			long result = jdbcTemplate.queryForLong(sql, args);
			
			long time = System.currentTimeMillis() - beginTime;
			logger.debug("执行完成 [time=" + time + " millisecond]");
			
			if (time > 1000)
			{
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[time=" + time + " millisecond]");
			}
			
			return result;
		}
		catch (Exception ex)
		{
			throw new JdbcException("", ex);
		}
	}
	
	/**
	 * 返回一个长整型数组
	 *
	 * @param sql SQL语句
	 * @return 查询的多条记录第一个字段的长整型值。
	 */
	public Long[] queryLongArray(String sql)
	{
		return queryLongArray(sql, null);
	}
	
	/**
	 * 返回一个长整型数组
	 * @param sql  SQL语句
	 * @param args 参数中的值*
	 * @return 查询的多条记录第一个字段的长整型值。
	 */
	public Long[] queryLongArray(String sql, Object[] args)
	{
		try
		{
			logger.debug("开始执行 [sql= " + sql + "]");
			long beginTime = System.currentTimeMillis();
			
			List<Long> resultList = jdbcTemplate.queryForList(sql, args, Long.class);
			
			long time = System.currentTimeMillis() - beginTime;
			logger.debug("执行完成 [time=" + time + " millisecond]");
			
			if (time > 1000)
			{
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[time=" + time + " millisecond]");
			}
			
			Long[] result = (Long[]) resultList.toArray();
			
			return result;
		}
		catch (Exception ex)
		{
			throw new JdbcException("", ex);
		}
	}
	
	/**
	 * 查询一条记录，返回类型为DynaModel，
	 *
	 * @param sql SQL语句
	 * @return 查询的第一行的结果,反回结果中的字段名都为小写
	 * @throws SQLException 
	 */
	public DynaModel queryMap(String sql) throws SQLException
	{
		return queryMap(sql, DynaModel.class, null);
	}
	
	/**
	 * 查询一条记录，返回类型为DynaModel。
	 *
	 * @param sql  SQL语句
	 * @param args 参数中的值
	 * @return 查询的第一行的结果,反回结果中的字段名都为小写。
	 * @throws SQLException 
	 */
	public DynaModel queryMap(String sql, Object[] args) throws SQLException
	{
		return queryMap(sql, DynaModel.class, args);
	}
	
	/**
	 * 查询一条记录，返回类型为DynaModel。
	 *
	 * @param sql  SQL语句
	 * @param args 参数中的值
	 * @return 查询的第一行的结果,反回结果中的字段名都为小写。
	 */
	@SuppressWarnings("unchecked")
	public <T>T queryMap(String sql, Class<T> cls, Object[] args) throws SQLException
	{
		logger.debug("开始执行 [sql= " + sql + "]");
		long beginTime = System.currentTimeMillis();
		
		Map<String, Object> map = null;
		try
		{
			map = jdbcTemplate.queryForMap(sql, args);
		}
		catch (EmptyResultDataAccessException e)
		{
			logger.error(e.getMessage());
			return null;
		}
		catch (BadSqlGrammarException e)
		{
			logger.error(e.getMessage());
			throw e.getSQLException();
		}
		
		Map<String, Object> temp = Maps.newHashMap();
		for (Iterator<String> keys = map.keySet().iterator(); keys.hasNext();)
		{
			String key = keys.next();
			temp.put(key.toLowerCase(), map.get(key));
		}
		
		if(cls.getName().equals(Map.class.getName())){
			return (T)temp;
		}
		
		T result;
		try
		{
			result = cls.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			logger.error(e.getMessage());
			return null;
		}
		boolean mark = false;
		if (result instanceof DynaModel)
		{
			mark = true;
		}
		if(mark){
			((DynaModel)result).putAll(temp);
		}else{
			try
			{
				BeanUtils.populate(result, temp);
			}
			catch (IllegalAccessException | InvocationTargetException e)
			{
				logger.error(e.getMessage());
				return null;
			}
		}
		
		long time = System.currentTimeMillis() - beginTime;
		logger.debug("执行完成 [time=" + time + " millisecond]");
		
		if (time > 1000)
		{
			logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[time=" + time + " millisecond]");
		}
		return result;
	}
	
	/**
	 * 查询一个分页列表结果。
	 *
	 * @param sql        SQL语句
	 * @param curPage    当前页数
	 * @param numPerPage 每页显示的记录数
	 * @return 分页对象
	 */
	public DBPage<DynaModel> queryPage(String sql, int curPage, int numPerPage)
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
	public DBPage<DynaModel> queryPage(String sql, Object[] args, int curPage, int numPerPage)
	{
		return queryPage(sql, DynaModel.class, args, curPage, numPerPage);
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
	public <T> DBPage<T> queryPage(String sql, Class<T> cls, Object[] args, int curPage, int numPerPage)
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
		DBPage<T> page = new DBPage<T>(curPage, numPerPage);
		//设置总的记录数
		page.setTotalRows(totalRows);
		
		//查询对应页的数据
		int startIndex = page.getStartIndex();
		int endIndex = page.getLastIndex();
		int rows = endIndex - startIndex;
		rows = (rows < 0) ? 0 : rows;
		
		List<T> list = queryForList(sql, cls, args, startIndex, rows);
		//设置数据
		page.setData(list);
		return page;
	}
	
	/**
	 * 查询一个字符串结果。
	 * @param sql SQL语句
	 * @return 查询的第一行的第一个字段的字符串值。
	 */
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
	public String queryString(String sql, Object[] args)
	{
		try
		{
			logger.debug("开始执行 [sql= " + sql + "]");
			long beginTime = System.currentTimeMillis();
			
			String result = jdbcTemplate.queryForObject(sql, args, String.class);
			
			long time = System.currentTimeMillis() - beginTime;
			logger.debug("执行完成 [time=" + time + " millisecond]");
			
			if (time > 1000)
			{
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[time=" + time + " millisecond]");
			}
			return result;
		}
		catch (Exception ex)
		{
			throw new JdbcException("", ex);
		}
	}
	
	/**
	 * 返回一个字符串数组
	 *
	 * @param sql SQL语句
	 * @return 查询的多条记录第一个字段的字符串值。
	 */
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
	public String[] queryStringArray(String sql, Object[] args)
	{
		try
		{
			logger.debug("开始执行 [sql= " + sql + "]");
			long beginTime = System.currentTimeMillis();
			
			List<String> list = jdbcTemplate.queryForList(sql, args, String.class);
			
			long time = System.currentTimeMillis() - beginTime;
			logger.debug("执行完成 [time=" + time + " millisecond]");
			
			if (time > 1000)
			{
				logger.warn("执行 [sql= " + sql + "]时间过长，当前执行时间[time=" + time + " millisecond]");
			}
			
			String[] result = (String[]) list.toArray();
			
			return result;
		}
		catch (Exception ex)
		{
			throw new JdbcException("", ex);
		}
	}
	
	/**
	 * 执行指定的sql并返回更新的记录数。
	 * @param sql SQL语句
	 * @return 更新的记录数
	 */
	public int update(String sql)
	{
		return update(sql, null);
	}
	
	/**
	 * 更新表中的一条记录信息
	 * @param tableName     需要更新的表名
	 * @param data          需要更新的信息
	 * @param identify      识别字段的名称
	 * @param identifyValue 识别字段的值
	 * @return
	 */
	public int update(String tableName, DynaModel data, String identify, Object identifyValue)
	{
		return update(tableName, data, new String[] { identify }, new Object[] { identifyValue });
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
	public int update(String tableName, DynaModel data, String[] identifys, Object[] identifyValues)
	{
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("update " + tableName + " set ");
		
		int i = 0;
		for (int j = 0; j < identifys.length; j++)
		{
			data.remove(identifys[j]);
		}
		ArrayList<Object> valueList = new ArrayList<Object>();
		for (Iterator<String> iter = data.toMap().keySet().iterator(); iter.hasNext();)
		{
			i++;
			String key = iter.next();
			valueList.add(data.toMap().get(key));
			
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
		
		return update(sqlBuffer.toString(), valueList.toArray());
	}
}
