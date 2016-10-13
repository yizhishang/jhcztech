package com.jhcz.base.jdbc;

import java.util.List;

import com.jhcz.base.jdbc.session.Session;
import com.jhcz.base.jdbc.session.SessionFactory;
import com.jhcz.base.util.StringHelper;

/**
 * 描述:	 JDBC操作模板
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-4
 * 创建时间: 23:11:24
 */
public class JdbcTemplate
{
    private String id = "";
    private String generatedKeys = "";

    /**
     * 构造数据操作对象
     */
    public JdbcTemplate()
    {
    }

    /**
     * 根据datasource.xml文件中配置的数据源ID，构造数据操作对象
     *
     * @param id 数据源的ID
     */
    public JdbcTemplate(String id)
    {
        this.id = id;
    }


    /**
     * 批量执行更新并返回每次的更新记录数
     *
     * @param sql  SQL语句
     * @param args 参数中的值
     * @return
     */
    public int[] batchUpdate(String sql, Object[][] args)
    {
        Session session = null;
        try
        {
            session = getSession();
            return session.batchUpdate(sql, args);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }

    /**
     * 批量执行更新并返回每次的更新记录数
     *
     * @param sqlArray SQL语句数组
     * @return 每次执行更新的记录数数组
     */
    public int[] batchUpdate(String[] sqlArray)
    {
        Session session = null;
        try
        {
            session = getSession();
            return session.batchUpdate(sqlArray);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }

    /**
     * 删除表中的记录
     *
     * @param tableName     需要删除的记录的表名
     * @param identify      识别字段的名称
     * @param identifyValue 识别字段的值
     * @return
     */
    public void delete(String tableName, String identify, Object identifyValue)
    {
        Session session = null;
        try
        {
            session = getSession();
            session.delete(tableName, identify, identifyValue);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }
    
    /**
     * 描述：获取自生成主键值
     * 作者：袁永君
     * 时间：2013年7月23日 下午5:48:52
     * @return
     */
    public String getGeneratedKeys()
    {
    	return generatedKeys;
    }

    private Session getSession()
    {
        if (!StringHelper.isEmpty(id))
            return SessionFactory.getSession(id);
        else
            return SessionFactory.getSession();
    }

    /**
     * 在相应的表中增加一条记录
     *
     * @param tableName 需要添加的表名
     * @param data      需要添加的信息
     * @return
     */
    public void insert(String tableName, DataRow data)
    {
        Session session = null;
        try
        {
            session = getSession();
            session.insert(tableName, data);
            generatedKeys = session.getGeneratedKeys();
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }


    /**
     * 查询一个对象列表结果,列表中的每一行为一个DataRow。
     * @param sql SQL语句
     * @return 查询所有结果列表。
     */
    public List<Object> query(String sql)
    {
        Session session = null;
        try
        {
            session = getSession();
            return session.query(sql);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }


    /**
     * 查询一个对象列表结果,列表中的每一行为一个DataRow。
     * @param rows 返回的记录数量
     * @return 查询固定的记录数
     */
    public List<Object> query(String sql, int rows)
    {
        Session session = null;
        try
        {
            session = getSession();
            return session.query(sql, rows);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }

    /**
     * 查询一个对象列表结果,列表中的每一行为一个DataRow。
     *
     * @param sql      SQL语句
     * @param startRow 起始的行数
     * @param rows     记录的数量
     * @return 查询所有结果并。
     */
    public List<Object> query(String sql, int startRow, int rows)
    {
        Session session = null;
        try
        {
            session = getSession();
            return session.query(sql, startRow, rows);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }

    /**
     * 查询一个对象列表结果,列表中的每一行为一个DataRow。
     *
     * @param sql  SQL语句
     * @param args 参数中的值
     * @return 查询所有结果。
     */
    public List<Object> query(String sql, Object[] args)
    {
        Session session = null;
        try
        {
            session = getSession();
            return session.query(sql, args);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }

    /**
     * 查询一个对象列表结果,列表中的每一行为一个DataRow。
     *
     * @param sql  SQL语句
     * @param args 参数中的值
     * @param rows 返回的记录数量*
     * @return 查询固定的记录数
     */
    public List<Object> query(String sql, Object[] args, int rows)
    {
        Session session = null;
        try
        {
            session = getSession();
            return session.query(sql, args, rows);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }


    /**
     * 查询一个对象列表结果,列表中的每一行为一个DataRow。
     * @param sql      SQL语句
     * @param args     参数中的值
     * @param startRow 起始的行数
     * @param rows     记录的数量
     * @return 查询所有结果并。
     */
    public List<Object> query(String sql, Object[] args, int startRow, int rows)
    {
        Session session = null;
        try
        {
            session = getSession();
            return session.query(sql, args, startRow, rows);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
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
        Session session = null;
        try
        {
            session = getSession();
            return session.queryInt(sql);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
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
        Session session = null;
        try
        {
            session = getSession();
            return session.queryInt(sql, args);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }


    /**
     * 返回一个数字数组
     *
     * @param sql SQL语句
     * @return 查询的多条记录第一个字段的整型值。
     */
    public int[] queryIntArray(String sql)
    {
        Session session = null;
        try
        {
            session = getSession();
            return session.queryIntArray(sql);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }


    /**
     * 返回一个数字数组
     *
     * @param sql  SQL语句
     * @param args 参数中的值*
     * @return 查询的多条记录第一个字段的整型值。
     */
    public int[] queryIntArray(String sql, Object[] args)
    {
        Session session = null;
        try
        {
            session = getSession();
            return session.queryIntArray(sql, args);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
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
        Session session = null;
        try
        {
            session = getSession();
            return session.queryLong(sql);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
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
        Session session = null;
        try
        {
            session = getSession();
            return session.queryLong(sql, args);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }

    /**
     * 返回一个长整型数组
     *
     * @param sql SQL语句
     * @return 查询的多条记录第一个字段的长整型值。
     */
    public long[] queryLongArray(String sql)
    {
        Session session = null;
        try
        {
            session = getSession();
            return session.queryLongArray(sql);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }

    /**
     * 返回一个长整型数组
     *
     * @param sql  SQL语句
     * @param args 参数中的值*
     * @return 查询的多条记录第一个字段的长整型值。
     */
    public long[] queryLongArray(String sql, Object[] args)
    {
        Session session = null;
        try
        {
            session = getSession();
            return session.queryLongArray(sql, args);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }


    /**
     * 查询一条记录，返回类型为DataRow，
     *
     * @param sql SQL语句
     * @return 查询的第一行的结果,反回结果中的字段名都为小写
     */
    public DataRow queryMap(String sql)
    {
        Session session = null;
        try
        {
            session = getSession();
            return session.queryMap(sql);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }


    /**
     * 查询一条记录，返回类型为DataRow。
     *
     * @param sql  SQL语句
     * @param args 参数中的值
     * @return 查询的第一行的结果,反回结果中的字段名都为小写。
     */
    public DataRow queryMap(String sql, Object[] args)
    {
        Session session = null;
        try
        {
            session = getSession();
            return session.queryMap(sql, args);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
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
    public DBPage queryPage(String sql, int curPage, int numPerPage)
    {
        Session session = null;
        try
        {
            session = getSession();
            return session.queryPage(sql, curPage, numPerPage);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
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
    public DBPage queryPage(String sql, Object[] args, int curPage, int numPerPage)
    {
        Session session = null;
        try
        {
            session = getSession();
            return session.queryPage(sql, args, curPage, numPerPage);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }


    /**
     * 查询一个字符串结果。
     * @param sql SQL语句
     * @return 查询的第一行的第一个字段的字符串值。
     */
    public String queryString(String sql)
    {
        Session session = null;
        try
        {
            session = getSession();
            return session.queryString(sql);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
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
        Session session = null;
        try
        {
            session = getSession();
            return session.queryString(sql, args);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
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
        Session session = null;
        try
        {
            session = getSession();
            return session.queryStringArray(sql);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
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
        Session session = null;
        try
        {
            session = getSession();
            return session.queryStringArray(sql, args);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }

    /**
     * 执行指定的sql并返回更新的记录数。
     *
     * @param sql SQL语句
     * @return 更新的记录数
     */
    public int update(String sql)
    {
        Session session = null;
        try
        {
            session = getSession();
            return session.update(sql);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
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
    public void update(String tableName, DataRow data, String identify, Object identifyValue)
    {
        Session session = null;
        try
        {
            session = getSession();
            session.update(tableName, data, identify, identifyValue);
            generatedKeys = session.getGeneratedKeys();
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
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
    public void update(String tableName, DataRow data, String[] identifys, Object[] identifyValues)
    {
    	Session session = null;
    	try
    	{
    		session = getSession();
    		session.update(tableName, data, identifys, identifyValues);
    		generatedKeys = session.getGeneratedKeys();
    	}
    	finally
    	{
    		if (session != null)
    		{
    			session.close();
    			session = null;
    		}
    	}
    }
    
    /**
     * 执行指定的sql并返回更新的记录数。
     *
     * @param sql  SQL语句
     * @param args 参数中的值
     * @return 更新的记录数
     */
    public int update(String sql, Object[] args)
    {
        Session session = null;
        try
        {
            session = getSession();
            return session.update(sql, args);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }

}
