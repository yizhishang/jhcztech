package com.jhcz.base.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import com.jhcz.base.jdbc.exception.JdbcException;

/**
 * 描述:	 数据库连接管理
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-4
 * 创建时间: 21:32:25
 */

public final class ConnManager
{
    
    private static Configure configure = Configure.getInstance();
    
    /**
     * 开始数据库事务
     * @param conn
     */
    public static void begin(Connection conn)
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
            throw new JdbcException(ex);
        }
    }
    
    /**
     * 关闭数据库连接
     * @param conn
     */
    public static void close(Connection conn)
    {
        try
        {
            if (conn != null && !conn.isClosed())
            {
                conn.close();
            }
        }
        catch (Exception ex)
        {
            throw new JdbcException("", ex);
        }
    }
    
    /**
     * 提交数据库事务
     * @param conn
     */
    public static void commit(Connection conn)
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
     * 获得缺省的数据源的数据库连接
     *
     * @return conn
     */
    public static Connection getConnection()
    {
        try
        {
            DataSource dataSource = configure.getDataSource();
            Connection conn = dataSource.getConnection();
            return conn;
        }
        catch (SQLException ex)
        {
            throw new JdbcException(ex);
        }
    }
    
    /**
     * 根据datasource.xml文件中配置的数据源ID，得到对应的数据库连接
     *
     * @param id 数据源ID
     * @return conn
     */
    public static Connection getConnection(String id)
    {
        try
        {
            DataSource dataSource = configure.getDataSource(id);
            Connection conn = dataSource.getConnection();
            return conn;
        }
        catch (SQLException ex)
        {
            throw new JdbcException(ex);
        }
    }
    
    /**
     * 描述：获得数据库单连接
     * 作者：刘宝
     * 时间：2011-11-14 下午01:17:35
     * @param id
     * @return
     */
    public static Connection getSingleConn(String id)
    {
        try
        {
            HashMap<String, Object> xmlMap = configure.getDbConnXmlMap(id);
            String driverName = (String) xmlMap.get("driver-name");
            String url = (String) xmlMap.get("url");
            String user = (String) xmlMap.get("user");
            String password = (String) xmlMap.get("password");
            Class.forName(driverName).newInstance();
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        }
        catch (Exception ex)
        {
            throw new JdbcException(ex);
        }
    }
    
    /**
     * 回滚数据库事务
     * @param conn
     */
    public static void rollback(Connection conn)
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
    
}
